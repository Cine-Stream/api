#!/usr/bin/env pwsh
<#
.SYNOPSIS
  Agente de commits organizado: agrupa as mudancas por area e cria commits
  no formato Conventional Commits.

.DESCRIPTION
  Sem -Apply apenas mostra o plano. Com -Apply faz o stage de cada grupo e
  commita na ordem das regras. Aborta se ja existir algo no stage, para nao
  misturar mudancas de terceiros.

.EXAMPLE
  .\scripts\commit-organizado.ps1

.EXAMPLE
  .\scripts\commit-organizado.ps1 -Apply
#>
[CmdletBinding()]
param(
    [switch]$Apply
)

$ErrorActionPreference = 'Stop'

$repo = git rev-parse --show-toplevel
if (-not $repo) {
    throw 'Nao esta dentro de um repositorio Git.'
}
Push-Location $repo

try {
    # Regras: a primeira que casar define tipo, escopo, mensagem e ordem.
    $rules = @(
        @{ Pattern = '^src/main/java/com/example/cinestream/armazenamento/|^pom\.xml$'; Type = 'feat'; Scope = 's3'; Message = 'integra o upload de avatar ao bucket S3'; Detail = 'Adiciona cliente S3 configuravel por ambiente, compativel com MinIO/LocalStack, e mantem o conteudo fora do banco.'; Order = 10 }
        @{ Pattern = '^src/main/java/com/example/cinestream/perfil/'; Type = 'feat'; Scope = 'perfil'; Message = 'expoe upload e remocao do avatar do perfil'; Detail = 'Salva a imagem no S3 e atualiza perfil.avatar_url no Neon; valida tipo, tamanho e existencia do perfil.'; Order = 20 }
        @{ Pattern = '^src/main/java/com/example/cinestream/qualidaderesolucao/'; Type = 'feat'; Scope = 'qualidade-resolucao'; Message = 'ajusta o CRUD de qualidade de resolucao'; Detail = 'Mantem o CRUD alinhado a tabela qualidade_resolucao.'; Order = 30 }
        @{ Pattern = '^src/main/java/com/example/cinestream/assinatura/'; Type = 'feat'; Scope = 'assinatura'; Message = 'ajusta o fluxo de historico de assinatura'; Detail = 'Mantem a chave composta e os DTOs do historico de assinatura.'; Order = 40 }
        @{ Pattern = '^src/main/java/.*/common/DataSeeder\.java$'; Type = 'fix'; Scope = 'seed'; Message = 'ajusta a carga inicial'; Detail = 'Mantem os dados de exemplo compativeis com o schema atual.'; Order = 50 }
        @{ Pattern = '^src/main/java/.*/common/ApiExceptionHandler\.java$'; Type = 'fix'; Scope = 'api'; Message = 'ajusta o tratamento de erros HTTP'; Detail = 'Traduz falhas de upload e multipart para 413/400 com mensagem clara.'; Order = 60 }
        @{ Pattern = '^src/main/resources/|^\.env\.example$'; Type = 'fix'; Scope = 'config'; Message = 'ajusta a configuracao da aplicacao'; Detail = 'Documenta as variaveis do S3, limita o upload a 5MB e mantem o cache local por padrao.'; Order = 70 }
        @{ Pattern = '^scripts/|^AGENTS\.md$'; Type = 'chore'; Scope = 'commits'; Message = 'atualiza a automacao de commits'; Detail = 'Registra a convencao de commits e o script que agrupa as mudancas por area.'; Order = 80 }
    )

    function Get-ScopeFromPath([string]$Path) {
        if ($Path -match '^src/main/java/com/example/cinestream/([^/]+)/') {
            return $Matches[1]
        }
        if ($Path -match '^src/(main|test)/') {
            return 'config'
        }
        return 'outros'
    }

    $staged = git diff --cached --name-only
    if ($staged) {
        throw "Existem arquivos no stage. Rode 'git reset' para o script agrupar tudo."
    }

    $changes = @()
    foreach ($line in (git status --porcelain=v1 -uall)) {
        if ($line.Length -lt 4) { continue }

        $path = $line.Substring(3).Trim()
        if ($path -match '^"(.*)"$') {
            $path = $Matches[1]
        }
        if ($path -match ' -> ') {
            $path = ($path -split ' -> ')[-1].Trim('"')
        }
        if ($path -eq '.env') {
            Write-Warning 'Ignorando .env (credenciais).'
            continue
        }
        if ($path -match '^target/') {
            continue
        }

        $changes += [pscustomobject]@{ Code = $line.Substring(0, 2); Path = $path }
    }

    if ($changes.Count -eq 0) {
        Write-Host 'Nada para commitar.' -ForegroundColor Green
        return
    }

    $groups = @()
    $unmatched = @()

    foreach ($change in $changes) {
        $rule = $rules | Where-Object { [regex]::IsMatch($change.Path, $_.Pattern) } | Select-Object -First 1
        if ($rule) {
            $group = $groups | Where-Object { $_.Key -eq "$($rule.Type)|$($rule.Scope)" } | Select-Object -First 1
            if (-not $group) {
                $group = [pscustomobject]@{
                    Key     = "$($rule.Type)|$($rule.Scope)"
                    Type    = $rule.Type
                    Scope   = $rule.Scope
                    Message = $rule.Message
                    Detail  = $rule.Detail
                    Order   = $rule.Order
                    Paths   = @()
                }
                $groups += $group
            }
            $group.Paths += $change.Path
        }
        else {
            $unmatched += $change.Path
        }
    }

    foreach ($byScope in ($unmatched | Group-Object { Get-ScopeFromPath $_ })) {
        $groups += [pscustomobject]@{
            Key     = "refactor|$($byScope.Name)"
            Type    = 'refactor'
            Scope   = $byScope.Name
            Message = "ajusta $($byScope.Name)"
            Detail  = "Ajustes em $($byScope.Name) que nao se encaixam nas regras especificas."
            Order   = 900
            Paths   = @($byScope.Group)
        }
    }

    $groups = $groups | Sort-Object Order

    Write-Host ''
    Write-Host "Plano de commits ($($groups.Count) grupo(s)):" -ForegroundColor Cyan
    foreach ($group in $groups) {
        Write-Host ''
        Write-Host ("{0}({1}): {2}" -f $group.Type, $group.Scope, $group.Message) -ForegroundColor Yellow
        $group.Paths | Sort-Object | ForEach-Object { Write-Host "  - $_" }
    }

    if (-not $Apply) {
        Write-Host ''
        Write-Host 'Nada foi commitado. Rode com -Apply para aplicar.' -ForegroundColor DarkGray
        return
    }

    git diff --check
    if ($LASTEXITCODE -ne 0) {
        throw "git diff --check encontrou problemas de espaco em branco."
    }

    foreach ($group in $groups) {
        Write-Host ''
        Write-Host ("Commitando {0}({1})..." -f $group.Type, $group.Scope) -ForegroundColor Cyan

        git add -- @($group.Paths)
        if ($LASTEXITCODE -ne 0) {
            throw "Falha no git add do grupo $($group.Key)."
        }

        $fileList = ($group.Paths | Sort-Object | ForEach-Object { "- $_" }) -join "`n"
        $body = "$($group.Detail)`n`nArquivos alterados:`n`n$fileList"
        $subject = "{0}({1}): {2}" -f $group.Type, $group.Scope, $group.Message

        git commit -m $subject -m $body
        if ($LASTEXITCODE -ne 0) {
            throw "Falha no commit do grupo $($group.Key)."
        }
    }

    Write-Host ''
    Write-Host 'Commits criados:' -ForegroundColor Green
    git --no-pager log --oneline -n $groups.Count
    Write-Host ''
    git status --short
}
finally {
    Pop-Location
}

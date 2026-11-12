# Agente de commits

Este repositório usa [Conventional Commits](https://www.conventionalcommits.org/).
Toda alteração deve virar commits pequenos, um por área, com o corpo explicando
o motivo da mudança.

## Formato

```text
<tipo>(<escopo>): <resumo no imperativo, minúsculo, sem ponto final>

- o que mudou e por quê
- efeitos colaterais, decisões e limites
```

Tipos aceitos: `feat`, `fix`, `refactor`, `docs`, `test`, `chore`, `style`, `perf`.

Escopos deste projeto: `model`, `qualidade-resolucao`, `assinatura`, `perfil`,
`s3`, `seed`, `api`, `config`, `commits`.

## Regras

- Um commit por área. Não misture, por exemplo, `model` com `config`.
- `.env` e qualquer credencial nunca entram no commit (use `.env.example`).
- Antes de commitar, revise `git status` e `git diff --check`.
- Não reescreva histórico já publicado sem pedido explícito.
- Mensagens em português, sem acentos no assunto (o histórico atual segue assim).

## Como commitar

```powershell
# mostra o plano de commits agrupado por área
.\scripts\commit-organizado.ps1

# aplica os commits na ordem das regras
.\scripts\commit-organizado.ps1 -Apply
```

O script aborta se houver algo já no stage: rode `git reset` primeiro para que
ele controle o agrupamento.

Se a política de execução do Windows bloquear o script, rode:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\commit-organizado.ps1 -Apply
```

## Ordem sugerida

1. `model`
2. `qualidade-resolucao`
3. `assinatura`
4. `perfil` / `s3`
5. `seed`
6. `api`
7. `config`
8. `docs` / `commits`

## Checklist do agente

- [ ] `git status` limpo depois dos commits.
- [ ] Cada commit compila sozinho quando possível.
- [ ] Nenhum arquivo de credencial ou `target/` no commit.
- [ ] Corpo do commit explica o motivo, não só a lista de arquivos.

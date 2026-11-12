package com.example.cinestream.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.cinestream.assinante.Assinante;
import com.example.cinestream.assinante.AssinanteRepository;
import com.example.cinestream.assinatura.Assinatura;
import com.example.cinestream.assinatura.AssinaturaId;
import com.example.cinestream.assinatura.AssinaturaRepository;
import com.example.cinestream.classificacaomaturidade.ClassificacaoDescricao;
import com.example.cinestream.classificacaomaturidade.ClassificacaoMaturidade;
import com.example.cinestream.classificacaomaturidade.ClassificacaoMaturidadeRepository;
import com.example.cinestream.dispositivo.Dispositivo;
import com.example.cinestream.dispositivo.DispositivoRepository;
import com.example.cinestream.episodio.Episodio;
import com.example.cinestream.episodio.EpisodioId;
import com.example.cinestream.episodio.EpisodioRepository;
import com.example.cinestream.fatura.Fatura;
import com.example.cinestream.fatura.FaturaRepository;
import com.example.cinestream.genero.Genero;
import com.example.cinestream.genero.GeneroRepository;
import com.example.cinestream.obra.Obra;
import com.example.cinestream.obra.ObraRepository;
import com.example.cinestream.obragenero.ObraGenero;
import com.example.cinestream.obragenero.ObraGeneroId;
import com.example.cinestream.obragenero.ObraGeneroRepository;
import com.example.cinestream.obraprofissional.FuncaoProfissional;
import com.example.cinestream.obraprofissional.ObraProfissional;
import com.example.cinestream.obraprofissional.ObraProfissionalId;
import com.example.cinestream.obraprofissional.ObraProfissionalRepository;
import com.example.cinestream.perfil.Perfil;
import com.example.cinestream.perfil.PerfilId;
import com.example.cinestream.perfil.PerfilRepository;
import com.example.cinestream.plano.Plano;
import com.example.cinestream.plano.PlanoRepository;
import com.example.cinestream.profissional.Profissional;
import com.example.cinestream.profissional.ProfissionalRepository;
import com.example.cinestream.qualidaderesolucao.QualidadeResolucao;
import com.example.cinestream.qualidaderesolucao.QualidadeResolucaoDescricao;
import com.example.cinestream.qualidaderesolucao.QualidadeResolucaoRepository;
import com.example.cinestream.statuscobranca.StatusCobranca;
import com.example.cinestream.statuscobranca.StatusCobrancaDescricao;
import com.example.cinestream.statuscobranca.StatusCobrancaRepository;
import com.example.cinestream.visualizacao.Visualizacao;
import com.example.cinestream.visualizacao.VisualizacaoRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    /** CPF no formato CHAR(14) usado pelo banco. */
    private static final String SEED_CPF = "000.000.000-01";

    private final AssinanteRepository assinanteRepository;
    private final PlanoRepository planoRepository;
    private final AssinaturaRepository assinaturaRepository;
    private final QualidadeResolucaoRepository qualidadeRepository;
    private final ClassificacaoMaturidadeRepository classificacaoRepository;
    private final PerfilRepository perfilRepository;
    private final ObraRepository obraRepository;
    private final GeneroRepository generoRepository;
    private final ObraGeneroRepository obraGeneroRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ObraProfissionalRepository obraProfissionalRepository;
    private final EpisodioRepository episodioRepository;
    private final DispositivoRepository dispositivoRepository;
    private final VisualizacaoRepository visualizacaoRepository;
    private final FaturaRepository faturaRepository;
    private final StatusCobrancaRepository statusCobrancaRepository;

    public DataSeeder(
            AssinanteRepository assinanteRepository,
            PlanoRepository planoRepository,
            AssinaturaRepository assinaturaRepository,
            QualidadeResolucaoRepository qualidadeRepository,
            ClassificacaoMaturidadeRepository classificacaoRepository,
            PerfilRepository perfilRepository,
            ObraRepository obraRepository,
            GeneroRepository generoRepository,
            ObraGeneroRepository obraGeneroRepository,
            ProfissionalRepository profissionalRepository,
            ObraProfissionalRepository obraProfissionalRepository,
            EpisodioRepository episodioRepository,
            DispositivoRepository dispositivoRepository,
            VisualizacaoRepository visualizacaoRepository,
            FaturaRepository faturaRepository,
            StatusCobrancaRepository statusCobrancaRepository) {
        this.assinanteRepository = assinanteRepository;
        this.planoRepository = planoRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.qualidadeRepository = qualidadeRepository;
        this.classificacaoRepository = classificacaoRepository;
        this.perfilRepository = perfilRepository;
        this.obraRepository = obraRepository;
        this.generoRepository = generoRepository;
        this.obraGeneroRepository = obraGeneroRepository;
        this.profissionalRepository = profissionalRepository;
        this.obraProfissionalRepository = obraProfissionalRepository;
        this.episodioRepository = episodioRepository;
        this.dispositivoRepository = dispositivoRepository;
        this.visualizacaoRepository = visualizacaoRepository;
        this.faturaRepository = faturaRepository;
        this.statusCobrancaRepository = statusCobrancaRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (assinanteRepository.existsByCpf(SEED_CPF)) {
            return;
        }

        QualidadeResolucao sd = criarQualidade(QualidadeResolucaoDescricao.SD);
        QualidadeResolucao fullHd = criarQualidade(QualidadeResolucaoDescricao.FULL_HD);
        QualidadeResolucao quatroK = criarQualidade(QualidadeResolucaoDescricao.QUALIDADE_4K);
        qualidadeRepository.save(sd);
        qualidadeRepository.save(fullHd);
        qualidadeRepository.save(quatroK);

        ClassificacaoMaturidade infantil = criarClassificacao(ClassificacaoDescricao.INFANTIL);
        ClassificacaoMaturidade adolescente = criarClassificacao(ClassificacaoDescricao.ADOLESCENTE);
        ClassificacaoMaturidade adulto = criarClassificacao(ClassificacaoDescricao.ADULTO);
        classificacaoRepository.save(infantil);
        classificacaoRepository.save(adolescente);
        classificacaoRepository.save(adulto);

        StatusCobranca pendente = criarStatus(StatusCobrancaDescricao.PENDENTE);
        StatusCobranca paga = criarStatus(StatusCobrancaDescricao.PAGA);
        StatusCobranca atrasada = criarStatus(StatusCobrancaDescricao.ATRASADA);
        StatusCobranca estornada = criarStatus(StatusCobrancaDescricao.ESTORNADA);
        statusCobrancaRepository.save(pendente);
        statusCobrancaRepository.save(paga);
        statusCobrancaRepository.save(atrasada);
        statusCobrancaRepository.save(estornada);

        Plano basico = criarPlano("Basico", (short) 1, sd);
        Plano familia = criarPlano("Familia", (short) 3, fullHd);
        Plano premium = criarPlano("Premium 4K", (short) 5, quatroK);
        planoRepository.save(basico);
        planoRepository.save(familia);
        planoRepository.save(premium);

        Assinante alice = criarAssinante("Alice Monteiro", SEED_CPF, "alice.monteiro@fake.com", "83991010001", true);
        Assinante bruno = criarAssinante("Bruno Carvalho", "000.000.000-02", "bruno.carvalho@fake.com", "83991010002",
                true);
        Assinante carla = criarAssinante("Carla Nogueira", "000.000.000-03", "carla.nogueira@fake.com", "83991010003",
                false);
        assinanteRepository.save(alice);
        assinanteRepository.save(bruno);
        assinanteRepository.save(carla);

        Perfil aliceAdulto = criarPerfil(alice, 1, "Alice", "https://example.com/avatars/alice.png", adulto);
        Perfil aliceKids = criarPerfil(alice, 2, "Kids", "https://example.com/avatars/kids.png", infantil);
        Perfil brunoPerfil = criarPerfil(bruno, 1, "Bruno", "https://example.com/avatars/bruno.png", adulto);
        Perfil carlaPerfil = criarPerfil(carla, 1, "Carla", "https://example.com/avatars/carla.png", adolescente);
        perfilRepository.save(aliceAdulto);
        perfilRepository.save(aliceKids);
        perfilRepository.save(brunoPerfil);
        perfilRepository.save(carlaPerfil);

        Dispositivo tvAlice = criarDispositivo("AA:BB:CC:00:00:01", "Tizen", alice);
        Dispositivo celularAlice = criarDispositivo("AA:BB:CC:00:00:02", "Android", alice);
        Dispositivo notebookBruno = criarDispositivo("AA:BB:CC:00:00:03", "Windows", bruno);
        dispositivoRepository.save(tvAlice);
        dispositivoRepository.save(celularAlice);
        dispositivoRepository.save(notebookBruno);

        Genero acao = criarGenero("Acao", "Filmes e series com aventura e ritmo intenso.");
        Genero comedia = criarGenero("Comedia", "Obras leves e divertidas.");
        Genero drama = criarGenero("Drama", "Historias focadas em conflitos humanos.");
        Genero ficcao = criarGenero("Ficcao cientifica", "Tecnologia, futuro e realidades alternativas.");
        generoRepository.save(acao);
        generoRepository.save(comedia);
        generoRepository.save(drama);
        generoRepository.save(ficcao);

        Obra eclipse = criarObra("Eclipse Urbano", "Um suspense futurista em uma cidade sem luz natural.",
                (short) 2024, (short) 14);
        Obra cafe = criarObra("Cafe com Estrelas", "Uma comedia romantica sobre encontros improvaveis.",
                (short) 2022, (short) 10);
        Obra fronteiras = criarObra("Fronteiras do Amanha", "Serie sobre exploradores tentando sobreviver em Marte.",
                (short) 2025, (short) 12);
        obraRepository.save(eclipse);
        obraRepository.save(cafe);
        obraRepository.save(fronteiras);

        obraGeneroRepository.save(criarObraGenero(eclipse, acao));
        obraGeneroRepository.save(criarObraGenero(eclipse, ficcao));
        obraGeneroRepository.save(criarObraGenero(cafe, comedia));
        obraGeneroRepository.save(criarObraGenero(cafe, drama));
        obraGeneroRepository.save(criarObraGenero(fronteiras, ficcao));
        obraGeneroRepository.save(criarObraGenero(fronteiras, drama));

        Profissional marina = criarProfissional("Marina Duarte", LocalDate.of(1985, 4, 18), "Brasil");
        Profissional otavio = criarProfissional("Otavio Reis", LocalDate.of(1979, 9, 2), "Portugal");
        Profissional helena = criarProfissional("Helena Costa", LocalDate.of(1991, 1, 27), "Brasil");
        profissionalRepository.save(marina);
        profissionalRepository.save(otavio);
        profissionalRepository.save(helena);

        obraProfissionalRepository.save(criarObraProfissional(marina, eclipse, FuncaoProfissional.DIRETOR));
        obraProfissionalRepository.save(criarObraProfissional(otavio, eclipse, FuncaoProfissional.ATOR));
        obraProfissionalRepository.save(criarObraProfissional(helena, cafe, FuncaoProfissional.ATOR));
        obraProfissionalRepository.save(criarObraProfissional(marina, fronteiras, FuncaoProfissional.DIRETOR));
        obraProfissionalRepository.save(criarObraProfissional(helena, fronteiras, FuncaoProfissional.ATOR));

        episodioRepository.save(criarEpisodio(fronteiras, (short) 1, (short) 1, "Sinais Vermelhos", (short) 48));
        episodioRepository.save(criarEpisodio(fronteiras, (short) 1, (short) 2, "Poeira e Silencio", (short) 52));
        episodioRepository.save(criarEpisodio(fronteiras, (short) 2, (short) 1, "A Nova Orbita", (short) 55));

        assinaturaRepository.save(criarAssinatura(alice, premium, LocalDate.now().minusMonths(6), null, "49.90"));
        assinaturaRepository.save(criarAssinatura(bruno, familia, LocalDate.now().minusMonths(3), null, "34.90"));
        assinaturaRepository.save(criarAssinatura(carla, basico, LocalDate.now().minusMonths(8),
                LocalDate.now().minusMonths(1), "19.90"));

        faturaRepository.save(criarFatura(alice, paga, LocalDate.now().minusDays(20), LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(9), "49.90"));
        faturaRepository.save(criarFatura(alice, pendente, LocalDate.now().minusDays(2), LocalDate.now().plusDays(8),
                null, "49.90"));
        faturaRepository.save(criarFatura(bruno, paga, LocalDate.now().minusDays(18), LocalDate.now().minusDays(8),
                LocalDate.now().minusDays(7), "34.90"));
        faturaRepository.save(criarFatura(carla, atrasada, LocalDate.now().minusDays(35), LocalDate.now().minusDays(25),
                null, "19.90"));
        faturaRepository.save(criarFatura(carla, estornada, LocalDate.now().minusDays(65), LocalDate.now().minusDays(55),
                LocalDate.now().minusDays(54), "19.90"));

        visualizacaoRepository.save(criarVisualizacao(aliceAdulto, eclipse, tvAlice, "100.00"));
        visualizacaoRepository.save(criarVisualizacao(aliceKids, cafe, celularAlice, "72.00"));
        visualizacaoRepository.save(criarVisualizacao(brunoPerfil, fronteiras, notebookBruno, "45.00"));
        visualizacaoRepository.save(criarVisualizacao(carlaPerfil, fronteiras, null, "88.00"));
    }

    private QualidadeResolucao criarQualidade(QualidadeResolucaoDescricao descricao) {
        QualidadeResolucao qualidade = new QualidadeResolucao();
        qualidade.setDescricao(descricao);
        return qualidade;
    }

    private ClassificacaoMaturidade criarClassificacao(ClassificacaoDescricao descricao) {
        ClassificacaoMaturidade classificacao = new ClassificacaoMaturidade();
        classificacao.setDescricao(descricao);
        return classificacao;
    }

    private StatusCobranca criarStatus(StatusCobrancaDescricao descricao) {
        StatusCobranca status = new StatusCobranca();
        status.setNome(descricao);
        return status;
    }

    private Plano criarPlano(String nome, Short telas, QualidadeResolucao qualidade) {
        Plano plano = new Plano();
        plano.setNomeComercial(nome);
        plano.setMaxTelas(telas);
        plano.setQualidadeResolucao(qualidade);
        return plano;
    }

    private Assinante criarAssinante(String nome, String cpf, String email, String telefone, boolean contaAtiva) {
        Assinante assinante = new Assinante();
        assinante.setNome(nome);
        assinante.setCpf(cpf);
        assinante.setEmail(email);
        assinante.setTelefone(telefone);
        assinante.setContaAtiva(contaAtiva);
        return assinante;
    }

    private Perfil criarPerfil(Assinante assinante, Integer numero, String nome, String avatar,
            ClassificacaoMaturidade classificacao) {
        Perfil perfil = new Perfil();
        perfil.setId(new PerfilId(assinante.getId(), numero));
        perfil.setAssinante(assinante);
        perfil.setNomeExibicao(nome);
        perfil.setAvatarUrl(avatar);
        perfil.setClassificacaoMaturidade(classificacao);
        return perfil;
    }

    private Dispositivo criarDispositivo(String mac, String sistemaOperacional, Assinante assinante) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setEnderecoMac(mac);
        dispositivo.setSistemaOperacional(sistemaOperacional);
        dispositivo.setAssinante(assinante);
        return dispositivo;
    }

    private Genero criarGenero(String nome, String descricao) {
        Genero genero = new Genero();
        genero.setNome(nome);
        genero.setDescricao(descricao);
        return genero;
    }

    private Obra criarObra(String titulo, String sinopse, Short ano, Short idadeMinima) {
        Obra obra = new Obra();
        obra.setTitulo(titulo);
        obra.setSinopse(sinopse);
        obra.setAnoLancamento(ano);
        obra.setIdadeMinimaRecomendada(idadeMinima);
        return obra;
    }

    private ObraGenero criarObraGenero(Obra obra, Genero genero) {
        ObraGenero obraGenero = new ObraGenero();
        obraGenero.setId(new ObraGeneroId(obra.getId(), genero.getId()));
        obraGenero.setObra(obra);
        obraGenero.setGenero(genero);
        return obraGenero;
    }

    private Profissional criarProfissional(String nome, LocalDate dataNascimento, String pais) {
        Profissional profissional = new Profissional();
        profissional.setNomeArtistico(nome);
        profissional.setDataNascimento(dataNascimento);
        profissional.setPaisOrigem(pais);
        return profissional;
    }

    private ObraProfissional criarObraProfissional(Profissional profissional, Obra obra, FuncaoProfissional funcao) {
        ObraProfissional obraProfissional = new ObraProfissional();
        obraProfissional.setId(new ObraProfissionalId(profissional.getId(), obra.getId(), funcao));
        obraProfissional.setProfissional(profissional);
        obraProfissional.setObra(obra);
        return obraProfissional;
    }

    private Episodio criarEpisodio(Obra obra, Short temporada, Short capitulo, String titulo, Short duracao) {
        Episodio episodio = new Episodio();
        episodio.setId(new EpisodioId(obra.getId(), temporada, capitulo));
        episodio.setObra(obra);
        episodio.setTitulo(titulo);
        episodio.setDuracao(duracao);
        return episodio;
    }

    private Assinatura criarAssinatura(Assinante assinante, Plano plano, LocalDate inicio, LocalDate fim, String valor) {
        Assinatura assinatura = new Assinatura();
        assinatura.setId(new AssinaturaId(assinante.getId(), plano.getId(), inicio));
        assinatura.setAssinante(assinante);
        assinatura.setPlano(plano);
        assinatura.setDataFim(fim);
        assinatura.setValorCobrado(new BigDecimal(valor));
        return assinatura;
    }

    private Fatura criarFatura(Assinante assinante, StatusCobranca status, LocalDate emissao, LocalDate vencimento,
            LocalDate pagamento, String valor) {
        Fatura fatura = new Fatura();
        fatura.setAssinante(assinante);
        fatura.setStatusCobranca(status);
        fatura.setDataEmissao(emissao);
        fatura.setDataVencimento(vencimento);
        fatura.setDataPagamento(pagamento);
        fatura.setValorTotal(new BigDecimal(valor));
        return fatura;
    }

    private Visualizacao criarVisualizacao(Perfil perfil, Obra obra, Dispositivo dispositivo, String porcentagem) {
        Visualizacao visualizacao = new Visualizacao();
        visualizacao.setPerfil(perfil);
        visualizacao.setObra(obra);
        visualizacao.setDispositivo(dispositivo);
        visualizacao.setDataHora(LocalDateTime.now().minusDays(2));
        visualizacao.setPorcentagemAssistida(new BigDecimal(porcentagem));
        return visualizacao;
    }
}

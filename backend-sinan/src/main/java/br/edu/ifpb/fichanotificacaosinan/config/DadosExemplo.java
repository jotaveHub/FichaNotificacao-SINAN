package br.edu.ifpb.fichanotificacaosinan.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifpb.fichanotificacaosinan.model.DadosResidencia;
import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import br.edu.ifpb.fichanotificacaosinan.service.NotificacaoService;

@Component
public class DadosExemplo implements CommandLineRunner {

    private final NotificacaoService service;
    private final boolean ativo;

    public DadosExemplo(NotificacaoService service,
                        @Value("${sinan.dados-exemplo:true}") boolean ativo) {
        this.service = service;
        this.ativo = ativo;
    }

    @Override
    public void run(String... args) {
        if (!ativo) {
            return;
        }

        service.criar(nova("001", "Dengue", "Maria da Silva", "1990-05-10", "F",
                "Ana da Silva", "2026-03-12", "PB", "Cajazeiras"));
        service.criar(nova("002", "Dengue", "Maria da Silva", "1990-05-10", "F",
                "Ana da Silva", "2026-03-14", "PB", "Cajazeiras"));
        service.criar(nova("003", "Dengue", "José Souza", "1985-01-20", "M",
                "Rita Souza", "2026-04-01", "PB", "João Pessoa"));
        service.criar(nova("004", "Dengue", "José Souza", "1985-01-20", "M",
                "Rita Souza", "2026-04-09", "PB", "João Pessoa"));
        service.criar(nova("005", "Hepatites virais", "Carlos Pereira", "2000-08-03", "M",
                null, "2026-05-05", "PE", "Recife"));
        service.criar(nova("006", "Hepatites virais", "Carlos Pereira", "2000-08-03", "M",
                null, "2026-05-06", "PE", "Recife"));

        Notificacao zika = nova("007", "Zika", "Maria Oliveira", "1978-11-02", "F",
                "Joana Oliveira", "2026-03-13", "PB", "Cajazeiras");
        zika.setClassificacaoFinal(1);
        zika.setDataEncerramento(LocalDate.of(2026, 3, 20));
        service.criar(zika);
    }

    private Notificacao nova(String numero, String agravo, String paciente, String nascimento,
                             String sexo, String mae, String dataNotificacao,
                             String uf, String municipio) {
        LocalDate data = LocalDate.parse(dataNotificacao);

        Notificacao n = new Notificacao();
        n.setNumeroNotificacao(numero);
        n.setAgravo(agravo);
        n.setDataNotificacao(data);
        n.setUfNotificacao("PB");
        n.setMunicipioNotificacao("Cajazeiras");
        n.setUnidadeSaude("UBS Centro");
        n.setDataPrimeirosSintomas(data.minusDays(2));
        n.setNomePaciente(paciente);
        n.setDataNascimento(LocalDate.parse(nascimento));
        n.setSexo(sexo);
        n.setGestante("F".equals(sexo) ? 5 : 6);
        n.setNomeMae(mae);

        DadosResidencia residencia = new DadosResidencia();
        residencia.setUf(uf);
        residencia.setMunicipio(municipio);
        n.setDadosResidencia(residencia);

        n.setDataInvestigacao(data.plusDays(1));
        return n;
    }
}
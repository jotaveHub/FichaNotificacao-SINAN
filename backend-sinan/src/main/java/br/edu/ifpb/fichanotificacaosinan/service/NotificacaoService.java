package br.edu.ifpb.fichanotificacaosinan.service;

import br.edu.ifpb.fichanotificacaosinan.exception.NotificacaoNaoEncontradaException;
import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import br.edu.ifpb.fichanotificacaosinan.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;
import br.edu.ifpb.fichanotificacaosinan.model.DadosResidencia;
import java.time.LocalDate;
import  java.util.List;
import br.edu.ifpb.fichanotificacaosinan.util.Texto;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository repository) {
        this.repository = repository;
    }

    public Notificacao criar(Notificacao notificacao) {
        aplicarPadroes(notificacao);
        return repository.salvar(notificacao);
    }

    public Notificacao buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new NotificacaoNaoEncontradaException(id));
    }

    public Notificacao atualizar(Long id, Notificacao notificacao) {
        aplicarPadroes(notificacao);
        return repository.atualizar(id, notificacao)
                .orElseThrow(() -> new NotificacaoNaoEncontradaException(id));
    }

    public void remover(Long id) {
        if (!repository.remover(id)) {
            throw new NotificacaoNaoEncontradaException(id);
        }
    }

    private void aplicarPadroes(Notificacao notificacao) {
        if (notificacao.getGestante() == null) {
            notificacao.setGestante(6);
        }
    }

    public List<Notificacao> listar(FiltroNotificacao filtro) {
        return repository.listarTodas().stream()
                .filter(n -> Texto.contem(n.getNumeroNotificacao(), filtro.numeroNotificacao()))
                .filter(n -> Texto.contem(n.getAgravo(), filtro.agravo()))
                .filter(n -> Texto.contem(n.getNomePaciente(), filtro.nomePaciente()))
                .filter(n -> ufConfere(n, filtro.ufResidencia()))
                .filter(n -> municipioConfere(n, filtro.municipioResidencia()))
                .filter(n -> classificacaoConfere(n, filtro.classificacaoFinal()))
                .filter(n -> periodoConfere(n, filtro.dataNotificacaoInicio(), filtro.dataNotificacaoFim()))
                .toList();
    }

    private boolean ufConfere(Notificacao n, String uf) {
        if (Texto.vazio(uf)) {
            return true;
        }
        DadosResidencia residencia = n.getDadosResidencia();
        return residencia != null
                && residencia.getUf() != null
                && residencia.getUf().equalsIgnoreCase(uf.trim());
    }

    private boolean municipioConfere(Notificacao n, String municipio) {
        if (Texto.vazio(municipio)) {
            return true;
        }
        DadosResidencia residencia = n.getDadosResidencia();
        return residencia != null && Texto.contem(residencia.getMunicipio(), municipio);
    }

    private boolean classificacaoConfere(Notificacao n, Integer classificacao) {
        return classificacao == null || classificacao.equals(n.getClassificacaoFinal());
    }

    private boolean periodoConfere(Notificacao n, LocalDate inicio, LocalDate fim) {
        LocalDate data = n.getDataNotificacao();
        if (data == null) {
            return inicio == null && fim == null;
        }
        return (inicio == null || !data.isBefore(inicio))
                && (fim == null || !data.isAfter(fim));
    }
}
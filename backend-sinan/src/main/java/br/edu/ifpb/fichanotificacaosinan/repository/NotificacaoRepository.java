package br.edu.ifpb.fichanotificacaosinan.repository;

import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificacaoRepository {

    private final List<Notificacao> notificacoes = new ArrayList<>();
    private long proximoId = 1;

    public synchronized Notificacao salvar(Notificacao notificacao) {
        notificacao.setId(proximoId++);
        notificacoes.add(notificacao);
        return notificacao;
    }

    public synchronized Optional<Notificacao> buscarPorId(Long id) {
        return notificacoes.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst();
    }

    public synchronized boolean remover(Long id) {
        return notificacoes.removeIf(n -> n.getId().equals(id));
    }

    public synchronized List<Notificacao> listarTodas() {
        return new ArrayList<>(notificacoes);
    }
}
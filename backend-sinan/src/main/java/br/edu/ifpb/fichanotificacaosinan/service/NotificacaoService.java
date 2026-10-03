package br.edu.ifpb.fichanotificacaosinan.service;

import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import br.edu.ifpb.fichanotificacaosinan.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository repository) {
        this.repository = repository;
    }

    public Notificacao criar(Notificacao notificacao) {
        return repository.salvar(notificacao);
    }

    public Optional<Notificacao> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Optional<Notificacao> atualizar(Long id, Notificacao notificacao) {
        return repository.atualizar(id, notificacao);
    }
}
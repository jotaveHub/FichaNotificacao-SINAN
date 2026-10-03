package br.edu.ifpb.fichanotificacaosinan.service;

import br.edu.ifpb.fichanotificacaosinan.exception.NotificacaoNaoEncontradaException;
import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import br.edu.ifpb.fichanotificacaosinan.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository repository) {
        this.repository = repository;
    }

    public Notificacao criar(Notificacao notificacao) {
        return repository.salvar(notificacao);
    }

    public Notificacao buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new NotificacaoNaoEncontradaException(id));
    }

    public Notificacao atualizar(Long id, Notificacao notificacao) {
        return repository.atualizar(id, notificacao)
                .orElseThrow(() -> new NotificacaoNaoEncontradaException(id));
    }

    public void remover(Long id) {
        if (!repository.remover(id)) {
            throw new NotificacaoNaoEncontradaException(id);
        }
    }
}
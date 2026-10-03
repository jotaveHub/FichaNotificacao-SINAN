package br.edu.ifpb.fichanotificacaosinan.exception;

public class NotificacaoNaoEncontradaException extends RuntimeException {

    public NotificacaoNaoEncontradaException(Long id) {
        super("Notificação com id " + id + " não encontrada.");
    }
}
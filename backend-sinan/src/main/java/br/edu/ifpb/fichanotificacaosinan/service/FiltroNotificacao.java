package br.edu.ifpb.fichanotificacaosinan.service;

import java.time.LocalDate;

public record FiltroNotificacao(
        String numeroNotificacao,
        String agravo,
        String nomePaciente,
        String ufResidencia,
        String municipioResidencia,
        Integer classificacaoFinal,
        LocalDate dataNotificacaoInicio,
        LocalDate dataNotificacaoFim) {
}
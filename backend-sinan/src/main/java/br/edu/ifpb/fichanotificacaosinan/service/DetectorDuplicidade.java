package br.edu.ifpb.fichanotificacaosinan.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import br.edu.ifpb.fichanotificacaosinan.util.Texto;

public final class DetectorDuplicidade {

    private static final long LIMITE_DIAS = 3;

    private record Chave(String agravo, String paciente, LocalDate nascimento, String mae) {
    }

    private DetectorDuplicidade() {
    }

    public static Set<Long> idsDuplicados(Collection<Notificacao> notificacoes) {
        Map<Chave, List<Notificacao>> grupos = notificacoes.stream()
                .filter(DetectorDuplicidade::temCamposDeComparacao)
                .collect(Collectors.groupingBy(n -> new Chave(
                        Texto.normalizar(n.getAgravo()),
                        Texto.normalizar(n.getNomePaciente()),
                        n.getDataNascimento(),
                        Texto.normalizar(n.getNomeMae()))));

        Set<Long> ids = new HashSet<>();

        for (List<Notificacao> grupo : grupos.values()) {
            if (grupo.size() < 2) {
                continue;
            }

            List<Notificacao> ordenado = new ArrayList<>(grupo);
            ordenado.sort(Comparator.comparing(Notificacao::getDataNotificacao));

            for (int i = 0; i < ordenado.size(); i++) {
                for (int j = i + 1; j < ordenado.size(); j++) {
                    long dias = ChronoUnit.DAYS.between(
                            ordenado.get(i).getDataNotificacao(),
                            ordenado.get(j).getDataNotificacao());

                    if (dias > LIMITE_DIAS) {
                        break;
                    }
                    ids.add(ordenado.get(i).getId());
                    ids.add(ordenado.get(j).getId());
                }
            }
        }
        return ids;
    }

    private static boolean temCamposDeComparacao(Notificacao n) {
        return !Texto.vazio(n.getAgravo())
                && !Texto.vazio(n.getNomePaciente())
                && !Texto.vazio(n.getNomeMae())
                && n.getDataNascimento() != null
                && n.getDataNotificacao() != null;
    }
}
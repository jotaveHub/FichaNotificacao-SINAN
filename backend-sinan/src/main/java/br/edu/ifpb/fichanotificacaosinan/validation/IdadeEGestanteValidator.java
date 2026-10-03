package br.edu.ifpb.fichanotificacaosinan.validation;

import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdadeEGestanteValidator implements ConstraintValidator<IdadeEGestanteValidos, Notificacao> {

    @Override
    public boolean isValid(Notificacao n, ConstraintValidatorContext contexto) {
        if (n == null) {
            return true;
        }

        contexto.disableDefaultConstraintViolation();
        boolean valido = true;

        // Campo 10: idade
        boolean temIdadeValor = n.getIdadeValor() != null;
        boolean temIdadeUnidade = n.getIdadeUnidade() != null;

        if (n.getDataNascimento() == null) {
            if (!temIdadeValor) {
                valido = erro(contexto, "idadeValor",
                        "A idade é obrigatória quando a data de nascimento não é informada");
            }
            if (!temIdadeUnidade) {
                valido = erro(contexto, "idadeUnidade",
                        "A unidade da idade é obrigatória quando a data de nascimento não é informada");
            }
        } else if (temIdadeValor != temIdadeUnidade) {
            valido = erro(contexto, temIdadeValor ? "idadeUnidade" : "idadeValor",
                    "Informe o valor e a unidade da idade juntos");
        }

        // Campo 12: gestante
        if (n.getSexo() != null) {
            Integer anos = n.idadeEmAnos();
            boolean feminino = "F".equals(n.getSexo());
            boolean naoSeAplica = !feminino || (anos != null && anos < 7);
            Integer gestante = n.getGestante();

            if (naoSeAplica) {
                if (gestante != null && gestante != 6) {
                    valido = erro(contexto, "gestante", feminino
                            ? "Para menores de 7 anos use 6 (não se aplica) ou deixe em branco"
                            : "Só se aplica ao sexo feminino; use 6 (não se aplica) ou deixe em branco");
                }
            } else if (gestante == null) {
                valido = erro(contexto, "gestante",
                        "A gestante é obrigatória quando o sexo é feminino");
            }
        }

        return valido;
    }

    private boolean erro(ConstraintValidatorContext contexto, String campo, String mensagem) {
        contexto.buildConstraintViolationWithTemplate(mensagem)
                .addPropertyNode(campo)
                .addConstraintViolation();
        return false;
    }
}
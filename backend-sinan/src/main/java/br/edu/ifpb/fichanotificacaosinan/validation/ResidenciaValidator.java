package br.edu.ifpb.fichanotificacaosinan.validation;

import br.edu.ifpb.fichanotificacaosinan.model.DadosResidencia;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ResidenciaValidator implements ConstraintValidator<ResidenciaValida, DadosResidencia> {

    @Override
    public boolean isValid(DadosResidencia residencia, ConstraintValidatorContext contexto) {
        if (residencia == null) {
            return true;
        }

        contexto.disableDefaultConstraintViolation();
        boolean valido = true;

        boolean temUf = !vazio(residencia.getUf());
        boolean temMunicipio = !vazio(residencia.getMunicipio());
        boolean paisEstrangeiro = !vazio(residencia.getPais())
                && !"brasil".equalsIgnoreCase(residencia.getPais().trim());

        if (temUf) {
            if (!temMunicipio) {
                valido = erro(contexto, "municipio",
                        "O município é obrigatório quando a UF é informada");
            }
            if (paisEstrangeiro) {
                valido = erro(contexto, "pais",
                        "O país deve ficar em branco (ou ser Brasil) quando a UF é informada");
            }
        } else {
            if (!paisEstrangeiro) {
                valido = erro(contexto, "uf",
                        "A UF é obrigatória quando o paciente reside no Brasil; "
                                + "se reside em outro país, informe o país");
            }
            if (temMunicipio) {
                valido = erro(contexto, "municipio",
                        "O município só deve ser informado junto com a UF");
            }
        }

        return valido;
    }

    private boolean vazio(String texto) {
        return texto == null || texto.isBlank();
    }

    private boolean erro(ConstraintValidatorContext contexto, String campo, String mensagem) {
        contexto.buildConstraintViolationWithTemplate(mensagem)
                .addPropertyNode(campo)
                .addConstraintViolation();
        return false;
    }
}
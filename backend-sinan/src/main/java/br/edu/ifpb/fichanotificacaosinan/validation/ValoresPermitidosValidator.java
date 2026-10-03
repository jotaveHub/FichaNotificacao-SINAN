package br.edu.ifpb.fichanotificacaosinan.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValoresPermitidosValidator implements ConstraintValidator<ValoresPermitidos, Integer> {

    private int[] permitidos;

    @Override
    public void initialize(ValoresPermitidos anotacao) {
        this.permitidos = anotacao.valores();
    }

    @Override
    public boolean isValid(Integer valor, ConstraintValidatorContext contexto) {
        if (valor == null) {
            return true;
        }
        for (int permitido : permitidos) {
            if (permitido == valor) {
                return true;
            }
        }
        return false;
    }
}
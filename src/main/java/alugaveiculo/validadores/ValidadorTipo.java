/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.validadores;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author davi
 */
public class ValidadorTipo implements ConstraintValidator<ValidaTipo, String> {
    
    private List<String> tipos;
    
    @Override
    public void initialize(ValidaTipo validatipo) {
        this.tipos = new ArrayList<>();
        this.tipos.add("Sedã");
        this.tipos.add("Jipe");
        this.tipos.add("Hatch");
        this.tipos.add("Esportivo");
        this.tipos.add("Van");
        this.tipos.add("Station Wagon");
        this.tipos.add("Pick-up");
        this.tipos.add("SUV");
        
        
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : tipos.contains(valor);
    }
    
}

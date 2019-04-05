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
public class ValidadorFabricante implements ConstraintValidator<ValidaFabricante, String> {
    
     private List<String> fabricantes;
    
    @Override
    public void initialize(ValidaFabricante validafabricante) {
        this.fabricantes = new ArrayList<>();
        this.fabricantes.add("Fiat");
        this.fabricantes.add("Jeep");
        this.fabricantes.add("Honda");
        this.fabricantes.add("Volkswagen");
        this.fabricantes.add("Chevrolet");
        this.fabricantes.add("Ford");
        this.fabricantes.add("Volvo");
        this.fabricantes.add("Toyota");
        this.fabricantes.add("Cintroen");
        this.fabricantes.add("Jac Motors");
        this.fabricantes.add("BMW");
        this.fabricantes.add("Mercedes Benz");
        
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : fabricantes.contains(valor);
    }
    
}

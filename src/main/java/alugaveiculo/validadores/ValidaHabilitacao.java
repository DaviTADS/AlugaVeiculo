/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.validadores;

/**
 *
 * @author ALUNO
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;

/**
 *
 * @author davi
 */
@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorHabilitacao.class)
@Documented

public @interface ValidaHabilitacao  {
    
     String message() default "{com.mycompany.alugaveiculo.Veiculo.habilitacoes}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}

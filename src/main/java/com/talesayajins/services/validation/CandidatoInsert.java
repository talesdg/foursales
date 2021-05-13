package com.talesayajins.services.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CandidatoInsertValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CandidatoInsert {
    String message() default "Erro de validação";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

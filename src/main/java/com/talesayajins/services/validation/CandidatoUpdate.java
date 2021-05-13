package com.talesayajins.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CandidatoUpdateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CandidatoUpdate {
    String message() default "Erro de validação";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

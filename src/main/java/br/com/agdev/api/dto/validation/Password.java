package br.com.agdev.api.dto.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = { PasswordValidator.class })
public @interface Password {

	String message() default "{br.com.agdev.Password.invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] restrictions() default {};

}

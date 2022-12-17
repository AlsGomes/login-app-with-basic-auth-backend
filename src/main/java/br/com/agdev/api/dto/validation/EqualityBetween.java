package br.com.agdev.api.dto.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = { EqualityBetweenValidator.class })
public @interface EqualityBetween {

	String message() default "{br.com.agdev.EqualityBetween.invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] fields() default {};
}

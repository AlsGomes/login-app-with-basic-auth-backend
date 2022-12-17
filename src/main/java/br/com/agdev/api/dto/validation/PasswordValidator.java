package br.com.agdev.api.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	private String[] restrictions;

	@Override
	public void initialize(Password constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.restrictions = constraintAnnotation.restrictions();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return false;

		if (value.isBlank())
			return false;

		if (restrictions.length == 0)
			return true;

		for (String restriction : restrictions) {
			if (value.contains(restriction))
				return false;
		}

		return true;
	}
}

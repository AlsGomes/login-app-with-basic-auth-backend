package br.com.agdev.api.dto.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class EqualityBetweenValidator implements ConstraintValidator<EqualityBetween, Object> {

	private String[] fields;
	private Set<Object> fieldValues = new HashSet<>();

	@Override
	public void initialize(EqualityBetween constraintAnnotation) {
		this.fields = constraintAnnotation.fields();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		fieldValues.clear();

		for (String field : fields) {
			try {
				var fieldValue = BeanUtils.getPropertyDescriptor(value.getClass(), field).getReadMethod().invoke(value);
				fieldValues.add(fieldValue);
			} catch (Exception e) {
				throw new ValidationException(e);
			}
		}

		return fieldValues.size() == 1;
	}
}

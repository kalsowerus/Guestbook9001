package guestbook.form.validator;

import guestbook.form.constraint.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	@Override
	public void initialize(Password constraintAnnotation) {}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
		return password != null
				&& password.matches("(?=.*[0-9])(?=.*[a-zA-Z])");
	}
}

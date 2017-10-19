package guestbook.form.constraint;

import guestbook.form.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
	String message() default "{Password}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

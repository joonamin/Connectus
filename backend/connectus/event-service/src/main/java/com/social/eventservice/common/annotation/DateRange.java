package com.social.eventservice.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// import com.social.eventservice.common.validator.DurationValidator;
//
// import jakarta.validation.Constraint;
// import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// @Constraint(validatedBy = DurationValidator.class)
public @interface DateRange {
	String message() default "날짜 형식이 잘못되었습니다.";

	Class<?>[] groups() default {};

	// Class<? extends Payload>[] payload() default {};
}

package com.webapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class YearConstraintValidator implements ConstraintValidator<Year, Date> {

	private int annotationYear;
	
	@Override
	public void initialize(Year year) {
		this.annotationYear = year.value();
	}

	@Override
	public boolean isValid(Date target, ConstraintValidatorContext cxt) {
		System.out.println("-DATE TARGET--   " + target);
		System.out.println("-ConstraintValidatorContext--   " + cxt);


		if (target == null) {
			return true;
		}
		Calendar c = Calendar.getInstance();
		System.out.println("-Calendar before--   " + c);

		c.setTime(target);
		System.out.println("-Calendar after--   " + c);
		int fieldYear = c.get(Calendar.YEAR);
		System.out.println("fieldYear" + fieldYear);
		return fieldYear == annotationYear;
	}

}

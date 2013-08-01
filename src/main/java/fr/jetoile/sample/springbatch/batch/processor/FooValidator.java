package fr.jetoile.sample.springbatch.batch.processor;

import fr.jetoile.sample.springbatch.batch.model.FooModel;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import java.util.regex.Pattern;

public class FooValidator implements Validator<FooModel> {

	@Override
	public void validate(FooModel value) throws ValidationException {
		if (value == null) {
			throw new ValidationException("nothing to do");
		}

		if (value.getMotcle() == null) {
			throw new ValidationException("nothing to do for " + value.getId());
		}

		if (Pattern.compile(" NOT|SAUF(\\s+)?\\(", Pattern.CASE_INSENSITIVE).matcher(value.getMotcle()).find()) {
			throw new ValidationException("this pattern has to be managed manually: " + value);
		}
	}
}

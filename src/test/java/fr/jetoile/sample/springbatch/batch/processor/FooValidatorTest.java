package fr.jetoile.sample.springbatch.batch.processor;

import fr.jetoile.sample.springbatch.batch.model.FooModel;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;
import org.springframework.batch.item.validator.ValidationException;

public class FooValidatorTest {

	private FooValidator validator = new FooValidator();

	private FooModel foo = null;

	@Test(expected = ValidationException.class)
	public void null_value_should_fail() {
		foo = null;

		validator.validate(foo);
	}

	@Test(expected = ValidationException.class)
	public void value_with_no_arcmocle_should_fail() {
		foo = new FooModel();
		foo.setMotcle(null);

		validator.validate(foo);
	}

	@Test
	public void value_with_arcmocle_should_success() {
		foo = new FooModel();
		foo.setMotcle("coucou");

		validator.validate(foo);
		assertThat(foo.getMotcle()).isEqualTo("coucou");
	}

	@Test
	public void value_with_arcmocle_with_parenthesis_should_success() {
		foo = new FooModel();
		foo.setMotcle("coucou (titi)");

		validator.validate(foo);
		assertThat(foo.getMotcle()).isEqualTo("coucou (titi)");
	}

	@Test
	public void value_with_arcmocle_with_sauf_should_success() {
		foo = new FooModel();
		foo.setMotcle("coucou SAUF titi");

		validator.validate(foo);
		assertThat(foo.getMotcle()).isEqualTo("coucou SAUF titi");
	}

	@Test(expected = ValidationException.class)
	public void value_with_arcmocle_with_sauf_parenthesis_should_fail1() {
		foo = new FooModel();
		foo.setMotcle("coucou SAUF(titi)");
		validator.validate(foo);
	}

	@Test(expected = ValidationException.class)
	public void value_with_arcmocle_with_not_parenthesis_should_fail1() {
		foo = new FooModel();
		foo.setMotcle("coucou NOT(titi)");
		validator.validate(foo);
	}

	@Test(expected = ValidationException.class)
	public void value_with_arcmocle_with_sauf_parenthesis_should_fail2() {
		foo = new FooModel();

		foo.setMotcle("coucou SAUF  (titi)");
		validator.validate(foo);
	}

	@Test(expected = ValidationException.class)
	public void value_with_arcmocle_with_sauf_parenthesis_should_fail3() {
		foo = new FooModel();

		foo.setMotcle("coucou SAUF (titi)");
		validator.validate(foo);
	}
}

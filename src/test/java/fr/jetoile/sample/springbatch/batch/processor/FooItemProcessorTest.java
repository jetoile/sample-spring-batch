package fr.jetoile.sample.springbatch.batch.processor;

import fr.jetoile.sample.springbatch.batch.model.FooModel;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/fr/jetoile/sample/springbatch/batch/springbatch.xml"})
public class FooItemProcessorTest {

	@Autowired
	private FooItemProcessor processor;

	@Test
	public void simple_processor_should_suppress_ou() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("Bts  OU en ou altermance");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
	}

	@Test
	public void simple_processor_should_suppress_or() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("Bts  OR en or altermance");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
	}

	@Test
	public void simple_processor_should_suppress_et() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("Bts  ET en et altermance");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
	}

	@Test
	public void simple_processor_should_replace_sauf_by_minus() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("Bts  SAUF en sauf altermance");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts -en -altermance");
	}

	@Test
	public void simple_processor_should_suppress_etoile() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("Bts* en altermance");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
	}

	@Test
	public void simple_processor_should_replace_single_quote_by_quote() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("Bts en 'altermance'");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts en \"altermance\"");
	}

	@Test
	public void simple_processor_should_suppress_parenthesis() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("(Bts) en (altermance)");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("Bts en altermance");
	}

	@Test
	public void simple_processor_should_replace_first_sauf_by_minus() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("SAUF altermance)");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("-altermance");
	}

	@Test
	public void simple_processor_should_replace_first_not_by_minus() throws Exception {
		FooModel assistantRecherche = new FooModel();
		assistantRecherche.setId(1L);
		assistantRecherche.setNom("toto");
		assistantRecherche.setMotcle("NOT altermance)");

		FooModel res = processor.process(assistantRecherche);
		assertThat(res.getId()).isEqualTo(1L);
		assertThat(res.getNom()).isEqualTo("toto");
		assertThat(res.getMotcle()).isEqualTo("-altermance");
	}
}

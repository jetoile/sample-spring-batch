package fr.jetoile.sample.springbatch.batch.processor;

import fr.jetoile.sample.springbatch.batch.model.FooModel;
import org.springframework.batch.item.ItemProcessor;

import java.util.regex.Pattern;

public class FooItemProcessor implements ItemProcessor<FooModel, FooModel> {

	@Override
	public FooModel process(FooModel item) throws Exception {
		FooModel assistantRecherche = new FooModel();

		String motclef = item.getMotcle();

		String nouveauMotClef = motclef.replaceAll("\\s+", " ");

		nouveauMotClef = Pattern.compile(" OU|OR ", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("");
		nouveauMotClef = Pattern.compile(" ET|AND ", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("");
		nouveauMotClef = Pattern.compile(" SAUF ", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll(" -");
		nouveauMotClef = Pattern.compile("^SAUF ", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("-");
		nouveauMotClef = Pattern.compile(" NOT ", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll(" -");
		nouveauMotClef = Pattern.compile("^NOT ", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("-");
		nouveauMotClef = Pattern.compile("\\*", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("");
		nouveauMotClef = Pattern.compile("'", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("\"");
		nouveauMotClef = Pattern.compile("\\(", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("");
		nouveauMotClef = Pattern.compile("\\)", Pattern.CASE_INSENSITIVE).matcher(nouveauMotClef).replaceAll("");

		assistantRecherche.setId(item.getId());
		assistantRecherche.setNom(item.getNom());

		assistantRecherche.setMotcle(nouveauMotClef);

		return assistantRecherche;
	}


}


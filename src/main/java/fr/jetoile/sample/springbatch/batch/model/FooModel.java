package fr.jetoile.sample.springbatch.batch.model;

public class FooModel {

	private Long id;
	private String nom;
	private String motcle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotcle() {
		return motcle;
	}

	public void setMotcle(String motcle) {
		this.motcle = motcle;
	}

	@Override
	public String toString() {
		return "FooModel{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", motcle='" + motcle + '\'' +
				'}';
	}
}

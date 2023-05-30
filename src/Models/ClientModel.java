package Models;

import java.util.Date;

public class ClientModel {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private Date dateajout;
	
	public ClientModel(int id, String nom, String prenom, String adresse, String telephone, Date dateajout) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.dateajout = dateajout;
	}

	public ClientModel(String text, String text2, String text3, String text4, int id2) {
		this.id = id2;
		this.nom = text;
		this.prenom = text2;
		this.adresse = text3;
		this.telephone = text4;
	}
	
	
	public ClientModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientModel(int id2) {
		this.id = id2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateajout() {
		return dateajout;
	}

	public void setDateajout(Date dateajout) {
		this.dateajout = dateajout;
	}
	
	
}




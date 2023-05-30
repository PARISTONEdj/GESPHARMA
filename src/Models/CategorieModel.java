package Models;

import java.util.Date;

public class CategorieModel {
	private int id;
	private String libelle;
	private Date datecreation;
	
	
	public CategorieModel(int id, String libelle, Date datecreation) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.datecreation = datecreation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	
	
}

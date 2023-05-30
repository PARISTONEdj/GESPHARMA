package Models;

import java.util.Date;

public class ProduitModel {
	private int id;
	private String nom;
	private String description;
	private double seuil;
	private float prix;
	private double qte;
	private int categorie;
	private Date datecreer;
	public ProduitModel(int id, String nom, String description, double seuil, float prix, double qte, int categorie,
			Date datecreer) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.seuil = seuil;
		this.prix = prix;
		this.qte = qte;
		this.categorie = categorie;
		this.datecreer = datecreer;
	}
	public ProduitModel(Object object, String nomp, Float seuilp, String descriptionp, float prixp, int i, int id2) {
		this.nom = nomp;
		this.description = descriptionp;
		this.seuil = seuilp;
		this.prix = prixp;
		this.categorie = i;
		this.id = id2;
	}
	
	public ProduitModel(int id2) {
		this.id = id2;
	}
	
	public ProduitModel(String l, String d, float s, int i, float p) {
		this.nom = l;
		this.description = d;
		this.seuil = s;
		this.prix = p;
		this.categorie = i;
	}
	
	
	public ProduitModel(double qte2, int id2) {
		this.qte = qte2;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSeuil() {
		return seuil;
	}
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public double getQte() {
		return qte;
	}
	public void setQte(double qte) {
		this.qte = qte;
	}
	public int getCategorie() {
		return categorie;
	}
	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}
	public Date getDatecreer() {
		return datecreer;
	}
	public void setDatecreer(Date datecreer) {
		this.datecreer = datecreer;
	}
	
	
}

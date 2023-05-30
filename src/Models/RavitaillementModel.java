package Models;

import java.util.Date;

public class RavitaillementModel {
	private int id;
	private double quantite;
	private Date daterav;
	private int produit;
	
	public RavitaillementModel(int id, double quantite, Date daterav, int produit) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.daterav = daterav;
		this.produit = produit;
	}


	

	public RavitaillementModel(double qte, int id2) {
		this.produit = id2;
		this.quantite = qte;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public Date getDaterav() {
		return daterav;
	}

	public void setDaterav(Date daterav) {
		this.daterav = daterav;
	}

	public int getProduit() {
		return produit;
	}

	public void setProduit(int produit) {
		this.produit = produit;
	}
	
	
}


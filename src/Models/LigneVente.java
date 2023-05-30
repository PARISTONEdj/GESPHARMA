package Models;

public class LigneVente {
	private int id;
	private double quantite;
	private int produit;
	private int vente;
	public LigneVente(int id, double quantite, int produit, int vente) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.produit = produit;
		this.vente = vente;
	}
	
	
	public LigneVente(double q1, int i, int max) {
		this.quantite = q1;
		this.produit = i;
		this.vente = max;
	}
	
	
	public LigneVente(int id2) {
		this.id = id2;
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
	public int getProduit() {
		return produit;
	}
	public void setProduit(int produit) {
		this.produit = produit;
	}
	public int getVente() {
		return vente;
	}
	public void setVente(int vente) {
		this.vente = vente;
	}
	
	
}


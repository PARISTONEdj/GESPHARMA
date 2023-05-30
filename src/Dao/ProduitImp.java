package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Models.CategorieModel;
import Models.ProduitModel;

public class ProduitImp implements InterfaceDao<ProduitModel>{
	Connection cnx = Connecteur.Connect();
	@Override
	public boolean inserer(ProduitModel obj) {
		boolean res = false;
		String req = "INSERT INTO produit (nom, description, seuil, prix, categorie, datecreer) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getDescription());
			ps.setDouble(3, obj.getSeuil());
			ps.setFloat(4, obj.getPrix());
			ps.setInt(5, obj.getCategorie());
			ps.setDate(6, java.sql.Date.valueOf(LocalDate.now()));			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "Produit ajouter");
			res = true;

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le produit n'est pas inseré");
			res = false;
		}

		return res;

	}
	
	@Override
	public boolean delete(ProduitModel obj) {
		boolean res = false;
		int a = obj.getId();
		String req = "delete from produit where id ='" + a + "'";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			 ps.executeUpdate();
				JOptionPane.showInternalMessageDialog(null, "Produit supprimer");
				res = true;		

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le produit n'est pas supprimer");
			res = false;
		}

		return res;

	}

	@Override
	public boolean update(ProduitModel obj) {
		boolean res = false;
		String req = "UPDATE produit set nom = ?, seuil= ?, description= ?, prix= ?, categorie= ? where id = ?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			
			ps.setString(1, obj.getNom());
			ps.setString(3, obj.getDescription());
			ps.setDouble(2, obj.getSeuil());
			ps.setFloat(4, obj.getPrix());
			ps.setInt(5, obj.getCategorie());
			ps.setInt(6, obj.getId());

			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "Produit modifier ");

				res = true;
				System.out.println("Categorie modifier");
			

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("la categorie n'est pas modifier");
			res = false;
		}

		return res;
	}
	
	public void updatequantite(ProduitModel obj) {
		String req = "UPDATE produit set qte = qte + ? where id = ?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setDouble(1, obj.getQte());
			ps.setInt(2, obj.getId());

			ps.executeUpdate();
				
				System.out.println("Quantite Augmenter");
			

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("Quantite n'est pas Augmenter");
		}
	}
	
	public void reduirquantite(ProduitModel obj) {
		String req = "UPDATE produit set qte = qte - ? where id = ?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setDouble(1, obj.getQte());
			ps.setInt(2, obj.getId());

			ps.executeUpdate();
				
				System.out.println("Quantite reduit");
			

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("la quantite n'est pas reduit");
		}
	}
	
}

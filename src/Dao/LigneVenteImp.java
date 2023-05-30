package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Models.LigneVente;
import Models.VenteModel;

public class LigneVenteImp implements InterfaceDao<LigneVente>{
	Connection cnx = Connecteur.Connect();
	@Override
	public boolean inserer(LigneVente obj) {
		boolean res = false;
		String req = "INSERT INTO lignevente (quantite, produit, vente) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setDouble(1, obj.getQuantite());
			ps.setInt(2, obj.getProduit());
			ps.setInt(3, obj.getVente());			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "Produit ajouter au panier");
			res = true;

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("vente impossible");
			res = false;
		}

		return res;

	}
	
	@Override
	public boolean delete(LigneVente obj) {
		boolean res = false;
		int a = obj.getId();
		String req = "delete from lignevente where id ='" + a + "'";
		String req2 = "select produit, quantite from lignevente where id ='" + a + "'";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			
			PreparedStatement ps1 = cnx.prepareStatement(req2);
			 ps.executeUpdate();
			ResultSet rs = ps1.executeQuery();
	            while (rs.next()) {
	            int id = rs.getInt("produit");
	            double qte = rs.getDouble("quantite");
				res = true;	
				JOptionPane.showInternalMessageDialog(null, "Produit retirer du panier");

	            }

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le produit n'est pas retirer du panier");
			res = false;
		}

		return res;
	}
	@Override
	public boolean update(LigneVente obj) {
		// TODO Auto-generated method stub
		return false;
	}
}

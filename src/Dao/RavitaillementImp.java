package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Models.ProduitModel;
import Models.RavitaillementModel;

public class RavitaillementImp implements InterfaceDao<RavitaillementModel>{
	Connection cnx = Connecteur.Connect();
	@Override
	public boolean inserer(RavitaillementModel obj) {
		boolean res = false;
		String req = "INSERT INTO ravitaillement (quantite, produit, daterav) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setDouble(1, obj.getQuantite());
			ps.setInt(2, obj.getProduit());
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "Ravitaillement effectuer");
			res = true;

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le ravitaillement n'est pas effectuer");
			res = false;
		}

		return res;

	}
	@Override
	public boolean delete(RavitaillementModel obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(RavitaillementModel obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}

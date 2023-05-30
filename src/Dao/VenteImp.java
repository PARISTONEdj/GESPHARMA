package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Models.ProduitModel;
import Models.VenteModel;

public class VenteImp implements InterfaceDao<VenteModel>{
	Connection cnx = Connecteur.Connect();
	@Override
	public boolean inserer(VenteModel obj) {
		boolean res = false;
		String req = "INSERT INTO vente (client, employer, datevente) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setInt(1, obj.getClient());
			ps.setInt(2, obj.getEmployer());
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "panier charger");
			res = true;

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("panier non charger");
			res = false;
		}

		return res;

	}
	
	@Override
	public boolean delete(VenteModel obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(VenteModel obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
	

}

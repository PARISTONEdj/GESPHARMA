package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Interfaces.Menu;
import Models.CategorieModel;
import Models.EmployerModel;

public class CategorieImp implements InterfaceDao<CategorieModel>{
	
	Connection cnx = Connecteur.Connect();
	@Override
	public boolean inserer(CategorieModel obj) {
		boolean res = false;
		String req = "INSERT INTO categorie (libelle, datecreation) VALUES (?, ?)";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setString(1, obj.getLibelle());
			ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "Categorie ajouter");
			res = true;

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("la categorie n'est pas inseré");
			res = false;
		}

		return res;

	}
	
	@Override
	public boolean delete(CategorieModel obj) {
		boolean res = false;
		int a = obj.getId();
		String req = "delete from categorie where id ='" + a + "'";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			 ps.executeUpdate();
				JOptionPane.showInternalMessageDialog(null, "categorie supprimer");
				res = true;		

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("la categorie n'est pas supprimer");
			res = false;
		}

		return res;

	}

	@Override
	public boolean update(CategorieModel obj) {
		boolean res = false;
		String req = "UPDATE categorie set libelle = ? where id= ?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			ps.setString(1, obj.getLibelle());
			ps.setInt(2, obj.getId());

			
			ps.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "categorie modifier ");

				res = true;
				System.out.println("Categorie modifier");
			

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("la categorie n'est pas modifier");
			res = false;
		}

		return res;
	}
	
}





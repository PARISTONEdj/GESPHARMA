package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Connection.Connecteur;
import Interfaces.ListeEmployer;
import Interfaces.Menu;
import Models.EmployerModel;
import Models.EmployerModel;

public class EmployerImp implements InterfaceDao<EmployerModel> {
	Connection cnx = Connecteur.Connect();
	
	public boolean inserer(EmployerModel obj) {
		boolean res = false;
		String req = "INSERT INTO employer (nom, prenom, email, adresse, login, mdp, roles, dateajout) VALUES (?, ? , ? , ? , ? , ? , ?, ? )";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getAdresse()); 
			ps.setString(5, obj.getLogin());
			ps.setString(6, obj.getMdp());
			ps.setString(7, obj.getRoles());
			ps.setDate(8, java.sql.Date.valueOf(LocalDate.now()));


		   ps.executeUpdate();
				//cnx.close();
				res = true;
				System.out.println("employer ajouter");
				JOptionPane.showInternalMessageDialog(null, "Employer inserer ");

				
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("l'employer n'est pas inseré");
			res = false;
		}

		return res;

	}

	@Override
	public boolean delete(EmployerModel obj) {
		Connection cnx = Connecteur.Connect();
		boolean res = false;
		int a = obj.getId();
		String req = "delete from employer where id ='" + a + "'";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			int r = ps.executeUpdate();
			if (r > 0) {
				//cnx.close();
				res = true;
				System.out.println("employer supprimé");
				JOptionPane.showInternalMessageDialog(null, "Employer supprimer : ");

			} else {
				//cnx.close();
				res = false;
			}

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("l'employer n'est pas supprimé");
			res = false;
		}

		return res;

	}

	@Override
	public boolean update(EmployerModel obj) {
		Connection cnx = Connecteur.Connect();
		boolean res = false;
		String req = "UPDATE employer set nom = ?, prenom = ?, email=?, adresse=? roles=? where id = ?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getEmail());
			ps.setInt(6, obj.getId());
			ps.setString(4, obj.getAdresse());
			ps.setString(5, obj.getRoles());
			int r = ps.executeUpdate();
			if (r > 0) {
				//cnx.close();
				res = true;
				JOptionPane.showInternalMessageDialog(null, "Employer modifier : ");
				System.out.println("Employer modifier");
				
			} else {
				//cnx.close();
				res = false;
			}

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("l'employer n'est pas modifier");
			res = false;
		}

		return res;
	}
	
	
	public boolean Connexion(EmployerModel obj) {
		boolean res = false;
		String req = "SELECT id, roles FROM employer WHERE login=? AND mdp=?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getMdp());
			
			ResultSet rs = ps.executeQuery();
			
			 if (rs.next()) {
				 res = true;
             	int userId = rs.getInt(1);
             	String role = rs.getString(2);
            	donnerRole(role);
             	
            	UserSession.setUserId(userId);
             	
				 JOptionPane.showInternalMessageDialog(null, "Connexion reussie");
					System.out.println("employer trouver");
				 	
             	}
			 else {
				 JOptionPane.showInternalMessageDialog(null, "employer introuvable");
					System.out.println("employer introuvable");
				 res = false;
			 }
			

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("impossible");
			res = false;
		}
		
		return res;

	}
	
	
	 public void donnerRole(String role) {
		 if(role.equals("Admin")) {
         	UserSession.setRole(1);
     	}
     	if(role.equals("Pharmacien")) {

         	UserSession.setRole(2);

     	}if(role.equals("Caissier")) {
         	UserSession.setRole(3);
     	}
	 }
	
	

}





package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Models.ClientModel;

public class ClientImp implements InterfaceDao<ClientModel>{
Connection cnx = Connecteur.Connect();
	
	public boolean inserer(ClientModel obj) {
		boolean res = false;
		String req = "INSERT INTO client (nom, prenom, adresse, telephone, dateajout) VALUES (?, ? , ? , ? , ? )";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getAdresse());
			ps.setString(4, obj.getTelephone()); 
			ps.setDate(5, java.sql.Date.valueOf(LocalDate.now()));


		   ps.executeUpdate();
				//cnx.close();
				res = true;
				System.out.println("client ajouter");
				JOptionPane.showInternalMessageDialog(null, "client inserer ");

				
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le client n'est pas inseré");
			res = false;
		}

		return res;

	}

	@Override
	public boolean delete(ClientModel obj) {
		Connection cnx = Connecteur.Connect();
		boolean res = false;
		int a = obj.getId();
		String req = "delete from client where id ='" + a + "'";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			int r = ps.executeUpdate();
			if (r > 0) {
				//cnx.close();
				res = true;
				System.out.println("client supprimé");
				JOptionPane.showInternalMessageDialog(null, "client supprimer : ");

			} else {
				//cnx.close();
				res = false;
			}

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le client n'est pas supprimé");
			res = false;
		}

		return res;

	}

	@Override
	public boolean update(ClientModel obj) {
		Connection cnx = Connecteur.Connect();
		boolean res = false;
		String req = "UPDATE client set nom = ?, prenom = ?, adresse=?, telephone=? where id = ?";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);

			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getAdresse());
			ps.setInt(5, obj.getId());
			ps.setString(4, obj.getTelephone());
			int r = ps.executeUpdate();
			if (r > 0) {
				//cnx.close();
				res = true;
				JOptionPane.showInternalMessageDialog(null, "Client modifier : ");
				System.out.println("Client modifier");
				
			} else {
				//cnx.close();
				res = false;
			}

		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("le client n'est pas modifier");
			res = false;
		}

		return res;
	}
	
	
	
	
	
}

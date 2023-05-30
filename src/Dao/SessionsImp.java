package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import Connection.Connecteur;
import Models.Sessions;

public class SessionsImp implements InterfaceDao<Sessions> {
	Connection cnx = Connecteur.Connect();

	@Override
	public boolean inserer(Sessions obj) {
		boolean res = false;
		String req = "INSERT INTO sessions (employer, heuredebut, datejour) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = cnx.prepareStatement(req);
			ps.setInt(1, obj.getEmployer());
            ps.setTime(2, Time.valueOf(LocalTime.now()));
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));


		   ps.executeUpdate();
				res = true;
				System.out.println("client ajouter");
				JOptionPane.showInternalMessageDialog(null, "Sessions activer");

				
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "nous avons une erreur : " + e.getMessage());
			System.out.println("Sessions expirer");
			res = false;
		}

		return res;
	}

	@Override
	public boolean delete(Sessions obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Sessions obj) {
		// TODO Auto-generated method stub
		return false;
	}

}

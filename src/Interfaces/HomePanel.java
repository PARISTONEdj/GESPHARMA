package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	public HomePanel() throws SQLException {
		setBackground(SystemColor.activeCaption);
		setSize(714, 566);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 11, 662, 114);
		add(panel);
		panel.setLayout(new GridLayout(1, 4));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(27, 78, 101, 25);
		panel_1.add(lblNewLabel);
		
		JLabel employer = new JLabel("New label");
		employer.setFont(new Font("Tahoma", Font.BOLD, 22));
		employer.setForeground(Color.RED);
		employer.setBounds(62, 36, 46, 31);
		panel_1.add(employer);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblClient = new JLabel("CLIENT");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClient.setBounds(52, 77, 71, 26);
		panel_2.add(lblClient);
		
		JLabel client = new JLabel("New label");
		client.setForeground(Color.RED);
		client.setFont(new Font("Tahoma", Font.BOLD, 22));
		client.setBounds(63, 35, 46, 31);
		panel_2.add(client);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLUE);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblProduit = new JLabel("PRODUIT");
		lblProduit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProduit.setBounds(45, 78, 88, 25);
		panel_3.add(lblProduit);
		
		JLabel produit = new JLabel("New label");
		produit.setForeground(Color.RED);
		produit.setFont(new Font("Tahoma", Font.BOLD, 22));
		produit.setBounds(71, 33, 46, 31);
		panel_3.add(produit);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblVente = new JLabel("VENTE");
		lblVente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVente.setBounds(58, 78, 83, 25);
		panel_4.add(lblVente);
		
		JLabel vente = new JLabel("New label");
		vente.setForeground(Color.RED);
		vente.setFont(new Font("Tahoma", Font.BOLD, 22));
		vente.setBounds(66, 36, 46, 31);
		panel_4.add(vente);
		
		String e = "SELECT COUNT(id) as max from employer";
		int req1 = max(e); 
		employer.setText(Integer.toString(req1));
		
		String c = "SELECT COUNT(id) as max from client";
		int req2 = max(c); 
		client.setText(Integer.toString(req2));

		
		String p = "SELECT COUNT(id) as max from produit";
		int req3 = max(p); 
		produit.setText(Integer.toString(req3));

		
		String v = "SELECT COUNT(id) as max from vente";
		int req4 = max(e); 
		vente.setText(Integer.toString(req4));

		
		
	}
	
	 public int max(String sql) throws SQLException {
	    	int max = 0;
	    	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
		        PreparedStatement stmt = con.prepareStatement(sql);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            max = rs.getInt("max");
		        }
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    	return max;
	    }
}

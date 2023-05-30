package Interfaces;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

import Connection.Connecteur;
import Dao.ProduitImp;
import Models.ProduitModel;

public class AddProduit extends JPanel {
	private JTextField seuil;
	private JTextField lib;
	private JTextField prix;
	private JTextArea description;
    PreparedStatement statement = null;
    private JComboBox  libellecat;
	 Connection cnx = Connecteur.Connect();


	/**
	 * Create the panel.
	 */
	public AddProduit(ListeProduit listeProduit, RavitaillementPanel ravitaillementPanel) {
		setBackground(SystemColor.activeCaption);
		setBounds(10, 67, 694, 488);
		setLayout(null);
		setName("produit panel");
		ProduitImp pi = new ProduitImp();

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(20, 0, 649, 120);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nouveau produit");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\images\\pilule.png"));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 0, 282, 120);
		panel.add(lblNewLabel);
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(22, 142, 647, 335);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(205, 241, 121, 40);
		panel_1.add(btnNewButton);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(350, 241, 121, 40);
		panel_1.add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annuler();
			}
		});
		btnAnnuler.setForeground(Color.BLACK);
		btnAnnuler.setBackground(UIManager.getColor("Button.background"));
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		prix = new JTextField();
		prix.setBounds(87, 186, 206, 25);
		panel_1.add(prix);
		
		JLabel lblNewLabel_3 = new JLabel("Prix");
		lblNewLabel_3.setBounds(22, 183, 46, 27);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		seuil = new JTextField();
		seuil.setBounds(430, 186, 206, 25);
		panel_1.add(seuil);
		
		JLabel lblNewLabel_5 = new JLabel("Quantite Seuil");
		lblNewLabel_5.setBounds(325, 179, 95, 35);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lib = new JTextField();
		lib.setBounds(87, 134, 206, 25);
		panel_1.add(lib);
		
		JLabel lblNewLabel_2 = new JLabel("Libelle");
		lblNewLabel_2.setBounds(22, 137, 73, 14);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4 = new JLabel("Categorie");
		lblNewLabel_4.setBounds(325, 127, 68, 34);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		

		libellecat = new JComboBox();
		libellecat.setBounds(431, 133, 209, 26);
		panel_1.add(libellecat);
		
		comb();
		
		JLabel lblNewLabel_1 = new JLabel("Description du produit");
		lblNewLabel_1.setBounds(237, 67, 169, 27);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		description = new JTextArea();
		description.setBounds(406, 11, 234, 83);
		panel_1.add(description);
		description.setBackground(Color.LIGHT_GRAY);
		description.setText("description");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String l = lib.getText();
					String d = description.getText();
					
					String h = (String) libellecat.getSelectedItem();
					
				
				if (l.equals("") || d.equals("description") || prix.getText().equals("") || seuil.getText().equals("") || h=="SELECT") {
	                    JOptionPane.showMessageDialog(null, "Renseigner tous les champs");
	                } 
				 else {
					 try {
							float p = Float.parseFloat(prix.getText());
							float s = Float.parseFloat(seuil.getText());
							if(s<=0 || p<=0) {
			                    JOptionPane.showMessageDialog(null, "le prix ou seuil ne doivent etre inferieur a 0");
						 }
							else {
	                        
	                        PreparedStatement stmt = cnx.prepareStatement("SELECT id from categorie WHERE libelle=?");
	                        stmt.setString(1, h);
	                        ResultSet rs = stmt.executeQuery();
	                        if (rs.next()) {
	    			            int i = rs.getInt("id");
	                            String sql = "INSERT INTO produit (nom, description, seuil, categorie, prix, datecreer) VALUES (?, ? , ? , ? , ?, ? )";
		                        statement = cnx.prepareStatement(sql);
		                        if (pi.inserer(new ProduitModel(l, d, s, i, p)))
		      					{
		      						JOptionPane.showMessageDialog(null,"succes", "echec",
		      							JOptionPane.INFORMATION_MESSAGE);
			                        ravitaillementPanel.charger();

		      					}
		                        listeProduit.charger();
		                        annuler();
		                        ravitaillementPanel.charger();
	                        }
	                        
	                       
							}

	                    } catch (Exception e1) {
	                        System.out.println("erreur" + e1.getMessage());
	                        JOptionPane.showMessageDialog(null, "Le prix et le seuil doivent etre des nombres");

	                    }
				 }

			}
			
		});

	}
		

	public void comb() {
		try {   
            String sql="Select libelle FROM categorie";
            PreparedStatement ps=cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            libellecat.removeAllItems();
            libellecat.addItem("SELECT");            
            while (rs.next()) {
            String name = rs.getString("libelle");
            libellecat.addItem(name);            
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
	}
	
	public void annuler() {
		seuil.setText("");
		lib.setText("");
		description.setText("description");
		prix.setText("");
	}
}

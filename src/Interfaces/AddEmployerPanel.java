package Interfaces;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Dao.EmployerImp;
import Interfaces.ListeEmployer;
import Models.EmployerModel;

public class AddEmployerPanel extends JPanel {
	
	private JTextField nom;
	private JTextField prenom;
	private JTextField email;
	private JTextField adresse;
	private JTextField login;
	private JTextField mdp;
    PreparedStatement statement = null;


	public AddEmployerPanel(ListeEmployer listeEmployer) {
		setBackground(SystemColor.activeCaption);
		setBounds(23, 61, 661, 494);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Panel.background"));
		panel.setBounds(0, 0, 661, 47);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter un employer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(204, 0, 174, 47);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(61, 68, 532, 172);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Information de base");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 11, 193, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nom");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(21, 60, 46, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Prenom");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(21, 88, 78, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(21, 118, 78, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Adresse");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(21, 143, 101, 14);
		panel_1.add(lblNewLabel_5);
		
		nom = new JTextField();
		nom.setBounds(227, 59, 243, 20);
		panel_1.add(nom);
		
		prenom = new JTextField();;
		prenom.setBounds(227, 82, 243, 20);
		panel_1.add(prenom);
		
		email = new JTextField();;
		email.setBounds(227, 106, 243, 20);
		panel_1.add(email);
		
		adresse = new JTextField();;
		adresse.setBounds(227, 137, 243, 20);
		panel_1.add(adresse);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(61, 251, 532, 154);
		add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Information de Connexion");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 11, 191, 14);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Login");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(21, 43, 46, 24);
		panel_1_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Mot de Passe");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(21, 88, 144, 14);
		panel_1_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Role");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(21, 119, 46, 14);
		panel_1_1.add(lblNewLabel_4_1);
		
		login = new JTextField();;
		login.setBounds(235, 47, 243, 20);
		panel_1_1.add(login);
		
		mdp = new JTextField();;
		mdp.setBounds(235, 82, 243, 20);
		panel_1_1.add(mdp);
		
		JComboBox role = new JComboBox();
		role.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Pharmacien", "Caissier"}));
		role.setBounds(235, 117, 243, 22);
		panel_1_1.add(role);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployerImp ei = new EmployerImp();
				String n = nom.getText();
				String p = prenom.getText();
				String em = email.getText();
				String a = adresse.getText();
				String l = login.getText();
				String m = mdp.getText();
				String r = role.getSelectedItem().toString();
				 if (n.equals("") || p.equals("")|| em.equals("") || a.equals("") || l.equals("") || m.equals("")) {
	                    JOptionPane.showMessageDialog(null, "Renseigner tous les champs");
	                } 
				 else {
					 try {
						 
	                        if (ei.inserer(new EmployerModel(n, p, em, a, l, m, r)))
	    					{
	    						JOptionPane.showMessageDialog(null,"succes", "echec",
	    								JOptionPane.INFORMATION_MESSAGE);
		                        listeEmployer.charger();

	    					}

	                        annuler();

	                    } catch (Exception e1) {
	                        System.out.println("erreur" + e1.getMessage());
	                        JOptionPane.showMessageDialog(null, "Erreur de Connexion");

	                    }
				 }

			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setBounds(146, 416, 121, 35);
		add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annuler();
			}
		});
		btnAnnuler.setBackground(UIManager.getColor("Button.background"));
		btnAnnuler.setBounds(382, 416, 135, 35);
		add(btnAnnuler);

	}
	
	public void annuler() {
		nom.setText("");
		prenom.setText("");
		email.setText("");
		adresse.setText("");
		login.setText("");
		mdp.setText("");
	}

}

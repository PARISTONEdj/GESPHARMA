package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Connection.Connecteur;
import Dao.EmployerImp;
import Interfaces.*;
import Models.EmployerModel;
import javax.swing.JComboBox;

public class ListeEmployer extends JPanel {

	private JTextField recherche;
	 private JTable table_1;
	 private  JScrollPane scrollPane;
	 private int id;
	 private EmployerPanel employerPanel;
	 //public String data;
	 public String dt;
	 private JLabel lblNewLabel_1;
	 private JTextField nom;
	 private JTextField prenom;
	 private JTextField adresse;
	 private JTextField email;
	 PreparedStatement statement = null;
 	Connection cnx = Connecteur.Connect();
 	private JComboBox role;


	
	public ListeEmployer(EmployerPanel employerPanel) {
		setBackground(SystemColor.activeCaption);
		setBounds(23, 61, 661, 494);
		setLayout(null);
		this.employerPanel = employerPanel;
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Panel.background"));
		panel.setBounds(0, 0, 661, 183);
		add(panel);
		panel.setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Liste des employer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 126, 214, 25);
		panel.add(lblNewLabel);
		
		recherche = new JTextField();
		recherche.setBounds(48, 152, 201, 20);
		panel.add(recherche);
		
		lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 29, 37, 25);
		panel.add(lblNewLabel_1);
		
		nom = new JTextField();
		nom.setBounds(48, 31, 201, 20);
		panel.add(nom);
		
		prenom = new JTextField();
		prenom.setBounds(330, 31, 201, 20);
		panel.add(prenom);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mise a jour employer");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 0, 150, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Prenom");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(272, 29, 66, 25);
		panel.add(lblNewLabel_1_2);
		
		adresse = new JTextField();
		adresse.setBounds(330, 67, 201, 20);
		panel.add(adresse);
		
		email = new JTextField();
		email.setBounds(48, 65, 201, 20);
		panel.add(email);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Adresse");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1.setBounds(272, 65, 66, 25);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Email");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_2.setBounds(10, 60, 66, 25);
		panel.add(lblNewLabel_1_2_2);
		
		JButton update = new JButton("Mettre a jour");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0) {
	                try {
						EmployerImp ei = new EmployerImp();
						
						if(role.getSelectedItem()=="SELECT") {
							JOptionPane.showMessageDialog(null, "selectionner un role");
						}
						
						if (ei.update(new EmployerModel(nom.getText(), prenom.getText(), email.getText(), adresse.getText(), role.getSelectedItem(), id)))
    					{
    						JOptionPane.showMessageDialog(null, "succes", "echec",
    							JOptionPane.INFORMATION_MESSAGE);
    						id = 0;
    					}
	                        annuler();
                        	charger();


                   } catch (Exception e1) {
                       System.out.println("erreur" + e1.getMessage());
                       JOptionPane.showMessageDialog(null, "erreur mise a jour");
                   }

       			
       		}
				else {
                   JOptionPane.showMessageDialog(null, "vous devevez selectionner un element du tableau");

				}
			}
		});
		update.setBackground(UIManager.getColor("Button.background"));
		update.setFont(new Font("Tahoma", Font.PLAIN, 12));
		update.setBounds(541, 23, 110, 31);
		panel.add(update);
		
		JButton delete = new JButton("Supprimer");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0) {
					EmployerImp ei = new EmployerImp();

				if(JOptionPane.showConfirmDialog(null, "voulez vous vraimant supprimer l' employer ?") ==0) {
	                try {
	                		
	                	 if (ei.delete(new EmployerModel(id)))
	    					{
	    						JOptionPane.showMessageDialog(null, "succes", "echec",
	    						JOptionPane.INFORMATION_MESSAGE);
	    						id = 0;

	    					}
                     		charger();
	                        annuler();
                   } catch (Exception e1) {
                       System.out.println("erreur" + e1.getMessage());
                       JOptionPane.showMessageDialog(null, "empossible de supprimer");

                   }
				}
				}
				else {
                   JOptionPane.showMessageDialog(null, "vous devevez selectionner un element du tableau");

				}
			}
		});
		delete.setBackground(UIManager.getColor("Button.background"));
		delete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delete.setBounds(541, 69, 110, 25);
		panel.add(delete);
		
		JButton rech = new JButton("Recherche");
		rech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rech() ;
			}
		});
		rech.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rech.setBackground(UIManager.getColor("Button.background"));
		rech.setBounds(277, 146, 110, 31);
		panel.add(rech);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annuler();
			}
		});
		annuler.setFont(new Font("Tahoma", Font.PLAIN, 12));
		annuler.setBackground(UIManager.getColor("Button.background"));
		annuler.setBounds(407, 146, 110, 31);
		panel.add(annuler);
		
		role = new JComboBox();
		role.setBounds(48, 96, 201, 22);
		role.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "Admin", "Pharmacien", "Caissier"}));
		panel.add(role);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Role");
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_2_1.setBounds(10, 96, 66, 25);
		panel.add(lblNewLabel_1_2_2_1);
		
		 table_1 = new JTable();
		 table_1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		try {
	            	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
	            
	            	Statement st = cnx.createStatement();
	            	ResultSet res = st.executeQuery("select nom, prenom, email, adresse, roles from employer where id="+id);
	            	if(res.next()) {
	            		
	            		nom.setText(res.getString(1));
	            		prenom.setText(res.getString(2));
	            		email.setText(res.getString(3));
	            		adresse.setText(res.getString(4));
	            		role.setSelectedItem(res.getString(5));
	            	}
	            	res.close();
	            	st.close();
	            	}
	            	catch(Exception e1){
	            	System.out.println("erreur"+e1.getMessage());

	            	}
		 	}
		 });
	        

	        scrollPane = new JScrollPane(table_1);
	        scrollPane.setBounds(0, 194, 661, 289);
	        JViewport viewport = scrollPane.getViewport();
	        //scrollPane.setVisible(false);
	        add(scrollPane);
	        table_1.setBackground(Color.WHITE);
	        
			EmployerImp ei = new EmployerImp();

			charger();

	}
	
	
	public void charger() {
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery("select id, nom, prenom, adresse, email, roles, dateajout from employer");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "nom";
            colName[2] = "prenom";
            colName[3] = "Adresse";
            colName[4] = "Email";
            colName[5] = "Roles";
            colName[6] = "Date d'ajout";

            String b1 = "SELECT";
            model_1.setColumnIdentifiers(colName);
            String id, nom, prenom, adresse, email, dateajout, roles;
            while (res.next()) {
            	 id = res.getString(1);
	                nom = res.getString(2);
	                prenom = res.getString(3);
	                adresse = res.getString(4);
	                email = res.getString(5);
	                roles = res.getString(6);
	                dateajout = res.getString(7);
	                String[] row = {id, nom, prenom, adresse, email, roles, dateajout};
                model_1.addRow(row);
            }
            st.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
        dt = recherche.getText();
    }
	
	
	
	public void annuler() {
		nom.setText("");
		prenom.setText("");
		adresse.setText("");
		email.setText("");
		recherche.setText("");
		charger();
		role.setSelectedItem("SELECT");
		id = 0;
	}
	
	public void rech() {
		String r = recherche.getText();
		if(r.equals("")) {
   		JOptionPane.showMessageDialog(null, "remplir champs");
		}
		else {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	            Statement st = con.createStatement();
	            ResultSet res = st.executeQuery("select id, nom, prenom, adresse, email, dateajout from employer where nom like '%"+r+"%' or prenom like '%"+r+"%'");
	            ResultSetMetaData rsm = res.getMetaData();
	            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
	            model_1.setRowCount(0);
	            int cols = rsm.getColumnCount();
	            String[] colName = new String[cols];
	            colName[0] = "id";
	            colName[1] = "nom";
	            colName[2] = "prenom";
	            colName[3] = "Adresse";
	            colName[4] = "Email";
	            colName[5] = "Date d'ajout";

	            String b1 = "SELECT";
	            model_1.setColumnIdentifiers(colName);
	            String id, nom, prenom, adresse, email, dateajout;
	            while (res.next()) {
	            	 id = res.getString(1);
		                nom = res.getString(2);
		                prenom = res.getString(3);
		                adresse = res.getString(4);
		                email = res.getString(5);
		                dateajout = res.getString(6);
		                String[] row = {id, nom, prenom, adresse, email, dateajout};
	                model_1.addRow(row);
	            }
	            st.close();
	            con.close();

	        } catch (Exception e) {
	            System.out.println("erreur" + e.getMessage());
	        }
		}
	}
}

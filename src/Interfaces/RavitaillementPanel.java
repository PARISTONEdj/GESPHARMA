package Interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import Connection.Connecteur;
import Dao.ProduitImp;
import Dao.RavitaillementImp;
import Models.ProduitModel;
import Models.RavitaillementModel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class RavitaillementPanel extends JPanel {
	private JTable table_1, table_2;
	 private  JScrollPane scrollPane;
	 private JTextField recherche;
	 private JTextField produit;
	 private JTextField seuil;
	 private JTextField quantite;
	 private JScrollPane scrollPane_1;
	 private int id;
	 private JTextField recherche1;
	 private JPanel panel;
	 private JPanel panel_1;
	 Connection cnx = Connecteur.Connect();


	
	public RavitaillementPanel() {
		setBackground(SystemColor.activeCaption);
		setBounds(10, 67, 694, 445);
		setLayout(null);
        charger();
        
		RavitaillementImp ri = new RavitaillementImp();
		
		ProduitImp pi = new ProduitImp();     
         panel = new JPanel();
         panel.setBounds(10, 207, 674, 284);
         add(panel);
         panel.setLayout(null);
         
		 table_2 = new JTable();
		 scrollPane_1 = new JScrollPane(table_2);
		 scrollPane_1.setBounds(21, 45, 556, 228);
		 panel.add(scrollPane_1);
		 
		 JLabel lblNewLabel_3 = new JLabel("Liste des Ravitaillements");
		 lblNewLabel_3.setBounds(20, 11, 153, 23);
		 panel.add(lblNewLabel_3);
		 lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 
		 recherche1 = new JTextField();
		 recherche1.setBounds(172, 14, 176, 20);
		 panel.add(recherche1);
		 
		 JButton Recherche_1 = new JButton("Recherche");
		 Recherche_1.setBounds(368, 11, 118, 23);
		 panel.add(Recherche_1);
		 
		 JButton annule = new JButton("Annuler");
		 annule.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		charg();
		 	}
		 });
		 annule.setBounds(499, 12, 92, 23);
		 panel.add(annule);
		 
		 panel_1 = new JPanel();
		 panel_1.setBounds(10, 7, 674, 189);
		 add(panel_1);
		 panel_1.setLayout(null);
		 
		  table_1 = new JTable();
		  charger();
		  table_1.addMouseListener(new MouseAdapter() {
			 
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		try {
			 			
		             	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
		             	Statement st = cnx.createStatement();
		             	ResultSet res = st.executeQuery("select id, nom, seuil, qte from produit where id="+id);
		             	if(res.next()) {
		             		produit.setText(res.getString(2));
		             		seuil.setText(res.getString(3));
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
		  scrollPane.setBounds(10, 46, 415, 132);
		  panel_1.add(scrollPane);
		  JViewport viewport = scrollPane.getViewport();
		  table_1.setBackground(Color.WHITE);
		  
		  recherche = new JTextField ();
		  recherche.setBounds(10, 15, 188, 20);
		  panel_1.add(recherche);
		  
		  JButton Recherche = new JButton("Recherche");
		  Recherche.setBounds(208, 12, 118, 23);
		  panel_1.add(Recherche);
		  
		  JButton btnAnnuler = new JButton("Annuler");
		  btnAnnuler.setBounds(336, 12, 89, 23);
		  panel_1.add(btnAnnuler);
		  
		  JLabel lblNewLabel = new JLabel("Produit");
		  lblNewLabel.setBounds(445, 15, 72, 14);
		  panel_1.add(lblNewLabel);
		  
		  produit = new JTextField();
		  produit.setBounds(445, 30, 188, 20);
		  panel_1.add(produit);
		  produit.setEditable(false);
		  
		  JLabel lblNewLabel_1 = new JLabel("Seuil");
		  lblNewLabel_1.setBounds(445, 61, 46, 14);
		  panel_1.add(lblNewLabel_1);
		  
		  seuil = new JTextField();
		  seuil.setBounds(445, 75, 188, 20);
		  panel_1.add(seuil);
		  seuil.setEditable(false);
		  
		  JLabel lblNewLabel_2 = new JLabel("Quantite");
		  lblNewLabel_2.setBounds(445, 110, 72, 14);
		  panel_1.add(lblNewLabel_2);
		  
		  quantite = new JTextField();
		  quantite.setBounds(445, 124, 188, 20);
		  panel_1.add(quantite);
		  
		  JButton btnValider = new JButton("Valider");
		  btnValider.setBounds(445, 155, 89, 23);
		  panel_1.add(btnValider);
		  btnValider.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		String qt = quantite.getText();

		  		if(id==0 || qt.equals("")) {
		              JOptionPane.showMessageDialog(null, "selectionner un produit dans le tableau et renseigner la quantite");
		  		}
		  		else {
		  			double qte = Double.parseDouble(quantite.getText());
		  			if(qte<=0) {
		                  JOptionPane.showMessageDialog(null, "la quantite doit etre superieur a 0");
		  			}
		  			else {
		  				

		  				 if (ri.inserer(new RavitaillementModel(qte, id)))
	      					{
	      						JOptionPane.showMessageDialog(null, "succes", "echec",
	      							JOptionPane.INFORMATION_MESSAGE);
	      						pi.updatequantite(new ProduitModel(qte, id));
	      						
	      					}
		                      charg();
		                      charger();
		  			}
		  			
		  		}
		  	}
		  });
		  Recherche.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		String r = recherche.getText();
				if(r.equals("")) {
		    		JOptionPane.showMessageDialog(null, "remplir champs");
				}
				else {
					try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
			            Statement st = con.createStatement();
			            ResultSet res = st.executeQuery("select id, nom, seuil, qte from produit where nom like '%"+r+"%' ");
			            ResultSetMetaData rsm = res.getMetaData();
			            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
			            model_1.setRowCount(0);
			            int cols = rsm.getColumnCount();
			            String[] colName = new String[cols+1];
			            colName[0] = "id";
			            colName[1] = "nom produit";
			            colName[2] = "seuil";
			            colName[3] = "Quantite";
			            colName[4] = "Etat";
			            model_1.setColumnIdentifiers(colName);
			            String id, nom, seuil, etat = null, qte;
			            while (res.next()) {
			            	 	id = res.getString(1);
				                nom = res.getString(2);
				                seuil = res.getString(3);
				                qte = res.getString(4);
				                Float a = Float.parseFloat(qte);
				                Float b = Float.parseFloat(seuil);
				                if(a<b) {
				                	etat="A ravitailler";
				                }
				                else {
				                	etat="Normale";
				                }
				                String[] row = {id, nom, seuil, qte, etat};
			                model_1.addRow(row);
			            }
			            st.close();
			            con.close();

			        } catch (Exception e1) {
			            System.out.println("erreur" + e1.getMessage());
			        }
				}
		  	}
		  });
		 Recherche_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String r1 = recherche1.getText();
				if(r1.equals("")) {
		    		JOptionPane.showMessageDialog(null, "remplir champs");
				}
				else {
					try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
			            Statement st = con.createStatement();
			            ResultSet res = st.executeQuery("select r.id, p.nom, r.quantite, r.daterav from ravitaillement r inner join produit p on r.produit = p.id where p.nom like '%"+r1+"%' ");
			            ResultSetMetaData rsm = res.getMetaData();
			            DefaultTableModel model_1 = (DefaultTableModel) table_2.getModel();
			            model_1.setRowCount(0);
			            int cols = rsm.getColumnCount();
			            String[] colName = new String[cols];
			            colName[0] = "id";
			            colName[1] = "nom produit";
			            colName[2] = "Quantite";
			            colName[3] = "Date";
			            model_1.setColumnIdentifiers(colName);
			            String id, nom, quantite, date;
			            while (res.next()) {
			            	 	id = res.getString(1);
				                nom = res.getString(2);
				                quantite = res.getString(3);
				                date= res.getString(4);
				                String[] row = {id, nom, quantite, date};
			                model_1.addRow(row);
			            }
			            st.close();
			            con.close();

			        } catch (Exception e1) {
			            System.out.println("erreur" + e1.getMessage());
			        }
				}
		 	}
		 });
         
        
         charg();


	}
	
	
	public void charg() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select r.id, p.nom, r.quantite, r.daterav from ravitaillement r inner join produit p on r.produit = p.id \r\n");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_2.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "nom produit";
            colName[2] = "Quantite";
            colName[3] = "Date";
            model_1.setColumnIdentifiers(colName);
            String id, nom, quantite, date;
            while (res.next()) {
            	 	id = res.getString(1);
	                nom = res.getString(2);
	                quantite = res.getString(3);
	                date= res.getString(4);
	                String[] row = {id, nom, quantite, date};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
	
	public void charger() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select id, nom, seuil, qte from produit");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols+1];
            colName[0] = "id";
            colName[1] = "produit";
            colName[2] = "seuil";
            colName[3] = "Quantite";
            colName[4] = "Etat";

            model_1.setColumnIdentifiers(colName);
            String id, produit, seuil, quantite, etat;
            while (res.next()) {
            	 	id = res.getString(1);
	                produit = res.getString(2);
	                seuil = res.getString(3);
	                quantite = res.getString(4);
	                Float a = Float.parseFloat(quantite);
	                Float b = Float.parseFloat(seuil);
	                if(a<b) {
	                	etat="A ravitailler";
	                }
	                else {
	                	etat="Normale";
	                }
	                String[] row = {id, produit, seuil, quantite, etat};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
}

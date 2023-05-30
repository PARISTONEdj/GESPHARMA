package Interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import Connection.Connecteur;
import Dao.CategorieImp;
import Dao.ProduitImp;
import Models.CategorieModel;
import Models.ProduitModel;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ListeProduit extends JPanel {
	
	 private JTable table_1;
	 private  JScrollPane scrollPane;
	 private JLabel lblNewLabel;
	 private JTextField nom;
	 private JTextField seuil;
	 private JTextField categorie;
	 private JTextField prix;
	 private JButton delete;
	 private JTextField recherche;
	 private JButton rech;
	 private JButton annuler;
	 private JLabel lblListeDesProduits;
	 private int id;
	 private JTextArea description;
	 private int cat;
	 private JComboBox  categori;
	 PreparedStatement statement = null;

	 Connection cnx = Connecteur.Connect();


	/**
	 * Create the panel.
	 */
	public ListeProduit(RavitaillementPanel ravitaillementPanel) {
		setBackground(SystemColor.activeCaption);
		setBounds(10, 67, 694, 488);
		setLayout(null);
		
		ProduitImp pi = new ProduitImp();

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 694, 156);
		add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Mise a jour Produit");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 0, 160, 17);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Produit");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(30, 28, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Categorie");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(303, 28, 66, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Seuil");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(30, 48, 46, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Prix");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(313, 48, 46, 14);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Description");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_4.setBounds(26, 108, 66, 14);
		panel.add(lblNewLabel_1_4);
		
		nom = new JTextField();
		nom.setBounds(102, 21, 180, 20);
		panel.add(nom);
		
		seuil = new JTextField();
		seuil.setBounds(102, 46, 180, 20);
		panel.add(seuil);
		
		categorie = new JTextField();
		
		categori = new JComboBox<>();
		categori.setBounds(447, 250, 209, 26);
		

		categori.setBounds(364, 21, 180, 20);
		panel.add(categori);
		
		
		prix = new JTextField();
		prix.setBounds(364, 46, 180, 20);
		panel.add(prix);
		
		JButton update = new JButton("Mettre a jour");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0) {

	                String nomp = nom.getText();
	                Float seuilp = Float.parseFloat(seuil.getText());
	                String descriptionp = description.getText();
	                float prixp = Float.parseFloat(prix.getText());
	                String cat = (String) categori.getSelectedItem();
	                try {

	                	 Class.forName("com.mysql.cj.jdbc.Driver");
	                        Connection con = DriverManager.getConnection(
	                                "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	                        PreparedStatement stmt = con.prepareStatement("SELECT id from categorie WHERE libelle=?");
	                        stmt.setString(1, cat);
	                        ResultSet resu = stmt.executeQuery();
	                        if (resu.next()) {
	    			            int i = resu.getInt("id");
	    			            
	    			            if (pi.update(new ProduitModel(null, nomp, seuilp, descriptionp, prixp, i, id)))
	        					{
	        						JOptionPane.showMessageDialog(null, "succes", "echec",
	        							JOptionPane.INFORMATION_MESSAGE);
	        					}

	                        ravitaillementPanel.charger();
	                        annuler();
	                        }

                    } catch (Exception e1) {
                        System.out.println("erreur" + e1.getMessage());
                    }        			
        		}
        		else {
                    JOptionPane.showMessageDialog(null, "selectionner un produit dans le tableau");
        		}
			}
		});
		update.setBounds(554, 20, 130, 23);
		panel.add(update);
		
		delete = new JButton("Supprimer");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0) {
        			if(JOptionPane.showConfirmDialog(null, "voulez vous vraimant supprimer le produit ?") ==0) {
        				String cat = categorie.getText();
        				  if (pi.delete(new ProduitModel(id)))
      					{
      						JOptionPane.showMessageDialog(null, "succes", "echec",
      							JOptionPane.INFORMATION_MESSAGE);
	                        ravitaillementPanel.charger();
	                        annuler();

      					}
		                        
    				}
        		}
        		else {
                    JOptionPane.showMessageDialog(null, "selectionner un produit dans le tableau");
        		}
			}
		});
		delete.setBounds(554, 45, 130, 23);
		panel.add(delete);
		
		description = new JTextArea();
		description.setBounds(102, 77, 180, 46);
		panel.add(description);
		
		recherche = new JTextField();
		recherche.setBounds(289, 127, 180, 20);
		panel.add(recherche);
		
		rech = new JButton("Recherche");
		rech.addActionListener(new ActionListener() {
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
			            ResultSet res = st.executeQuery("select p.id, p.nom, c.libelle, p.description, p.seuil, p.prix, p.datecreer from produit p inner join categorie c on p.categorie = c.id where p.nom like '%"+r+"%' or p.description like '%"+r+"%'");
			            ResultSetMetaData rsm = res.getMetaData();
			            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
			            model_1.setRowCount(0);
			            int cols = rsm.getColumnCount();
			            String[] colName = new String[cols];
			            colName[0] = "id";
			            colName[1] = "nom produit";
			            colName[2] = "categorie";
			            colName[3] = "Description";
			            colName[4] = "Seuil";
			            colName[5] = "prix";
			            colName[6] = "Date";
			            model_1.setColumnIdentifiers(colName);
			            String id, nom, categorie, description, date, pris, seuil;
			            while (res.next()) {
			            	 id = res.getString(1);
				                nom = res.getString(2);
				                categorie = res.getString(3);
				                description = res.getString(4);
				                seuil = res.getString(5);
				                pris = res.getString(6);
				                date = res.getString(7);
				                String[] row = {id, nom, categorie, description, seuil, pris, date};
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
		rech.setBounds(485, 126, 100, 23);
		panel.add(rech);
		
		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annuler();
			}
		});
		annuler.setBounds(595, 126, 89, 23);
		panel.add(annuler);
		
		lblListeDesProduits = new JLabel("Liste des produits");
		lblListeDesProduits.setForeground(Color.WHITE);
		lblListeDesProduits.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListeDesProduits.setBounds(10, 135, 160, 17);
		panel.add(lblListeDesProduits);
		
		
		
		 table_1 = new JTable();
		 table_1.addMouseListener(new MouseAdapter() {
			 
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		try {
			 			comb();
		            	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
		            	Statement st = cnx.createStatement();
		            	ResultSet res = st.executeQuery("select p.nom, p.description, p.prix, P.seuil, c.libelle from produit p inner join categorie c on p.categorie = c.id where p.id="+id);
		            	if(res.next()) {
		            		nom.setText(res.getString(1));
		            		seuil.setText(res.getString(4));
		            		prix.setText(res.getString(3));
		            		description.setText(res.getString(2));
		            		categori.setSelectedItem(res.getString(5));
		            		
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
	        scrollPane.setBounds(0, 176, 694, 312);
	        JViewport viewport = scrollPane.getViewport();
	        //scrollPane.setVisible(false);
	        add(scrollPane);
	        table_1.setBackground(Color.WHITE);
	        charger();
	}
	
	public void charger() {
        try {
           
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery("select p.id, nom, c.libelle, p.description, p.seuil, p.prix, p.datecreer\r\n"
            		+ "from produit p inner join categorie c\r\n"
            		+ "on p.categorie = c.id;");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "nom produit";
            colName[2] = "categorie";
            colName[3] = "Description";
            colName[4] = "Seuil";
            colName[5] = "prix";
            colName[6] = "Date";
            model_1.setColumnIdentifiers(colName);
            String id, nom, categorie, description, date, pris, seuil;
            while (res.next()) {
            	 id = res.getString(1);
	                nom = res.getString(2);
	                categorie = res.getString(3);
	                description = res.getString(4);
	                seuil = res.getString(5);
	                pris = res.getString(6);
	                date = res.getString(7);
	                String[] row = {id, nom, categorie, description, seuil, pris, date};
                model_1.addRow(row);
            }
            st.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
	
	public void annuler() {
		seuil.setText("");
		prix.setText("");
		nom.setText("");
		description.setText("");
		recherche.setText("");
		id = 0;
		categori.setSelectedItem("SELECT");
		charger();
	}
	
	public void comb() {
		try {   
			
            String sql="Select libelle FROM categorie";
            PreparedStatement ps=cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            categori.addItem("SELECT");            
            while (rs.next()) {
            String name = rs.getString("libelle");
            categori.addItem(name);            
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
	}
}

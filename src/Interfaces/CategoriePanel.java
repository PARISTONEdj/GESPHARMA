package Interfaces;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JViewport;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.Connecteur;
import Dao.CategorieImp;
import Dao.EmployerImp;
import Models.CategorieModel;
import Models.EmployerModel;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;


public class CategoriePanel extends JPanel {
		private JTextField categorie;
	    PreparedStatement statement = null;
	    private JTable table;
	    private JTable table_1;
	    private int id;
	    JButton Delete;
	    private JTextField recherche;
	 	Connection cnx = Connecteur.Connect();



	    public CategoriePanel(AddProduit addProduit) {
	        setBackground(SystemColor.activeCaption);
			setBounds(10, 67, 694, 488);
	        setLayout(null);
	        
			CategorieImp cai = new CategorieImp();

	        JPanel panel = new JPanel();
	        panel.setBackground(UIManager.getColor("Panel.background"));
	        panel.setBounds(22, 0, 664, 125);
	        add(panel);
	        panel.setLayout(null);
	        JLabel lblNewLabel = new JLabel("Nouvelle categorie");
	        lblNewLabel.setBackground(Color.GREEN);
	        lblNewLabel.setForeground(Color.GRAY);
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblNewLabel.setBounds(10, 0, 150, 34);
	        panel.add(lblNewLabel);

	        categorie = new JTextField();
	        categorie.setSize(235, 20);
	        categorie.setLocation(10, 38);
	        categorie.setBackground(Color.WHITE);
	        panel.add(categorie);

	        JButton btnNewButton = new JButton("Ajouter");
	        btnNewButton.setForeground(Color.BLACK);
	        btnNewButton.setBackground(UIManager.getColor("Button.background"));
	        btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String cat = categorie.getText();
	                if (cat.equals("")) {
	                    JOptionPane.showMessageDialog(null, "Renseigner Categorie");
	                } else {
	                	
	                	if (cai.inserer(new CategorieModel(id, cat, null)))
    					{
    						JOptionPane.showMessageDialog(null, "succes", "echec",
    							JOptionPane.INFORMATION_MESSAGE);
    					}
	                        categorie.setText("");

	                    charger();
	                    addProduit.comb();

	                }

	            }
	        });
	        btnNewButton.setBounds(265, 36, 115, 25);
	        panel.add(btnNewButton);
	        
	        JButton update = new JButton("Mettre a jour");
	        update.setForeground(Color.BLACK);
	        update.setBackground(UIManager.getColor("Button.background"));
	        update.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(id!=0) {

		                String cat = categorie.getText();
		                if (cai.update(new CategorieModel(id, cat, null)))
    					{
    						JOptionPane.showMessageDialog(null,"succes", "echec",
    							JOptionPane.INFORMATION_MESSAGE);
    						annuler();
    					}

			                    addProduit.comb();	        			
	        		}
	        		else {
                        JOptionPane.showMessageDialog(null, "selectionner une categorie dans le tableau");
	        		}
	        	}
	        });
	        update.setBounds(524, 34, 130, 28);
	        panel.add(update);
	        
	        Delete = new JButton("Supprimer");
	        Delete.setForeground(Color.BLACK);
	        Delete.setBackground(UIManager.getColor("Button.background"));
	        Delete.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(id!=0) {
	        			if(JOptionPane.showConfirmDialog(null, "voulez vous vraimant supprimer la categorie ?") ==0) {
	        				String cat = categorie.getText();
			               
	        				if (cai.delete(new CategorieModel(id, cat, null)))
	    					{
	    						JOptionPane.showMessageDialog(null, "succes", "echec",
	    							JOptionPane.INFORMATION_MESSAGE);
	    						annuler();
	    					}

				                    addProduit.comb();
			                        
		                    
	    				}
	        		}
	        		else {
                        JOptionPane.showMessageDialog(null, "selectionner une categorie dans le tableau");
	        		}

	        	}
	        });
	        Delete.setBounds(404, 35, 101, 26);
	        panel.add(Delete);
	        
	        recherche = new JTextField();
	        recherche.setBackground(Color.WHITE);
	        recherche.setBounds(10, 87, 235, 20);
	        panel.add(recherche);
	        
	        JButton btnRecherche = new JButton("Recherche");
	        btnRecherche.addActionListener(new ActionListener() {
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
	        	            ResultSet res = st.executeQuery("select * from categorie where libelle like '%"+r+"%' ");
	        	            ResultSetMetaData rsm = res.getMetaData();
	        	            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
	        	            model_1.setRowCount(0);
	        	            int cols = rsm.getColumnCount();
	        	            String[] colName = new String[cols];
	        	            colName[0] = "id";
	        	            colName[1] = "libelle";
	        	            colName[2] = "date";
	        	            model_1.setColumnIdentifiers(colName);
	        	            model_1.setColumnIdentifiers(colName);
	        	            String num, libelle, dat;
	        	            while (res.next()) {
	        	                num = res.getString(1);
	        	                libelle = res.getString(2);
	        	                dat = res.getString(3);
	        	                String[] row = {num, libelle, dat};
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
	        btnRecherche.setForeground(Color.BLACK);
	        btnRecherche.setBackground(UIManager.getColor("Button.background"));
	        btnRecherche.setBounds(265, 85, 115, 25);
	        panel.add(btnRecherche);
	        
	        JButton btnAnnuler = new JButton("Annuler");
	        btnAnnuler.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		annuler();
	        	}
	        });
	        btnAnnuler.setForeground(Color.BLACK);
	        btnAnnuler.setBackground(UIManager.getColor("Button.background"));
	        btnAnnuler.setBounds(404, 85, 115, 25);
	        panel.add(btnAnnuler);

	        table_1 = new JTable();
	        table_1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {

	            	try {
	            	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
	            	Statement st = cnx.createStatement();
	            	ResultSet res = st.executeQuery("select * from categorie where id="+id);
	            	if(res.next()) {
	            		categorie.setText(res.getString(2));
	            		System.out.println(res.getString(2));
		            	}
	            	res.close();
	            	st.close();
	            	}
	            	catch(Exception e1){
	            	System.out.println("erreur"+e1.getMessage());

	            	}

	            }
	        });

	        JScrollPane scrollPane = new JScrollPane(table_1);
	        scrollPane.setBounds(22, 136, 664, 341);
	        JViewport viewport = scrollPane.getViewport();

	        add(scrollPane);
	        table_1.setBackground(Color.WHITE);
	        charger();
	      
	    }
	    

		public void charger() {
	        try {
	           
	            Statement st = cnx.createStatement();
	            ResultSet res = st.executeQuery("select * from categorie");
	            ResultSetMetaData rsm = res.getMetaData();
	            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
	            model_1.setRowCount(0);
	            int cols = rsm.getColumnCount();
	            String[] colName = new String[cols];
	            colName[0] = "id";
	            colName[1] = "libelle";
	            colName[2] = "date";
	            model_1.setColumnIdentifiers(colName);
	            String num, libelle, dat;
	            while (res.next()) {
	                num = res.getString(1);
	                libelle = res.getString(2);
	                dat = res.getString(3);
	                String[] row = {num, libelle, dat};
	                model_1.addRow(row);
	            }
	            st.close();

	        } catch (Exception e) {
	            System.out.println("erreur" + e.getMessage());
	        }
	    }
	    
	    public void annuler() {
	    	recherche.setText("");
	    	categorie.setText("");
	    	id = 0;
	    	charger();
	    }
}













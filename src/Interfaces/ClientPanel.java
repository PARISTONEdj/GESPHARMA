package Interfaces;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import Dao.ClientImp;
import Dao.EmployerImp;
import Models.ClientModel;
import Models.EmployerModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ClientPanel extends JPanel {
	private JTextField nom;
	private JTextField adresse;
	private JTextField prenom;
	private JTextField telephone;
	private JTextField recherche;
	private JTable table_1, table_2;
	private  JScrollPane scrollPane;
	private int id=0;
	PreparedStatement statement = null;



	/**
	 * Create the panel.
	 */
	public ClientPanel(VentePanel ventePanel) {
		setBackground(SystemColor.activeCaption);
        setSize(714, 566);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(23, 11, 681, 162);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("CLIENTELLE");
        lblNewLabel.setBounds(10, 0, 100, 14);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nom");
        lblNewLabel_1.setBounds(10, 25, 46, 14);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Prenom");
        lblNewLabel_1_1.setBounds(269, 25, 46, 14);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Adresse");
        lblNewLabel_1_2.setBounds(10, 61, 61, 14);
        panel.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("Telephone");
        lblNewLabel_1_3.setBounds(269, 61, 65, 14);
        panel.add(lblNewLabel_1_3);
        
		ClientImp ci = new ClientImp();

        
        nom = new JTextField();
        nom.setBounds(81, 25, 178, 20);
        panel.add(nom);
        
        adresse = new JTextField();
        adresse.setBounds(81, 58, 178, 20);
        panel.add(adresse);
        
        prenom = new JTextField();
        prenom.setBounds(330, 22, 178, 20);
        panel.add(prenom);
        
        telephone = new JTextField();
        telephone.setBounds(330, 58, 178, 20);
        panel.add(telephone);
        
        JButton btnNewButton = new JButton("Mise a jour");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(id!=0) {
        			String n = nom.getText();
	           		 String p = prenom.getText();
	           		 String a = adresse.getText();
	           		 String t = telephone.getText();
	                	
	                	if (ci.update(new ClientModel(n, p, a, t, id)))
    					{
    						JOptionPane.showMessageDialog(null,
    							JOptionPane.INFORMATION_MESSAGE);
    						annuler();
	                        id=0;
	                        ventePanel.comb();
    					}

                    charger();
        			
        		}
        		else {
                    JOptionPane.showMessageDialog(null, "selectionner un client dans le tableau");
        		}
        	}
        });
        btnNewButton.setBounds(551, 21, 120, 23);
        panel.add(btnNewButton);
        
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(id!=0) {
        			if(JOptionPane.showConfirmDialog(null, "voulez vous vraimant supprimer le client ?") ==0) {
		                        annuler();
		                        id = 0;
		                        ventePanel.comb();
		                        if (ci.delete(new ClientModel(id)))
		    					{
		    						JOptionPane.showMessageDialog(null, "succes", "echec",
		    							JOptionPane.INFORMATION_MESSAGE);
		    						annuler();
			                        id=0;
			                        ventePanel.comb();
		    					}

		                    charger();
	                       				}
        		}
        		else {
                    JOptionPane.showMessageDialog(null, "selectionner un client dans le tableau");
        		}
        	}
        });
        btnSupprimer.setBounds(551, 57, 120, 23);
        panel.add(btnSupprimer);
        
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String n = nom.getText();
        		 String p = prenom.getText();
        		 String a = adresse.getText();
        		 String t = telephone.getText();

	                if (n.equals("") || p.equals("") || a.equals("")|| t.equals("")) {
	                    JOptionPane.showMessageDialog(null, "Renseigner tous les champs");
	                } 
	                else {
	                	
	                	if (ci.inserer(new ClientModel(n, p, a, t, id)))
    					{
    						JOptionPane.showMessageDialog(null, "succes", "echec",
    							JOptionPane.INFORMATION_MESSAGE);
    						annuler();
	                        ventePanel.comb();
    					}

	                	charger();
	                    ventePanel.comb();


	                   
	                    
	                }
        	}
        });
        btnAjouter.setBounds(134, 89, 89, 23);
        panel.add(btnAjouter);
        
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		annuler();
        	}
        });
        btnAnnuler.setBounds(340, 89, 89, 23);
        panel.add(btnAnnuler);
        
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
        	            ResultSet res = st.executeQuery("select * from client where nom like '%"+r+"%' or prenom like '%"+r+"%' ");
        	            ResultSetMetaData rsm = res.getMetaData();
        	            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
        	            model_1.setRowCount(0);
        	            int cols = rsm.getColumnCount();
        	            String[] colName = new String[cols];
        	            colName[0] = "id";
        	            colName[1] = "nom";
        	            colName[2] = "prenom";
        	            colName[3] = "Adresse";
        	            colName[4] = "Telephone";
        	            colName[5] = "Date";
        	            model_1.setColumnIdentifiers(colName);
        	            String num, nom, prenom, adresse, telephone, dat;
        	            while (res.next()) {
        	                num = res.getString(1);
        	                nom = res.getString(2);
        	                prenom = res.getString(3);
        	                adresse = res.getString(4);
        	                telephone = res.getString(5);
        	                dat = res.getString(6);
        	                String[] row = {num, nom, prenom, adresse, telephone, dat};
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
        
        btnRecherche.setBounds(500, 129, 110, 23);
        panel.add(btnRecherche);
        
        recherche = new JTextField();
        recherche.setBounds(306, 130, 178, 20);
        panel.add(recherche);
        
        JLabel lblListeDesClients = new JLabel("LISTE DES CLIENTS");
        lblListeDesClients.setBounds(0, 133, 140, 14);
        panel.add(lblListeDesClients);
		 table_1 = new JTable();
		 table_1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	try {
	            	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
	            	Class.forName("com.mysql.cj.jdbc.Driver");
	            	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	            	Statement st = con.createStatement();
	            	ResultSet res = st.executeQuery("select * from client where id="+id);
	            	if(res.next()) {
	            		nom.setText(res.getString(2));
	            		prenom.setText(res.getString(3));
	            		adresse.setText(res.getString(4));
	            		telephone.setText(res.getString(5));
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
        scrollPane.setBounds(23, 184, 681, 358);
        JViewport viewport = scrollPane.getViewport();
        add(scrollPane);
        
        charger();
	}
	
	public void charger() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select * from client");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "nom";
            colName[2] = "prenom";
            colName[3] = "Adresse";
            colName[4] = "Telephone";
            colName[5] = "Date";
            model_1.setColumnIdentifiers(colName);
            String num, nom, prenom, adresse, telephone, dat;
            while (res.next()) {
                num = res.getString(1);
                nom = res.getString(2);
                prenom = res.getString(3);
                adresse = res.getString(4);
                telephone = res.getString(5);
                dat = res.getString(6);
                String[] row = {num, nom, prenom, adresse, telephone, dat};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
    
    public void annuler() {
    	recherche.setText("");
    	nom.setText("");
    	adresse.setText("");
    	telephone.setText("");
    	adresse.setText("");
    	prenom.setText("");
    	charger();
    }
}

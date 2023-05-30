package Interfaces;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;


import Connection.Connecteur;
import Dao.LigneVenteImp;
import Dao.ProduitImp;
import Dao.VenteImp;
import Models.LigneVente;
import Models.ProduitModel;
import Models.VenteModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class VentePanel extends JPanel {
	private JTable table_1, table_2;
	private  JScrollPane scrollPane, scrollpane2;
	private int id;
	PreparedStatement statement = null;
	PreparedStatement statement1 = null;
	 Connection cnx = Connecteur.Connect();


    private JComboBox  categori;
    private JLabel lblNewLabel_1;
    private JPanel panelproduit;
    private JLabel lblNewLabel_2;
    private JTextField recherche;
    private JTextField produit;
    private JTextField qte;
    private JTextField quantite;
    private JTextField nom;
    private JTextField prenom;
    private JTextField telephone;
    private JTextField totalht;
    private JTextField totalttc;
    private JTextField prix;
    private JTextField produitu;
    private JTextField quantiteu;
    private JLabel datev, prod, quant;
    private JLabel nome;
    private JLabel prenome;
    private JPanel ligne;
    private  JButton neuf, imp, ret;


	
	public VentePanel() {
		setBackground(SystemColor.activeCaption);
        setSize(714, 566);
        setLayout(null);
		VenteImp vi = new VenteImp();
		
		LigneVenteImp li = new LigneVenteImp();
		
		ProduitImp pi = new ProduitImp();

        JPanel panel = new JPanel();
        panel.setBounds(21, 11, 683, 61);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nouvelle vente");
        lblNewLabel.setBounds(0, 0, 131, 14);
        panel.add(lblNewLabel);
        
        
        categori = new JComboBox();
		categori.setBounds(176, 22, 209, 20);
		comb();
		
		panel.add(categori);
		
		lblNewLabel_1 = new JLabel("Selectionner le client");
		lblNewLabel_1.setBounds(10, 25, 156, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Panier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String cat = (String) categori.getSelectedItem();
	                if (cat.equals("SELECT")) {
	                    JOptionPane.showMessageDialog(null, "Selectionner un client");
	                }else {
	                    try {

	                        Class.forName("com.mysql.cj.jdbc.Driver");
	                        Connection con = DriverManager.getConnection(
	                                "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	                        PreparedStatement stmt = con.prepareStatement("SELECT id from client WHERE nom=?");
	                        stmt.setString(1, cat);
	                        ResultSet rs = stmt.executeQuery();
	                        if (rs.next()) {
	    			            int i = rs.getInt("id");
	                        	int iduser =  Dao.UserSession.getUserId();
	    			            
	    			            if (vi.inserer(new VenteModel(i, iduser)))
		      					{
		      						JOptionPane.showMessageDialog(null, "succes", "echec",
		      							JOptionPane.INFORMATION_MESSAGE);
		      						charger();
			                        panelproduit.setVisible(true);
			                        ligne.setVisible(true);

		      					}
		                        


	                        }

	                    } catch (Exception e1) {
	                        System.out.println("erreur" + e1.getMessage());
	                    }
	                   
	                }
			}
		});
		btnNewButton.setBounds(423, 21, 89, 23);
		panel.add(btnNewButton);
		
		panelproduit = new JPanel();
		panelproduit.setBounds(21, 83, 683, 153);
		add(panelproduit);
		panelproduit.setLayout(null);
		panelproduit.setVisible(false);
		
		lblNewLabel_2 = new JLabel("Ajouter des produits aux panier");
		lblNewLabel_2.setBounds(0, 0, 192, 14);
		panelproduit.add(lblNewLabel_2);
		
		 table_1 = new JTable();
		 
		 table_1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {

	            	try {
	            	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
	            	Statement st = cnx.createStatement();
	            	ResultSet res = st.executeQuery("select id, nom, qte, prix from produit where id="+id);
	            	if(res.next()) {
	            		produit.setText(res.getString(2));
	            		qte.setText(res.getString(3));
	            		prix.setText(res.getString(4));
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
	        scrollPane.setBounds(10, 51, 382, 91);
	        JViewport viewport = scrollPane.getViewport();
	        panelproduit.add(scrollPane);
	        
	        recherche = new JTextField();
	        recherche.setBounds(10, 20, 152, 20);
	        panelproduit.add(recherche);
	        
	        JButton btnNewButton_1 = new JButton("Recherche");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String r = recherche.getText();
	        		if(r.equals("")) {
	            		JOptionPane.showMessageDialog(null, "remplir champs");
	        		}
	        		else {
	        			try {
	        	            Statement st = cnx.createStatement();
	        	            ResultSet res = st.executeQuery("select id, nom, qte from produit where nom like '%"+r+"%' and qte>0");
	        	            ResultSetMetaData rsm = res.getMetaData();
	        	            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
	        	            model_1.setRowCount(0);
	        	            int cols = rsm.getColumnCount();
	        	            String[] colName = new String[cols];
	        	            colName[0] = "id";
	        	            colName[1] = "nom";
	        	            colName[2] = "Stock";
	        	            model_1.setColumnIdentifiers(colName);
	        	            String num, nom, stock;
	        	            while (res.next()) {
	        	                num = res.getString(1);
	        	                nom = res.getString(2);
	        	                stock = res.getString(3);
	        	                String[] row = {num, nom, stock};
	        	                model_1.addRow(row);
	        	            }
	        	            st.close();

	        	        } catch (Exception e1) {
	        	            System.out.println("erreur" + e1.getMessage());
	        	        }
	        		}
	        	}
	        });
	        btnNewButton_1.setBounds(175, 19, 106, 23);
	        panelproduit.add(btnNewButton_1);
	        
	        JLabel lblNewLabel_3 = new JLabel("Produit");
	        lblNewLabel_3.setBounds(445, 11, 62, 14);
	        panelproduit.add(lblNewLabel_3);
	        
	        produit = new JTextField();
	        produit.setEditable(false);
	        produit.setBounds(517, 8, 152, 20);
	        panelproduit.add(produit);
	        
	        qte = new JTextField();
	        qte.setEditable(false);
	        qte.setBounds(517, 64, 152, 20);
	        panelproduit.add(qte);
	        
	        quantite = new JTextField();
	        quantite.setBounds(517, 88, 152, 20);
	        panelproduit.add(quantite);
	        
	        JLabel lblNewLabel_3_1 = new JLabel("Stock");
	        lblNewLabel_3_1.setBounds(445, 67, 62, 14);
	        panelproduit.add(lblNewLabel_3_1);
	        
	        JLabel lblNewLabel_3_2 = new JLabel("Quantite");
	        lblNewLabel_3_2.setBounds(445, 94, 62, 14);
	        panelproduit.add(lblNewLabel_3_2);
	        
	        JButton btnNewButton_2 = new JButton("Ajouter");
	        btnNewButton_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 String nom = produit.getText();
	        		 String q = qte.getText();
	        		 String quant = quantite.getText();

		                if (nom.equals("")||q.equals("")||quant.equals("")) {
		                    JOptionPane.showMessageDialog(null, "Selectionner un produit dans le tableau et renseigner la quantiter");
		                } else {
		                	double q1 = Float.parseFloat(quant);
		                	float s1 = Float.parseFloat(q);
		                	
		                    try {
		                        
		                        PreparedStatement stmt = cnx.prepareStatement("SELECT id from produit WHERE nom=?");
		                        stmt.setString(1, nom);
		                        ResultSet rs = stmt.executeQuery();
		                        if (rs.next()) {
		    			            int i = rs.getInt("id");
		    			            if(q1<s1) {
		    			            	
		    			            	 if (li.inserer(new LigneVente(q1, i, max())))
		 		      					{
		 		      						JOptionPane.showMessageDialog(null, "succes", "echec",
		 		      							JOptionPane.INFORMATION_MESSAGE);
		 		      						
		 		      						pi.reduirquantite(new ProduitModel(q1, i));
		 		      						
		 		      						charger();
		 		      						chargerligne();
		 		      						chargerclient();
		 		      						chargeremployer();
		 		      						total();

		 		      					}            

		    			            }
		    			            else {
				                        JOptionPane.showMessageDialog(null, "La quantiter est superieur au stock");
		    			            }
		                        }

		                    } catch (Exception e1) {
		                        System.out.println("erreur" + e1.getMessage());
		                    }

		                }
	        	}
	        });
	        btnNewButton_2.setBounds(477, 119, 89, 23);
	        panelproduit.add(btnNewButton_2);
	        
	        JButton btnNewButton_2_1 = new JButton("Annuler");
	        btnNewButton_2_1.setBounds(580, 119, 89, 23);
	        panelproduit.add(btnNewButton_2_1);
	        
	        prix = new JTextField();
	        prix.setEditable(false);
	        prix.setBounds(517, 33, 152, 20);
	        panelproduit.add(prix);
	        
	        JLabel lblNewLabel_3_3 = new JLabel("Prix");
	        lblNewLabel_3_3.setBounds(445, 36, 62, 14);
	        panelproduit.add(lblNewLabel_3_3);
	        
	        ligne = new JPanel();
	        ligne.setBounds(21, 247, 683, 308);
	        add(ligne);
	        ligne.setLayout(null);
	        ligne.setVisible(false);
	        
	        JLabel lblNewLabel_4 = new JLabel("Panier du Client");
	        lblNewLabel_4.setBounds(10, 11, 125, 21);
	        ligne.add(lblNewLabel_4);
	        
	        nom = new JTextField();
	        nom.setEditable(false);
	        nom.setBounds(133, 11, 136, 20);
	        ligne.add(nom);
	        
	        prenom = new JTextField();
	        prenom.setEditable(false);
	        prenom.setBounds(279, 11, 136, 20);
	        ligne.add(prenom);
	        
	        telephone = new JTextField();
	        telephone.setEditable(false);
	        telephone.setBounds(460, 11, 110, 20);
	        ligne.add(telephone);
	        
	        JLabel lblNewLabel_4_1 = new JLabel("Tel:");
	        lblNewLabel_4_1.setBounds(418, 11, 43, 21);
	        ligne.add(lblNewLabel_4_1);
        
        
			 table_2 = new JTable();
			 table_2.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {

		            	try {
		            	id = Integer.parseInt(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
		            	Class.forName("com.mysql.cj.jdbc.Driver");
		            	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
		            	Statement st = con.createStatement();
		            	ResultSet res = st.executeQuery("select l.id, p.nom, p.prix, l.quantite from lignevente l inner join produit p on l.produit = P.id where l.vente="+max());
		            	if(res.next()) {
		            		produitu.setText(res.getString(2));
		            		quantiteu.setText(res.getString(4));
		            	}
		            	res.close();
		            	st.close();
		            	}
		            	catch(Exception e1){
		            	System.out.println("erreur"+e1.getMessage());

		            	}

		            }
		        });

	        scrollpane2 = new JScrollPane(table_2);
	        scrollpane2.setBounds(10, 43, 543, 176);
	        JViewport viewport2 = scrollpane2.getViewport();
	        ligne.add(scrollpane2);
	        
	        JLabel lblNewLabel_5 = new JLabel("TVA : 6%");
	        lblNewLabel_5.setBounds(20, 230, 78, 14);
	        ligne.add(lblNewLabel_5);
	        
	        JLabel lblNewLabel_5_1 = new JLabel("TOTAL HT ");
	        lblNewLabel_5_1.setBounds(78, 230, 82, 14);
	        ligne.add(lblNewLabel_5_1);
	        
	        totalht = new JTextField();
	        totalht.setEditable(false);
	        totalht.setBounds(138, 227, 125, 20);
	        ligne.add(totalht);
	        
	        totalttc = new JTextField();
	        totalttc.setForeground(Color.RED);
	        totalttc.setEditable(false);
	        totalttc.setBounds(370, 227, 136, 20);
	        ligne.add(totalttc);
	        
	        JLabel lblNewLabel_5_1_1 = new JLabel("TOTAL TTC");
	        lblNewLabel_5_1_1.setBounds(296, 230, 82, 14);
	        ligne.add(lblNewLabel_5_1_1);
	        
	        neuf = new JButton("Nouvelle vente");
	        neuf.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		panelproduit.setVisible(false);
	        		ligne.setVisible(false);
	        		categori.setSelectedItem("SELECT");
	        	}
	        });
	        neuf.setBounds(370, 258, 125, 39);
	        ligne.add(neuf);
	        
	        
	       imp = new JButton("Imprimer Facture");
	       /* btnNewButton_3_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		chargerligne();
	        		MessageFormat header = new MessageFormat("Facture du client : "+nom.getText()+" ,  "+prenom.getText());
	        		MessageFormat caissier = new MessageFormat("Total TTC: "+totalttc.getText());
	        		MessageFormat footer = new MessageFormat("page{0, number, integer}");
	        		try {
	        			table_2.print(JTable.PrintMode.NORMAL, header, footer);
	        		}
	        		catch(java.awt.print.PrinterException e1) {
	        			System.err.format("erreur d'impression", e1.getMessage()); 
	        		}
	        	}
	        });*/
	        
	        imp.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                chargerligne();
	                MessageFormat header = new MessageFormat("Facture du client : " + nom.getText() + ", " + prenom.getText());
	                MessageFormat footer = new MessageFormat("page {0, number, integer}");
	                
	                try {
	                    PrinterJob job = PrinterJob.getPrinterJob();
	                    job.setPrintable(new Printable() {
	                    	@Override
	                        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	                            if (pageIndex > 0) {
	                                return NO_SUCH_PAGE;
	                            }
	                            Graphics2D g2d = (Graphics2D) graphics;
	                            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	                            ligne.printAll(graphics); // Imprime le contenu du JPanel
	                            return PAGE_EXISTS;
	                        }
	                    });
	                    if (job.printDialog()) {
	                    	retire();
	                        job.print();
	                    }
	                } catch (PrinterException ex) {
	                    System.err.format("Erreur d'impression : %s", ex.getMessage());
	                }
	                ajouter();
	            }
	        });
	       
	       
	        
	        
	        imp.setBackground(Color.GREEN);
	        imp.setBounds(537, 258, 136, 39);
	        ligne.add(imp);
	        
	        produitu = new JTextField();
	        produitu.setEditable(false);
	        produitu.setBounds(570, 66, 103, 21);
	        ligne.add(produitu);
	        
	        prod = new JLabel("Produit");
	        prod.setBounds(563, 42, 62, 14);
	        ligne.add(prod);
	        
	        quant = new JLabel("Quantite");
	        quant.setBounds(563, 98, 62, 14);
	        ligne.add(quant);
	        
	        quantiteu = new JTextField();
	        quantiteu.setEditable(false);
	        quantiteu.setBounds(570, 123, 103, 21);
	        ligne.add(quantiteu);
	        
	        ret = new JButton("retirer");
	        ret.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(id!=0) {
	        			if(JOptionPane.showConfirmDialog(null, "voulez vous vraimant retirer le produit ?") ==0) {
			                try {
			                	
			                	
			                	 if (li.delete(new LigneVente(id)))
	 		      					{
	 		      						JOptionPane.showMessageDialog(null, "succes", "echec",
	 		      							JOptionPane.INFORMATION_MESSAGE);	
	 		      					}   
			                	 	String sql1 = "update produit set qte = qte + ? where nom = ?";
			                        statement1 = cnx.prepareStatement(sql1);
			                        Float p = Float.parseFloat(quantiteu.getText());
			                        statement1.setFloat(1, p);
			                        statement1.setString(2, produitu.getText());
			                        statement1.executeUpdate();
		      						charger();
		      						chargerligne();
		      						chargerclient();
		      						total();
			                        quantiteu.setText("");
			                        produitu.setText("");
			                        
			                        
		                    } catch (Exception e1) {
		                        System.out.println("erreur" + e1.getMessage());
		                    }
		                    charger();
	    				}
	        		}
	        		else {
                        JOptionPane.showMessageDialog(null, "selectionner une categorie dans le tableau");
	        		}
	        	}
	        });
	        ret.setBounds(580, 165, 93, 29);
	        ligne.add(ret);
	        
	        JLabel lblNewLabel_6 = new JLabel("Caissier");
	        lblNewLabel_6.setBounds(10, 255, 62, 14);
	        ligne.add(lblNewLabel_6);
	        
	        JLabel lblNewLabel_6_1 = new JLabel("Date");
	        lblNewLabel_6_1.setBounds(10, 283, 62, 14);
	        ligne.add(lblNewLabel_6_1);
	        
	        nome = new JLabel("");
	        nome.setBounds(66, 255, 100, 14);
	        ligne.add(nome);
	        
	        prenome = new JLabel("");
	        prenome.setBounds(161, 255, 103, 14);
	        ligne.add(prenome);
	        
	        datev = new JLabel("");
	        datev.setBounds(44, 280, 103, 17);
	        ligne.add(datev);
	        
	        

	}
	
	public void charger() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select id, nom, qte from produit where qte>0");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "nom";
            colName[2] = "Stock";
            model_1.setColumnIdentifiers(colName);
            String num, nom, stock;
            while (res.next()) {
                num = res.getString(1);
                nom = res.getString(2);
                stock = res.getString(3);
                String[] row = {num, nom, stock};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
    
    public void comb() {
		try {   
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            String sql="Select nom FROM client";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            categori.removeAllItems();
            categori.addItem("SELECT");            
            while (rs.next()) {
            String name = rs.getString("nom");
            categori.addItem(name);            
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
	}
    
    public int max() throws SQLException {
    	int max = 0;
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	        PreparedStatement stmt = con.prepareStatement("SELECT MAX(id) as max from vente");
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
    
    public void chargerligne() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select l.id, p.nom, p.prix, l.quantite from lignevente l inner join produit p on l.produit = P.id where l.vente="+max());
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_2.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols+1];
            colName[0] = "id";
            colName[1] = "produit";
            colName[2] = "Prix";
            colName[3] = "Quantite";
            colName[4] = "Totale HT";
            model_1.setColumnIdentifiers(colName);
            String num, nom, prix, stock;
            while (res.next()) {
                num = res.getString(1);
                nom = res.getString(2);
                prix = res.getString(3);
                stock = res.getString(4);
                float t = Float.parseFloat(res.getString(3)) * Float.parseFloat(res.getString(4));
                String t1 = Float.toString(t);
                String[] row = {num, nom, prix, stock, t1};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
    
    public void chargerclient() throws SQLException {
    	try {   
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            String sql="Select client FROM vente where id ='"+max()+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            int name = rs.getInt("client");
           // JOptionPane.showMessageDialog(null, name);
            String sql1="Select nom, prenom, adresse, telephone FROM client where id ='"+name+"'";
            PreparedStatement pst=con.prepareStatement(sql1);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                telephone.setText(rst.getString("telephone"));
                nom.setText(rst.getString("nom"));
                prenom.setText(rst.getString("prenom"));
                }
        }
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void total() throws SQLException {
    	try {   
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            String sql="select SUM(p.prix  * quantite) as total  from lignevente l inner join produit p on l.produit = p.id where vente='"+max()+"'";
            		
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            float name = rs.getFloat("total");
            float t = (float) (name + (name * 0.06));
            totalht.setText(Float.toString(name));
            totalttc.setText(Float.toString(t));
           
        }
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void annuler() {
    	charger();
    	recherche.setText("");
    	produit.setText("");
    	prix.setText("");
    	quantite.setText("");
    	qte.setText("");

    }
    
    public void chargeremployer() throws SQLException {
    	try {   
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            String sql="Select employer, datevente FROM vente where id ='"+max()+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            int name = rs.getInt("employer");
            datev.setText(rs.getString("datevente"));
            //JOptionPane.showMessageDialog(null, name);
            String sql1="Select nom, prenom FROM employer where id ='"+name+"'";
            PreparedStatement pst=con.prepareStatement(sql1);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                nome.setText(rst.getString("nom"));
                prenome.setText(rst.getString("prenom"));
                }
        }
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    
   public void retire() {
	   ligne.setBackground(Color.white);
	   quantiteu.setVisible(false);
	   produitu.setVisible(false);
	  imp.setVisible(false);
	  neuf.setVisible(false);
	  prod.setVisible(false);
	  quant.setVisible(false);
	  ret.setVisible(false);

   }
   
   public void ajouter() {
	   ligne.setBackground(null);
	   quantiteu.setVisible(true);
	   produitu.setVisible(true);
	  imp.setVisible(true);
	  neuf.setVisible(true);
	  prod.setVisible(true);
	  quant.setVisible(true);
	  ret.setVisible(true);

   }
}














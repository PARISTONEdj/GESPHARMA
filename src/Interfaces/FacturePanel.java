package Interfaces;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacturePanel extends JPanel {
	private JTable table_1, table_2;
	private  JScrollPane scrollPane, scrollPane2;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private int id=0;
	private JLabel nom;
	private JLabel prenom;
	private JLabel telephone;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel date;
	private JLabel lblNewLabel_4;
	private JLabel employer;


	/**
	 * Create the panel.
	 */
	public FacturePanel() {
		setBackground(SystemColor.activeCaption);
        setSize(714, 566);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(30, 11, 657, 233);
        add(panel);
        
        table_1 = new JTable();
		 table_1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	try {
	            	id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
	            	Class.forName("com.mysql.cj.jdbc.Driver");
	            	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	            	Statement st = con.createStatement();
	            	ResultSet res = st.executeQuery("select v.id, c.nom, e.nom, v.datevente, v.client, v.datevente  from vente v inner join client c inner join employer e on v.client = c.id and v.employer = e.id ");
	            	if(res.next()) {
                        JOptionPane.showMessageDialog(null, res.getString(1));
                        int a = Integer.parseInt(res.getString(5));
                        chargerclient(a);
	            		charg(id);
	            		date.setText(res.getString(6));
	            		employer.setText(res.getString(3));
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
       scrollPane.setBounds(25, 30, 560, 126);
       JViewport viewport = scrollPane.getViewport();
       panel.setLayout(null);
       panel.add(scrollPane);
       
       lblNewLabel = new JLabel("Liste des ventes");
       lblNewLabel.setBounds(10, 0, 96, 14);
       panel.add(lblNewLabel);
       
       panel_1 = new JPanel();
       panel_1.setBounds(30, 267, 657, 266);
       add(panel_1);
       panel_1.setLayout(null);
       
       JButton btnNewButton = new JButton("Imprimer");
     
       btnNewButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        PrinterJob job = PrinterJob.getPrinterJob();
    	        MessageFormat header = new MessageFormat("Facture de : "+nom.getText()+" "+prenom.getText()+" tel: "+telephone.getText()+" Caissier: "+employer.getText()+" Date :"+date.getText());
        		MessageFormat footer = new MessageFormat("page{0, number, integer}");
    	        job.setPrintable(table_2.getPrintable(JTable.PrintMode.FIT_WIDTH, header, footer));

    	        if (job.printDialog()) {
    	            try {
    	                job.print();
    	            } catch (PrinterException ex) {
    	                ex.printStackTrace();
    	            }
    	        }
    	    }
    	});
       
     

       btnNewButton.setBounds(10, 11, 89, 23);
       panel_1.add(btnNewButton);
       
       table_2 = new JTable();
       
       scrollPane2 = new JScrollPane(table_2);
       scrollPane2.setBounds(25, 45, 593, 176);
       JViewport viewport1 = scrollPane2.getViewport();
       panel_1.add(scrollPane2);
       
       nom = new JLabel("");
       nom.setBounds(234, 11, 77, 14);
       panel_1.add(nom);
       
       prenom = new JLabel("");
       prenom.setBounds(335, 11, 77, 14);
       panel_1.add(prenom);
       
       telephone = new JLabel("");
       telephone.setBounds(513, 11, 77, 14);
       panel_1.add(telephone);
       
       lblNewLabel_1 = new JLabel("Nom du client");
       lblNewLabel_1.setBounds(109, 15, 129, 14);
       panel_1.add(lblNewLabel_1);
       
       lblNewLabel_2 = new JLabel("Telephone");
       lblNewLabel_2.setBounds(444, 15, 83, 14);
       panel_1.add(lblNewLabel_2);
       
       lblNewLabel_3 = new JLabel("Date");
       lblNewLabel_3.setBounds(93, 232, 69, 14);
       panel_1.add(lblNewLabel_3);
       
       date = new JLabel("");
       date.setBounds(149, 232, 89, 14);
       panel_1.add(date);
       
       lblNewLabel_4 = new JLabel("Caissier");
       lblNewLabel_4.setBounds(383, 232, 77, 14);
       panel_1.add(lblNewLabel_4);
       
       employer = new JLabel("");
       employer.setBounds(473, 232, 83, 14);
       panel_1.add(employer);

       charger();
	}
	
	public void charger() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select v.id, c.nom, e.nom, v.datevente  from vente v inner join client c inner join employer e on v.client = c.id and v.employer = e.id ");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "client";
            colName[2] = "Caissier";
            colName[3] = "date vente";
      
            model_1.setColumnIdentifiers(colName);
            String num, nom, prenom, adresse;
            while (res.next()) {
                num = res.getString(1);
                nom = res.getString(2);
                prenom = res.getString(3);
                adresse = res.getString(4);
                
                String[] row = {num, nom, prenom, adresse};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
	
	public void charg(int i) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select p.nom, p.prix, l.quantite, l.vente from lignevente l inner join produit p on l.produit = p.id where l.vente ='"+i+"'");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_2.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols+1];
            colName[0] = "produit";
            colName[1] = "Prix";
            colName[2] = "quantite";
            colName[3] = "reference de vente";
            colName[4] = "Totale";


            model_1.setColumnIdentifiers(colName);
            String num, nom, prenom, adresse, ref;
            float totalHT = 0.0f; 
            float totalTVA = 0.0f;
            while (res.next()) {
                nom = res.getString(1);
                prenom = res.getString(2);
                adresse = res.getString(3);
                ref = res.getString(4);
                float prix = Float.parseFloat(res.getString(2));
                float quantite = Float.parseFloat(res.getString(3));
                float t = prix * quantite;
                totalHT += t; 
                float tva = t + (t * 0.06f); 
                totalTVA += tva;
                String t1 = Float.toString(t);
                String[] row = {nom, prenom, adresse, ref, t1};
                model_1.addRow(row);
            }
            String totalHTFormatted = String.format("%.2f", totalHT); 
            String totalTVAFormatted = String.format("%.2f", totalTVA); 
            String[] totalHTData = {"Total HT", "", "", "", totalHTFormatted}; 
            String[] tv = {"TVA", "", "", "", "6%"};
            String[] totalTVAData = {"Total TTC", "", "", "",  totalTVAFormatted};
            model_1.addRow(tv);
            model_1.addRow(totalHTData);
            model_1.addRow(totalTVAData);
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }
	
	 public void chargerclient(int max) throws SQLException {
	    	try {   
				Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	            String sql="Select client FROM vente where id ='"+max+"'";
	            PreparedStatement ps=con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            int name = rs.getInt("client");
	            JOptionPane.showMessageDialog(null, name);
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
}

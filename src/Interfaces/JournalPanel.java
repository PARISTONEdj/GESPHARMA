package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

public class JournalPanel extends JPanel {

	private JTable table_1;
	 private  JScrollPane scrollPane;

	public JournalPanel() {
		setBackground(SystemColor.activeCaption);
		setBounds(23, 61, 661, 494);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 11, 608, 43);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Journal de Connexion");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(232, 11, 223, 21);
		panel.add(lblNewLabel);
			table_1 = new JTable();
		 	scrollPane = new JScrollPane(table_1);
	        scrollPane.setBounds(25, 81, 608, 385);
	        JViewport viewport = scrollPane.getViewport();
	        //scrollPane.setVisible(false);
	        add(scrollPane);
	        table_1.setBackground(Color.WHITE);
	        charger();
	}
	
	public void charger() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select s.id, e.nom, s.datejour, s.heuredebut, s.heurefin from sessions s inner join employer e on s.employer = e.id order by s.id desc;\r\n"
            		+ "");
            ResultSetMetaData rsm = res.getMetaData();
            DefaultTableModel model_1 = (DefaultTableModel) table_1.getModel();
            model_1.setRowCount(0);
            int cols = rsm.getColumnCount();
            String[] colName = new String[cols];
            colName[0] = "id";
            colName[1] = "Employer";
            colName[2] = "date";
            colName[3] = "Heure debut";
            colName[4] = "Heure fin";
            model_1.setColumnIdentifiers(colName);
            String id, nom, debut, fin, dat;
            while (res.next()) {
            	 id = res.getString(1);
	                nom = res.getString(2);
	                dat = res.getString(3);
	                debut = res.getString(4);
	                fin = res.getString(5);
	                if(fin == null) {
	                	fin ="En cours";
	                }
	                String[] row = {id, nom, dat, debut, fin};
                model_1.addRow(row);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }

}

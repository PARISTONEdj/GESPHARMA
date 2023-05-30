package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.SessionsImp;
import Dao.UserSession;
import Models.Sessions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class LoginUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField password;
    PreparedStatement statement = null;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUpdate frame = new LoginUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nom d'Utilisateur");
		lblNewLabel_2.setBounds(57, 92, 120, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de Passe");
		lblNewLabel_3.setBounds(57, 148, 98, 14);
		contentPane.add(lblNewLabel_3);
		
		login = new JTextField();
		login.setBounds(57, 117, 237, 20);
		contentPane.add(login);
		
		password = new JPasswordField();
		password.setBounds(57, 173, 237, 20);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login.getText().equals("") || password.getText().equals("")) {
		            JOptionPane.showMessageDialog(LoginUpdate.this, "Renseigner tous les champs");
				}
				else {
					String log = login.getText();
	                String pass = password.getText();
	                
	                try {
	                	
	                	 Class.forName("com.mysql.cj.jdbc.Driver");
	                        Connection con = DriverManager.getConnection(
	                                "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
	                        PreparedStatement stmt2 = con.prepareStatement("update employer set login = ?, mdp = ? where id=?");
	                        stmt2.setString(1, log);
	                        stmt2.setString(2, pass);
	                        stmt2.setInt(3, UserSession.getUserId());
	                        stmt2.executeUpdate();
	                        int i = Dao.UserSession.getUserId();
                        	SessionsImp si = new SessionsImp();
                        	 if (si.inserer(new Sessions(i)))
         					{	
                        		 max();
     	                        Dao.UserSession.setSessionId(+max());
	                        	Menu menu = new Menu();
			 					menu.setVisible(true);
			 					LoginUpdate.this.dispose();
		 					}
	                       
	                }
					 catch (Exception e1) {
	                    System.out.println("erreur" + e1.getMessage());
					 }
				}
			
			}
		});
		btnNewButton.setBounds(57, 204, 89, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(47, 11, 397, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHANGER VOS IDENTIFIANT DE CONNEXION ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(79, 45, 247, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenu sur PHARMA+");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(93, 11, 247, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\Downloads\\pharmacie.png"));
		lblNewLabel_4.setBounds(0, 0, 484, 261);
		contentPane.add(lblNewLabel_4);
	}
	
	 public int max() throws SQLException {
	    	int max = 0;
	    	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "ansufati10");
		        PreparedStatement stmt = con.prepareStatement("SELECT MAX(id) as max from sessions");
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
}

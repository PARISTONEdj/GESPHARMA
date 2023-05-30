package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import Connection.Connecteur;
import Dao.EmployerImp;
import Dao.SessionsImp;
import Models.EmployerModel;
import Models.Sessions;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField mdp;
    PreparedStatement statement = null;
	Connection cnx = Connecteur.Connect();



	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setSize(600, 400);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1,2));
		
        ImageIcon icon = new ImageIcon("path/to/your/image.png");
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(128, 255, 255));
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		panel2.setLayout(null);
		JButton btexit = new JButton(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\PHARMACIE\\src\\Image\\close (1).png"));
		btexit.setBackground(new Color(255, 255, 255));
		
		btexit.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dispose(); 
	            }
	        });
		
		btexit.setBounds(259, 0, 36, 25);
		panel2.add(btexit);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\images\\pharmacy (2).png"));
		btnNewButton.setBounds(29, 23, 244, 247);
		panel1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("PHARMA+");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(91, 301, 163, 35);
		panel1.add(lblNewLabel_1);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\PARISTONE\\Downloads\\logo.jpg");
		contentPane.add(panel2);
		
		JTextField login = new JTextField();
		login.setForeground(new Color(204, 204, 153));
		login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		login.setText("Nom d'utilisateur");
		login.setToolTipText("nom d'utilisateur");
		login.setBounds(22, 192, 218, 32);
		login.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.green));
		panel2.add(login);
		login.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(0, 153, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(22, 69, 109, 32);
		panel2.add(lblNewLabel);
		
		mdp = new JPasswordField();
		mdp.setToolTipText("nom d'utilisateur");
		mdp.setText("");
		mdp.setForeground(new Color(204, 204, 153));
		mdp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mdp.setColumns(10);
		mdp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.green));
		mdp.setBounds(22, 283, 218, 32);
		panel2.add(mdp);
		
		JButton btnNewButton_1 = new JButton("Connexion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EmployerImp ei = new EmployerImp();
				if(login.getText().equals("") || mdp.getText().equals("")) {
		            JOptionPane.showMessageDialog(Login.this, "Renseigner tous les champs");
				}
				else {
					String log = login.getText();
	                String pass = mdp.getText();
	                
	                if (ei.Connexion(new EmployerModel(log, pass)))
					{
						JOptionPane.showMessageDialog(null, "succes", "echec",
								JOptionPane.INFORMATION_MESSAGE);
						 PreparedStatement stmt2;
						try {
							stmt2 = cnx.prepareStatement("select id from employer where id not in (select employer from sessions) and id=?");
	                        stmt2.setInt(1, Dao.UserSession.getUserId());
	                        ResultSet rs2 = stmt2.executeQuery();
	                        if (rs2.next()) {
	                        	LoginUpdate lo = new LoginUpdate();
	                        	lo.setVisible(true);
	            				Login.this.dispose();
	                        }
	                        else {
	                        	int i = Dao.UserSession.getUserId();
	                        	SessionsImp si = new SessionsImp();
	                        	 if (si.inserer(new Sessions(i)))
	         					{	
	                        		 max();
	     	                        Dao.UserSession.setSessionId(+max());
		                        	Menu menu = new Menu();
				 					menu.setVisible(true);
				 					Login.this.dispose();
			 					}
	                        }

						} catch (SQLException e2) {
							e2.printStackTrace();
						}
	                        				 				
					}

					 
				}
				
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(new Color(0, 255, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(22, 341, 109, 32);
		panel2.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Annuler");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                dispose(); 
			}
		});
		btnNewButton_1_1.setForeground(Color.GREEN);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBackground(Color.WHITE);
		btnNewButton_1_1.setBounds(141, 341, 109, 32);
		panel2.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setBounds(22, 246, 169, 25);
		panel2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nom d'utilisateur");
		lblNewLabel_3.setBounds(22, 156, 129, 14);
		panel2.add(lblNewLabel_3);

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



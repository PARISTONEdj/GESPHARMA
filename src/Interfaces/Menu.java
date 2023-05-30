package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.Connecteur;

import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.awt.SystemColor;
import javax.swing.JLabel;

public class Menu extends JFrame {

	private JPanel contentPane;
	
	private HomePanel homePanel;
	
	
	private EmployerPanel employerPanel;
	
	private ProduitPanel produitPanel;
	
	private ClientPanel clientPanel;
	
	private FacturePanel facturePanel;
	
	private VentePanel ventePanel;
	
	private JButton btnAccueil;
	
	private JButton btnProduit;
	
	private JButton btnVente;
	
	private JButton btnClient;
	
	private JButton btnUtilisateur;
	
    PreparedStatement statement = null;
    private JButton btnFacture;
    
	Connection cnx = Connecteur.Connect();
	private JLabel lblNewLabel;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Menu() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		homePanel = new HomePanel();
		employerPanel = new EmployerPanel();
		produitPanel = new ProduitPanel();
		facturePanel = new FacturePanel();
		ventePanel = new VentePanel();

		clientPanel = new ClientPanel(ventePanel);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 277, 589);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(0, 0, 277, 117);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("PHARMA+");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\images\\p.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 257, 95);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(0, 117, 277, 462);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(6,1));
		
		btnAccueil = new JButton("ACCUEIL");
		btnAccueil.setBackground(Color.GRAY);
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked(homePanel);
				boutonClicked(btnAccueil);				
			}
		});
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\images\\home.png"));
		btnAccueil.setForeground(Color.GREEN);
		btnAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(btnAccueil);
		
		btnProduit = new JButton("PRODUITS");
		btnProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked(produitPanel);
				boutonClicked(btnProduit);
				refreshPanel(produitPanel);
			}
		});
		btnProduit.setBackground(Color.WHITE);
		btnProduit.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\images\\medicine.png"));
		btnProduit.setForeground(Color.GREEN);
		btnProduit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(btnProduit);
		
		if(Dao.UserSession.getRole()==1 || Dao.UserSession.getRole()==2 ) {
			panel_2.add(btnProduit);
		}
		
		btnVente = new JButton("VENTE");
		btnVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked(ventePanel);
				boutonClicked(btnVente);
			}
		});
		btnVente.setBackground(Color.WHITE);
		btnVente.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\images\\loan.png"));
		btnVente.setForeground(Color.GREEN);
		btnVente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		if(Dao.UserSession.getRole()==1 || Dao.UserSession.getRole()==3 ) {
			panel_2.add(btnVente);
		}
		
		btnUtilisateur = new JButton("UTILISATEUR");
		btnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked(employerPanel);

				boutonClicked(btnUtilisateur);
			}
		});
		
		btnFacture = new JButton("FACTURE");
		btnFacture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked(facturePanel);
				boutonClicked(btnFacture);

			}
		});
		btnFacture.setForeground(Color.GREEN);
		btnFacture.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFacture.setBackground(Color.WHITE);
		if(Dao.UserSession.getRole()==3 || Dao.UserSession.getRole()==1) {
			panel_2.add(btnFacture);
		}
		
		btnClient = new JButton("CLIENT");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked(clientPanel);
				boutonClicked(btnClient);

			}
		});
		btnClient.setBackground(Color.WHITE);
		btnClient.setForeground(Color.GREEN);
		btnClient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(btnClient);
		
		btnUtilisateur.setBackground(Color.WHITE);
		btnUtilisateur.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\GESPHARMA\\src\\Images\\user.png"));
		btnUtilisateur.setForeground(Color.GREEN);
		btnUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		if(Dao.UserSession.getRole()==1) {
			panel_2.add(btnUtilisateur);
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(276, 0, 745, 23);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\PARISTONE\\eclipse-workspace\\PHARMACIE\\src\\Image\\close (1).png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "voulez vous vraimant quitter ?") ==0) {
					try {
						
						String sql = "update sessions set heurefin = ? where id = ?";
	                    statement = cnx.prepareStatement(sql);
	                    statement.setTime(1, Time.valueOf(LocalTime.now()));
	                    statement.setInt(2, Dao.UserSession.getSessionId());
	                    statement.executeUpdate();
						Login login = new Login();
						login.setVisible(true);
						Menu.this.dispose();
					}
					catch(Exception e1) {
                        System.out.println("erreur" + e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(680, 0, 39, 23);
		panel_3.add(btnNewButton_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(0, 571, 1011, 45);
		contentPane.add(panel_3_1);
		panel_3_1.setBackground(Color.LIGHT_GRAY);
		
		JPanel PanelContent = new JPanel();
		PanelContent.setBackground(Color.WHITE);
		PanelContent.setBounds(276, 23, 714, 566);
		PanelContent.add(homePanel);
		PanelContent.add(produitPanel);
		PanelContent.add(employerPanel);
		PanelContent.add(clientPanel);
		PanelContent.add(ventePanel);
		PanelContent.add(facturePanel);
		menuClicked(homePanel);
		contentPane.add(PanelContent);
		PanelContent.setLayout(null);
		
		
	}
	
	public void menuClicked(JPanel panel) {
	    System.out.println("Menu clicked: showing panel " + panel.getName());
		homePanel.setVisible(false);
		employerPanel.setVisible(false);
		produitPanel.setVisible(false);
		clientPanel.setVisible(false);
		ventePanel.setVisible(false);
		facturePanel.setVisible(false);
		panel.setVisible(true);
	}

	public void boutonClicked(JButton bouton) {
		btnAccueil.setBackground(Color.white);
		
		btnProduit.setBackground(Color.white);
		
		
		btnVente.setBackground(Color.white);
		
		btnFacture.setBackground(Color.white);

		
		btnClient.setBackground(Color.white);
		
		 btnUtilisateur.setBackground(Color.white);
		 
		bouton.setBackground(Color.gray);
		bouton.setForeground(Color.green);
	}
	
	public void refreshPanel(JPanel panel) {
	    Component[] components = panel.getComponents();
	    for (Component component : components) {
	        component.repaint();
	    }
	    panel.revalidate();
	}
	

}

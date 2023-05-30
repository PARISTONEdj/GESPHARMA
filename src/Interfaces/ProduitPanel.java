package Interfaces;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ProduitPanel extends JPanel {
	private ListeProduit listeProduit;
	private AddProduit addProduit;
	private CategoriePanel categoriePanel;
	private RavitaillementPanel ravitaillementPanel;
	/**
	 * Create the panel.
	 */
	public ProduitPanel() {
		setBackground(SystemColor.activeCaption);
        setSize(714, 566);
        setName("Produit");
        setLayout(null);
        
        ravitaillementPanel = new RavitaillementPanel();
        ravitaillementPanel.setBounds(0, 0, 704, 488);
    	listeProduit = new ListeProduit(ravitaillementPanel);
    	listeProduit.setBounds(0, 0, 704, 488);
    	addProduit = new AddProduit(listeProduit, ravitaillementPanel);
        addProduit.setBounds(0, 0, 704, 488);
        categoriePanel = new CategoriePanel(addProduit);
        categoriePanel.setBounds(0, 0, 704, 488);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 704, 56);
        add(panel);
        panel.setLayout(new GridLayout(1, 4));
        
        JButton btnNewButton = new JButton("Liste des produits");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(listeProduit);

        	}
        });
        panel.add(btnNewButton);
        
        JButton btnNewButton_2 = new JButton("Ajouter produit");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(addProduit);
        	}
        });
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Categorie");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(categoriePanel);
        	}
        });
        panel.add(btnNewButton_3);
        
        JButton btnNewButton_1 = new JButton("Liste des ravitaillement");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(ravitaillementPanel);
        	}
        });
        panel.add(btnNewButton_1);
        
        JPanel produitcontent = new JPanel();
        produitcontent.setBounds(10, 67, 694, 488);
        produitcontent.add(addProduit);
        produitcontent.add(listeProduit);
        produitcontent.add(categoriePanel);
        produitcontent.add(ravitaillementPanel);
        add(produitcontent);
        produitcontent.setLayout(null);
        menuClicked(listeProduit);

	}
	public void menuClicked(JPanel panel) {
	    System.out.println("Menu clicked: showing panel " + panel.getName());
		addProduit.setVisible(false);
		listeProduit.setVisible(false);
		categoriePanel.setVisible(false);
		ravitaillementPanel.setVisible(false);
		//updateEmployer.setVisible(false);
		panel.setVisible(true);
	}
	
	
	
}

package Interfaces;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Interfaces.*;


public class EmployerPanel extends JPanel {

	private AddEmployerPanel addEmployerPanel;
	private ListeEmployer listeEmployer;
	private EmployerPanel employerPanel;
	private JournalPanel journalPanel;
    
    
	public EmployerPanel() {
		//this.menu = menu;
		setBackground(SystemColor.activeCaption);
        setSize(714, 566);
        setName("emp");
        setLayout(null);
        
        //updateEmployer.setBackground(Color.green);
        listeEmployer = new ListeEmployer(this);
        listeEmployer.setBounds(0, 0, 686, 494);
        addEmployerPanel = new AddEmployerPanel(listeEmployer);
       
        addEmployerPanel.setBounds(0, 0, 686, 494);
        journalPanel = new JournalPanel();
        journalPanel.setBounds(0, 0, 686, 494);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 674, 42);
        add(panel);
        panel.setLayout(new GridLayout(1,3));
        
        JButton liste = new JButton("Liste des employer");
        liste.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(listeEmployer);
        	}
        });
        panel.add(liste);
        
        JButton add = new JButton("Ajouter Employer");
        add.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(addEmployerPanel);
        	}
        });
        panel.add(add);
        
        JButton btnNewButton_2 = new JButton("journal de connexion");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuClicked(journalPanel);

        	}
        });
        panel.add(btnNewButton_2);
        
        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.GREEN);
        basePanel.setBounds(23, 61, 661, 494);
        add(basePanel);
        basePanel.setLayout(null);
        basePanel.add(listeEmployer);
        basePanel.add(addEmployerPanel);
        basePanel.add(journalPanel);
        menuClicked(listeEmployer);
       
	}
	
	public void menuClicked(JPanel panel) {
	    System.out.println("Menu clicked: showing panel " + panel.getName());
		listeEmployer.setVisible(false);
		addEmployerPanel.setVisible(false);
		journalPanel.setVisible(false);
		
			panel.setVisible(true);
		
	}
}

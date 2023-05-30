package Models;

import java.sql.Date;
import java.time.LocalDate;

public class EmployerModel {
	private int id;
	private String nom;
	private String prenom;
	private Date dateajout;
	private String email;
	private String login;
	private String mdp;
	private String roles;
	private String adresse;
	
	
	 public EmployerModel(int id, String nom, String prenom, String email, String adresse, String login, String mdp, Date dateajout, String roles) {
	        this.id = id;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.email = email;
	        this.adresse = adresse;
	        this.login = login;
	        this.mdp = mdp;
	        this.dateajout = dateajout;
	        this.roles = roles;
	    }


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public EmployerModel() {
		super();
	}


	public EmployerModel(String log, String pass) {
        this.login = log;
        this.mdp = pass;
        
	}
	


	public EmployerModel(String n, String p, String em, String a, String l, String m, String r) {
	        this.nom = n;
	        this.prenom = p;
	        this.email = em;
	        this.adresse = a;
	        this.login = l;
	        this.mdp = m;
	        this.roles = r;
	}


	public EmployerModel(int id2) {
		this.id = id2;
	}



	


	public EmployerModel(String text, String text2, String text3, String text4,  int id2) {
		this.nom = text;
		this.prenom = text2;
		this.email = text3;
		this.adresse = text4;
		this.id = id2;
	}


	

	public EmployerModel(String text, String text2, String text3, String text4, Object selectedItem, int id2) {
		this.nom = text;
		this.prenom = text2;
		this.email = text3;
		this.adresse = text4;
		this.id = id2;
		this.roles = (String) selectedItem;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateajout() {
		return dateajout;
	}


	public void setDate(Date dateajout) {
		this.dateajout = dateajout;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
	

}


package Models;

import java.util.Date;

public class VenteModel {
	private int id;
	private int client;
	private int employer;
	private Date datevente;
	
	public VenteModel(int id, int client, int employer, Date datevente) {
		super();
		this.id = id;
		this.client = client;
		this.employer = employer;
		this.datevente = datevente;
	}

	public VenteModel(int i, int iduser) {
		this.client = i;
		this.employer = iduser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public int getEmployer() {
		return employer;
	}

	public void setEmployer(int employer) {
		this.employer = employer;
	}

	public Date getDatevente() {
		return datevente;
	}

	public void setDatevente(Date datevente) {
		this.datevente = datevente;
	}
	
	
}


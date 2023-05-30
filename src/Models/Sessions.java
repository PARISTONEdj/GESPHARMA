package Models;

import java.util.Date;
import java.util.Timer;

public class Sessions {
	private int id;
	private Timer heuredebut;
	private Timer heurefin;
	private int employer;
	private Date datejour;
	public Sessions(int id, Timer heuredebut, Timer heurefin, int employer, Date datejour) {
		super();
		this.id = id;
		this.heuredebut = heuredebut;
		this.heurefin = heurefin;
		this.employer = employer;
		this.datejour = datejour;
	}
	public Sessions(int i) {
		this.employer = i;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timer getHeuredebut() {
		return heuredebut;
	}
	public void setHeuredebut(Timer heuredebut) {
		this.heuredebut = heuredebut;
	}
	public Timer getHeurefin() {
		return heurefin;
	}
	public void setHeurefin(Timer heurefin) {
		this.heurefin = heurefin;
	}
	public int getEmployer() {
		return employer;
	}
	public void setEmployer(int employer) {
		this.employer = employer;
	}
	public Date getDatejour() {
		return datejour;
	}
	public void setDatejour(Date datejour) {
		this.datejour = datejour;
	}
	
	
}


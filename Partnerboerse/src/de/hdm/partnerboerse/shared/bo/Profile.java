package de.hdm.partnerboerse.shared.bo;

import java.util.Date;

public class Profile extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private String firstName = "";
	
	private String lastName = "";
	
	private Date dateOfBirth = null;
	
	private String eMail = "";
	
	private int height = 0;
	
	private boolean smoker = false;
	
	private enum hairColor{
		braun,
		blond,
		schwarz,
		rot,
		grün,
		blau,
		orange,
		grau,
		lila,
		lilablassblau,
		kastanienrot,
		tabakrot,
		regenbogen,
		weiß,
		wasserstoffblond,
		himmelblau,
		glatze
	}
	
	private enum confession{
		katholisch,
		evangelisch,
		buddhismus,
		rastafari,
		islam,
		pastafari,
		hinduistisch,
		konfessionslos
	}
	
	private enum gender{
		weiblich,
		männlich,
		transgender
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}	
	
	public String toString() {
	    return super.toString() + this.firstName + " " + this.lastName + " " + this.eMail + " " + this.dateOfBirth + " " + this.smoker + " " + this.height;
	  }
}

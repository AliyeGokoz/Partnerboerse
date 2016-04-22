package de.hdm.partnerboerse.shared.bo;

public class Profile extends BusinessObject{
	
	private String hairColor; 
	private boolean smoker; 
	private String confession; 
	private String gender;
	
	/**
	   * Auslesen der Haarfarbe.
	   */
	public String getHairColor() {
		return hairColor;
	}
	
	/**
	   * Setzen der Haarfarbe.
	   */
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	
	/**
	   * Auslesen des Raucherverhaltens.
	   */
	public boolean isSmoker() {
		return smoker;
	}
	
	/**
	   * Setzen des Raucherverhaltens.
	   */
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	
	/**
	   * Auslesen der Religionszugehörigkeit.
	   */
	public String getConfession() {
		return confession;
	}
	
	/**
	   * Setzen der Religionszugehörigkeit.
	   */
	public void setConfession(String confession) {
		this.confession = confession;
	}
	
	/**
	   * Auslesen des Geschlechts.
	   */
	public String getGender() {
		return gender;
	}
	
	/**
	   * Setzen des Geschlechts.
	   */
	public void setGender(String gender) {
		this.gender = gender;
	} 
	
	

}

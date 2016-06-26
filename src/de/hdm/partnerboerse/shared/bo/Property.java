package de.hdm.partnerboerse.shared.bo;

public class Property extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private String propertyName = "";
	
	private String textualDescriptionForProfile = "";
	
	private String textualDescriptionForSearchProfile = "";
	
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getTextualDescriptionForProfile() {
		return textualDescriptionForProfile;
	}

	public void setTextualDescriptionForProfile(String textualDescriptionForProfile) {
		this.textualDescriptionForProfile = textualDescriptionForProfile;
	}
	
	public String getTextualDescriptionForSearchProfile() {
		return textualDescriptionForSearchProfile;
	}

	public void setTextualDescriptionForSearchProfile(String textualDescriptionForSearchProfile) {
		this.textualDescriptionForSearchProfile = textualDescriptionForSearchProfile;
	}

	public String toString() {
	    return super.toString() + " " + this.propertyName + " " + this.textualDescriptionForProfile + " " + this.textualDescriptionForSearchProfile;
	}   
}

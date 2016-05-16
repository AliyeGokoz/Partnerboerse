package de.hdm.partnerboerse.shared.bo;

public class Property extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private String propertyName = "";
	
	private String textualDescription = "";

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getTextualDescription() {
		return textualDescription;
	}

	public void setTextualDescription(String textualDescription) {
		this.textualDescription = textualDescription;
	}
	
	public String toString() {
	    return super.toString() + " " + this.propertyName + " " + this.textualDescription;
	  }
}

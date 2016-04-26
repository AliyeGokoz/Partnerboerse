package de.hdm.partnerboerse.shared.bo;

public class Info extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	private String informationValue = "";

	public String getInformationValue() {
		return informationValue;
	}

	public void setInformationValue(String informationValue) {
		this.informationValue = informationValue;
	}
	
	public String toString() {
	    return super.toString() + " " + this.informationValue;
	  }
}

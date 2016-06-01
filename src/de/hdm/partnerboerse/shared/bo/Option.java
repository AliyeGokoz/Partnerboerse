package de.hdm.partnerboerse.shared.bo;

public class Option extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private String option = "";

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String toString() {
		return super.toString() + " " + this.option;
	}

}

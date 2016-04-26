package de.hdm.partnerboerse.shared.bo;

public class Similarity extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private Profile fromProfile = null;
	
	private Profile toProfile = null;
	
	private double similarityValue = 0;

	public Profile getFromProfile() {
		return fromProfile;
	}

	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}

	public Profile getToProfile() {
		return toProfile;
	}

	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	public double getSimilarityValue() {
		return similarityValue;
	}

	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

	public String toString() {
	    return super.toString() + this.fromProfile + " " + this.toProfile + " " + this.similarityValue;
	  }

}

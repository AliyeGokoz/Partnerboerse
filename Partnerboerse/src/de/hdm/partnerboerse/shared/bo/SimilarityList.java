package de.hdm.partnerboerse.shared.bo;

public class SimilarityList extends BusinessObject{
	
	private Profile fromProfile;
	
	private Profile toProfile;
	
	private double similarityValue;

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
	
	
	/*
	//TODO compareProfiles
	public double compareProfiles(Profile fromProfile, Profile toProfile){
	
	}*/
	
	//TODO toString/SimilarityList
	public String toString() {
	    return super.toString() + " " + this.fromProfile + " " + this.toProfile;
	  }
	
}

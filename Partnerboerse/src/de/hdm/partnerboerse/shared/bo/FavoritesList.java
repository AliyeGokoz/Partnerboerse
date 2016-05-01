package de.hdm.partnerboerse.shared.bo;

public class FavoritesList extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private Profile fromProfile = null;
	
	private Profile toProfile = null;

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


	public String toString() {
	    return super.toString() + this.fromProfile + " " + this.toProfile;
	  }

}

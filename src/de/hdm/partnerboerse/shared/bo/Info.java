package de.hdm.partnerboerse.shared.bo;

public class Info extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String informationValue = "";

	private Selection selection;

	private Description description;

	private Profile profile;
	
	private SearchProfile searchProfile; 

	public String getInformationValue() {
		return informationValue;
	}

	public void setInformationValue(String informationValue) {
		this.informationValue = informationValue;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String toString() {
		return super.toString() + " " + this.informationValue;
	}

	public SearchProfile getSearchProfile() {
		return searchProfile;
	}

	public void setSearchProfile(SearchProfile searchProfile) {
		this.searchProfile = searchProfile;
	}
}

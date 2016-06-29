package de.hdm.partnerboerse.shared;

import java.io.Serializable;

import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Die Klasse LoginInfo beinhaltet alle Login Informationen eines Nutzers.
 * Dies beinhaltet den Login-Status, eine Login URL, eine Logout URL,
 * eine Email-Adresse sowie der Nickname des Nutzers.
 */
public class LoginInfo implements Serializable {
	
	/**
	  * 
	  */
	private static final long serialVersionUID = 1L;

	/**
	  * Aktueller Loginzustand
	  */
	private boolean loggedIn = false;
	
	/**
	  * Die Login-URL
	  */
	private String loginUrl;

	 /**
	  * Die Logout-URL
	  */
	private String logoutUrl;
	
	/**
	  * Die Email Adresse des eingeloggten Nutzers
	  */
	private String emailAddress;

	/**
	  * Der Nickname und das Profil des eingeloggten Nutzers
	  */
	private String nickname;
	
	private Profile profile;

	/**
	  * Auslesen des Login-Status
	  */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	  * Setzen des Login-Status
	  */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	 /**
	  * Auslesen der Login URL
	  */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	  * Setzen der Login URL
	  */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	  * Auslesen der Logout URL
	  */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	  * Setzen der Logout URL
	  */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	 /**
	  * Auslesen der Email-Adresse
	  */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	  * Setzen der Email-Adresse
	  */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	  * Auslesen des Nicknames des Nutzers
	  */
	public String getNickname() {
		return nickname;
	}
	
	/**
	  * Setzen des Nicknames des Nutzers
	  */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	  * Auslesen des Profils
	  */
	public Profile getProfile() {
		return profile;
	}
	
	/**
	  * Setzen des Nutzer-Profils
	  */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}

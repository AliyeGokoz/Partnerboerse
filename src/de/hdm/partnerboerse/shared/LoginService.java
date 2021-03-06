package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Synchrone Schnittstelle für die Klasse <code>LoginServiceImpl</code>.
 * 
 * 
 * @author Carolin Elsner
 */

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

	/**
	 * Ausführen des Logins und ablegen aller relevanten Nutzer Informationen in
	 * einem LoginInfo Objekt
	 * 
	 * @param requestUri
	 *            Die Basis URL der aufrufenden Seite (ermittelt über
	 *            GWT.getHostPageBaseURL())
	 * @return
	 */
	public LoginInfo login(String requestUri);

	/**
	 * Auslesen des aktuellen Profils
	 */
	Profile getCurrentProfile();

}
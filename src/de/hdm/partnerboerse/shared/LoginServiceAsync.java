package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Asynchrones Interface zum synchronen Interface {@link LoginService}. Dieses
 * Interface wird automatisch durch das Google Plugin erstellt und nur
 * clientseitig benoetigt.
 *
 */
public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	void getCurrentProfile(AsyncCallback<Profile> callback);

}

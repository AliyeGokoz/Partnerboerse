package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Profile;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	void getCurrentProfile(AsyncCallback<Profile> callback);

}

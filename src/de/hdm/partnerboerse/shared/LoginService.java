package de.hdm.partnerboerse.shared;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.Profile;


@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
 

 public LoginInfo login(String requestUri);

Profile getCurrentProfile();
 
}
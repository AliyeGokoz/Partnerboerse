package de.hdm.partnerboerse.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilePage extends VerticalPanel{

	@Override
	public void onLoad(){
		
		final VerticalPanel hbuttons = new VerticalPanel();
		final HorizontalPanel hcontent = new HorizontalPanel();
		final Button addProfile = new Button("Profil anlegen");
		final Button editProfile = new Button("Profil bearbeiten");
		final Button deleteProfile = new Button("Profil löschen");
		Grid profilGrid = new Grid(2, 3);
		
		hbuttons.setStyleName("hbuttons");
		hcontent.setStyleName("hcontent");
		
		addProfile.setStyleName("buttonwidth");
		editProfile.setStyleName("buttonwidth");
		deleteProfile.setStyleName("buttonwidth");
		
		hbuttons.add(addProfile);
		hbuttons.add(editProfile);
		hbuttons.add(deleteProfile);
		hcontent.add(profilGrid);
		
		RootPanel.get("Buttonzone").clear();
		RootPanel.get("Contentzone").clear();
  	  	RootPanel.get("Buttonzone").add(hbuttons);
  	  	RootPanel.get("Contentzone").add(hcontent);
  	  	
  	  	//TODO wenn bereits Profil erstellt dann anzeigen, wnnn nicht leere Seite mit Ausgabe: erstellen sich doch ein Profil
  	  	
	}
	
}

package de.hdm.partnerboerse.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.server.LoginServiceImpl;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Profile;

public class UserOverview extends VerticalPanel{
	
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	
	@Override
	public void onLoad() {
	final VerticalPanel seeAllUsers = new VerticalPanel();
	final FlexTable profileFlexTable  = new FlexTable();
	final ScrollPanel scrollPanel = new ScrollPanel(seeAllUsers);
	scrollPanel.setSize("500px", "480px");
	final DecoratorPanel decoratorPanel = new DecoratorPanel();
	decoratorPanel.add(scrollPanel);
	
	
	profileFlexTable.setText(0, 0, "Vorname");
	profileFlexTable.setText(0, 1, "Nachname");
	profileFlexTable.setText(0, 2, "Zur Merkliste");
	profileFlexTable.setText(0, 3, "Kontaktsperren");
	

	
	profileFlexTable.setCellPadding(6);

	seeAllUsers.add(profileFlexTable);
	
	RootPanel.get("Content").clear();
	RootPanel.get("Content").add(decoratorPanel);
	
	partnerboerseVerwaltung.getAllProfiles(new AsyncCallback<ArrayList<Profile>>(){

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(ArrayList<Profile> allprofilesresult) {
			int i = 1;
			for (final Profile p : allprofilesresult){
				profileFlexTable.setText(i, 0, p.getFirstName());
				profileFlexTable.setText(i, 1, p.getLastName());
				final Button addtoFav = new Button("+");
				profileFlexTable.setWidget(i, 2, addtoFav);
				final Button blockProfil = new Button("+");
				profileFlexTable.setWidget(i++, 3, blockProfil);
				
				addtoFav.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						
						 loginService.getCurrentProfile(new AsyncCallback<Profile>() {
							
							@Override
							public void onSuccess(Profile currentProfile) {
								partnerboerseVerwaltung.createFavoritesList(currentProfile, p, new AsyncCallback<FavoritesList>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(FavoritesList result) {
										Window.alert("You selected a menu item!");
									}
								});
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
						});
						
					}
				});
			}
			
		}
		
	});
	
	}
}

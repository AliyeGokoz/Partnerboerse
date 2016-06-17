package de.hdm.partnerboerse.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Partnerboerse implements EntryPoint {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		loginService.login(Window.Location.getHref(), new AsyncCallback<LoginInfo>() {
			
			@Override
			public void onSuccess(LoginInfo result) {
				if(result.isLoggedIn()){
					onModuleLoadLoggedIn(result);
				} else {
					Window.Location.replace(result.getLoginUrl());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});	

		
	}
	
	private void onModuleLoadLoggedIn(final LoginInfo loginInfo){
		final Profile profile = loginInfo.getProfile();
		final VerticalPanel content = new VerticalPanel();

		// Make a command that we will execute from all leaves.
		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected a menu item!");
			}
		};

		Command addnewProfile = new Command() {
			public void execute() {
				NewProfilePage addnewProfil = new NewProfilePage();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(addnewProfil);
			}
		};
		
		if(loginInfo.getProfile() == null){
			addnewProfile.execute();
		}
		
		Command showProfil = new Command() {
			public void execute() {
				ProfilePage showProfil = new ProfilePage();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(showProfil);
			}
		};
		
		Command allUsers = new Command() {
			public void execute() {
				UserOverview allUsers = new UserOverview();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(allUsers);
			}
		};
		
		Command seeFavoritList = new Command() {
			public void execute() {
				FavoritListOverview seeFavoritList = new FavoritListOverview();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(seeFavoritList);
			}
		};
		
		Command seeBlockingList = new Command() {
			public void execute() {
				BlockingListOverview seeBlockingList = new BlockingListOverview();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(seeBlockingList);
			}
		};
		
		Command seeSearchProfilePage = new Command() {
			public void execute() {
				SearchProfilePage showSearchProfile = new SearchProfilePage(profile);
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(showSearchProfile);
			}
		};
		
		Command seePartnerProposel = new Command() {
			public void execute() {
				Window.Location.replace("PartnerboerseReport.html");
			}
		};
		
		Command logoutUser = new Command() {
			public void execute() {
				Window.Location.replace(loginInfo.getLogoutUrl());
			}
		};

		// Make some sub-menus that we will cascade from the top menu.
		

		
		
		MenuBar profilMenu = new MenuBar();
		final String userAddimage = "<img src='images/user_add_edit.png'/>";
		final String showUserimage ="<img src='images/user.png'/>";


		profilMenu.addItem(new MenuItem(userAddimage,true,addnewProfile));
		profilMenu.addItem(new MenuItem(showUserimage,true,showProfil));
		
		MenuBar searchprofilMenu = new MenuBar();
		final String showSearchprofiles ="<img src='images/searchprofiles.png'/>";
		//searchprofilMenu.addItem("Suchprofil anlegen", cmd);
		searchprofilMenu.addItem(new MenuItem(showSearchprofiles,true, seeSearchProfilePage));

		MenuBar favoritlistMenu = new MenuBar(true);
		favoritlistMenu.addItem("Merkzettel ansehen", seeFavoritList);

		MenuBar blockedcontactsMenu = new MenuBar(true);
		blockedcontactsMenu.addItem("Kontaktsperrenliste ansehen", seeBlockingList);
		
		MenuBar allProfilesMenu = new MenuBar(true);
		allProfilesMenu.addItem("Alle Profile Ansehen", allUsers);

		MenuBar partnerproposelMenu = new MenuBar(true);
		partnerproposelMenu.addItem("Finde neue Menschen", seePartnerProposel);
		
		MenuBar logoutButton = new MenuBar(true);
		logoutButton.addItem("Loggen Sie sich aus", logoutUser);

		// Make a new menu bar, adding a few cascading menus to it.
		MenuBar menu = new MenuBar();
		menu.addItem("Profil", profilMenu);
		menu.addItem("Suchprofil", searchprofilMenu);
		menu.addItem("Merkzettel", favoritlistMenu);
		menu.addItem("Kontaktsperre", blockedcontactsMenu);
		menu.addItem("Alle Profile", allProfilesMenu);
		menu.addItem("Personenvorschläge", partnerproposelMenu);
		menu.addItem("Logout", logoutButton);

		// TODO automatisch Usernamen ausgeben
		// Say Hello to User
		final Label lblhello = new Label("Willkommen ");

		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
			
			@Override
			public void onSuccess(Profile value) {
				lblhello.setText("Willkommen " + value.getFirstName());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});

		// Add it to the root panel.

				content.add(lblhello);

				
		RootPanel.get("Content").add(lblhello);
		RootPanel.get("Navigator").add(menu);
	}
}

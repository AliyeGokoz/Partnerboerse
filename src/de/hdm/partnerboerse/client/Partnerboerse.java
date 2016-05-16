package de.hdm.partnerboerse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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

		final VerticalPanel content = new VerticalPanel();

		// Make a command that we will execute from all leaves.
		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected a menu item!");
			}
		};

		Command showProfil = new Command() {
			public void execute() {
				ProfilePage showProfil = new ProfilePage();
				RootPanel.get("Buttonzone").clear();
				RootPanel.get("Contentzone").clear();
				RootPanel.get("Contentzone").add(showProfil);
			}
		};
		
		Command allUsers = new Command() {
			public void execute() {
				UserOverview allUsers = new UserOverview();
				RootPanel.get("Buttonzone").clear();
				RootPanel.get("Contentzone").clear();
				RootPanel.get("Contentzone").add(allUsers);
			}
		};
		
		Command seeFavoritList = new Command() {
			public void execute() {
				FavoritListOverview seeFavoritList = new FavoritListOverview();
				RootPanel.get("Buttonzone").clear();
				RootPanel.get("Contentzone").clear();
				RootPanel.get("Contentzone").add(seeFavoritList);
			}
		};

		// Make some sub-menus that we will cascade from the top menu.
		MenuBar profilMenu = new MenuBar(true);
		profilMenu.addItem("Profil ansehen", showProfil);
		profilMenu.addItem("Suchprofil ansehen", cmd);

		MenuBar favoritlistMenu = new MenuBar(true);
		favoritlistMenu.addItem("Merkzettel ansehen", seeFavoritList);

		MenuBar blockedcontactsMenu = new MenuBar(true);
		blockedcontactsMenu.addItem("Kontaktsperrenliste ansehen", cmd);
		
		MenuBar allProfilesMenu = new MenuBar(true);
		allProfilesMenu.addItem("Alle Profile Ansehen", allUsers);

		MenuBar partnerproposelMenu = new MenuBar(true);
		partnerproposelMenu.addItem("Partnervorschläge Suchprofil", cmd);
		partnerproposelMenu.addItem("Partnervorschläge Neu", cmd);

		// Make a new menu bar, adding a few cascading menus to it.
		MenuBar menu = new MenuBar();
		menu.addItem("Profil", profilMenu);
		menu.addItem("Merkzettel", favoritlistMenu);
		menu.addItem("Kontaktsperre", blockedcontactsMenu);
		menu.addItem("Alle Profile", allProfilesMenu);
		menu.addItem("Personenvorschläge", partnerproposelMenu);

		// TODO automatisch Usernamen ausgeben
		// Say Hello to User
		final Label lblhello = new Label("Willkommen ");

		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
			
			@Override
			public void onSuccess(Profile value) {
				Profile profile = new Profile();
				profile.setFirstName("Max");
				lblhello.setText("Willkommen " + profile.getFirstName());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});

		// Add it to the root panel.

				content.add(lblhello);

		RootPanel.get("Contentzone").add(lblhello);
		RootPanel.get("Navigator").add(menu);
	}
}

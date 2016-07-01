package de.hdm.partnerboerse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Partnerboerse implements EntryPoint {

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
				if (result.isLoggedIn()) {
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

	/**
	 * Methode, welche aufgerufen wird, wenn es ein Nutzer mit bestehendem
	 * Profil ist
	 * 
	 * @param loginInfo
	 */
	private void onModuleLoadLoggedIn(final LoginInfo loginInfo) {
		/*
		 * eingelogtes Profil
		 */
		final Profile profile = loginInfo.getProfile();
		/*
		 * Vertical Panel für die Ausgabe
		 */
		final VerticalPanel all = new VerticalPanel();
		final VerticalPanel content = new VerticalPanel();
		final VerticalPanel herzpane = new VerticalPanel();

		/*
		 * Commands die Aufgerufen werden, wenn in der Menü Leiste ein etwas
		 * angelickt wird.
		 */
		Command addnewProfile = new Command() {
			public void execute() {
				NewProfilePage addnewProfil = new NewProfilePage();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(addnewProfil);
			}
		};

		if (loginInfo.getProfile() == null) {
			addnewProfile.execute();
		}

		Command showProfil = new Command() {
			public void execute() {
				ProfilePage showProfil = new ProfilePage();
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(showProfil.showProfilePage());
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

		/*
		 * Menu-Tabs anlegen
		 */
		MenuBar profilMenu = new MenuBar();
		MenuBar searchprofilMenu = new MenuBar();
		MenuBar favoritlistMenu = new MenuBar();
		MenuBar blockedcontactsMenu = new MenuBar();
		MenuBar allProfilesMenu = new MenuBar();
		MenuBar partnerproposelMenu = new MenuBar();
		MenuBar logoutButton = new MenuBar();

		/*
		 * String für das Menü, damit statt einem Text ein Bild angezeigt wird.
		 */
		final String userAddimage = "<img src='images/user-profile-edition.png'/>";
		final String showUserimage = "<img src='images/user-profile-show.png'/>";
		final String showSearchprofiles = "<img src='images/users.png'/>";

		/*
		 * MenuItems dem Menu-Tab zuweisen. 
		 */
		profilMenu.addItem(new MenuItem(userAddimage, true, addnewProfile));
		profilMenu.addItem(new MenuItem(showUserimage, true, showProfil));

		searchprofilMenu.addItem(new MenuItem(showSearchprofiles, true, seeSearchProfilePage));

		favoritlistMenu.addItem("Merkzettel ansehen", seeFavoritList);

		blockedcontactsMenu.addItem("Kontaktsperrenliste ansehen", seeBlockingList);

		allProfilesMenu.addItem("Alle Profile Ansehen", allUsers);

		partnerproposelMenu.addItem("Finde neue Menschen", seePartnerProposel);

		logoutButton.addItem("Loggen Sie sich aus", logoutUser);

		/*
		 *  Make a new menu bar, adding a few cascading menus to it.
		 */
		MenuBar menu = new MenuBar();
		menu.addItem("Profil", profilMenu);
		menu.addItem("Suchprofil", searchprofilMenu);
		menu.addItem("Merkzettel", favoritlistMenu);
		menu.addItem("Kontaktsperre", blockedcontactsMenu);
		menu.addItem("Alle Profile", allProfilesMenu);
		menu.addItem("Personenvorschläge", partnerproposelMenu);
		menu.addItem("Logout", logoutButton);

		/*
		 * zurgiff auf aktuelles Profil
		 */
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {

			@Override
			public void onSuccess(Profile value) {
				/*
				 * Strings anlegen für die Ausgabe in der HTML
				 */
				final String name = value.getFirstName();
				final String welcome = "Wilkommen";
				final String gLiebe = "finde heute deine große ";
				final String liebe = "Liebe!";
				final String herz = "<img src='images/herz.png'/>";
				
				/*
				 * HTML anlegen
				 */
				final HTML html = new HTML("<div>" + welcome + "</div>");
				final HTML htmlname = new HTML("<div>" + name + "</div>");
				final HTML htmltext = new HTML("<div>" + gLiebe + "</div>");
				final HTML htmlLiebe = new HTML("<div>" + liebe + "</div>");
				final HTML htmlherz = new HTML("<image>" + herz + "</image>");

				/*
				 * Widget dem Panel zuweisen
				 */
				content.add(html);
				content.add(htmlname);
				content.add(htmltext);
				content.add(htmlLiebe);
				herzpane.add(htmlherz);
				
				/*
				 * HTML Stylen
				 */
				htmlname.setStyleName("username");
				htmlLiebe.setStyleName("liebetext");
				htmlherz.setStyleName("herz");
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

		/*
		 * Panel stylen
		 */
		content.setStyleName("contentpanel");
		all.setStyleName("contentpanel");
		
		/*
		 * Panels für Text und Bild dem Panel für alle zuweisen
		 */
		all.add(content);
		all.add(herzpane);
		
		/*
		 * Alles dem RootPanel zuweisen
		 */
		RootPanel.get("Content").add(all);
		RootPanel.get("Navigator").add(menu);
	}
}

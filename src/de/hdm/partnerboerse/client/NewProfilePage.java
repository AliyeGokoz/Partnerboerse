package de.hdm.partnerboerse.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.bo.Profile;


/**
 * Klasse welche den TabPanel generieren 
 * und die Zugriff auf die Ansichten zum hinzufügen von einem Profil
 * und hinzufügen von Infos ermöglichen
 * @author aliyegokoz
 *
 */
public class NewProfilePage extends VerticalPanel {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	
	@Override
	public void onLoad() {

		/*
		 * aufruf von eingelogtem User
		 */
		loginService.login(Window.Location.getHref(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onSuccess(LoginInfo loginInfo) {
				Profile profile = loginInfo.getProfile() == null ? new Profile() : loginInfo.getProfile();
				profile.seteMail(loginInfo.getEmailAddress());

				/*
				 * TabPanel anlegen für die verschiedenen Bereiche wie Profil anlegen
				 * und Informationen anlegen
				 */
				final TabPanel addProfilTapPanel = new TabPanel();

				/*
				 * Title für die Tabs
				 */
				final String tab1Title = "Profil anlegen";
				final String tab2Title = "Informationen anlegen";

				/*
				 * Content für die Tabs Zuweißen
				 */
				addProfilTapPanel.add(addNewProfil(profile), tab1Title);
				addProfilTapPanel.add(addInfoToNewProfil(profile), tab2Title);
				
				/*
				 * ersten Tab auswählen für die Ausgabe
				 */
				addProfilTapPanel.selectTab(0);

				/*
				 * Style TabPanel
				 */
				addProfilTapPanel.setStyleName("profiletabPanel");

				/*
				 * Panel dem RootPanel zuweisen
				 */
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(addProfilTapPanel);
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

	/**
	 * Aufruf Klasse für Infos zum Profil anlegen
	 * @param profil
	 * @return HorizontalPanel
	 */
	private HorizontalPanel addInfoToNewProfil(final Profile profil) {
		final HorizontalPanel infoOfProfilPanel = new HorizontalPanel();
		
		ShowInfoOfProfile showInfos = new ShowInfoOfProfile();
		
		final HorizontalPanel addInfoToProfilPanel = new HorizontalPanel();
		AddInfoToProfilePage addinf = new AddInfoToProfilePage(showInfos);
		addInfoToProfilPanel.add(addinf.addinfotoprofile(profil));
		addInfoToProfilPanel.setStyleName("addinfo");
		
		final VerticalPanel infoPanel = new VerticalPanel();
		infoPanel.add(showInfos.showInfo(profil));
		
		infoOfProfilPanel.add(infoPanel);
		infoOfProfilPanel.add(addInfoToProfilPanel);
		infoPanel.setStyleName("contentinfo");
		
		return infoOfProfilPanel;
	}

	/**
	 * Aufruf Klasse zum hinzufügen von Profil Inhalten
	 * @param profile
	 * @return HorizontalPanel
	 */
	private HorizontalPanel addNewProfil(final Profile profile) {
		final HorizontalPanel addnewProfilPanel = new HorizontalPanel();
		
		AddProfilePage addprofile = new AddProfilePage();
		addnewProfilPanel.add(addprofile.addnewProfile(profile));
		
		return addnewProfilPanel;
	}
}

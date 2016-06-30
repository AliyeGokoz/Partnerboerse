package de.hdm.partnerboerse.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Selection;

/**
 * Klasse welche den TabPanel generieren 
 * und die Zugriff auf die Ansichten zum hinzufügen von einem Profil
 * und hinzufügen von Infos ermöglichen
 * @author aliyegokoz
 *
 */
public class NewProfilePage extends VerticalPanel {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	
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

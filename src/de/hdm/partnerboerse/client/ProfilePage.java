package de.hdm.partnerboerse.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;

public class ProfilePage {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	

	/**
	 * Panel für die Ausgabe
	 */
	final HorizontalPanel showProfile = new HorizontalPanel();
	
	public Widget showProfilePage() {

		/**
		 * Panel für die Ausgabe
		 */
		final HorizontalPanel showProfilePanel = new HorizontalPanel();
		


		loginService.login(Window.Location.getHref(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(LoginInfo loginInfo) {

				
				showProfil(loginInfo);
				showProfilePanel.add(showProfile);

			}
		});
		return showProfilePanel;

	}

	private HorizontalPanel showInfoProfil() {
		final HorizontalPanel showInfoProfilePanel = new HorizontalPanel();
		return showInfoProfilePanel;
	}

	private HorizontalPanel showProfil(final LoginInfo loginInfo) {
		Profile profile = loginInfo.getProfile();

		/**
		 * Anlegen von VerticalPanel für die Buttons
		 */
		final VerticalPanel buttonsPanel = new VerticalPanel();

		/**
		 * Panel für den Inhalt anlegen
		 */
		final HorizontalPanel profilPanel = new HorizontalPanel();

		/**
		 * Buttons werden engelegt für das editieren und löschen
		 */
		final Button deleteProfileButton = new Button("<img src='images/delete_user.png'/>");

		/**
		 * Tabelle für das Formular
		 */
		final FlexTable addnewProfileTable = new FlexTable();
		final FlexTable addnewProfileTable2 = new FlexTable();
		addnewProfileTable.setWidth("200");
		addnewProfileTable2.setWidth("200");

		/**
		 * FlexTable formatieren
		 */
		addnewProfileTable.setCellSpacing(10);
		addnewProfileTable2.setCellSpacing(10);

		/**
		 * Panel für den Inhalt dem VerticalPanel zuweißen
		 */
		profilPanel.add(addnewProfileTable);
		profilPanel.add(addnewProfileTable2);

		/**
		 * Butten dem dazugehörenden Panel zufügen
		 */
		buttonsPanel.add(deleteProfileButton);

		/**
		 * Profil ausgabe
		 */

		/**
		 * Label für die Ausgaben
		 */
		final Label fNLabel = new Label();
		fNLabel.setText(profile.getFirstName());
		final Label lNLabel = new Label();
		lNLabel.setText(profile.getLastName());
		final Label ELabel = new Label();
		ELabel.setText(profile.geteMail());
		final Label bDLabel = new Label();
		bDLabel.setText(profile.getDateOfBirth().toString());
		final Label heightLabel = new Label();
		heightLabel.setText(Integer.toString(profile.getHeight()));
		final Label lgender = new Label();
		lgender.setText(profile.getGender().getName());
		final Label lhaircolor = new Label();
		lhaircolor.setText(profile.getHairColor().getName().toString());
		final Label lconf = new Label();
		lconf.setText(profile.getConfession().getName().toString());
		final Label lsmoke = new Label();
		lsmoke.setText(profile.isSmoker() ? "Ja" : "Nein");

		/**
		 * FlexTable mit Inhalt füllen = Userprofil Formular
		 */
		// addnewProfileTable.setHTML(0, 0, "<h2>Profil anlegen</h2>");
		addnewProfileTable.setHTML(0, 0, "<div>Vorname</div>");
		addnewProfileTable.setWidget(0, 1, fNLabel);
		addnewProfileTable.setHTML(1, 0, "<div>Geburtsdatum</div>");
		addnewProfileTable.setWidget(1, 1, bDLabel);
		addnewProfileTable.setHTML(2, 0, "<div>Geschlecht</div>");
		addnewProfileTable.setWidget(2, 1, lgender);
		addnewProfileTable.setHTML(3, 0, "<div>Religion</div>");
		addnewProfileTable.setWidget(3, 1, lconf);
		addnewProfileTable.setHTML(4, 0, "<div>Raucher</div>");
		addnewProfileTable.setWidget(4, 1, lsmoke);

		addnewProfileTable2.setHTML(0, 0, "<div>Nachname</div>");
		addnewProfileTable2.setWidget(0, 1, lNLabel);
		addnewProfileTable2.setHTML(1, 0, "<div>Email</div>");
		addnewProfileTable2.setWidget(1, 1, ELabel);
		addnewProfileTable2.setHTML(2, 0, "<div>Größe</div>");
		addnewProfileTable2.setWidget(2, 1, heightLabel);
		addnewProfileTable2.setHTML(3, 0, "<div>Haarfarbe</div>");
		addnewProfileTable2.setWidget(3, 1, lhaircolor);

		showProfile.add(profilPanel);
		showProfile.add(buttonsPanel);

		deleteProfileButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// Instantiate the dialog box and show it.
				MyDialogforDltProfiles myDialog = new MyDialogforDltProfiles(loginInfo);

				int left = Window.getClientWidth() / 2;
				int top = Window.getClientHeight() / 2;
				myDialog.setPopupPosition(left, top);
				myDialog.show();
			}
		});

		return showProfile;
	}

	
	private class MyDialogforDltProfiles extends DialogBox {

		public MyDialogforDltProfiles(final LoginInfo loginInfo) {
			// Set the dialog box's caption.
			setText("Profil löschen");

			// Enable animation.
			setAnimationEnabled(true);

			// Enable glass background.
			setGlassEnabled(true);

			// DialogBox is a SimplePanel, so you have to set its widget
			// property to whatever you want its contents to be.
			Button jaButton = new Button("Ja");
			Button neinButton = new Button("Nein");
			neinButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					MyDialogforDltProfiles.this.hide();
				}
			});

			// TODO löschen

			jaButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					// partnerboerseVerwaltung.delete(profile, new
					// AsyncCallback<Void>(){
					//
					// }
					partnerboerseVerwaltung.delete(loginInfo.getProfile(), new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							Window.Location.assign(loginInfo.getLogoutUrl());
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler beim löschen des Profiles");
						}
					});
				}
			});

			Label label = new Label("Wollen sie Ihr GANZES Profil wirklich unwiederruflich löschen?");

			final VerticalPanel panel = new VerticalPanel();
			panel.setHeight("100");
			panel.setWidth("300");
			panel.setSpacing(10);
			panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			panel.add(label);
			panel.add(jaButton);
			panel.add(neinButton);

			setWidget(panel);
		}
	}

}

package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Klasse für die Ausgabe des Profiles
 * 
 * @author aliyegokoz
 * @author alena gerlinskaja
 */
public class ProfilePage {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/*
	 * Panel für die Ausgabe
	 */
	final VerticalPanel showProfile = new VerticalPanel();
	final HorizontalPanel showprof = new HorizontalPanel();
	final VerticalPanel showinfo = new VerticalPanel();

	/*
	 * Widgets anlegen für die Ausgabe
	 */
	final Label fNLabel = new Label();
	final Label lNLabel = new Label();
	final Label ELabel = new Label();
	final Label bDLabel = new Label();
	final Label heightLabel = new Label();
	final Label lgender = new Label();
	final Label lorientation = new Label();
	final Label lhaircolor = new Label();
	final Label lconf = new Label();
	final Label lsmoke = new Label();
	final HTML name = new HTML("Vorname");
	final HTML lastname = new HTML("Nachname");
	final HTML birth = new HTML("Geburtsdatum");
	final HTML gender = new HTML("Geschlecht");
	final HTML orient = new HTML("Orientierung");
	final HTML conff = new HTML("Religion");
	final HTML smoke = new HTML("Raucher");
	final HTML email = new HTML("Email");
	final HTML height = new HTML("Größe");
	final HTML haircolor = new HTML("Haarfarbe");
	final HTML ubermich = new HTML("Über Mich");

	
	/*
	 * CellTable für die Ausgabe der Informationen
	 */
	final CellTable<Info> infoTable = new CellTable<>();
	final ListDataProvider<Info> infoDataProvider = new ListDataProvider<>();
	
	/**
	 * 
	 * Widget als Rückgabe für den Aufruf. Diese Methode gibt beim Klick auf den
	 * Button das Profil des eingeloggten Users aus.
	 *
	 * @return Widget = HorizontalPanel
	 */
	public Widget showProfilePage() {

		/*
		 * Panel für die Ausgabe
		 */
		final HorizontalPanel showProfilePanel = new HorizontalPanel();

		/*
		 * Aktueler User
		 */
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

		/*
		 * Panel Stylen
		 */
		showProfilePanel.setStyleName("contentpanel");

		/*
		 * Return Panel
		 */
		return showProfilePanel;

	}

	/**
	 * Ausgabe des Profiles
	 * 
	 * @param loginInfo
	 * @return HorizontalPanel
	 */
	private VerticalPanel showProfil(final LoginInfo loginInfo) {
		Profile profile = loginInfo.getProfile();

		/*
		 * Anlegen von VerticalPanel für die Buttons
		 */
		final VerticalPanel buttonsPanel = new VerticalPanel();

		/*
		 * Panel für den Inhalt anlegen
		 */
		final HorizontalPanel profilPanel = new HorizontalPanel();

		/*
		 * Buttons werden engelegt für das editieren und löschen
		 */
		final Button deleteProfileButton = new Button("<img src='images/delete.png'/>");

		/*
		 * Style Button, Label
		 */
		showinfo.setStyleName("infospanel");
		deleteProfileButton.setStyleName("button");
		name.setStyleName("labelstyle");
		lastname.setStyleName("labelstyle");
		email.setStyleName("labelstyle");
		gender.setStyleName("labelstyle");
		height.setStyleName("labelstyle");
		conff.setStyleName("labelstyle");
		orient.setStyleName("labelstyle");
		smoke.setStyleName("labelstyle");
		haircolor.setStyleName("labelstyle");
		birth.setStyleName("labelstyle");
		ubermich.setStyleName("labelstyle");

		/*
		 * Tabelle für das Formular
		 */
		final FlexTable addnewProfileTable = new FlexTable();
		final FlexTable addnewProfileTable2 = new FlexTable();

		/*
		 * FlexTable formatieren
		 */
		addnewProfileTable.setWidth("200");
		addnewProfileTable2.setWidth("200");
		addnewProfileTable.setCellSpacing(10);
		addnewProfileTable2.setCellSpacing(10);

		/*
		 * Panel für den Inhalt dem VerticalPanel zuweißen
		 */
		profilPanel.add(addnewProfileTable);
		profilPanel.add(addnewProfileTable2);

		/*
		 * Butten dem dazugehörenden Panel zufügen
		 */
		buttonsPanel.add(deleteProfileButton);

		/*
		 * Label mit Inhalt füllen
		 */
		fNLabel.setText(profile.getFirstName());
		lNLabel.setText(profile.getLastName());
		ELabel.setText(profile.geteMail());
		heightLabel.setText(Integer.toString(profile.getHeight()));
		lgender.setText(profile.getGender().getName());
		lorientation.setText(profile.getOrientation().getName());
		lhaircolor.setText(profile.getHairColor().getName().toString());
		lconf.setText(profile.getConfession().getName().toString());
		lsmoke.setText(profile.isSmoker() ? "Ja" : "Nein");

		/*
		 * FlexTable mit Inhalt füllen = Userprofil Formular
		 */
		addnewProfileTable.setWidget(0, 0, name);
		addnewProfileTable.setWidget(0, 1, fNLabel);
		addnewProfileTable.setWidget(1, 0, birth);
		addnewProfileTable.setWidget(1, 1, bDLabel);
		addnewProfileTable.setWidget(2, 0, gender);
		addnewProfileTable.setWidget(2, 1, lgender);
		addnewProfileTable.setWidget(3, 0, orient);
		addnewProfileTable.setWidget(3, 1, lorientation);
		addnewProfileTable.setWidget(4, 0, conff);
		addnewProfileTable.setWidget(4, 1, lconf);
		addnewProfileTable.setWidget(5, 0, smoke);
		addnewProfileTable.setWidget(5, 1, lsmoke);

		addnewProfileTable2.setWidget(0, 0, lastname);
		addnewProfileTable2.setWidget(0, 1, lNLabel);
		addnewProfileTable2.setWidget(1, 0, email);
		addnewProfileTable2.setWidget(1, 1, ELabel);
		addnewProfileTable2.setWidget(2, 0, height);
		addnewProfileTable2.setWidget(2, 1, heightLabel);
		addnewProfileTable2.setWidget(3, 0, haircolor);
		addnewProfileTable2.setWidget(3, 1, lhaircolor);

		showprof.add(profilPanel);
		showprof.add(buttonsPanel);
		

		/*
		 * dem Button zum Löschen einen Clickhandler zuweißen
		 */
		deleteProfileButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MyDialogforDltProfiles myDialog = new MyDialogforDltProfiles(loginInfo);

				myDialog.center();
				myDialog.show();
			}
		});

		/*
		 * Datumformat in Deutsches Format
		 */
		getDate(profile);
		
		/*
		 * Headline für die Infos
		 */
		showinfo.add(ubermich);
		
		/*
		 * Infos ausgeben
		 */
		getInfos(profile);
		
		showProfile.add(showprof);
		showProfile.add(showinfo);

		return showProfile;
	}
	
	public void getInfos(final Profile profile){
		
		/*
		 * Columns für die Informationen
		 */
		infoTable.addColumn(new TextColumn<Info>() {
			@Override
			public String getValue(Info object) {
				if (object.getSelection() != null) {
					return object.getSelection().getTextualDescriptionForProfile();
				} else if (object.getDescription() != null) {
					return object.getDescription().getTextualDescriptionForProfile();
				}
				return "";
			}
		});

		infoTable.addColumn(new TextColumn<Info>() {
			@Override
			public String getValue(Info object) {
				return object.getInformationValue();
			}
		});
		
		infoDataProvider.addDataDisplay(infoTable);
		
		showinfo.add(infoTable);
		
		loadInfos(profile);
		
	}
	
	public void loadInfos(Profile profile) {
		partnerboerseVerwaltung.getInfoOf(profile, new AsyncCallback<ArrayList<Info>>() {

			@Override
			public void onSuccess(ArrayList<Info> result) {
				infoDataProvider.getList().clear();
				infoDataProvider.getList().addAll(result);
				infoDataProvider.flush();
				infoDataProvider.refresh();
				infoTable.redraw();
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	

	/**
	 * DialogBox erzeugen die Erscheint, wenn auf den Löschen-Button geklickt
	 * wird.
	 */
	private class MyDialogforDltProfiles extends DialogBox {

		/**
		 * DialogBox mit einer Ausgabe füllen
		 * 
		 * @param loginInfo
		 */
		public MyDialogforDltProfiles(final LoginInfo loginInfo) {
			/*
			 * Überschrift der DialogBox
			 */
			setText("Profil löschen");

			/*
			 * Button generieren für die Antwort des Users
			 */
			Button jaButton = new Button("Ja");
			Button neinButton = new Button("Nein");
			
			/*
			 * ClickHandler für die Buttons
			 */
			neinButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					MyDialogforDltProfiles.this.hide();
				}
			});

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

			/*
			 * Label für die Frage
			 */
			Label label = new Label("Wollen sie Ihr GANZES Profil wirklich unwiederruflich löschen?");

			/*
			 * VerticalPanel mit Ausgabe
			 */
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

	/**
	 * Datumformat in Deutsches format umwandeln
	 * @param profile
	 */
	public void getDate(final Profile profile) {
		try {
			DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
			String result = format.format(profile.getDateOfBirth());
			bDLabel.setText("" + result);
		} catch (Exception e) {
			// ignore
		}
	}

}

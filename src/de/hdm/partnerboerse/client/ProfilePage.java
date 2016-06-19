package de.hdm.partnerboerse.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Film;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Profile.Music;

public class ProfilePage extends VerticalPanel {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();


	@Override
	public void onLoad() {
		
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Profile profile) {
				/**
				 * TabPanel anlegen für die verschiedenen Bereiche wie Allg infos und Über Mich
				 */
				final TabPanel showProfilTapPanel = new TabPanel();
				
				/**
				 * Title für die Tabs
				 */
				final String tab1Title = "Profil";
				final String tab2Title = "Über Mich";
				
				/**
				 * Content für die Tabs Zuweißen
				 */
				showProfilTapPanel.add(showProfil(profile), tab1Title);
				showProfilTapPanel.add(showInfoProfil(), tab2Title);
				
				//select first tab
				showProfilTapPanel.selectTab(0);

				//set width if tabpanel
				showProfilTapPanel.setStyleName("profiletabPanel");
						
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(showProfilTapPanel);
				
			}
		});
		
		
//		// buttonausgabe für den Testzweck
//		final VerticalPanel buttonsPanel = new VerticalPanel();
//		final VerticalPanel contentPanel = new VerticalPanel();
//		final Button editProfile = new Button("Profil bearbeiten");
//		final Button deleteProfile = new Button("Profil löschen");
//		final Button addProfile = new Button("Profil anlegen");
//		final StackPanel showPanel = new StackPanel();
//
//		buttonsPanel.setStyleName("hbuttons");
//		contentPanel.setStyleName("hcontent");
//
//		addProfile.setStyleName("buttonwidth");
//		editProfile.setStyleName("buttonwidth");
//		deleteProfile.setStyleName("buttonwidth");
//
//		buttonsPanel.add(addProfile);
//		buttonsPanel.add(editProfile);
//		buttonsPanel.add(deleteProfile);
//
//		showPanel.setWidth("500px");
//		showPanel.add(showProfil(), "Mein Profil");
//		showPanel.add(showInfos(), "Meine Informationen");
//		
//		contentPanel.add(showPanel);

//		RootPanel.get("Content").clear();
//		RootPanel.get("Content").add(buttonsPanel);
//		RootPanel.get("Content").add(contentPanel);

//		deleteProfile.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// Instantiate the dialog box and show it.
//				MyDialogforDltProfiles myDialog = new MyDialogforDltProfiles();
//
//				int left = Window.getClientWidth() / 2;
//				int top = Window.getClientHeight() / 2;
//				myDialog.setPopupPosition(left, top);
//				myDialog.show();
//			}
//		});

		
//		editProfile.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				editPanelProfil();
//			}
////		});
//
//		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
//
//			@Override
//			public void onSuccess(Profile value) {
//				Profile profile = new Profile();
//				profile.setFirstName("Max");
//				profile.setLastName("Mustermann");
//				profile.setDateOfBirth(new Date());
//				profile.seteMail("mustermann@hdhhd.de");
//				profile.setHeight(170);
//				profile.setHairColor(null);
//				profile.setGender(null);
//				profile.setConfession(null);
//				profile.setSmoker(true);
//
//				// TODO get-set für die Eigenschaften
				/*
				 * profile.g; profile.setSmoker(true); profile.setSmoker(true);
				 * profile.setSmoker(true); profile.setSmoker(true);
				 * profile.setSmoker(true);
				 */
//				showlFirstname.setText("Willkommen " + profile.getFirstName());
//				showlLastName.setText("Nachname: " + profile.getLastName());
//				showlDateofbirth.setText("Geburtsdatum: " + profile.getDateOfBirth());
//				showlEmail.setText("Email: " + profile.geteMail());
//				showlHeight.setText("Größe: " + profile.getHeight());
//				showlHairColor.setText("Haarfarbe: " + profile.getHairColor());
//				showlConfession.setText("Religion: " + profile.getConfession());
//				showlGender.setText("Geschlecht: " + profile.getGender());
//				showlSmoker.setText("Raucher: " + profile.isSmoker());
//				/*
//				 * lHobbyText.setText("Ich mache gern:"); lMusikText.setText(
//				 * "Ich höre gern:"); lfavBand.setText(
//				 * "Mein/e Lieblingsband/Lieblingskünstler/in sind/ist: ");
//				 * lSportText.setText(
//				 * "Ich betreibe gerne folgende Sportart/en: ");
//				 * lFilmeText.setText("Ich schaue gerne: "); lfavFilm.setText(
//				 * "Mein/e Lieblingsfilm/e ist/sind: ");
//				 */
//
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//
//			}
//		});

	}

	private HorizontalPanel showInfoProfil() {
		final HorizontalPanel showInfoProfilePanel = new HorizontalPanel();
		return showInfoProfilePanel;
	}

	private HorizontalPanel showProfil(final Profile profile) {
		
		//TODO inhalt anzeigen wenn Profil gefüllt, wenn kein Profil gefüllt ist dann Anzeige: Hey 
		// hey leg dir doch ein Profil an
		
		/**
		 * Panel für die Ausgabe
		 */
		final HorizontalPanel showProfilePanel = new HorizontalPanel();
		
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
		// TODO smoker ausgabe
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

		addnewProfileTable2.setHTML(0, 0, "<div>Nachname</div>");
		addnewProfileTable2.setWidget(0, 1, lNLabel);
		addnewProfileTable2.setHTML(1, 0, "<div>Email</div>");
		addnewProfileTable2.setWidget(1, 1, ELabel);
		addnewProfileTable2.setHTML(2, 0, "<div>Größe</div>");
		addnewProfileTable2.setWidget(2, 1, heightLabel);
		addnewProfileTable2.setHTML(3, 0, "<div>Haarfarbe</div>");
		addnewProfileTable2.setWidget(3, 1, lhaircolor);
		addnewProfileTable2.setHTML(4, 0, "<div>Religion</div>");
		addnewProfileTable2.setWidget(4, 1, lconf);
		addnewProfileTable2.setHTML(5, 0, "<div>Raucher</div>");
		addnewProfileTable2.setWidget(5, 1, lsmoke);
		

		
		showProfilePanel.add(profilPanel);
		showProfilePanel.add(buttonsPanel);
		
		deleteProfileButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// Instantiate the dialog box and show it.
				MyDialogforDltProfiles myDialog = new MyDialogforDltProfiles(profile);

				int left = Window.getClientWidth() / 2;
				int top = Window.getClientHeight() / 2;
				myDialog.setPopupPosition(left, top);
				myDialog.show();
			}
		});
		
		return showProfilePanel;
	}

//	/**
//	 * neues Profil zum ansehen
//	 */
//	private VerticalPanel showProfil() {
//		final VerticalPanel showProfil = new VerticalPanel();
//
//		showProfil.add(showlFirstname);
//		showProfil.add(showlLastName);
//		showProfil.add(showlDateofbirth);
//		showProfil.add(showlEmail);
//		showProfil.add(showlHeight);
//		showProfil.add(showlHairColor);
//		showProfil.add(showlConfession);
//		showProfil.add(showlGender);
//		showProfil.add(showlSmoker);
//
//		return showProfil;
//	}
//
//	private DecoratorPanel showInfos() {
//		final VerticalPanel showinfotoProfilPanel = new VerticalPanel();
//		final ScrollPanel scrollPanel = new ScrollPanel(showinfotoProfilPanel);
//		scrollPanel.setSize("500px", "300px");
//		DecoratorPanel decoratorPanel = new DecoratorPanel();
//		decoratorPanel.add(scrollPanel);
//
//		//TODO Label Dynamisch füllen
//		final Label showlHobby = new Label("Hobbys");
//		final Label showlHobbyText = new Label("Ich mache gern:");
//		final Label showlMusik = new Label("Musik");
//		final Label showlMusikText = new Label("Ich höre gern:");
//		final Label showlfavBand = new Label("Mein/e Lieblingsband/Lieblingskünstler/in sind/ist: ");
//		final Label showlSport = new Label("Sport");
//		final Label showlSportText = new Label("Ich betreibe gerne folgende Sportart/en: ");
//		final Label showlFilme = new Label("Filme");
//		final Label showlFilmeText = new Label("Ich schaue gerne: ");
//		final Label showlfavFilm = new Label("Mein/e Lieblingsfilm/e ist/sind: ");
//
//		showinfotoProfilPanel.add(showlHobby);
//		showinfotoProfilPanel.add(showlHobbyText);
//		showinfotoProfilPanel.add(showlMusik);
//		showinfotoProfilPanel.add(showlMusikText);
//		showinfotoProfilPanel.add(showlfavBand);
//		showinfotoProfilPanel.add(showlSport);
//		showinfotoProfilPanel.add(showlSportText);
//		showinfotoProfilPanel.add(showlFilme);
//		showinfotoProfilPanel.add(showlFilmeText);
//		showinfotoProfilPanel.add(showlfavFilm);
//
//		return decoratorPanel;
//	}
//	
//	public HorizontalPanel addnewUserProfil(){
//		
//		final HorizontalPanel addnewUserProfilPanel = new HorizontalPanel();
//		
//		/**
//		 * Panel für Button und Content Ausgabe
//		 */
//		final HorizontalPanel contentPanel = new HorizontalPanel();
//		final VerticalPanel addProfilePanel = new VerticalPanel();
//		final VerticalPanel addProfilePanel2 = new VerticalPanel();
//		final FlexTable addnewProfileTable = new FlexTable();
//		final FlexTable addnewProfileTable2 = new FlexTable();
//		addProfilePanel2.setStyleName("hcontent2");
//		addProfilePanel.setStyleName("hcontent");
//		
//		contentPanel.add(addProfilePanel);
//		contentPanel.add(addProfilePanel2);
//		
//		/**
//		 * Button anlegen zum zurück gehen
//		 */
//		final Button addinfo = new Button("<img src='images/back.png'/><div>Neue Info anlegen</div>");
//		final Button saveUser = new Button("Speichern");
//		
//
//		/**
//		 * FlexTable formatieren
//		 */
//		addnewProfileTable.setCellSpacing(10);
//		addnewProfileTable2.setCellSpacing(10);
//		
//		/**
//		 * Textbox Widgets anlegen für die Eingaben des Users
//		 */
//		final TextBox tFirstname = new TextBox();
//		final TextBox tLastname = new TextBox();
//		final TextBox tEmail = new TextBox();
//		final TextBox tHeight = new TextBox();
//		
//		/**
//		 * Listbox Widgets anlegen für Haarfarbe und Religion
//		 */
//		final ListBox lbHaircolor = new ListBox();
//		final ListBox lbConfession = new ListBox();
//		
//		HairColor[] hairColorValue = Profile.HairColor.values();
//		for (HairColor hairColor : hairColorValue) {
//			lbHaircolor.addItem(hairColor.getName(), hairColor.toString());
//		}
//		
//		Confession[] confessionValue = Profile.Confession.values();
//		for (Confession confession : confessionValue) {
//			lbConfession.addItem(confession.getName(), confession.toString());
//		}
//		
//		Gender[] genderValues = Profile.Gender.values();
//		int i = 2;
//		for (Gender gender : genderValues) {
//			RadioButton radioButton = new RadioButton("genderGroup", gender.getName());
//			addnewProfileTable.setWidget(i++, 1, radioButton);
//		}
//		
//		RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
//		RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
//		
//		//TODO DatePicker überarbeiten
//		final DatePicker datePicker = new DatePicker();
//		
//		/**
//		 * FlexTable mit Inhalt füllen = Userprofil Formular
//		 */
//		//addnewProfileTable.setHTML(0, 0, "<h2>Profil anlegen</h2>");
//		addnewProfileTable.setHTML(0, 0, "<div>Vorname</div>");
//		addnewProfileTable.setWidget(0, 1, tFirstname);
//	    addnewProfileTable.setHTML(1, 0, "<div>Geburtsdatum</div>");
//	    addnewProfileTable.setWidget(1, 1, datePicker);
//	    addnewProfileTable.setHTML(2, 0, "<div>Geschlecht</div>");
//	    
//	    
//	    addnewProfileTable2.setHTML(0, 0, "<div>Nachname</div>");
//	    addnewProfileTable2.setWidget(0, 1, tLastname);
//	    addnewProfileTable2.setHTML(1, 0, "<div>Email</div>");
//	    addnewProfileTable2.setWidget(1, 1, tEmail);
//	    addnewProfileTable2.setHTML(2, 0, "<div>Größe</div>");
//	    addnewProfileTable2.setWidget(2, 1, tHeight);
//	    addnewProfileTable2.setHTML(3, 0, "<div>Haarfarbe</div>");
//	    addnewProfileTable2.setWidget(3, 1, lbHaircolor);
//	    addnewProfileTable2.setHTML(4, 0, "<div>Religion</div>");
//	    addnewProfileTable2.setWidget(4, 1, lbConfession);
//	    addnewProfileTable2.setHTML(5, 0, "<div>Raucher</div>");
//	    addnewProfileTable2.setWidget(5, 1, Rbsmokeyes);
//	    addnewProfileTable2.setWidget(6, 1, Rbsmokeno);
//	    /**
//	     * Zelle formatieren bei Gender
//	     */
//	    //cellFormatter.setColSpan(0, 0, 2);
//	    //cellFormatter.setHorizontalAlignment(
//	        //0, 0, HasHorizontalAlignment.ALIGN_CENTER);
//	
//	    
//	    addProfilePanel.add(addnewProfileTable);
//	    addProfilePanel.add(addinfo);
//	    //addProfilePanel.add(saveUser);
//	    addProfilePanel2.add(addnewProfileTable2);
//		
//		
//		return addnewUserProfilPanel;
//	}
	
//	private void editPanelProfil() {
//
//		final HorizontalPanel saveUserPanel = new HorizontalPanel();
//		final StackPanel profilPanel = new StackPanel();
//		Button saveUser = new Button("Speichern");
//		profilPanel.setWidth("500px");
//		profilPanel.add(editnewProfil(), "Profil bearbeiten");
//		profilPanel.add(editInfoProfil(), "Informationen bearbeiten");
//		saveUserPanel.add(saveUser);
//
//		RootPanel.get("Content").clear();
//		RootPanel.get("Content").add(profilPanel);
//		RootPanel.get("Content").add(saveUserPanel);
//
//	}

//	private HorizontalPanel editnewProfil() {
//
//		final HorizontalPanel editProfilPanel = new HorizontalPanel();
//		final Grid maineditGrid = new Grid(10, 4);
//
//		final Label lTitle = new Label("Neues Profil anlegen");
//		final Label lFirstname = new Label("Vorname: ");
//		final Label lLastName = new Label("Nachname: ");
//		final Label lDateofbirth = new Label("Geburtsdatum: ");
//		final Label lEmail = new Label("Email: ");
//		final Label lHeight = new Label("Größe: ");
//		final Label lHairColor = new Label("Haarfarbe: ");
//		final Label lConfession = new Label("Religion: ");
//		final Label lGender = new Label("Geschlecht: ");
//		final Label lSmoker = new Label("Raucher: ");
//
//		final TextBox tFirstname = new TextBox();
//		final TextBox tLastname = new TextBox();
//		final TextBox tDateofbirth = new TextBox();
//		final TextBox tEmail = new TextBox();
//		final TextBox tHeight = new TextBox();
//		final ListBox lbHaircolor = new ListBox();
//		final ListBox lbConfession = new ListBox();
//		RadioButton Rbgenderfm = new RadioButton("genderGroup", "weiblich");
//		RadioButton Rbgenderm = new RadioButton("genderGroup", "männlich");
//		RadioButton Rbgendero = new RadioButton("genderGroup", "andere");
//		RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
//		RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
//
//		lbHaircolor.addItem("select");
//		lbHaircolor.addItem("braun");
//		lbHaircolor.addItem("blond");
//		lbHaircolor.addItem("schwarz");
//		lbHaircolor.addItem("rot");
//		lbHaircolor.addItem("grau");
//		lbHaircolor.addItem("others");
//
//		lbConfession.addItem("select");
//		lbConfession.addItem("evangelisch");
//		lbConfession.addItem("katholisch");
//		lbConfession.addItem("buddistisch");
//		lbConfession.addItem("hinduistisch");
//		lbConfession.addItem("muslimisch");
//		lbConfession.addItem("jüdisch");
//		lbConfession.addItem("keine Konfession");
//		lbConfession.addItem("andere");
//
//		lbHaircolor.setVisibleItemCount(1);
//		lbConfession.setVisibleItemCount(1);
//
//		maineditGrid.setWidget(0, 0, lTitle);
//		maineditGrid.setWidget(1, 0, lFirstname);
//		maineditGrid.setWidget(2, 0, lLastName);
//		maineditGrid.setWidget(3, 0, lDateofbirth);
//		maineditGrid.setWidget(4, 0, lEmail);
//		maineditGrid.setWidget(5, 0, lHeight);
//		maineditGrid.setWidget(6, 0, lHairColor);
//		maineditGrid.setWidget(7, 0, lConfession);
//		maineditGrid.setWidget(8, 0, lGender);
//		maineditGrid.setWidget(9, 0, lSmoker);
//		maineditGrid.setWidget(1, 1, tFirstname);
//		maineditGrid.setWidget(2, 1, tLastname);
//		maineditGrid.setWidget(3, 1, tDateofbirth);
//		maineditGrid.setWidget(4, 1, tEmail);
//		maineditGrid.setWidget(5, 1, tHeight);
//		maineditGrid.setWidget(6, 1, lbHaircolor);
//		maineditGrid.setWidget(7, 1, lbConfession);
//		maineditGrid.setWidget(8, 1, Rbgenderfm);
//		maineditGrid.setWidget(8, 2, Rbgenderm);
//		maineditGrid.setWidget(8, 3, Rbgendero);
//		maineditGrid.setWidget(9, 1, Rbsmokeyes);
//		maineditGrid.setWidget(9, 2, Rbsmokeno);
//
//		editProfilPanel.add(maineditGrid);
//
//		return editProfilPanel;
//	}
//	
//	private DecoratorPanel editInfoProfil() {
//
//		final VerticalPanel editInfoProfilPanel = new VerticalPanel();
//		final ScrollPanel scrollPanel = new ScrollPanel(editInfoProfilPanel);
//		scrollPanel.setSize("500px", "300px");
//		DecoratorPanel decoratorPanel = new DecoratorPanel();
//		decoratorPanel.add(scrollPanel);
//
//		final ListBox lbMusik = new ListBox();
//		final ListBox lbFilme = new ListBox();
//
//		final CheckBox cbSport1 = new CheckBox("Fußball");
//		final CheckBox cbSport2 = new CheckBox("Basketball");
//		final CheckBox cbSport3 = new CheckBox("Wandern");
//		final CheckBox cbSport4 = new CheckBox("Volleyball");
//		final CheckBox cbSport5 = new CheckBox("Volkstanz");
//		final CheckBox cbSport6 = new CheckBox("Tennis");
//		final CheckBox cbSport7 = new CheckBox("Tauchen");
//		final CheckBox cbSport8 = new CheckBox("Teakwondo");
//		final CheckBox cbSport9 = new CheckBox("Seilspringen");
//		final CheckBox cbSport10 = new CheckBox("Schwimmen");
//		final CheckBox cbSport11 = new CheckBox("Rugby");
//		final CheckBox cbSport12 = new CheckBox("Other");
//
//		final TextBox tHobby = new TextBox();
//		final TextBox tBand = new TextBox();
//		final TextBox tFilme = new TextBox();
//		// TODO value zuweißen
//		// tFilme.setValue("Bla");
//
//		/**
//		 * Untenstrich für die Infoüberschriften
//		 */
//		lHobby.setStyleName("infohead");
//		lMusik.setStyleName("infohead");
//		lSport.setStyleName("infohead");
//		lFilme.setStyleName("infohead");
//		tHobby.setStyleName("textboxsize");
//		tBand.setStyleName("textboxsize");
//		tFilme.setStyleName("textboxsize");
//
//		/**
//		 * Hinzufügen der Info-Objekte für die Eigenschaft Musik
//		 */
//		lbMusik.addItem("select");
//		lbMusik.addItem("Rock");
//		lbMusik.addItem("Pop");
//		lbMusik.addItem("Electro");
//		lbMusik.addItem("House");
//		lbMusik.addItem("Volksmusik");
//		lbMusik.addItem("others");
//
//		/**
//		 * Hinzufügen der Info-Objekte für die Eigenschaft Musik
//		 */
//		lbFilme.addItem("select");
//		lbFilme.addItem("Rock");
//		lbFilme.addItem("Pop");
//		lbFilme.addItem("Electro");
//		lbFilme.addItem("House");
//		lbFilme.addItem("Volksmusik");
//		lbFilme.addItem("others");
//
//		/**
//		 * new Panel für die Sportarten Checkboxen
//		 */
//		final HorizontalPanel sportCheckBoxen = new HorizontalPanel();
//
//		sportCheckBoxen.add(cbSport1);
//		sportCheckBoxen.add(cbSport2);
//		sportCheckBoxen.add(cbSport3);
//		sportCheckBoxen.add(cbSport4);
//		sportCheckBoxen.add(cbSport5);
//		sportCheckBoxen.add(cbSport6);
//		sportCheckBoxen.add(cbSport7);
//		sportCheckBoxen.add(cbSport8);
//		sportCheckBoxen.add(cbSport9);
//		sportCheckBoxen.add(cbSport10);
//		sportCheckBoxen.add(cbSport11);
//		sportCheckBoxen.add(cbSport12);
//
//		/**
//		 * Hinzufügen der Widgets an den Panel
//		 */
//		editInfoProfilPanel.add(lHobby);
//		editInfoProfilPanel.add(lHobbyText);
//		editInfoProfilPanel.add(tHobby);
//		editInfoProfilPanel.add(lMusik);
//		editInfoProfilPanel.add(lMusikText);
//		editInfoProfilPanel.add(lbMusik);
//		editInfoProfilPanel.add(lfavBand);
//		editInfoProfilPanel.add(tBand);
//		editInfoProfilPanel.add(lSport);
//		editInfoProfilPanel.add(lSportText);
//		editInfoProfilPanel.add(sportCheckBoxen);
//		editInfoProfilPanel.add(lFilme);
//		editInfoProfilPanel.add(lFilmeText);
//		editInfoProfilPanel.add(lbFilme);
//		editInfoProfilPanel.add(lfavFilm);
//		editInfoProfilPanel.add(tFilme);
//
//		return decoratorPanel;
//	}
//
	private class MyDialogforDltProfiles extends DialogBox {

		public MyDialogforDltProfiles(final Profile profile) {
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
			
			//TODO löschen

			jaButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
//					partnerboerseVerwaltung.delete(profile, new AsyncCallback<Void>(){
//						
//					}
					partnerboerseVerwaltung.delete(profile, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							Window.alert("Profil wurde gelöscht");
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

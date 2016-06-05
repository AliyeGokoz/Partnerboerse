package de.hdm.partnerboerse.client;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.SearchProfile;

public class SearchProfilePage extends VerticalPanel {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/**
	 * VerticalPanel für die Widgets anlegen
	 */
	final HorizontalPanel searchprofilesPanel = new HorizontalPanel();
	final VerticalPanel buttonsearchProfilePanel = new VerticalPanel();
	final VerticalPanel searchProfilesPanel = new VerticalPanel();
	final VerticalPanel buttonSPpanel = new VerticalPanel();
	final VerticalPanel showallSPPanel = new VerticalPanel();
	final VerticalPanel showoneSPPanel = new VerticalPanel();

	final TextBox showlHeight = new TextBox();
	final TextBox showlHairColor = new TextBox();
	final TextBox showlConfession = new TextBox();
	final TextBox showlGender = new TextBox();
	final TextBox showlSmoker = new TextBox();
	private final Profile profile;

	public SearchProfilePage(Profile profile) {
		this.profile = profile;
	}

	public void onLoad() {

		/**
		 * Button anlegen zum Anlegen von Suchprofilen
		 */
		final Button addnewSPButton = new Button("<img src='images/add_searchprofiles.png'/>");

		addnewSPButton.setStyleName("buttonmargin");

		/**
		 * Button dem VerticalPanel zuweisen
		 */
		// searchProfilPanel.add(new HTML("<h2>Hallo</h2>"));
		buttonsearchProfilePanel.add(addnewSPButton);

		/**
		 * Ausgabe für die Headline der Suchprofile
		 */
		searchProfilesPanel.add(new HTML("<h3> Deine Suchprofile </h3>"));

		searchprofilesPanel.add(buttonsearchProfilePanel);
		searchprofilesPanel.add(searchProfilesPanel);

		buttonsearchProfilePanel.add(buttonSPpanel);
		searchProfilesPanel.add(showallSPPanel);
		searchProfilesPanel.add(showoneSPPanel);

		/**
		 * Ausgeben aller bestehender Suchprofile
		 */
		partnerboerseVerwaltung.getSearchProfileOf(profile, new AsyncCallback<ArrayList<SearchProfile>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<SearchProfile> allsearchprofilesresult) {
				CellTable<SearchProfile> table = new CellTable<>();

				TextColumn<SearchProfile> heightColumn = new TextColumn<SearchProfile>() {
					@Override
					public String getValue(SearchProfile searchProfile) {
						return searchProfile.getFromHeight() + " - " + searchProfile.getToHeight();
					}
				};
				
				TextColumn<SearchProfile> ageColumn = new TextColumn<SearchProfile>() {
					@Override
					public String getValue(SearchProfile searchProfile) {
						return searchProfile.getFromAge() + " - " + searchProfile.getToAge();
					}
				};
				
				TextColumn<SearchProfile> hairColorColumn = new TextColumn<SearchProfile>() {
					@Override
					public String getValue(SearchProfile searchProfile) {
						return searchProfile.getHairColor().getName();
					}
				};
				
				TextColumn<SearchProfile> confessionColumn = new TextColumn<SearchProfile>() {
					@Override
					public String getValue(SearchProfile searchProfile) {
						return searchProfile.getConfession().getName();
					}
				};
				
				table.addColumn(heightColumn, "Größe");
				table.addColumn(ageColumn, "Alter");
				table.addColumn(hairColorColumn, "Haarfarbe");
				table.addColumn(confessionColumn, "Religion");
				
				table.setRowData(allsearchprofilesresult);
				
				showallSPPanel.add(table);
			}
		});

		/**
		 * Panels dem RootPanel zuweisen
		 */
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(searchprofilesPanel);

		/**
		 * ClickHandler für den Button "Neues Suchprofil anlegen" anlegen, damit
		 * dieser beim Anklicken die Ansicht öffnet damit ein Neues Suchprofil
		 * angelegt werden kann.
		 * 
		 */
		addnewSPButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				showoneSPPanel.clear();
				buttonSPpanel.clear();
				addNewSearchProfile();
			}
		});

	}

	private void addNewSearchProfile() {

		final SearchProfile searchProfile = new SearchProfile();

		showoneSPPanel.add(new HTML("<h3> Neues Suchprofil anlegen </h3>"));

		/**
		 * Panel zum Anlegen von neuen Suchprofilen
		 */
		final FlexTable newSearchProfileTable = new FlexTable();
		newSearchProfileTable.setWidth("200");

		showoneSPPanel.add(newSearchProfileTable);
		/**
		 * FlexTable formatieren
		 */
		newSearchProfileTable.setCellSpacing(10);

		/**
		 * Panels generieren für Größe und Alter
		 */
		final HorizontalPanel heightPanel = new HorizontalPanel();
		final HorizontalPanel agePanel = new HorizontalPanel();

		/**
		 * Widgets für das Suchprofil generieren
		 */
		final Button saveButton = new Button("<img src='images/saveuser.png'/>");
		final ListBox lbHaircolor = new ListBox();
		final ListBox lbConfession = new ListBox();
		final TextBox tHeightfrom = new TextBox();
		final TextBox tHeightto = new TextBox();
		final TextBox tagerfrom = new TextBox();
		final TextBox tageto = new TextBox();
		final RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
		final RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");

		heightPanel.add(tHeightfrom);
		heightPanel.add(tHeightto);
		agePanel.add(tagerfrom);
		agePanel.add(tageto);

		HairColor[] hairColorValue = Profile.HairColor.values();
		for (HairColor hairColor : hairColorValue) {
			lbHaircolor.addItem(hairColor.getName(), hairColor.toString());
		}

		Confession[] confessionValue = Profile.Confession.values();
		for (Confession confession : confessionValue) {
			lbConfession.addItem(confession.getName(), confession.toString());
		}

		final Gender[] genderValues = Profile.Gender.values();
		final RadioButton[] genderRadioButtons = new RadioButton[genderValues.length];
		int i = 4;
		for (int j = 0; j < genderRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("genderGroup", genderValues[j].getName());
			newSearchProfileTable.setWidget(i++, 1, radioButton);
		}

		/**
		 * Table mit Inhalt füllen damit Formular für Suchprofil angelegt werden
		 * kann
		 */
		newSearchProfileTable.setHTML(0, 0, "<div>Größe</div>");
		newSearchProfileTable.setWidget(0, 1, heightPanel);
		newSearchProfileTable.setHTML(1, 0, "<div>Alter</div>");
		newSearchProfileTable.setWidget(1, 1, agePanel);
		newSearchProfileTable.setHTML(2, 0, "<div>Haarfarbe</div>");
		newSearchProfileTable.setWidget(2, 1, lbHaircolor);
		newSearchProfileTable.setHTML(3, 0, "<div>Religion</div>");
		newSearchProfileTable.setWidget(3, 1, lbConfession);
		newSearchProfileTable.setHTML(4, 0, "<div>Geschlecht</div>");

		showoneSPPanel.add(saveButton);

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String ageFromString = tagerfrom.getValue();
				if (ageFromString != null && !ageFromString.isEmpty()) {
					searchProfile.setFromAge(Integer.valueOf(tagerfrom.getValue()));
				}
				String ageToString = tageto.getValue();
				if (ageToString != null && !ageToString.isEmpty()) {
					searchProfile.setToAge(Integer.valueOf(tageto.getValue()));
				}
				String heigthFromString = tHeightfrom.getValue();
				if (heigthFromString != null && !heigthFromString.isEmpty()) {
					searchProfile.setFromHeight(Integer.valueOf(tHeightfrom.getValue()));
				}
				String heigthToString = tageto.getValue();
				if (heigthToString != null && !heigthToString.isEmpty()) {
					searchProfile.setToHeight(Integer.valueOf(tageto.getValue()));
				}
				searchProfile.setHairColor(HairColor.valueOf(lbHaircolor.getValue(lbHaircolor.getSelectedIndex())));
				searchProfile.setConfession(Confession.valueOf(lbConfession.getValue(lbConfession.getSelectedIndex())));
				for (int j = 0; j < genderRadioButtons.length; j++) {
					if (genderRadioButtons[j].getValue()) {
						searchProfile.setGender(genderValues[j]);
						break;
					}
				}

				partnerboerseVerwaltung.save(searchProfile, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Profil gespeichert");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim speichern");
					}
				});
			}
		});

		buttonSPpanel.add(new HTML("<h3> Informationen </h3>"));

	}

}

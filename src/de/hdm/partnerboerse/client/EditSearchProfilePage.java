package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

/**
 * Klasse zum Bearbeiten des Suchrprofiles
 * 
 * @author aliyegokoz
 *
 */
public class EditSearchProfilePage {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/*
	 * Panel anlegen
	 */
	final VerticalPanel editsearchprofilePanel = new VerticalPanel();

	/*
	 * FlexTable zum Anlegen von neuen Suchprofilen
	 */
	final FlexTable newSearchProfileTable = new FlexTable();

	/*
	 * Panels generieren für Größe und Alter
	 */
	final HorizontalPanel heightPanel = new HorizontalPanel();
	final HorizontalPanel agePanel = new HorizontalPanel();

	/*
	 * Widgets für das Suchprofilformular generieren
	 */
	final ListBox lbHaircolor = new ListBox();
	final ListBox lbConfession = new ListBox();
	final TextBox tHeightfrom = new TextBox();
	final TextBox tHeightto = new TextBox();
	final TextBox tagerfrom = new TextBox();
	final TextBox tageto = new TextBox();
	final RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
	final RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
	final ListBox selectionpropertyListboxSP = new ListBox(false);

	/*
	 * Button zum speichern anlegen
	 */
	final Button saveSearchProfileButton = new Button("<img src='images/saveuser.png'/>");

	private final Profile profile;
	private RadioButton[] genderRadioButtons;
	private Gender[] genderValues;

	private SearchProfilePage searchprofilepage;

	public EditSearchProfilePage(Profile profile, SearchProfilePage searchprofilepage) {
		this.profile = profile;
		this.searchprofilepage = searchprofilepage;
	}

	/**
	 * Methode generiert ein Formular, 
	 * welches es ermöglicht bestehende Suchprofile zu bearbeiten
	 * @param searchProfile
	 * @return
	 */
	public Widget editsearchprofile(final SearchProfile searchProfile) {

		/*
		 * Suchprofil headline
		 */
		editsearchprofilePanel.add(new HTML("<h3> Suchprofil bearbeiten </h3>"));

		/*
		 * Tabellenbreite setzten
		 */
		newSearchProfileTable.setWidth("200");

		/*
		 * Tabelle an Panel heften
		 */
		editsearchprofilePanel.add(newSearchProfileTable);

		/*
		 * FlexTable formatieren
		 */
		newSearchProfileTable.setCellSpacing(10);

		/*
		 * Widgets für Von-Bis Größe und Von-Bis Alter an Panels heften damit
		 * sie in der Tabelle zusammen ausgegeben werden können
		 */
		heightPanel.add(tHeightfrom);
		heightPanel.add(tHeightto);
		agePanel.add(tagerfrom);
		agePanel.add(tageto);

		/*
		 * Tabele mit Inhalt zum Bearbeiten ausgeben
		 */
		tHeightfrom.setValue("" + searchProfile.getFromHeight());
		tHeightto.setValue("" + searchProfile.getToHeight());
		tagerfrom.setValue("" + searchProfile.getFromAge());
		tageto.setValue("" + searchProfile.getToAge());

		HairColor[] hairColorValue = Profile.HairColor.values();
		for (HairColor hairColor : hairColorValue) {
			lbHaircolor.addItem(hairColor.getName(), hairColor.toString());
		}
		lbHaircolor.setSelectedIndex(searchProfile.getHairColor().ordinal());

		Confession[] confessionValue = Profile.Confession.values();
		for (Confession confession : confessionValue) {
			lbConfession.addItem(confession.getName(), confession.toString());
		}
		lbConfession.setSelectedIndex(searchProfile.getConfession().ordinal());

		genderValues = Profile.Gender.values();
		genderRadioButtons = new RadioButton[genderValues.length];
		int i = 4;
		for (int j = 0; j < genderRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("genderGroup", genderValues[j].getName());
			newSearchProfileTable.setWidget(i++, 1, radioButton);
			if (genderValues[j].equals(searchProfile.getGender())) {
				radioButton.setValue(true);
			}
			genderRadioButtons[j] = radioButton;
		}

		/*
		 * Table mit Inhalt füllen damit Formular für Suchprofil angelegt werden
		 * kann
		 */
		newSearchProfileTable.setHTML(0, 0, "<div>Größe in cm</div>");
		newSearchProfileTable.setWidget(0, 1, heightPanel);
		newSearchProfileTable.setHTML(1, 0, "<div>Alter</div>");
		newSearchProfileTable.setWidget(1, 1, agePanel);
		newSearchProfileTable.setHTML(2, 0, "<div>Haarfarbe</div>");
		newSearchProfileTable.setWidget(2, 1, lbHaircolor);
		newSearchProfileTable.setHTML(3, 0, "<div>Religion</div>");
		newSearchProfileTable.setWidget(3, 1, lbConfession);
		newSearchProfileTable.setHTML(4, 0, "<div>Geschlecht</div>");

		editsearchprofilePanel.add(saveSearchProfileButton);

		/*
		 * OnClick für den Button, damit SuchProfile gespeichert werden könnne
		 */
		saveSearchProfileButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				savesearchProfile(searchProfile);
			}
		});

		return editsearchprofilePanel;
	}

	/**
	 * Methode zum Speichern des Suchprofiles
	 * @param searchProfile
	 */
	public void savesearchProfile(final SearchProfile searchProfile) {

		/*
		 * Eingaben werden überprüft
		 */
		String ageFromString = tagerfrom.getValue();
		if (ageFromString != null && !ageFromString.isEmpty()) {
			try {
				Integer valueOfageFrom = Integer.valueOf(tagerfrom.getValue());
				if (valueOfageFrom > 110 || valueOfageFrom < 18) {
					Window.alert("Bitte eine Gültige Zahl eingeben. Die Zahl:" + valueOfageFrom + " ist ungültig.");
					return;
				}
				searchProfile.setFromAge(valueOfageFrom);

				String ageToString = tageto.getValue();
				if (ageToString != null && !ageToString.isEmpty()) {
						Integer valueOfageTo = Integer.valueOf(tageto.getValue());
						if (valueOfageTo > 110 || valueOfageTo < 18 || valueOfageTo < valueOfageFrom) {
							Window.alert(
									"Bitte eine gültige Zahl eingeben. Die Zahl:" + valueOfageTo + " ist ungültig.");
							return;
						}
						searchProfile.setToAge(valueOfageTo);
				}

			} catch (NumberFormatException e) {
				Window.alert("Bitte eine Gültige Zahl eingeben.");
				return;
			}
		}
		
		String heigthFromString = tHeightfrom.getValue();
		if (heigthFromString != null && !heigthFromString.isEmpty()) {
			try {
				Integer valueOfheigthFrom = Integer.valueOf(tHeightfrom.getValue());
				if (valueOfheigthFrom > 230 || valueOfheigthFrom < 120) {
					Window.alert("Bitte eine gültige Zahl eingeben. Die Zahl:" + valueOfheigthFrom + " ist ungültig.");
					return;
				}
				searchProfile.setFromHeight(valueOfheigthFrom);
				
				String heigthToString = tHeightto.getValue();
				if (heigthToString != null && !heigthToString.isEmpty()) {
						Integer valueOfheightTo = Integer.valueOf(tHeightto.getValue());
						if (valueOfheightTo > 230 || valueOfheightTo < 120 || valueOfheightTo < valueOfheigthFrom) {
							Window.alert("Bitte eine gültige Zahl eingeben. Die Zahl:" + valueOfheightTo + "ist ungültig.");
							return;
						}
					
					searchProfile.setToHeight(Integer.valueOf(tHeightto.getValue()));
				}
				
			} catch (NumberFormatException e) {
				Window.alert("Bitte eine gültige Zahl eingeben.");
				return;
			}
		}

		searchProfile.setHairColor(HairColor.valueOf(lbHaircolor.getValue(lbHaircolor.getSelectedIndex())));
		searchProfile.setConfession(Confession.valueOf(lbConfession.getValue(lbConfession.getSelectedIndex())));
		for (int j = 0; j < genderRadioButtons.length; j++) {
			if (genderRadioButtons[j].getValue()) {
				searchProfile.setGender(genderValues[j]);
				break;
			}
		}

		/*
		 * Suchprofil wird gespeichert
		 */
		partnerboerseVerwaltung.save(searchProfile, new AsyncCallback<SearchProfile>() {

			@Override
			public void onSuccess(SearchProfile result) {

				if (searchProfile.getId() == 0) {
					searchprofilepage.dataProvider.getList().add(result);
				}
				searchprofilepage.dataProvider.flush();
				searchprofilepage.dataProvider.refresh();
				searchprofilepage.table.redraw();
				Window.alert("Profil gespeichert");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim speichern");
			}
		});
	}
}

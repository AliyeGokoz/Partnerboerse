package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Profile.Orientation;

public class AddProfilePage {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/**
	 * Panel anlegen für die Ausgabe des Profilformulares
	 */
	final HorizontalPanel profilePanel = new HorizontalPanel();

	/**
	 * Button zum speichern des Profiles
	 */
	final Button saveProfileButton = new Button("<img src='images/save.png'/>");

	/**
	 * Tabellen für das Profilformular
	 */
	final FlexTable addnewProfileTable = new FlexTable();
	final FlexTable addnewProfileTable2 = new FlexTable();

	/**
	 * Textbox Widgets anlegen für die Eingaben des Users
	 */
	final TextBox tFirstname = new TextBox();
	final TextBox tLastname = new TextBox();
	final TextBox tEmail = new TextBox();
	final TextBox tHeight = new TextBox();

	/**
	 * // * Listbox Widgets anlegen für Haarfarbe und Religion //
	 */
	final ListBox lbHaircolor = new ListBox();
	final ListBox lbConfession = new ListBox();

	/**
	 * RadioButtons für Status: Raucher/Nicht Raucher
	 */
	final RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
	final RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");

	/**
	 * DatePicker zum Eintragen des Geburtstages
	 */
	final DatePicker datePicker = new DatePicker();

	final Gender[] genderValues = Profile.Gender.values();
	final Orientation[] orientationValues = Profile.Orientation.values();
	final RadioButton[] genderRadioButtons = new RadioButton[genderValues.length];
	final RadioButton[] orientationRadioButtons = new RadioButton[orientationValues.length];

	public Widget addnewProfile(final Profile profile) {

		/**
		 * Style Tabel
		 */
		addnewProfileTable.setWidth("200");
		addnewProfileTable2.setWidth("200");
		addnewProfileTable2.setStyleName("hcontent2");
		addnewProfileTable.setStyleName("hcontent");

		/**
		 * FlexTable formatieren
		 */
		addnewProfileTable.setCellSpacing(10);
		addnewProfileTable2.setCellSpacing(10);

		/**
		 * Wenn Profil bereits vorhanden, dann ausfüllen
		 */
		tFirstname.setValue(profile.getFirstName());
		tLastname.setValue(profile.getLastName());
		tEmail.setEnabled(false);
		tEmail.setValue(profile.geteMail());
		tHeight.setValue("" + profile.getHeight());

		HairColor[] hairColorValue = Profile.HairColor.values();
		for (HairColor hairColor : hairColorValue) {
			lbHaircolor.addItem(hairColor.getName(), hairColor.toString());
		}
		lbHaircolor.setSelectedIndex(profile.getHairColor().ordinal());

		Confession[] confessionValue = Profile.Confession.values();
		for (Confession confession : confessionValue) {
			lbConfession.addItem(confession.getName(), confession.toString());
		}
		lbConfession.setSelectedIndex(profile.getConfession().ordinal());

		int i = 2;
		for (int j = 0; j < genderRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("genderGroup", genderValues[j].getName());
			addnewProfileTable.setWidget(i++, 1, radioButton);
			if (genderValues[j].equals(profile.getGender())) {
				radioButton.setValue(true);
			}
			genderRadioButtons[j] = radioButton;
		}

		i = 2 + genderValues.length;
		for (int j = 0; j < orientationRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("orientationGroup", orientationValues[j].getName());
			addnewProfileTable.setWidget(i++, 1, radioButton);
			if (orientationValues[j].equals(profile.getOrientation())) {
				radioButton.setValue(true);
			}
			orientationRadioButtons[j] = radioButton;
		}

		Rbsmokeyes.setValue(profile.isSmoker());
		Rbsmokeno.setValue(!profile.isSmoker());

		datePicker.setYearAndMonthDropdownVisible(true);
		datePicker.setYearArrowsVisible(true);
		datePicker.setVisibleYearCount(150);
		datePicker.setValue(profile.getDateOfBirth());

		/**
		 * FlexTable mit Inhalt füllen = Userprofil Formular
		 */
		addnewProfileTable.setHTML(0, 0, "<h2>Profil anlegen</h2>");
		addnewProfileTable.setHTML(0, 0, "<div>Vorname</div>");
		addnewProfileTable.setWidget(0, 1, tFirstname);
		addnewProfileTable.setHTML(1, 0, "<div>Geburtsdatum</div>");
		addnewProfileTable.setWidget(1, 1, datePicker);
		addnewProfileTable.setHTML(2, 0, "<div>Geschlecht</div>");
		addnewProfileTable.setHTML(2 + genderValues.length, 0, "<div>Orientierung</div>");

		addnewProfileTable2.setHTML(0, 0, "<div>Nachname</div>");
		addnewProfileTable2.setWidget(0, 1, tLastname);
		addnewProfileTable2.setHTML(1, 0, "<div>Email</div>");
		addnewProfileTable2.setWidget(1, 1, tEmail);
		addnewProfileTable2.setHTML(2, 0, "<div>Größe in cm</div>");
		addnewProfileTable2.setWidget(2, 1, tHeight);
		addnewProfileTable2.setHTML(3, 0, "<div>Haarfarbe</div>");
		addnewProfileTable2.setWidget(3, 1, lbHaircolor);
		addnewProfileTable2.setHTML(4, 0, "<div>Religion</div>");
		addnewProfileTable2.setWidget(4, 1, lbConfession);
		addnewProfileTable2.setHTML(5, 0, "<div>Raucher</div>");
		addnewProfileTable2.setWidget(5, 1, Rbsmokeyes);
		addnewProfileTable2.setWidget(6, 1, Rbsmokeno);

		profilePanel.add(addnewProfileTable);
		profilePanel.add(addnewProfileTable2);
		profilePanel.add(saveProfileButton);

		saveProfile(profile);

		return profilePanel;
	}

	public void saveProfile(final Profile profile) {
		saveProfileButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String regEx = "[^0-9]*";
				// Überprüfen ob ein Name eingegeben wurde und ob diese nur aus
				// Buchstaben besteht
				if (!tFirstname.getValue().isEmpty()) {
					String namevalue = tFirstname.getValue();
					if (namevalue.matches(regEx)) {
						profile.setFirstName(tFirstname.getValue());
					} else {
						Window.alert("Bitte tragen Sie einen gültigen Namen ein.Nur Buchstaben gültig.");
						return;
					}
				} else {
					Window.alert("Bitte tragen Sie einen gültigen Namen ein.");
					return;
				}

				if (!tLastname.getValue().isEmpty()) {
					String lastnamevalue = tLastname.getValue();
					if (lastnamevalue.matches(regEx)) {
						profile.setLastName(tLastname.getValue());
					} else {
						Window.alert("Bitte tragen Sie einen gültigen Nachnamen ein. Nur Buchstaben gültig.");
						return;
					}

				} else {
					Window.alert("Bitte tragen Sie einen gültigen Nachnamen ein.");
					return;
				}

				profile.seteMail(tEmail.getValue());
				profile.setDateOfBirth(datePicker.getValue());
				if (!tHeight.getValue().isEmpty()) {
					try {
						int parseInt = Integer.parseInt(tHeight.getValue());
						if (parseInt > 250 || parseInt < 100) {
							Window.alert(
									"Bitte trage eine gültige Größe ein. Die Größe: " + parseInt + " ist ungültig.");
							return;
						}
						profile.setHeight(Integer.parseInt(tHeight.getValue()));
					} catch (NumberFormatException ex) {
						Window.alert("Bitte tragen sie eine gültige Größe ein.");
						return;
					}
				} else {
					Window.alert("Bitte tragen sie eine gültige Größe ein.");
					return;
				}

				for (int j = 0; j < genderRadioButtons.length; j++) {
					if (genderRadioButtons[j].getValue()) {
						profile.setGender(genderValues[j]);
						break;
					}
				}
				
				for (int j = 0; j < orientationRadioButtons.length; j++) {
					if (orientationRadioButtons[j].getValue()) {
						profile.setOrientation(orientationValues[j]);
						break;
					}
				}

				profile.setSmoker(Rbsmokeyes.getValue());
				profile.setConfession(Confession.valueOf(lbConfession.getValue(lbConfession.getSelectedIndex())));
				profile.setHairColor(HairColor.valueOf(lbHaircolor.getValue(lbHaircolor.getSelectedIndex())));

				partnerboerseVerwaltung.save(profile, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Profil gespeichert");

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Profil konnte nicht gespeichert werden. Bitte füllen Sie alle Felder aus");

					}
				});
			}
		});
	}
}

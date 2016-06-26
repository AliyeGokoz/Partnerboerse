package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public class SearchProfileFormular{

	private RadioButton[] genderRadioButtons;
	private Gender[] genderValues;
	//private SearchProfile searchProfile;
	
	public Widget searchprofileformular(){
		
		/**
		 * Panel für das Formular
		 */
		final VerticalPanel searchProfilePanel = new VerticalPanel();
		
		/**
		 * FlexTable zum Anlegen von neuen Suchprofilen
		 */
		final FlexTable newSearchProfileTable = new FlexTable();
		
		/**
		 * Panels generieren für Größe und Alter
		 */
		final HorizontalPanel heightPanel = new HorizontalPanel();
		final HorizontalPanel agePanel = new HorizontalPanel();
		
		/**
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
		
		/**
		 * Button zum speichern anlegen
		 */
		final Button saveButton = new Button("<img src='images/saveuser.png'/>");
		
		/*
		 * Tabellenbreite setzten
		 */
		newSearchProfileTable.setWidth("200");
		
		/*
		 * Tabelle an Panel heften 
		 */
		searchProfilePanel.add(newSearchProfileTable);
		
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
		
		HairColor[] hairColorValue = Profile.HairColor.values();
		for (HairColor hairColor : hairColorValue) {
			lbHaircolor.addItem(hairColor.getName(), hairColor.toString());
		}

		Confession[] confessionValue = Profile.Confession.values();
		for (Confession confession : confessionValue) {
			lbConfession.addItem(confession.getName(), confession.toString());
		}

		genderValues = Profile.Gender.values();
		genderRadioButtons = new RadioButton[genderValues.length];
		int i = 4;
		for (int j = 0; j < genderRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("genderGroup", genderValues[j].getName());
			newSearchProfileTable.setWidget(i++, 1, radioButton);
			genderRadioButtons[j] = radioButton;
		}
		
		/*
		 * Tabelle mit Inhalt füllen damit Formular für Suchprofil angelegt werden
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
		
		searchProfilePanel.add(saveButton);
		
		return searchProfilePanel;
//		RootPanel.get("Content").add(searchProfilePanel);
		
		
	}
}

package de.hdm.partnerboerse.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;

public class ShowOneSearchProfile {

	/**
	 * Panel anlegen
	 */
	final VerticalPanel searchprofilepanel = new VerticalPanel();

	/**
	 * Label anlegen für die Ausgabe des Suchprofiles
	 */
	final Label nameofSp = new Label();
	final Label heigtofSp = new Label();
	final Label ageofSp = new Label();
	final Label haircolorofSp = new Label();
	final Label confessionofSp = new Label();
	final Label genderofSp = new Label();

	final FlexTable table = new FlexTable();

	private SearchProfilePage searchprofilepage;
	
	public ShowOneSearchProfile(SearchProfilePage searchprofilepage) {
		this.searchprofilepage = searchprofilepage;
	}

	public Widget showSearchProfile(final SearchProfile searchProfile) {

		// Style FlexTable
		table.setWidth("200");
		table.setCellSpacing(10);
		
		//FlexTable mit Inhalt füllen
		table.setHTML(0, 0, "<div>Name deines Suchprofiles</div>");
		table.setWidget(0, 1, nameofSp);
		table.setHTML(1, 0, "<div>Größe in cm</div>");
		table.setWidget(1, 1, heigtofSp);
		table.setHTML(2, 0, "<div>Alter</div>");
		table.setWidget(2, 1, ageofSp);
		table.setHTML(3, 0, "<div>Haarfarbe</div>");
		table.setWidget(3, 1, haircolorofSp);
		table.setHTML(4, 0, "<div>Religion</div>");
		table.setWidget(4, 1, confessionofSp);
		table.setHTML(5, 0, "<div>Geschlecht</div>");
		table.setWidget(5, 1, genderofSp);

		// FlexTable dem Panel zuweißen
		searchprofilepanel.add(table);

		return searchprofilepanel;
	}

}

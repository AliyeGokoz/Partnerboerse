package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.SearchProfile;

/**
 * Klasse die das Suchprofil ausgibt,
 * für die Ansicht
 * @author aliyegokoz
 * @author alena gerlinskaja
 *
 */
public class ShowOneSearchProfile {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	
	/*
	 * Panel anlegen
	 */
	final VerticalPanel searchprofilepanel = new VerticalPanel();

	/*
	 * Label anlegen für die Ausgabe des Suchprofiles
	 */
	final Label nameofSp = new Label();
	final Label heightofSp = new Label();
	final Label ageofSp = new Label();
	final Label haircolorofSp = new Label();
	final Label confessionofSp = new Label();
	final Label genderofSp = new Label();

	/*
	 * FlexTable für die Ausgabe
	 */
	final FlexTable table = new FlexTable();
	
	/*
	 * CellList für die Ausgabe der Informationen
	 */
	final CellTable<Info> infoTable = new CellTable<>();
	ListDataProvider<Info> dataProvider = new ListDataProvider<>();
	
	
	private SearchProfilePage searchprofilepage;

	public ShowOneSearchProfile(SearchProfilePage searchprofilepage) {
		this.searchprofilepage = searchprofilepage;
	}

	/**
	 * Methode, welche das Suchprofil ausgibt 
	 * bei onClick auf den Button: Suchprofil ansehen
	 * @param searchProfile
	 * @return VerticalPanel
	 */
	public Widget showSearchProfile(final SearchProfile searchProfile) {

		/*
		 * Style 
		 */
//		searchprofilepanel.setStyleName("showSp");
		nameofSp.setStyleName("headlinesp");
		
		/*
		 * Suchprofil Werte übergeben
		 */
		String height = searchProfile.getFromHeight() + " - " + searchProfile.getToHeight();
		String age = searchProfile.getFromAge() + " - " + searchProfile.getToAge();

		nameofSp.setText(searchProfile.getName());
		heightofSp.setText(height);
		ageofSp.setText(age);
		haircolorofSp.setText(searchProfile.getHairColor().getName());
		confessionofSp.setText(searchProfile.getConfession().getName());

		if (searchProfile.getGender() == null) {
			genderofSp.setText(" - ");
		}else{
			genderofSp.setText(searchProfile.getGender().getName());
		}
		
		/*
		 * Zugriff auf die Infos die User zum
		 * Suchprofil hinzugefügt hat
		 */
		partnerboerseVerwaltung.getInfosOf(searchProfile, new AsyncCallback<ArrayList<Info>>() {
			
			@Override
			public void onSuccess(ArrayList<Info> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				dataProvider.flush();
				dataProvider.refresh();
				infoTable.redraw();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Infos können nicht ausgegeben werden.");
				
			}
		});
		
		
		/*
		 * CellList für die Ausgabe der Infos für das Suchprofil
		 */
		dataProvider.addDataDisplay(infoTable);

		TextColumn<Info> nameInfo = new TextColumn<Info>() {

			@Override
			public String getValue(Info info) {
				if (info.getDescription() != null) {
					return info.getDescription().getTextualDescriptionForSearchProfile();
				} else {
					return info.getSelection().getTextualDescriptionForSearchProfile();
				}
			}
		};

		TextColumn<Info> valueColumn = new TextColumn<Info>() {

			@Override
			public String getValue(Info info) {
				return info.getInformationValue();
			}
		};
		
		/*
		 * Columns der Tabelle zuweisen, für die Ausgabe der Informationen
		 */
		infoTable.addColumn(nameInfo);
		infoTable.addColumn(valueColumn);

		/*
		 * Style FlexTable
		 */
		table.setWidth("200");
		table.setCellSpacing(10);

		/*
		 * FlexTable mit Inhalt füllen
		 */
		table.setWidget(0, 0, nameofSp);
		table.setHTML(1, 0, "<div>Größe in cm</div>");
		table.setWidget(1, 1, heightofSp);
		table.setHTML(2, 0, "<div>Alter</div>");
		table.setWidget(2, 1, ageofSp);
		table.setHTML(3, 0, "<div>Haarfarbe</div>");
		table.setWidget(3, 1, haircolorofSp);
		table.setHTML(4, 0, "<div>Religion</div>");
		table.setWidget(4, 1, confessionofSp);
		table.setHTML(5, 0, "<div>Geschlecht</div>");
		table.setWidget(5, 1, genderofSp);

		/*
		 *  FlexTable dem Panel zuweißen
		 */
		searchprofilepanel.add(table);
		searchprofilepanel.add(infoTable);

		return searchprofilepanel;
	}

}

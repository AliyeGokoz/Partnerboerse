package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;

/**
 * Klasse für das Suchprofilformular
 * @author aliyegokoz
 *
 */
public class SearchProfilePage extends VerticalPanel {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/*
	 * VerticalPanel für die Widgets anlegen
	 */
	final HorizontalPanel searchprofilesPanel = new HorizontalPanel();
	final VerticalPanel buttonsearchProfilePanel = new VerticalPanel();
	final VerticalPanel searchProfilesPanel = new VerticalPanel();
	final VerticalPanel buttonSPpanel = new VerticalPanel();
	final VerticalPanel allsp = new VerticalPanel();
	final VerticalPanel showallSPPanel = new VerticalPanel();
	final VerticalPanel showoneSPPanel = new VerticalPanel();

	/*
	 * List für die Ausgabe der Searchprofiles
	 */
	CellTable<SearchProfile> table = new CellTable<>();
	ListDataProvider<SearchProfile> dataProvider = new ListDataProvider<>();

	/*
	 * Buttons anlegen zum Anlegen, Löschen, Bearbeiten
	 */
	final Button showSP = new Button("Suchprofil ansehen");
	final Button addnewSPButton = new Button("Hinzufügen");
	final Button editSpButton = new Button("Bearbeiten");
	final Button deleteSpButton = new Button("Löschen");
	final Button addInfotoSP = new Button("Zusätzliche Informationen");

	private final Profile profile;

	private SearchProfile searchProfile;

	public SearchProfilePage(Profile profile) {
		this.profile = profile;
	}

	@Override
	public void onLoad() {

		/*
		 * style panels
		 */
		buttonsearchProfilePanel.setStyleName("searchprofileBPanel");
		showoneSPPanel.setStyleName("addsppanel");
		allsp.setStyleName("spall");

		dataProvider.addDataDisplay(table);

		/*
		 * Ausgabe für die Headline der Suchprofile
		 */
		allsp.add(new HTML("<h3> Deine Suchprofile </h3>"));

		/*
		 * Tabelen Spalten für die Suchprofile
		 */
		TextColumn<SearchProfile> nameColumn = new TextColumn<SearchProfile>() {
			@Override
			public String getValue(SearchProfile searchProfile) {
				return searchProfile.getName();
			}
		};

		/*
		 * Spalten der Tabelle zuweisen
		 */
		table.addColumn(nameColumn);

		/*
		 * Tabele aller Suchprofile dem ersten Panel zuweisen
		 */
		showallSPPanel.add(table);
		allsp.add(showallSPPanel);

		/*
		 * Style Button
		 */
		showallSPPanel.setStyleName("infospanelprof");
		showSP.setStyleName("buttonmargin");
		addnewSPButton.setStyleName("buttonmargin");
		editSpButton.setStyleName("buttonmargin");
		deleteSpButton.setStyleName("buttonmargin");
		addInfotoSP.setStyleName("buttonmargin");

		/*
		 * Button dem VerticalPanel zuweisen
		 */
		buttonsearchProfilePanel.add(showSP);
		buttonsearchProfilePanel.add(addnewSPButton);
		buttonsearchProfilePanel.add(editSpButton);
		buttonsearchProfilePanel.add(deleteSpButton);
		buttonsearchProfilePanel.add(addInfotoSP);

		/*
		 * Panels werden dem 'searchprofilesPanel' angehengt
		 */
		searchprofilesPanel.add(buttonsearchProfilePanel);
		searchprofilesPanel.add(allsp);
		searchprofilesPanel.add(showoneSPPanel);

		/*
		 * Methode aufrufen: CellList SingleSelectionModel zuweisen
		 */
		addselectionSearchProfile();

		/*
		 * Ausgeben aller bestehender Suchprofile
		 */
		partnerboerseVerwaltung.getSearchProfileOf(profile, new AsyncCallback<ArrayList<SearchProfile>>() {

			@Override
			public void onFailure(Throwable caught) {
			

			}

			@Override
			public void onSuccess(ArrayList<SearchProfile> allsearchprofilesresult) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(allsearchprofilesresult);
				dataProvider.flush();
				dataProvider.refresh();
				table.redraw();
			}
		});

		/*
		 * Panels dem RootPanel zuweisen
		 */
		RootPanel.get("Content").add(searchprofilesPanel);

		/*
		 * ClickHandler für den Button "Neues Suchprofil anlegen" anlegen, damit
		 * dieser beim Anklicken die Ansicht öffnet damit ein Neues Suchprofil
		 * angelegt werden kann.
		 * 
		 */
		addnewSPButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showoneSPPanel.clear();
				AddSearchProfilePage addSProfile = new AddSearchProfilePage(profile, SearchProfilePage.this);
				showoneSPPanel.add(addSProfile.addsearchProfile());
			}
		});

	}

	/**
	 * Weist der CellList eine SingleSelectionModel zu, so können die Daten dann
	 * onClick bearbeitet oder gelöscht werden.
	 */
	private void addselectionSearchProfile() {

		final SingleSelectionModel<SearchProfile> selectioSProfile = new SingleSelectionModel<SearchProfile>();
		table.setSelectionModel(selectioSProfile);
		
		/*
		 * 
		 */
		showSP.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();

				if (selectedsp != null) {
					showoneSPPanel.clear();
					searchProfile = selectedsp;
					ShowOneSearchProfile showSP = new ShowOneSearchProfile(SearchProfilePage.this);
					showoneSPPanel.add(showSP.showSearchProfile(selectedsp));
				}
				
			}
		});
		
		/*
		 * ClickHandler für den Button "Bearbeiten" anlegen, damit dieser beim
		 * Anklicken die Ansicht öffnet damit das Suchprofil überarbeitet werden
		 * kann
		 */
		editSpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();
				if (selectedsp != null) {
					showoneSPPanel.clear();
					searchProfile = selectedsp;
					EditSearchProfilePage editSProfile = new EditSearchProfilePage(profile, SearchProfilePage.this);
					showoneSPPanel.add(editSProfile.editsearchprofile(selectedsp));
				}
			}
		});

		/**
		 * ClickHandler für den Button "Löschen" anlegen, damit dieser beim
		 * Anklicken die Ansicht öffnet damit ein Neues Suchprofil angelegt
		 * werden kann.
		 * 
		 */
		deleteSpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();
				if (selectedsp != null) {
					deleteSearchProfile(selectedsp);
				}

			}
		});

		addInfotoSP.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();
				if (selectedsp != null) {
					searchprofilesPanel.clear();
					searchProfile = selectedsp;
					AddInfoToSearchProfile addinfo = new AddInfoToSearchProfile();
					searchprofilesPanel.add(addinfo.addInfo(selectedsp));
				}
			}
		});

	}

	/**
	 * Methode zum Löschen der Suchprofile, das markierte Suchprofil wird
	 * gelöscht und die CellList wird aktualisiert.
	 * 
	 * @param searchProfile
	 */
	public void deleteSearchProfile(final SearchProfile searchProfile) {
		partnerboerseVerwaltung.delete(searchProfile, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				

			}

			@Override
			public void onSuccess(Void result) {
				dataProvider.getList().remove(searchProfile);
				dataProvider.flush();
				dataProvider.refresh();
				table.redraw();
			}
		});

	}
}

package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
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
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;

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

	/**
	 * List für die Ausgabe der Searchprofiles
	 */
	CellTable<SearchProfile> table = new CellTable<>();
	ListDataProvider<SearchProfile> dataProvider = new ListDataProvider<>();

	/**
	 * Buttons anlegen zum Anlegen, Löschen, Bearbeiten
	 */
	final Button addnewSPButton = new Button("<img src='images/add-sp-icon.png'/>");
	final Button editSpButton = new Button("<img src='images/edit-sp-icon.png'/>");
	final Button deleteSpButton = new Button("<img src='images/delete-sp-icon.png'/>");
	final Button addInfotoSP = new Button("Info hinzufügen");

	private final Profile profile;

	private SearchProfile searchProfile;

	public SearchProfilePage(Profile profile) {
		this.profile = profile;
	}

	@Override
	public void onLoad() {

		/**
		 * style panels
		 */
		buttonsearchProfilePanel.setStyleName("searchprofileBPanel");
		showoneSPPanel.setStyleName("addsppanel");

		dataProvider.addDataDisplay(table);

		/**
		 * Ausgabe für die Headline der Suchprofile
		 */
		showallSPPanel.add(new HTML("<h3> Deine Suchprofile </h3>"));

		/**
		 * Tabelen Spalten für die Suchprofile
		 */

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

		TextColumn<SearchProfile> genderColumn = new TextColumn<SearchProfile>() {
			@Override
			public String getValue(SearchProfile searchProfile) {
				if (searchProfile.getGender() != null) {
					return searchProfile.getGender().getName();
				}
				return "";
			}
		};

		/**
		 * Spalten der Tabelle zuweisen
		 */
		table.addColumn(heightColumn, "Größe" + " von - bis");
		table.addColumn(ageColumn, "Alter" + " von - bis");
		table.addColumn(hairColorColumn, "Haarfarbe");
		table.addColumn(confessionColumn, "Religion");
		table.addColumn(genderColumn, "Geschlecht");

		/**
		 * Tabele aller Suchprofile dem ersten Panel zuweisen
		 */
		showallSPPanel.add(table);

		/**
		 * Button anlegen zum Anlegen von Suchprofilen
		 */

		/*
		 * Style
		 */
		addnewSPButton.setStyleName("buttonmargin");
		editSpButton.setStyleName("buttonmargin");
		deleteSpButton.setStyleName("buttonmargin");

		/**
		 * Button dem VerticalPanel zuweisen
		 */
		// searchProfilPanel.add(new HTML("<h2>Hallo</h2>"));
		buttonsearchProfilePanel.add(addnewSPButton);
		buttonsearchProfilePanel.add(editSpButton);
		buttonsearchProfilePanel.add(deleteSpButton);
		buttonsearchProfilePanel.add(addInfotoSP);

		/*
		 * Panels werden dem 'searchprofilesPanel' angehengt
		 */
		searchprofilesPanel.add(buttonsearchProfilePanel);
		searchprofilesPanel.add(showallSPPanel);
		searchprofilesPanel.add(showoneSPPanel);

		// searchProfilesPanel.add(showallSPPanel);
		// searchProfilesPanel.add(showoneSPPanel);

		/**
		 * Der CellList SingleSelectionModel zuweisen
		 */
		addselectionSearchProfile();

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
				dataProvider.getList().clear();
				dataProvider.getList().addAll(allsearchprofilesresult);
				dataProvider.flush();
				dataProvider.refresh();
				table.redraw();
			}
		});

		/**
		 * Panels dem RootPanel zuweisen
		 */
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
				searchprofilesPanel.clear();
				AddSearchProfilePage addSProfile = new AddSearchProfilePage(profile, SearchProfilePage.this);
				searchprofilesPanel.add(addSProfile.addsearchProfile());
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

		/**
		 * ClickHandler für den Button "Bearbeiten" anlegen, damit dieser beim
		 * Anklicken die Ansicht öffnet damit das Suchprofil überarbeitet werden
		 * kann
		 */
		editSpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();
				if (selectedsp != null) {
					searchprofilesPanel.clear();
					searchProfile = selectedsp;
					EditSearchProfilePage editSProfile = new EditSearchProfilePage(profile, SearchProfilePage.this);
					searchprofilesPanel.add(editSProfile.editsearchprofile(selectedsp));
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
					searchprofilesPanel.add(addinfo.addInfo());
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				dataProvider.getList().remove(searchProfile);
				dataProvider.flush();
				dataProvider.refresh();
				table.redraw();
				Window.alert("Suchprofil wurde gelöscht !");

			}
		});

	}
}

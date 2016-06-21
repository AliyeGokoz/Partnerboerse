package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
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
	CellTable<SearchProfile> table = new CellTable<>();
	ListDataProvider<SearchProfile> dataProvider = new ListDataProvider<>();

	final Button addnewSPButton = new Button("<img src='images/add_searchprofiles.png'/>");
	final Button editSpButton = new Button("bearbeiten");
	final Button deleteSpButton = new Button("löschen");

	/**
	 * Panel zum Anlegen von neuen Suchprofilen
	 */
	final FlexTable newSearchProfileTable = new FlexTable();

	/**
	 * Panels generieren für Größe und Alter
	 */
	final HorizontalPanel heightPanel = new HorizontalPanel();
	final HorizontalPanel agePanel = new HorizontalPanel();

	/**
	 * Widgets für das Suchprofil generieren
	 */

	final ListBox lbHaircolor = new ListBox();
	final ListBox lbConfession = new ListBox();
	final TextBox tHeightfrom = new TextBox();
	final TextBox tHeightto = new TextBox();
	final TextBox tagerfrom = new TextBox();
	final TextBox tageto = new TextBox();
	final RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
	final RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");

	private final Profile profile;
	private RadioButton[] genderRadioButtons;
	private Gender[] genderValues;
	
	private SearchProfile searchProfile;

	public SearchProfilePage(Profile profile) {
		this.profile = profile;
	}

	@Override
	public void onLoad() {
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		dataProvider.addDataDisplay(table);

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

		showallSPPanel.add(table);

		/**
		 * Button anlegen zum Anlegen von Suchprofilen
		 */

		addnewSPButton.setStyleName("buttonmargin");

		/**
		 * Button dem VerticalPanel zuweisen
		 */
		// searchProfilPanel.add(new HTML("<h2>Hallo</h2>"));
		buttonsearchProfilePanel.add(addnewSPButton);
		buttonsearchProfilePanel.add(editSpButton);
		buttonsearchProfilePanel.add(deleteSpButton);

		/**
		 * Ausgabe für die Headline der Suchprofile
		 */
		searchProfilesPanel.add(new HTML("<h3> Deine Suchprofile </h3>"));

		searchprofilesPanel.add(buttonsearchProfilePanel);
		searchprofilesPanel.add(searchProfilesPanel);

		buttonsearchProfilePanel.add(buttonSPpanel);
		searchProfilesPanel.add(showallSPPanel);
		searchProfilesPanel.add(showoneSPPanel);

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
				// showoneSPPanel.getElement().setInnerHTML("");
				searchProfile = new SearchProfile();
				searchProfile.setProfile(profile);
				addNewSearchProfile();
				showoneSPPanel.setVisible(true);
			}
		});
		
		createForm();

	}

	private void createForm() {

		// showoneSPPanel.getElement().setInnerHTML("");
		showoneSPPanel.add(new HTML("<h3> Suchprofil bearbeiten </h3>"));

		newSearchProfileTable.setWidth("200");

		showoneSPPanel.add(newSearchProfileTable);
		/**
		 * FlexTable formatieren
		 */
		newSearchProfileTable.setCellSpacing(10);

		heightPanel.add(tHeightfrom);
		heightPanel.add(tHeightto);
		agePanel.add(tagerfrom);
		agePanel.add(tageto);

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

		final Button saveButton = new Button("<img src='images/saveuser.png'/>");
		showoneSPPanel.add(saveButton);

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				savesearchProfile(searchProfile);
			}
		});
		
		showoneSPPanel.setVisible(false);
	}

	private void addselectionSearchProfile() {

		final SingleSelectionModel<SearchProfile> selectioSProfile = new SingleSelectionModel<SearchProfile>();
		table.setSelectionModel(selectioSProfile);

		editSpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();
				if (selectedsp != null) {
					searchProfile = selectedsp;
					editSearchProfile(selectedsp);
					showoneSPPanel.setVisible(true);
				}
			}
		});

		deleteSpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final SearchProfile selectedsp = selectioSProfile.getSelectedObject();
				if (selectedsp != null) {
					deleteSearchProfile(selectedsp);
				}

			}
		});

	}

	private void addNewSearchProfile() {

		

		tagerfrom.setValue("");
		tageto.setValue("");
		tHeightfrom.setValue("");
		tHeightto.setValue("");
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

		

	}

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

	public void editSearchProfile(final SearchProfile searchProfile) {

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

		final Gender[] genderValues = Profile.Gender.values();
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

	}

	public void savesearchProfile(final SearchProfile searchProfile) {

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
		String heigthToString = tHeightto.getValue();
		if (heigthToString != null && !heigthToString.isEmpty()) {
			searchProfile.setToHeight(Integer.valueOf(tHeightto.getValue()));
		}
		searchProfile.setHairColor(HairColor.valueOf(lbHaircolor.getValue(lbHaircolor.getSelectedIndex())));
		searchProfile.setConfession(Confession.valueOf(lbConfession.getValue(lbConfession.getSelectedIndex())));
		for (int j = 0; j < genderRadioButtons.length; j++) {
			if (genderRadioButtons[j].getValue()) {
				searchProfile.setGender(genderValues[j]);
				break;
			}
		}

		partnerboerseVerwaltung.save(searchProfile, new AsyncCallback<SearchProfile>() {

			@Override
			public void onSuccess(SearchProfile result) {
				if (searchProfile.getId() == 0) {
					dataProvider.getList().add(result);
				}
				dataProvider.flush();
				dataProvider.refresh();
				table.redraw();
				Window.alert("Profil gespeichert");
				showoneSPPanel.setVisible(false);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim speichern");
			}
		});
	}
}

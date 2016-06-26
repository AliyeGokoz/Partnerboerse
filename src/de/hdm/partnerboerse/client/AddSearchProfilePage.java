package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.client.SearchProfileFormular;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public class AddSearchProfilePage {
	
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	
	/**
	 * Panel anlegen
	 */
	final HorizontalPanel searchProfileInfos = new HorizontalPanel();
	final VerticalPanel addsearchProfilePanel = new VerticalPanel();
	final VerticalPanel searchProfileFormular = new VerticalPanel();
	final VerticalPanel searchProfileInfo = new VerticalPanel();
	final VerticalPanel selectionInfoPanel = new VerticalPanel();
	final VerticalPanel descriptionInfoPanel = new VerticalPanel();
	
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
	final TextBox searchProfileName = new TextBox();
	final TextBox tHeightfrom = new TextBox();
	final TextBox tHeightto = new TextBox();
	final TextBox tagerfrom = new TextBox();
	final TextBox tageto = new TextBox();
	final RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
	final RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
	
	/**
	 * Listbox erstellen für die Ausgabe der Eigenschaften
	 */
	final ListBox selectionpropertyListbox = new ListBox(false);
	final ListBox descriptionpropertyListbox = new ListBox(false);
	final ListBox optionsListBox = new ListBox();
	final TextArea textdesc = new TextArea();
	
	private final ArrayList<Selection> selections = new ArrayList<>();
	private final ArrayList<Option> options = new ArrayList<>();
	private final ArrayList<Description> descriptions = new ArrayList<>();
	
	/**
	 * Button zum speichern anlegen
	 */
	final Button saveSearchProfileButton = new Button("<img src='images/saveuser.png'/>");
	final Button addselectionInfo = new Button("<img src='images/add.png'/>");
	final Button adddescriptionInfo = new Button("<img src='images/add.png'/>");

	private final Profile profile;
	private RadioButton[] genderRadioButtons;
	private Gender[] genderValues;
	private SearchProfile searchProfile;

	private SearchProfilePage searchprofilepage;
	
	public AddSearchProfilePage(Profile profile, SearchProfilePage searchprofilepage){
		this.profile = profile;
		this.searchprofilepage = searchprofilepage;
	}

	public Widget addsearchProfile() {
		
		searchProfile = new SearchProfile();
		searchProfile.setProfile(profile);
		
		/**
		 * Panel Headline zuweißen
		 */
		addsearchProfilePanel.add(new HTML("<h3> Suchprofil anlegen </h3>"));
		
		/**
		 * Formular Style
		 */
		newSearchProfileTable.setStyleName("newSPstyle");
		/*
		 * Tabellenbreite setzten
		 */
		newSearchProfileTable.setWidth("200");
		
		/*
		 * Tabelle an Panel heften 
		 */
		addsearchProfilePanel.add(newSearchProfileTable);
		
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
		int i = 5;
		for (int j = 0; j < genderRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("genderGroup", genderValues[j].getName());
			newSearchProfileTable.setWidget(i++, 1, radioButton);
			genderRadioButtons[j] = radioButton;
		}
		
		/*
		 * Tabelle mit Inhalt füllen damit Formular für Suchprofil angelegt werden
		 * kann
		 */
		newSearchProfileTable.setHTML(0, 0, "<div>Suchprofilname</div>");
		newSearchProfileTable.setWidget(0, 1, searchProfileName);
		newSearchProfileTable.setHTML(1, 0, "<div>Größe in cm</div>");
		newSearchProfileTable.setWidget(1, 1, heightPanel);
		newSearchProfileTable.setHTML(2, 0, "<div>Alter</div>");
		newSearchProfileTable.setWidget(2, 1, agePanel);
		newSearchProfileTable.setHTML(3, 0, "<div>Haarfarbe</div>");
		newSearchProfileTable.setWidget(3, 1, lbHaircolor);
		newSearchProfileTable.setHTML(4, 0, "<div>Religion</div>");
		newSearchProfileTable.setWidget(4, 1, lbConfession);
		newSearchProfileTable.setHTML(5, 0, "<div>Geschlecht</div>");
		
		searchProfileFormular.add(saveSearchProfileButton);
		
		createselectioninfo();
		createdecriptionInfo();
		
		/**
		 * Panels mit den Infos an den Panel heften für die Ausgabe 
		 */
		searchProfileInfo.add(selectionInfoPanel);
		searchProfileInfo.add(descriptionInfoPanel);
		
		addsearchProfilePanel.add(searchProfileFormular);
		addsearchProfilePanel.add(searchProfileInfo);
	
		saveSearchProfileButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				savesearchProfile(searchProfile);
			}
		});
		return addsearchProfilePanel;

	}
	
	/**
	 * Methode zum generieren der Dropdown-Listen für die Information(Selection)
	 */
	public void createselectioninfo() {
		partnerboerseVerwaltung.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {

			@Override
			public void onSuccess(final ArrayList<Selection> selections) {
				AddSearchProfilePage.this.selections.clear();
				AddSearchProfilePage.this.selections.addAll(selections);

				for (final Selection s : selections) {
					selectionpropertyListbox.addItem(s.getPropertyName().toString());
				}

				// selectionpropertyDBPanel.setSpacing(4);
				// selectionpropertyDBPanel.add(selectionpropertyListbox);

				selectionInfoPanel.add(selectionpropertyListbox);

				selectionpropertyListbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						Selection selection = selections.get(selectionpropertyListbox.getSelectedIndex());

						partnerboerseVerwaltung.getOptionsOf(selection, new AsyncCallback<ArrayList<Option>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(ArrayList<Option> result) {
								optionsListBox.clear();
								AddSearchProfilePage.this.options.clear();
								AddSearchProfilePage.this.options.addAll(result);

								for (Option o : result) {
									optionsListBox.addItem(o.getOption());
								}

								selectionInfoPanel.add(optionsListBox);
								selectionInfoPanel.add(addselectionInfo);
							}
						});
					}
				});

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * Methode zum generieren der Dropdown-List für die Information(Description)
	 */
	public void createdecriptionInfo() {
		partnerboerseVerwaltung.getAllDescriptions(new AsyncCallback<ArrayList<Description>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Description> resultDescription) {
				descriptions.clear();
				descriptions.addAll(resultDescription);

				for (final Description d : resultDescription) {
					descriptionpropertyListbox.addItem(d.getTextualDescription().toString());
				}

				// descriptionpropertyDBPanel.setSpacing(4);
				// descriptionpropertyDBPanel.add(descriptionpropertyListbox);

				descriptionInfoPanel.add(descriptionpropertyListbox);

				descriptionpropertyListbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						textdesc.setValue("");
						descriptionInfoPanel.add(textdesc);
						descriptionInfoPanel.add(adddescriptionInfo);
					}
				});
			}
		});
	}
	
	
	public void savesearchProfile(final SearchProfile searchProfile) {

		String ageFromString = tagerfrom.getValue();
		if (ageFromString != null && !ageFromString.isEmpty()) {
			try{
				Integer valueOfageFrom = Integer.valueOf(tagerfrom.getValue());
				if(valueOfageFrom>110 || valueOfageFrom<18){
					Window.alert("Bitte eine Gültige Zahl eingeben. Die Zahl:" + valueOfageFrom + " ist ungültig.");
					return;
				}
				searchProfile.setFromAge(valueOfageFrom);
			}catch(NumberFormatException e){
				Window.alert("Bitte eine Gültige Zahl eingeben.");
				return;
			}
		}
		
		String ageToString = tageto.getValue();
		if (ageToString != null && !ageToString.isEmpty()) {
			try{
				Integer valueOfageTo = Integer.valueOf(tageto.getValue());
				if(valueOfageTo>110 || valueOfageTo<18 /*valueOfageTo>valueOfageFrom*/){
					Window.alert("Bitte eine gültige Zahl eingeben. Die Zahl:" + valueOfageTo + " ist ungültig.");
					return;
				}
				searchProfile.setToAge(valueOfageTo);
			}catch(NumberFormatException e){
				Window.alert("Bitte eine gültige Zahl eingeben.");
				return;
			}
		}
		
		String heigthFromString = tHeightfrom.getValue();
		if (heigthFromString != null && !heigthFromString.isEmpty()) {
			try{
				Integer valueOfheigthFrom = Integer.valueOf(tHeightfrom.getValue());
				if(valueOfheigthFrom>230 || valueOfheigthFrom<120){
					Window.alert("Bitte eine gültige Zahl eingeben. Die Zahl:" + valueOfheigthFrom + " ist ungültig.");
					return;
				}
				searchProfile.setFromHeight(valueOfheigthFrom);
			}catch(NumberFormatException e){
				Window.alert("Bitte eine gültige Zahl eingeben.");
				return;
			}
		}
		
		String heigthToString = tHeightto.getValue();
		if (heigthToString != null && !heigthToString.isEmpty()) {
			try{
				Integer valueOfheightTo = Integer.valueOf(tHeightto.getValue());
				if(valueOfheightTo>230 || valueOfheightTo<120){
					Window.alert("Bitte eine gültige Zahl eingeben. Die Zahl:" + valueOfheightTo + "ist ungültig.");
				}
			}catch(NumberFormatException e){
				Window.alert("Bitte eine gültige Zahl eingeben.");
			}
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
					searchprofilepage.dataProvider.getList().add(result);
				}
				searchprofilepage.dataProvider.flush();
				searchprofilepage.dataProvider.refresh();
				searchprofilepage.table.redraw();
				Window.alert("Profil gespeichert");
				searchprofilepage.showoneSPPanel.setVisible(false);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim speichern");
			}
		});
	}

}

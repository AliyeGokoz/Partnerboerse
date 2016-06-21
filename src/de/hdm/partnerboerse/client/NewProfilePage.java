package de.hdm.partnerboerse.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Selection;

public class NewProfilePage extends VerticalPanel {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	// Add a drop box with the list types
	final ListBox propertyListbox = new ListBox(false);
	final ListBox propertyListbox1 = new ListBox(false);
	final ListBox infoListBox = new ListBox();
	final TextArea textdesc = new TextArea();
	final VerticalPanel infoPanel = new VerticalPanel();
	final Button deleteButton = new Button("<img src='images/delete.png'/>");
	final Button editInfoButton = new Button("bearbeiten");
	final CellTable<Info> infoTable = new CellTable<>();
	ListDataProvider<Info> dataProvider = new ListDataProvider<>();
	private ListBox optionsListBox;

	private final ArrayList<Description> descriptions = new ArrayList<>();
	private final ArrayList<Selection> selections = new ArrayList<>();
	private final ArrayList<Option> options = new ArrayList<>();

	@Override
	public void onLoad() {
		
		

		loginService.login(Window.Location.getHref(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onSuccess(LoginInfo loginInfo) {
				Profile profile = loginInfo.getProfile() == null ? new Profile() : loginInfo.getProfile();
				profile.seteMail(loginInfo.getEmailAddress());
				
				
				/**
				 * TabPanel anlegen für die verschiedenen Bereiche wie Allg
				 * infos und Über Mich
				 */
				final TabPanel addProfilTapPanel = new TabPanel();

				/**
				 * Title für die Tabs
				 */
				final String tab1Title = "Profil anlegen";
				final String tab2Title = "Informationen anlegen";

				/**
				 * Content für die Tabs Zuweißen
				 */
				addProfilTapPanel.add(addNewProfil(profile), tab1Title);
				addProfilTapPanel.add(addInfoToNewProfil(profile), tab2Title);

				// select first tab
				addProfilTapPanel.selectTab(0);

				// set width if tabpanel
				addProfilTapPanel.setStyleName("profiletabPanel");

				getInfoTabel(profile);

				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(addProfilTapPanel);
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

	private HorizontalPanel addInfoToNewProfil(final Profile profil) {
		final HorizontalPanel addInfoToProfilPanel = new HorizontalPanel();
		final VerticalPanel addinfo = new VerticalPanel();
		addinfo.add(new HTML("<h3> Eigenschaften </h3>"));
		// infosOutput.add(infoPanel);
		// infosOutput.add(w);

		final VerticalPanel buttonPanel = new VerticalPanel();
		final VerticalPanel newInfoforProfilPanel = new VerticalPanel();
		newInfoforProfilPanel.add(new HTML("<div> Suche dir Eigenschaften aus: </div>"));
		final VerticalPanel selectionInfoPanel = new VerticalPanel();
		final VerticalPanel descriptionInfoPanel = new VerticalPanel();
		newInfoforProfilPanel.add(selectionInfoPanel);
		newInfoforProfilPanel.add(descriptionInfoPanel);

		// final Button saveButton = new Button("<img src='images/add.png'/>");
		final Button saveButto1 = new Button("Hinzufügen");
		final Button saveButto2 = new Button("Hinzufügen");

		addinfo.add(newInfoforProfilPanel);
		addinfo.add(descriptionInfoPanel);
		addinfo.add(buttonPanel);

		infoPanel.add(new HTML("<h3> Deine Hinzugefügten Informationen </h3>"));

		infoPanel.setStyleName("infpan");

		buttonPanel.setStyleName("hbuttons");
		newInfoforProfilPanel.setStyleName("htcontent");

		buttonPanel.setStyleName("savePanel");
		// buttonPanel.add(saveButton);

		/**
		 * ListBox für die Eigenschaften erstellen
		 */

		partnerboerseVerwaltung.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(final ArrayList<Selection> selections) {
				NewProfilePage.this.selections.clear();
				NewProfilePage.this.selections.addAll(selections);
				for (final Selection s : selections) {
					propertyListbox1.addItem(s.getPropertyName().toString());
				}

				propertyListbox.ensureDebugId("cwListBox-dropBox");
				final VerticalPanel propertydropBoxPanel1 = new VerticalPanel();
				propertydropBoxPanel1.setSpacing(4);
				propertydropBoxPanel1.add(propertyListbox1);
				final VerticalPanel secondSelectPanel = new VerticalPanel();
				propertydropBoxPanel1.add(secondSelectPanel);
				selectionInfoPanel.add(propertydropBoxPanel1);
				selectionInfoPanel.add(saveButto1);
				propertyListbox1.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						Selection selection = selections.get(propertyListbox1.getSelectedIndex());

						partnerboerseVerwaltung.getOptionsOf(selection, new AsyncCallback<ArrayList<Option>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(ArrayList<Option> result) {
								NewProfilePage.this.options.clear();
								NewProfilePage.this.options.addAll(result);

								optionsListBox = new ListBox();
								for (Option o : result) {
									optionsListBox.addItem(o.getOption());
								}
								secondSelectPanel.clear();
								secondSelectPanel.add(optionsListBox);

							}
						});

					}
				});

			}
		});

		partnerboerseVerwaltung.getAllDescriptions(new AsyncCallback<ArrayList<Description>>() {

			@Override
			public void onSuccess(ArrayList<Description> resultDescriptions) {

				descriptions.clear();
				descriptions.addAll(resultDescriptions);
				for (final Description d : resultDescriptions) {
					propertyListbox.addItem(d.getTextualDescription().toString());
				}
				propertyListbox.ensureDebugId("cwListBox-dropBox");
				VerticalPanel propertydropBoxPanel = new VerticalPanel();
				propertydropBoxPanel.setSpacing(4);
				propertydropBoxPanel.add(propertyListbox);
				final VerticalPanel textAreaPanel = new VerticalPanel();
				propertydropBoxPanel.add(textAreaPanel);
				descriptionInfoPanel.add(propertydropBoxPanel);
				descriptionInfoPanel.add(saveButto2);
				propertyListbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						textAreaPanel.clear();
						textdesc.setValue("");
						textAreaPanel.add(textdesc);
					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		/**
		 * ClickHandler zum Abspeichern von Infos
		 */
		saveButto1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Info info = new Info();
				info.setProfile(profil);
				Selection selection = selections.get(propertyListbox1.getSelectedIndex());
				info.setSelection(selection);
				Option option = options.get(optionsListBox.getSelectedIndex());
				info.setInformationValue(option.getOption());
				saveInfo(info);

			}
		});

		saveButto2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Info info = new Info();

				Description description = descriptions.get(propertyListbox.getSelectedIndex());
				info.setDescription(description);
				info.setProfile(profil);
				info.setInformationValue(textdesc.getValue().toString());

				saveInfo(info);
			}
		});

		addInfoToProfilPanel.add(addinfo);
		addInfoToProfilPanel.add(infoPanel);
		return addInfoToProfilPanel;
	}

	private void saveInfo(final Info info) {
		partnerboerseVerwaltung.save(info, new AsyncCallback<Info>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Konnte nicht gespeichert!");

			}

			@Override
			public void onSuccess(Info result) {

				dataProvider.getList().add(info);
				dataProvider.flush();
				dataProvider.refresh();
				infoTable.redraw();

				Window.alert("Info abgespeichert!");

			}
		});
	}

	private void getInfoTabel(Profile currentProfile) {

		partnerboerseVerwaltung.getInfoOf(currentProfile, new AsyncCallback<ArrayList<Info>>() {

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
				// TODO Auto-generated method stub

			}
		});

		dataProvider.addDataDisplay(infoTable);

		TextColumn<Info> nameInfo = new TextColumn<Info>() {
			@Override
			public String getValue(Info info) {
				if (info.getDescription() != null) {
					return info.getDescription().getPropertyName();
				} else {
					return info.getSelection().getPropertyName();
				}
			}
		};

		TextColumn<Info> valueColumn = new TextColumn<Info>() {
			@Override
			public String getValue(Info info) {
				
				if (info.getDescription() != null) {
					String result = info.getDescription().getTextualDescription() + "\n" +   info.getInformationValue();
					return result;
				} else {
					return info.getInformationValue();
				}
//				return info.getInformationValue();
			}
		};

		infoTable.addColumn(nameInfo, "Eigenschaft");
		infoTable.addColumn(valueColumn, "Information");

		infoPanel.add(infoTable);
		infoPanel.add(deleteButton);
		infoPanel.add(editInfoButton);

		final SingleSelectionModel<Info> selectionInfo = new SingleSelectionModel<Info>();
		infoTable.setSelectionModel(selectionInfo);
		
		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final Info selected = selectionInfo.getSelectedObject();
				if (selected != null) {
				partnerboerseVerwaltung.delete(selected, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {
						dataProvider.getList().remove(selected);
						dataProvider.flush();
						dataProvider.refresh();
						infoTable.redraw();
						
						Window.alert("erfolgreich gelöscht");

					}
				});
				}

			}
		});
	}

	private HorizontalPanel addNewProfil(final Profile profile) {
		final HorizontalPanel addnewProfilPanel = new HorizontalPanel();

		final Button saveButton = new Button("<img src='images/saveuser.png'/>");
		/**
		 * Tabelle für das Formular
		 */
		final FlexTable addnewProfileTable = new FlexTable();
		final FlexTable addnewProfileTable2 = new FlexTable();
		addnewProfileTable.setWidth("200");
		addnewProfileTable2.setWidth("200");
		// addnewProfileTable2.setStyleName("hcontent2");
		// addnewProfileTable.setStyleName("hcontent");

		addnewProfilPanel.add(addnewProfileTable);
		addnewProfilPanel.add(addnewProfileTable2);

		/**
		 * FlexTable formatieren
		 */
		addnewProfileTable.setCellSpacing(10);
		addnewProfileTable2.setCellSpacing(10);

		/**
		 * Textbox Widgets anlegen für die Eingaben des Users
		 */
		final TextBox tFirstname = new TextBox();
		tFirstname.setValue(profile.getFirstName());
		final TextBox tLastname = new TextBox();
		tLastname.setValue(profile.getLastName());
		final TextBox tEmail = new TextBox();
		tEmail.setEnabled(false);
		tEmail.setValue(profile.geteMail());
		final TextBox tHeight = new TextBox();
		tHeight.setValue("" + profile.getHeight());

		/**
		 * Listbox Widgets anlegen für Haarfarbe und Religion
		 */
		final ListBox lbHaircolor = new ListBox();
		final ListBox lbConfession = new ListBox();

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

		final Gender[] genderValues = Profile.Gender.values();
		final RadioButton[] genderRadioButtons = new RadioButton[genderValues.length];
		int i = 2;
		for (int j = 0; j < genderRadioButtons.length; j++) {
			RadioButton radioButton = new RadioButton("genderGroup", genderValues[j].getName());
			addnewProfileTable.setWidget(i++, 1, radioButton);
			if (genderValues[j].equals(profile.getGender())) {
				radioButton.setValue(true);
			}
			genderRadioButtons[j] = radioButton;
		}

		final RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
		Rbsmokeyes.setValue(profile.isSmoker());
		final RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
		Rbsmokeno.setValue(!profile.isSmoker());

		// TODO DatePicker überarbeiten
		final DatePicker datePicker = new DatePicker();
		datePicker.setYearAndMonthDropdownVisible(true);
		datePicker.setYearArrowsVisible(true);
		datePicker.setVisibleYearCount(150);

		datePicker.setValue(profile.getDateOfBirth());

		/**
		 * FlexTable mit Inhalt füllen = Userprofil Formular
		 */
		// addnewProfileTable.setHTML(0, 0, "<h2>Profil anlegen</h2>");
		addnewProfileTable.setHTML(0, 0, "<div>Vorname</div>");
		addnewProfileTable.setWidget(0, 1, tFirstname);
		addnewProfileTable.setHTML(1, 0, "<div>Geburtsdatum</div>");
		addnewProfileTable.setWidget(1, 1, datePicker);
		addnewProfileTable.setHTML(2, 0, "<div>Geschlecht</div>");

		addnewProfileTable2.setHTML(0, 0, "<div>Nachname</div>");
		addnewProfileTable2.setWidget(0, 1, tLastname);
		addnewProfileTable2.setHTML(1, 0, "<div>Email</div>");
		addnewProfileTable2.setWidget(1, 1, tEmail);
		addnewProfileTable2.setHTML(2, 0, "<div>Größe</div>");
		addnewProfileTable2.setWidget(2, 1, tHeight);
		addnewProfileTable2.setHTML(3, 0, "<div>Haarfarbe</div>");
		addnewProfileTable2.setWidget(3, 1, lbHaircolor);
		addnewProfileTable2.setHTML(4, 0, "<div>Religion</div>");
		addnewProfileTable2.setWidget(4, 1, lbConfession);
		addnewProfileTable2.setHTML(5, 0, "<div>Raucher</div>");
		addnewProfileTable2.setWidget(5, 1, Rbsmokeyes);
		addnewProfileTable2.setWidget(6, 1, Rbsmokeno);

		/**
		 * Zelle formatieren bei Gender
		 */
		// cellFormatter.setColSpan(0, 0, 2);
		// cellFormatter.setHorizontalAlignment(
		// 0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//Überprüfen ob ein Name eingegeben wurde und ob diese nur aus Buchstaben besteht
				if(!tFirstname.getValue().isEmpty()){
					String regEx = "[^0-9]*";
					String namevalue = tFirstname.getValue();
					if(namevalue.matches(regEx)){
						profile.setFirstName(tFirstname.getValue());
					}else{
						Window.alert("Bitte tragen Sie einen gültigen Namen ein.Nur Buchstaben gültig.");
						return;
					}
					
				} else {
					Window.alert("Bitte tragen Sie einen gültigen Namen ein.");
					return;
				}
				
				if(!tLastname.getValue().isEmpty()){
					String regEx = "[^0-9]*";
					String lastnamevalue = tLastname.getValue();
					if(lastnamevalue.matches(regEx)){
						profile.setLastName(tLastname.getValue());
					}else{
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

		addnewProfilPanel.add(saveButton);
		return addnewProfilPanel;
	}
}

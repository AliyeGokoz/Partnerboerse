package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
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
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Profile.Music;
import de.hdm.partnerboerse.shared.bo.Selection;

public class NewProfilePage extends VerticalPanel {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	@Override
	public void onLoad() {

		loginService.getCurrentProfile(new AsyncCallback<Profile>() {

			@Override
			public void onSuccess(Profile profile) {
				profile = profile == null ? new Profile() : profile;

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
				addProfilTapPanel.add(addInfoToNewProfil(), tab2Title);

				// select first tab
				addProfilTapPanel.selectTab(0);

				// set width if tabpanel
				addProfilTapPanel.setStyleName("profiletabPanel");

				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(addProfilTapPanel);
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

	private HorizontalPanel addInfoToNewProfil() {
		final HorizontalPanel addInfoToProfilPanel = new HorizontalPanel();
		final VerticalPanel addinfo = new VerticalPanel();
		final HorizontalPanel infoPanel = new HorizontalPanel();
		
		final VerticalPanel buttonPanel = new VerticalPanel();
		final HorizontalPanel newInfoforProfilPanel = new HorizontalPanel();
		final FlexTable infoFlexTabel = new FlexTable();
		final Button saveButton = new Button("<img src='images/add.png'/>");
		final Button deleteButton = new Button("<img src='images/delete.png'/>");
		
		addinfo.add(newInfoforProfilPanel);
		addinfo.add(buttonPanel);
		addinfo.add(deleteButton);
		
		
//		/**
//		 *  Create table for infos
//		 */
//		infoFlexTabel.setText(0, 0, "Deine Hinzugefügten Informationen");

		
		infoPanel.add(new HTML("<h3> Deine Hinzugefügten Informationen </h3>"));
		
		infoPanel.setStyleName("infpan");
		
		// Add styles to elements in the stock list table.
		infoFlexTabel.setCellPadding(6);
		
		// Add styles to elements in the stock list table.
//		infoFlexTabel.getCellFormatter().addStyleName(0, 0, "propertyhead");
		
		buttonPanel.setStyleName("hbuttons");
		newInfoforProfilPanel.setStyleName("htcontent");
		
		buttonPanel.setStyleName("savePanel");
		buttonPanel.add(saveButton);

		/**
		 * ListBox für die Eigenschaften erstellen
		 */

		// Add a drop box with the list types
		final ListBox propertyListbox = new ListBox(false);
		final ListBox propertyListbox1 = new ListBox(false);

		// saveButton.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// propertyListbox1.getV
		// }
		// });
		//
//		partnerboerseVerwaltung.getAllDescriptions(new AsyncCallback<ArrayList<Description>>() {
//
//			@Override
//			public void onSuccess(ArrayList<Description> resultDescriptions) {
//
//				for (final Description d : resultDescriptions) {
//					propertyListbox.addItem(d.getPropertyName().toString());
//				}
//				propertyListbox.ensureDebugId("cwListBox-dropBox");
//				VerticalPanel propertydropBoxPanel = new VerticalPanel();
//				propertydropBoxPanel.setSpacing(4);
//				propertydropBoxPanel.add(new HTML("<h2> Eigenschaften </h2>"));
//				propertydropBoxPanel.add(propertyListbox);
//				addInfoToProfilPanel.add(propertydropBoxPanel);
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//
//			}
//		});

//		partnerboerseVerwaltung.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onSuccess(final ArrayList<Selection> selections) {
//				for (final Selection s : selections) {
//					propertyListbox1.addItem(s.getPropertyName().toString());
//				}
//
//				propertyListbox.ensureDebugId("cwListBox-dropBox");
//				final VerticalPanel propertydropBoxPanel1 = new VerticalPanel();
//				propertydropBoxPanel1.setSpacing(4);
//				propertydropBoxPanel1.add(new HTML("<h2> Eigenschaften </h2>"));
//				propertydropBoxPanel1.add(propertyListbox1);
//				final VerticalPanel secondSelectPanel = new VerticalPanel();
//				propertydropBoxPanel1.add(secondSelectPanel);
//				profilPanel.add(propertydropBoxPanel1);
//				propertyListbox1.addChangeHandler(new ChangeHandler() {
//
//					//TODO zweite ListBox Ausgabe überarbeiten
//					@Override
//					public void onChange(ChangeEvent event) {
//						Selection selection = selections.get(propertyListbox1.getSelectedIndex());
//						partnerboerseVerwaltung.getInfoOf(selection, new AsyncCallback<ArrayList<Info>>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onSuccess(ArrayList<Info> result) {
//								final ListBox infoListBox = new ListBox();
//
//								for (Info info : result) {
//									infoListBox.addItem(info.getInformationValue());
//								}
//
//								secondSelectPanel.clear();
//								secondSelectPanel.add(infoListBox);
//
//							}
//						});
//
//					}
//				});
//
//			}
//		});

		partnerboerseVerwaltung.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(final ArrayList<Selection> selections) {
				for (final Selection s : selections) {
					propertyListbox1.addItem(s.getPropertyName().toString());
				}

				propertyListbox.ensureDebugId("cwListBox-dropBox");
				final VerticalPanel propertydropBoxPanel1 = new VerticalPanel();
				propertydropBoxPanel1.setSpacing(4);
				propertydropBoxPanel1.add(new HTML("<h2> Eigenschaften </h2>"));
				propertydropBoxPanel1.add(propertyListbox1);
				final VerticalPanel secondSelectPanel = new VerticalPanel();
				propertydropBoxPanel1.add(secondSelectPanel);
				newInfoforProfilPanel.add(propertydropBoxPanel1);
				propertyListbox1.addChangeHandler(new ChangeHandler() {

					//TODO zweite ListBox Ausgabe überarbeiten
					@Override
					public void onChange(ChangeEvent event) {
						Selection selection = selections.get(propertyListbox1.getSelectedIndex());
						Music[] selectionsValue = Music.class.getEnumConstants();
						final ListBox valuesEnum = new ListBox();
						for(Music m : selectionsValue){
							valuesEnum.addItem(m.getName().toString());
							
						}
						secondSelectPanel.clear();
						secondSelectPanel.add(valuesEnum);
//
//						
//						partnerboerseVerwaltung.getInfoOf(selection, new AsyncCallback<ArrayList<Info>>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onSuccess(ArrayList<Info> result) {
//								final ListBox infoListBox = new ListBox();
//
//								for (Info info : result) {
//									infoListBox.addItem(info.getInformationValue());
//								}
//
//								secondSelectPanel.clear();
//								secondSelectPanel.add(infoListBox);
//
//							}
//						});

					}
				});

			}
		});
		
		infoPanel.add(infoFlexTabel);
		
		
		
		addInfoToProfilPanel.add(addinfo);
		addInfoToProfilPanel.add(infoPanel);
		return addInfoToProfilPanel;
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
			if(genderValues[j].equals(profile.getGender())){
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
				profile.setFirstName(tFirstname.getValue());
				profile.setLastName(tLastname.getValue());
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
						Window.alert("Fehler beim speichern");
					}
				});
			}
		});

		addnewProfilPanel.add(saveButton);
		return addnewProfilPanel;
	}
}

package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;

/**
 * Klasse zum hinzufügen der
 * Informationen zum Suchprofil
 * @author aliyegokoz
 * @author alena gerlinskaja
 *
 */
public class AddInfoToSearchProfile {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/*
	 * Panel anlegen für die Ausgabe
	 */
	final HorizontalPanel infosofSP = new HorizontalPanel();
	final VerticalPanel addinfo = new VerticalPanel();
	final VerticalPanel showInfos = new VerticalPanel();
	final VerticalPanel selectionInfoPanel = new VerticalPanel();
	final VerticalPanel descriptionInfoPanel = new VerticalPanel();
	final VerticalPanel selectionpropertyDBPanel = new VerticalPanel();
	final VerticalPanel descriptionpropertyDBPanel = new VerticalPanel();

	/*
	 * Buttons anlegen zum Hinzufügen der Infos und Eigenschaften
	 */
	final Button addselectionInfo = new Button("<img src='images/add.png'/>");
	final Button adddescriptionInfo = new Button("<img src='images/add.png'/>");

	/*
	 * Listbox erstellen für die Ausgabe der Eigenschaften
	 */
	final ListBox selectionpropertyListbox = new ListBox();
	final ListBox descriptionpropertyListbox = new ListBox();
	final ListBox optionsListBox = new ListBox();
	final TextArea textdesc = new TextArea();

	private final ArrayList<Selection> selections = new ArrayList<>();
	private final ArrayList<Option> options = new ArrayList<>();
	private final ArrayList<Description> descriptions = new ArrayList<>();
	private ShowInfoOfSearchProfile showinfoOfsearchprofile = new ShowInfoOfSearchProfile();

	/**
	 * Methode für das hinzufügen von Informationen
	 * @param selectedsp
	 * @return HorizontalPanel
	 */
	public Widget addInfo(SearchProfile selectedsp) {
		
		/*
		 * Sytle 
		 */
		adddescriptionInfo.setStyleName("button");
		addselectionInfo.setStyleName("button");
		infosofSP.setStyleName("infoofsp");
		addinfo.setStyleName("addinfoPanel");

		addinfo.add(new HTML("<div> Suche dir Eigenschaften aus: </div>"));
		addinfo.add(selectionInfoPanel);
		addinfo.add(descriptionInfoPanel);

		/*
		 * Methodenaufruf Selection
		 */
		createselectioninfo();

		/*
		 * Methodenaufruf Description
		 */
		createdecriptionInfo();

		/*
		 * Methode für die Ausgabe der Infos
		 */
		showInfoOfSP(selectedsp);

		/*
		 * Abspeichern der Info
		 */
		saveButtonClickHandler(selectedsp);

		infosofSP.add(showInfos);
		infosofSP.add(addinfo);

		return infosofSP;
	}

	/**
	 * Methode zum generieren der Dropdown-Listen für die Information(Selection)
	 */
	public void createselectioninfo() {
		partnerboerseVerwaltung.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {

			@Override
			public void onSuccess(final ArrayList<Selection> selections) {
				AddInfoToSearchProfile.this.selections.clear();
				AddInfoToSearchProfile.this.selections.addAll(selections);

				for (final Selection s : selections) {
					selectionpropertyListbox.addItem(s.getPropertyName().toString());
				}

				selectionInfoPanel.add(selectionpropertyListbox);

				selectionpropertyListbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						/*
						 * Methodenaufruf
						 * beim Asuwahl von Selection
						 * neue Selection
						 */
						selectionPropertyChanged();
					}
				});

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Es ist ein Fehler aufgetreten");
			}
		});
	}

	/**
	 * Methode, welche bei Auswahl der Selection(Eigenschaften),
	 * eine neue DropDown-Liste generiert
	 * für die Informationen
	 */
	private void selectionPropertyChanged() {
		Selection selection = selections.get(selectionpropertyListbox.getSelectedIndex());

		partnerboerseVerwaltung.getOptionsOf(selection, new AsyncCallback<ArrayList<Option>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Informationen können nicht aufgerufen werden.");

			}

			@Override
			public void onSuccess(ArrayList<Option> result) {
				optionsListBox.clear();
				AddInfoToSearchProfile.this.options.clear();
				AddInfoToSearchProfile.this.options.addAll(result);

				for (Option o : result) {
					optionsListBox.addItem(o.getOption());
				}

				selectionInfoPanel.add(optionsListBox);
				selectionInfoPanel.add(addselectionInfo);
			}
		});
	}

	/**
	 * Methode zum generieren der Dropdown-List für die Eigenschaften(Description)
	 */
	public void createdecriptionInfo() {
		partnerboerseVerwaltung.getAllDescriptions(new AsyncCallback<ArrayList<Description>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Eigenschaften können nicht aufgerufen werden.");

			}

			@Override
			public void onSuccess(ArrayList<Description> resultDescription) {
				descriptions.clear();
				descriptions.addAll(resultDescription);

				for (final Description d : resultDescription) {
					descriptionpropertyListbox.addItem(d.getTextualDescriptionForSearchProfile().toString());
				}

				// descriptionpropertyDBPanel.setSpacing(4);
				// descriptionpropertyDBPanel.add(descriptionpropertyListbox);

				descriptionInfoPanel.add(descriptionpropertyListbox);

				descriptionpropertyListbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						descriptionPropertyChanged();
					}
				});

			}
		});

	}

	/**
	 * Wenn eine Description(Eigenschaft) ausgewählt ist
	 * wird eine Textbox generiert und ausgegeben
	 */
	private void descriptionPropertyChanged() {
		textdesc.setValue("");
		descriptionInfoPanel.add(textdesc);
		descriptionInfoPanel.add(adddescriptionInfo);
	}

	/**
	 * Methode, welche den Buttons, onClickHandler zuweisst damit Informationen
	 * abgespeichert werden könnne.
	 * 
	 * @param searchProfile
	 */
	public void saveButtonClickHandler(final SearchProfile searchProfile) {
		addselectionInfo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Info info = new Info();
				info.setSearchProfile(searchProfile);
				Selection selection = selections.get(selectionpropertyListbox.getSelectedIndex());
				info.setSelection(selection);
				Option option = options.get(optionsListBox.getSelectedIndex());
				info.setInformationValue(option.getOption());
				saveInfo(info);
			}
		});

		adddescriptionInfo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Info info = new Info();
				Description description = descriptions.get(descriptionpropertyListbox.getSelectedIndex());
				info.setDescription(description);
				info.setSearchProfile(searchProfile);
				info.setInformationValue(textdesc.getValue().toString());
				saveInfo(info);
			}
		});

	}

	/**
	 * Methode zum Speichern der Informationen zum Profil
	 * 
	 * @param info
	 */
	public void saveInfo(final Info info) {
		partnerboerseVerwaltung.save(info, new AsyncCallback<Info>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Konnte nicht gespeichert!");

			}

			@Override
			public void onSuccess(Info result) {
				if (result != null) {
					showinfoOfsearchprofile.dataProvider.getList().add(result);
					showinfoOfsearchprofile.dataProvider.flush();
					showinfoOfsearchprofile.dataProvider.refresh();
					showinfoOfsearchprofile.infoTable.redraw();
					textdesc.setValue("");
				}
			}
		});
	}

	/**
	 * Methode gibt alle Infos aus, welche 
	 * zum Suchprofil hinzugefügt wurden
	 * @param searchProfile
	 */
	public void showInfoOfSP(final SearchProfile searchProfile) {
		showInfos.setStyleName("styleinfospanel");
		showInfos.clear();
		showInfos.add(showinfoOfsearchprofile.showInfoOfSearchProfile(searchProfile));
	}

}

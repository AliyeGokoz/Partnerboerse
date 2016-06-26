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

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Selection;

public class AddInfoToProfilePage {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/**
	 * Panel anlegen für die Ausgabe
	 */
	final VerticalPanel addinfo = new VerticalPanel();
	final VerticalPanel selectionInfoPanel = new VerticalPanel();
	final VerticalPanel descriptionInfoPanel = new VerticalPanel();
	final VerticalPanel selectionpropertyDBPanel = new VerticalPanel();
	final VerticalPanel descriptionpropertyDBPanel = new VerticalPanel();

	/**
	 * Buttons anlegen zum Hinzufügen der Infos und Eigenschaften
	 */
	final Button addselectionInfo = new Button("<img src='images/add.png'/>");
	final Button adddescriptionInfo = new Button("<img src='images/add.png'/>");

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

	public Widget addinfotoprofile(Profile profile) {

		addinfo.add(new HTML("<h3> Eigenschaften </h3>"));
		// final VerticalPanel newInfoforProfilPanel = new VerticalPanel();
		addinfo.add(new HTML("<div> Suche dir Eigenschaften aus: </div>"));
		addinfo.add(selectionInfoPanel);
		addinfo.add(descriptionInfoPanel);

		/**
		 * 
		 */
		createselectioninfo();

		createdecriptionInfo();

		saveButtonClickHandler(profile);

		return addinfo;
	}

	/**
	 * Methode zum generieren der Dropdown-Listen für die Information(Selection)
	 */
	public void createselectioninfo() {
		partnerboerseVerwaltung.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {

			@Override
			public void onSuccess(final ArrayList<Selection> selections) {
				AddInfoToProfilePage.this.selections.clear();
				AddInfoToProfilePage.this.selections.addAll(selections);

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
								AddInfoToProfilePage.this.options.clear();
								AddInfoToProfilePage.this.options.addAll(result);

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
					descriptionpropertyListbox.addItem(d.getTextualDescriptionForProfile());
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

	/**
	 * Methode, welche den Buttons, onClickHandler zuweisst damit Informationen
	 * abgespeichert werden könnne.
	 * 
	 * @param profile
	 */
	public void saveButtonClickHandler(final Profile profile) {
		addselectionInfo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Info info = new Info();
				info.setProfile(profile);
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
				info.setProfile(profile);
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
				// dataProvider.getList().add(info);
				// dataProvider.flush();
				// dataProvider.refresh();
				// infoTable.redraw();
				textdesc.setValue("");
				Window.alert("Info abgespeichert!");

			}
		});
	}
}

package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public class SearchProfilePage extends VerticalPanel {

	final Label showlHeight = new Label("Größe: ");
	final Label showlHairColor = new Label("Haarfarbe: ");
	final Label showlConfession = new Label("Religion: ");
	final Label showlGender = new Label("Geschlecht: ");
	final Label showlSmoker = new Label("Raucher: ");
	
	public void onLoad(){
		
		final VerticalPanel buttonsPanel = new VerticalPanel();
		final VerticalPanel contentPanel = new VerticalPanel();
		final Button addSearchProfile = new Button("Suchprofil anlegen");
		final Button editSearchProfile = new Button("Suchprofil bearbeiten");
		final Button deleteSearchProfile = new Button("Suchprofil löschen");
		final StackPanel showPanel = new StackPanel();

		buttonsPanel.setStyleName("hbuttons");
		contentPanel.setStyleName("hcontent");
		
		addSearchProfile.setStyleName("buttonwidth");
		editSearchProfile.setStyleName("buttonwidth");
		deleteSearchProfile.setStyleName("buttonwidth");
		
		buttonsPanel.add(addSearchProfile);
		buttonsPanel.add(editSearchProfile);
		buttonsPanel.add(deleteSearchProfile);
		
		showPanel.setWidth("500px");
		showPanel.add(showProfil(), "Mein Suchprofil");
		
		contentPanel.add(showPanel);
		
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(buttonsPanel);
		RootPanel.get("Content").add(contentPanel);
		
		addSearchProfile.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				stockPanelSearchProfil();
			}
		});
		
	}

	private VerticalPanel showProfil() {
		final VerticalPanel showProfil = new VerticalPanel();

		showProfil.add(showlHeight);
		showProfil.add(showlHairColor);
		showProfil.add(showlConfession);
		showProfil.add(showlGender);
		showProfil.add(showlSmoker);

		return showProfil;
	}
	
	/**
	 * Neuen SearchprofileStockPanel anlegen für die Ausgabe der Panel Ansicht beim 
	 * Suchprofil anlegen.
	 */
	private void stockPanelSearchProfil() {

		final HorizontalPanel saveSearchProfilPanel = new HorizontalPanel();
		final StackPanel searchProfilPanel = new StackPanel();
		Button saveUser = new Button("Speichern");
		searchProfilPanel.setWidth("500px");
		searchProfilPanel.add(addnewSearchProfil(), "Suchprofil anlegen");
		saveSearchProfilPanel.add(saveUser);

		
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(searchProfilPanel);
		RootPanel.get("Content").add(saveSearchProfilPanel);
	}

	private HorizontalPanel addnewSearchProfil() {
		
		final HorizontalPanel addnewSearchProfilPanel = new HorizontalPanel();
		final Grid mainGrid = new Grid(10, 4);
		
		//anlegen der benötigten Widgets
		final Label slHeight = new Label("Größe: ");
		final Label slHairColor = new Label("Haarfarbe: ");
		final Label slConfession = new Label("Religion: ");
		final Label slGender = new Label("Geschlecht: ");
		final Label slSmoker = new Label("Raucher: ");
		
		//TODO final TextBox tHeight = new TextBox();
		final ListBox slbHaircolor = new ListBox();
		final ListBox slbConfession = new ListBox();
		
		Gender[] genderValues = Profile.Gender.values();
		int i = 1;
		for (Gender gender : genderValues) {
			RadioButton radioButton = new RadioButton("genderGroup", gender.getName());
			mainGrid.setWidget(4, i++, radioButton);
		}
		
		RadioButton sRbsmokeyes = new RadioButton("smokeGroup", "ja");
		RadioButton sRbsmokeno = new RadioButton("smokeGroup", "nein");
		
		HairColor[] hairColorValue = Profile.HairColor.values();
		for (HairColor hairColor : hairColorValue) {
			slbHaircolor.addItem(hairColor.getName(), hairColor.toString());
		}

		Confession[] confessionValue = Profile.Confession.values();
		for (Confession confession : confessionValue) {
			slbConfession.addItem(confession.getName(), confession.toString());
		}

		slbHaircolor.setVisibleItemCount(1);
		slbConfession.setVisibleItemCount(1);
		
		mainGrid.setWidget(1, 0, slHeight);
		mainGrid.setWidget(2, 0, slHairColor);
		mainGrid.setWidget(3, 0, slConfession);
		mainGrid.setWidget(4, 0, slGender);
		mainGrid.setWidget(5, 0, slSmoker);
		//TODO mainGrid.setWidget(5, 1, tHeight);
		mainGrid.setWidget(2, 1, slbHaircolor);
		mainGrid.setWidget(3, 1, slbConfession);
	
		mainGrid.setWidget(5, 1, sRbsmokeyes);
		mainGrid.setWidget(5, 2, sRbsmokeno);

		addnewSearchProfilPanel.add(mainGrid);
		
		return addnewSearchProfilPanel;
	}


}

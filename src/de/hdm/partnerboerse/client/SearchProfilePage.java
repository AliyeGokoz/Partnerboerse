package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
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
	
	
	/**
	 * VerticalPanel für die Widgets anlegen
	 */
	final HorizontalPanel searchprofilesPanel = new HorizontalPanel();
	final VerticalPanel buttonsearchProfilePanel = new VerticalPanel();
	final VerticalPanel searchProfilesPanel = new VerticalPanel();
	final VerticalPanel buttonSPpanel = new VerticalPanel();
	final VerticalPanel addinfosPanel = new VerticalPanel();
	final VerticalPanel showallSPPanel = new VerticalPanel();
	final VerticalPanel showoneSPPanel = new VerticalPanel();

	final Label showlHeight = new Label("Größe: ");
	final Label showlHairColor = new Label("Haarfarbe: ");
	final Label showlConfession = new Label("Religion: ");
	final Label showlGender = new Label("Geschlecht: ");
	final Label showlSmoker = new Label("Raucher: ");
	
	public void onLoad(){
		
		searchprofilesPanel.add(buttonsearchProfilePanel);
		searchprofilesPanel.add(searchProfilesPanel);
		
		buttonsearchProfilePanel.add(buttonSPpanel);
		buttonsearchProfilePanel.add(addinfosPanel);
		
		searchProfilesPanel.add(showallSPPanel);
		searchProfilesPanel.add(showoneSPPanel);
		
		
		
		/**
		 * Button anlegen zum Anlegen von Suchprofilen
		 */
		final Button addnewSPButton = new Button("<img src='images/add_searchprofiles.png'/>");
	
		addnewSPButton.setStyleName("buttonmargin");
		
		/**
		 * Button dem VerticalPanel zuweisen
		 */
		//searchProfilPanel.add(new HTML("<h2>Hallo</h2>"));
		buttonsearchProfilePanel.add(addnewSPButton);
		
		/**
		 * Ausgabe für die Headline der Suchprofile
		 */
		showallSPPanel.add(new HTML("<h3> Deine Suchprofile </h3>"));
		
		/**
		 * Panels dem RootPanel zuweisen
		 */
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(searchprofilesPanel);
		
		/**
		 * ClickHandler für den Button "Neues Suchprofil anlegen" anlegen, 
		 * damit dieser beim Anklicken die Ansicht öffnet damit
		 * ein Neues Suchprofil angelegt werden kann.
		 * 
		 */
		addnewSPButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addinfosPanel.clear();
				showoneSPPanel.clear();
				addNewSearchProfile();
			}
		});
		
	}
	
	private void addNewSearchProfile(){
		
		showoneSPPanel.add(new HTML("<h3> Neues Suchprofil anlegen </h3>"));
		addinfosPanel.add(new HTML("<h3> Informationen </h3>"));
		
	}

}

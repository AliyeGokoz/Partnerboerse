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

	final Label showlHeight = new Label("Größe: ");
	final Label showlHairColor = new Label("Haarfarbe: ");
	final Label showlConfession = new Label("Religion: ");
	final Label showlGender = new Label("Geschlecht: ");
	final Label showlSmoker = new Label("Raucher: ");
	
	public void onLoad(){
		
		/**
		 * VerticalPanel für die Widgets anlegen
		 */
		final VerticalPanel searchProfilePanel = new VerticalPanel();
		final VerticalPanel addnewsearchProfilePanel = new VerticalPanel();
		searchProfilePanel.setSpacing(5);
		addnewsearchProfilePanel.setStyleName("hbutton");
		
		/**
		 * Button anlegen zum Anlegen von Suchprofilen
		 */
		final Button addnewSPButton = new Button("Neues Suchprofil anlegen");
	
		
		/**
		 * Button dem VerticalPanel zuweisen
		 */
		//searchProfilPanel.add(new HTML("<h2>Hallo</h2>"));
		addnewsearchProfilePanel.add(addnewSPButton);
		
		/**
		 * Panels dem RootPanel zuweisen
		 */
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(addnewsearchProfilePanel);
		
		/**
		 * ClickHandler für den Button "Neues Suchprofil anlegen" anlegen, 
		 * damit dieser beim Anklicken die Ansicht öffnet damit
		 * ein Neues Suchprofil angelegt werden kann.
		 * 
		 */
		addnewSPButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addNewSearchProfile();
			}
		});
		
	}
	
	private void addNewSearchProfile(){
	
		/**
		 * VerticalPanel anlegen 
		 */
		final VerticalPanel addSearchProfilPanel = new VerticalPanel();
		final VerticalPanel buttonPanel = new VerticalPanel();
		buttonPanel.setStyleName("hbuttons");
		addSearchProfilPanel.setStyleName("hcontent");
		
		/**
		 * Button anlegen zum zurück gehen
		 */
		final Button backButton = new Button("<img src='images/back.png'/>");
		
		/**
		 * Button an buttonPanel heften
		 */
		buttonPanel.add(backButton);
		
		/**
		 * Widget an Panel anheften
		 */
		addSearchProfilPanel.add(new HTML("<h2>Neues Suchprofil anlegen</h2>"));
		
		
		/**
		 * Panels an RootPanel heften
		 */
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(buttonPanel);
		RootPanel.get("Content").add(addSearchProfilPanel);
		
		backButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onLoad();
			}
		});
	}

}

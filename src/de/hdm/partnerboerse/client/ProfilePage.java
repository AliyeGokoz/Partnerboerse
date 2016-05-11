package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilePage extends VerticalPanel{

	@Override
	public void onLoad(){
		
		
		//TODO Button anlegen nur dann anzeigen wenn kein Profil angelegt ist
		/* 
		if(profil angelegt){
			final Button editProfile = new Button("Profil bearbeiten");
			final Button deleteProfile = new Button("Profil löschen");
			
			anzeigen Profil
		}else{
			final Button addProfile = new Button("Profil anlegen");
		}
		 */
		
		//buttonausgabe für den Testzweck
		final VerticalPanel hbuttons = new VerticalPanel();
		final HorizontalPanel hcontent = new HorizontalPanel();
		final Button editProfile = new Button("Profil bearbeiten");
		final Button deleteProfile = new Button("Profil löschen");
		final Button addProfile = new Button("Profil anlegen");
		
		
		hbuttons.setStyleName("hbuttons");
		hcontent.setStyleName("hcontent");
		
		addProfile.setStyleName("buttonwidth");
		editProfile.setStyleName("buttonwidth");
		deleteProfile.setStyleName("buttonwidth");
		
		hbuttons.add(addProfile);
		hbuttons.add(editProfile);
		hbuttons.add(deleteProfile);
		
		RootPanel.get("Buttonzone").clear();
		RootPanel.get("Contentzone").clear();
  	  	RootPanel.get("Buttonzone").add(hbuttons);
  	  	RootPanel.get("Contentzone").add(hcontent);
  	  	
  	  	
  	  addProfile.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addnewProfil();
			}
		});

		
  	  	
  	  	/*final Grid mainGrid = new Grid(10,4);
		
		final Label lTitle = new Label("Neues Profil anlegen");
		final Label lFirstname = new Label("Vorname: ");
		final Label lLastName = new Label("Nachname: ");
		final Label lDateofbirth = new Label("Geburtsdatum: ");
		final Label lEmail = new Label("Email: ");
		final Label lHeight = new Label("Größe: ");
		final Label lHairColor = new Label("Haarfarbe: ");
		final Label lConfession = new Label("Relegion: ");
		final Label lGender = new Label("Geschlecht: ");
		final Label lSmoker = new Label("Raucher: ");
		
		final TextBox tFirstname = new TextBox();
		final TextBox tLastname = new TextBox();
		final TextBox tDateofbirth = new TextBox();
		final TextBox tEmail = new TextBox();
		final TextBox tHeight = new TextBox();
		final ListBox lbHaircolor = new ListBox();
		final ListBox lbConfession = new ListBox();
		RadioButton Rbgenderfm = new RadioButton("genderGroup", "weiblich");
	    RadioButton Rbgenderm = new RadioButton("genderGroup", "männlich");
	    RadioButton Rbgendero = new RadioButton("genderGroup", "andere");
	    RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
	    RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
		
		lbHaircolor.addItem("select");
		lbHaircolor.addItem("braun");
		lbHaircolor.addItem("blond");
		lbHaircolor.addItem("schwarz");
		lbHaircolor.addItem("rot");
		lbHaircolor.addItem("grau");
		lbHaircolor.addItem("others");
		
		lbConfession.addItem("select");
		lbConfession.addItem("evangelisch");
		lbConfession.addItem("katholisch");
		lbConfession.addItem("buddistisch");
		lbConfession.addItem("hinduistisch");
		lbConfession.addItem("muslimisch");
		lbConfession.addItem("jüdisch");
		lbConfession.addItem("keine Konfession");
		lbConfession.addItem("andere");
		
		lbHaircolor.setVisibleItemCount(1);
		lbConfession.setVisibleItemCount(1);
  	  	
		mainGrid.setWidget(0, 0, lTitle);
		mainGrid.setWidget(1, 0, lFirstname);
		mainGrid.setWidget(2, 0, lLastName);
		mainGrid.setWidget(3, 0, lDateofbirth);
		mainGrid.setWidget(4, 0, lEmail);
		mainGrid.setWidget(5, 0, lHeight);
		mainGrid.setWidget(6, 0, lHairColor);
		mainGrid.setWidget(7, 0, lConfession);
		mainGrid.setWidget(8, 0, lGender);
		mainGrid.setWidget(9, 0, lSmoker);
		mainGrid.setWidget(1, 1, tFirstname);
		mainGrid.setWidget(2, 1, tLastname);
		mainGrid.setWidget(3, 1, tDateofbirth);
		mainGrid.setWidget(4, 1, tEmail);
		mainGrid.setWidget(5, 1, tHeight);
		mainGrid.setWidget(6, 1, lbHaircolor);
		mainGrid.setWidget(7, 1, lbConfession);
		mainGrid.setWidget(8, 1, Rbgenderfm);
		mainGrid.setWidget(8, 2, Rbgenderm);
		mainGrid.setWidget(8, 3, Rbgendero);
		mainGrid.setWidget(9, 1, Rbsmokeyes);
		mainGrid.setWidget(9, 2, Rbsmokeno);
		
		hcontent.add(mainGrid);
		
		RootPanel.get("Contentzone").clear();
		RootPanel.get("Contentzone").add(lTitle);
		RootPanel.get("Contentzone").add(hcontent);	
  
		//add Buttons 
		/*final Button addProfile = new Button("Profil anlegen");
		final Button editProfile = new Button("Profil bearbeiten");
		final Button deleteProfile = new Button("Profil löschen");*/
		
		/*
		final VerticalPanel hbuttons = new VerticalPanel();
		final HorizontalPanel hcontent = new HorizontalPanel();
		final Button addProfile = new Button("Profil anlegen");
		final Button editProfile = new Button("Profil bearbeiten");
		final Button deleteProfile = new Button("Profil löschen");
		
		hbuttons.setStyleName("hbuttons");
		hcontent.setStyleName("hcontent");
		
		addProfile.setStyleName("buttonwidth");
		editProfile.setStyleName("buttonwidth");
		deleteProfile.setStyleName("buttonwidth");
		
		hbuttons.add(addProfile);
		hbuttons.add(editProfile);
		hbuttons.add(deleteProfile);
		
		RootPanel.get("Buttonzone").clear();
		RootPanel.get("Contentzone").clear();
  	  	RootPanel.get("Buttonzone").add(hbuttons);
  	  	RootPanel.get("Contentzone").add(hcontent);
  	  	
  	  	//TODO wenn bereits Profil erstellt dann anzeigen, wnnn nicht leere Seite mit Ausgabe: erstellen sich doch ein Profil
  	  	
  	  	
  	  	*/
	}	
	
	private void addnewProfil() {
		
				   final VerticalPanel hbuttons = new VerticalPanel();
				   final HorizontalPanel hcontent = new HorizontalPanel();
				   final Grid mainGrid = new Grid(10,4);
					
					final Label lTitle = new Label("Neues Profil anlegen");
					final Label lFirstname = new Label("Vorname: ");
					final Label lLastName = new Label("Nachname: ");
					final Label lDateofbirth = new Label("Geburtsdatum: ");
					final Label lEmail = new Label("Email: ");
					final Label lHeight = new Label("Größe: ");
					final Label lHairColor = new Label("Haarfarbe: ");
					final Label lConfession = new Label("Relegion: ");
					final Label lGender = new Label("Geschlecht: ");
					final Label lSmoker = new Label("Raucher: ");
					
					final TextBox tFirstname = new TextBox();
					final TextBox tLastname = new TextBox();
					final TextBox tDateofbirth = new TextBox();
					final TextBox tEmail = new TextBox();
					final TextBox tHeight = new TextBox();
					final ListBox lbHaircolor = new ListBox();
					final ListBox lbConfession = new ListBox();
					RadioButton Rbgenderfm = new RadioButton("genderGroup", "weiblich");
				    RadioButton Rbgenderm = new RadioButton("genderGroup", "männlich");
				    RadioButton Rbgendero = new RadioButton("genderGroup", "andere");
				    RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
				    RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
					
					lbHaircolor.addItem("select");
					lbHaircolor.addItem("braun");
					lbHaircolor.addItem("blond");
					lbHaircolor.addItem("schwarz");
					lbHaircolor.addItem("rot");
					lbHaircolor.addItem("grau");
					lbHaircolor.addItem("others");
					
					lbConfession.addItem("select");
					lbConfession.addItem("evangelisch");
					lbConfession.addItem("katholisch");
					lbConfession.addItem("buddistisch");
					lbConfession.addItem("hinduistisch");
					lbConfession.addItem("muslimisch");
					lbConfession.addItem("jüdisch");
					lbConfession.addItem("keine Konfession");
					lbConfession.addItem("andere");
					
					lbHaircolor.setVisibleItemCount(1);
					lbConfession.setVisibleItemCount(1);
			  	  	
					mainGrid.setWidget(0, 0, lTitle);
					mainGrid.setWidget(1, 0, lFirstname);
					mainGrid.setWidget(2, 0, lLastName);
					mainGrid.setWidget(3, 0, lDateofbirth);
					mainGrid.setWidget(4, 0, lEmail);
					mainGrid.setWidget(5, 0, lHeight);
					mainGrid.setWidget(6, 0, lHairColor);
					mainGrid.setWidget(7, 0, lConfession);
					mainGrid.setWidget(8, 0, lGender);
					mainGrid.setWidget(9, 0, lSmoker);
					mainGrid.setWidget(1, 1, tFirstname);
					mainGrid.setWidget(2, 1, tLastname);
					mainGrid.setWidget(3, 1, tDateofbirth);
					mainGrid.setWidget(4, 1, tEmail);
					mainGrid.setWidget(5, 1, tHeight);
					mainGrid.setWidget(6, 1, lbHaircolor);
					mainGrid.setWidget(7, 1, lbConfession);
					mainGrid.setWidget(8, 1, Rbgenderfm);
					mainGrid.setWidget(8, 2, Rbgenderm);
					mainGrid.setWidget(8, 3, Rbgendero);
					mainGrid.setWidget(9, 1, Rbsmokeyes);
					mainGrid.setWidget(9, 2, Rbsmokeno);
					
					hcontent.add(mainGrid);
					
					RootPanel.get("Contentzone").clear();
					RootPanel.get("Contentzone").add(lTitle);
					RootPanel.get("Contentzone").add(hcontent);
	}
	
}

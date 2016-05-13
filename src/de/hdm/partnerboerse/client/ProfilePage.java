package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackPanel;
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
  	  	
  	  	
  	  deleteProfile.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
             // Instantiate the dialog box and show it.
        	  MyDialogforDltProfiles myDialog = new MyDialogforDltProfiles();

             int left = Window.getClientWidth()/ 2;
             int top = Window.getClientHeight()/ 2;
             myDialog.setPopupPosition(left, top);
             myDialog.show();				
          }
       });

  	  
  	  	
  	  addProfile.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				stockPanelProfil();
			}
		});

	}	
	
	/**
	 * Neuen StockPanel anlegen für die Ausgabe der Panel Ansicht beim Profil anlegen und 
	 * Informationen anlegen.
	 */
	private void stockPanelProfil(){
		
	    StackPanel profilPanel = new StackPanel();
	    profilPanel.setWidth("500px");
	    profilPanel.add(addnewProfil(), "Profil anlegen");
	    profilPanel.add(addInfoToNewProfil(), "Informationen anlegen");
	
	   
	    RootPanel.get("Contentzone").clear();
	    RootPanel.get("Contentzone").add(profilPanel);
		
	}
	
	private HorizontalPanel addnewProfil() {
		
				   final HorizontalPanel addnewProfilPanel = new HorizontalPanel();
				   final Grid mainGrid = new Grid(10,4);
					
					final Label lTitle = new Label("Neues Profil anlegen");
					final Label lFirstname = new Label("Vorname: ");
					final Label lLastName = new Label("Nachname: ");
					final Label lDateofbirth = new Label("Geburtsdatum: ");
					final Label lEmail = new Label("Email: ");
					final Label lHeight = new Label("Größe: ");
					final Label lHairColor = new Label("Haarfarbe: ");
					final Label lConfession = new Label("Religion: ");
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
					
					addnewProfilPanel.add(mainGrid);
					
					return addnewProfilPanel;
	}
	
	private DecoratorPanel addInfoToNewProfil(){
		
			final VerticalPanel addnewinfotoProfilPanel = new VerticalPanel();
			final ScrollPanel scrollPanel = new ScrollPanel(addnewinfotoProfilPanel);
			scrollPanel.setSize("500px", "300px");
			DecoratorPanel decoratorPanel = new DecoratorPanel();
			decoratorPanel.add(scrollPanel);

			
			
			final Label lHobby = new Label("Hobbys");
			final Label lHobbyText = new Label("Ich mache gern:");
			final Label lMusik = new Label("Musik");
			final Label lMusikText = new Label("Ich höre gern:");
			final Label lfavBand = new Label("Mein/e Lieblingsband/Lieblingskünstler/in sind/ist: ");
			final Label lSport = new Label("Sport");
			final Label lSportText = new Label("Ich betreibe gerne folgende Sportart/en: ");
			final Label lFilme = new Label("Filme");
			final Label lFilmeText = new Label("Ich schaue gerne: ");
			final Label lfavFilm = new Label("Mein/e Lieblingsfilm/e ist/sind: ");
			
			final ListBox lbMusik = new ListBox();
			final ListBox lbFilme = new ListBox();
			
			final CheckBox cbSport1 = new CheckBox("Fußball");
			final CheckBox cbSport2 = new CheckBox("Basketball");
			final CheckBox cbSport3 = new CheckBox("Wandern");
			final CheckBox cbSport4 = new CheckBox("Volleyball");
			final CheckBox cbSport5 = new CheckBox("Volkstanz");
			final CheckBox cbSport6 = new CheckBox("Tennis");
			final CheckBox cbSport7 = new CheckBox("Tauchen");
			final CheckBox cbSport8 = new CheckBox("Teakwondo");
			final CheckBox cbSport9 = new CheckBox("Seilspringen");
			final CheckBox cbSport10 = new CheckBox("Schwimmen");
			final CheckBox cbSport11 = new CheckBox("Rugby");
			final CheckBox cbSport12 = new CheckBox("Other");
			
			final TextBox tHobby = new TextBox();
			final TextBox tBand = new TextBox();
			final TextBox tFilme = new TextBox();
			
			/**
			 * Untenstrich für die Infoüberschriften
			 */
			lHobby.setStyleName("infohead");
			lMusik.setStyleName("infohead");
			lSport.setStyleName("infohead");
			lFilme.setStyleName("infohead");
			tHobby.setStyleName("textboxsize");
			tBand.setStyleName("textboxsize");
			tFilme.setStyleName("textboxsize");
			
			/**
			 * Hinzufügen der Info-Objekte für die Eigenschaft Musik
			 */
			lbMusik.addItem("select");
			lbMusik.addItem("Rock");
			lbMusik.addItem("Pop");
			lbMusik.addItem("Electro");
			lbMusik.addItem("House");
			lbMusik.addItem("Volksmusik");
			lbMusik.addItem("others");
			
			/**
			 * Hinzufügen der Info-Objekte für die Eigenschaft Musik
			 */
			lbFilme.addItem("select");
			lbFilme.addItem("Rock");
			lbFilme.addItem("Pop");
			lbFilme.addItem("Electro");
			lbFilme.addItem("House");
			lbFilme.addItem("Volksmusik");
			lbFilme.addItem("others");
			
			/**
			 * new Panel für die Sportarten Checkboxen
			 */
			final HorizontalPanel sportCheckBoxen = new HorizontalPanel();
			
			sportCheckBoxen.add(cbSport1);
			sportCheckBoxen.add(cbSport2);
			sportCheckBoxen.add(cbSport3);
			sportCheckBoxen.add(cbSport4);
			sportCheckBoxen.add(cbSport5);
			sportCheckBoxen.add(cbSport6);
			sportCheckBoxen.add(cbSport7);
			sportCheckBoxen.add(cbSport8);
			sportCheckBoxen.add(cbSport9);
			sportCheckBoxen.add(cbSport10);
			sportCheckBoxen.add(cbSport11);
			sportCheckBoxen.add(cbSport12);
			
			
			/**
			 * Hinzufügen der Widgets an den Panel
			 */
			addnewinfotoProfilPanel.add(lHobby);
			addnewinfotoProfilPanel.add(lHobbyText);
			addnewinfotoProfilPanel.add(tHobby);
			addnewinfotoProfilPanel.add(lMusik);
			addnewinfotoProfilPanel.add(lMusikText);
			addnewinfotoProfilPanel.add(lbMusik);
			addnewinfotoProfilPanel.add(lfavBand);
			addnewinfotoProfilPanel.add(tBand);
			addnewinfotoProfilPanel.add(lSport);
			addnewinfotoProfilPanel.add(lSportText);
			addnewinfotoProfilPanel.add(sportCheckBoxen);
			addnewinfotoProfilPanel.add(lFilme);
			addnewinfotoProfilPanel.add(lFilmeText);
			addnewinfotoProfilPanel.add(lbFilme);
			addnewinfotoProfilPanel.add(lfavFilm);
			addnewinfotoProfilPanel.add(tFilme);
			
			
			
			
			return decoratorPanel;
	}
	
	private static class MyDialogforDltProfiles extends DialogBox {

	      public MyDialogforDltProfiles() {
	         // Set the dialog box's caption.
	         setText("Profil löschen");

	         // Enable animation.
	         setAnimationEnabled(true);

	         // Enable glass background.
	         setGlassEnabled(true);

	         // DialogBox is a SimplePanel, so you have to set its widget 
	         // property to whatever you want its contents to be.
	         Button ok = new Button("OK");
	         ok.addClickHandler(new ClickHandler() {
	            public void onClick(ClickEvent event) {
	            	MyDialogforDltProfiles.this.hide();
	            }
	         });

	         Label label = new Label("Wollen sie Ihr Profil wirklich unwiederruflich löschen?");

	         VerticalPanel panel = new VerticalPanel();
	         panel.setHeight("100");
	         panel.setWidth("300");
	         panel.setSpacing(10);
	         panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	         panel.add(label);
	         panel.add(ok);

	         setWidget(panel);
	      }
	   }

	

}


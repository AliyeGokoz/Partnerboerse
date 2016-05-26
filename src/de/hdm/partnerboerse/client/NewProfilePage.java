package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public class NewProfilePage extends VerticalPanel{

	@Override
	public void onLoad() {
		/**
		 * TabPanel anlegen für die verschiedenen Bereiche wie Allg infos und Über Mich
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
		addProfilTapPanel.add(addNewProfil(), tab1Title);
		addProfilTapPanel.add(addInfoToNewProfil(), tab2Title);
		
		//select first tab
		addProfilTapPanel.selectTab(0);

	      //set width if tabpanel
		addProfilTapPanel.setStyleName("profiletabPanel");
		
		RootPanel.get("Content").clear();
		RootPanel.get("Content").add(addProfilTapPanel);

	}

	private HorizontalPanel addInfoToNewProfil() {
		final HorizontalPanel addInfoToProfilPanel = new HorizontalPanel();
		
		/**
		 * ListBox für die Eigenschaften erstellen
		 */
		final ListBox propertyListbox = new ListBox();
		
		
		
		return addInfoToProfilPanel;
	}

	private HorizontalPanel addNewProfil() {
		final HorizontalPanel addnewProfilPanel = new HorizontalPanel();
		
		final Button saveButton = new Button("Speichern");
		/**
		 * Tabelle für das Formular
		 */
		final FlexTable addnewProfileTable = new FlexTable();
		final FlexTable addnewProfileTable2 = new FlexTable();
		addnewProfileTable.setWidth("200");
		addnewProfileTable2.setWidth("200");
		//addnewProfileTable2.setStyleName("hcontent2");
		//addnewProfileTable.setStyleName("hcontent");
		
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
		final TextBox tLastname = new TextBox();
		final TextBox tEmail = new TextBox();
		final TextBox tHeight = new TextBox();
		
		/**
		 * Listbox Widgets anlegen für Haarfarbe und Religion
		 */
		final ListBox lbHaircolor = new ListBox();
		final ListBox lbConfession = new ListBox();
		
		HairColor[] hairColorValue = Profile.HairColor.values();
		for (HairColor hairColor : hairColorValue) {
			lbHaircolor.addItem(hairColor.getName(), hairColor.toString());
		}
		
		Confession[] confessionValue = Profile.Confession.values();
		for (Confession confession : confessionValue) {
			lbConfession.addItem(confession.getName(), confession.toString());
		}
		
		Gender[] genderValues = Profile.Gender.values();
		int i = 2;
		for (Gender gender : genderValues) {
			RadioButton radioButton = new RadioButton("genderGroup", gender.getName());
			addnewProfileTable.setWidget(i++, 1, radioButton);
		}
		
		RadioButton Rbsmokeyes = new RadioButton("smokeGroup", "ja");
		RadioButton Rbsmokeno = new RadioButton("smokeGroup", "nein");
		
		//TODO DatePicker überarbeiten
		final DatePicker datePicker = new DatePicker();
		
		/**
		 * FlexTable mit Inhalt füllen = Userprofil Formular
		 */
		//addnewProfileTable.setHTML(0, 0, "<h2>Profil anlegen</h2>");
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
	    //cellFormatter.setColSpan(0, 0, 2);
	    //cellFormatter.setHorizontalAlignment(
	        //0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    
	    addnewProfilPanel.add(saveButton);
		return addnewProfilPanel;
	}
}

package de.hdm.partnerboerse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;

public class PartnerboerseReport implements EntryPoint {
	
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	
	Command showReport = new Command() {
		public void execute() {
			ReportPage showReport = new ReportPage();
			RootPanel.get("Buttonzone").clear();
			RootPanel.get("Contentzone").clear();
			RootPanel.get("Buttonzone").add(showReport);
			RootPanel.get("Contentzone").add(showReport);
		}
	};

	Command goBack = new Command() {
		public void execute() {
			ProfilePage goBack = new ProfilePage();
			RootPanel.get("Buttonzone").clear();
			RootPanel.get("Contentzone").clear();
			RootPanel.get("Buttonzone").add(goBack);
			RootPanel.get("Contentzone").add(goBack);
		}
	};

	@Override
	public void onModuleLoad() {
		final VerticalPanel content = new VerticalPanel();
		MenuBar backToMenu = new MenuBar(true);
		backToMenu.addItem("Zurück zum Hauptmenü", goBack);
		MenuBar reportMenu = new MenuBar(true);
		reportMenu.addItem("Report ansehen", showReport);

		MenuBar menu = new MenuBar();
		menu.addItem("Zurück", backToMenu);
		menu.addItem("Report", reportMenu);


		// buttonausgabe für den Testzweck
		final VerticalPanel hbuttons = new VerticalPanel();
		final HorizontalPanel hcontent = new HorizontalPanel();
		final Button profileProposals = new Button("Profilvorschläge");
		final Button profileProposalsBySearchProfile = new Button("Profilvorschläge anhand des " + "Suchprofils");
		final Button allReports = new Button("Alle Reports");

		hbuttons.setStyleName("hbuttons");
		hcontent.setStyleName("hcontent");

		profileProposals.setStyleName("buttonwidth");
		profileProposalsBySearchProfile.setStyleName("buttonwidth");
		allReports.setStyleName("buttonwidth");

		hbuttons.add(profileProposals);
		hbuttons.add(profileProposalsBySearchProfile);
		hbuttons.add(allReports);

		RootPanel.get("Buttonzone").clear();
		RootPanel.get("Contentzone").clear();
		RootPanel.get("Buttonzone").add(hbuttons);
		RootPanel.get("Contentzone").add(hcontent);
		
		RootPanel.get("Navigator").add(menu);
	}
}

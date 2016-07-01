package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginInfo;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.ReportGeneratorAsync;
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;

public class PartnerboerseReport implements EntryPoint {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private ReportGeneratorAsync reportGeneratorAsync = ClientsideSettings.getReportGenerator();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	Command goBack = new Command() {
		public void execute() {
			Window.Location.replace("Partnerboerse.html");
		}
	};

	@Override
	public void onModuleLoad() {

		final VerticalPanel content = new VerticalPanel();
		MenuBar backToMenu = new MenuBar(true);
		backToMenu.addItem("Zurück zum Hauptmenü", goBack);
		MenuBar reportMenu = new MenuBar(true);

		MenuBar menu = new MenuBar();
		menu.addItem("Zurück", backToMenu);
		menu.addItem("Report", reportMenu);

		// buttonausgabe für den Testzweck
		final VerticalPanel hbuttons = new VerticalPanel();
		final HorizontalPanel hcontent = new HorizontalPanel();
		final VerticalPanel vcontent = new VerticalPanel();
		vcontent.setVisible(false);
		hcontent.add(vcontent);
		final Panel reportPanel = new VerticalPanel();
		hcontent.add(reportPanel);
		final Button profileProposals = new Button("Noch nicht besuchte Profile");
		final Button profileProposalsBySearchProfile = new Button("Profilvorschläge anhand aller " + "Suchprofile");
		final Button singleSearchProfileReports = new Button("Profilvorschläge anhand eines Suchprofils");

		Command showReport = new Command() {
			public void execute() {
				vcontent.setVisible(false);
				reportPanel.setVisible(false);
			}
		};
		reportMenu.addItem("Report ansehen", showReport);
		
		hbuttons.setStyleName("hbuttons");
		hcontent.setStyleName("hcontent");

		profileProposals.setStyleName("buttonwidth");
		profileProposalsBySearchProfile.setStyleName("buttonwidth");
		singleSearchProfileReports.setStyleName("buttonwidth");

		hbuttons.add(profileProposals);
		hbuttons.add(profileProposalsBySearchProfile);
		hbuttons.add(singleSearchProfileReports);

		RootPanel.get("Buttonzone").clear();
		RootPanel.get("Contentzone").clear();
		RootPanel.get("Buttonzone").add(hbuttons);
		RootPanel.get("Contentzone").add(hcontent);

		RootPanel.get("Navigator").add(menu);

		final CellTable<SearchProfile> table = new CellTable<SearchProfile>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final VerticalPanel selectSearchProfile = new VerticalPanel();
		final ListDataProvider<SearchProfile> dataProvider = new ListDataProvider<>();
		dataProvider.addDataDisplay(table);

		final TextColumn<SearchProfile> searchProfileName = new TextColumn<SearchProfile>() {

			@Override
			public String getValue(SearchProfile sp) {
				return sp.getName();
			}
		};
		table.addColumn(searchProfileName, "Suchprofil Name");

		final SingleSelectionModel<SearchProfile> selectionModel = new SingleSelectionModel<SearchProfile>();
		table.setSelectionModel(selectionModel);

		final Button selectionForReport = new Button("Report generieren");
		selectionForReport.setVisible(false);
		selectSearchProfile.add(selectionForReport);
		selectionForReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportPanel.setVisible(true);
				ArrayList<SearchProfile> selectedArray = new ArrayList<SearchProfile>();
				selectedArray.add(selectionModel.getSelectedObject());
				reportGeneratorAsync.renderPartnerProposalsBySearchProfilesReport(selectedArray,
						new AsyncCallback<String>() {
							@Override
							public void onSuccess(String result) {
								reportPanel.clear();
								reportPanel.add(new HTML(result));
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});

		singleSearchProfileReports.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				vcontent.setVisible(true);
				reportPanel.setVisible(false);
			}
		});
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				final SearchProfile selected = selectionModel.getSelectedObject();
				if (selected != null) {
					selectionForReport.setVisible(true);
				} else {
					selectionForReport.setVisible(false);
				}
			}
		});

		loginService.login(Window.Location.getHref(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fail");
			}

			@Override
			public void onSuccess(LoginInfo loginInfo) {
				Profile result = loginInfo.getProfile();
				if (!loginInfo.isLoggedIn()) {
					Window.Location.assign(loginInfo.getLoginUrl());
				}

				partnerboerseVerwaltung.getSearchProfileOf(result, new AsyncCallback<ArrayList<SearchProfile>>() {

					@Override
					public void onSuccess(ArrayList<SearchProfile> result) {
						dataProvider.getList().clear();
						dataProvider.getList().addAll(result);
						dataProvider.flush();
						dataProvider.refresh();
						table.redraw();
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}

		});

		vcontent.add(table);
		vcontent.add(selectSearchProfile);
		hcontent.setWidth("400");
		// hcontent.add(buttonPanel);

		RootPanel.get("Contentzone").clear();
		RootPanel.get("Contentzone").add(hcontent);

		profileProposalsBySearchProfile.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				vcontent.setVisible(false);
				reportPanel.setVisible(true);
				reportGeneratorAsync.renderPartnerProposalsBySearchProfilesReport(null, new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						reportPanel.clear();
						HTML html = new HTML(result);
						reportPanel.add(html);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			}
		});

		profileProposals.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				vcontent.setVisible(false);
				reportPanel.setVisible(true);
				
				// Instantiate the dialog box and show it.

				reportGeneratorAsync.renderPartnerProposalsByNotViewedProfilesReport(new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String result) {
						reportPanel.clear();
						HTML html = new HTML(result);
						reportPanel.add(html);

					}
				});

				// int left = Window.getClientWidth()/ 2;
				// int top = Window.getClientHeight()/ 2;
				// myDialog.setPopupPosition(left, top);
				// myDialog.show();
			}
		});
	}
}

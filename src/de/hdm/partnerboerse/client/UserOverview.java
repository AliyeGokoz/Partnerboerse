package de.hdm.partnerboerse.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.server.LoginServiceImpl;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.VisitList;

public class UserOverview extends HorizontalPanel {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	@Override
	public void onLoad() {

		final CellTable<Profile> table = new CellTable<Profile>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final VerticalPanel seeAllUsers = new VerticalPanel();
		final VerticalPanel buttonPanel = new VerticalPanel();

		partnerboerseVerwaltung.getAllProfiles(new AsyncCallback<ArrayList<Profile>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Profile> result) {

				final TextColumn<Profile> firstNameColumn = new TextColumn<Profile>() {

					@Override
					public String getValue(Profile p) {
						return p.getFirstName();
					}

				};
				table.addColumn(firstNameColumn, "Name");

				final TextColumn<Profile> lastNameColumn = new TextColumn<Profile>() {
					@Override
					public String getValue(Profile p) {
						return p.getLastName();
					}
				};
				table.addColumn(lastNameColumn, "Nachname");

				final TextColumn<Profile> emailColumn = new TextColumn<Profile>() {
					@Override
					public String getValue(Profile p) {
						return p.geteMail();
					}
				};
				table.addColumn(emailColumn, "Email");
				final SingleSelectionModel<Profile> selectionModel = new SingleSelectionModel<Profile>();
				table.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						final Profile selected = selectionModel.getSelectedObject();
						if (selected != null) {
							GWT.log("bdbddjd");
							seeAllUsers.clear();
//							saveUsedProfile(visitList, profile, selected);							
							OtherUserProfilePage showProfile = new OtherUserProfilePage();
							seeAllUsers.add(showProfile.showProfileofUser(selected));
						}
//							Window.alert(
//									"You selected:" + " " + selected.getFirstName() + " " + selected.getLastName());
//							final Button saveToFavoritesList = new Button("Zum Merkzettel hinzufügen");
//							final Button saveToBlockingList = new Button("Kontakt sperren");
//
//							buttonPanel.add(saveToBlockingList);
//							buttonPanel.add(saveToFavoritesList);
//							
//							saveToBlockingList.addClickHandler(new ClickHandler() {
//
//								@Override
//								public void onClick(ClickEvent event) {
//								
//									loginService.getCurrentProfile(new AsyncCallback<Profile>() {
//										
//										@Override
//										public void onSuccess(Profile result) {
//											//Window.alert("Kontakt gesperrt");
//											partnerboerseVerwaltung.createBlocking(result, selected, new AsyncCallback<Blocking>() {
//												
//												@Override
//												public void onSuccess(Blocking result) {
//													Window.alert("Sie haben den Kontakt" +" " + selected.getFirstName() + " " + selected.getFirstName() + " " + "gesperrt" );
//													buttonPanel.clear();
//												}
//												
//												@Override
//												public void onFailure(Throwable caught) {
//													// TODO Auto-generated method stub
//													
//												}
//											});
//
//										}
//										
//										@Override
//										public void onFailure(Throwable caught) {
//											// TODO Auto-generated method stub
//											
//										}
//									});
//								}
//								
//							});
//							
//							saveToFavoritesList.addClickHandler(new ClickHandler() {
//								
//								@Override
//								public void onClick(ClickEvent event) {
//									loginService.getCurrentProfile(new AsyncCallback<Profile>() {
//										@Override
//										public void onSuccess(Profile result) {
//											Window.alert("Erfolgreich zum Merkzettel hinzugefügt");
//											partnerboerseVerwaltung.createFavoritesList(result, selected,
//													new AsyncCallback<FavoritesList>() {
//
//														@Override
//														public void onSuccess(FavoritesList result) {
//															buttonPanel.clear();
//														}
//
//														@Override
//														public void onFailure(Throwable caught) {
//															// TODO
//															// Auto-generated
//															// method stub
//
//														}
//													});
//
//										}
//
//										@Override
//										public void onFailure(Throwable caught) {
//											// TODO Auto-generated method stub
//
//										}
//									});
//								}
//							});
//						}
					}
				});

				table.setRowData(result);

//				final VerticalPanel seeAllUsers = new VerticalPanel();
				seeAllUsers.add(table);
				seeAllUsers.setWidth("400");
				seeAllUsers.add(buttonPanel);

				RootPanel.get("Content").add(seeAllUsers);
			}

		});
	}

	
	public void saveUsedProfile(final VisitList visitList, final Profile profile){
		
		
		partnerboerseVerwaltung.save(visitList, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}

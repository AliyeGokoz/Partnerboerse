package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Profile;

public class FavoritListOverview extends VerticalPanel {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	@Override
	public void onLoad() {
		final CellTable<FavoritesList> table = new CellTable<FavoritesList>();
		final ListDataProvider<FavoritesList> dataProvider = new ListDataProvider<>();
		dataProvider.addDataDisplay(table);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final VerticalPanel seeAllUsers = new VerticalPanel();
		final VerticalPanel buttonPanel = new VerticalPanel();
		
		
		
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
			
			@Override
			public void onSuccess(Profile result) {
				partnerboerseVerwaltung.getFavoritesListsOf(result, new AsyncCallback<ArrayList<FavoritesList>>() {
					
					@Override
					public void onSuccess(ArrayList<FavoritesList> result) {
						final TextColumn<FavoritesList> firstNameColumn = new TextColumn<FavoritesList>() {
							
							@Override
							public String getValue(FavoritesList f) {
								return f.getToProfile().getFirstName();
							}
						};
						table.addColumn(firstNameColumn, "Vorname");
						
						final TextColumn<FavoritesList> lastNameColumn = new TextColumn<FavoritesList>() {
							
							@Override
							public String getValue(FavoritesList f) {
								return f.getToProfile().getLastName();
							}
						};
						table.addColumn(lastNameColumn, "Nachname");
						
						final TextColumn<FavoritesList> emailColumn = new TextColumn<FavoritesList>() {
							
							@Override
							public String getValue(FavoritesList f) {
								return f.getToProfile().geteMail();
							}
						};
						table.addColumn(emailColumn, "Email");
						
						final SingleSelectionModel<FavoritesList> selectionModel = new SingleSelectionModel<FavoritesList>();
						table.setSelectionModel(selectionModel);
						selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
							public void onSelectionChange(SelectionChangeEvent event) {
								final FavoritesList selected = selectionModel.getSelectedObject();
								if (selected != null) {
									Window.alert(
											"You selected:" + " " + selected.getToProfile().getFirstName() + " " + selected.getToProfile().getLastName());
									final Button delteFromFavoritesList = new Button("Aus dem Merkzettel entfernen");
									buttonPanel.add(delteFromFavoritesList);
									delteFromFavoritesList.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											partnerboerseVerwaltung.delete(selected, new AsyncCallback<Void>() {
									

												@Override
												public void onFailure(Throwable caught) {
													// TODO Auto-generated method stub

												}

												@Override
												public void onSuccess(Void result) {
													dataProvider.getList().remove(selected);
													dataProvider.flush();
													dataProvider.refresh();
													table.redraw();
													Window.alert("Favorit wurde entfernt !");
													buttonPanel.clear();
												}
											});
										}
									});
								}
							}
						});

						dataProvider.getList().addAll(result);

//						final VerticalPanel seeAllUsers = new VerticalPanel();
						seeAllUsers.add(table);
						seeAllUsers.setWidth("400");
						seeAllUsers.add(buttonPanel);
						
						RootPanel.get("Content").clear();
						RootPanel.get("Content").add(seeAllUsers);

					}
					
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	

//	
//
//
//		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
//
//			@Override
//			public void onSuccess(Profile currentProfile) {
//				partnerboerseVerwaltung.getFavoritesListsOf(currentProfile,
//						new AsyncCallback<ArrayList<FavoritesList>>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void onSuccess(ArrayList<FavoritesList> favoritsResult) {
//								int i = 1;
//								for (final FavoritesList f : favoritsResult) {
//									favoritesFlexTable.setText(i, 0, f.getToProfile().getFirstName());
//									favoritesFlexTable.setText(i, 1, f.getToProfile().getLastName());
//									i++;
//								}
//							}
//						});
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//	}
	
}
}


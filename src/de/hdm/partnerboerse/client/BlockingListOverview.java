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
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.Profile;

public class BlockingListOverview extends VerticalPanel{
	
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	ListDataProvider<Blocking> dataProvider = new ListDataProvider<>();


	@Override
	public void onLoad() {
		final CellTable<Blocking> table = new CellTable<Blocking>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final VerticalPanel seeAllUsers = new VerticalPanel();
		final VerticalPanel buttonPanel = new VerticalPanel();
		final ListDataProvider<Blocking> dataProvider = new ListDataProvider<>();
		dataProvider.addDataDisplay(table);

		
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
			
			@Override
			public void onSuccess(Profile result) {
				partnerboerseVerwaltung.getBlockingsOf(result, new AsyncCallback<ArrayList<Blocking>>() {
					
					@Override
					public void onSuccess(ArrayList<Blocking> result) {
						final TextColumn<Blocking> firstNameColumn = new TextColumn<Blocking>() {
							
							@Override
							public String getValue(Blocking b) {
								return b.getToProfile().getFirstName();
							}
						};
						table.addColumn(firstNameColumn, "Vorname");
						
						final TextColumn<Blocking> lastNameColumn = new TextColumn<Blocking>() {
							
							@Override
							public String getValue(Blocking b) {
								return b.getToProfile().getLastName();
							}
						};
						table.addColumn(lastNameColumn, "Nachname");
						
						final TextColumn<Blocking> emailColumn = new TextColumn<Blocking>() {
							
							@Override
							public String getValue(Blocking b) {
								return b.getToProfile().geteMail();
							}
						};
						table.addColumn(emailColumn, "Email");
						
						final SingleSelectionModel<Blocking> selectionModel = new SingleSelectionModel<Blocking>();
						table.setSelectionModel(selectionModel);
						selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
							public void onSelectionChange(SelectionChangeEvent event) {
								final Blocking selected = selectionModel.getSelectedObject();
								if (selected != null) {
									final Button delteFromFavoritesList = new Button("Kontaktsperre aufheben");
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
													Window.alert("Kontaktsperre wurde aufgehoben !");
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
	
	}


}

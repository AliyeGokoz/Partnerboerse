package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
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

/**
 * Klasse für die Ausgabe aller gemerkten Profile
 * @author aliyegokoz
 *
 */
public class FavoritListOverview extends VerticalPanel {
	
	public interface CellTableResource extends CellTable.Resources {

	    /**
	       * The styles applied to the table.
	       */
	    interface CellTableStyle extends CellTable.Style {
	    }

	    @Override
	    @Source({ CellTable.Style.DEFAULT_CSS, "CellTable.css" })
	    CellTableStyle cellTableStyle();

	}
	
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	@Override
	public void onLoad() {
		
		/*
		 * Panel anlegen 
		 */
		final VerticalPanel favoritPanel = new VerticalPanel();
		final VerticalPanel seeAllUsers = new VerticalPanel();
		final VerticalPanel buttonPanel = new VerticalPanel();
		
		/*
		 * Style 
		 */
		seeAllUsers.setStyleName("infospanelprof");
		favoritPanel.setStyleName("panelscenter");
		
		/*
		 * CellTable wird generiert für die Ausgabe
		 */
		
		CellTableResource resource = GWT.create(CellTableResource.class);
		final CellTable<FavoritesList> table = new CellTable<FavoritesList>(10,resource);
		final ListDataProvider<FavoritesList> dataProvider = new ListDataProvider<>();
		dataProvider.addDataDisplay(table);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		/*
		 * Zugriff auf Profil
		 */
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {
			
			@Override
			public void onSuccess(Profile result) {
				
				/*
				 * Zugriff auf alle gemerkten Profile
				 */
				partnerboerseVerwaltung.getFavoritesListsOf(result, new AsyncCallback<ArrayList<FavoritesList>>() {
					
					@Override
					public void onSuccess(ArrayList<FavoritesList> result) {
						
						/*
						 * Column für die Ausgabe aller gemerkten Profile
						 */
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
						
						/*
						 * SingleSelectionModel zuweisen,
						 * damit Profil markiert und gegebenfalls aus der Liste entfernt werden kann
						 */
						final SingleSelectionModel<FavoritesList> selectionModel = new SingleSelectionModel<FavoritesList>();
						table.setSelectionModel(selectionModel);
						selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
							public void onSelectionChange(SelectionChangeEvent event) {
								final FavoritesList selected = selectionModel.getSelectedObject();
								if (selected != null) {
									
									/*
									 * Aus der Liste löschen
									 */
									final Button delteFromFavoritesList = new Button("Aus dem Merkzettel entfernen");
									delteFromFavoritesList.setStyleName("button");
									
									buttonPanel.add(delteFromFavoritesList);
									delteFromFavoritesList.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											partnerboerseVerwaltung.delete(selected, new AsyncCallback<Void>() {
									

												@Override
												public void onFailure(Throwable caught) {
													Window.alert("Profil konnte nicht gelöscht werden.");

												}

												@Override
												public void onSuccess(Void result) {
													dataProvider.getList().remove(selected);
													dataProvider.flush();
													dataProvider.refresh();
													table.redraw();
													buttonPanel.clear();
												}
											});
										}
									});
								}
							}
						});

						dataProvider.getList().addAll(result);

						seeAllUsers.add(table);
						seeAllUsers.setWidth("400");
						
						favoritPanel.add(seeAllUsers);
						favoritPanel.add(buttonPanel);
						
						RootPanel.get("Content").clear();
						RootPanel.get("Content").add(favoritPanel);

					}
					
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Profile können nicht ausgegeben werden.");
						
					}
				});
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("");
				
			}
		});
	
}
}


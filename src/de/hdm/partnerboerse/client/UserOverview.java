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

/**
 * Klasse die alle Profile ausgibt
 * @author aliyegokoz
 *
 */
public class UserOverview extends HorizontalPanel {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	@Override
	public void onLoad() {

		/*
		 * VerticalPanel anlegen
		 */
		final VerticalPanel seeAllUsers = new VerticalPanel();
		
		/*
		 * Style
		 */
		seeAllUsers.setStyleName("allusersstyle");
		
		/*
		 * Celltable für die Ausgabe
		 */
		final CellTable<Profile> table = new CellTable<Profile>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		/*
		 * Zugriff auf alle Profile
		 */
		partnerboerseVerwaltung.getAllProfilesFiltered(new AsyncCallback<ArrayList<Profile>>() {

			@Override
			public void onFailure(Throwable caught) {
				

			}

			@Override
			public void onSuccess(ArrayList<Profile> result) {

				/*
				 * Columns für die Ausgabe
				 */
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
				
				/*
				 * SingleSelectionModel zuweisen damit, Profile ausgewählt werden können
				 */
				final SingleSelectionModel<Profile> selectionModel = new SingleSelectionModel<Profile>();
				table.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						final Profile selected = selectionModel.getSelectedObject();
						if (selected != null) {
							seeAllUsers.clear();
							setVisited(selected);							
							OtherUserProfilePage showProfile = new OtherUserProfilePage();
							seeAllUsers.add(showProfile.showProfileofUser(selected));
						}
					}
				});

				table.setRowData(result);

				seeAllUsers.add(table);
				seeAllUsers.setWidth("400");

				RootPanel.get("Content").add(seeAllUsers);
			}

		});
	}

	
	/**
	 * Methode, welche besuchte Profile speichert
	 * @param profile
	 */
	public void setVisited(final Profile profile){
		partnerboerseVerwaltung.visit(profile, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Void result) {
			}
		});
	}

}

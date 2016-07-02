package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle.Source;
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

import de.hdm.partnerboerse.client.FavoritListOverview.CellTableResource;
import de.hdm.partnerboerse.client.FavoritListOverview.CellTableResource.CellTableStyle;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Klasse für die Ausgabe der Blockierten Kontakte
 * 
 * @author aliyegokoz
 *
 */
public class BlockingListOverview extends VerticalPanel {
	
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
	ListDataProvider<Blocking> dataProvider = new ListDataProvider<>();

	@Override
	public void onLoad() {

		/*
		 * Panel generieren
		 */
		final VerticalPanel blockingPanel = new VerticalPanel();
		final VerticalPanel seeAllUsers = new VerticalPanel();
		final VerticalPanel buttonPanel = new VerticalPanel();

		/*
		 * Style
		 */
		seeAllUsers.setStyleName("infospanelprof");
		blockingPanel.setStyleName("panelscenter");

		/*
		 * CellTable generieren damit Blockierte Kontakte ausgegeben werden
		 * können
		 */
		CellTableResource resource = GWT.create(CellTableResource.class);
		final CellTable<Blocking> table = new CellTable<Blocking>(10,resource);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final ListDataProvider<Blocking> dataProvider = new ListDataProvider<>();
		dataProvider.addDataDisplay(table);

		/*
		 * Columns für die Ausgabe der Profile
		 */
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

		/*
		 * Ein Single Selection Model damit User ausgewählt werden können
		 */
		final SingleSelectionModel<Blocking> selectionModel = new SingleSelectionModel<Blocking>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				final Blocking selected = selectionModel.getSelectedObject();
				if (selected != null) {
					/*
					 * Wenn ein Profil ausgweählt ist und auf den Löschen Button
					 * gedrückt wird wird der User wieder entfernt aus der Liste
					 */
					final Button deleteFromBlockingList = new Button("Kontaktsperre aufheben");
					deleteFromBlockingList.setStyleName("button");

					buttonPanel.add(deleteFromBlockingList);
					deleteFromBlockingList.addClickHandler(new ClickHandler() {

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
									buttonPanel.clear();

								}
							});
						}
					});
				}
			}
		});

		seeAllUsers.add(table);
		seeAllUsers.setWidth("400");

		blockingPanel.add(seeAllUsers);
		blockingPanel.add(buttonPanel);

		RootPanel.get("Content").add(blockingPanel);

		/*
		 * zugriff auf User
		 */
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {

			@Override
			public void onSuccess(Profile result) {
				partnerboerseVerwaltung.getBlockingsOf(result, new AsyncCallback<ArrayList<Blocking>>() {

					@Override
					public void onSuccess(ArrayList<Blocking> result) {
						dataProvider.getList().clear();
						dataProvider.getList().addAll(result);
						dataProvider.flush();
						dataProvider.refresh();
						table.redraw();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Blockierte Profile können nicht ausgegeben werden.");

					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Profil konnte nicht ausgegeben werden.");

			}
		});

	}

}

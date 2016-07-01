package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.SearchProfile;

/**
 * Klasse für die Ausgabe der Informationen
 * des Suchprofiles
 * @author aliyegokoz
 * @author alena gerlinskaja
 *
 */
public class ShowInfoOfSearchProfile {

	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();
	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();

	/*
	 * CellList für die Ausgabe der Informationen
	 */
	final CellTable<Info> infoTable = new CellTable<>();
	ListDataProvider<Info> dataProvider = new ListDataProvider<>();

	/*
	 * Panel für die Ausgabe der Informationen
	 */
	final VerticalPanel infosPanel = new VerticalPanel();

	/*
	 * Button zum Löschen von Informationen
	 */
	final Button deleteButton = new Button("<img src='images/delete.png'/>");
	
	/**
	 * Methode generiert die CellList und die Ausgabe der 
	 * Informationen des Suchprofiles
	 * @param searchProfile
	 * @return
	 */
	public Widget showInfoOfSearchProfile(final SearchProfile searchProfile){
		
		/*
		 * Style 
		 */
		deleteButton.setStyleName("button");
		
		/*
		 * Zugriff auf alle Infos
		 */
		partnerboerseVerwaltung.getInfosOf(searchProfile, new AsyncCallback<ArrayList<Info>>() {

			@Override
			public void onSuccess(ArrayList<Info> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				dataProvider.flush();
				dataProvider.refresh();
				infoTable.redraw();

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		/*
		 * Generieren der CellTable
		 */
		dataProvider.addDataDisplay(infoTable);

		TextColumn<Info> nameInfo = new TextColumn<Info>() {

			@Override
			public String getValue(Info info) {
				if (info.getDescription() != null) {
					return info.getDescription().getTextualDescriptionForSearchProfile();
				} else {
					return info.getSelection().getTextualDescriptionForSearchProfile();
				}
			}
		};

		TextColumn<Info> valueColumn = new TextColumn<Info>() {

			@Override
			public String getValue(Info info) {
				return info.getInformationValue();
			}
		};

		/*
		 * Columns der Tabelle zuweisen, für die Ausgabe der Informationen
		 */
		infoTable.addColumn(nameInfo);
		infoTable.addColumn(valueColumn);

		infosPanel.add(infoTable);
		infosPanel.add(deleteButton);

		deleteInfo();
		return infosPanel;
	}
	
	/**
	 * Methode zum Löschen Ausgewählten Information
	 */
	public void deleteInfo(){
		final SingleSelectionModel<Info> selectionInfo = new SingleSelectionModel<Info>();
		infoTable.setSelectionModel(selectionInfo);
		
		deleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final Info selected = selectionInfo.getSelectedObject();
				if(selected != null){
					partnerboerseVerwaltung.delete(selected, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							dataProvider.getList().remove(selected);
							dataProvider.flush();
							dataProvider.refresh();
							infoTable.redraw();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Information konnte nicht gelöscht werden.");
							
						}
					});
				}
				
			}
		});
	}
	
}

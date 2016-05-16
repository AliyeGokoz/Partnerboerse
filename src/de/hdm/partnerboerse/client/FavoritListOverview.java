package de.hdm.partnerboerse.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Profile;

public class FavoritListOverview extends VerticalPanel {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	@Override
	public void onLoad() {
		final VerticalPanel seeFavoritUsers = new VerticalPanel();
		final FlexTable favoritesFlexTable = new FlexTable();
		final ScrollPanel scrollPanel = new ScrollPanel(seeFavoritUsers);
		scrollPanel.setSize("500px", "480px");
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(scrollPanel);

		favoritesFlexTable.setText(0, 0, "Vorname");
		favoritesFlexTable.setText(0, 1, "Nachname");

		favoritesFlexTable.setCellPadding(6);

		seeFavoritUsers.add(favoritesFlexTable);

		RootPanel.get("Buttonzone").clear();
		RootPanel.get("Contentzone").clear();
		RootPanel.get("Contentzone").add(decoratorPanel);

		loginService.getCurrentProfile(new AsyncCallback<Profile>() {

			@Override
			public void onSuccess(Profile currentProfile) {
				partnerboerseVerwaltung.getFavoritesListsOf(currentProfile,
						new AsyncCallback<ArrayList<FavoritesList>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(ArrayList<FavoritesList> favoritsResult) {
								int i = 1;
								for (final FavoritesList f : favoritsResult) {
									favoritesFlexTable.setText(i, 0, f.getToProfile().getFirstName());
									favoritesFlexTable.setText(i, 1, f.getToProfile().getLastName());
								}
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

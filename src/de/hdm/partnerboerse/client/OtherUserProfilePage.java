package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.LoginService;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Profile;

public class OtherUserProfilePage {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	/**
	 * Panel anlegen für die Ausgabe
	 */
	final VerticalPanel showProfile = new VerticalPanel();

	// /**
	// * Anlegen von VerticalPanel für die Buttons
	// */
	// final VerticalPanel buttonsPanel = new VerticalPanel();

	/**
	 * Buttons anlegen für das hinzufügen von Profil in Merkzettel oder
	 * Konktaksperre
	 */
	final Button backButton = new Button("zurück");
	final Button saveToBlockingList = new Button("Profil sperren");
	final Button saveToFavoritesList = new Button("Profil merken");

	/**
	 * Flextabel für die Profil ausgabe
	 */
	final FlexTable showProfileofUser = new FlexTable();

	/**
	 * Labels für die Ausgabe der Inhalte
	 */
	final Label firstnameLabel = new Label();
	final Label lastnameLabel = new Label();
	final Label dateofBirthLabel = new Label();
	final Label emailLabel = new Label();
	final Label genderLabel = new Label();
	final Label heightLabel = new Label();
	final Label confessionLabel = new Label();
	final Label haircolorLabel = new Label();
	final Label smokeLaben = new Label();

	public Widget showProfileofUser(final Profile selected) {

		// style FlexTable
		showProfileofUser.setWidth("200");
		showProfileofUser.setCellSpacing(10);

		setDate(selected);

		// Label mit Inhalt füllen
		firstnameLabel.setText(selected.getFirstName());
		lastnameLabel.setText(selected.getLastName());
		emailLabel.setText(selected.geteMail());
		genderLabel.setText(selected.getGender().getName());
		heightLabel.setText(Integer.toString(selected.getHeight()));
		confessionLabel.setText(selected.getConfession().getName());
		haircolorLabel.setText(selected.getHairColor().getName());
		smokeLaben.setText(selected.isSmoker() ? "Ja" : "Nein");

		// FlexTable mi Inhalt füllen
		showProfileofUser.setHTML(0, 0, "<div>Vorname</div>");
		showProfileofUser.setWidget(0, 1, firstnameLabel);
		showProfileofUser.setHTML(1, 0, "Nachname");
		showProfileofUser.setWidget(1, 1, lastnameLabel);
		showProfileofUser.setHTML(2, 0, "Geburtsdatum");
		showProfileofUser.setWidget(2, 1, dateofBirthLabel);
		showProfileofUser.setHTML(3, 0, "Email");
		showProfileofUser.setWidget(3, 1, emailLabel);
		showProfileofUser.setHTML(4, 0, "Geschlecht");
		showProfileofUser.setWidget(4, 1, genderLabel);
		showProfileofUser.setHTML(5, 0, "Größe");
		showProfileofUser.setWidget(5, 1, heightLabel);
		showProfileofUser.setHTML(6, 0, "Religion");
		showProfileofUser.setWidget(6, 1, confessionLabel);
		showProfileofUser.setHTML(7, 0, "Haarfarbe");
		showProfileofUser.setWidget(7, 1, haircolorLabel);
		showProfileofUser.setHTML(8, 0, "Raucher");
		showProfileofUser.setWidget(8, 1, smokeLaben);

		// Panel anordnen
		showProfile.add(backButton);
		showProfile.add(showProfileofUser);
		showProfile.add(saveToBlockingList);
		showProfile.add(saveToFavoritesList);

		goBacktoUserOverview();
		onClickforBlockingandFavorit(selected);

		return showProfile;
	}

	public void setDate(final Profile selected) {
		try {
			DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
			String result = format.format(selected.getDateOfBirth());
			dateofBirthLabel.setText("" + result);
		} catch (Exception e) {
			// ignore
		}
	}

	public void goBacktoUserOverview() {
		backButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				UserOverview showUserOverview = new UserOverview();
				showProfile.clear();
				showProfile.add(showUserOverview);
			}
		});
	}

	public void onClickforBlockingandFavorit(final Profile selected) {

		saveToBlockingList.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				loginService.getCurrentProfile(new AsyncCallback<Profile>() {

					@Override
					public void onSuccess(Profile result) {
						partnerboerseVerwaltung.createBlocking(result, selected, new AsyncCallback<Blocking>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Blocking result) {
								Window.alert("Sie haben den Kontakt " + selected.getFirstName() + " gesperrt" );
								
							}
						});
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});
			}
		});
		
		saveToFavoritesList.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				loginService.getCurrentProfile(new AsyncCallback<Profile>() {

					@Override
					public void onFailure(Throwable caught) {
						
						
					}

					@Override
					public void onSuccess(Profile result) {
						partnerboerseVerwaltung.createFavoritesList(result, selected, new AsyncCallback<FavoritesList>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(FavoritesList result) {
								Window.alert("Sie haben den Kontakt " +  selected.getFirstName() + " zum Merkzettel hinzugefügt." );
								
							}
						});
						
					}
				});
				
			}
		});
	}

}

package de.hdm.partnerboerse.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministrationAsync;
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Profile;

/**
 * Klasse welche die Ansicht 
 * für Einzelne User Profile zeigt
 * @author aliyegokoz
 *
 */
public class OtherUserProfilePage {

	private PartnerboerseAdministrationAsync partnerboerseVerwaltung = ClientsideSettings.getPartnerboerseVerwaltung();
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();

	/*
	 * Panel anlegen für die Ausgabe
	 */
	final VerticalPanel showProfile = new VerticalPanel();


	/*
	 * Buttons anlegen für das hinzufügen von Profil in Merkzettel oder
	 * Konktaksperre
	 */
	final Button backButton = new Button("zurück");
	final Button saveToBlockingList = new Button("Profil sperren");
	final Button saveToFavoritesList = new Button("Profil merken");

	/*
	 * Flextabel für die Profil ausgabe
	 */
	final FlexTable showProfileofUser = new FlexTable();

	/*
	 * Widgets anlegen für die Ausgabe
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
	final Label orientLabel = new Label();
	final HTML name = new HTML("Vorname");
	final HTML lastname = new HTML("Nachname");
	final HTML birth = new HTML("Geburtsdatum");
	final HTML gender = new HTML("Geschlecht");
	final HTML orient = new HTML("Orientierung");
	final HTML conff = new HTML("Religion");
	final HTML smoke = new HTML("Raucher");
	final HTML email = new HTML("Email");
	final HTML height = new HTML("Größe");
	final HTML haircolor = new HTML("Haarfarbe");

	/**
	 * Methode, welche das in der Alle Profile Ansicht 
	 * ausgewählte Profil ausgibt
	 * @param selected
	 * @return
	 */
	public Widget showProfileofUser(final Profile selected) {

		/*
		 * Style
		 */
		showProfileofUser.setWidth("200");
		showProfileofUser.setCellSpacing(10);
		backButton.setStyleName("button");
		saveToBlockingList.setStyleName("button");
		saveToFavoritesList.setStyleName("button");
		name.setStyleName("labelstyle");
		lastname.setStyleName("labelstyle");
		email.setStyleName("labelstyle");
		gender.setStyleName("labelstyle");
		height.setStyleName("labelstyle");
		conff.setStyleName("labelstyle");
		orient.setStyleName("labelstyle");
		smoke.setStyleName("labelstyle");
		haircolor.setStyleName("labelstyle");
		birth.setStyleName("labelstyle");

		/*
		 * Datum umformatieren
		 */
		getDate(selected);

		/*
		 * Label mit Inhalt füllen
		 */
		firstnameLabel.setText(selected.getFirstName());
		lastnameLabel.setText(selected.getLastName());
		emailLabel.setText(selected.geteMail());
		genderLabel.setText(selected.getGender().getName());
		heightLabel.setText(Integer.toString(selected.getHeight()));
		confessionLabel.setText(selected.getConfession().getName());
		haircolorLabel.setText(selected.getHairColor().getName());
		smokeLaben.setText(selected.isSmoker() ? "Ja" : "Nein");
		orientLabel.setText(selected.getOrientation().getName());
		
		/*
		 *  FlexTable mi Inhalt füllen
		 */
		showProfileofUser.setWidget(0, 0, name);
		showProfileofUser.setWidget(0, 1, firstnameLabel);
		showProfileofUser.setWidget(1, 0, lastname);
		showProfileofUser.setWidget(1, 1, lastnameLabel);
		showProfileofUser.setWidget(2, 0, birth);
		showProfileofUser.setWidget(2, 1, dateofBirthLabel);
		showProfileofUser.setWidget(3, 0, email);
		showProfileofUser.setWidget(3, 1, emailLabel);
		showProfileofUser.setWidget(4, 0, gender);
		showProfileofUser.setWidget(4, 1, genderLabel);
		showProfileofUser.setWidget(5, 0, height);
		showProfileofUser.setWidget(5, 1, heightLabel);
		showProfileofUser.setWidget(6, 0, conff);
		showProfileofUser.setWidget(6, 1, confessionLabel);
		showProfileofUser.setWidget(7, 0, haircolor);
		showProfileofUser.setWidget(7, 1, haircolorLabel);
		showProfileofUser.setWidget(8, 0, smoke);
		showProfileofUser.setWidget(8, 1, smokeLaben);
		showProfileofUser.setWidget(9, 0, orient);
		showProfileofUser.setWidget(9, 1, orientLabel);

		/*
		 *  Panel anordnen
		 */
		showProfile.add(backButton);
		showProfile.add(showProfileofUser);
		showProfile.add(saveToBlockingList);
		showProfile.add(saveToFavoritesList);

		/*
		 * Methode zurück zur Allen Usern
		 */
		goBacktoUserOverview();
		
		/*
		 * Methode zum Hinzufügen zur Kontaktsperre 
		 * und Favorits
		 */
		onClickforBlockingandFavorit(selected);

		return showProfile;
	}

	/**
	 * Methode formatiert das Datum 
	 * ins Deutsche 
	 */
	public void getDate(final Profile selected) {
		try {
			DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
			String result = format.format(selected.getDateOfBirth());
			dateofBirthLabel.setText("" + result);
		} catch (Exception e) {
			// ignore
		}
	}

	/**
	 * Methode zurück zu Allen Userprofilen
	 */
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

	/**
	 * Methode zum Hinzufügen des Ausgewählten Profiles in
	 * in die Kontaktsperre oder Merkzettel
	 * @param selected
	 */
	public void onClickforBlockingandFavorit(final Profile selected) {

		/*
		 * Speichern in die Kontaktsperre
		 */
		saveToBlockingList.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				loginService.getCurrentProfile(new AsyncCallback<Profile>() {

					@Override
					public void onSuccess(Profile result) {
						partnerboerseVerwaltung.createBlocking(result, selected, new AsyncCallback<Blocking>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Profil konnte nicht Blockiert werden.");
								
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
		
		/*
		 * Speichern in den Merkzettel
		 */
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

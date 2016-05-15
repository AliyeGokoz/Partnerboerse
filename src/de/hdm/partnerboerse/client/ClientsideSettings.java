package de.hdm.partnerboerse.client;

import com.google.gwt.core.client.GWT;

import de.hdm.partnerboerse.shared.*;

public class ClientsideSettings extends CommonSettings {

	private static LoginServiceAsync loginServiceAsync = null;

	public static LoginServiceAsync getLoginService() {
		if (loginServiceAsync == null) {
			loginServiceAsync = GWT.create(LoginService.class);
		}
		return loginServiceAsync;
	}

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
	 * Dienst namens <code>PartnerboerseAdministration</code>.
	 */
	private static PartnerboerseAdministrationAsync partnerboerseVerwaltung = null;

	public static PartnerboerseAdministrationAsync getPartnerboerseVerwaltung() {
		if (partnerboerseVerwaltung == null) {
			partnerboerseVerwaltung = GWT.create(PartnerboerseAdministration.class);
		}
		return partnerboerseVerwaltung;
	}

}

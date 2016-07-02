package de.hdm.partnerboerse.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.*;

/**
 * Beinhaltet alle Konfigurationen die nur auf dem Client gültig sind.
 * 
 * @author Carolin Elsner
 *
 */
public class ClientsideSettings extends CommonSettings {

	private static LoginServiceAsync loginServiceAsync = null;

	private static PartnerboerseAdministrationAsync partnerboerseVerwaltung = null;

	private static ReportGeneratorAsync reportGenerator = null;

	private static final String LOGGER_NAME = "Partnerboerse Web Client";

	private static final Logger log = Logger.getLogger(LOGGER_NAME);

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

	public static PartnerboerseAdministrationAsync getPartnerboerseVerwaltung() {
		if (partnerboerseVerwaltung == null) {
			partnerboerseVerwaltung = GWT
					.create(PartnerboerseAdministration.class);
		}
		return partnerboerseVerwaltung;
	}

	public static ReportGeneratorAsync getReportGenerator() {
		if (reportGenerator == null) {
			reportGenerator = GWT.create(ReportGenerator.class);
			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings
							.getLogger()
							.severe("Der ReportGenerator konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().severe(
							"Der ReportGenerator wurde initialisiert werden!");
				}

			};
			reportGenerator.init(initReportGeneratorCallback);
		}
		return reportGenerator;

	}

	protected static Logger getLogger() {
		return log;
	}
}

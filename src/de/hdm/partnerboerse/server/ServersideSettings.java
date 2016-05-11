package de.hdm.partnerboerse.server;

import java.util.logging.Logger;
import de.hdm.partnerboerse.shared.CommonSettings;

public class ServersideSettings extends CommonSettings {
	private static final String LOGGER_NAME = "Partnerboerse Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	public static Logger getLogger(){
		return log;
	}
			
		
	

}

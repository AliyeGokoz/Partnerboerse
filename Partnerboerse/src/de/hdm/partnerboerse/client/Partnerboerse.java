package de.hdm.partnerboerse.client;

import de.hdm.partnerboerse.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO
 */
public class Partnerboerse implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		VerticalPanel navigationPanel = new VerticalPanel();
		
		/*
		 * zuweisung von navigationPanel(VerticalPanel) an seinen Platz im HTML Element durch dessen ID
		 */
		RootPanel.get("Navigator").add(navigationPanel);
		
		/*
		 * Buttons für die Navigation
		 * TODO add correct Name of Button
		 */
		final Button example1 = new Button("example1");
		
		
		/*
	     * TODO add a nice CSS-formating
	     */
	    //buttonname.setStylePrimaryName("cssname");
		
		/*
	     * Hinzufügen des Buttons zum VerticalPanel.
	     */
	    navigationPanel.add(example1);

	}
}

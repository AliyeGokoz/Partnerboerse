package de.hdm.partnerboerse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.partnerboerse.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Partnerboerse implements EntryPoint {

	static interface ResourcesTree extends CellTree.Resources {
		@Override
		@Source("closedTree.gif")
	    ImageResource cellTreeClosedItem();

	    @Override
		@Source("openTree.gif")
	    ImageResource cellTreeOpenItem();

	    @Override
		@Source("CellTree.css")
	    CellTree.Style cellTreeStyle(); 
	}
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Make a command that we will execute from all leaves.
	    Command cmd = new Command() {
	      public void execute() {
	        Window.alert("You selected a menu item!");
	      }
	    };

	    // Make some sub-menus that we will cascade from the top menu.
	    MenuBar profilMenu = new MenuBar(true);

	    MenuBar searchprofilMenu = new MenuBar(true);

	    MenuBar favoritlistMenu = new MenuBar(true);
	    
	    MenuBar blockedcontactsMenu = new MenuBar(true);
	    
	    MenuBar partnerproposelMenu = new MenuBar(true);
	    partnerproposelMenu.addItem("Partnervorschläge Suchprofil", cmd);
	    partnerproposelMenu.addItem("Partnervorschläge Neu", cmd);

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("Profil", profilMenu);
	    menu.addItem("Suchprofil", searchprofilMenu);
	    menu.addItem("Merkzettel", favoritlistMenu);
	    menu.addItem("Kontaktsperre", blockedcontactsMenu);
	    menu.addItem("Personenvorschläge", partnerproposelMenu);

	    // Add it to the root panel.
	    RootPanel.get("Navigator").add(menu);
	  }
	}

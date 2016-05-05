package de.hdm.partnerboerse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * TODO
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
	    profilMenu.addItem("the", cmd);
	    profilMenu.addItem("foo", cmd);
	    profilMenu.addItem("menu", cmd);

	    MenuBar searchprofilMenu = new MenuBar(true);
	    searchprofilMenu.addItem("the", cmd);
	    searchprofilMenu.addItem("bar", cmd);
	    searchprofilMenu.addItem("menu", cmd);

	    MenuBar favoritlistMenu = new MenuBar(true);
	    favoritlistMenu.addItem("the", cmd);
	    favoritlistMenu.addItem("baz", cmd);
	    favoritlistMenu.addItem("menu", cmd);
	    
	    MenuBar blockedcontactsMenu = new MenuBar(true);
	    blockedcontactsMenu.addItem("the", cmd);
	    blockedcontactsMenu.addItem("baz", cmd);
	    blockedcontactsMenu.addItem("menu", cmd);
	    
	    MenuBar partnerproposelMenu = new MenuBar(true);
	    partnerproposelMenu.addItem("the", cmd);
	    partnerproposelMenu.addItem("baz", cmd);
	    partnerproposelMenu.addItem("menu", cmd);

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


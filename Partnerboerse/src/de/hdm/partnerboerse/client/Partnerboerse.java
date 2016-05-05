package de.hdm.partnerboerse.client;

import de.hdm.partnerboerse.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.resources.client.ImageResource;


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
	    MenuBar fooMenu = new MenuBar(true);
	    fooMenu.addItem("the", cmd);
	    fooMenu.addItem("foo", cmd);
	    fooMenu.addItem("menu", cmd);

	    MenuBar barMenu = new MenuBar(true);
	    barMenu.addItem("the", cmd);
	    barMenu.addItem("bar", cmd);
	    barMenu.addItem("menu", cmd);

	    MenuBar bazMenu = new MenuBar(true);
	    bazMenu.addItem("the", cmd);
	    bazMenu.addItem("baz", cmd);
	    bazMenu.addItem("menu", cmd);

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("foo", fooMenu);
	    menu.addItem("bar", barMenu);
	    menu.addItem("baz", bazMenu);

	    // Add it to the root panel.
	    RootPanel.get("Navigator").add(menu);
	  }
		
		/*
		 * Buttons für die Navigation
		 * TODO add correct Name of Button
		 */
		final Button searchprofile = new Button("Suchprofil");
		final Button bookmarks = new Button("Merkzettel");
		final Button partnerProposelnew = new Button("Partnervorschläge Neu");
		final Button partnerProposelsearch = new Button("Partnervorschläge Suchprofil");
		
		/*
	     * TODO add a nice CSS-formating
	     */
	    //buttonname.setStylePrimaryName("cssname");
		
		

	}


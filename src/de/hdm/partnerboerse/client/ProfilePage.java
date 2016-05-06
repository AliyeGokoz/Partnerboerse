package de.hdm.partnerboerse.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilePage extends VerticalPanel{

	@Override
	public void onLoad(){
		Label label1 = new Label("This is first GWT Label");
	    Label label2 = new Label("This is second GWT Label");

	      // use UIObject methods to set label properties.
	      label1.setTitle("Title for first Lable");
	      label2.setTitle("Title for second Lable");

	      // add labels to the root panel.
	      VerticalPanel panel = new VerticalPanel();
	      panel.add(label1);
	      panel.add(label2);

	      RootPanel.get("Content").clear();
	      RootPanel.get("Content").add(panel);
	}
}

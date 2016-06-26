package de.hdm.partnerboerse.shared.report;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paragraph value;


	public Column() {
	}
	
	
	/**
	 * 
	 */
	public Column(Paragraph s){
		this.value = s;
	}
	
	public Paragraph getVaulue(){
		return this.value;
	}
	
	public void setValue(Paragraph newValue){
		this.value = newValue;
	}


}

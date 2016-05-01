package de.hdm.partnerboerse.shared.report;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;


	public Column() {
	}
	
	public Column(String s){
		this.value = s;
	}
	
	public String getVaulue(){
		return this.value;
	}
	
	public void setValue(String newValue){
		this.value = newValue;
	}

	@Override
	public String toString() {
		return this.value;
	}
	

}

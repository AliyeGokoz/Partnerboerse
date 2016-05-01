package de.hdm.partnerboerse.shared.bo;

import java.io.Serializable;


public abstract class BusinessObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int iD = 0;
	
	public int getiD(){
		return this.iD;
	}
	
	public void setiD(int iD){
		this.iD = iD;
	}
	
	public String toString() {
	    return this.getClass().getName() + " #" + this.iD;
	  }

	public boolean equals(Object o) {
	    if (o != null && o instanceof BusinessObject) {
	    	BusinessObject bo = (BusinessObject) o;
	    	try {
	    		if (bo.getiD() == this.iD)
	    			return true;
	    	}
	    	catch (IllegalArgumentException e) {
	    		return false;
	    	}
	    }
	    return false;
	}
	public int hashCode() {
		return this.iD;
	}

}

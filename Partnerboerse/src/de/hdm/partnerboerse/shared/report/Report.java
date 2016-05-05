package de.hdm.partnerboerse.shared.report;

import java.io.Serializable;
import java.util.Date;

public abstract class Report implements Serializable {

	public static final long serialVersionUID = 1L;

	private Paragraph imprint = null;
	private Paragraph headerData = null;
	private String title = "Love24/7";
	private Date created = new Date();

	public Paragraph getImprint() {
		return imprint;
	}

	public void setImprint(Paragraph imprint) {
		this.imprint = imprint;
	}

	public Paragraph getHeaderData() {
		return headerData;
	}

	public void setHeaderData(Paragraph headerData) {
		this.headerData = headerData;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}

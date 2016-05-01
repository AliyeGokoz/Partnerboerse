package de.hdm.partnerboerse.shared.report;

public class SimpleParagraph extends Paragraph {

	private static final long serialVersionUID = 1L;

	String text="Hello";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return this.text.toString();
	}

}

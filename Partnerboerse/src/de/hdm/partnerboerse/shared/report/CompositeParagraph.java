package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

public class CompositeParagraph extends Paragraph {

	private static final long serialVersionUID = 1L;

	private Vector<SimpleParagraph> subParagraphs;

	public void addParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);
	}

	public void removeParagraph(SimpleParagraph p) {
		this.subParagraphs.remove(p);
	}

	public Vector<SimpleParagraph> getParagraph() {
		return this.subParagraphs;
	}

	public int getNumParagraphs() {
		return this.subParagraphs.size();

	}

	public SimpleParagraph getParagraphAt(int i) {
		return this.subParagraphs.elementAt(i);
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();

		for (int i = 0; i < subParagraphs.size(); i++) {
			SimpleParagraph p = this.subParagraphs.elementAt(i);

			b.append(p.toString() + "\n");
		}
		return b.toString();

	}

}

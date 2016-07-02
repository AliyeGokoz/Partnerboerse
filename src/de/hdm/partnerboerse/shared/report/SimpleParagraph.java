package de.hdm.partnerboerse.shared.report;

/**
 * Diese Klasse stellt einzelne Absätze dar, dessen Inhalt wird als String
 * gespeichert. 
 * 
 */

public class SimpleParagraph extends Paragraph {

	private static final long serialVersionUID = 1L;

	 /**
	   * Inhalt des Absatzes.
	   * @author alenagerlinskaja
	   */
	String text="Hello";
	
	
	/**
	 * No-Argument-Konstruktor
	 *
	 *@see #SimpleParagraph(String)
	 *@author alenagerlinskaja
	 */
	
	public SimpleParagraph(){
		
	}
	
	 /**
	   * Dieser Konstruktor, der bei der Instantiierung von
	   * <code>SimpleParagraph</code>-Objekten die Angabe von deren Inhalt ermöglicht.
	   * 
	   * @param value der Inhalt des Absatzes
	   * @see #SimpleParagraph()
	   * @author alenagerlinskaja
	   */
	
	public SimpleParagraph(String value){
		this.text = value;
	}

	 /**
	   * Auslesen des Inhalts.
	   * 
	   * @return Inhalt als String
	   * @author alenagerlinskaja
	   */
	
	public String getText() {
		return text;
	}

	 /**
	   * Überschreiben des Inhalts.
	   * 
	   * @param text der neue Inhalt des Absatzes.
	   * @author alenagerlinskaja
	   */
	
	public void setText(String text) {
		this.text = text;
	}

	/**
	   * Umwandeln des <code>SimpleParagraph</code>-Objekts in einen String.
	   * @author alenagerlinskaja
	   */
	
	@Override
	public String toString() {
		return this.text.toString();
	}

}

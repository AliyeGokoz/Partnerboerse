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
	   */
	String text="Hello";
	
	
	/**
	 * No-Argument-Konstruktor
	 *
	 *@see #SimpleParagraph(String)
	 */
	
	public SimpleParagraph(){
		
	}
	
	 /**
	   * Dieser Konstruktor, der bei der Instantiierung von
	   * <code>SimpleParagraph</code>-Objekten die Angabe von deren Inhalt ermöglicht.
	   * 
	   * @param value der Inhalt des Absatzes
	   * @see #SimpleParagraph()
	   */
	
	public SimpleParagraph(String value){
		this.text = value;
	}

	 /**
	   * Auslesen des Inhalts.
	   * 
	   * @return Inhalt als String
	   */
	
	public String getText() {
		return text;
	}

	 /**
	   * Überschreiben des Inhalts.
	   * 
	   * @param text der neue Inhalt des Absatzes.
	   */
	
	public void setText(String text) {
		this.text = text;
	}

	/**
	   * Umwandeln des <code>SimpleParagraph</code>-Objekts in einen String.
	   */
	
	@Override
	public String toString() {
		return this.text.toString();
	}

}

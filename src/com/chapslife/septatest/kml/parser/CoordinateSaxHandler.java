package com.chapslife.septatest.kml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CoordinateSaxHandler extends DefaultHandler {

	private boolean in_coordinatestag = false;
	private boolean in_linestringtag = false;
	private boolean in_kmltag = false; 
	private boolean in_placemarktag = false;
	
	private Coordinates coordinates = new Coordinates();

	
	/**
	 * @return the coordinates
	 */
	public Coordinates getParsedData() {
		return this.coordinates;
	}
	// ===========================================================
    // Methods
    // ===========================================================
    @Override
    public void startDocument() throws SAXException {
            this.coordinates = new Coordinates();
    }

    @Override
    public void endDocument() throws SAXException {
            // Nothing to do
    }
    /** Gets be called on opening tags like: 
	  * <tag> 
	  * Can provide attribute(s), when xml was like: 
	  * <tag attribute="attributeValue">*/ 
	 @Override 
	 public void startElement(String namespaceURI, String localName, 
	           String qName, Attributes atts) throws SAXException { 
	      if (localName.equals("kml")) { 
	           this.in_kmltag = true;
	      } else if (localName.equals("Placemark")) { 
	           this.in_placemarktag = true; 
	      } else if (localName.equals("LineString")) { 
	          this.in_linestringtag = true;  
	      } else if (localName.equals("coordinates")) {
	          this.in_coordinatestag = true;                        
	      }
	 } 
	 /** Gets be called on closing tags like: 
	  * </tag> */ 
	 @Override 
	 public void endElement(String namespaceURI, String localName, String qName) 
	           throws SAXException { 
	       if (localName.equals("kml")) {
	           this.in_kmltag = false; 
	       } else if (localName.equals("Placemark")) { 
	           this.in_placemarktag = false;
	       } else if (localName.equals("LineString")) { 
	           this.in_linestringtag = false; 
	       } else if (localName.equals("coordinates")) { 
	           this.in_coordinatestag = false;
	       }
	 } 
	 /** Gets be called on the following structure: 
	  * <tag>characters</tag> */ 
	 @Override 
	public void characters(char ch[], int start, int length) { 
	    if(this.in_coordinatestag){        
	        coordinates.setCoordinates(new String(ch,start,length));
	    }
	} 

}

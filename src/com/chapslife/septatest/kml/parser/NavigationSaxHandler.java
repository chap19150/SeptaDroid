package com.chapslife.septatest.kml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class NavigationSaxHandler extends DefaultHandler{ 
	
	// =========================================================== 
	 // Fields 
	 // =========================================================== 

	 private boolean in_kmltag = false; 
	 private boolean in_placemarktag = false; 
	 private boolean in_nametag = false;
	 private boolean in_descriptiontag = false;
	 private boolean in_geometrycollectiontag = false;
	 private boolean in_linestringtag = false;
	 private boolean in_pointtag = false;
	 private boolean in_coordinatestag = false;

	 private StringBuffer buffer;

	 private NavigationDataSet navigationDataSet = new NavigationDataSet(); 
	 private Coordinates coordinate = new Coordinates();
	// =========================================================== 
	 // Getter & Setter 
	 // =========================================================== 

	 public NavigationDataSet getParsedData() {
		 Log.d("ALLCOORDS",buffer.toString().trim());
		 
	      //navigationDataSet.getCurrentPlacemark().setCoordinates(buffer.toString().trim());
	     return this.navigationDataSet; 
	 } 
	// =========================================================== 
	 // Methods 
	 // =========================================================== 
	 @Override 
	 public void startDocument() throws SAXException { 
	      this.navigationDataSet = new NavigationDataSet(); 
	      
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
	      } else if (localName.equals("name")) { 
	           this.in_nametag = true;
	      } else if (localName.equals("description")) { 
	          this.in_descriptiontag = true;
	      } else if (localName.equals("GeometryCollection")) { 
	          this.in_geometrycollectiontag = true;
	      } else if (localName.equals("LineString")) { 
	          this.in_linestringtag = true;  
	          navigationDataSet.setCurrentLineString(new LineString());
	      } else if (localName.equals("point")) { 
	          this.in_pointtag = true;          
	      } else if (localName.equals("coordinates")) {
	          this.in_coordinatestag = true;
	          buffer = new StringBuffer();
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
	       } else if (localName.equals("name")) { 
	           this.in_nametag = false;           
	       } else if (localName.equals("description")) { 
	           this.in_descriptiontag = false;
	       } else if (localName.equals("GeometryCollection")) { 
	           this.in_geometrycollectiontag = false;
	       } else if (localName.equals("LineString")) { 
	           this.in_linestringtag = false; 
	           navigationDataSet.getCurrentLineString().setCoordinates(buffer.toString().trim());
	           navigationDataSet.addCurrentLineString();
	       } else if (localName.equals("point")) { 
	           this.in_pointtag = false;          
	       } else if (localName.equals("coordinates")) { 
	           this.in_coordinatestag = false;
	           
	       }
	 } 

	 /** Gets be called on the following structure: 
	  * <tag>characters</tag> */ 
	 @Override 
	public void characters(char ch[], int start, int length) { 
	   
	    if(this.in_coordinatestag){ 
	    	 if (navigationDataSet.getCurrentLineString()==null) navigationDataSet.setCurrentLineString(new LineString());
	    	buffer.append(new String(ch, start, length));
	    	
	    }
	} 
}

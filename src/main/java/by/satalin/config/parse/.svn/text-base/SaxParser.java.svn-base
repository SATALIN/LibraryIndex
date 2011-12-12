package by.satalin.config.parse;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser {
	 public static void main(String argv[]) {
		 
		    try {
		 
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
		 
			boolean id = false;
			boolean title = false;
			boolean author = false;
			boolean abonent = false;
			boolean date = false;
		 
			public void startElement(String uri, String localName,String qName, 
		                Attributes attributes) throws SAXException {
		 
				System.out.println("Start Element :" + qName);
		 
				if (qName.equalsIgnoreCase("id")) {
					id = true;
				}
		 
				if (qName.equalsIgnoreCase("title")) {
					title = true;
				}
		 
				if (qName.equalsIgnoreCase("author")) {
					author = true;
				}
		 
				if (qName.equalsIgnoreCase("abonent")) {
					abonent = true;
				}
				
				if (qName.equalsIgnoreCase("date")) {
					date = true;
				}
			}
		 
			public void endElement(String uri, String localName,
				String qName) throws SAXException {
		 
				System.out.println("End Element :" + qName);
		 
			}
		 
			public void characters(char ch[], int start, int length) throws SAXException {
		 
				if (id) {
					System.out.println("id : " + new String(ch, start, length));
					id = false;
				}
		 
				if (title) {
					System.out.println("title : " + new String(ch, start, length));
					title = false;
				}
		 
				if (author) {
					System.out.println("author : " + new String(ch, start, length));
					author = false;
				}
		 
				if (abonent) {
					System.out.println("abonent : " + new String(ch, start, length));
					abonent = false;
				}
				
				if (date) {
					System.out.println("date : " + new String(ch, start, length));
					date = false;
				}
			}
		 
		     };
		 
		       saxParser.parse("C:/Users/alexander.salin/workspace/LibraryIndex/src/by/satalin/config/try.xml", handler);
		 
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		 
		   }
}

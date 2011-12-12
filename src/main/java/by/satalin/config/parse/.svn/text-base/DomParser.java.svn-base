package by.satalin.config.parse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DomParser {
	private static final int ZERO = 0;
	private Document doc;

	public DomParser(final String xmlFile) throws ParserConfigurationException, SAXException, IOException{
		parse(xmlFile);
	}
	private void parse(final String xmlFile) throws ParserConfigurationException, SAXException, IOException{
		File XmlFile = new File(xmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(XmlFile);
		doc.getDocumentElement().normalize();
	}
	
	public NodeList getElementsByTag(String tag){
		return doc.getElementsByTagName(tag);
	}
	
	
	public Map<String,String> createMapFromNodeList(String nodeList, String key, String value) throws ParserConfigurationException{
		NodeList nodes =getElementsByTag(nodeList);
		Map<String,String> configMap = new HashMap<String, String>();
		if (nodes.getLength()==0) throw new ParserConfigurationException("List is empty NodeList ="+nodeList);
		for (int iter = 0; iter < nodes.getLength(); iter++){
			Node node = nodes.item(iter);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
                configMap.put(getTagValue(key, eElement),
                				getTagValue(value, eElement));
			}
		}
		return configMap;
	}

	
	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(ZERO)
				.getChildNodes();

		Node nValue = (Node) nlList.item(ZERO);

		return nValue.getNodeValue();
	}
}

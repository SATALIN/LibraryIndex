package by.satalin.config;

import java.io.IOException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import by.satalin.config.parse.DomParser;


public class Configuration {
	private static final String LOCATION = "location";
	private static final String TARGET = "target";
	private static final String PATH = "Path";
	private static final String VALUE = "value";
	private static final String KEY = "key";
	private static final String LINK = "Link";
	private static volatile Configuration instance;
	private Map<String, String> properties = null;
	public static final Logger log = Logger.getLogger(Configuration.class);

	private static final String configLocation = "C:/Users/alexander.salin/workspace/LibraryIndex/src/ApplicationConfig.xml";

	private Configuration() throws ParserConfigurationException, SAXException, IOException {

		log.warn("Create configuration :" + this.toString());
		log.warn("Create configuration hashcode:" + this.hashCode());
		DomParser parser = new DomParser(configLocation);
		properties = new HashMap<String, String>();
		properties = parser.createMapFromNodeList(LINK, KEY, VALUE);
		properties.putAll(parser.createMapFromNodeList(PATH, TARGET, LOCATION));

	}

	public synchronized static Configuration getInstance()  {
		if (instance == null) {
			synchronized (Configuration.class) {
				if (instance == null) {
					try {
						instance = new Configuration();
					} catch (Exception e) {
						instance = null;
						log.fatal(e);
					}
				}
			}
		}
		return instance;
	}

	public synchronized String getProperty(String key) {
		String value = null;
		if (properties.containsKey(key))
			value = properties.get(key);
		else {
			// TODO ???
			// throw new PropertyNotFoundException("key :"+key);
		}
		return value;
	}
}
package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigurationGetPropertyValues {
	
	public static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	private static final String TEXT_WIDTH = "TEXT_WIDTH";
	private static final String TEXT_HEIGHT = "TEXT_HEIGHT";
	private static final String TEXT_FIELD_LENGTH = "TEXT_FIELD_LENGTH";
	private static final String FRAME_WIDTH = "FRAME_WIDTH";
	private static final String FRAME_HEIGHT = "FRAME_HEIGHT";
	private static final String FRAME_TITLE = "FRAME_TITLE";
 
	public Map<String, String> getPropValues() throws IOException {
		Properties prop = new Properties();
		String propFileName = "config.properties";
		
		HashMap<String, String> newHash = null;
		
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {

			prop.load(inputStream);
 
			Date time = new Date(System.currentTimeMillis());
 
			newHash = new HashMap<>();
			
			newHash.put(TEXT_WIDTH, prop.getProperty(TEXT_WIDTH));
			newHash.put(TEXT_HEIGHT, prop.getProperty(TEXT_HEIGHT));
			newHash.put(TEXT_FIELD_LENGTH, prop.getProperty(TEXT_FIELD_LENGTH));
			newHash.put(FRAME_WIDTH, prop.getProperty(FRAME_WIDTH));
			newHash.put(FRAME_HEIGHT, prop.getProperty(FRAME_HEIGHT));
			newHash.put(FRAME_TITLE, prop.getProperty(FRAME_TITLE));
			
			logger.info(newHash + "\nProgram Ran on " + time + " with configurations =" + newHash.size());
		} catch (Exception e) {
			logger.error("Exception caught when reading configuration file" , e);
		}
		
		return newHash;
	}
}

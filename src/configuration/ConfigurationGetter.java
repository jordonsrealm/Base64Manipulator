package configuration;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ConfigurationGetter {

	public static final Logger logger = LogManager.getLogger(ConfigurationGetter.class);
	
	private Map<String, String> properties;
	private static final String TEXT_WIDTH = "TEXT_WIDTH";
	private static final String TEXT_HEIGHT = "TEXT_HEIGHT";
	private static final String TEXT_FIELD_LENGTH = "TEXT_FIELD_LENGTH";
	private static final String FRAME_WIDTH = "FRAME_WIDTH";
	private static final String FRAME_HEIGHT = "FRAME_HEIGHT";
	private static final String FRAME_TITLE = "FRAME_TITLE";
	
	
	public ConfigurationGetter() throws IOException {
		setPropertyValues();
	}
	
	public void setPropertyValues() throws IOException {
		ConfigurationGetPropertyValues propertiesValue = new ConfigurationGetPropertyValues();
		this.properties = propertiesValue.getPropValues();
	}
	
	public int getTextWidth() {
		return Integer.parseInt(properties.get(TEXT_WIDTH));
	}
	
	public int getTextHeight() {
		return Integer.parseInt(properties.get(TEXT_HEIGHT));
	}

	public int getFrameWidth() {
		return Integer.parseInt(properties.get(FRAME_WIDTH));
	}

	public int getFrameHeight() {
		return Integer.parseInt(properties.get(FRAME_HEIGHT));
	}

	public int getTextFieldLength() {
		return Integer.parseInt(properties.get(TEXT_FIELD_LENGTH));
	}
	
	public String getTitle() {
		return properties.get(FRAME_TITLE);
	}
}

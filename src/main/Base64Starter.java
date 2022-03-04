package main;


import java.io.IOException;
import javax.swing.SwingUtilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import configuration.ConfigurationGetter;


public class Base64Starter {
	
	static final Logger logger = LogManager.getLogger(Base64Starter.class);

	private ConfigurationGetter configurationGetter;
	private BaseFrameComponentHolder compHolder;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater( () -> {
				try {
					new Base64Starter();
				} catch (IOException e) {
					logger.error("Exception thrown when creating new BaseFrame.", e);
				}
			}
		);
	}
	
	public Base64Starter() throws IOException {
		configurationGetter = new ConfigurationGetter();
		
		compHolder = new BaseFrameComponentHolder(configurationGetter);
		compHolder.setFrameProperties();
		compHolder.createInputFields();
		compHolder.setPanelingStructure();
	}
	
}

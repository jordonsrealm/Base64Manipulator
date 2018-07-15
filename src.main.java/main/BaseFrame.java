package main;


import java.io.IOException;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import configuration.ConfigurationGetter;


public class BaseFrame {
	
	static final Logger logger = LogManager.getLogger(BaseFrame.class.getName());

	private ConfigurationGetter configurationGetter;
	private BaseFrameComponentHolder compHolder;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater( () -> {
				try {
					new BaseFrame();
				} catch (IOException e) {
					logger.error("Exception thrown when creating new BaseFrame.", e);
				}
			}
		);
	}
	
	public BaseFrame() throws IOException {
		configurationGetter = new ConfigurationGetter();
		
		compHolder = new BaseFrameComponentHolder(configurationGetter);
		compHolder.setFrameProperties();
		compHolder.createInputFields();
		compHolder.setPanelingStructure();
	}
	
}

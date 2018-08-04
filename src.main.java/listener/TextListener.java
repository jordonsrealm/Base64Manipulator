package listener;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.converter.Base64Translator;
import main.BaseFrameComponentHolder;


public class TextListener implements CaretListener{
	
	private static final Logger logger = LogManager.getLogger(TextListener.class.getName());
	private BaseFrameComponentHolder componentHolder;
	private int conversionType = 0;
	private String charSetName = "UTF-8";
	
	
	public TextListener(BaseFrameComponentHolder compHolder) {
		this.componentHolder = compHolder;
	}
	
	
	public int getConversionType() {
		return conversionType;
	}

	public void setConversionType(int conversionType) {
		this.conversionType = conversionType;
		updateConversionTextArea();
	}

	private void updateConversionTextArea() {
		String strToConvert = componentHolder.getOriginalTextString();
		byte[] dataBytes = null;
		
		try {
			dataBytes = strToConvert.getBytes(charSetName);
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception thrown when trying ot get bytes from text field", e);
		}
		
		
		try {
			Base64Translator dataTranslator = new Base64Translator(dataBytes);
			dataTranslator.setConversionType(conversionType);
			componentHolder.setConvertedTextString( dataTranslator.getConvertedStringBytes() );
			componentHolder.getDrawingPanel().updateComponentWithBytes( dataTranslator.getConvertedDataBytes() );
		} catch(Exception ex) {
			componentHolder.setConvertedTextString( "Unable to decode" );
			logger.error("Exception thrown when getting Decoded base64 text", ex);
		}
		
	}
	
	@Override
	public void caretUpdate(CaretEvent ce) {
		String textAreaString = componentHolder.getOriginalTextString();
		String encodedString = null;
		try {
			encodedString = new String(Base64.getEncoder().encode(textAreaString.getBytes(charSetName)));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception thrown due to encoding string bytes", e);
		}
		
		componentHolder.setConvertedTextString( encodedString );
	}
}

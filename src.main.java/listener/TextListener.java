package listener;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.converter.Base64Translator;
import data.types.ByteSource;
import main.BaseFrameComponentHolder;


public class TextListener implements CaretListener{
	
	static final Logger logger = LogManager.getLogger(TextListener.class.getName());
	
	private BaseFrameComponentHolder componentHolder;
	private String textAreaString = null;
	private int conversionType = 0;
	
	
	public TextListener(BaseFrameComponentHolder compHolder) {
		this.componentHolder = compHolder;
	}
	

	public void setConversionType(int conversionType) {
		this.conversionType = conversionType;
		updateConversionTextArea();
	}
	public void setText(String strValue) {
		componentHolder.getEntireConvertedText().setText( strValue );
	}

	@Override
	public void caretUpdate(CaretEvent ce) {
		textAreaString = componentHolder.getEntireOriginalText().getText();
		this.updateConvertedTextArea();
	}
	
	public void updateConvertedTextArea() {
		componentHolder.getEntireConvertedText().setText(this.getEncodedString(textAreaString));
	}
	
	public String getEncodedString(String str) {
		String encodedString = null;
		try {
			encodedString = new String(Base64.getEncoder().encode(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception thrown due to", e);
		}
		
		return encodedString;
	}

	private void updateConversionTextArea() {
		String strToConvert = componentHolder.getEntireOriginalText().getText();
		byte[] dataBytes = null;
		
		try {
			dataBytes = strToConvert.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception thrown when trying ot get bytes from text field", e);
		}
		
		Base64Translator dataTranslator = new Base64Translator(dataBytes);
		dataTranslator.setConversionType(conversionType);
		
		try {
			updateDrawingPanelUsingData(dataTranslator.getConvertedByteSource());
		} catch(Exception ex) {
			setText("Unable to decode");
			logger.error("Exception thrown when getting Decoded base64 text", ex);
		}
		
	}
	
	public void updateDrawingPanelUsingData(ByteSource byteSource) {
		setText( byteSource.getStringBytes() );
		componentHolder.getDrawingPanel().updateComponentWithBytes( byteSource.getDataBytes() );
	}
	
	public int getConversionType() {
		return conversionType;
	}

}

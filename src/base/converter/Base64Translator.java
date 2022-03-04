package base.converter;

import java.util.Base64;

import org.apache.logging.log4j.Logger;

import data.types.ByteSource;


public class Base64Translator {

    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(Base64Translator.class);
	private byte[] dataBytes = null;
	private int conversionType = -1;
	public static final int DECODE = 1;
	public static final int ENCODE = 0;
	
	
	public Base64Translator(byte[] bytes) {
		this.setDataBytes(bytes);
	}
	
	public ByteSource getConvertedByteSource() {
		if(this.conversionType > 0) {
			return getDecodedByteSource();
		} else {
			return getEncodedByteSource();
		}
	}
	
	public String getConvertedStringBytes() {
		return getConvertedByteSource().getStringBytes();
	}
	
	public byte[] getConvertedDataBytes() {
		return getConvertedByteSource().getDataBytes();
	}
	
	private ByteSource getDecodedByteSource() {
		return new ByteSource( getB64DecoderToDecode(dataBytes) , getB64DecodedBytes(dataBytes));
	}
	
	private ByteSource getEncodedByteSource() {
		return new ByteSource( getB64EncoderToEncode(dataBytes) , getB64EncodedBytes(dataBytes) );
	}
	
	public String getB64DecoderToDecode(byte[] byteSrc) {
		return new String(getB64DecodedBytes(byteSrc));
	}
	
	public byte[] getB64DecodedBytes(byte[] byteSrc) {
		try {
			return Base64.getDecoder().decode(byteSrc);
		} catch (Exception e) {
			logger.error("Unable to decode bytes: {}", byteSrc);
		}
		
		return byteSrc;
	}
	
	public String getB64EncoderToEncode(byte[] byteSrc) {
		try {
			return Base64.getEncoder().encodeToString(byteSrc);
		} catch (Exception e) {
			logger.error("Unable to encode bytes: {}", byteSrc);
		}
		
		return "";
	}

	public byte[] getB64EncodedBytes(byte[] byteSrc) {

		try {
			return Base64.getEncoder().encode(byteSrc);
		} catch (Exception e) {
			logger.error("Unable to decode bytes: {}", byteSrc);
		}
		
		return byteSrc;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}
	
	public void setConversionType(int conversionType) {
		this.conversionType = conversionType;
	}
}

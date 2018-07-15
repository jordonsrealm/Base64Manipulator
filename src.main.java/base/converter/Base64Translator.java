package base.converter;

import java.util.Base64;

import data.types.ByteSource;


public class Base64Translator {

	private byte[] dataBytes = null;
	private int conversionType = -1;
	public static final int DECODE = 1;
	public static final int ENCODE = 0;
	
	
	public Base64Translator() {
		//
	}
	
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
	
	private ByteSource getDecodedByteSource() {
		return new ByteSource( getB64DecoderToDecode(dataBytes) , getB64DecodedBytes(dataBytes));
	}
	
	private ByteSource getEncodedByteSource() {
		return new ByteSource( getB64EncoderToEncode(dataBytes) , getB64EncodedBytes(dataBytes) );
	}
	
	public ByteSource getEncodedByteSource(byte[] dataBytes) {
		setDataBytes(dataBytes);
		return getEncodedByteSource();
	}
	
	public ByteSource getDecodedByteSource(byte[] dataBytes) {
		setDataBytes(dataBytes);
		return getDecodedByteSource();
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}
	
	public byte[] getB64DecodedBytes(byte[] bytesSrc) {
		return Base64.getDecoder().decode(bytesSrc);
	}
	
	public String getB64DecoderToDecode(byte[] byteSrc) {
		return new String(getB64DecodedBytes(byteSrc));
	}
	
	public byte[] getB64EncodedBytes(byte[] bytesSrc) {
		return Base64.getEncoder().encode(bytesSrc);
	}

	public String getB64EncoderToEncode(byte[] byteSrc) {
		return Base64.getEncoder().encodeToString(byteSrc);
	}

	public int getConversionType() {
		return conversionType;
	}

	public void setConversionType(int conversionType) {
		this.conversionType = conversionType;
	}

}

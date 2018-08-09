package data.types;

public class ByteSource {

	private String stringBytes;
	private byte[] dataBytes;
	
	
	public ByteSource(String stringBytes, byte[] dataBytes) {
		this.stringBytes = stringBytes;
		this.dataBytes = dataBytes;
	}
	
	
	public String getStringBytes() {
		return stringBytes;
	}
	
	public void setStringBytes(String stringBytes) {
		this.stringBytes = stringBytes;
	}
	
	public byte[] getDataBytes() {
		return dataBytes;
	}
	
	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}
}

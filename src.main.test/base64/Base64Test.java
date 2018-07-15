package base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import base.converter.Base64Translator;
import data.types.ByteSource;


public class Base64Test {
	
	
	@Test
	public void testTranslator() {
		String testString = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MD" +
							"gsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAACAAIDASI" +
							"AAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0" +
							"KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6" +
							"ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtR" +
							"EAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVV" +
							"ldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP0" +
							"9fb3+Pn6/9oADAMBAAIRAxEAPwD9/KKKKAP/2Q==";
		
		byte[] strAsByteArray = null;
		
		try {
			strAsByteArray = testString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			fail();
		}
		
		Base64Translator translatorForDecode =  new Base64Translator( strAsByteArray );
		translatorForDecode.setConversionType(Base64Translator.DECODE);
		ByteSource byteSourceForDecode = translatorForDecode.getConvertedByteSource();
		
		Base64Translator translatorForEncode =  new Base64Translator( byteSourceForDecode.getDataBytes() );
		translatorForEncode.setConversionType(Base64Translator.ENCODE);
		ByteSource byteSourceForEncode = translatorForEncode.getConvertedByteSource();

		assertEquals( testString, byteSourceForEncode.getStringBytes() );
	}
	
	@Test
	public void testDecode() {
		
		String testStringDecoded = "https://www.amazon.com/StrivedayTM-Solid-Electric-gauge-32-8ft/dp/B077HWS5X" +
							"V/ref=sr_1_4?s=hi&ie=UTF8&qid=1530588963&sr=1-4&keywords=24+awg+solid+wire";
		
		String testString = "aHR0cHM6Ly93d3cuYW1hem9uLmNvbS9TdHJpdmVkYXlUTS1Tb2xpZC1FbGVjdHJpYy" +
								   "1nYXVnZS0zMi04ZnQvZHAvQjA3N0hXUzVYVi9yZWY9c3JfMV80P3M9aGkmaWU9VVRGO" +
								   "CZxaWQ9MTUzMDU4ODk2MyZzcj0xLTQma2V5d29yZHM9MjQrYXdnK3NvbGlkK3dpcmU=";
		
		byte[] strAsByteArray = null;
		
		try {
			strAsByteArray = testString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			fail();
		}
		
		Base64Translator translator =  new Base64Translator( strAsByteArray );
		translator.setConversionType(Base64Translator.DECODE);
		ByteSource byteSource = translator.getConvertedByteSource();

		assertEquals( testStringDecoded, byteSource.getStringBytes() );
	}
	
	@Test
	public void testEncode() {
		
		String testString = "https://www.amazon.com/StrivedayTM-Solid-Electric-gauge-32-8ft/dp/B077HWS5X" +
							"V/ref=sr_1_4?s=hi&ie=UTF8&qid=1530588963&sr=1-4&keywords=24+awg+solid+wire";
		
		String testStringEncoded = "aHR0cHM6Ly93d3cuYW1hem9uLmNvbS9TdHJpdmVkYXlUTS1Tb2xpZC1FbGVjdHJpYy" +
								   "1nYXVnZS0zMi04ZnQvZHAvQjA3N0hXUzVYVi9yZWY9c3JfMV80P3M9aGkmaWU9VVRGO" +
								   "CZxaWQ9MTUzMDU4ODk2MyZzcj0xLTQma2V5d29yZHM9MjQrYXdnK3NvbGlkK3dpcmU=";
		
		byte[] strAsByteArray = null;
		
		try {
			strAsByteArray = testString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			fail();
		}
		
		Base64Translator translator =  new Base64Translator( strAsByteArray );
		translator.setConversionType(Base64Translator.ENCODE);
		ByteSource byteSource = translator.getConvertedByteSource();

		assertEquals( testStringEncoded, byteSource.getStringBytes() );
	}
	
}

package data.types;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConversionType implements ActionListener {

	public static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private JFrame parentFrame;
	private JTextArea textArea;
	
	
	public ConversionType(JFrame theParentFrame, JTextArea area) {
		parentFrame = theParentFrame;
		this.textArea = area;
	}
	
	private void tryToOpenFile(JButton whichButton) {
		File file = null;
		String stringToUse = "";
		
		if(whichButton.getText().equals("Choose File To Open")) {
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(parentFrame);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            file = chooser.getSelectedFile();
	            
	            logger.debug( "tryToOpenFile: {}", file.getName());
	            
	            byte[] bytesFromFile = new byte[(int) file.length()];
	            
	            try(FileInputStream fileInputStream = new FileInputStream(file)){
					int bytesRead = fileInputStream.read(bytesFromFile);
					
					if(bytesRead>=0) {
						byte[] convertByteArray = getBase64ByteArray(bytesFromFile);
						stringToUse = new String(convertByteArray, StandardCharsets.US_ASCII);
					}
				} catch (IOException e) {
					logger.error("Exception thrown when reading files bytes", e);
				}
	        }
			
			textArea.setText(stringToUse);
		}
	}
	
	private void saveToFilePath(JButton whichButton) {
		File file = null;
		
		if(whichButton.getText().equals("Save To File Path")) {
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(parentFrame);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            file = chooser.getSelectedFile();
	            logger.debug( "saveToFilePath: {}", file.getName());
	            
	            try(FileOutputStream fileInputStrm = new FileOutputStream(file)) {
	            	byte[] bytesFromFile = getDecodedText( textArea.getText().getBytes() );
	            	
	            	if(file.isFile()) {
	            		fileInputStrm.write(bytesFromFile);
						JOptionPane.showMessageDialog(parentFrame, "File saved to: " + file.getName());
		            } else {
		            	JOptionPane.showMessageDialog(parentFrame, "File already exists or cannot be created");
		            }
				} catch (IOException e) {
					logger.error("Exception thrown when write bytes to file", e);
				}
	        }
		}
	}
	
	private byte[] getDecodedText(byte[] strToByte) {
		return Base64.getDecoder().decode( strToByte );
	}
	
	private byte[] getBase64ByteArray(byte[] bArray) {
		return Base64.getEncoder().encode(bArray);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton whichButton = (JButton) ae.getSource();
		
		tryToOpenFile(whichButton);
		
		saveToFilePath(whichButton);
	}

}

package component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ColorChooser extends JFileChooser {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(ColorChooser.class);
	private File file;
	private JFrame parentFrame;
	private JTextArea textArea;

	private static final String CHOOSE = "Choose File To Open";
	
	
	public ColorChooser(JFrame parentFrame, File file, JTextArea textArea) {
		this.parentFrame = parentFrame;
		this.file = file;
		this.textArea = textArea;
	}
	
	public String openDialogAndGetString(String whichButton) {
		
		String stringToUse = null;
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(parentFrame);
		
		if(CHOOSE.equals(whichButton)) {
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				stringToUse = getConvertedByteArray(chooser);
	        }
		}else {
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				saveFile(chooser);
	        }
		}
		
		return stringToUse;
	}
	
	public String getConvertedByteArray(JFileChooser chooser) {
        file = chooser.getSelectedFile();
        String stringToUse = null;
        logger.debug("tryToOpenFile: " + file.getName());
        
        byte[] bytesFromFile = new byte[(int) file.length()];
        
        try(FileInputStream fileInputStream = new FileInputStream(file)){
			int bytesRead = fileInputStream.read(bytesFromFile);
			
			if(bytesRead>=0) {
				byte[] convertByteArray = Base64.getEncoder().encode(bytesFromFile);
				stringToUse = new String(convertByteArray, StandardCharsets.US_ASCII);
			}
		} catch (IOException e) {
			logger.error("Exception thrown when reading files bytes", e);
		}
        
        return stringToUse;
	}
	
	public void saveFile(JFileChooser chooser) {
		file = chooser.getSelectedFile();
        logger.debug( "saveToFilePath: " + file.getName());
        
        try(FileOutputStream fileInputStrm = new FileOutputStream(file)) {
        	byte[] bytesFromFile = Base64.getDecoder().decode( textArea.getText().getBytes() );
        	
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
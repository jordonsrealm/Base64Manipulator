package data.types;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import component.ColorChooser;


public class ConversionType implements ActionListener {

	public static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private JFrame parentFrame;
	private JTextArea textArea;
	
	
	public ConversionType( JFrame theParentFrame, JTextArea area) {
		parentFrame = theParentFrame;
		this.textArea = area;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton whichButton = (JButton) ae.getSource();
		
		File file = null;
		String stringToUse = "";
		
		stringToUse = new ColorChooser(parentFrame, file, logger, textArea).openDialogAndGetString(whichButton.getText());
			
		textArea.setText( stringToUse );
	}
}

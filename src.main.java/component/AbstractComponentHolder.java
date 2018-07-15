package component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import configuration.ConfigurationGetter;
import drawing.DrawingPanel;
import listener.TextListener;


public abstract class AbstractComponentHolder {

	protected JFrame frame;
	protected JTextArea entireOriginalText;
	protected JTextArea entireConvertedText;
	protected JTextField pathToOpenFile;
	protected JTextField pathToSaveFile;
	protected JButton fileChooserOpenButton;
	protected JButton fileChooserSaveButton;
	protected JRadioButton encodeRadioButton;
	protected JRadioButton decodeRadioButton;
	protected TextListener myTextListener;
	protected DrawingPanel drawingPanel;
	protected JSplitPane splitPane;
	
	protected ConfigurationGetter configurationGetter;
	
	protected String chooseFile = "Choose File To Open";
	protected String saveFile   = "Save To File Path";
	protected String encodeFile = "Encode Incoming File";
	protected String decodeFile = "Decode Incoming File";
	
	
	public abstract JFrame getFrame();
	
	public abstract void setFrame(JFrame frame);
	
	public abstract JTextArea getEntireOriginalText();
	
	public abstract void setEntireOriginalText(JTextArea entireOriginalText);
	
	public abstract JTextArea getEntireConvertedText();
	
	public abstract void setEntireConvertedText(JTextArea entireConvertedText);
	
	public abstract JTextField getPathToOpenFile();
	
	public abstract void setPathToOpenFile(JTextField pathToOpenFile);
	
	public abstract JTextField getPathToSaveFile();
	
	public abstract void setPathToSaveFile(JTextField pathToSaveFile);
	
	public abstract JButton getFileChooserOpenButton();
	
	public abstract void setFileChooserOpenButton(JButton fileChooserOpenButton);
	
	public abstract JButton getFileChooserSaveButton();
	
	public abstract void setFileChooserSaveButton(JButton fileChooserSaveButton);
	
	public abstract JRadioButton getEncodeRadioButton();
	
	public abstract void setEncodeRadioButton(JRadioButton encodeRadioButton);
	
	public abstract JRadioButton getDecodeRadioButton();
	
	public abstract void setDecodeRadioButton(JRadioButton decodeRadioButton);
	
	public abstract TextListener getMyTextListener();
	
	public abstract void setMyTextListener(TextListener myTextListener);
	
	public abstract DrawingPanel getDrawingPanel();
	
	public abstract void setDrawingPanel(DrawingPanel drawingPanel);
	
	public abstract void setJSplitPane(JSplitPane splitPane);
	
	public abstract JSplitPane getJSplitPane();
}

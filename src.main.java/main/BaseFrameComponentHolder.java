package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import component.AbstractComponentHolder;
import configuration.ConfigurationGetter;
import data.types.ConversionType;
import drawing.DrawingPanel;
import listener.BaseFrameListener;
import listener.TextListener;


public class BaseFrameComponentHolder extends AbstractComponentHolder implements ActionListener{

	
	public BaseFrameComponentHolder( ConfigurationGetter configurationGetter ) {
		this.configurationGetter = configurationGetter;
	}
	
	public void setFrameProperties() {
		frame = new JFrame(configurationGetter.getTitle());
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		
		int configFrameWidth  = configurationGetter.getFrameWidth();
		int configFrameHeight = configurationGetter.getFrameHeight();
		
		frame.setSize( configFrameWidth, configFrameHeight);
	}
	
	public void createInputFields() {
		
		int textFieldLength = configurationGetter.getTextFieldLength();
		this.pathToOpenFile = new JTextField(textFieldLength);
		this.pathToSaveFile = new JTextField(textFieldLength);
		this.fileChooserOpenButton = new JButton(chooseFile);
		this.fileChooserSaveButton = new JButton(saveFile);
		
		this.myTextListener = new TextListener(this);
		
		this.originalText = new JTextArea();
		this.originalText.addCaretListener(myTextListener);
		this.convertedText = new JTextArea();
		
		this.encodeRadioButton = new JRadioButton(encodeFile);
		this.decodeRadioButton = new JRadioButton(decodeFile);
		this.encodeRadioButton.addActionListener(this);
		this.decodeRadioButton.addActionListener(this);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(encodeRadioButton);
		radioGroup.add(decodeRadioButton);

		originalText.setEditable(true);
		convertedText.setEditable(true);
		
		Dimension textDimension = new Dimension( configurationGetter.getTextWidth(), configurationGetter.getTextHeight());
		originalText.setPreferredSize( textDimension );
		convertedText.setPreferredSize( textDimension );
		
		drawingPanel = new DrawingPanel();
	}
	
	public void setPanelingStructure() {
		ConversionType conversionTypeListener = new ConversionType( frame , originalText );
		this.fileChooserOpenButton.addActionListener( conversionTypeListener );
		this.fileChooserSaveButton.addActionListener( conversionTypeListener );
				
		JPanel topPanel = new JPanel(new GridLayout(3,2));
		topPanel.add(pathToOpenFile);
		topPanel.add(fileChooserOpenButton);
		topPanel.add(pathToSaveFile);
		topPanel.add(fileChooserSaveButton);
		topPanel.add(encodeRadioButton);
		topPanel.add(decodeRadioButton);

		JScrollPane sp1 = new JScrollPane(originalText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollPane sp2 = new JScrollPane(convertedText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp1.setVerticalScrollBar(new JScrollBar());
		sp2.setVerticalScrollBar(new JScrollBar());
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp1, sp2);
		splitPane.setDividerLocation(frame.getWidth()/2);
		
		this.splitPane = splitPane;
		
		frame.addComponentListener(new BaseFrameListener(this.splitPane, frame));
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(splitPane, BorderLayout.CENTER);
		frame.add(this.drawingPanel, BorderLayout.SOUTH);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JRadioButton radBtn = (JRadioButton) ae.getSource();
		if(radBtn.getText().equals(encodeFile)) {
			this.myTextListener.setConversionType(0);
		} else {
			this.myTextListener.setConversionType(1);
		}
	}
	
	@Override
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public JTextArea getOriginalTextArea() {
		return originalText;
	}
	
	@Override
	public void setOriginalTextArea(JTextArea entireOriginalText) {
		this.originalText = entireOriginalText;
	}
	
	public String getOriginalTextString() {
		return originalText.getText();
	}
	
	public void setOriginalTextString(String newString) {
		originalText.setText( newString );
	}
	
	@Override
	public JTextArea getConvertedTextArea() {
		return convertedText;
	}
	
	@Override
	public void setConvertedTextArea(JTextArea entireConvertedText) {
		this.convertedText = entireConvertedText;
	}
	
	public String getConvertedTextString() {
		return convertedText.getText();
	}
	
	public void setConvertedTextString(String newString) {
		convertedText.setText( newString );
	}
	
	@Override
	public JTextField getPathToOpenFile() {
		return pathToOpenFile;
	}
	
	@Override
	public void setPathToOpenFile(JTextField pathToOpenFile) {
		this.pathToOpenFile = pathToOpenFile;
	}
	
	@Override
	public JTextField getPathToSaveFile() {
		return pathToSaveFile;
	}
	
	@Override
	public void setPathToSaveFile(JTextField pathToSaveFile) {
		this.pathToSaveFile = pathToSaveFile;
	}
	
	@Override
	public JButton getFileChooserOpenButton() {
		return fileChooserOpenButton;
	}
	
	@Override
	public void setFileChooserOpenButton(JButton fileChooserOpenButton) {
		this.fileChooserOpenButton = fileChooserOpenButton;
	}	
	
	@Override
	public JButton getFileChooserSaveButton() {
		return fileChooserSaveButton;
	}
	
	@Override
	public void setFileChooserSaveButton(JButton fileChooserSaveButton) {
		this.fileChooserSaveButton = fileChooserSaveButton;
	}
	
	@Override
	public JRadioButton getEncodeRadioButton() {
		return encodeRadioButton;
	}
	
	@Override
	public void setEncodeRadioButton(JRadioButton encodeRadioButton) {
		this.encodeRadioButton = encodeRadioButton;
	}
	
	@Override
	public JRadioButton getDecodeRadioButton() {
		return decodeRadioButton;
	}
	
	@Override
	public void setDecodeRadioButton(JRadioButton decodeRadioButton) {
		this.decodeRadioButton = decodeRadioButton;
	}
	
	@Override
	public TextListener getMyTextListener() {
		return myTextListener;
	}
	
	@Override
	public void setMyTextListener(TextListener myTextListener) {
		this.myTextListener = myTextListener;
	}
	
	@Override
	public DrawingPanel getDrawingPanel() {
		return drawingPanel;
	}
	
	@Override
	public void setDrawingPanel(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	@Override
	public void setJSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	@Override
	public JSplitPane getJSplitPane() {
		return this.splitPane;
	}
	
}

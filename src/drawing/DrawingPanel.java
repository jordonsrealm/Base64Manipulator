package drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class DrawingPanel extends JPanel {

	private static final Logger logger = LogManager.getLogger(DrawingPanel.class);
	private static final long serialVersionUID = 1L;
	private transient BufferedImage imageToDraw;
	

	public void updateComponentWithBytes(byte[] imageBytes) {
		try {
			InputStream inputStream = new ByteArrayInputStream(imageBytes);
			this.imageToDraw = ImageIO.read( inputStream);
		} catch (IOException e) {
			logger.error("Exception thrown when reading bytes into a buffered image", e);
		}

		repaint();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if(this.imageToDraw != null) {
			ScaledImage scaledImage = new ScaledImage( imageToDraw, this);
			Dimension centerPoint = scaledImage.getCenterRelativePoint();
			
			graphics.setColor(Color.WHITE);
			graphics.fillRect(centerPoint.width, centerPoint.height, scaledImage.getScaledWidth(), scaledImage.getScaledHeight() );
			graphics.drawImage( scaledImage.getImg(), centerPoint.width, centerPoint.height, scaledImage.getScaledWidth(), scaledImage.getScaledHeight(), null);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(400,400);
	}
}

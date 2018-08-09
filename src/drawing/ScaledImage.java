package drawing;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ScaledImage {

	
	private BufferedImage img;
	private JPanel container;
	private Dimension centerRelativePoint;
	private double scalingFactor;
	private int origWidth;
	private int origHeight;
	
	public ScaledImage(BufferedImage buffImage, JPanel container) {
		this.img = buffImage;
		this.container = container;
		this.origWidth = img.getWidth();
		this.origHeight = img.getHeight();
		
		this.scalingFactor = initialScalingFactor();
		this.centerRelativePoint = getCenteredPointRelativeToContainer(scalingFactor);
	}
	
	private double initialScalingFactor() {

		int contW = container.getWidth();
		int contH = container.getHeight();
		int origImgH = img.getHeight();
		int origImgW = img.getWidth();
		
		double scaleFactor;
		
		if(origImgW > contW || origImgH > contH) {
			
			int largestImgDim = origImgW > origImgH ? origImgW : origImgH;
			
			int smallestContDim = contW > contH ? contH : contW;
			
			scaleFactor = (double) smallestContDim/largestImgDim;
		} else {
			scaleFactor = 1;
		}

		return scaleFactor;
	}
	
	private Dimension getCenteredPointRelativeToContainer(double scalingFactor) {
		int contW = container.getWidth();
		int contH = container.getHeight();
		int origImgH = img.getHeight();
		int origImgW = img.getWidth();
		
		int centeredX = (int) ((contW - origImgW * scalingFactor)/2);
		int centeredY = (int) ((contH - origImgH * scalingFactor)/2);
		return new Dimension(centeredX, centeredY);
	}
	
	public int getScaledWidth() {
		return (int) (origWidth * scalingFactor);
	}

	public int getScaledHeight() {
		return (int) (origHeight * scalingFactor);
	}

	public Dimension getCenterRelativePoint() {
		return centerRelativePoint;
	}


	public void setCenterRelativePoint(Dimension centerRelativePoint) {
		this.centerRelativePoint = centerRelativePoint;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
}

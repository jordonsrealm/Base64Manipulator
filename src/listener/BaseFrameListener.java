package listener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import javax.swing.JSplitPane;


public class BaseFrameListener implements ComponentListener{

	private JSplitPane splitPane;
	private JFrame frame;
	
	
	public BaseFrameListener(JSplitPane splitPane, JFrame frame) {
		this.splitPane = splitPane;
		this.frame = frame;
	}


	@Override
	public void componentResized(ComponentEvent arg0) {
		
		if(splitPane != null) {
			int width = frame.getWidth();
			splitPane.setDividerLocation( width/2 );
		}
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// not implemented yet
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// not implemented yet
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// not implemented yet
	}
	
}

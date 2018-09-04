import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Graph extends JPanel {
	static JFrame f = new JFrame("Graph");
	public void paint () {
		
	}

	public static GeneralPath getGraph(GUI gui, Graph graph) {
		GeneralPath path = new GeneralPath();
		path.moveTo(0, 0);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(graph);
		f.setSize(290, 325);
		f.setLocation(550, 25);
		f.setVisible(true);
		/* M There might be a synchronization issue here so I'm trying this. 
		 * We won't know for sure until we fix the NullPointerException in drawGraphics()*/
		for(int i = 0; i < gui.getXValues().length; i++) {
				path.lineTo(gui.getXValues()[i], gui.getYValues()[i]);
		}
		return path;
	}
}
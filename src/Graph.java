import java.awt.*;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Graph extends JPanel {
	Path2D.Double path = new Path2D.Double();
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		Graph graph = new Graph();
		JFrame f = new JFrame("Graph");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(graph);
		f.setSize(290, 325);
		f.setLocation(550, 25);
		f.setVisible(true);
		/* There might be a synchronization issue here so I'm trying this. 
		 * We won't know for sure until we fix the NullPointerException in drawGraphics()*/
	}
	
	public static String firstDerivative(String exp) {
		
	}
}
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
//M Added these things to hopefully get some drawings gong.
public class Graph {
	//M Adding a new JPanel on which to hold the things.
	JPanel graphWindow = new JPanel();
	public Graph() {
		JFrame frame = new JFrame();
		graphWindow.setSize(500, 500);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(graphWindow, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	 public void draw(double [] xValues, double [] yValues) {
		 //TODO A method to draw the graph.
	        //Get the Graphics2D object of a JPanel, to draw on
	        g = (Graphics2D) graphWindow.getGraphics();
	        //Draw some axes
	        g.drawLine(250, 500, 250, 0); // The Y axis
	        g.drawLine(0, 250, 500, 250);  //The X axis


	        //You can also draw scales and labels here 
	        //drawString draws a string onto the screen

	        //Plot the data points, using our method
	        System.out.println("Beginning to draw a point");
	        g.draw(new Line2D.Double(xValues[0], yValues[0], xValues[0], yValues[0]));
	        for (int i=1; i < xValues.length; i++)
	            drawPoint(xValues[i], yValues[i]);  //Your data gets read here
	        }

	        private void drawPoint(double x, double y) {
	        	System.out.println("Drawing the point (" + x + ", " + y + ").");
	        	//TODO A method to draw the various lines that make up the graph.
	            g.draw(new Line2D.Double(x, -y, x, -y)); //Use negative Y to simulate a standard Cartesian behaivor
	        }
	Graphics2D g;
}
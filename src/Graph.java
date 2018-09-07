import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
//M Added these things to hopefully get some drawings gong.
public class Graph {
	//M Adding a new JPanel on which to hold the things.
	JPanel graphWindow = new JPanel();
	 public void draw(double [] xValues, double [] yValues) {
		 //TODO A method to draw the graph.
	        //Get the Graphics2D object of a JPanel, to draw on
	        g = (Graphics2D) graphWindow.getGraphics();

	        //Draw some axes
	        g.drawLine(0, 0, 0, 100); //The Y axis
	        g.drawLine(0, 100, 100, 100);  //The X axis


	        //You can also draw scales and labels here 
	        //drawString draws a string onto the screen

	        //Plot thr data points, using our method
	        for (int i=0; i < xValues.length; i++)
	            drawPoint(xValues[i], yValues[i]);  //Your data gets read here
	        }

	        private void drawPoint(double x, double y) {
	        	//TODO A method to draw the various lines that make up the graph.
	            g.draw(new Line2D.Double(x, -y, x, -y)); //Use negative Y to simulate a standard Cartesian behaivor
	        }
	Graphics2D g;
}
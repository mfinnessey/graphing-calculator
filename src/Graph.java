import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
//M Currently issues with graphing complex polynomial functions, likely an issue with syntax for MathEvaluator.
//M Added these things to hopefully get some drawings gong.
public class Graph {
	//M Adding a new JPanel on which to hold the things.
	JPanel graphWindow = new JPanel();
	static private int colorTracker = 0;
	private String [] lineColors = {"#FF0000", "#FFA500", "#008000", "#00FFFF", "#000080",
			"#FF00FF", "#800080", "#C0C0C0"};
	private int graphRatio = 0;
	public Graph(int lowerX, int upperX, int lowerY, int upperY) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(graphWindow, BorderLayout.CENTER);
		frame.pack();
		int width = upperX - lowerX;
		int height = upperY - lowerY;
		if(width > 1000 || height > 1000) {
			System.out.println("Error: range too great");
			System.exit(0);
		}
		else if ((width * 25) > 1000 || (height * 25) > 1000) {
			graphRatio = 1000/width;
			if(1000/height < graphRatio) {
				graphRatio = 1000/height;
			}
		}
		frame.setSize(1000, 1000);
		graphWindow.setSize(1000, 1000);
		frame.setLocation(600, 0);
		frame.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("Width: " + screenSize.getWidth());
		System.out.println("Height: " + screenSize.getHeight());
	}

	public void clear() {
		//M A method to clear the graph
		g = (Graphics2D) graphWindow.getGraphics();
		g.clearRect(0, 0, 500, 500);
		colorTracker = 0;
	}
	
	 public void draw(double [] xValues, double [] yValues) {
		 //TODO A method to draw the graph.
	        //Get the Graphics2D object of a JPanel, to draw on
	        g = (Graphics2D) graphWindow.getGraphics();
        	//M Reusing the colors as necessary.
	        if(colorTracker > 7) {
        		colorTracker = 0;
        	}
        	//M Changing the colors of lines.
        	g.setColor(Color.decode(lineColors[colorTracker++]));
		        for (int i=1; i < xValues.length; i++) {
		            drawPoint(xValues[i], yValues[i]);  //Your data gets read here
		        }
	 }
	 public void draw() {
		 g = (Graphics2D) graphWindow.getGraphics();
		//K draws some coordinate lines
	    for (int i = 0; i < 500; i+=25) {
	    	g.setColor(Color.lightGray);
	        g.drawLine(0, i, 500, i); // X coords
	        g.drawLine(i, 500, i, 0); // Y coords
	    }
	        //Draw some axes
	    g.setColor(Color.BLACK);
	    g.drawLine(250, 500, 250, 0); // The Y axis
	    g.drawLine(0, 250, 500, 250);  //The X axis
	    //K Writes coordinate numberline
	    g.setColor(Color.GRAY);
	    for(int i = -10; i <=10; i++) {
	    	g.drawString(Integer.toString(i), 25*(i+10), 250); // X coords
	    	g.drawString(Integer.toString(i), 250, 500-25*(i+10)); // Y coords
	    }
	        
	        //K I added some aesthetics but now the graph is kinda messed up because the
	        //K axes keep on going over the lines when I do more than one line in a graph
	        //K Other than that it works great
	 }
	 
	 public void draw(double x, double y, String color) {
		 g = (Graphics2D) graphWindow.getGraphics();
		 g.setColor(Color.decode(color));
		 g.fillOval((int) ((25 * x) + 247), (int) ((25 * -y) + 247), 6, 6);
	 }
	 
	

	        private void drawPoint(double x, double y) {
	        	//TODO A method to draw the various lines that make up the graph.
	        	//M Implementing behavior to scale with the axes, more complexity can be added with more time.
	            g.draw(new Line2D.Double(((25 * x) + 250), ((25 * -y) + 250), ((25 * x) + 250), ((25 * -y) + 250))); //Use negative Y to simulate a standard Cartesian behaivor
	        }
	Graphics2D g;
}
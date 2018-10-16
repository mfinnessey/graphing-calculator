import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
//M Currently issues with graphing complex polynomial functions, likely an issue with syntax for MathEvaluator.
//M Added these things to hopefully get some drawings gong.
public class Graph {
	//M Adding a new JPanel on which to hold the things.
	JPanel graphWindow = new JPanel();
	private double [] xValues1 = new double[20001];
	private double [] yValues1 = new double [20001];
	private double [] xValues2 = new double[20001];
	private double [] yValues2 = new double [20001];
	private double [] xValues3 = new double[20001];
	private double [] yValues3 = new double [20001];
	private double [] xValues4 = new double[20001];
	private double [] yValues4 = new double [20001];
	private double [] xValues5 = new double[20001];
	private double [] yValues5 = new double [20001];
	private double [] xValues6 = new double[20001];
	private double [] yValues6 = new double [20001];
	private double [] xValues7 = new double[20001];
	private double [] yValues7 = new double [20001];
	private double [] xValues8 = new double[20001];
	private double [] yValues8 = new double [20001];
	private double [][] keyPoints1 = new double [2][50];
	private double [][] keyPoints2 = new double [2][50];
	private double [][] keyPoints3 = new double [2][50];
	private double [][] keyPoints4 = new double [2][50];
	private double [][] keyPoints5 = new double [2][50];
	private double [][] keyPoints6 = new double [2][50];
	private double [][] keyPoints7 = new double [2][50];
	private double [][] keyPoints8 = new double [2][50];
	static private int arrayTracker = 1;
	private String [] lineColors = {"#FF0000", "#FFA500", "#008000", "#00FFFF", "#000080",
			"#FF00FF", "#800080", "#C0C0C0"};
	private int colorTracker = 0;
	public Graph() {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(graphWindow, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(500, 500);
		graphWindow.setSize(500, 500);
		frame.setLocation(500, 0);
		frame.setVisible(true);
	}
	public void storePoints(double [] xValues, double [] yValues) {
		switch(arrayTracker++) {
			case 1: 
				xValues1 = xValues;
				yValues1 = yValues;
				break;
			case 2: 
				xValues2 = xValues;
				yValues2 = yValues;
				break;
			case 3: 
				xValues3 = xValues;
				yValues3 = yValues;
				break;
			case 4: 
				xValues4 = xValues;
				yValues4 = yValues;
				break;
			case 5: 
				xValues5 = xValues;
				yValues5 = yValues;
				break;
			case 6: 
				xValues6 = xValues;
				yValues6 = yValues;
				break;
			case 7: 
				xValues7 = xValues;
				yValues7 = yValues;
				break;
			case 8: 
				xValues8 = xValues;
				yValues8 = yValues;
				break;
			default: 
				System.out.println("Error");
				System.exit(0);
		}
	}
	/* public void storeKeyPoints(double[][] ) {
		switch(arrayTracker++) {
			case 1: 
				xValues1 = xValues;
				yValues1 = yValues;
				break;
			case 2: 
				xValues2 = xValues;
				yValues2 = yValues;
				break;
			case 3: 
				xValues3 = xValues;
				yValues3 = yValues;
				break;
			case 4: 
				xValues4 = xValues;
				yValues4 = yValues;
				break;
			case 5: 
				xValues5 = xValues;
				yValues5 = yValues;
				break;
			case 6: 
				xValues6 = xValues;
				yValues6 = yValues;
				break;
			case 7: 
				xValues7 = xValues;
				yValues7 = yValues;
				break;
			case 8: 
				xValues8 = xValues;
				yValues8 = yValues;
				break;
			default: 
				System.out.println("Error");
				System.exit(0);
		}
	} */
	public void clear() {
		//M A method to clear the graph.
		g.clearRect(0, 0, 500, 500);
		colorTracker = 0;
		Graph graph = new Graph();
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
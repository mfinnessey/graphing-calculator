//M Various necessary graphics imports.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Graph {
	//M Adding a JPanel on which the graphs are drawn.
	JPanel graphWindow = new JPanel();
	//M An integer to track which colors from lineColors have already been used.
	static private int colorTracker = 0;
	//M An array of hex codes which are processed into various colors for the different graphs.
	private String [] lineColors = {"#FF0000", "#FFA500", "#008000", "#00FFFF", "#000080",
			"#FF00FF", "#800080", "#C0C0C0"};
	public Graph() {
		//TODO A method to construct a new Graph object.
		//M Creating the underlying JFrame.
		JFrame frame = new JFrame();
		//M Setting the program to close when the JFrame is closed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//M Adding the JPanel to the JFrame.
		frame.getContentPane().add(graphWindow, BorderLayout.CENTER);
		//M Setting the size of the underlying JFrame.
		frame.setSize(500, 500);
		//M Setting the size of the graph window.
		graphWindow.setSize(500, 500);
		//M Setting the location of the graph window on the screen.
		frame.setLocation(100, 30);
		//M Making the graph window visible.
		frame.setVisible(true);
		
		g = (Graphics2D) graphWindow.getGraphics();
		g.drawString("Graphing Calculator by Mike Finnessey and Kevin Hui",95,170);
		g.drawString("Made for Mr. Duran's Calculus BC project",130,190);
		g.drawString("Your graph will be drawn here!",155,210);
		g.drawString("Link to GitHub repository:",170,230);
		g.drawString("https://github.com/mrfinnessey/psychic-guacamole",105,250);
	}

	public void clear() {
		//TODO A method to clear the graph
		//M Getting the graphics object.
		g = (Graphics2D) graphWindow.getGraphics();
		//M Drawing a blank rectangle over the area in the window where the graph is drawn.
		g.clearRect(0, 0, 500, 500);
		//M Resetting colorTracker, as the graph is now blank again.
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
        	//M Changing the colors of lines to be drawn, which iterates with each line drawn.
	        //M This provides increased visual clarity to the program.
	        //M Each color is set from an array of hex codes, which is incremented through by colorTracker.
        	g.setColor(Color.decode(lineColors[colorTracker++]));
        		//M Iterating through each point in the array to draw.
		        for (int i=1; i < xValues.length; i++) {
		        	//M Passing each individual point to be drawn.
		            drawPoint(xValues[i], yValues[i]);
		        }
	 }
	 public void draw() {
		 //TODO A method to draw a graph.
		 g = (Graphics2D) graphWindow.getGraphics();
		//K draws some coordinate lines
	    for (int i = 0; i < 500; i+=25) {
	    	//M Setting the color of the grid-lines.
	    	g.setColor(Color.lightGray);
	    	//K X coords
	        g.drawLine(0, i, 500, i);
	        //K Y coords
	        g.drawLine(i, 500, i, 0);
	    }
	    //K Draw some axes
	    //M Setting the color of the axes.
	    g.setColor(Color.BLACK);
	    //K The Y axis
	    g.drawLine(250, 500, 250, 0);
	    //K The X axis
	    g.drawLine(0, 250, 500, 250);
	    //K Writes coordinate numberlines
	    //M Setting the color of the numbers.
	    g.setColor(Color.GRAY);
	    //M Iterating through each number that is going to be drawn.
	    //M The draw methods spread the numbers through the last statement, which "flips" when the numbers become negative.
	    for(int i = -10; i <=10; i++) {
	    	//M Writing the X coordinates.
	    	g.drawString(Integer.toString(i), 25*(i+10), 250);
	    	//M Writing the Y coordinates.
	    	g.drawString(Integer.toString(i), 250, 500-25*(i+10));
	    }
	 }
	 
	 public void draw(double x, double y, String color) {
		 //TODO A method to draw larger dots at key points.
		 g = (Graphics2D) graphWindow.getGraphics();
		 //M Setting the color of the key point to be drawn, based on a given hex string.
		 g.setColor(Color.decode(color));
		 g.fillOval((int) ((25 * x) + 247), (int) ((25 * -y) + 247), 6, 6);
	 }
	 
	

	        private void drawPoint(double x, double y) {
	        	//TODO A method to draw the various points that make up the graph.
	        	//Use negative Y to simulate a standard Cartesian behaivor
	            g.draw(new Line2D.Double(((25 * x) + 250), ((25 * -y) + 250), ((25 * x) + 250), ((25 * -y) + 250))); 
	        }
	Graphics2D g;
}
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Graph extends JPanel {
	Graphics g;
	public void drawGraph(Graphics g, int [] xValues, int [] yValues) {
		// This should explain how to fix the NullPointerException: https://stackoverflow.com/questions/12335121/initializing-a-graphics-variable
		g.setColor(Color.decode("0x45e5B"));
		g.drawPolyline(yValues, yValues, xValues.length);
	}
	
	public Graphics getClassGraphics() {
		return g;
	}
	
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		Graph graph = new Graph();
		JFrame f = new JFrame("Twilight Zone");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(graph);
		f.setSize(290, 325);
		f.setLocation(550, 25);
		f.setVisible(true);
		/* There might be a synchronization issue here so I'm trying this. 
		 * We won't know for sure until we fix the NullPointerException in drawGraphics()*/
		while(true) {
			graph.drawGraph(graph.getClassGraphics() , gui.getXValues(), gui.getYValues());
		}
	}
}
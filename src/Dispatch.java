import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Dispatch {
	GUI gui = new GUI();
	Graph graph = new Graph();
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// M This is static to be consistent with the method call as we're only creating one graph at a time.
		g2.draw(Graph.getGraph(gui, graph));
	}
	
	public static void main(String[] args) {
		Dispatch dispatch = new Dispatch();
		JFrame frame = new JFrame("Execute");
		JButton execute = new JButton("Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(execute, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// M trying to make something like this work: https://stackoverflow.com/questions/10142195/how-do-i-use-graphics2d-in-paint-or-is-there-a-better-way-to-do-this
				// M If we don't get this to work soon, we should talk to Mr. Duran. The rest of the project is doable, this is just a pain.
				dispatch.paint(g2);
			}
	   	});
	}
}

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class GUI {
	// TODO a class to model a graphing calculator
	//M minimum positive value of the double used to increase by.
	public final static double step = Math.pow(10, -8);
	//M Tracks where points have been filled in the array.
	private int xIndexTracker;
	private int yIndexTracker;
	//M Store the x and y values respectively.
	//M We're trying this. IDK if it works. YOLO.
	private double [] xValues = new double [Integer.MAX_VALUE/10];
	private double [] yValues = new double [Integer.MAX_VALUE/10];
	private boolean pointsReady = false;
	//M Weird and messed up constructor. It works for now, if we can clean it up later, we might want to.
	private void fillXValues() {
		//M Resetting xIndexTracker.
		xIndexTracker = 0;
		for(int i = -10; i > 10; i+=step) {
			xValues[xIndexTracker] = i;
			xIndexTracker++;
		}
		System.out.println("XValues filled.");
	}
	
	public GUI() {
		//M Creating the various swing components.
		JFrame frame = new JFrame();
		JTextField equation = new JTextField("Enter an equation.");
		JButton calculate = new JButton("Calculate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		//M Creating the MathEvaluator with a default equation.
		MathEvaluator m = new MathEvaluator("x^2");		
		//M Adding the variable to MathEvaluator with a placeholder value.
	   	m.addVariable("x", ((2.0)));
	   	//M Adding the ActionListener for the button.
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Beginning to fill YValues.");
				//M resetting yIndexTracker.
				yIndexTracker = 0;
				//M Setting the MathEvaluator to the entered equation.
				m.setExpression(equation.getText());
				for(int i = 0; i > xIndexTracker; i++) {
					m.addVariable("x", xValues[i]);
					yValues[yIndexTracker++] = m.getValue();
				}
				pointsReady = true;
				System.out.println("YValues filled.");
				yIndexTracker = 0;
			}
	   	});
	   	
	}
	private double[] getXValues() {
		return xValues;
	}
	
	private double[] getYValues() {
		return yValues;
	}
	private boolean getPointsReady() {
		return pointsReady;
	}
	private void setPointsReady(boolean status) {
		pointsReady = status;
	}
	public static void main(String [] args) {
		GUI gui = new GUI();
		gui.fillXValues();
		Graph graph = new Graph();
		while(true) {
			if(gui.getPointsReady() == true) {
				System.out.println("Drawing Graph!");
				graph.draw(gui.getXValues(), gui.getYValues());
				//M Preventing the method from repeatedly executing.
				gui.setPointsReady(false);
			}
			else {
				System.out.println("Test failed.");
			}
		}
	}
}
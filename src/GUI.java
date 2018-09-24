import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class GUI {
	// TODO a class to model a graphing calculator
	//M minimum positive value of the double used to increase by.
	public final static double step = Math.pow(10, -3);
	//M Tracks where points have been filled in the array.
	private int xIndexTracker;
	private int yIndexTracker;
	//M Store the x and y values respectively.
	//M We're trying this. IDK if it works. YOLO.
	private double [] xValues = new double[20001];
	private double [] yValues = new double [20001];
	private boolean pointsReady = false;
	//M Weird and messed up constructor. It works for now, if we can clean it up later, we might want to.
	
	public GUI() {
		//M Creating the various swing components.
		JFrame frame = new JFrame();
		JTextField equation = new JTextField("f(x)");
		JButton calculate = new JButton("Calculate");
		JButton firstDerivative = new JButton("First Derivative");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(firstDerivative, BorderLayout.NORTH);
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
				//M resetting yIndexTracker.
				yIndexTracker = 0;
				//M Setting the MathEvaluator to the entered equation.
				m.setExpression(equation.getText());
				//M This isn't working out either.
				for(int i = 0; i <= (xValues.length - 1); i++) {
					m.addVariable("x", xValues[i]);
					yValues[yIndexTracker++] = m.getValue();
				}
				pointsReady = true;
				yIndexTracker = 0;
			}
	   	});
	   	firstDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M Resetting yIndexTracker.
				yIndexTracker = 0;
				//M Setting the MathEvaluator to the entered equation.
				m.setExpression(equation.getText());
				for(int i = 0; i < (xValues.length - 1); i++) {
					m.addVariable("x", xValues[i]);
					yValues[yIndexTracker++] = m.getValue();
				}
				yValues = Derivative.findDerivative(xValues, yValues);
				pointsReady = true;
				//M Resetting yIndexTracker.
				yIndexTracker = 0;
			}
	   	});
	}
	
	private void fillXValues() {
		//M A method to fill xValues with consecutive numbers.
		/*M The index of xValues is tracked separately from the loop to allow arithmetic to be done 
		 * with the loop counter.*/
		xIndexTracker = 0;
		for(int i = (int) (-1 * Math.pow(10,4)); i <= (int) Math.pow(10, 4); i++) {
			xValues[xIndexTracker++] = (double) (i * step);
		}
	}
	
	private double[] getXValues() {
		//M A method to get the xValues.
		return xValues;
	}
	
	private double[] getYValues() {
		//M A method to get yValues.
		return yValues;
	}
	private boolean getPointsReady() {
		//M A method to get pointsReady.
		return pointsReady;
	}
	private void setPointsReady(boolean status) {
		//A method to set pointsReady.
		pointsReady = status;
	}
	private void printValues(double [] xValues, double [] yValues) {
		//M A method to print (x,y) pairs.
		for(int i = 0; i < xValues.length; i++) {
			System.out.println("( " + xValues[i] + " , " + yValues[i]+ " )");
		}
	}
	public static void main(String [] args) {
		GUI gui = new GUI();
		//M Filling xValues with consecutive x-values.
		gui.fillXValues();
		Graph graph = new Graph();
		//M Infinite loop (AKA I have no idea how to synchronize) to continuously graph the updated yValues.
		while(true) {
			//M If the points are ready, then they are graphed.
			if(gui.getPointsReady() == true) {
				//M Statement to print out and check derivative values.
				gui.printValues(gui.getXValues(), Derivative.findDerivative(gui.getXValues(), gui.getYValues()));
				//M Sending the points to be graphed.
				graph.draw(gui.getXValues(), gui.getYValues());
				//M Preventing the method from executing again until the yValues are recalculated.
				gui.setPointsReady(false);
			}
			//M This seems to do something for synchronization, so I'm just going to leave it for now.
			else {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
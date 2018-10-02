import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.java.com.udojava.evalex.Expression;



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
	private boolean clearDesired = false;
	//M Weird and messed up constructor. It works for now, if we can clean it up later, we might want to.
	
	public GUI() {
		//M Creating the various swing components.
		JFrame frame = new JFrame();
		JTextField equation = new JTextField("enter function here");
		JButton calculate = new JButton("Calculate");
		JButton firstDerivative = new JButton("First Derivative");
		JButton secondDerivative = new JButton("Second Derivative");
		JButton clear = new JButton("Clear");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(firstDerivative, BorderLayout.NORTH);
		frame.getContentPane().add(secondDerivative, BorderLayout.WEST);
		frame.getContentPane().add(clear, BorderLayout.EAST);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		JFrame integralFrame = new JFrame();
		JTextField lowerLimit = new JTextField("Enter lower limit here.");
		JTextField upperLimit = new JTextField("Enter upper limit here.");
		JButton evaluate = new JButton("Evaluate");
		JTextArea integralValue = new JTextArea("No Value to Display");
		integralFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		integralFrame.setLocation(0, 250);
		integralFrame.getContentPane().add(lowerLimit, BorderLayout.WEST);
		integralFrame.getContentPane().add(upperLimit, BorderLayout.EAST);
		integralFrame.getContentPane().add(evaluate, BorderLayout.CENTER);
		integralFrame.getContentPane().add(integralValue, BorderLayout.SOUTH);
		integralFrame.pack();
		integralFrame.setVisible(true);
		evaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integral integral = new Integral();
				double result = integral.findDefiniteIntegral(Double.parseDouble(lowerLimit.getText()), Double.parseDouble(upperLimit.getText()), xValues, yValues);
				integralValue.setText(String.valueOf(result));
			}
	   	});
		//M Creating the MathEvaluator with a default equation.
		MathEvaluator m = new MathEvaluator("x^2");		
		//M Adding the variable to MathEvaluator with a placeholder value.
	   	m.addVariable("x", ((2.0)));
	   	//M Adding the ActionListener for the button.
	   	calculate.addActionListener(new ActionListener() {
	   		//M Let's do work on this branch for now, everything seems to be working, just need to add integrals.
	   		//M Some of the syntax is painful, but it all works on MathEvaluator.
			public void actionPerformed(ActionEvent e) {
				//M resetting yIndexTracker.
				yIndexTracker = 0;
				//M Setting the MathEvaluator to the entered equation.
				if(equation.getText().startsWith("poly")) {
					Expression ex = new Expression(equation.getText().substring(4));
					System.out.println(ex.getExpression());
					for(int i = 0; i <= (xValues.length - 1); i++) {
						ex.with("x", Double.toString(xValues[i]));
						yValues[yIndexTracker++] = ex.eval().doubleValue();
					}
				}
				else {
					m.setExpression(equation.getText());
					for(int i = 0; i <= (xValues.length - 1); i++) {
						m.addVariable("x", xValues[i]);
						yValues[yIndexTracker++] = m.getValue();
					}
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
				if(equation.getText().startsWith("poly")) {
					Expression ex = new Expression(equation.getText().substring(4));
					System.out.println(ex.getExpression());
					for(int i = 0; i <= (xValues.length - 1); i++) {
						ex.with("x", Double.toString(xValues[i]));
						yValues[yIndexTracker++] = ex.eval().doubleValue();
					}
				}
				else {
					m.setExpression(equation.getText());
					for(int i = 0; i <= (xValues.length - 1); i++) {
						m.addVariable("x", xValues[i]);
						yValues[yIndexTracker++] = m.getValue();
					}
				}
				yValues = Derivative.findDerivative(xValues, yValues);
				pointsReady = true;
				//M Resetting yIndexTracker.
				yIndexTracker = 0;
			}
	   	});
		secondDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M Resetting yIndexTracker.
				yIndexTracker = 0;
				//M Setting the MathEvaluator to the entered equation.
				if(equation.getText().startsWith("poly")) {
					Expression ex = new Expression(equation.getText().substring(4));
					System.out.println(ex.getExpression());
					for(int i = 0; i <= (xValues.length - 1); i++) {
						ex.with("x", Double.toString(xValues[i]));
						yValues[yIndexTracker++] = ex.eval().doubleValue();
					}
				}
				else {
					m.setExpression(equation.getText());
					for(int i = 0; i <= (xValues.length - 1); i++) {
						m.addVariable("x", xValues[i]);
						yValues[yIndexTracker++] = m.getValue();
					}
				}
				yValues = Derivative.findDerivative(xValues, yValues);
				yValues = Derivative.findDerivative(xValues, yValues);
				pointsReady = true;
				//M Resetting yIndexTracker.
				yIndexTracker = 0;
			}
	   	});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearDesired = true;
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
	private double [] polynomialCalculator(String polynomial, double [] xValues) {
		//M A method to calculate the values from polynomials.
		double [] intermediateValues = new double [20001];
		
		return intermediateValues;
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
	private boolean getClearDesired() {
		//M A method to get clearDesired.
		return clearDesired;
	}
	private void setPointsReady(boolean status) {
		//M A method to set pointsReady.
		pointsReady = status;
	}
	private void setClearDesired(boolean status) {
		//M A method to set clearDesired.
		clearDesired = status;
	}
	private void printValues(double [] xValues, double [] yValues) {
		//M A method to print (x,y) pairs. Used for debugging.
		for(int i = 0; i < xValues.length; i++) {
			System.out.println("( " + xValues[i] + " , " + yValues[i]+ " )");
		}
		//K A method to print zero pairs. Used for debugging?
		double[][] zeros = Zeroes.findZeros(xValues, yValues);
		if (zeros.length == 0) {
			System.out.println("No Zeroes");
		}else {
			for (int i = 0; i < zeros.length; i++) {
				System.out.println("Zero: (" + zeros[i][0] + "," + zeros[i][1] + ")");
			}
		}
		double[][] maxes = Zeroes.max(xValues, yValues);
		if (maxes.length == 0) {
			System.out.println("No Local Maxes");
		}else {
			for (int i = 0; i < zeros.length; i++) {
				System.out.println("Local max at: (" + maxes[i][0] + "," + maxes[i][1] + ")");
			}
		}
		double[][] mins = Zeroes.min(xValues, yValues);
		if (mins.length == 0) {
			System.out.println("No Local Maxes");
		}else {
			for (int i = 0; i < zeros.length; i++) {
				System.out.println("Local min at: (" + mins[i][0] + "," + mins[i][1] + ")");
			}
		}
		double[][] pois = Zeroes.POI(xValues, yValues);
		if (mins.length == 0) {
			System.out.println("No Points of Inflection");
		}else {
			for (int i = 0; i < zeros.length; i++) {
				System.out.println("Point of Inflection at: (" + mins[i][0] + "," + mins[i][1] + ")");
			}
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
				//M Sending the points to be graphed.
				graph.draw(gui.getXValues(), gui.getYValues());
				//M Preventing the method from executing again until the yValues are recalculated.
				gui.setPointsReady(false);
				/*M This is currently broken with at least trig functions and is crashing the program.
				 * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 6
					at GUI.printValues(GUI.java:213)
					at GUI.main(GUI.java:238) 
				 *  gui.printValues(gui.getXValues(), gui.getYValues()); 
				 */
			}
			else if(gui.getClearDesired() == true) {
				graph.clear();
				gui.setClearDesired(false);
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
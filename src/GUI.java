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
	private double [][] maxes;
	private double [][] mins;
	private double [][] pois;
	private double [][] holes;
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
		JButton evaluate = new JButton("Evaluate Integral");
		JTextArea integralValue = new JTextArea("No Value to Display");
		integralFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		integralFrame.setLocation(0, 250);
		integralFrame.getContentPane().add(lowerLimit, BorderLayout.WEST);
		integralFrame.getContentPane().add(upperLimit, BorderLayout.EAST);
		integralFrame.getContentPane().add(evaluate, BorderLayout.CENTER);
		integralFrame.getContentPane().add(integralValue, BorderLayout.SOUTH);
		integralFrame.pack();
		integralFrame.setVisible(true);
		JFrame trapezoidalIntegralFrame = new JFrame();
		JTextField trapezoidalLowerLimit = new JTextField("Enter lower limit here.");
		JTextField trapezoidalUpperLimit = new JTextField("Enter upper limit here.");
		JButton trapezoidalEvaluate = new JButton("Evaluate Trapezoidal Integral");
		JTextArea trapezoidalIntegralValue = new JTextArea("No Value to Display");
		JTextField function = new JTextField("Enter f(x)");
		trapezoidalIntegralFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trapezoidalIntegralFrame.setLocation(0, 450);
		trapezoidalIntegralFrame.getContentPane().add(function, BorderLayout.NORTH);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalLowerLimit, BorderLayout.WEST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalUpperLimit, BorderLayout.EAST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalEvaluate, BorderLayout.CENTER);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalIntegralValue, BorderLayout.SOUTH);
		trapezoidalIntegralFrame.pack();
		trapezoidalIntegralFrame.setVisible(true);
		
		trapezoidalEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double lowerValue;
				double upperValue;
				double FTC;
				MathEvaluator m = new MathEvaluator(function.getText());
				m.addVariable("x", Double.parseDouble(trapezoidalLowerLimit.getText()));
				lowerValue = m.getValue();
				m.addVariable("x", Double.parseDouble(trapezoidalUpperLimit.getText()));
				upperValue = m.getValue();
				FTC = upperValue - lowerValue;
				Integral integral = new Integral();
				String result = integral.trapezoidalIntegral(Double.parseDouble(trapezoidalLowerLimit.getText()), Double.parseDouble(trapezoidalUpperLimit.getText()), xValues, yValues);
				trapezoidalIntegralValue.setText(result + " = FTC: " + String.valueOf(FTC));
			}
	   	});
		
		evaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integral integral = new Integral();
				String result = integral.findDefiniteIntegral(Double.parseDouble(lowerLimit.getText()), Double.parseDouble(upperLimit.getText()), xValues, yValues);
				String FTC = integral.FTC(Double.parseDouble(lowerLimit.getText()), Double.parseDouble(upperLimit.getText()), xValues, yValues);
				integralValue.setText(result);
				//M This is broken for now. I don't get how we're supposed to show the FTC when we don't know
				//M The constant of integration.
				//integralValue.setText(result + " = " + FTC);
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
					if (m.getValue() == null) {
						equation.setText("Syntax Error");
						return;
					}
					else{
						for(int i = 0; i <= (xValues.length - 1); i++) {
							ex.with("x", Double.toString(xValues[i]));
							yValues[yIndexTracker++] = ex.eval().doubleValue();
						}
					}
				}
				else {
					m.setExpression(equation.getText());
					if (m.getValue() == null) {
						equation.setText("Syntax Error");
						return;
					}
					else{
						for(int i = 0; i <= (xValues.length - 1); i++) {
							m.addVariable("x", xValues[i]);
							yValues[i] = m.getValue();
						}
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
					if (ex.eval() == null) {
						equation.setText("Syntax Error");
						return;
					}
					else{
						for(int i = 0; i <= (xValues.length - 1); i++) {
							ex.with("x", Double.toString(xValues[i]));
							yValues[yIndexTracker++] = ex.eval().doubleValue();
						}
					}
				}
				else {
					m.setExpression(equation.getText());
					if (m.getValue() == null) {
						equation.setText("Syntax Error");
						return;
					}
					else{
						for(int i = 0; i <= (xValues.length - 1); i++) {
							m.addVariable("x", xValues[i]);
							yValues[yIndexTracker++] = m.getValue();
						}
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
					if (ex.eval() == null) {
						equation.setText("Syntax Error");
						return;
					}
					else{
						for(int i = 0; i <= (xValues.length - 1); i++) {
							ex.with("x", Double.toString(xValues[i]));
							yValues[yIndexTracker++] = ex.eval().doubleValue();
						}
					}
				}
				else {
					m.setExpression(equation.getText());
					if (m.getValue() == null) {
						equation.setText("Syntax Error");
						return;
					}
					else{
						for(int i = 0; i <= (xValues.length - 1); i++) {
							m.addVariable("x", xValues[i]);
							yValues[yIndexTracker++] = m.getValue();
						}
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
	private int getMaxesLength() {
		//M A method to get the length of maxes.
		return maxes.length;
	}
	private int getMinsLength() {
		//M A method to get the length of mins.
		return mins.length;
	}
	private int getPoisLength() {
		//M A method to get the length of pois.
		return pois.length;
	}
	private int getHolesLength() {
		//M A method to get the length of holes.
		return holes.length;
	}
	private double [][] getMaxes(){
		//M A method to get maxes.
		return maxes;
	}
	private double [][] getMins(){
		//M A method to get mins.
		return mins;
	}
	private double [][] getPois(){
		//M A method to get pois.
		return pois;
	}
	private double [][] getHoles(){
		//M a method to get holes.
		return holes;
	}
	private void setPointsReady(boolean status) {
		//M A method to set pointsReady.
		pointsReady = status;
	}
	private void setClearDesired(boolean status) {
		//M A method to set clearDesired.
		clearDesired = status;
	}
	private void findKeyPoints(double [] xValues, double [] yValues) {
		maxes = Zeroes.max(xValues, yValues);
		mins = Zeroes.min(xValues, yValues);
		pois = Zeroes.POI(xValues, yValues);
		holes = Zeroes.hole(xValues, yValues);
	}
	private void printValues(double [] xValues, double [] yValues) {
		//M A method to print (x,y) pairs. Used for debugging.
		for(int i = 0; i < xValues.length; i++) {
			System.out.println("( " + xValues[i] + " , " + yValues[i]+ " )");
		}
	}
	private void printKeyPoint(double [] xValues, double [] yValues, double keyValue) {
		//TODO A method to print a key point.
		for(int i = 0; i < xValues.length; i++) {
			if(xValues[i] == keyValue) {
				System.out.println("( " + xValues[i] + " , " + yValues[i] + " )");
			}
		}
	}
	private double getKeyPoint(double [] xValues, double [] yValues, double keyValue) {
		for(int i = 0; i < xValues.length; i++) {
			if(xValues[i] == keyValue) {
				return yValues[i];
			}
		}
		return 2;
	}
	private double [][] unifyKeyPoints(double [][] mins, double [][] maxes, double [][] pois, double [][] holes){
		//M A method to create a unified array of keyPoints.
		int length = (mins.length + maxes.length + pois.length + holes.length);
		System.out.println("Length: " + length);
		int indexTracker = 0;
		double [][] keyPoints = new double[length + 4][2];
		for(int i = 0; i < mins.length; i++) {
			keyPoints[indexTracker][0] = mins[i][0];
			keyPoints[indexTracker][0] = mins[i][0];
			indexTracker++;
		}
		//M CHANGE IF GIVING USER POWER TO SET WINDOW
		System.out.println("IT: " + indexTracker);
		keyPoints[indexTracker++][0] = -15;
		for(int i = 0; i < maxes.length; i++) {
			keyPoints[indexTracker][0] = maxes[i][0];
			keyPoints[indexTracker][0] = maxes[i][0];
			indexTracker++;
		}
		//M CHANGE IF GIVING USER POWER TO SET WINDOW
		System.out.println("IT: " + indexTracker);
		keyPoints[indexTracker++][0] = -15;
		for(int i = 0; i < pois.length; i++) {
			keyPoints[indexTracker][0] = pois[i][0];
			keyPoints[indexTracker][0] = pois[i][0];
			indexTracker++;
		}
		//M CHANGE IF GIVING USER POWER TO SET WINDOW
		keyPoints[indexTracker++][0] = -15;
		for(int i = 0; i < holes.length; i++) {
			keyPoints[indexTracker][0] = holes[i][0];
			keyPoints[indexTracker][0] = holes[i][0];
			indexTracker++;
		}
		//M CHANGE IF GIVING USER POWER TO SET WINDOW
		System.out.println("IT: " + indexTracker);
		keyPoints[indexTracker++][0] = -15;
		System.out.println();
		System.out.println("UNIFY KEY POINTS TEST DATA");
		System.out.println();
		for(int i = 0; i < keyPoints.length; i++) {
			System.out.println("( " + keyPoints[i][0] + " , " + keyPoints[i][1] + " )" );
		}
		return keyPoints;
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
				graph.draw();
				//M Calculating the key points, hopefully this leaves enough time.
				 gui.findKeyPoints(gui.getXValues(), gui.getYValues());
				for(int i = 0; i < gui.getHolesLength(); i++) {
					graph.draw(gui.getHoles()[i][0], gui.getHoles()[i][1], "#F44F0D");
				}
				//M Sending the points to be graphed.
				graph.draw(gui.getXValues(), gui.getYValues());
				//M Preventing the method from executing again until the yValues are recalculated.
				if(Zeroes.lineCheck(gui.getXValues(), gui.getYValues()) == false) {
					for(int i = 0; i < gui.getMaxesLength(); i++ ) {
						graph.draw(gui.getMaxes()[i][0], gui.getMaxes()[i][1], "#67D4C4");
						System.out.println("Drawing max ( " + gui.getMaxes()[i][0] + " , " + gui.getMaxes()[i][1] + " )");
					}
					for(int j = 0; j < gui.getMinsLength(); j++ ) {
						graph.draw(gui.getMins()[j][0], gui.getMins()[j][1], "#FFC0CB");
						System.out.println("Drawing min ( " + gui.getMins()[j][0] + " , " + gui.getMins()[j][1] + " )");
					}
					for(int k = 0; k < gui.getPoisLength(); k++ ) {
						//M This is drawing erroneous POIs at x = -10 in particular on some rational functions.
						//M Unfortunately, simply disabling them isn't an option unlike with lines.
						graph.draw(gui.getPois()[k][0], gui.getPois()[k][1], "#E9967A");
						System.out.println("Drawing POI ( " + gui.getPois()[k][0] + " , " + gui.getPois()[k][1] + " )");
					}
				}
				//gui.findKeyPoints(gui.getXValues(), gui.getYValues());
				//graph.drawGraph(gui.getXValues(), gui.getYValues(), gui.unifyKeyPoints(gui.getMins(), gui.getMaxes(), gui.getPois(), gui.getHoles()));
				gui.setPointsReady(false);
				// System.out.println(gui.getXValues()[20000]);
			}
			else if(gui.getClearDesired() == true) {
				graph.clear();
				// System.out.flush();
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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class GUI {
	// TODO a class to model a graphing calculator
	//M minimum positive value of the double used to increase by.
	public final static double step = Math.pow(10, -3);
	//M Tracks where points have been filled in the array.
	private int xIndexTracker;
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
	private String equationText = "";
	List<String> equations = new ArrayList<String>();
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
	   	//M Adding the ActionListener for the button.
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquation(equation.getText());
				pointsReady = true;
			}
	   	});
	   	firstDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquation("done" + equation.getText());
				pointsReady = true;
			}
	   	});
		secondDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquation("dtwo" + equation.getText());
				pointsReady = true;
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
	private void addEquation(String equation) {
		equations.add(equation);
		equationText = equation;
	}
	private String getEquationText() {
		return equationText;
	}
	private double[] getXValues() {
		//M A method to get the xValues.
		return xValues;
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
		holes = Zeroes.hole(xValues, yValues, equationText);
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
	private List getEquations() {
		return equations;
	}
	private double [] getYValues(double [] xValues, String equation, int derivativeNumber) {
		MathEvaluator m = new MathEvaluator();
		System.out.println("Equation: " + equation);
		m.setExpression(equation);
		double [] yValues = new double[20001];
		for(int i = 0; i <= (xValues.length - 1); i++) {
			m.addVariable("x", xValues[i]);
			System.out.println(String.valueOf("i: " + i + " / xValue: " + xValues[i] + " / value: " + m.getValue()));
			yValues[i] = m.getValue();
		}
		switch(derivativeNumber) {
			case 1:
				yValues = Derivative.findDerivative(xValues, yValues);
				return yValues;
			case 2:
				yValues = Derivative.findDerivative(xValues, yValues);
				yValues = Derivative.findDerivative(xValues, yValues);
				return yValues;
			default:
				return yValues;
		}
	}
	public static void main(String [] args) {
		GUI gui = new GUI();
		//M Filling xValues with consecutive x-values.
		gui.fillXValues();
		Graph graph = new Graph();
		double [] yValues = new double [20001];
		String equation = "";
		//M Infinite loop (AKA I have no idea how to synchronize) to continuously graph the updated yValues.
		while(true) {
			//M If the points are ready, then they are graphed.
			if(gui.getPointsReady() == true) {
				graph.clear();
				graph.draw();
				for(int i = 0; i < gui.getEquations().size(); i++) {
					equation = (String) gui.getEquations().get(i);
					if(equation.startsWith("done")) {
						yValues = gui.getYValues(gui.getXValues(), equation, 2);
					}
					else if(equation.startsWith("dtwo")) {
						yValues = gui.getYValues(gui.getXValues(), equation, 1);
					}
					else {
						//M Need to add syntax handling.
						yValues = gui.getYValues(gui.getXValues(), equation, 0);
					}
					graph.draw(gui.getXValues(), yValues);
					//M Calculating the key points, hopefully this leaves enough time.
					gui.findKeyPoints(gui.getXValues(), yValues);
					for(int j = 0; j < gui.getHolesLength(); j++) {
						graph.draw(gui.getHoles()[j][0], gui.getHoles()[j][1], "#F44F0D");
					}
					//M Preventing the method from executing again until the yValues are recalculated.
					if(Zeroes.lineCheck(gui.getXValues(), yValues) == false) {
						for(int k = 0; k < gui.getMaxesLength(); k++ ) {
							graph.draw(gui.getMaxes()[k][0], gui.getMaxes()[k][1], "#67D4C4");
							System.out.println("Drawing max ( " + gui.getMaxes()[k][0] + " , " + gui.getMaxes()[k][1] + " )");
						}
						for(int l = 0; l < gui.getMinsLength(); l++ ) {
							graph.draw(gui.getMins()[l][0], gui.getMins()[l][1], "#FFC0CB");
							System.out.println("Drawing min ( " + gui.getMins()[l][0] + " , " + gui.getMins()[l][1] + " )");
						}
						for(int m = 0; m < gui.getPoisLength(); m++ ) {
							//M This is drawing erroneous POIs at x = -10 in particular on some rational functions.
							//M Unfortunately, simply disabling them isn't an option unlike with lines.
							graph.draw(gui.getPois()[m][0], gui.getPois()[m][1], "#E9967A");
							System.out.println("Drawing POI ( " + gui.getPois()[m][0] + " , " + gui.getPois()[m][1] + " )");
						}
					}
					gui.setPointsReady(false);
				}
				//M Sending the points to be graphed.
				
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
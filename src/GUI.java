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
	double step = Math.pow(10, -3);
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
	private int equationIndexTracker = -1;
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
		
		JFrame trapezoidalIntegralFrame = new JFrame();
		JTextField trapezoidalLowerLimit = new JTextField("Lower Limit");
		JTextField trapezoidalUpperLimit = new JTextField("Upper Limit");
		JButton trapezoidalEvaluate = new JButton("Evaluate");
		JTextArea trapezoidalIntegralValue = new JTextArea("No Value to Display");
		JTextField function = new JTextField("Enter f(x)");
		trapezoidalIntegralFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trapezoidalIntegralFrame.setLocation(0, 250);
		trapezoidalIntegralFrame.getContentPane().add(function, BorderLayout.NORTH);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalLowerLimit, BorderLayout.WEST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalUpperLimit, BorderLayout.EAST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalEvaluate, BorderLayout.CENTER);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalIntegralValue, BorderLayout.SOUTH);
		trapezoidalIntegralFrame.pack();
		trapezoidalIntegralFrame.setSize(350, 100);
		trapezoidalIntegralFrame.setVisible(true);
		
		trapezoidalEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempEquation;
				double lowerValue;
				double upperValue;
				double FTC;
				System.out.println("f(x): " + function.getText());
				MathEvaluator m = new MathEvaluator(function.getText());
				m.addVariable("x", Double.parseDouble(trapezoidalLowerLimit.getText()));
				lowerValue = m.getValue();
				System.out.println("lowerValue: " + lowerValue);
				m.addVariable("x", Double.parseDouble(trapezoidalUpperLimit.getText()));
				upperValue = m.getValue();
				System.out.println("Upper Value: " + upperValue);
				FTC = upperValue - lowerValue;
				Integral integral = new Integral();
				tempEquation = equations.get(equationIndexTracker);
				System.out.println("Unprocessed Temp: " + tempEquation);
				if(tempEquation.startsWith("done")) {
					tempEquation = tempEquation.substring(4);
					m.setExpression(tempEquation);
					for(int i = 0; i <= (xValues.length -1); i++) {
						m.addVariable("x", xValues[i]);
						yValues[i] = m.getValue();
					}
					yValues = Derivative.findDerivative(xValues, yValues);
				}
				else if(tempEquation.startsWith("dtwo")) {
					tempEquation = tempEquation.substring(4);
					m.setExpression(tempEquation);
					for(int i = 0; i <= (xValues.length -1); i++) {
						m.addVariable("x", xValues[i]);
						yValues[i] = m.getValue();
					}
					yValues = Derivative.findDerivative(xValues, yValues);
					yValues = Derivative.findDerivative(xValues, yValues);
				}
				else {
					m.setExpression(tempEquation);
					for(int i = 0; i <= (xValues.length -1); i++) {
						m.addVariable("x", xValues[i]);
						yValues[i] = m.getValue();
					}
				}
				System.out.println("Temp Equation: " + tempEquation);
				
				//M Still not working with derivatives.
				String result = integral.trapezoidalIntegral(Double.parseDouble(trapezoidalLowerLimit.getText()), Double.parseDouble(trapezoidalUpperLimit.getText()), xValues, yValues);
				trapezoidalIntegralValue.setText(result + " = FTC: " + String.valueOf(FTC));
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
	public void fillXValues() {
		//M A method to fill xValues with consecutive numbers.
		/*M The index of xValues is tracked separately from the loop to allow arithmetic to be done 
		 * with the loop counter.*/
		xIndexTracker = 0;
		for(int i = (int) (-1 * Math.pow(10,4)); i <= (int) Math.pow(10, 4); i++) {
			xValues[xIndexTracker++] = (double) (i * step);
		}
	}
	private void addEquation(String equation) {
		equationIndexTracker++;
		equations.add(equation);
		equationText = equation;
	}
	public String getEquationText() {
		return equationText;
	}
	public double[] getXValues() {
		//M A method to get the xValues.
		return xValues;
	}
	
	public boolean getPointsReady() {
		//M A method to get pointsReady.
		return pointsReady;
	}
	public boolean getClearDesired() {
		//M A method to get clearDesired.
		return clearDesired;
	}
	public int getMaxesLength() {
		//M A method to get the length of maxes.
		return maxes.length;
	}
	public int getMinsLength() {
		//M A method to get the length of mins.
		return mins.length;
	}
	public int getPoisLength() {
		//M A method to get the length of pois.
		return pois.length;
	}
	public int getHolesLength() {
		//M A method to get the length of holes.
		return holes.length;
	}
	public double [][] getMaxes(){
		//M A method to get maxes.
		return maxes;
	}
	public double [][] getMins(){
		//M A method to get mins.
		return mins;
	}
	public double [][] getPois(){
		//M A method to get pois.
		return pois;
	}
	public double [][] getHoles(){
		//M a method to get holes.
		return holes;
	}
	public void setPointsReady(boolean status) {
		//M A method to set pointsReady.
		pointsReady = status;
	}
	public void setClearDesired(boolean status) {
		//M A method to set clearDesired.
		clearDesired = status;
		equations.clear();
		equationIndexTracker = -1;
	}
	public void findKeyPoints(double [] xValues, double [] yValues) {
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
	public List<String> getEquations() {
		return equations;
	}
	public double [] getYValues(double [] xValues, String equation, int derivativeNumber) {
		MathEvaluator m = new MathEvaluator();
		m.setExpression(equation);
		double [] yValues = new double[20001];
		for(int i = 0; i <= (xValues.length - 1); i++) {
			m.addVariable("x", xValues[i]);
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
}
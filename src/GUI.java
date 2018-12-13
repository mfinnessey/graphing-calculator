//M Various necessary listener imports.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Various necessary list imports.
import java.util.ArrayList;
import java.util.List;
//M A timing import.
import java.util.concurrent.TimeUnit;
//M Various necessary graphics imports.
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	//M The value by which the stored values are incremented, 0.001.
	public final static double step = Math.pow(10, -4);
	//M Tracks where points have been filled in the xValues array.
	private int xIndexTracker;
	//M Arrays to store the x and y values respectively.
	private double [] xValues = new double[200001];
	private double [] yValues = new double [200001];
	//M Arrays to store the various kinds of key points. Their size is determined later.
	private double [][] zeros;
	private double [][] maxes;
	private double [][] mins;
	private double [][] pois;
	private double [][] holes;
	//M Booleans to indicate  when a new equation is ready to graph.
	private boolean newEquationReady = false;
	private boolean clearDesired = false;
	//M A String to store the text of the currently entered equation.
	private String equationText = "";
	//M An integer to track the length of equations.
	private int equationIndexTracker = -1;
	//M A List to store the text of all entered (or calculated) equations.
	List<String> equations = new ArrayList<String>();
	public GUI() {
		//M Creating the various swing components.
		JFrame frame = new JFrame();
		//M Text field in which the user can enter the equation.
		JTextField equation = new JTextField("enter function here");
		//M A button for the user to press when they want to graph a function.
		JButton calculate = new JButton("Calculate");
		//M A button for the user to press to take the first derivative of the currently entered equation.
		JButton firstDerivative = new JButton("First Derivative");
		//M A button for the user to press to take the second derivative of the currently entered equation.
		JButton secondDerivative = new JButton("Second Derivative");
		//M A button for the user to press to clear the graph.
		JButton clear = new JButton("Clear");
		
		//M Setting up the various swing components for the main frame.
		//M Setting the program to exit when the window is closed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//M Adding the various buttons to the JFrame.
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(firstDerivative, BorderLayout.NORTH);
		frame.getContentPane().add(secondDerivative, BorderLayout.WEST);
		frame.getContentPane().add(clear, BorderLayout.EAST);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		//M Packing the main frame.
		frame.pack();
		//M Making the main frame visible.
		frame.setVisible(true);
		
		//M Creating the various swing components for the definite integral frame.
		JFrame trapezoidalIntegralFrame = new JFrame();
		//M Text field for the user to enter the lower limit of integration.
		JTextField trapezoidalLowerLimit = new JTextField("Enter lower limit here.");
		//M Text field for the user to enter the upper limit of integration.
		JTextField trapezoidalUpperLimit = new JTextField("Enter upper limit here.");
		//M Button for the user to click to evaluate the definite integral.
		JButton trapezoidalEvaluate = new JButton("Evaluate Integral");
		//M Text area to display the value of the definite integral.
		JTextArea trapezoidalIntegralValue = new JTextArea("No Value to Display");
		//M Text field for the user to enter the function to be integrated.
		//JTextField function = new JTextField("Enter f(x)");
		
		//M Setting up the various swing components for the integral frame.
		//M Setting the program to close when the window is closed.
		trapezoidalIntegralFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//M Setting the location of the frame on screen.
		trapezoidalIntegralFrame.setLocation(0, 250);
		//M Adding the various swing components to the JFrame.
		//trapezoidalIntegralFrame.getContentPane().add(function, BorderLayout.NORTH);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalLowerLimit, BorderLayout.WEST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalUpperLimit, BorderLayout.EAST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalEvaluate, BorderLayout.CENTER);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalIntegralValue, BorderLayout.SOUTH);
		//M Packing the definite integral frame.
		trapezoidalIntegralFrame.pack();
		//M Making the definite integral frame visible.
		trapezoidalIntegralFrame.setVisible(true);
		
		//M Creating the various swing components for the definite integral frame.
		JFrame yValueFrame = new JFrame();
		JTextField enterX = new JTextField("Enter x value here.");
		JButton evaluatefx = new JButton("Evaluate f(x) at x");
		JTextArea fxValue = new JTextArea("No Value to Display");
		
		yValueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//M Setting the location of the frame on screen.
		yValueFrame.setLocation(0, 150);
		yValueFrame.getContentPane().add(enterX, BorderLayout.NORTH);
		yValueFrame.getContentPane().add(evaluatefx, BorderLayout.CENTER);
		yValueFrame.getContentPane().add(fxValue, BorderLayout.SOUTH);
		yValueFrame.pack();
		yValueFrame.setVisible(true);

		//K: Adding listener to evaluate f(x)
		evaluatefx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathEvaluator m = new MathEvaluator(equation.getText());
				//K Calculating f(x) from the user-entered a value.
				m.addVariable("x", Double.parseDouble(enterX.getText()));
				//M Storing result
				String result = Double.toString(m.getValue());
				//K Rounding down to 3 decimal places
				for (int j = 0; j < result.length(); j++) {
					if ((result.substring(j, j + 1)).compareTo(".") == 0 && (result.length() - j) > 4) {
						result = result.substring(0,j+4);
					}
				}
				fxValue.setText(result);
			}
	   	});
				
		//M Adding listeners to the buttons so that they work.
		trapezoidalEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M A string to store the f'(x) equation from equations.
				String tempEquation;
				/*
				//M doubles to store the calculated f(b) and f(a) values.
				double lowerValue;
				double upperValue;
				//M A double to store f(b)-f(a)
				double FTC;
				//M Creating a MathEvaluator to calculate the various arithmetic from the functions.
				//M Setting the MathEvaluator's equation to the user-entered f(x)
				*/
				MathEvaluator m = new MathEvaluator(equation.getText());
				/*
				//M Calculating f(a) from the user-entered a value.
				//m.addVariable("x", Double.parseDouble(trapezoidalLowerLimit.getText()));
				//M Storing f(a)
				lowerValue = m.getValue();
				//M Calculating f(b) from the user-entered b value.
				m.addVariable("x", Double.parseDouble(trapezoidalUpperLimit.getText()));
				//M Storing f(b)
				upperValue = m.getValue();
				//M Calculating and storing f(b) - f(a).
				FTC = upperValue - lowerValue;
				//M Creating a new Integral class, which is used for its methods later.
				 */
				Integral integral = new Integral();
				//M Getting the f'(x) equation that the user has previously graphed.
				tempEquation = equations.get(equationIndexTracker);
				//M Storing the values from the temporary equation after processing it.
				if(tempEquation.startsWith("done")) {
					//M Trimming off the initial first derivative indicator.
					tempEquation = tempEquation.substring(4);
					//M Setting the MathEvaluator's expression to the processed equation.
					m.setExpression(tempEquation);
					//M Calculating all of the y values of the equation.
					for(int i = 0; i <= (xValues.length -1); i++) {
						//M Adding each x value.
						m.addVariable("x", xValues[i]);
						//M Calculating each resulting y value.
						yValues[i] = m.getValue();
					}
					//M Taking the derivative of these values (as the values calculated were for f(x), not f'(x).).
					yValues = Derivative.findDerivative(xValues, yValues);
				}
				else if(tempEquation.startsWith("dtwo")) {
					//M Trimming off the initial second derivative indicator.
					tempEquation = tempEquation.substring(4);
					//M Setting the MathEvaluator's expression to the processed equation.
					m.setExpression(tempEquation);
					//M Calculating all of the y values of the equation.
					for(int i = 0; i <= (xValues.length -1); i++) {
						//M Adding each x value.
						m.addVariable("x", xValues[i]);
						//M Calculating each resulting y value.
						yValues[i] = m.getValue();
					}
					//M Taking the derivative of these values twice (as the values calculated were for f(x), not f"(x)).
					yValues = Derivative.findDerivative(xValues, yValues);
					yValues = Derivative.findDerivative(xValues, yValues);
				}
				//M Otherwise, the equation is simply already considered processed.
				else {
					//M Setting MathEvaluator's expression to the equation.
					m.setExpression(tempEquation);
					//M Calculating all of the y values of the equation.
					for(int i = 0; i <= (xValues.length -1); i++) {
						//M Adding each x value.
						m.addVariable("x", xValues[i]);
						//M Calculating each y value.
						yValues[i] = m.getValue();
					}
				}
				//M Storing the result of the integral, with all of the previously calculated arguments passed off to the Integral object.
				String result = integral.trapezoidalIntegral(Double.parseDouble(trapezoidalLowerLimit.getText()), Double.parseDouble(trapezoidalUpperLimit.getText()), xValues, yValues);
				//M Setting the display text to the integral.
				trapezoidalIntegralValue.setText(result);
			}
	   	});
	   	//M Adding the ActionListener for the calculate button.
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M Storing the user-entered equation.
				addEquation(equation.getText());
				//M Setting that a new equation is ready to be graphed.
				newEquationReady = true;
			}
	   	});
	   	//M Adding the ActionListener for the firstDerivative button.
	   	firstDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M Storing the first derivative of the user-entered equation.
				addEquation("done" + equation.getText());
				//M Setting that a new equation is ready to be graphed.
				newEquationReady = true;
			}
	   	});
	   	//M Adding the ActionListener for the secondDerivative button.
		secondDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M Storing the second derivative of the user-entered equation.
				addEquation("dtwo" + equation.getText());
				//M Setting that a new equation is ready to be graphed.
				newEquationReady = true;
			}
	   	});
		//M Adding the ActionListener for the clear button.
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M Setting that the user wants to clear the graph.
				clearDesired = true;
			}
	   	});
	}
	private void fillXValues() {
		//TODO A method to fill xValues with consecutive numbers.
		//M An integer to track where xValues has been filled to.
		xIndexTracker = 0;
		//M iterating through the range of numbers that xValues will be filled with (made to fit in an integer range).
		for(int i = (int) (-1 * Math.pow(10,5)); i <= (int) Math.pow(10, 5); i++) {
			//M Setting the next value in xValues to the given value, downsized by step to its proper value.
			xValues[xIndexTracker++] = (double) (i * step);
		}
	}
	private void addEquation(String equation) {
		//TODO A method to add an equation to equations.
		//M Incrementing equationIndexTracker, which tracks where the List has been filled to.
		equationIndexTracker++;
		//M Adding the equation to equations.
		equations.add(equation);
	}
	private double[] getXValues() {
		//TODO A method to get xValues.
		return xValues;
	}
	
	private boolean getNewEquationReady() {
		//TODO A method to get pointsReady.
		return newEquationReady;
	}
	private boolean getClearDesired() {
		//TODO A method to get clearDesired.
		return clearDesired;
	}
	
	private int getZerosLength() {
		//TODO A method to get the length of zeros.
		return zeros.length;
	}
	
	private int getMaxesLength() {
		//TODO A method to get the length of maxes.
		return maxes.length;
	}
	private int getMinsLength() {
		//TODO A method to get the length of mins.
		return mins.length;
	}
	private int getPoisLength() {
		//TODO A method to get the length of pois.
		return pois.length;
	}
	private int getHolesLength() {
		//TODO A method to get the length of holes.
		return holes.length;
	}
	
	private double[][] getZeros(){
		//TODO A method to get zeros.
		return zeros;
	}
	
	private double [][] getMaxes(){
		//TODO method to get maxes.
		return maxes;
	}
	private double [][] getMins(){
		//TODO A method to get mins.
		return mins;
	}
	private double [][] getPois(){
		//TODO A method to get pois.
		return pois;
	}
	private double [][] getHoles(){
		//TODO a method to get holes.
		return holes;
	}
	private void setNewEquationReady(boolean status) {
		//TODO A method to set equationReady.
		newEquationReady = status;
	}
	private void setClearDesired(boolean status) {
		//TODO A method to set clearDesired.
		//M Setting clearDesired.
		clearDesired = status;
		//M Clearing equations so that they are not re-graphed.
		equations.clear();
		//M Resetting equationIndexTracker so it begins at the start of the cleared list.
		equationIndexTracker = -1;
	}
	private void findKeyPoints(double [] xValues, double [] yValues, String equationText) {
		//TODO A method to find the key points of a function.
		//M Passing the calculated x and y values on to the various methods contained in other classes and storing the results in arrays.
		zeros = Zeroes.findZeros(xValues, yValues);
		maxes = Zeroes.max(xValues, yValues);
		mins = Zeroes.min(xValues, yValues);
		pois = Zeroes.POI(xValues, yValues);
		holes = Zeroes.hole(xValues, yValues, equationText);
	}
	//M Will likely remove these methods before the final product, leaving in now for debugging.
	private void printValues(double [] xValues, double [] yValues) {
		//M A method to print (x,y) pairs. Used for debugging.
		//M Iterating through the values.
		for(int i = 0; i < xValues.length; i++) {
			//M Printing each value.
			System.out.println("( " + xValues[i] + " , " + yValues[i]+ " )");
		}
	}
	private void printKeyPoint(double [] xValues, double [] yValues, double keyValue) {
		//TODO A method to print a key point.
		//M Iterating through the values.
		for(int i = 0; i < xValues.length; i++) {
			//M Checking if the value matches the desired y value.
			if(xValues[i] == keyValue) {
				//M If the value matches, then its (x,y) pair is printed.
				System.out.println("( " + xValues[i] + " , " + yValues[i] + " )");
			}
		}
	}
	private double getKeyPoint(double [] xValues, double [] yValues, double keyValue) {
		//TODO A method to get the y value of a given x value.
		//M Iterating through the values.
		for(int i = 0; i < xValues.length; i++) {
			//M Checking if the x value is the appropriate one.
			if(xValues[i] == keyValue) {
				//M Returning the desired x value's y value.
				return yValues[i];
			}
		}
		//M A return case if the x value is not found in the values given.
		return 2;
	}
	private List<String> getEquations() {
		//TODO A method to get equations.
		return equations;
	}
	private double [] getYValues(double [] xValues, String equation, int derivativeNumber) {
		//TODO A method to calculate and get yValues.
		//M Creating a MathEvaluator to calculate the y values from the x values and the equation.
		MathEvaluator m = new MathEvaluator();
		//M Setting the mathEvaluator's expression to the given function.
		m.setExpression(equation);
		//M Creating an array to store the y values temporarily.
		double [] yValues = new double[200001];
		//M Iterating through the x values
		for(int i = 0; i <= (xValues.length - 1); i++) {
			//M Adding each value to MathEvaluator.
			m.addVariable("x", xValues[i]);
			//M Calculating the resulting y value and storing it.
			yValues[i] = m.getValue();
		}
		//M If the values need to have derivatives taken (derivativeNumber is calculated beforehand).
		switch(derivativeNumber) {
			//M If it's the first derivative, then the derivative is taken once.
			case 1:
				yValues = Derivative.findDerivative(xValues, yValues);
				return yValues;
			//M If it's the second derivative, then the derivative is taken twice.
			case 2:
				yValues = Derivative.findDerivative(xValues, yValues);
				yValues = Derivative.findDerivative(xValues, yValues);
				return yValues;
			//M Otherwise, the already calculated values are returned.
			default:
				return yValues;
		}
	}
	
	public static void main(String [] args) {
		//M Creating a GUI object to access its methods and present its interface to the user.
		GUI gui = new GUI();
		//M Filling xValues with consecutive x-values.
		gui.fillXValues();
		//M Creating a graph object to display on the screen with the graphs.
		Graph graph = new Graph();
		//M Creating an array to store yValues.
		double [] yValues = new double [20001];
		//M Creating a STring to store the equation text.
		String equation = "";
		String rawEquation = "";
		//M Infinite loop to continuously graph the updated yValues.
		while(true) {
			//M If the equation is ready, then its points are calculated and graphed.
			if(gui.getNewEquationReady() == true) {
				//M Clearing the graph so that the grid-lines always are on the bottom layer.
				graph.clear();
				//M Drawing the axes, numbers, and grid-lines.
				graph.draw();
				//M Iterating through the list of equations.
				for(int i = 0; i < gui.getEquations().size(); i++) {
					//M Setting the current equation to the current equation in the list.
					equation = (String) gui.getEquations().get(i);
					//M Calculating the values of the equation.
					//M Checking for derivative indicators.
					rawEquation = equation;
					if(equation.startsWith("dtwo")) {
						//M Removing the derivative indicator.
						equation = equation.substring(4);
						//M Getting the y values of the equation.
						yValues = gui.getYValues(gui.getXValues(), equation, 2);
					}
					else if(equation.startsWith("done")) {
						//M Removing the derivative indicator.
						equation = equation.substring(4);
						//M Getting the y values of the equation.
						yValues = gui.getYValues(gui.getXValues(), equation, 1);
					}
					else {
						//M Getting the values of the equation.
						yValues = gui.getYValues(gui.getXValues(), equation, 0);
					}
					//M Drawing the points calculated from the equation.
					graph.draw(gui.getXValues(), yValues);
					//M Calculating the key points for the equation.
					gui.findKeyPoints(gui.getXValues(), yValues, equation);
					//M Iterating through the holes in the graph.
					for(int j = 0; j < gui.getHolesLength(); j++) {
						//M Drawing each hole.
						graph.draw(gui.getHoles()[j][0], gui.getHoles()[j][1], "#F44F0D");
						System.out.println("Drawing hole ( " + gui.getHoles()[j][0] + " , " + gui.getHoles()[j][1] + " )");
					}
					//M Preventing mins, maxes, and pois from being drawn on lines (becomes a slight issue with the limit definition at lower precision).
					if(Zeroes.lineCheck(gui.getXValues(), yValues, rawEquation) == false) {
						//M Iterating through the zeroes of the graph.
						for(int n = 0; n < gui.getZerosLength(); n++ ) {
							//M Drawing each zero.
							graph.draw(gui.getZeros()[n][0], gui.getZeros()[n][1], "#00FF00");
							System.out.println("Drawing zero ( " + gui.getZeros()[n][0] + " , " + gui.getZeros()[n][1] + " )");
						}
						//M Iterating through the maxes of the graph.
						for(int k = 0; k < gui.getMaxesLength(); k++ ) {
							//M Drawing each max.
							graph.draw(gui.getMaxes()[k][0], gui.getMaxes()[k][1], "#67D4C4");
							System.out.println("Drawing max ( " + gui.getMaxes()[k][0] + " , " + gui.getMaxes()[k][1] + " )");
						}
						//M Iterating through the mins of the graph.
						for(int l = 0; l < gui.getMinsLength(); l++ ) {
							//M Drawing each min.
							graph.draw(gui.getMins()[l][0], gui.getMins()[l][1], "#6600FF");
							System.out.println("Drawing min ( " + gui.getMins()[l][0] + " , " + gui.getMins()[l][1] + " )");
						}
						//M Iterating through the points of inflection of the graph.
						for(int m = 0; m < gui.getPoisLength(); m++ ) {
							//M Drawing each point of inflection.
							graph.draw(gui.getPois()[m][0], gui.getPois()[m][1], "#FFFF00");
							System.out.println("Drawing POI ( " + gui.getPois()[m][0] + " , " + gui.getPois()[m][1] + " )");
						}
					}
				//M Preventing the drawing from repeating again until a new equation is ready (i. e. entered by the user).
				gui.setNewEquationReady(false);
				System.out.println();
				}
			}
			//M Clearing the graph if the user desires it.
			else if(gui.getClearDesired() == true) {
				//M Clearing the graph.
				graph.clear();
				//M Redrawing the axes, grid-lines, and numbers.
				graph.draw();
				//M Setting that the user has not yet requested to clear the graph again.
				gui.setClearDesired(false);
			}
			//M This waiting aids in synchronization of the various methods (which take time to calculate internally).
			else {
				//M Trying to wait.
				try {
					//M Waiting for a second.
					TimeUnit.SECONDS.sleep(1);
					//M If the waiting fails, a stack trace is printed.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
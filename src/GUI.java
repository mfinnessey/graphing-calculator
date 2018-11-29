
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
	public final static double step = Math.pow(10, -3);
	//M Tracks where points have been filled in the xValues array.
	private int xIndexTracker;
	//M Arrays to store the x and y values respectively.
	private double [] xValues = new double[20001];
	private double [] yValues = new double [20001];
	//M Arrays to store the various kinds of key points. Their size is determined later.
	private double [][] zeros;
	private double [][] maxes;
	private double [][] mins;
	private double [][] pois;
	private double [][] holes;
	//M Booleans to indicate  when an equation has been entered.
	private boolean equationReady = false;
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
		JButton trapezoidalEvaluate = new JButton("Evaluate Trapezoidal Integral");
		//M Text area to display the value of the definite integral.
		JTextArea trapezoidalIntegralValue = new JTextArea("No Value to Display");
		//M Text field for the user to enter the function to be integrated.
		JTextField function = new JTextField("Enter f(x)");
		
		//M Setting up the various swing components for the integral frame.
		//M Setting the program to close when the window is closed.
		trapezoidalIntegralFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//M Setting the location of the frame on screen.
		trapezoidalIntegralFrame.setLocation(0, 250);
		//M Adding the various swing components to the JFrame.
		trapezoidalIntegralFrame.getContentPane().add(function, BorderLayout.NORTH);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalLowerLimit, BorderLayout.WEST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalUpperLimit, BorderLayout.EAST);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalEvaluate, BorderLayout.CENTER);
		trapezoidalIntegralFrame.getContentPane().add(trapezoidalIntegralValue, BorderLayout.SOUTH);
		//M Packing the definite integral frame.
		trapezoidalIntegralFrame.pack();
		//M Making the definite integral frame visible.
		trapezoidalIntegralFrame.setVisible(true);
		
		//M Adding listeners to the buttons so that they work.
		trapezoidalEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//M A string to store the f'(x) equation from equations.
				String tempEquation;
				//M doubles to store the calculated f(b) and f(a) values.
				double lowerValue;
				double upperValue;
				//M A double to store f(b)-f(a)
				double FTC;
				//M Creating a MathEvaluator to calculate the various arithmetic from the functions.
				//M Setting the MathEvaluator's equation to the user-entered f(x)
				MathEvaluator m = new MathEvaluator(function.getText());
				//M Calculating f(a) from the user-entered a value.
				m.addVariable("x", Double.parseDouble(trapezoidalLowerLimit.getText()));
				//M Storing f(a)
				lowerValue = m.getValue();
				//M Calculating f(b) from the user-entered b value.
				m.addVariable("x", Double.parseDouble(trapezoidalUpperLimit.getText()));
				//M Storing f(b)
				upperValue = m.getValue();
				//M Calculating and storing f(b) - f(a).
				FTC = upperValue - lowerValue;
				//M Creating a new Integral class, which is used for its methods later.
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
				//M Setting the display text to the two values calculated by the FTC and the limit definition of the integral.
				trapezoidalIntegralValue.setText(result + " = FTC: " + String.valueOf(FTC));
			}
	   	});
	   	//M Adding the ActionListener for the button.
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquation(equation.getText());
				equationReady = true;
			}
	   	});
	   	firstDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquation("done" + equation.getText());
				equationReady = true;
			}
	   	});
		secondDerivative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEquation("dtwo" + equation.getText());
				equationReady = true;
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
		equationIndexTracker++;
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
	
	private boolean getEquationReady() {
		//M A method to get pointsReady.
		return equationReady;
	}
	private boolean getClearDesired() {
		//M A method to get clearDesired.
		return clearDesired;
	}
	
	private int getZerosLength() {
		//K A method to get the length of zeros.
		return zeros.length;
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
	
	private double[][] getZeros(){
		//K A method to get zeros.
		return zeros;
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
	private void setEquationReady(boolean status) {
		//M A method to set equationReady.
		equationReady = status;
	}
	private void setClearDesired(boolean status) {
		//M A method to set clearDesired.
		clearDesired = status;
		equations.clear();
		equationIndexTracker = -1;
	}
	private void findKeyPoints(double [] xValues, double [] yValues) {
		zeros = Zeroes.findZeros(xValues, yValues);
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
	private List<String> getEquations() {
		return equations;
	}
	private double [] getYValues(double [] xValues, String equation, int derivativeNumber) {
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
			if(gui.getEquationReady() == true) {
				graph.clear();
				graph.draw();
				for(int i = 0; i < gui.getEquations().size(); i++) {
					equation = (String) gui.getEquations().get(i);
					if(equation.startsWith("dtwo")) {
						equation = equation.substring(4);
						yValues = gui.getYValues(gui.getXValues(), equation, 2);
					}
					else if(equation.startsWith("done")) {
						equation = equation.substring(4);
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
						System.out.println("Drawing hole ( " + gui.getHoles()[j][0] + " , " + gui.getHoles()[j][1] + " )");
					}
					//M Preventing the method from executing again until the yValues are recalculated.
					if(Zeroes.lineCheck(gui.getXValues(), yValues) == false) {
						for(int n = 0; n < gui.getZerosLength(); n++ ) {
							graph.draw(gui.getZeros()[n][0], gui.getZeros()[n][1], "#00FF00");
							System.out.println("Drawing zero ( " + gui.getZeros()[n][0] + " , " + gui.getZeros()[n][1] + " )");
						}
						for(int k = 0; k < gui.getMaxesLength(); k++ ) {
							graph.draw(gui.getMaxes()[k][0], gui.getMaxes()[k][1], "#67D4C4");
							System.out.println("Drawing max ( " + gui.getMaxes()[k][0] + " , " + gui.getMaxes()[k][1] + " )");
						}
						for(int l = 0; l < gui.getMinsLength(); l++ ) {
							graph.draw(gui.getMins()[l][0], gui.getMins()[l][1], "#6600FF");
							System.out.println("Drawing min ( " + gui.getMins()[l][0] + " , " + gui.getMins()[l][1] + " )");
						}
						for(int m = 0; m < gui.getPoisLength(); m++ ) {
							//M This is drawing erroneous POIs at x = -10 in particular on some rational functions.
							//M Unfortunately, simply disabling them isn't an option unlike with lines.
							graph.draw(gui.getPois()[m][0], gui.getPois()[m][1], "#FFFF00");
							System.out.println("Drawing POI ( " + gui.getPois()[m][0] + " , " + gui.getPois()[m][1] + " )");
						}
					}
					gui.setEquationReady(false);
					System.out.println();
				}
				//M Sending the points to be graphed.
				
			}
			else if(gui.getClearDesired() == true) {
				graph.clear();
				graph.draw();
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
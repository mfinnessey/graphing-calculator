import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class GUI {
	// TODO a class to model a graphing calculator
	//M minimum positive value of the double used to increase by.
	public final static double step = Double.MIN_NORMAL;
	//M Tracks where points have been filled in the array.
	private int indexTracker = 0;
	//M Store the x and y values respectively.
	//M We're trying this. IDK if it works. YOLO.
	private double [] xValues = new double [Integer.MAX_VALUE-2];
	private double [] yValues = new double [Integer.MAX_VALUE-2];
	//M Weird and messed up constructor. It works for now, if we can clean it up later, we might want to.
	private void fillXValues() {
		for(int i = -10; i <= 10; i+=step) {
			xValues[indexTracker] = i;
			indexTracker++;
		}
		System.out.println("Done!");
		indexTracker = 0;
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
				//M Setting the MathEvaluator to the entered equation.
				m.setExpression(equation.getText());
				for(int i = -5; i <= 5; i++) {
					m.addVariable("x", (i));
					System.out.println(m.getValue());
					yValues[indexTracker++] = m.getValue();
				}
				for(int i = 0; i <= 10; i++) {
					System.out.println((i-5) + "," + yValues[i]);
				}
			}
	   	});
	   	
	}
	
	private double[] getXValues() {
		return xValues;
	}
	
	private double[] getYValues() {
		return yValues;
	}
	
	
	
	public static void main(String [] args) {
		GUI gui = new GUI();
		gui.fillXValues();
		Graph graph = new Graph();
		System.out.println(20D/step);
	}
}
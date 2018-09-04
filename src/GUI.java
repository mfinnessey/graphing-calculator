import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
public class GUI {
	// M TODO a class to model a graphing calculator
	int indexTracker = 0;
	double [] xValues = new double [11];
	double [] yValues = new double [11];
	public GUI() {
		
		final JTextField equation = new JTextField("Enter an equation.");
		JButton calculate = new JButton("Calculate");
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		MathEvaluator m = new MathEvaluator("x^2");		
	   	m.addVariable("x", ((2.0)));
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Equation: " + equation.getText());
				m.setExpression(equation.getText());
				for(int i = -5; i <= 5; i++) {
					m.addVariable("x", (i));
					System.out.println(m.getValue());
					xValues[indexTracker] = i;
					yValues[indexTracker++] = m.getValue();
				}
				
			}
	   	});
	}
	public int [] getXValues() {
		int [] integerXValues = new int[xValues.length];
		for(int i = 0; i < xValues.length; i++) {
			integerXValues[i] = (int) xValues[i];
		}
		return integerXValues;
	}
	
	public int [] getYValues() {
		int [] integerYValues = new int[yValues.length];
		for(int i = 0; i < yValues.length; i++) {
			integerYValues[i] = (int) yValues[i];
		}
		return integerYValues;
	}
}
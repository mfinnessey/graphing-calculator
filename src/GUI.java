import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyListener;

public class GUI {
	// TODO a class to model a graphing calculator
	public GUI() {
		KeyListener key = null;
		JFrame frame = new JFrame();
		final JTextField equation = new JTextField("Enter an equation.");
		equation.addKeyListener(key);
		JButton calculate = new JButton("Calculate");
		calculate.setMnemonic(KeyEvent.VK_ENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		MathEvaluator m = new MathEvaluator("x^2");		
	   	m.addVariable("x", ((2.0)));
	   	System.out.println(m.getValue());
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Equation: " + equation.getText());
				m.setExpression(equation.getText());
				System.out.println("Equation value = " + m.getValue());
			}
	   	});
	}
	
	public static void main(String [] args) {
		GUI gui = new GUI();
	}
}
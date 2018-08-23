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

public class Calculator implements ActionListener{
	// TODO a class to model a graphing calculator
	public Calculator() {
		KeyListener key = null;
		JFrame frame = new JFrame();
		JTextField equation = new JTextField("Enter an equation.");
		equation.addKeyListener(key);
		JButton calculate = new JButton("Calculate");
		calculate.setMnemonic(KeyEvent.VK_ENTER);
		calculate.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calculator = new Calculator();
		
	   	MathEvaluator m = new MathEvaluator("x^2");		
	   	m.addVariable("x", ((2.0)));
	   	System.out.println(m.getValue());
	   	//double value_test = m.getValue();
	   	//System.out.println("is equation null?   " + value_test);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Click!");
	}
}
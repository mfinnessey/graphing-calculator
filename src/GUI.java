import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUI {
	// TODO a class to model a graphing calculator
	public GUI() {
		
		JFrame frame = new JFrame();
		final JTextField equation = new JTextField("Enter an equation.");
		JButton calculate = new JButton("Calculate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		MathEvaluator m = new MathEvaluator("x^2");		
	   	m.addVariable("x", ((2.0)));
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
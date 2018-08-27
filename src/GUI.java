import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI {
	// TODO a class to model a graphing calculator
	int indexTracker = 0;
	double [] yValues = new double [11];
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
				System.out.println("Equation: " + equation.getText());
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
	
	public static void main(String [] args) {
		GUI gui = new GUI();
	}
}
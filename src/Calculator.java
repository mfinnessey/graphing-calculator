import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import javax.swing.JFrame;

public class Calculator {
// TODO a class to model a graphing calculator.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		Label yellowLabel = new Label("Graphing Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		}
}
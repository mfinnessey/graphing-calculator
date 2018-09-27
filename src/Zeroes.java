import java.math.BigDecimal;
import main.java.com.udojava.evalex.Expression;

public class Zeroes {
//TODO: create a class to find zeros and other values using IVT
	/* K: Current idea is to use a for loop and increase i by 0.001 and use IVT to chekc if there's a 0
	 * K: Problem: if a function touches the x axis but doesn't cross
	 * K: No solution yet
	 */
	
	public static void main(String[] args) {
		double[] zeros = zeroFinder("(5*(x^3))-(9*(x^2))-(8*(x+5))");
		if (zeros.length == 0) {
			System.out.println("no zeros");
		}
		for (int i = 0; i < zeros.length; i++) {
			System.out.println(zeros[i]);
		}
	}
	
	public static int splitter(String s) {
		// M Splitting the polynomial expression at every term
		// M Let's hope we won't have to simplify anything. We should check in on this.
		// M Also we need to find a way to store the + or - as split doesn't retain it.
		String[] terms = s.split("\\+|\\-");
		int splitIndex = 0;
		for(int i = 0; i < terms.length; i++) {
			terms[i].trim();
			for(int j = 0; j < terms[i].length(); j++) {
				if (terms[i].charAt(j) == 'x'){
					splitIndex = j;
				}
			}
		}
		return splitIndex;
	}
	
	public static double plugIn(String s, double xval) {
		double rV = 0;
		BigDecimal result = null;
		Expression expression = new Expression(s);
		String xvalue = Double.toString(xval);
		expression.with("x", xvalue);
		result = expression.eval();
		return rV;
	}
	
	public static double[] zeroFinder(String s) {
		int zeros = 0;
		for (int i = -10; i < 10; i+=1) {
			double firstValue = plugIn(s, i);
			double secondValue = plugIn(s, i+1);
			if (firstValue < 0 && (secondValue) > 0) {
				zeros++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				zeros++;
			}else if (firstValue == 0) {
				zeros++;
			}
		}
		double[] rV = new double[zeros];
		int index = 0;
		for (int i = -10; i <= 10; i+=1) {
			double firstValue = plugIn(s, i);
			double secondValue = plugIn(s, i + 1);
			if ((firstValue < 0) && (secondValue > 0)) {
				rV[index] = i;
				index++;
			}else if ((firstValue > 0) && (secondValue < 0)) {
				rV[index] = i;
				index++;
			}else if (firstValue == 0) {
				rV[index] = i;
				index++;
			}
		}
		return rV;
	}
}
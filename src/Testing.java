import java.math.BigDecimal;

import main.java.com.udojava.evalex.Expression;

public class Testing {
	public static void main(String [] args) {
		BigDecimal result = null;
		Expression expression = new Expression("(5 * (x^2)) + (4 * x)");
		expression.with("x", "2");
		result = expression.eval();
		System.out.println(result);
	}
}

public class MathEvaluatorTest {
//K: I made a class to troubleshoot MathEvaluator cuz it's broken
	public static void main(String[] args) {
		String test1 = "2x^2 - 1";
		double value = Zeroes.plugIn(test1, 5);
		System.out.println(value);
		//K: problem found: MathEvaluator doesn't know order of operations
		//K: seems like it doesn't know how to do parenthesis either
	}
}

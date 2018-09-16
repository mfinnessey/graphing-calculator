
public class Zeroes {
//TODO: create a class to find zeros and other values using IVT
	/* K: Current idea is to use a for looop and increase i by 0.001 and use IVT to chekc if there's a 0
	 * K: Problem: if a function touches the x axis but doesn't cross
	 * K: No solution yet
	 */
	MathEvaluator ME = new MathEvaluator();
	
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
		return rV;
	}
}
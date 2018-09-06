public class Derivative {
	
	public static void main(String [] args) {
		System.out.println(derivativeFinder("3x^2 + 2x - 6"));
	}
	
	public static String derivativeFinder(String s) {
		//TODO A method to determine which method should be used to find the derivative.
		//M This currently doesn't implement the chain rule. We'll need to add this.
		if(s.contains("sin")) {
			return(trigDerivative(s));
		}
		else if(s.contains("cos")) {
			return(trigDerivative(s));
		}
		else if(s.contains("tan")) {
			return(trigDerivative(s));
		}
		//M Trying regex. IDK if this works 100% properly yet, it may catch polynomials.
		else if(s.matches("\\^\\d+x")) {
			return(expDerivative(s));
		}
		
		else if(s.contains("log")) {
			return(logDerivative(s));
		}
		else if(s.contains("ln")) {
			return(logDerivative(s));
		}
		else {
			return(polyDerivative(s));
		}
	}
	
	
	private static String polyDerivative(String s) {
		//TODO A method to find the derivative of a polynomial function.
		//M Splitting the polynomial expression at every term (i.e. every + or -).
		//M Let's hope we don't have to simplify anything. We should check in on this.
		//M Also we need to find a way to store the + or - as split doesn't retain it.
		String [] terms = s.split("\\+|\\-");
		//M Strings to store the constant and exponent for the power rule.
		String constant = "";
		String exponent = "";
		//M Doubles to handle the actual math of the power rule.
		double exponentDouble;
		double constantDouble;
		//M Storing the index where x^ starts so that the string can be split.
		int splitIndex = 0;
		// M Taking the derivative piece by piece.
		for(int i = 0; i < terms.length; i++) {
			terms[i].trim();
			for(int j = 0; j < terms[i].length(); j++) {
				if(terms[i].charAt(j) == 'x') {
					splitIndex = j;
				}
			}
			//M If the term isn't a constant, do the power rule.
			if(splitIndex != 0) {
				//M Splitting the term into the constant and exponent parts.
				constant = terms[i].substring(0, splitIndex);
				exponent = terms[i].substring((splitIndex + 2));
				//M Making the parts into doubles so that actual math can be done.
				System.out.println("Constant: " + constant);
				System.out.println("Exponent: " + exponent);
				exponentDouble = Double.parseDouble(exponent);
				constantDouble = Double.parseDouble(constant);
				//M We'll need to check that the precision on this works out.
				//M The constant of the derivative is its original value times the exponent.
				constantDouble *= exponentDouble;
				//M These lines could be combined, but I'm doing this for clarity.
				//M Decreasing the exponent by one.
				exponentDouble--;
				//M Turning the newly calculated values back into strings.
				constant = String.valueOf(constantDouble);
				exponent = String.valueOf(exponentDouble);
				//M Setting the term to the combined newly calculated term.
				terms[i] = (constant + "x^" + exponent);
				System.out.println("Term: " + terms[i]);
			}
			// If the term is a constant then, its derivative is 0.
			else {
				terms[i] = "0";
			}
			//Compensating for constants.
			splitIndex = 0;
		}
			
		//M Reassembling the expression from the substrings.
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < terms.length; i++) {
			buffer.append(terms[i]);
		}
		//M Changing buffer into a String for the return type.
		String derivative = new String(buffer);
		return(derivative);
	}
	
	private static String trigDerivative(String s) {
		//TODO A method to find the derivative of a trigonometric function.
		return("trigMemes");
	}
	
	private static String logDerivative(String s) {
		//TODO A method to find the derivative of a logarithmic function.
		return ("logMemes");
	}
	
	private static String expDerivative(String s) {
		//TODO A method to find the derivative of an exponential function.
		return("expMemes");
	}
}
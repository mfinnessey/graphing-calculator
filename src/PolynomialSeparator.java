public class PolynomialSeparator {
	public static String polyDerivative(String s) {
		//TODO A method to split a PolynomialFunciton
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
		//M Iterating through the terms.
		for(int i = 0; i < terms.length; i++) {
			//M Removing the whitespace from each term
			terms[i].trim();
			//M Iterating through each term.
			for(int j = 0; j < terms[i].length(); j++) {
				//Splitting each term at x.
				if(terms[i].charAt(j) == 'x') {
					splitIndex = j;
					//M If the term isn't a constant, then do this
					if(splitIndex != -1) {
						//M Splitting the term into the constant and exponent parts.
						constant = terms[i].substring(0, splitIndex);
						exponent = terms[i].substring((splitIndex + 2));
						System.out.println("Constant: " + constant);
						//M Making the parts into doubles so that actual math can be done.
						System.out.println("Exponent: " + exponent);
						exponentDouble = Double.parseDouble(exponent);
						constantDouble = Double.parseDouble(constant);
						//M Setting the term to the combined newly calculated term.
						
						/* if (exponentDouble != 1 && exponentDouble != 0) {
							terms[i] = (constant + "x^" + exponent);
						}else if (exponentDouble == 1) {
							terms[i] = (constant + "x");
						}else{
							terms[i] = (constant);
						}
						System.out.println("Term: " + terms[i]); */
					} 
						
					//Compensating for constants.
					splitIndex = -1;
				}
			}
		}
		return("Meme");
	}
}
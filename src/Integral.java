public class Integral {
	public String trapezoidalIntegral(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
		//M Variable to store the value of the integral.
		double integralValue = 0.0;
		//M Constant to store the step value.
		final double STEP_VALUE = 0.0001;
		//M Variables to store the beginning and ending indices where the limits of integration are.
		int beginIndex = -1;
		int endIndex = -1;
		//M Variable to facilitate the swapping of the upper and lower limits indices.
		int temp = -1;
		//M Boolean to store whether the limits of integration are inverted (i.e. b < a).
		boolean inverted = false;
		//M Finding the beginning index.
		System.out.println("Upper Limit: " + upperLimit);
		for(int j = 0; j < xValues.length; j++) {
			//M Checking if the x value is the correct one.
			if(xValues[j] == lowerLimit) {
				//M Storing the ending index.
				beginIndex = j;
				//M Breaking out of the for loop.
				break;
			}
		}
		//M Finding the ending index.
		for(int j = 0; j < xValues.length; j++) {
			//M Checking if the x value is the correct one.
			if(xValues[j] == upperLimit) {
				//M Storing the ending index.
				endIndex = j;
				//M Breaking out of the for loop.
				break;
			}
		}
		//M Handling for when the limits of integration are inverted.
		if(beginIndex > endIndex) {
			//M Marking that the limits were reversed.
			inverted = true;
			//M Swapping the values of beginIndex and endIndex with the help of temp.
			temp = beginIndex;
			beginIndex = endIndex;
			endIndex = temp;
		}
		//M Checking if the function is continuous between the limits of integration.
		for(int k = beginIndex; k <= endIndex; k++) {
			//M Discontinuities are represented as not a number or infinite by Java.
			if(Double.isNaN(yValues[k]) || Double.isInfinite(yValues[k])) {
				//M Returning to end program execution with an appropriate error message.
				return "Not possible";
			}
		}
		//M Iterating through the y values to add up the area under the curve via trapezoidal sums.
		for(int l = beginIndex; l <= endIndex; l++) {
			//Adding the value of each trapezoidal sum to the integral's value.
			integralValue += (STEP_VALUE * (yValues[l]+yValues[l+1]) / 2);
		}
		//K Converting integralValue into a string
		String s = String.valueOf(integralValue);
		//K Rounding integralValue down to 3 decimal places
		for (int j = 0; j < s.length(); j++) {
			if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
				s = s.substring(0,j+5);
			}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
				s = "0.0";
			}
		}
		//M Handling for if the limits of integration were reversed.
		if(inverted == false) {
		//M If the limits of integration aren't reversed, then the calculated value is returned.
		return s;
		}
		else {
			//M If the limits of integration are inverted, then the opposite of the unreversed value is returned.
			s = "-" + s;
			return s;
		}
	}
}
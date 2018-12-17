public class KeyPoints {
//TODO: create a class to find zeros and other values using IVT
	//K: rV = returnValue
	/* K: Current idea is to use a for loop and increase i by 0.001 and use IVT to check if there's a 0
	 * K: Problem: if a function touches the x axis but doesn't cross
	 * K: No solution yet
	 */
	public static double[][] findZeros(double[] xValues, double[] yValues) {
		//K: Returns the zeros of the function entered
		int zeros = 0;
		//K: A counter to keep track of how many zeros there are
		for (int i = 1; i < xValues.length-1; i++) {
			//K: This first for loop scans every xValue of the function to see if there is a zero, then it will loop again to store all the zeros
			double firstValue = yValues[i];
			double secondValue = yValues[i+1];
			//K: If the first value has a different sign than the second value then the function has a zero at that point
			if (firstValue < 0 && (secondValue) > 0 && yValues[i] < 0.1 && yValues[i] > -0.1) {
				//K: Previously, the findZeros function was returning zeros that were not actually zeros
				//K: The y value sometimes returned in the thousands, which is why the last two parameters are there
				zeros++;
				//K: If the first value is less than 0 and the second value (at x+0.0001) is larger than zero there is a zero at the point
			}else if (firstValue > 0 && (secondValue) < 0 && yValues[i] < 0.1 && yValues[i] > -0.1) {
				zeros++;
				//K: If the first value is larger than 0 and the second value (at x+0.0001) is less than zero there is a zero at the point
			}else if (firstValue == 0) {
				zeros++;
				//K: Conversely, if the zero falls exactly on the first value, there is a zero there
			}
			//K: Uses IVT for every xValue to check if there is a 0 between f(x) and f(x + 0.001)
		}
		double[][] rV = new double[zeros][2];
		//K Creates an empty array with a space for all the zeros
		//K: The x values of the zeros are in the 0 index, the y values are in the y. For this, the y values should all be equal or close to 0
		int index = 0;
		//K: Index to keep track of the zeros already stored
		for (int i = 1; i < xValues.length-1; i++) {
			//K: Reiterates through to store the zeros
			double firstValue = yValues[i];
			double secondValue = yValues[i+1];
			if (firstValue < 0 && (secondValue) > 0 && yValues[i] < 0.1 && yValues[i] > -0.1) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				
				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 4 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				rV[index][1] = Double.parseDouble(s);
				//K: The index becomes the rounded value
				index++;
				//K: Onto the next index, if there is one
			}else if (firstValue > 0 && (secondValue) < 0 && yValues[i] < 0.1 && yValues[i] > -0.1) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				
				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 3 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}else if (firstValue == 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				
				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 3 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}
			//K: Stores the coordinate points for every zero value
		}
		return rV;
	}
	
	public static double[][] hole(double [] xValues, double [] yValues, String expression){
		//M Need to make procedural. Can be added later.
		if ((expression.indexOf("sqrt") > -1) || (expression.indexOf("log") > -1) || (expression.indexOf("ln") > -1) || (expression.indexOf("asin") > -1) || (expression.indexOf("acos") > -1) || (expression.indexOf("atan") > -1)) {
			//K: If the function contains any of these keywords, this method will not work so it just returns nothing
			double [][] rV = new double [0][2];
			return rV;
		}
		//K: If the function has a limited domain, then holes will return 0 holes
		else {
			int numberOfHoles = 0;
			//K: A counter to keep track of how many holes there are
			int indexTracker = 0;
			//K: A counter to keep track of which hole the method is evaluating
			for(int i = 0; i < xValues.length; i++) {
				if(Double.isNaN(yValues[i])) {
					numberOfHoles++;
				} //K: If there is no y value for the corresponding x value, there is a hole there
			}
			double [][] holes = new double [numberOfHoles][2];
			for(int j = 0; j < xValues.length; j++) {
				if(Double.isNaN(yValues[j])) {
					holes[indexTracker][0] = xValues[j];
					holes[indexTracker][1] = (yValues[j+1] + yValues[j-1])/2;
				} //K: Uses the limit towards the hole to find the y coordinate of the hole
			}
			return holes;
		}
	}
	public static double[][] max(double[] xValues, double[] yValues){
		//K: Finds the local maximums of the function. This method is analogous to findZeros
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		//K: Instead of finding the zeros to the function, maxes are found by finding the zeros of the derivative function
		int maxes = 0;
		//K: Analogous to the zeros variable in findZeros
		for (int i = 2; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i];
			double secondValue = derivativeYValues[i+1];
			if (firstValue > 0 && (secondValue) < 0 && firstValue < 0.1 && firstValue > -0.1) {
				//K: There is a local maximum when the derivative of the function goes from a positive value to a negative value 
				maxes++;
			}//K: We only want to find maxes in the method
		}
		double[][] rV = new double[maxes][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		//K: Exactly the same as the code in findZeros
		for (int i = 2; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i];
			double secondValue = derivativeYValues[i+1];
			if (firstValue > 0 && (secondValue) < 0 && firstValue < 0.1 && firstValue > -0.1) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];

				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 4 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}
		}
		return rV;
	}
	
	public static double[][] min(double[] xValues, double[] yValues){
		//K: Finds the local minimums of the function. This method is analogous to findZeros
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		//K: Instead of finding the zeros to the function, mins are found by finding the zeros of the derivative function
		int mins = 0;
		//K: Analogous to the zeros variable in findZeros
		for (int i = 2; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i];
			double secondValue = derivativeYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0 && firstValue < 0.1 && firstValue > -0.1) {
				//K: There is a local minimum when the derivative of the function goes from a negative value to a positive value 
				mins++;
			}
		}
		double[][] rV = new double[mins][2];
		//K Creates array with a space for all the zeros, exactly the same as findZeros
		int index = 0;
		for (int i = 2; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i];
			double secondValue = derivativeYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0 && firstValue < 0.1 && firstValue > -0.1) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];

				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 4 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}
		}
		return rV;
	}

	public static double[][] POI(double[] xValues, double[] yValues){
		//K: A method to find the points of inflection of the function
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		//K: Differentiates the function twice, as points are inflection are zeros of the second derivative function
		double[] inflectionYValues = Derivative.findDerivative(xValues, derivativeYValues);
		//K: When the second derivative function has a zero, then a POI will be returned
		int pois = 0;
		//K: Analogous to zeros variable in findZeros
		for (int i = 3; i < xValues.length-1; i++) {
			double firstValue = inflectionYValues[i];
			double secondValue = inflectionYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0 && firstValue < 0.1 && firstValue > -0.1) {
				//K: When the second derivative goes from negative to positive, there is a point of inflection
				pois++;
			}else if (firstValue > 0 && (secondValue) < 0 && firstValue < 0.1 && firstValue > -0.1) {
				//K: When the second derivative goes from positive to negative, there is a point of inflection
				pois++;
			}else if (firstValue == 0) {
				//K: When the second derivative equal zero there is a point of inflection
				pois++;
			}
		}
		double[][] rV = new double[pois][2];
		//K Creates array with a space for all the zeros, exactly the same as findZeros
		int index = 0;
		for (int i = 3; i < xValues.length-1; i++) {
			double firstValue = inflectionYValues[i];
			double secondValue = inflectionYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0 && firstValue < 0.1 && firstValue > -0.1) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];

				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 4 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}else if (firstValue > 0 && (secondValue) < 0 && firstValue < 0.1 && firstValue > -0.1) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];

				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 4 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}else if (firstValue == 0) {
				//M This might be where the issue is with rational functions. I'll do more work on this on Monday.
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];

				//K Converting the zero into a string
				String s = String.valueOf(rV[index][1]);
				//K Rounding down to 4 decimal places
				for (int j = 0; j < s.length(); j++) {
					if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
						s = s.substring(0,j+5);
					}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
						s = "0.0";
					}
				}
				
				rV[index][1] = Double.parseDouble(s);
				index++;
			}
		}
		return rV;
	}
	
	public static boolean lineCheck(double[] xValues, double[] yValues, String equation) {
		//K Checks if a function is linear
		//K IF the function is linear, then maxes/mins and pois won't be shown
		//K This is to prevent the calculator for putting points of inflection and critical points at every point if the function is a straight line
		//System.out.println("lineCheck running on: " + equation);
		double slope1 = (yValues[3] - yValues[2])/(xValues[3] - xValues[2]);
		double slope2;
		for(int i = 4; i <= xValues.length - 3; i++) {
			slope2 = (yValues[i] - yValues[i-1])/(xValues[i] - xValues[i-1]);
			if((slope2 != slope1) && (Math.abs(slope2 - slope1) > 0.1)) {
				//System.out.println("Slope 1: " + slope1 + " Slope 2: " + slope2);
				//System.out.println("I: " + i);
				//M lineCheck is returning false because it's only running on the first equation (the original) in some cases
				//M Oddly enough, it's only tracing points everywhere for the second derivative though which is off.
				return false;
			}
		}
		//System.out.println("Line Confirmed");
		return true;
	}
}
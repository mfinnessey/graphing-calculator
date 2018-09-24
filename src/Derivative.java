public class Derivative {
	public static double [] findDerivative(double [] xValues, double [] yValues) {
		//M A method to calculate the various derivative values from a set of points.
		double [] derivativeYValues = new double [yValues.length];
		//M Currently calculating the derivative on (-10,10). LMK if changes are needed.
		for(int i = 1; i < (yValues.length - 1); i++) {
			//M Applying the alternate limit definition.
			derivativeYValues[i] = (yValues[i] - yValues[i - 1]) / (xValues[i] - xValues[i - 1]);
		}
		//M Returning the derivativeYValues.
		return derivativeYValues;
	}
}

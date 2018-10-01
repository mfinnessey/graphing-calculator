
public class Integral {
	
	public double findDefiniteIntegral(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
		//M Need to implement handling for when the lower limit. is greater than the upper limit.
		//M Unsure of accuracy with trigonometric functions, check cos(x) for sure.
		//M Variable to store the value of the integral.
		double integralValue = 0.0;
		//M Variable to store the step value.
		final double STEP_VALUE = 0.001;
		//M Variables to store the beginning and ending indices where the limits of integration occur.
		int beginIndex = -1;
		int endIndex = -1;
		//M Finding the beginning and ending indices.
		for(int i = 0; i < xValues.length; i++) {
			if(xValues[i] == lowerLimit) {
				beginIndex = i;
				break;
			}
		}
		for(int j = 0; j < xValues.length; j++) {
			if(xValues[j] == upperLimit) {
				endIndex = j;
				break;
			}
		}
		for(int k = beginIndex; k <= endIndex; k++) {
			integralValue += (STEP_VALUE * yValues[k]);
		}
		return integralValue;
	}
}

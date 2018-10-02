public class Integral {
	public double findDefiniteIntegral(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
		//M Variable to store the value of the integral.
		double integralValue = 0.0;
		//M Variable to store the step value.
		final double STEP_VALUE = 0.001;
		//M Variables to store the beginning and ending indices where the limits of integration occur.
		int beginIndex = -1;
		int endIndex = -1;
		int temp = -1;
		boolean inverted = false;
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
		//M Handling for when the limits of integration are inverted.
		if(beginIndex > endIndex) {
			inverted = true;
			temp = beginIndex;
			beginIndex = endIndex;
			endIndex = temp;
		}
		for(int k = beginIndex; k <= endIndex; k++) {
			integralValue += (STEP_VALUE * yValues[k]);
		}
		
		if(inverted == false) {
		return integralValue;
		}
		else {
			//M If the limits of integration are inverted, then the opposite of the uninverted value is returned.
			return (-1 * integralValue);
		}
	}
}
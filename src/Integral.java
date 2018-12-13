public class Integral {
	public String findDefiniteIntegral(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
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
			if(Double.isNaN(yValues[k]) || Double.isInfinite(yValues[k])) {
				return "Not possible";
			}
		}
		for(int l = beginIndex; l <= endIndex; l++) {
			integralValue += (STEP_VALUE * yValues[l]);
		}
		
		if(inverted == false) {
		return String.valueOf(integralValue);
		}
		else {
			//M If the limits of integration are inverted, then the opposite of the uninverted value is returned.
			return String.valueOf(-1 * integralValue);
		}
	}
	
	public String trapezoidalIntegral(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
		//M Variable to store the value of the integral.
				double integralValue = 0.0;
				//M Variable to store the step value.
				final double STEP_VALUE = 0.0001;
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
					if(Double.isNaN(yValues[k]) || Double.isInfinite(yValues[k])) {
						return "Not possible";
					}
				}
				for(int l = beginIndex; l < endIndex; l++) {
					//this is the only line of code I changed
					integralValue += (STEP_VALUE * (yValues[l]+yValues[l+1]) / 2);
				}
				
				
				
				if(inverted == false) {
				return String.valueOf(integralValue);
				}
				else {
					//M If the limits of integration are inverted, then the opposite of the uninverted value is returned.
					return String.valueOf(-1 * integralValue);
				}
	}
	
	public String FTC(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
		//M This is high-key broken right now. 
		//M We should check in with Mr. Duran when he comes in next, but for now I"m going to assume
		//M that the constant of integration should be 0.
		double upperValue = 0;
		double lowerValue = 0;
		for(int i = 0; i <= xValues.length - 1; i++) {
			if(xValues[i] == lowerLimit) {
				lowerValue = yValues[i];
			}
		}
		for(int j = 0; j <= xValues.length - 1; j++) {
			if(xValues[j] == upperLimit) {
				upperValue = yValues[j];
			}
		}
		return Double.toString(upperValue-lowerValue);
	}
}
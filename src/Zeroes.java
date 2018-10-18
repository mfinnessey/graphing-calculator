public class Zeroes {
//TODO: create a class to find zeros and other values using IVT
	/* K: Current idea is to use a for loop and increase i by 0.001 and use IVT to chekc if there's a 0
	 * K: Problem: if a function touches the x axis but doesn't cross
	 * K: No solution yet
	 */
	public static double[][] findZeros(double[] xValues, double[] yValues) {
		//K returns zeros of the function
		//K: Problem: if a function touches the x axis but doesn't cross
		//K: Problem2: function has vertical asymptote
		int zeros = 0;
		for (int i = 0; i < xValues.length-1; i++) {
			double firstValue = yValues[i];
			double secondValue = yValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				zeros++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				zeros++;
			}else if (firstValue == 0) {
				zeros++;
			}
		}
		double[][] rV = new double[zeros][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 0; i < xValues.length-1; i++) {
			double firstValue = yValues[i];
			double secondValue = yValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}else if (firstValue == 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}
		}
		return rV;
	}
	
	public static double[][] hole(double [] xValues, double [] yValues){
		//M Need to make procedural. Can be added later.
		int numberOfHoles = 0;
		int indexTracker = 0;
		for(int i = 0; i < xValues.length; i++) {
			if(Double.isNaN(yValues[i])) {
				numberOfHoles++;
			}
		}
		double [][] holes = new double [numberOfHoles][2];
		for(int j = 0; j < xValues.length; j++) {
			if(Double.isNaN(yValues[j])) {
				holes[indexTracker][0] = xValues[j];
				holes[indexTracker][1] = (yValues[j+1] + yValues[j-1])/2;
			}
		}
		return holes;
	}
	public static double[][] max(double[] xValues, double[] yValues){
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		int maxes = 0;
		for (int i = 1; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i-1];
			double secondValue = derivativeYValues[i+1];
			if (firstValue > 0 && (secondValue) < 0) {
				maxes++;
			}
		}
		double[][] rV = new double[maxes][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 1; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i-1];
			double secondValue = derivativeYValues[i+1];
			if (firstValue > 0 && (secondValue) < 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}
		}
		return rV;
	}
	
	public static double[][] min(double[] xValues, double[] yValues){
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		int mins = 0;
		for (int i = 1; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i-1];
			double secondValue = derivativeYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				mins++;
			}
		}
		double[][] rV = new double[mins][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 1; i < xValues.length-1; i++) {
			double firstValue = derivativeYValues[i-1];
			double secondValue = derivativeYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}
		}
		return rV;
	}

	public static double[][] POI(double[] xValues, double[] yValues){
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		double[] inflectionYValues = Derivative.findDerivative(xValues, derivativeYValues);
		int pois = 0;
		for (int i = 1; i < xValues.length-2; i++) {
			double firstValue = inflectionYValues[i];
			double secondValue = inflectionYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				pois++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				pois++;
			}else if (firstValue == 0) {
				pois++;
			}
		}
		double[][] rV = new double[pois][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 1; i < xValues.length-2; i++) {
			double firstValue = inflectionYValues[i];
			double secondValue = inflectionYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}else if (firstValue == 0) {
				//M This might be where the issue is with rational functions. I'll do more work on this on Monday.
				rV[index][0] = xValues[i];
				rV[index][1] = yValues[i];
				index++;
			}
		}
		return rV;
	}
	
	public static boolean lineCheck(double[] xValues, double[] yValues) {
		//K Checks if a function is linear
		//K IF the function is linear, then maxes/mins and pois won't be shown
		double slope1 = (yValues[3] - yValues[2])/(xValues[3] - xValues[2]);
		double slope2;
		for(int i = 4; i <= xValues.length - 3; i++) {
			slope2 = (yValues[i] - yValues[i-1])/(xValues[i] - xValues[i-1]);
			if((slope2 != slope1) && (Math.abs(slope2 - slope1) > 0.001)) {
				return false;
			}
		}
		return true;
	}
}
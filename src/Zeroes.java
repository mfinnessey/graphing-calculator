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

	public static double[] POI(double[] xValues, double[] yValues){
		double[] derivativeYValues = Derivative.findDerivative(xValues, yValues);
		double[] inflectionYValues = Derivative.findDerivative(xValues, derivativeYValues);
		int pois = 0;
		for (int i = 0; i < xValues.length-1; i++) {
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
		double[] rV = new double[pois];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 0; i < xValues.length-1; i++) {
			double firstValue = inflectionYValues[i];
			double secondValue = inflectionYValues[i+1];
			if (firstValue < 0 && (secondValue) > 0) {
				rV[index] = xValues[i];
				index++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				rV[index] = xValues[i];
				index++;
			}else if (firstValue == 0) {
				rV[index] = xValues[i];
				index++;
			}
		}
		return rV;
	}
}
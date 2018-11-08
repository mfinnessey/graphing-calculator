import java.util.List;

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
	
	public static double[][] hole(List<Double> xValues, List<Double >yValues, String expression){
		//M Need to make procedural. Can be added later.
		if ((expression.indexOf("sqrt") > -1) || (expression.indexOf("log") > -1) || (expression.indexOf("ln") > -1) || (expression.indexOf("asin") > -1) || (expression.indexOf("acos") > -1) || (expression.indexOf("atan") > -1)) {
			double [][] rV = new double [0][2];
			return rV;
		}
		else {
			int numberOfHoles = 0;
			int indexTracker = 0;
			for(int i = 0; i < xValues.size(); i++) {
				if(Double.isNaN(yValues.get(i))) {
					numberOfHoles++;
				}
			}
			double [][] holes = new double [numberOfHoles][2];
			for(int j = 0; j < xValues.size(); j++) {
				if(Double.isNaN(yValues.get(j))) {
					holes[indexTracker][0] = xValues.get(j);
					holes[indexTracker][1] = (yValues.get(j+1) + yValues.get(j-1))/2;
				}
			}
			return holes;
		}
	}
	public static double[][] max(List<Double> xValues, List<Double> yValues){
		List<Double> derivativeYValues = Derivative.findDerivative(xValues, yValues);
		int maxes = 0;
		for (int i = 1; i < xValues.size()-1; i++) {
			double firstValue = derivativeYValues.get(i-1);
			double secondValue = derivativeYValues.get(i+1);
			if (firstValue > 0 && (secondValue) < 0) {
				maxes++;
			}
		}
		double[][] rV = new double[maxes][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 1; i < xValues.size()-1; i++) {
			double firstValue = derivativeYValues.get(i-1);
			double secondValue = derivativeYValues.get(i+1);
			if (firstValue > 0 && (secondValue) < 0) {
				rV[index][0] = xValues.get(i);
				rV[index][1] = yValues.get(i);
				index++;
			}
		}
		return rV;
	}
	
	public static double[][] min(List<Double> xValues, List<Double> yValues){
		List<Double> derivativeYValues = Derivative.findDerivative(xValues, yValues);
		int mins = 0;
		for (int i = 1; i < xValues.size()-1; i++) {
			double firstValue = derivativeYValues.get(i-1);
			double secondValue = derivativeYValues.get(i+1);
			if (firstValue < 0 && (secondValue) > 0) {
				mins++;
			}
		}
		double[][] rV = new double[mins][2];
		//K Creates array with a space for all the zeros
		int index = 0;
		for (int i = 1; i < xValues.size()-1; i++) {
			double firstValue = derivativeYValues.get(i-1);
			double secondValue = derivativeYValues.get(i+1);
			if (firstValue < 0 && (secondValue) > 0) {
				rV[index][0] = xValues.get(i);
				rV[index][1] = yValues.get(i);
				index++;
			}
		}
		return rV;
	}

	public static double[][] POI(List<Double> xValues, List<Double> yValues){
		List<Double> derivativeYValues = Derivative.findDerivative(xValues, yValues);
		List<Double> inflectionYValues = Derivative.findDerivative(xValues, derivativeYValues);
		int pois = 0;
		for (int i = 1; i < xValues.size()-2; i++) {
			double firstValue = inflectionYValues.get(i);
			double secondValue = inflectionYValues.get(i+1);
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
		for (int i = 1; i < xValues.size()-2; i++) {
			double firstValue = inflectionYValues.get(i);
			double secondValue = inflectionYValues.get(i+1);
			if (firstValue < 0 && (secondValue) > 0) {
				rV[index][0] = xValues.get(i);
				rV[index][1] = yValues.get(i);
				index++;
			}else if (firstValue > 0 && (secondValue) < 0) {
				rV[index][0] = xValues.get(i);
				rV[index][1] = yValues.get(i);
				index++;
			}else if (firstValue == 0) {
				//M This might be where the issue is with rational functions. I'll do more work on this on Monday.
				rV[index][0] = xValues.get(i);
				rV[index][1] = yValues.get(i);
				index++;
			}
		}
		return rV;
	}
	
	public static boolean lineCheck(List<Double> xValues, List<Double> yValues) {
		//K Checks if a function is linear
		//K IF the function is linear, then maxes/mins and pois won't be shown
		double slope1 = (yValues.get(3) - yValues.get(2))/(xValues.get(3) - xValues.get(2));
		double slope2;
		for(int i = 4; i <= xValues.size() - 3; i++) {
			slope2 = (yValues.get(i) - yValues.get(i-1))/(xValues.get(i) - xValues.get(i-1));
			if((slope2 != slope1) && (Math.abs(slope2 - slope1) > 0.001)) {
				return false;
			}
		}
		return true;
	}
}
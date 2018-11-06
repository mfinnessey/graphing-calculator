import java.util.ArrayList;
import java.util.List;

public class Derivative {
	public static List<Double> findDerivative(List<Double> xValues, List<Double> yValues) {
		//M A method to calculate the various derivative values from a set of points.
		List<Double> derivativeYValues = new ArrayList<Double>();
		//M Currently calculating the derivative on (-10,10). LMK if changes are needed.
		for(int i = 1; i < (yValues.size() - 1); i++) {
			//M Applying the alternate limit definition.
			derivativeYValues.set(i, ((yValues.get(i) - yValues.get(i-1)) / (xValues.get(i) - xValues.get(i-1))));
		}
		//M Returning the derivativeYValues.
		return derivativeYValues;
	}
}

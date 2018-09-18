import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.DoubleStream;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double step = Math.pow(10, -7);
		int indexTracker = 0;
		// final BigDecimal bd = new BigDecimal(Math.pow(10, -8));
		double [] xValues = new double[200000001];
		for(int i = (int) (-1 * Math.pow(10,8)); i <= (int) Math.pow(10, 8); i++) {
			xValues[indexTracker++] = (double) (i * step);
		}
		for(int j = 0; j < indexTracker; j++) {
			xValues[j] = round(xValues[j]);
			System.out.println(xValues[j]);
		}
		
		
		
		// final double step = Math.pow(10, -8);
		//M Currently issues with precision with this.
		/* DoubleStream stream = DoubleStream.iterate(-10, d -> d + step);
		double[] rawArray = stream.limit((long) (20/step)).toArray();
		//M index at which the filteredArray should be filled.
		int fillIndex = 0;
		double [] filteredArray = new double[rawArray.length];
		for(int i = 0; i < rawArray.length; i++) {
			System.out.println(rawArray[i]);
		} /*
		//M The runtime for this is currently stupid with step = 10^-8
		/* for(int i = 0; i < rawArray.length; i++) {
			for(int j = 0; j < filteredArray.length; j++) {
				if(rawArray[i] == filteredArray[j]) {
					break;
				}
			}
			filteredArray[fillIndex++] = rawArray[i];
		}
		for(int i = 0; i < filteredArray.length; i++) {
			System.out.println(filteredArray[i]);
		} */
	}
	public static double round(double d) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(7, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
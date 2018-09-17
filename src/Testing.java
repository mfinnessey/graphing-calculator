import java.util.stream.DoubleStream;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double step = Math.pow(10, -7);
		//M Currently issues with precision with this.
		DoubleStream stream = DoubleStream.iterate(-10, d->d + step);
		double[] rawArray = stream.limit((long) (20/step)).toArray();
		//M index at which the filteredArray should be filled.
		int fillIndex = 0;
		double [] filteredArray = new double[rawArray.length];
		for(int i = 0; i < rawArray.length; i++) {
			System.out.println(rawArray[i]);
		}
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
}
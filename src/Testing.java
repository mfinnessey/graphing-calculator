import java.util.stream.DoubleStream;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double step = Math.pow(10, -7);
		//M Currently issues with precision with this.
		DoubleStream stream = DoubleStream.iterate(-10, d->d+=step);
		double[] array = stream.limit((long) (20/step)).toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
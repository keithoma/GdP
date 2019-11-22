
public class EuclideanAlgorithm {

	public static int euclidean_algorithm_iterative(int a, int b) {
		while (b != 0) {
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public static void main(String[] args) {
		if (args.length >= 2) {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);

			int gcd = euclidean_algorithm_iterative(a, b);
			System.out.printf("The gcd of %d and %d is %d.\n", a, b, gcd);
		} else if (args.length == 0) {
			System.out.println("No arguments were given. We will demonstrate the euclidean algorithm for a = 1071 and b = 462.");
			int a = 1071;
			int b = 462;

			int gcd = euclidean_algorithm_iterative(a, b);
			System.out.printf("The gcd of %d and %d is %d.\n", a, b, gcd);
		} else {
			System.out.println("Only one arguments were given. Please input two integers.");
		}



	}

}

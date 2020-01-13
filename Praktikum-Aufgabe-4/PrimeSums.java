public class PrimeSums
{
	public static void main(String[] args)
	{
		if (args.length > 1)
			System.err.println("Mind the CLI.");
		else
		{
			if (args.length == 2 & args[0] == "-sums")
			{
				int N = Integer.parseInt(args[1]);
				int[][] all_sums = decompose_all(N);
				for (int i = 0; i < all_sums.length; i++)
					System.out.printf("%s\n", toString(all_sums[i]));
			}
			else
			{
				int N = 10;
				if (args.length == 1)
					N = Integer.parseInt(args[0]);
				for (int n = 4; n <= N; ++n)
					; // System.out.printf("%d: %s\n", n, toString(decompose(n)));
			}
		}
	}

	// Primzahlsummenzerlegung
	static int[][] decompose_all(int n)
	{
		return decompose_all(n, 2, 1);
	}

	static int[][] decompose_all(int n, int smallestPrime, int callDepth)
	{
		if (n == smallestPrime) {
			int[][] res = new int[1][];
			res[0] = new int[1];
			res[0][0] = n;
			return res;
		}

		int[][] results = null; // TODO: empty array, stdlib missing, wtf !
		int p = smallestPrime;
		while (p <= n) {
			int[][] a = PrimeSums.decompose_all(n - p, p, callDepth + 1);
			for (int k = 0; k < a.length; ++k) {
				append(a[k], p);
				sort(a[k]);
				if (!contains(results, a[k]))
					append(results, a[k]);
			}
			p = next_prime(p);
		}

		if (PrimeSums.prime(n) && callDepth != 1) {
			int[] n_ = {n};
			append(results, n_);
		}
		sort(results);
		return results;
	}

	static boolean contains(int[][] a, int[] b)
	{
		// TODO: test if b is in a
		return false;
	}

	static void sort(int[] array) {
		// TODO: sort in-place
	}

	static void sort(int[][] array) {
		// TODO: sort in-place
	}

	static void append(int[] a, int b) {
		// TODO (standard lib? ;-( )
	}

	static void append(int[][] a, int[] b) {
		// TODO (standard lib? ;-( )
	}

	static String toString(int[] _S)
	{
		String sval = Integer.toString(_S[0]);
		for (int i = 1; i < _S.length; ++i)
			sval = sval + "+" + Integer.toString(_S[i]);
		return sval;
	}

	static boolean prime(int n) // TODO: Sieve me
	{
		if (n <= 3)
			return n >= 2;
		else if (n % 2 == 0 || n % 3 == 0)
			return false;
		else
		{
			for (int i = 5; i * i <= n; i = i + 6)
				if (n % i == 0 || n % (i + 2) == 0)
					return false;
			return true;
		}
	}

	static int next_prime(int p)
	{
		p++;
		while (!PrimeSums.prime(p)) // TODO: Sieve me
			p++;
		return p;
	}
}

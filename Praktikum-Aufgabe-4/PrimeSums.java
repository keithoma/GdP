public class PrimeSums
{
	public static void main(String[] args)
	{
		int N = 10;
		if (args.length > 1)
			System.err.println("Mind the CLI.");
		else
		{
			if (args.length == 1)
				N = Integer.parseInt(args[0]);
			for (int n = 4; n <= N; ++n)
			System.out.printf("%d: %s\n", n, toString(decompose(n)));
		}
	}

	// Primzahlsummenzerlegung
	static int[][] decompose_all(int n)
	{
		return decompose_all(n, 2, 1);
	}

	static int[][] decompose_all(int n, int smallestPrime, int callDepth)
	{
	}

	// Merges two arrays of integers.
	static int[] merge(int[] s1, int[] s2)
	{
		int[] sm = new int[s1.length + s2.length];
		int i = 0;
		for (; i < s1.length; i++)
			sm[i] = s1[i];
		for (int j = 0; j < s2.length; ++j)
			sm[i++] = s2[j];
		return sm;
	}

	static long numberOfSums(int N)
	{
		return numberOfSums(N, 2);
	}

	// ermittle die Anzahl der Primzahlsummen von n, bei der keine kleineren Summanden als min auftauchen
	static long numberOfSums(int N, int min)
	{
		return 1; // TODO
	}

	static String toString(int[] _S)
	{
		String sval = Integer.toString(_S[0]);
		for (int i = 1; i < _S.length; ++i)
			sval = sval + "+" + Integer.toString(_S[i]);
		return sval;
	}

	static boolean prime_sexy(int p)
	{
		// only works up to native int = 16 (poor Java :-D)
		int factorial_p_1 = 1;
		for (int n = 2; n <= p - 1; ++n)
			factorial_p_1 *= n;
		return (factorial_p_1 % p) == p - 1;
	}

	static boolean prime(int n)
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
}

class Nolindrome {
	public static long reverse(long N) {
		long reversed = 0;
		while (N != 0) {
			long digit = N % 10;
			reversed = reversed * 10 + digit;
			N /= 10;
		}
		return reversed;
	}
	
	public static long palindrome_algorithm(long N, int max_iteration, int counter) {
		long R = reverse(N);

		long new_number = N + R;
		counter++;

		if (N + R >= Long.MAX_VALUE) {
			//o System.out.println(String.format("%d : %d\n", N, 0));
			return 0;
		} else if (N == reverse(N)) {
			// System.out.println(String.format("%d : %d\n", N, N));
			return N;
		} else if (counter >= max_iteration) {
			// System.out.println(String.format("%d : %d\n", N, -1));
			return -1;
		} else {
			return palindrome_algorithm(new_number, max_iteration, counter);
		}
	}

	public static void seek_palindrome(int upto) {
		for (int i=0; i <= upto; i++) {
			if (palindrome_algorithm(i, 100, 0) == -1) {
				System.out.println(i);
			}
		}
	}

	public static void main(String args[]) {
		System.out.println(palindrome_algorithm(196, 100, 0));
		// seek_palindrome(300);
	}
}

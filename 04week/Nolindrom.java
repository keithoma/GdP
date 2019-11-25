/**
 * This module uses an algorithm to create palindromes.
 */
class Nolindrom {
	/**
	 * Simply reverses the digits of a given number and returns the reversed.
	 */
	public static long reverse(long N) {
		long reversed = 0;
		while (N != 0) {
			final long digit = N % 10;
			reversed = reversed * 10 + digit;
			N /= 10;
		}
		return reversed;
	}

	/**
	 * Small function which checks if the addition would be an overflow.
	 */
	public static boolean check_overflow_addition(long N, long R) {
        if (Long.MAX_VALUE - N >= R) {
            return false;
        } else {
            return true;
        }
	}
	
	/**
	 * The heart of this module. Uses a fairly simple algorithm to generate palindromes.
	 * Note that the return value is 0 if there is an overflow, -1 if the algorithm takes too long.
	 * If the algorithm finds a palindrome, it will return the palindrome.
	 */
	private static long palindrome_algorithm(final long N, final int max_iteration, int counter) {
		final long R = reverse(N); // Find the reverse of N.
		++counter; // Add a counter for every recursion.

		if (check_overflow_addition(N, R) == true) {
			return 0; // 0 will be interpreted as overflow later in the module.
		} else if (N + R == reverse(N + R)) {
			return N; // The input is already a palindrome.
		} else if (counter >= max_iteration) {
            return -1; // After 100th recursion, we still didn't find a palindrome, abort.
                       // This should effectively never happen.
		} else {
			return palindrome_algorithm(N + R, max_iteration, counter); // Recursively continue.
		}
	}

	public static void main(String args[]) {
        // Check if a parameter was passed.
        if(args.length == 0)
        {
            System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
            System.exit(0);
        }

        int upto = Integer.parseInt(args[0]);

        // For everything, there are boundaries.
        if (upto > 100000) {
            System.out.printf("%d is too large! We set the maximal iteration to 100000.", upto);
        }

        // Now look for palindromes.
		for (int i = 1; i <= upto; i++) {
			// Apply the algorithm for i, upto 100 recursion.
			final long result = palindrome_algorithm(i, 100, 0);

			// Interpret the result and print to console.
			if (result == 0) {
                System.out.println(i);
				// System.out.printf("%d (Overflow!)\n", i);
			} else if (result == -1) {
                System.out.println(i);
				// System.out.printf("%d (Maximum Iteration Reached!)\n", i);
			} else {
				// Commented out, but if one wants to be really sure.
				// System.out.printf("%d (Palindrome found: %d)\n", i, result);
			}
		}
	}
}

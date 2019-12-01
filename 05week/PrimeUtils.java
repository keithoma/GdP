class PrimeUtils {
    // tod the trim thing could be a function


    public static boolean isPrime(short n) {
        // check naively from 2 to sqrt(n) if it is divisible by a positive integer
        double sqrt = Math.sqrt(n);
        for (double i=2.0; i < sqrt; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int n) {
        // check naively from 2 to sqrt(n) if it is divisible by a positive integer
        double sqrt = Math.sqrt(n);
        for (double i=2.0; i < sqrt; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(long n) {
        // check naively from 2 to sqrt(n) if it is divisible by a positive integer
        double sqrt = Math.sqrt(n);
        for (double i=2.0; i < sqrt; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static int[] getFirstPrimes(int upto) {
        boolean number_line[] = new boolean[upto + 1];
        int primes[] = new int[upto + 1];

        for (int i = 0; i < upto; ++i) 
            number_line[i] = true; 
        
        int i = 0;
        for (int p = 2; p <= upto; ++p) 
        {
            if (number_line[p] == true)
            {
                primes[i] = p;
                ++i;
                for (int j = p * 2; j <= upto; j += p) 
                    number_line[j] = false; 
            } 
        }

        int primes_trimmed[] = new int[i];
        for (int j = 0; j < i; ++j) {
            if (primes[j] != 0) {
                primes_trimmed[j] = primes[j];
            }
        }

        return primes_trimmed;
    }

    public static int[] getPrimesBetween(int a, int b) {
        int[] full_prime_array = getFirstPrimes(b);
        int[] primes_between = new int[b + 1];
        int counter = 0;
        for (int i = 0; i < full_prime_array.length; ++i) {
            if (full_prime_array[i] > a && full_prime_array[i] < b) {
                primes_between[counter] = full_prime_array[i];
                ++counter;
            }
        }

        int primes_between_trimmed[] = new int[counter];
        for (int j = 0; j < counter; ++j) {
            if (primes_between[j] != 0) {
                primes_between_trimmed[j] = primes_between[j];
            }
        }

        return primes_between_trimmed;
    }

    public static boolean isContained(int value, int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static int[] getPrimesBetween(int a, int b, int[] array) {

        int[] full_prime_array = getFirstPrimes(b);
        int[] primes_between = new int[b + 1];
        int counter = 0;
        for (int i = 0; i < full_prime_array.length; ++i) {
            if (full_prime_array[i] > a && full_prime_array[i] < b) {
                if (isContained(full_prime_array[i], array)) {
                    primes_between[counter] = full_prime_array[i];
                    ++counter;
                }
            }
        }

        int primes_between_trimmed[] = new int[counter];
        for (int j = 0; j < counter; ++j) {
            if (primes_between[j] != 0) {
                primes_between_trimmed[j] = primes_between[j];
            }
        }

        return primes_between_trimmed;


    }

    // end point is not included
    public static int getRandomRange(int a, int b){
        return (int)(Math.random()*(b-a))+a;
    }

    public static int getRandomPrimeFrom(int[] primes) {
        if (primes.length == 0) {
            return -1;
        }
        int random_index = getRandomRange(0, primes.length);
        return primes[random_index];
    }

    public static void main(String args[]) {
        // System.out.println(isPrime(8));
        // int[] a = getFirstPrimes(100);
        // int[] b = {79, 90, 91, 92, 97};
        // int[] a = getPrimesBetween(71, 100, b);
        // for (int i = 0; i < 25; ++i) {
        //    System.out.println(a[i]);
        //}
        System.out.println(getRandomRange(1, 2));
    }
}
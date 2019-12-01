/**
 * This class makes same very basic tests of the PrimeUtils class.
 * Delete this code from your memory the moment you hear about interfaces!
 * 
 * @author RLieck
 *
 */
public class PrimeUtilsSimpleTest {

	/**
	 * This simple method tries to call functions from the PrimeUtils class.
	 * 
	 * @param args No arguments are expected
	 */
	public static void main(String[] args) {
		short small = 17;
		int normal = 1999993;
		long big = 99999999991L;
		System.out.println( "Is this a prime? " + PrimeUtils.isPrime( small ) );
		System.out.println( "Is this a prime? " + PrimeUtils.isPrime( normal ) );
		System.out.println( "Is this a prime? " + PrimeUtils.isPrime( big ) );
		
		int upperBound = 2000000;
		int[] firstPrimes = PrimeUtils.getFirstPrimes( upperBound );
		System.out.printf( "There are %d primes smaller than %d\n", firstPrimes.length, upperBound );
		
		int[] bigPrimes = PrimeUtils.getPrimesBetween( 10_000_000, 100_000_000 );
		System.out.println( "There are " + bigPrimes.length + " primes between 10mio and 100mio");
		
		int[] lessBigPrimes = PrimeUtils.getPrimesBetween( 20_000_000, 30_000_000, bigPrimes );
		System.out.println( "There are " + lessBigPrimes.length + " primes between 20mio and 30mio");
		
		int oneBigPrime = PrimeUtils.getRandomPrimeFrom( bigPrimes );
		int otherBigPrime = PrimeUtils.getRandomPrimeFrom( bigPrimes );
		System.out.printf( "Those should not be the same: %d == %d ?\n" , oneBigPrime, otherBigPrime );

	}

}

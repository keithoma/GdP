#! /usr/bin/env python3









import math
class PrimeSums:
    def __init__(self, _n):
        self.n = _n
        self.sums = PrimeSums.decompose(_n, 2)

    @staticmethod
    def is_prime(p):
        return math.factorial(p - 1) % p - p == -1

    # 4: 2+2
    # 5: 2+3
    # 6: 3+3; 2+2+2
    # 7: (2+2)+3
    # 8: (2+2)+(2+2); 3+3+2; 5+3
    # 9: 5+(2+2)
    @staticmethod
    def decompose(value, start = 2):
        """
        Decomposes a number into sum of primes.
        Parameters:
            value (int) : the value to decompose into sum of primes
            start (int) : minimum prime to chose from (default: 2)
        Returns:
            int[] : array of primes
        """
        if PrimeSums.is_prime(value):
            return [value]
        res = []
        n = value - start
        while value > 0:
            if PrimeSums.is_prime(n):
                res.append(n)
                value -= n
                n = value
            else:
                n -= 1
        return res

    def __str__(self):
        return "{}: {}".format(self.n, "+".join(str(i) for i in self.sums))

N = 20
for n in range(4, N + 1):
    ps = PrimeSums(n)
    print(ps)

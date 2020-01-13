#! /usr/bin/env python3

# XXX testing in python is quicker than in Java...

from math import factorial

class PrimeSums:
    @staticmethod
    def is_prime(p):
        return p >= 2 and factorial(p - 1) % p - p == -1 # TODO: maybe sieve me

    @staticmethod
    def next_prime(p):
        p += 1
        while PrimeSums.is_prime(p) is False: # TODO Sieve me
            p += 1
        return p

    # 4: 2+2
    # 5: 2+3
    # 6: 2+2+2, 3+3
    # 7: 2+2+3, 2+5
    # 8: 2+2+2+2, 2+3+3, 3+5
    # 9: 2+2+2+3, 2+2+5, 2+7, 3+3+3
    @staticmethod
    def decompose_all(n, smallestPrime = 2, callDepth = 1):
        if n == smallestPrime:
            return [[n]]
        results = []
        p = smallestPrime
        while p <= n:
            a = PrimeSums.decompose_all(n - p, p, callDepth + 1)
            for a_k in a:
                a_k.append(p)
                a_k.sort()
                if results.count(a_k) == 0:
                    results.append(a_k)
            p = PrimeSums.next_prime(p)

        if PrimeSums.is_prime(n) and callDepth != 1:
            print("add@{}: {}: {}".format(callDepth, n, results))
            results.append([n])

        results.sort()
        return results

def test_n(n):
    all_sums = PrimeSums.decompose_all(n)
    for sums in all_sums:
        print("{}: {}".format(n, sums))

test_n(8)

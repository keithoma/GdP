#! /usr/bin/env python3
import matplotlib.pyplot as plt

COUNTER = 0
class gdp():
    def __init__(self, n):
        self.counter = 0
        self.alg(n)

    def alg(self, n):
        self.counter += 1
        if n <= 0:
            return 0
        return self.alg(n // 2) + n

yaxis = []

for n in range(10000):
    obj = gdp(n)
    print("{}: {} steps".format(n, obj.counter))
    yaxis.append(obj.counter)

xaxis = [x for x in range(10000)]
plt.plot(xaxis, yaxis)
plt.show()

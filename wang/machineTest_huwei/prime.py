import math
def primeFactor(num):
    num = int(num)
    isPrime = 1
    for x in range(2,math.ceil(math.sqrt(num))+1):
        if num % x ==0:
            isPrime = 0
            print(str(x),end=" ")
            primeFactor(int(num / x))
            break
    if isPrime == 1:
        print(str(num),end=" ")


primeFactor(input())
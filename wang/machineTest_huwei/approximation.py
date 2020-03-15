
num=input()
print(num)
print(type(num))
z=num.split('.')
print(z)
if int(z[1])>=5:
    print(int(z[0])+1)
else:
    print(int(z[0]))
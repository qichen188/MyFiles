#求取近似值
num = input()
z = num.split('.')
if int(z[1])>=5:
    print(int(z[0])+1)
else:
    print(int(z[0]))
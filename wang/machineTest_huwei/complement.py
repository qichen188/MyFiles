for x in range(2):
    string = input()
    if len(string)<=8:
        print(string + '0'*(8-len(string)))
    else:
        while len(string)>8:
            print(string[:8])
            string = string[8:]
        print(string + '0'*(8-len(string)))



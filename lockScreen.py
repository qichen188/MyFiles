from ctypes import *

def lock_screen():
    user32 = windll.LoadLibrary('user32.dll')
    user32.LockWorkStation()
lock_screen()
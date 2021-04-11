'''

'''
import cv2
import time
import uuid
import psutil as psutil
from PIL import ImageFont,ImageDraw,Image
from matplotlib import pyplot as plt

# 定义函数 file_name，用于生成文件名称，出参为 文件名称。
def generated_file_name():
    fileName = str(int(time.time()))
    fileName = fileName + '.jpg'
    return fileName

# 定义函数 turn_on_camera_take_pictures，用于开启摄像头并拍照。
def turn_on_camera_take_pictures(fileName):
    cap = cv2.VideoCapture(0 + cv2.CAP_DSHOW)
    f,frame = cap.read()
    cv2.imwrite(fileName,frame)
    cap.release()

# 定义函数 get_user_macadress 用于获取主机mac地址。
def get_user_macadress():
    mac = uuid.UUID(int=uuid.getnode()).hex[-12:]
    mac = ":".join(mac[x:x+2]for x in range(0,11,2))
    userInfo = 'your mac adress is:{}'.format(mac)
    return userInfo

# 定义函数 show_pictures 用于展示上述得到的图片
def show_pictures(fileName):
    image = Image.open(fileName) # 读取文件。
    drawObiect = ImageDraw.Draw(image) # 修改文件。
    text = get_user_macadress()
    font = ImageFont.truetype('C:\Windows\Fonts\\ahronbd.ttf',30)
    drawObiect.text((10,50),text,'red',font=font) # 将文本写入图片

    plt.figure(figsize=(10, 10),facecolor='blue')
    # plt.ion()  # 打开交互模式
    # plt.axis('on')  # 不需要坐标轴
    plt.imshow(image)
    mngr = plt.get_current_fig_manager()
    mngr.window.wm_geometry("+380+310")  # 调整窗口在屏幕上弹出的位置
    plt.pause(5)  # 该句显示图片15秒
    # plt.ioff()  # 显示完后一定要配合使用plt.ioff()关闭交互模式，否则可能出奇怪的问题
    # plt.clf()  # 清空图片

# # 定义函数 currentfile_address 获取当前python文件地址、名称、打包后的文件地址
# def currentfile_address():
#     path = os.getcwd() # 获取当前python文件所处目录地址
#     currentFileFullName = os.path.basename(__file__) # 获取当前python文件全名
#     filePath = path + '\\' + currentFileFullName # 拼接当前python文件路径
#     exeFilePath = path + '\\dist'  # 拼接处打包后程序所处目录
#     currentFileName = currentFileFullName[:-3] # 得到当前python文件名称
#     return filePath,exeFilePath,currentFileName
#
# # 定义函数 pack_pythonfile 进行python文件打包
# def pack_pythonfile():
#     filePath, exeFilePath, currentFileName = currentfile_address()
#     statement = 'pyinstaller -F -w ' + filePath # 拼接出cmd 命令运行的语句
#     os.system(statement)
#     targetExeFileFullName = currentFileName + '.exe' # 拼接出目标exe文件
#     targetFolderFileList = os.listdir(exeFilePath) # 得到打包后文件所处目录下所有文件，形成列表。
#     result = False
#     num = 0
#     while (result | (num == 600)) == 1:
#         if targetExeFileFullName in targetFolderFileList:
#             result = True
#             targetExeFilePath = exeFilePath + '\\' + targetExeFileFullName
#             return result,targetExeFilePath
#         else:
#             time.sleep(1)
#             num += 1
#             targetExeFilePath = ""
#             return result,targetExeFilePath
#
# def run_the_program(result,targetExeFilePath):
#     if result == True:
#         statement = 'start' + ' ' + targetExeFilePath
#         os.system(statement)


if __name__ == '__main__':
    fileName = generated_file_name()
    turn_on_camera_take_pictures(fileName)
    time.sleep(1)
    show_pictures(fileName)



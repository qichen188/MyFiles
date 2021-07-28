@echo off
set currentDate=%date%
set dateSuffix=%currentDate:~0,4%%currentDate:~5,2%%currentDate:~8,2%
set currentTime=%time%
set hour=1%currentTime:~1,1%
set timeSuffix=%hour%%currentTime:~3,2%%currentTime:~6,2%%currentTime:~9,2%
set dateTimeSuffix=%dateSuffix%%timeSuffix%
set currentPath=%~dp0
md "nullresult\%dateTimeSuffix%"
call jmeter -JthreadCount=30 -JrampTime=10 -JdurationTime=120 -n -t nullnull.jmx -l nullresult\%dateTimeSuffix%\null_%dateTimeSuffix%.jtl -j nullresult\%dateTimeSuffix%\null_%dateTimeSuffix%.log
call jmeter -g nullresult\%dateTimeSuffix%\null_%dateTimeSuffix%.jtl -e -o nullresult\%dateTimeSuffix%\html\

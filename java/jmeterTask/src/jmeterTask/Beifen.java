package jmeterTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Beifen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
//	public String packBatFile() throws InvalidFormatException, IOException{
//		FileWriter fw = new FileWriter(new File(batPath));
//		String batCommonds="@echo off\n" +
//	            "set currentDate=%date%\n" +
//	            "set dateSuffix=%currentDate:~0,4%%currentDate:~5,2%%currentDate:~8,2%\n" +
//	            "set currentTime=%time%\n" +
//	            "set hour=1%currentTime:~1,1%\n" +
//	            "set timeSuffix=%hour%%currentTime:~3,2%%currentTime:~6,2%%currentTime:~9,2%\n" +
//	            "set dateTimeSuffix=%dateSuffix%%timeSuffix%\n" +
//	            "set currentPath=%~dp0\n" +
//	            "md \"" + scriptPath + "result\\%dateTimeSuffix%\"\n" +
//	            "call jmeter -JthreadCount=" + threadCount + " -JrampTime=" + rampTime + " -JdurationTime=" + durationTime + " -n -t " + scriptPath + scriptName +".jmx -l " + scriptPath + "result\\%dateTimeSuffix%\\" +scriptName + "_%dateTimeSuffix%.jtl -j " + scriptPath + "result\\%dateTimeSuffix%\\" + scriptName + "_%dateTimeSuffix%.log\n" +
//	            "call jmeter -g " + scriptPath + "result\\%dateTimeSuffix%\\" + scriptName + "_%dateTimeSuffix%.jtl -e -o " + scriptPath + "result\\%dateTimeSuffix%\\html\\\n";
//		fw.write(batCommonds);
//		fw.close();
//		return batPath;
//	}

}

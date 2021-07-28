package jmeterTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class PackageBatFile extends GetConfigParams{

	public static void main(String[] args) {
		try {
			String filePath = "G:\\jmeter\\iSalesTest\\configFile.xlsx";
			PackageBatFile pb1 = new PackageBatFile();
			pb1.packBatFile(filePath,0,1);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String packBatFile(String file,int sheetIndex,int rowNumber) throws InvalidFormatException, IOException{
		String filePath = file;
		String scriptPath = getScriptPath(filePath,sheetIndex,rowNumber);
		String scriptName = getScriptName(filePath,sheetIndex,rowNumber);
		String threadCount = getThreadCount(filePath,sheetIndex,rowNumber);
		String rampTime = getRampTime(filePath,sheetIndex,rowNumber);
		String durationTime = getDurationTime(filePath,sheetIndex,rowNumber);
		String batPath = scriptPath + scriptName + ".bat";
		FileWriter fw = new FileWriter(new File(batPath));
		String batCommonds="@echo off\n" +
	            "set currentDate=%date%\n" +
	            "set dateSuffix=%currentDate:~0,4%%currentDate:~5,2%%currentDate:~8,2%\n" +
	            "set currentTime=%time%\n" +
	            "set hour=1%currentTime:~1,1%\n" +
	            "set timeSuffix=%hour%%currentTime:~3,2%%currentTime:~6,2%%currentTime:~9,2%\n" +
	            "set dateTimeSuffix=%dateSuffix%%timeSuffix%\n" +
	            "set currentPath=%~dp0\n" +
	            "md \"" + scriptPath + "result\\%dateTimeSuffix%\"\n" +
	            "call jmeter -JthreadCount=" + threadCount + " -JrampTime=" + rampTime + " -JdurationTime=" + durationTime + " -n -t " + scriptPath + scriptName +".jmx -l " + scriptPath + "result\\%dateTimeSuffix%\\" +scriptName + "_%dateTimeSuffix%.jtl -j " + scriptPath + "result\\%dateTimeSuffix%\\" + scriptName + "_%dateTimeSuffix%.log\n" +
	            "call jmeter -g " + scriptPath + "result\\%dateTimeSuffix%\\" + scriptName + "_%dateTimeSuffix%.jtl -e -o " + scriptPath + "result\\%dateTimeSuffix%\\html\\\n";
		fw.write(batCommonds);
		fw.close();
		return batPath;
	}
	public String packBatFile2(String file,int sheetIndex,int rowNumber) throws InvalidFormatException, IOException{
		String filePath = file;
		String scriptPath = getScriptFullPath(filePath,sheetIndex,1);
		String scriptName = getScriptName(filePath,sheetIndex,1);
		String threadCount = getThreadCount(filePath,sheetIndex,1);
		String rampTime = getRampTime(filePath,sheetIndex,1);
		String durationTime = getDurationTime(filePath,sheetIndex,1);
		String batPath = scriptPath + scriptName + "1.bat";
		FileWriter fw = new FileWriter(new File(batPath));
		String batCommonds="@echo off\n" +
	            "set currentDate=%date%\n" +
	            "set dateSuffix=%currentDate:~0,4%%currentDate:~5,2%%currentDate:~8,2%\n" +
	            "set currentTime=%time%\n" +
	            "set hour=1%currentTime:~1,1%\n" +
	            "set timeSuffix=%hour%%currentTime:~3,2%%currentTime:~6,2%%currentTime:~9,2%\n" +
	            "set dateTimeSuffix=%dateSuffix%%timeSuffix%\n" +
	            "set currentPath=%~dp0\n" +
	            "md \"%currentPath%result\\%dateTimeSuffix%\"\n" +
	            "call jmeter -JthreadCount=" + threadCount + " -JrampTime=" + rampTime + " -JdurationTime=" + durationTime + " -n -t %currentPath%iSalesTest.jmx -l %currentPath%result\\%dateTimeSuffix%\\iSalesTest_%dateTimeSuffix%.jtl -j %currentPath%result\\%dateTimeSuffix%\\iSalesTest_%dateTimeSuffix%.log\n" +
	            "call jmeter -g %currentPath%result\\%dateTimeSuffix%\\iSalesTest_%dateTimeSuffix%.jtl -e -o %currentPath%result\\%dateTimeSuffix%\\html\\";
		fw.write(batCommonds);
		fw.close();
		return batPath;
	}
}
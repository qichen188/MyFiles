package jmeterTask;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.SimpleFraction;

public class RunableClass {

	public static void main(String[] args) {
		try {
			new RunableClass().executionMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void executionMethod() throws InvalidFormatException, IOException, ParseException{
		String filePath = "G:\\jmeter\\iSalesTest\\configFile.xlsx";
		
		Runnable runnable1 = new Processor(filePath,0,1);
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		
		Runnable runnable2 = new Processor(filePath,0,2);
		Thread thread2 = new Thread(runnable2);
		thread2.start();
	}
}
class Processor implements Runnable{
	String filePath;
	int sheetIndex;
	int rowIndex;
	public Processor(String filePath,int sheetIndex,int rowIndex){
		this.filePath = filePath;
		this.sheetIndex = sheetIndex;
		this.rowIndex = rowIndex;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public int getSheetIndex() {
		return sheetIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	@Override
	public void run() {
		String targetStringTime = null;
		try {
			targetStringTime = new GetConfigParams().getTargetStringTime(filePath,sheetIndex,rowIndex);
		} catch (Exception e) {
			System.out.println("获取excle里目标字符串时间出错");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date targetTimeDate = null;
		try {
			targetTimeDate = sdf.parse(targetStringTime);
		} catch (ParseException e) {
			System.out.println("字符串时间转时间戳出错");
		}
		long targetTimeStamp = targetTimeDate.getTime();
		boolean flag = true;
		Process ps = null;
		while(flag){
			long currentTimeStamp = new Date().getTime();
			if(currentTimeStamp > targetTimeStamp){
				System.out.println(currentTimeStamp);
				String batPath = null;
				try {
					batPath = new PackageBatFile().packBatFile(filePath,sheetIndex,rowIndex);
				} catch (Exception e) {
					System.out.println("返回打包的bat文件地址出错");
				}
				String strcmd = "cmd /c start " + batPath;
				Runtime run = Runtime.getRuntime();
				try {
					ps = run.exec(strcmd);
				} catch (IOException e) {
					System.out.println("运行cmd命令出错");
				}
				flag = false;
			}else{
				try {
					Thread.sleep(5000);
					System.out.println("定时任务等待中。。。。。。。。。。");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}

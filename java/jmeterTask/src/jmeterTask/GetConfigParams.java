package jmeterTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetConfigParams {

	public static void main(String[] args) {
		try {
//			String filePath = "G:\\jmeter\\iSalesTest\\result\\2021072518433880\\iSalesTest_2021072518433880.xlsx";
			String filePath = "G:\\jmeter\\iSalesTest\\configFile.xlsx";
			int sheetIndex = 0;
			int rowIndex = 1;
			int columnIndex = 1;
			String s1 = new GetConfigParams().getScriptPath(filePath, sheetIndex, rowIndex);
			System.out.println(s1);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String getTypeConversion(Cell cell){
		if(cell.getCellType() == CellType.BOOLEAN){
			return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellType() == CellType.NUMERIC){
			double db = cell.getNumericCellValue();
			return String.valueOf(db);
		}else{
			return String.valueOf(cell.getStringCellValue());
		}
	}
	/*
	功能：获取指定路径下excle对象
	返回值：excle对象；
	*/
	public XSSFWorkbook getWorkbookObject(String filePath) throws InvalidFormatException, IOException{
		String configFile = filePath;
		FileInputStream fis = new FileInputStream(new File(configFile));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		return workbook;
	}
	/*
	功能：获取指定路径下指定表格的sheet对象
	返回值：excle对象；
	*/
	public XSSFSheet getSheetObject(String filePath,int sheetIndex) throws InvalidFormatException, IOException{
		XSSFWorkbook workbook = getWorkbookObject(filePath);
		XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		return sheet;
	}
	/*
	功能：创建一个sheet对象
	返回值：excle对象；
	*/
	public XSSFSheet createSheetObject(String sheetName) throws InvalidFormatException, IOException{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);
		return sheet;
	}
	/*
	功能：获取脚本是否运行标记
	入参：行号；
	返回值：数据集合；
	*/
	public String getRunFlag(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		ArrayList columnsData = getSpecifyRowValue(filePath,sheetIndex,0);
		Integer columnIndex = null;
		for(int i=0;i<columnsData.size();i++){
			if(columnsData.get(i).toString().equals("runFlag")){
				columnIndex = i;
			}
		}
		String runFlag = getSpecifyRowValue(filePath,sheetIndex,rowIndex).get(columnIndex).toString();
		return runFlag;
	}
	/*
	功能：获取脚本全路径
	入参：行号；
	返回值：数据集合；
	*/
	public String getScriptFullPath(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		ArrayList columnsData = getSpecifyRowValue(filePath,sheetIndex,0);
		Integer columnIndex = null;
		for(int i=0;i<columnsData.size();i++){
			if(columnsData.get(i).toString().equals("scriptFullPath")){
				columnIndex = i;
			}
		}
		String scriptFullPath = getSpecifyRowValue(filePath,sheetIndex,rowIndex).get(columnIndex).toString();
		return scriptFullPath;
	}
	/*
	功能：获取脚本全路径
	入参：行号；
	返回值：数据集合；
	*/
	public String getScriptPath(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		//获取脚本名称
		String scriptFullPath = getScriptFullPath(filePath,sheetIndex,rowIndex);
		String scriptPathPatternString = "(.*\\\\).*.jmx";
		Pattern scriptPathPattern = Pattern.compile(scriptPathPatternString);
		Matcher scriptNameResult = scriptPathPattern.matcher(scriptFullPath);
		String scriptPath = null;
		if(scriptNameResult.find()){
			scriptPath = scriptNameResult.group(1);
		}
		return scriptPath;
	}
	/*
	功能：获取线程数
	入参：行号；
	返回值：数据集合；
	*/
	public String getThreadCount(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		ArrayList columnsData = getSpecifyRowValue(filePath,sheetIndex,0);
		Integer columnIndex = null;
		for(int i=0;i<columnsData.size();i++){
			if(columnsData.get(i).toString().equals("threadCount")){
				columnIndex = i;
			}
		}
		String threadCount = getSpecifyRowValue(filePath,sheetIndex,rowIndex).get(columnIndex).toString();
		return threadCount;
	}
	/*
	功能：获取登录时间
	入参：行号；
	返回值：数据集合；
	*/
	public String getRampTime(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		ArrayList columnsData = getSpecifyRowValue(filePath,sheetIndex,0);
		Integer columnIndex = null;
		for(int i=0;i<columnsData.size();i++){
			if(columnsData.get(i).toString().equals("rampTime")){
				columnIndex = i;
			}
		}
		String rameStamp = getSpecifyRowValue(filePath,sheetIndex,rowIndex).get(columnIndex).toString();
		return rameStamp;
	}
	/*
	功能：获取运行时间
	入参：行号；
	返回值：数据集合；
	*/
	public String getDurationTime(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		ArrayList columnsData = getSpecifyRowValue(filePath,sheetIndex,0);
		Integer columnIndex = null;
		for(int i=0;i<columnsData.size();i++){
			if(columnsData.get(i).toString().equals("durationTime")){
				columnIndex = i;
			}
		}
		String durtation = getSpecifyRowValue(filePath,sheetIndex,rowIndex).get(columnIndex).toString();
		return durtation;
	}
	/*
	功能：获取预定时间
	入参：行号；
	返回值：数据集合；
	*/
	public String getTargetStringTime(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		ArrayList columnsData = getSpecifyRowValue(filePath,sheetIndex,0);
		Integer columnIndex = null;
		for(int i=0;i<columnsData.size();i++){
			if(columnsData.get(i).toString().equals("targetStringTime")){
				columnIndex = i;
			}
		}
		String targetTime = getSpecifyRowValue(filePath,sheetIndex,rowIndex).get(columnIndex).toString();
		return targetTime;
	}
	/*
	 * function:获取脚本名称
	 */
	public String getScriptName(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		//获取脚本名称
		String scriptFullPath = getScriptFullPath(filePath,sheetIndex,rowIndex);
		String scriptNamePatternString = ".*\\\\(.*).jmx";
		Pattern scriptNamePattern = Pattern.compile(scriptNamePatternString);
		Matcher scriptNameResult = scriptNamePattern.matcher(scriptFullPath);
		String scriptName = null;
		if(scriptNameResult.find()){
			scriptName = scriptNameResult.group(1);
		}
		return scriptName;
	}
	/*
	 * function:获取表格里指定表格的行数
	 */
	public int getRealRows(String filePath,int sheetIndex) throws InvalidFormatException, IOException{
		XSSFWorkbook workbook = getWorkbookObject(filePath);
		XSSFSheet sheet = getSheetObject(filePath,sheetIndex);
		int rows = sheet.getPhysicalNumberOfRows();
		return rows;
	}
	/*
	功能：获取表格指定行的所有单元格数据
	入参：行号；
	返回值：数据集合；
	*/
	public ArrayList getSpecifyRowValue(String filePath,int sheetIndex,int rowIndex) throws InvalidFormatException, IOException{
		XSSFWorkbook workbook = getWorkbookObject(filePath);
		XSSFSheet sheet = getSheetObject(filePath,sheetIndex);
		ArrayList<String> rowDataList = new ArrayList<String>();
		Row row = sheet.getRow(rowIndex);
		int totalColumns = row.getPhysicalNumberOfCells();
		for(int i=0;i<totalColumns;i++){
			Cell cell = row.getCell(i);
			String value = getTypeConversion(cell);
			rowDataList.add(value);
		}
		return rowDataList;
	}
	/*
	 * function:获取表格里指定列的数据
	 */
	public ArrayList getSpecifyCloumnValues(String filePath,int sheetIndex,int columnIndex) throws InvalidFormatException, IOException{
		XSSFWorkbook workbook = getWorkbookObject(filePath);
		XSSFSheet sheet = getSheetObject(filePath,sheetIndex);
		int rows = sheet.getPhysicalNumberOfRows();
		ArrayList<String> columnValueLis = new ArrayList<String>();
		for(int i=0;i<rows;i++){
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(columnIndex);
			String value = getTypeConversion(cell);
			columnValueLis.add(value);
		}
		return columnValueLis;
	}
	/*
	 * function:获取表格里指定单元格的数据
	 */
	public String getSpecifyCellValue(String filePath,int sheetIndex,int rowIndex,int columnIndex) throws InvalidFormatException, IOException{
		ArrayList<String> rowDataLis = getSpecifyRowValue(filePath, sheetIndex, rowIndex);
		String cellValue = rowDataLis.get(columnIndex);
		return cellValue;
	}
	/*
	 * function:用于去重
	 */
	public ArrayList dataDeduplication(String filePath,int sheetIndex,int columnIndex) throws InvalidFormatException, IOException{
		ArrayList<String> dataLis = new ArrayList<String>();
		dataLis = new GetConfigParams().getSpecifyCloumnValues(filePath, sheetIndex, columnIndex);
		ArrayList valueLis=(ArrayList) dataLis.stream().distinct().collect(Collectors.toList());
		return valueLis;
	}
}

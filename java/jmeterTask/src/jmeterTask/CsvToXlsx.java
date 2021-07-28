package jmeterTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CsvToXlsx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new CsvToXlsx().csvToXlsx();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * function：用于将csv文件内容写入xlsx
	 */
	public void csvToXlsx() throws IOException{
		String csvFilePath = "G:\\jmeter\\iSalesTest\\result\\2021072821520000\\result_2021072821520000.csv";
		String xlsxFilePath = "G:\\jmeter\\iSalesTest\\result\\2021072821520000\\result_2021072821520000.xlsx";
		File csvFile = new File(csvFilePath);
		File xlsxFile = new File(xlsxFilePath);
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet1");
		String contentLine = null;
		XSSFRow row;
		int index = 0;
		while((contentLine=br.readLine())!=null){
			ArrayList<String> cellValuesLis = SplitString(contentLine);
			row = sheet.createRow(index);
			for(int i=0;i<cellValuesLis.size();i++){
				Cell cell = row.createCell(i);
				cell.setCellValue(cellValuesLis.get(i).toString());
			}
			index = index + 1;
		}
		FileOutputStream fos = new FileOutputStream(xlsxFile);
		workbook.write(fos);
		fos.close();
		System.out.println("数据转换结束");
	}
	/*
	 * function:用于将csv重组
	 */
	public ArrayList SplitString(String content){
		String contentString = content;
		ArrayList<String> arrlis = null;
		if(contentString.contains("\"")){
			String[] array = contentString.split("\"");
			String[] array0 = array[0].substring(0, array[0].length()-2).split(",");
			String[] array1 = new String[]{array[1]};
			String[] array2 = array[2].substring(1, array[2].length()-1).split(",");
			arrlis = new ArrayList<String>(Arrays.asList(array0));
			arrlis.addAll(Arrays.asList(array1));
			arrlis.addAll(Arrays.asList(array2));

		}else{
			String[] array = contentString.split(",");
			arrlis = new ArrayList<String>(Arrays.asList(array));
		}
		return arrlis;
	}

}

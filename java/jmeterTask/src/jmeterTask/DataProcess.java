package jmeterTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sourceFilePath = "G:\\jmeter\\iSalesTest\\result\\2021072821520000\\sourcefile_2021072821520000.xlsx";
		String resultfilePath = "G:\\jmeter\\iSalesTest\\result\\2021072821520000\\resultfile_2021072821520000.xlsx";
		int sheetIndex = 0;
		int columnIndex = 2;
		try {
			new DataProcess().dataGetProcess(sourceFilePath,resultfilePath,sheetIndex,columnIndex);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * function:�������������б�
	 */
	public ArrayList generateIntegerList(Integer num){
		ArrayList<Integer> arrLis = new ArrayList<Integer>();
		for(int x=0;x<num;x++){
			arrLis.add(x);
		}
		return arrLis;
	}
	/*
	 * function:���ڷ�װ����
	 */
	public void dataGetProcess(String sourceFilePath,String ResultFilePath,int sheetIndex,int columnIndex) throws InvalidFormatException, IOException{
		ArrayList<String> labelLis = new GetConfigParams().dataDeduplication(sourceFilePath,sheetIndex,columnIndex);
		int rows = new GetConfigParams().getRealRows(sourceFilePath, sheetIndex);
		//�����б����ڴ�����ű������
		ArrayList<Integer> indexLis = generateIntegerList(rows);
		for(int i=0;i<labelLis.size();i++){
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("performanceTestData");
			//����Ƕ�׵��б����ڷ�װͬ�������������
			ArrayList<ArrayList<String>> cellValuesLis = new ArrayList<ArrayList<String>>();
			//ѭ����������ִ������������б�
			for(int rowIndex=0;rowIndex<indexLis.size();rowIndex++){
				String comparisonField = new GetConfigParams().getSpecifyCellValue(sourceFilePath, sheetIndex, rowIndex, columnIndex);
				if(comparisonField.equals(labelLis.get(i))){
					ArrayList rowValueLis = new GetConfigParams().getSpecifyRowValue(sourceFilePath, sheetIndex, rowIndex);
					cellValuesLis.add(rowValueLis);
				}
			}
			if(labelLis.get(i).toString().equals("label")){
				writeSheet(ResultFilePath,cellValuesLis,i);
			}else{
//				getAverageElapsed(cellValuesLis);
//				getLabel(cellValuesLis);
//				getSamples(cellValuesLis);
//				getError(cellValuesLis);
//				getThroughput(cellValuesLis);
//				getReceived(cellValuesLis);
				getSent(cellValuesLis);
			}
		}
		
	}
	
	public void writeSheet(String newfilePath,ArrayList<ArrayList<String>> valuesLis,int rowIndex) throws IOException{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("performanceTestData");
		String[] sheetHeadArray = new String[]{"Label","# Samples","Average","Median","90% Line","95% Line","99% Line","Min","Max",
				"Error %","Throughput","Received KB/sec","Sent KB/sec"};
		List<String> sheetHeadLis = new ArrayList<String>();
		sheetHeadLis =  Arrays.asList(sheetHeadArray);
		Row row = sheet.createRow(rowIndex);
		for(int i=0;i<sheetHeadLis.size();i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(sheetHeadLis.get(i).toString());
		}
		FileOutputStream fos = new FileOutputStream(newfilePath);
		workbook.write(fos);
		System.out.println("����д�����");
	}
	
	/*
	 * function����ȡlabel
	 */
	public String getLabel(ArrayList<ArrayList<String>> cellValuesLis){
		String label = cellValuesLis.get(0).get(2).toString();
		return label;
	}
	
	/*
	 * function����ȡsamples
	 */
	public String getSamples(ArrayList<ArrayList<String>> cellValuesLis){
		String samples = Integer.toString(cellValuesLis.size());
		return samples;
	}
	
	/*
	 * function����ȡƽ��ֵ
	 */
	public String getAverageElapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		Integer sum = 0;
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			sum = sum + value;
		}
		Integer averageElapsed = Math.round(sum / cellValuesLis.size());
		String average = averageElapsed.toString();
		return average;
	}
	
	/*
	 * function����ȡmedian
	 */
	public String getMedianElapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			elapsedLis.add(value);
		}
		Integer medianElapsedIndex = (int) Math.round(elapsedLis.size() * 0.5);
		String medianElapsed = elapsedLis.get(medianElapsedIndex).toString();
		return medianElapsed;
	}
	
	/*
	 * function����ȡp90
	 */
	public String getP90Elapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			elapsedLis.add(value);
		}
		Integer p90Index = (int) Math.round(elapsedLis.size() * 0.9);
		String P90Elapsed = elapsedLis.get(p90Index).toString();
		return P90Elapsed;
	}
	
	/*
	 * function����ȡp95
	 */
	public String getP95Elapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			elapsedLis.add(value);
		}
		Integer p95Index = (int) Math.round(elapsedLis.size() * 0.95);
		String P95Elapsed = elapsedLis.get(p95Index).toString();
		return P95Elapsed;
	}
	
	/*
	 * function����ȡp99
	 */
	public String getP99Elapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			elapsedLis.add(value);
		}
		Integer p99Index = (int) Math.round(elapsedLis.size() * 0.99);
		String P99Elapsed = elapsedLis.get(p99Index).toString();
		return P99Elapsed;
	}

	/*
	 * function����ȡ��Сֵʱ��
	 */
	public String getMinElapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			elapsedLis.add(value);
		}
		Collections.sort(elapsedLis);
		String minElapsed = elapsedLis.get(0).toString();
		return minElapsed;
	}
	
	/*
	 * function����ȡ���ֵʱ��
	 */
	public String getMaxElapsed(ArrayList<ArrayList<String>> cellValuesLis){
		List<Integer> elapsedLis = new ArrayList<Integer>();
		for(int i=0;i<cellValuesLis.size();i++){
			Integer value = Integer.parseInt(cellValuesLis.get(i).get(1).toString());
			elapsedLis.add(value);
		}
		Collections.sort(elapsedLis);
		String maxElapsed = elapsedLis.get(elapsedLis.size()).toString();
		return maxElapsed;
	}
	
	/*
	 * function����ȡ���ֵʱ��
	 */
	public void getError(ArrayList<ArrayList<String>> cellValuesLis){
		List<String> elapsedLis = new ArrayList<String>();
		ArrayList<String> errorLis = new ArrayList<String>();
		for(int i=0;i<cellValuesLis.size();i++){
			errorLis.add(cellValuesLis.get(i).get(7).toString());
		}
		System.out.println(errorLis);
		Integer trueCount = counterElement(errorLis, "FALSE");
		double errorRateDouble = trueCount / cellValuesLis.size() * 100;
		String errorRateString = String.format("%.2f", errorRateDouble);
		String percentageFailureRate = errorRateString + "%";
		System.out.println(percentageFailureRate);
	}
	
	/*
	* function����ȡ������
	 */
	public String getThroughput(ArrayList<ArrayList<String>> cellValuesLis){
		long startTimeStamp = Long.parseLong(cellValuesLis.get(0).get(0).toString());
		long endTimeStamp = Long.parseLong(cellValuesLis.get(cellValuesLis.size()-1).get(0).toString());
		int startEndTime = (int) (endTimeStamp - startTimeStamp);
		int finalSampleCost = Integer.parseInt(cellValuesLis.get(cellValuesLis.size()-1).get(1).toString());
		//�˴�Ϊms
		int costTime = startEndTime + finalSampleCost;
		Integer countSample = cellValuesLis.size();
		//�˴�Ϊ ָ���������� / ��
		double throughputDouble = (float)countSample / (float)costTime * 1000;
		String throughputString = String.format("%.2f", throughputDouble);
		String throughput = throughputString + "/sec";
		return throughput;
	}
	
	/*
	* function����ȡ����������
	 */
	public String getReceived(ArrayList<ArrayList<String>> cellValuesLis){
		long startTimeStamp = Long.parseLong(cellValuesLis.get(0).get(0).toString());
		long endTimeStamp = Long.parseLong(cellValuesLis.get(cellValuesLis.size()-1).get(0).toString());
		int startEndTime = (int) (endTimeStamp - startTimeStamp);
		int finalSampleCost = Integer.parseInt(cellValuesLis.get(cellValuesLis.size()-1).get(1).toString());
		//�˴�ʱ�䵥λ�� ms
		int costTime = startEndTime + finalSampleCost;
		Integer totalReceivedBytes = 0;
		for(int i=0;i<cellValuesLis.size();i++){
			Integer receivedBytes = Integer.parseInt(cellValuesLis.get(i).get(9).toString());
			totalReceivedBytes = totalReceivedBytes + receivedBytes;
		}
		//�˴���ʱ�䵥λ �� msתΪ s
		double receivedDouble = (float)totalReceivedBytes /1024 / (float)costTime * 1000;
		String receivedString = String.format("%.2f", receivedDouble);
		String received = receivedString;
		return received;
	}
	
	/*
	* function����ȡ����������
	 */
	public void getSent(ArrayList<ArrayList<String>> cellValuesLis){
		long startTimeStamp = Long.parseLong(cellValuesLis.get(0).get(0).toString());
		long endTimeStamp = Long.parseLong(cellValuesLis.get(cellValuesLis.size()-1).get(0).toString());
		int startEndTime = (int) (endTimeStamp - startTimeStamp);
		int finalSampleCost = Integer.parseInt(cellValuesLis.get(cellValuesLis.size()-1).get(1).toString());
		//�˴�ʱ�䵥λ�� ms
		int costTime = startEndTime + finalSampleCost;
		Integer totaSentlBytes = 0;
		for(int i=0;i<cellValuesLis.size();i++){
			Integer sentBytes = Integer.parseInt(cellValuesLis.get(i).get(10).toString());
			totaSentlBytes = totaSentlBytes + sentBytes;
		}
		//�˴���ʱ�䵥λ �� msתΪ s
		double sentDouble = (float)totaSentlBytes /1024 / (float)costTime * 1000;
		String sentString = String.format("%.2f", sentDouble);
		String sent = sentString;
		System.out.println("����������/����:"+sent);
	}
	
	/*
	 * function:ͳ��ָ��Ԫ�ظ���
	 */
	public Integer counterElement(ArrayList<String> valuesLis,String element){
		Integer count = 0;
		for(int i=0;i<valuesLis.size();i++){
			if(element.equals(valuesLis.get(i))){
				count = count + 1;
			}
		}
		return count;
	}
}

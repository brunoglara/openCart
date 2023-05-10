package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data Provider 1
	@DataProvider(name = "LoginData")
	public String [][] getData() throws IOException{
		
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		
		ExcelUtility xlUtility = new ExcelUtility(path);
		
		int totalRows = xlUtility.getRowCount("Sheet1");
		int totalCols= xlUtility.getCellCount("Sheet1",1);
		
		String loginData[][] = new String[totalRows][totalCols];
		
		
		for(int i=1; i<=totalRows; i++) {
			
			for(int c=0; c<totalCols;c++) {
				
				loginData[i-1][c]= xlUtility.getCellData("Sheet1", i, c);
			}
		}
		
		return loginData;
	}
}

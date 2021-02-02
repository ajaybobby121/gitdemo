package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {
	public static String WriteExcelTab(String fileName, String sheetNo , List<List<String>> listConf, int rowCnt, int colCnt) throws IOException {
		String ret_Msg = "";
		System.out.println("Start of ReadExcel WriteExcelTab");
		FileInputStream fis = new FileInputStream(fileName);		
		Workbook workbook = null;
		if(fileName.toLowerCase().endsWith("xlsx")){
			workbook = new XSSFWorkbook(fis);
		}else if(fileName.toLowerCase().endsWith("xls")){
			workbook = new HSSFWorkbook(fis);
		}
		Sheet sheet = workbook.getSheet(sheetNo);
		
		//----
		rowCnt = rowCnt - 1; //Excel read starts with 0 , Substract 1 to counter this. 
		//colCnt -1 already factored while passing the value. 
		//colCnt = colCnt + 1;
		for (List<String> strTemp : listConf){
			Cell cell = null;
			String cellValue = strTemp.get(colCnt);
			//System.out.println("cellValue :" + cellValue + "|" + rowCnt + "|" + colCnt);
			cell = sheet.getRow(rowCnt).getCell(colCnt);
	        cell.setCellValue(cellValue);
	        rowCnt = rowCnt + 1;			
		}		
        fis.close();

        FileOutputStream outFile =new FileOutputStream(fileName);
        workbook.write(outFile);
        outFile.close();	
        
/*
        //Update the value of cell
        cell = sheet.getRow(1).getCell(2);
        cell.setCellValue("TEST1");
        cell = sheet.getRow(2).getCell(2);
        cell.setCellValue("TEST2");
        Row row = sheet.getRow(0);
        row.createCell(3).setCellValue("Value 2");
*/
        ret_Msg = "Excel Updated";
        System.out.println("End of ReadExcel WriteExcelTab");
		return ret_Msg;
	}
	
	public static String WriteExcelTabCell(String fileName, String sheetNo , String cellText, int rowCnt, int colCnt) throws IOException {
		String ret_Msg = "";
		//System.out.println("Start of WriteExcelTabCell");
		FileInputStream fis = new FileInputStream(fileName);		
		Workbook workbook = null;
		if(fileName.toLowerCase().endsWith("xlsx")){
			workbook = new XSSFWorkbook(fis);
		}else if(fileName.toLowerCase().endsWith("xls")){
			workbook = new HSSFWorkbook(fis);
		}
		Sheet sheet = workbook.getSheet(sheetNo);
				
		Cell cell = null;
		String cellValue = cellText;
		cell = sheet.getRow(rowCnt).getCell(colCnt);
		cell.setCellValue(cellValue);
		
		fis.close();

        FileOutputStream outFile =new FileOutputStream(fileName);
        workbook.write(outFile);
        outFile.close();
        
        ret_Msg = "Excel Updated";
        //System.out.println("End of WriteExcelTabCell");
		return ret_Msg;
	}	    
	
	public List<List<String>> ReadExcelTab(String fileName, String sheetNo , int rowCnt) {
		System.out.println("Start of ReadExcel ReadExcelTab");
		List<List<String>> xlLogin_List = new ArrayList<List<String>>();
        
		int xl_rowCnt = 0;
		int xl_colCnt = 0;
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			//Sheet sheet = workbook.getSheetAt(sheetNo); //
			Sheet sheet = workbook.getSheet(sheetNo);
			xl_colCnt = 0;
			//every sheet has rows, iterate over them
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) 
	        {
				List<String> loginList = new ArrayList<String>();
				xl_rowCnt = xl_rowCnt + 1 ;
				//Get the row object
				Row row = rowIterator.next();
				//Every row has columns, get the column iterator and iterate over them
				Iterator<Cell> cellIterator = row.cellIterator();
				xl_colCnt = 0 ;
				if (xl_rowCnt >= rowCnt){ 
		            while (cellIterator.hasNext()) 
		            {
		            	xl_colCnt = xl_colCnt + 1;
		            	Cell cell = cellIterator.next();
		            	if ((xl_colCnt == 1) && (cell.getStringCellValue().trim().isEmpty())){
		            		break;			            	
		            	}
		            	loginList.add(cell.getStringCellValue().trim());
		            } //end of cell iterator
		            if (!loginList.isEmpty()){
		            	xlLogin_List.add(loginList);
		            }
				}
	        } //end of rows iterator
			
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End of ReadExcel ReadExcelTab");    
		return xlLogin_List;
	}
	
	//SOC: Akshaya @16/03/2020 : function to write data into an existing list
	
	public  void writeToExcel(String fileName, String tabName,List<List<String>> listConf) throws Exception { 
		
		File file=new File(fileName);
		XSSFWorkbook workbook;

		// Check file existence 
		if (file.exists() == false) {
		// Create new file if it does not exist
			workbook = new XSSFWorkbook();
		} 
		else {
			
			try ( 
		         // Make current input to exist file
		         InputStream is = new FileInputStream(file)) {
		         workbook = new XSSFWorkbook(is);
		        }
		    }
		XSSFSheet spreadsheet = workbook.getSheet(tabName);
		XSSFRow rows=null;
		int colCount=0;
		// Check if the workbook is empty or not
		if (workbook.getNumberOfSheets() != 0) {
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				if (workbook.getSheetName(i).equals(tabName)) {
					int noOfRows=spreadsheet.getLastRowNum();
					rows =spreadsheet.getRow(1) ;
				    colCount = rows.getLastCellNum(); 
					System.out.println("colcount"+colCount);
					System.out.println("rows" +noOfRows);
					spreadsheet = workbook.getSheet(tabName);
					for (int x = 0; x < listConf.size(); x++) {
					Row row = spreadsheet.createRow(noOfRows+1);
				    for (int j = 0; j < colCount; j++) {
						
						Cell cell = row.createCell(j);
						cell.setCellValue(listConf.get(x).get(j));
					}
					}
//					Row row = spreadsheet.createRow(noOfRows+1);
//					Cell cell = row.createCell(0);
//					cell.setCellValue(listConf.get(0).get(0));
//					cell = row.createCell(1);
//					cell.setCellValue(listConf.get(0).get(1));
//					cell = row.createCell(2);
//					cell.setCellValue(listConf.get(0).get(2));
				} 
			}
		 }

		FileOutputStream fos = new FileOutputStream(file);
	    workbook.write(fos);
	    System.out.println();
	    System.out.println("File exported successfully");
		
	}	
//EOC: Akshaya @16/03/2020 : function to write data into an existing list
	
	//SOC: Akshaya @18/03/2020 : function to read excel from specific start row to specific end row
	
	public  List<List<String>> ReadExcelTab(String fileName, String sheetNo , int rowCnt,int rowCntEnd) {
		System.out.println("Start of ReadExcel ReadExcelTab");
		List<List<String>> xlLogin_List = new ArrayList<List<String>>();
        
		int xl_rowCnt = 0;
		int xl_colCnt = 0;
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			//Sheet sheet = workbook.getSheetAt(sheetNo); //
			Sheet sheet = workbook.getSheet(sheetNo);
			xl_colCnt = 0;
			//every sheet has rows, iterate over them
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) 
	        {
				List<String> loginList = new ArrayList<String>();
				xl_rowCnt = xl_rowCnt + 1 ;
				//Get the row object
				Row row = rowIterator.next();
				//Every row has columns, get the column iterator and iterate over them
				Iterator<Cell> cellIterator = row.cellIterator();
				xl_colCnt = 0 ;
				if (xl_rowCnt >= rowCnt && xl_rowCnt<=rowCntEnd){ 
		            while (cellIterator.hasNext()) 
		            {
		            	xl_colCnt = xl_colCnt + 1;
		            	Cell cell = cellIterator.next();
		            	if ((xl_colCnt == 1) && (cell.getStringCellValue().trim().isEmpty())){
		            		break;			            	
		            	}
		            	loginList.add(cell.getStringCellValue().trim());
		            } //end of cell iterator
		            if (!loginList.isEmpty()){
		            	xlLogin_List.add(loginList);
		            }
				}
	        } //end of rows iterator
			
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End of ReadExcel ReadExcelTab");    
		return xlLogin_List;
	}
	
	//EOC: Akshaya @18/03/2020 : function to read excel from specific start row to specific end row
	public  void UpdateExcel(String fileName, String tabName,int sheetNum,Object[][] array) throws Exception { 
		 try {
	            FileInputStream inputStream = new FileInputStream(new File(fileName));
	            Workbook workbook = WorkbookFactory.create(inputStream);
	 
	            Sheet sheet = workbook.getSheetAt(1);
	 
//	            Object[][] bookData = {
//	                    {"The Passionate Programmer", "Chad Fowler", 16},
//	                    {"Software Craftmanship", "Pete McBreen", 26},
//	                    {"The Art of Agile Development", "James Shore", 32},
//	                    {"Continuous Delivery", "Jez Humble", 41},
//	            };
	            
	 
	            int rowCount = sheet.getLastRowNum();
//	            for (List<String> strTemp : listConf){
//	            	Row row = sheet.createRow(++rowCount);
//	            	  int columnCount = 0;
//	            	  Cell cell = row.createCell(columnCount);
//		                cell.setCellValue(rowCount);
//		                for (Object field :  strTemp) {
//		                    cell = row.createCell(++columnCount);
//		                    if (field instanceof String) {
//		                        cell.setCellValue((String) field);
//		                    } else if (field instanceof Integer) {
//		                        cell.setCellValue((Integer) field);
//		                    }
//		                }

//       			String cellValue = strTemp.get(colCnt);
//       			//System.out.println("cellValue :" + cellValue + "|" + rowCnt + "|" + colCnt);
//       			cell = sheet.getRow(rowCnt).getCell(colCnt);
//       	        cell.setCellValue(cellValue);
//               }
	            for (Object[] aBook : array) {
	                Row row = sheet.createRow(++rowCount);
	 
	                int columnCount = 0;
	                 
	                Cell cell = row.createCell(columnCount);
	                cell.setCellValue(rowCount);
	               
	                 
	                for (Object field : aBook) {
	                    cell = row.createCell(++columnCount);
	                    if (field instanceof String) {
	                        cell.setCellValue((String) field);
	                    } else if (field instanceof Integer) {
	                        cell.setCellValue((Integer) field);
	                    }
	                }
	 
	            }
	 
	            inputStream.close();
	 
	            FileOutputStream outputStream = new FileOutputStream(fileName);
	            workbook.write(outputStream);
	            ((FileInputStream) workbook).close();
	            outputStream.close();
	             
	        } catch (IOException | EncryptedDocumentException ex) {
	            ex.printStackTrace();
	        }
	    }
	
	public  void writeExcel(String fileName, String tabName , List<List<String>> listConf) throws FileNotFoundException, IOException{
		File file=new File(fileName);
		XSSFWorkbook workbook;

		// Check file existence 
		if (file.exists() == false) {
		// Create new file if it does not exist
			workbook = new XSSFWorkbook();
		} 
		else {
			
			try ( 
		         // Make current input to exist file
		         InputStream is = new FileInputStream(fileName)) {
		         workbook = new XSSFWorkbook(is);
		        }
		    }
		XSSFSheet spreadsheet = workbook.getSheet(tabName);
		Iterator <List<String>>i = listConf.iterator();
		int rownum=0;
        int cellnum = 0;
        while (i.hasNext()) {
            List<String> templist = (List<String>) i.next();
            Iterator<String> tempIterator= templist.iterator();
            Row row =spreadsheet.createRow(rownum++);
            cellnum = 0;
            while (tempIterator.hasNext()) {
                String temp = (String) tempIterator.next();
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(temp);

                }
        }
        FileOutputStream fos = new FileOutputStream(fileName);
	    workbook.write(fos);
	    System.out.println();
	    System.out.println("File exported successfully");
		
	}
	public List<List<String>> ReadExcelTablast(String fileName, String sheetNo) {
		System.out.println("Start of ReadExcel ReadExcelTab");
		List<List<String>> xlLogin_List = new ArrayList<List<String>>();
		
        
		int xl_rowCnt = 0;
		int xl_colCnt = 0;
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			//Sheet sheet = workbook.getSheetAt(sheetNo); //
			Sheet sheet = workbook.getSheet(sheetNo);
			int lastrow=sheet.getLastRowNum();
			 System.out.println("Sheet row count.." +sheet.getLastRowNum()); 
			xl_colCnt = 0;
			//every sheet has rows, iterate over them
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) 
	        {
				List<String> loginList = new ArrayList<String>();
				xl_rowCnt = xl_rowCnt + 1 ;
				//Get the row object
				Row row = rowIterator.next();
				//Every row has columns, get the column iterator and iterate over them
				Iterator<Cell> cellIterator = row.cellIterator();
				xl_colCnt = 0 ;
				if (xl_rowCnt >= lastrow){ 
		            while (cellIterator.hasNext()) 
		            {
		            	xl_colCnt = xl_colCnt + 1;
		            	Cell cell = cellIterator.next();
		            	if ((xl_colCnt == 1) && (cell.getStringCellValue().trim().isEmpty())){
		            		break;			            	
		            	}
		            	loginList.add(cell.getStringCellValue().trim());
		            } //end of cell iterator
		            if (!loginList.isEmpty()){
		            	xlLogin_List.add(loginList);
		            }
				}
	        } //end of rows iterator
			
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End of ReadExcel ReadExcelTab");    
		return xlLogin_List;
	}
	//function for clearing data in sheet except headers
	public void cleanSheetwheaders(String fileName, String sheetNo) throws IOException {
		File file=new File(fileName);
		XSSFWorkbook workbook;

		// Check file existence 
		if (file.exists() == false) {
		// Create new file if it does not exist
			workbook = new XSSFWorkbook();
		} 
		else {
			
			try ( 
		         // Make current input to exist file
		         InputStream is = new FileInputStream(fileName)) {
		         workbook = new XSSFWorkbook(is);
		        }
		    }
		Sheet sheet = workbook.getSheet(sheetNo);
	    int numberOfRows = sheet.getPhysicalNumberOfRows();
	    System.out.println(numberOfRows);

	    if(numberOfRows > 0) {
	        for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
	            if(sheet.getRow(i) != null) {
	                sheet.removeRow( sheet.getRow(i));
//	                System.out.println("ajay");
	            } else {
	                System.out.println("Info: clean sheet='" + sheet.getSheetName() + "' ... skip line: " + i);
	            }
	        }               
	    } else {
	        System.out.println("Info: clean sheet='" + sheet.getSheetName() + "' ... is empty");
	    }       
//		Sheet sheet = workbook.getSheet(sheetNo);
//		Iterator<Row> rowIte =  sheet.iterator();
//		while(rowIte.hasNext()){
//		    rowIte.next();              
//		    rowIte.remove();
//		    System.out.println("ajay");
//		}
//		for(int i = sheet.getLastRowNum(); i >= 1; i--)
//		{
//		  Row row = sheet.getRow(i);
//		  if(row != null)
//		  {
//		    sheet.removeRow(row);
//		  }
//	}
		FileOutputStream fos = new FileOutputStream(file);
	    workbook.write(fos);
	}
	//function for clearing data in sheet including headers
	public void cleanSheetwoheaders(String fileName, String sheetNo) throws IOException {
		File file=new File(fileName);
		XSSFWorkbook workbook;

		// Check file existence 
		if (file.exists() == false) {
		// Create new file if it does not exist
			workbook = new XSSFWorkbook();
		} 
		else {
			
			try ( 
		         // Make current input to exist file
		         InputStream is = new FileInputStream(fileName)) {
		         workbook = new XSSFWorkbook(is);
		        }
		    }
		Sheet sheet = workbook.getSheet(sheetNo);
	    int numberOfRows = sheet.getPhysicalNumberOfRows();
	    System.out.println(numberOfRows);

	    if(numberOfRows > 0) {
	        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
	            if(sheet.getRow(i) != null) {
	                sheet.removeRow( sheet.getRow(i));
	                System.out.println("ajay");
	            } else {
	                System.out.println("Info: clean sheet='" + sheet.getSheetName() + "' ... skip line: " + i);
	            }
	        }               
	    } else {
	        System.out.println("Info: clean sheet='" + sheet.getSheetName() + "' ... is empty");
	    }       
//		Sheet sheet = workbook.getSheet(sheetNo);
//		Iterator<Row> rowIte =  sheet.iterator();
//		while(rowIte.hasNext()){
//		    rowIte.next();              
//		    rowIte.remove();
//		    System.out.println("ajay");
//		}
//		for(int i = sheet.getLastRowNum(); i >= 1; i--)
//		{
//		  Row row = sheet.getRow(i);
//		  if(row != null)
//		  {
//		    sheet.removeRow(row);
//		  }
//	}
		FileOutputStream fos = new FileOutputStream(file);
	    workbook.write(fos);
	}
	
	 
	
	
}

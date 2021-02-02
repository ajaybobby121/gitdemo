package baseClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ElementConstants;

public class BaseDriver {
	
	WebDriver driver = new ChromeDriver();	
	//public static WebDriver driver;
	//BaseDriver.driver = new ChromeDriver();
	//WebDriver driver = new FirefoxDriver();
	//WebDriver driver = new InternetExplorerDriver();
	String mainWinID;
	String newAdwinID;
	
	public void creatingFile(String filepath, String data) throws IOException {
		sleep(2000);
		FileOutputStream out = new FileOutputStream(filepath);
		out.write(data.getBytes());
		out.close();
	}

	public void executeCmd(String testPlantPath) throws IOException, InterruptedException {
//		String count=driver.findElement(By.xpath("//*[@id='hideMenu']/app-home/div[1]/div[2]/div/div[2]/span[2]")).getText();
//		driver.
		System.out.println(testPlantPath);
		Process p;
		try {
			p = Runtime.getRuntime().exec(testPlantPath);
			System.out.println(p);

			p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteFile(String filepath) {
		String fileName = filepath;
		File inputFile = new File(fileName);
		if (inputFile.exists()) {
			inputFile.delete();
		}
	}
	
	protected void switchbacktoMainWindow() throws InterruptedException {
		driver.switchTo().window(mainWinID);
	}
	
	protected void switchwindow() throws InterruptedException {
		Set<String> windowId = driver.getWindowHandles(); // get window id of

		Iterator itererator = (Iterator) windowId.iterator();
		mainWinID = (String) itererator.next();
		newAdwinID = (String) itererator.next();
		driver.switchTo().window(newAdwinID);

		System.out.println("Switch Window");
		Thread.sleep(2000);
	}
	public void previousPage(){
		driver.navigate().back();
		
	}
	
	public void setTexttoAlert(String value) {
		//Alert alert = driver.switchTo().alert();
		//alert.sendKeys(value);
		//alert.accept();
		driver.switchTo().alert().sendKeys(value);
		driver.switchTo().alert().accept();

	}
	protected void sleep(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
	public void maximizeScreen() {
		driver.manage().window().maximize();
	}
	
	protected void click(String key, String value) {
		WebElement element = find(key, value);
		if (null != element) {
			element.click();
		}
	}
	
	

	
	protected void explicitWaitByxpath( String value) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(value)));
	}
	protected void explicitWaitByxpathclickable( String value) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(value)));
	}
	
	protected void tabOut() {
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
	}
	

	protected boolean isEnabled(String key, String value) {

		WebElement element = find(key, value);
		boolean visible = false;
		if (null != element) {
			visible = element.isEnabled();
		}
		return visible;
	}
	
	protected void doubleClick(String key, String value) {
		WebElement element = find(key, value);
		if (null != element) {
			Actions action = new Actions(driver);
			action.doubleClick(element).perform();
		}
	}
	
	protected void setValue(String key, String value, String enteredValue) {
		WebElement element = find(key, value);
		if (null != element) {
			element.click();
			element.clear();
			element.sendKeys(enteredValue);
		}
	}
	
	public void dropDownByIndex(String key, String value, int indexval) {
		// TODO Auto-generated method stub
		WebElement element = find(key, value);
		System.out.println("check 1");
		Select dropdown = new Select(element);
		System.out.println("check 2");
		dropdown.selectByIndex(indexval);
		System.out.println("check 3");
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)","");
		System.out.println("scroll");
	}
	
	public String getText(String key, String value) {
		// TODO Auto-generated method stub
		WebElement element = find(key, value);
		String text = null;
		if (null != element) {
			text = element.getText();
		}
		return text;
	}

	public void backspace(String key, String value) {
		// TODO Auto-generated method stub
		WebElement element = find(key, value);
		if (null != element) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	protected void clear(String key, String value) {
		WebElement element = find(key, value);
		if (null != element) {
			element.clear();
		}
	}

	protected String getAttributeTitle(String key, String value) {
		WebElement element = find(key, value);
		if (null != element) {
			value = element.getAttribute("title");
		}
		return value;
	}

	protected boolean isVisible(String key, String value) {
		boolean visible = false;
		try{
		WebElement element = find(key, value);
//		boolean visible = false;
		if (null != element) {
			visible = element.isDisplayed();
		}
//		return visible;
		}catch(NoSuchElementException e){
			
		}
		return visible;
	}
	
	public void hoveronElement(String key, String value) throws InterruptedException {
		WebElement element = find(key, value);
		if (null != element) {
			
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			// Give wait for 2 seconds 
			Thread.sleep(2000);
			/*
			System.out.println("Hover 3");
			// finally click on that element
			action.click(element).build().perform();
			System.out.println("Hover 4");
			*/
		}
	}
	
	public boolean isElementSelected(String key, String value) {
		WebElement element = find(key, value);
		if (element.isSelected()) {
			return true;
		}
		return false;
	}
	
	protected String readFromInput(String key, String value) {
		WebElement element = find(key, value);
		String returningvalue = null;
		if (null != element) {
			returningvalue = element.getAttribute("value");
		}
		return returningvalue;
	}
	
	public boolean existsElement(String key, String value) {
		WebElement element = null;
		try {
			element = find(key, value);
			if (element != null)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	protected WebElement find(String key, String value) {

		if (ElementConstants.ID.equalsIgnoreCase(key)) {
			return driver.findElement(By.id(value));
		}
		if (ElementConstants.XPATH.equalsIgnoreCase(key)) {
			return driver.findElement(By.xpath(value));
		}
		if (ElementConstants.NAME.equalsIgnoreCase(key)) {
			return driver.findElement(By.name(value));
		}

		if (ElementConstants.CLASS_NAME.equalsIgnoreCase(key)) {
			return driver.findElement(By.cssSelector(value));
		}
		if (ElementConstants.CSS_NAME.equalsIgnoreCase(key)) {
			return driver.findElement(By.tagName(value));
		}
		if (ElementConstants.LINK_TEXT.equalsIgnoreCase(key)) {
			return driver.findElement(By.linkText(value));
		}
		if (ElementConstants.PARTIAL_LINK_TEXT.equalsIgnoreCase(key)) {
			return driver.findElement(By.partialLinkText(value));
		}
		System.out.println("BD  FIND -- IF not matchin");
		return null;		
	}
	//added on 20-07-2020
	protected List<WebElement> findElements(String key, String value) {

		if (ElementConstants.ID.equalsIgnoreCase(key)) {
			return driver.findElements(By.id(value));
		}
		if (ElementConstants.XPATH.equalsIgnoreCase(key)) {
			return driver.findElements(By.xpath(value));
		}
		if (ElementConstants.NAME.equalsIgnoreCase(key)) {
			return driver.findElements(By.name(value));
		}

		if (ElementConstants.CLASS_NAME.equalsIgnoreCase(key)) {
			return driver.findElements(By.cssSelector(value));
		}
		if (ElementConstants.CSS_NAME.equalsIgnoreCase(key)) {
			return driver.findElements(By.tagName(value));
		}
		if (ElementConstants.LINK_TEXT.equalsIgnoreCase(key)) {
			return driver.findElements(By.linkText(value));
		}
		if (ElementConstants.PARTIAL_LINK_TEXT.equalsIgnoreCase(key)) {
			return driver.findElements(By.partialLinkText(value));
		}
		System.out.println("BD  FIND -- IF not matchin");
		return null;		
	}
	//ended on 20-07-2020
	protected void explicitWaitByID(String key, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		if (value.equals("xpath")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
		}else{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
		}
	}

	public String getcurrenturl() {
		return driver.getCurrentUrl();
	}

	public void url(String url) {
		driver.get(url);
	}

	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void declineAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void ifAlertCloseAlert() {
		try {
			if (driver.switchTo().alert() != null) {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		} catch (Exception e) {
		}
	}

	public void waitByXPATH(String key, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
	}

	public void sendEnter() {
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
	}

	public void sendDown(int n) {
		for (int i = 0; i < n; i++) {
			driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
		}
	}

	public void sendRight(int n) {
		for (int i = 0; i < n; i++) {
			driver.switchTo().activeElement().sendKeys(Keys.ARROW_RIGHT);
		}
	}

	public void tearDown() {
		driver.quit();
	}
	
	public void refresh(){
		driver.navigate().refresh();
	}

	public void changeToTab(String frameid) {
		driver.switchTo().frame(frameid); 
	}

	public void clickTableCheckBox(String item) {
		driver.findElement(By.xpath(item)).click();
	}
	
	public int getTableRowCount(String item) {
		int rowCount = driver.findElements(By.xpath(item)).size();
		return rowCount;
	}
	
	
	protected String getElementsbyCSSandClick(String item, String srchTxt) {	
		
		System.out.println("START CSS CLICK - SrchTxt:" + srchTxt + "|Item:" + item );			
		String retMessage = ""; 	
		
		List<WebElement> elmList = driver.findElements(By.cssSelector(item));			
		//System.out.println("elmList:" + elmList);
		for (WebElement strTemp : elmList){	
			System.out.println("srchTxt:" + srchTxt + "|strTemp:" + strTemp.getText());		
			if (srchTxt.equals("")){
				strTemp.click();
				System.out.println("CLICKED");
				return "SUCCESS";
			}
			if (strTemp.getText().equals(srchTxt)){
				strTemp.click();
				System.out.println("CLICKED");
				return "SUCCESS";
			}
		}
		//driver.findElement(By.cssSelector(item)).click();
		//System.out.println("CLICKED SINGLE ITEM");
		return "FAIL";
	}

	protected String getElementbyCSSandClick(String item, String srchTxt) {	
		
		System.out.println("START getelement - SrchTxt:" + srchTxt + "|Item:" + item );			
		String retMessage = ""; 	
		
		WebElement elmList = driver.findElement(By.cssSelector(item));			
		//System.out.println("elmList:" + elmList);
		elmList.click();
		System.out.println("getElementbyCSSandClick - CLICKED SINGLE ITEM");
		return "SUCCESS";
	}

	protected List<String> getElementsbyCSSandreturnText(String item, String srchTxt) {	
		
		System.out.println("START of getElementsbyCSSandreturnText - SrchTxt:" + srchTxt + "|Item:" + item );			
		List<String> txtList=new ArrayList<String>();
		List<WebElement> elmList = driver.findElements(By.cssSelector(item));			
		//System.out.println("elmList:" + elmList);
		for (WebElement strTemp : elmList){	
			System.out.println("srchTxt:" + srchTxt + "|strTemp:" + strTemp.getText());		
			txtList.add(strTemp.getText());		
		}
		return txtList;
	}	
	protected List<String>  tableread(String path) {
		WebElement Table = driver.findElement(By.ByClassName.className("table-responsive"));
		WebElement cell = driver.findElement(By.xpath(path));
		List < WebElement > rows_table = driver.findElements(By.tagName("tr"));
		List<String> table=new ArrayList<String>();
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		//Loop will execute for all the rows of the table
		String celltext=null;
		for (int row = 0; row < rows_count; row++) {
			//To locate columns(cells) of that specific row.
			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();
			//System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				//To retrieve text from the cells.
				 celltext = Columns_row.get(column).getText();
				//System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
				 table.add(celltext);
				}
			}
		return table;//rose change return type from null to String list
	}
	
	//extra added code updated on 13-03-2020 ajay
	protected List<List<String>> tableread1(String path) {
		WebElement Table = driver.findElement(By.ByClassName.className("table-responsive"));
		WebElement cell = driver.findElement(By.xpath(path));
		int numOfRow =Table.findElements(By.tagName("tr")).size();
		//System.out.println(numOfRow);
		int numOfCells = cell.findElements(By.tagName("td")).size();
		int numofcol=numOfCells/numOfRow;
		//System.out.println(numofcol);
		int col1=0;
		int row1=0;
		//System.out.println(numOfRow+"   ******** "+numofcol);
		List < WebElement > rows_table = driver.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		//System.out.println(rows_count);
	
		List<String>t=new ArrayList<>();
		//Loop will execute for all the rows of the table
		for (int row = 0; row < rows_count; row++) {
			//To locate columns(cells) of that specific row.
			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			//To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();
			//System.out.println(columns_count);
			row1++;
			//System.out.println("Number of cells In Row " + row + " are " + columns_count);
			for (int column = 0; column < columns_count; column++) {
				//To retrieve text from the cells.
				String celltext = Columns_row.get(column).getText();
				col1++;
				t.add(celltext);
			
			}
		}
		System.out.println("size :"+t.size());
		System.out.println(t);
		List<List<String>> tempList=new ArrayList<>();
		for (int j = 0; j <row1-1 ; j++) {
			for (int i = 0; i<col1-1; i+=8) {
				//System.out.println(t.get(i));
				tempList.add(Arrays.asList(t.get(i),t.get(i+1),t.get(i+2),t.get(i+3),t.get(i+4),t.get(i+5),t.get(i+6)));
				}
			}

		
		return tempList;
	}
	public void search(List<String> list){
		for (int i = 0; i < list.size(); i++) {
			WebElement search=driver.findElement(By.name("search"));
			
			search.sendKeys(list.get(i));
			WebElement lastseen=driver.findElement(By.xpath("/html/body/app-root/div/div/div/app-people/div/div/div[2]/div/div[2]/div/div[2]/div[2]/ul/li[1]/span"));
			String last=lastseen.getText();			
		}
	}
	// function to check whether the current time is in between start time and end time or current time is equal to start time or end time
	protected boolean timeCompare(String curTime,String startTime,String endTime){
		
		LocalTime targetTime = LocalTime.parse(curTime) ;
		if( ( targetTime.isAfter(LocalTime.parse( startTime ) ) || ( targetTime.equals(LocalTime.parse( startTime ) ) ) ) && ( targetTime.isBefore(LocalTime.parse( endTime ) ) ) || ( targetTime.equals ( LocalTime.parse( endTime ) ) ) ) {
			return true;
		}
		else{
			return false;
		}

	}
	// function to check whether the current time comes after end time 
	protected boolean timeCompareAfter(String curTime,String startTime,String endTime){
		
		LocalTime targetTime = LocalTime.parse(curTime) ;
		if(targetTime.isAfter(LocalTime.parse( endTime ))|| ( targetTime.equals ( LocalTime.parse( endTime ) ) )){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	protected List<Integer> capCount(){
		int whiteCap=driver.findElements(By.cssSelector("[style='color: rgb(242, 243, 245);']")).size();
		int yellowCap=driver.findElements(By.cssSelector("[style='color: rgb(255, 195, 11);']")).size();
		int blueCap=driver.findElements(By.cssSelector("[style='color: rgb(116, 185, 255);']")).size();
		List<Integer>list=new ArrayList<>();
		list.add(whiteCap);
		list.add(yellowCap);
		list.add(blueCap);
		//System.out.println(list);		
		return list;
				
	}
	//ajay ended code
	//updated code ends 13-03-2020
	
	//ajay soc  19-06-2020
	public List<WebElement> dropselect(String key, String value){
//		WebElement element = find(key, value);
		Select select = new Select(find(key, value));
		List<WebElement> option= select.getOptions(); 
//		System.out.println(option.toString());
        return option;
        
		}
	//ajay eoc 19-06-2020
	
	//ajay soc 01-07-2020
	public int dropsize(String key, String value){
		Select select = new Select(find(key, value));
		List<WebElement> option= select.getOptions(); 
		return option.size();
	}
	public WebElement path(String key, String value){
		WebElement w=find(key, value);
		return w;
	}
	public String version(){
		driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[2]/div/a/i")).click();
		String version=driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[2]/div/ul/li/div/label")).getText();
		driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[2]/div/a/i")).click();
		return version;
	}
	public int getRowCount(String item) {
		int rowCount = driver.findElements(By.xpath(item)).size();
		return rowCount;
	}
	public List tableData(String key,String value){
		
		WebElement table=find(key, value);
        List <WebElement>row=table.findElements(By.tagName("tr"));
//        int rows_count=row.size();
//        for (int i = 0; i<rows_count; i++) {
//		coloumns=row.get(i).findElements(By.tagName("td"));
//		}
        return row;
      }
	public int drpdwnwoselect(String key,String value){
		//dropdown with out select
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='hideMenu']/app-home/div[3]/div[1]/div/div/ul/li"));
		//xpath for the div
//		int i=0;
		int alert_count_int=0;
		for ( int i = 2;i <=list.size(); i++) {
			find(key,value).click();
			sleep(3000);
			WebElement e=driver.findElement(By.xpath("//*[@id='hideMenu']/app-home/div[3]/div[1]/div/div/ul/li["+i+"]"));
            e.click();
//            explicitWaitByxpath("//*[@id='hideMenu']/app-home/div[3]/div[2]/table/thead");
            try {
				Thread.sleep(8000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            String alert_count=driver.findElement(By.xpath("//*[@id='hideMenu']/app-home/div[3]/div[1]/a/span/span[1]")).getText();
            alert_count_int=alert_count_int+Integer.parseInt(alert_count);
            System.out.println(alert_count_int);
            
		}
		//for clicking the first main option
		find(key,value).click();
		driver.findElement(By.xpath("//*[@id='hideMenu']/app-home/div[3]/div[1]/div/div/ul/li[1]")).click();
//		System.out.println(flag);
		sleep(5000);
		return alert_count_int;
	}
	public List<WebElement> getelements(String key,String value){
		List<WebElement>list=findElements(key, value);
		return list;
		
	}
	public String getcolor(String key,String value){
		String color=find(key, value).getCssValue("background-color");
		System.out.println(color);
		String hexcolor=Color.fromString(color).asHex();
		System.out.println(hexcolor);
		return hexcolor;
	}
	public int filteractiveCount(String key,String value){
		 int count=0;
 		 List<WebElement>options=dropselect( key, value);
         for (int j = 1; j < options.size(); j++) {
		   String filt_options=options.get(j).getText();
		   String filt_options_arr[]=filt_options.split("-");
		   if(filt_options_arr.length==2){
		   String filt_options_trim=filt_options_arr[1].trim();
		   count=count+Integer.parseInt(filt_options_trim);
		   }
		   else{
		   String filt_options_trim=filt_options_arr[2].trim();
		   count=count+Integer.parseInt(filt_options_trim);
		   }
 		 }
 		 return count;
	}
	public void coreRefresh(){
		Actions act =new Actions(driver);
		act.keyDown(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
	}
	//contractor tiles count
	public int tilescontrcountPagination() throws InterruptedException{
		List<WebElement>list=driver.findElements(By.cssSelector("#hideMenu > app-contractor > div > ul > li.page-item.number-item"));
		System.out.println(list.size());
		int count =0;
		for(int i=3;i<list.size()+3;i++){
			driver.findElement(By.xpath("//*[@id='hideMenu']/app-contractor/div/ul/li["+i+"]/a")).click();
			Thread.sleep(3000);
			List<WebElement>tiles_count=driver.findElements(By.xpath("//*[@id='hideMenu']/app-contractor/div/div/div"));
			System.out.println("list size"+tiles_count.size());
			count =count+tiles_count.size();
	    }
		driver.findElement(By.xpath("//*[@id='hideMenu']/app-contractor/div/ul/li[3]/a")).click();
		Thread.sleep(3000);
		return count;
	}
	public void sentText(String text,String key,String value){
		WebElement element = find(key, value);
		element.sendKeys(text);
		
	}
	public String title(String key,String value){
		String title=find(key, value).getAttribute("title");
		return title;
	}
	protected void clickOverlap( String value) {
//		 WebElement element = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(value))); 
		 WebElement element=driver.findElement(By.xpath(value));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	public void peoplePagination(int count) throws InterruptedException{
		
		if(driver.findElement(By.xpath("//*[@id='hideMenu']/app-people/div/div/div[2]/ul")).isDisplayed()){
		List<WebElement>list=driver.findElements(By.cssSelector("#hideMenu > app-people > div > div > div.table-responsive > ul > li.page-item.number-item"));
//			List<WebElement>list=driver.findElements(By.xpath("//*[@id='hideMenu']/app-people/div/div/div[2]/ul/li/a"));
		System.out.println("list"+list.size());
		if(list.size()>0){
			int int_rand=(int)(Math.random()*((list.size()+2-3)+1)+3);
			System.out.println("rand"+int_rand);
			driver.findElement(By.xpath("//*[@id='hideMenu']/app-people/div/div/div[2]/ul/li["+int_rand+"]/a")).click();
			sleep(6000);
		}
		}
	}
		public void devicePagination() throws InterruptedException{
			
			if(driver.findElement(By.xpath("//*[@id='dynamic']/div/div[3]/ul")).isDisplayed()){
			List<WebElement>list=driver.findElements(By.cssSelector("#dynamic > div > div.p-2.table-responsive.bg-white.h-100.w-100 > ul >li.page-item.number-item> a"));
			System.out.println(list.size());
			int int_rand=0;
			if(list.size()>0){
				 int_rand=(int)(Math.random()*(list.size()-4+1)+4);//min-4 ,max -size of list , min is 4 because pagination starts from 3
				System.out.println("rand"+int_rand);
				driver.findElement(By.cssSelector("#dynamic > div > div.p-2.table-responsive.bg-white.h-100.w-100 > ul >li.page-item.number-item:nth-child("+int_rand+")> a")).click();
				sleep(6000);
			}
			}
		}
		public String[] settingsTime(){
			String str="Not Available";
			String ehs="Not Available";
			String navisafe="Not Available";
			String aggrq="Not Available";
			String aggrd="Not Available";
			String reprt_jb="Not Available";
			
		    String time_array[]=new String[6];
			List<String>time=null;
			List<WebElement> rows=driver.findElements(By.xpath("//*[@id='other']/div[3]/table/tbody/tr"));
			System.out.println("sync table size "+rows.size());
			
			explicitWaitByxpath("//*[@id='other']/div[3]/table/tbody/tr[1]/td[1]");
			if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr[1]/td[1]")).isDisplayed())
			{
			System.out.println(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr[1]/td[1]")).getText());
			
			for (int i = 1; i <=rows.size(); i++) {
				if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("STR"))
				{
					str=driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
					System.out.println(str+" str");					
					break;
			    }
			}
			for (int i = 1; i <=rows.size(); i++) {
				if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("EHS")){
					ehs=driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
					System.out.println(ehs+" ehs");
					break;
				}
			}
            for (int i = 1; i <=rows.size(); i++) {
				if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("NaviSafe")){
					navisafe=driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
					System.out.println(navisafe+" navisafe");
					break;
				}
				
			}
			for (int i = 1; i <=rows.size(); i++) {
				if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("AggrQ")){
					aggrq=driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
					System.out.println(aggrq+" aggrq");
					break;
				}
				
			}
			for (int i = 1; i <=rows.size(); i++) {
				if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("AggrD")){
					aggrd=driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
					System.out.println(aggrd+" aggrd");
					break;
				}
				
			}
			for (int i = 1; i <=rows.size(); i++) {
				if(driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Report-Job")){
					reprt_jb=driver.findElement(By.xpath("//*[@id='other']/div[3]/table/tbody/tr["+i+"]/td[3]")).getText();
					System.out.println(reprt_jb+" report_job");
					break;
				}
				
			}
//			if(str.){
//				
//			}
			
			time_array[0]=str;
			time_array[1]=ehs;
			time_array[2]=navisafe;
			time_array[3]=aggrq;
			time_array[4]=aggrd;
			time_array[5]=reprt_jb;

			return time_array;
		}
			else{
				time_array[0]=str;
				time_array[1]=ehs;
				time_array[2]=navisafe;
				time_array[3]=aggrq;
				time_array[4]=aggrd;
				time_array[5]=reprt_jb;
				return time_array;
			}
		}
	
		
		
		
		
//		Random rand=new Random();
		
		
	
	

}




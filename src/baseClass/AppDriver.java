package baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//
//import com.thoughtworks.selenium.webdriven.commands.GetAttribute;

public class AppDriver {
	public static BaseDriver base;
	static Properties prop;
	
	public void sleep(int value) {
		base.sleep(value);
	}

	public void switchbacktoMainWindow() throws InterruptedException {
		base.switchbacktoMainWindow();
	}
	public void switchwindow() throws InterruptedException {
		base.switchwindow();
	}
	public void previouspage() {
		base.previousPage();
	}

	public AppDriver(String propFileName) {
		prop = new Properties();
		setUp(propFileName);
	}
	
	public AppDriver() {
		// TODO Auto-generated constructor stub
	}

	public void setUp(String propFileName) {
		try {
			prop.load(new FileInputStream(propFileName));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String getElement(String element) {
		//System.out.println("element:" + element); //delete later
		return prop.getProperty(element);
	}
//	public static String getElement(WebElement element) {
//		//System.out.println("element:" + element); //delete later
//		return prop.getProperty(element);
//	}
	
	
	
	
	public String ReplaceSplCharbyWhiteSpace(String strTemp){
		//Convert space > . ( ) / | ' - to null 
		strTemp = strTemp.replaceAll("\\s","");	
		strTemp = strTemp.replaceAll("[>]","");					
		strTemp = strTemp.replaceAll("[.]","");					
		strTemp = strTemp.replaceAll("[/]","");
		strTemp = strTemp.replaceAll("[']","");
		strTemp = strTemp.replaceAll("[(]","");
		strTemp = strTemp.replaceAll("[)]","");
		strTemp = strTemp.replaceAll("[|]","");
		strTemp = strTemp.replaceAll("[-]","");
		return strTemp;
	}	
	
	public String SearchHMandClick(Map<String, String> hmXpath, String strIdent) {
		//System.out.println(strIdent);
		if (hmXpath.containsKey(strIdent)){ 
			sleep(500);
			String identifier = hmXpath.get(strIdent); 
		    //System.out.println("value for key :" + strIdent + "-" + identifier); 
		    String xpath_ident = "xpath" + "," + identifier;
			click(xpath_ident);
			return "PASS";
		}
		System.out.println("Key Not Found");
		return "FAIL";		 
	}
	
	public void deleteFile(String Filepath) {
		// Deleting the FromSeleniumFile
		base.deleteFile(Filepath);
	}

	public void creatingFile(String Filepath, String data) throws IOException {
		// Deleting the FromSeleniumFile
		base.creatingFile(Filepath, data);
	}
	
	public String SearchHMGetText(Map<String, String> hmXpath, String strIdent) {
		//System.out.println(strIdent);
		if (hmXpath.containsKey(strIdent)){ 
			sleep(500);
			String identifier = hmXpath.get(strIdent); 
		    //System.out.println("value for key :" + strIdent + "-" + identifier); 
		    String xpath_ident = "xpath" + "," + identifier;
			String strText =  getText(xpath_ident);
			return strText;
		}
		System.out.println("Key Not Found");
		return "FAIL";		 
	}
	
	public boolean SearchHMexistsElement(Map<String, String> hmXpath, String strIdent) {
		//System.out.println(strIdent);
		if (hmXpath.containsKey(strIdent)){ 
			sleep(500);
			String identifier = hmXpath.get(strIdent); 
		    //System.out.println("value for key :" + strIdent + "-" + identifier); 
		    String xpath_ident = "xpath" + "," + identifier;
			return existsElement(xpath_ident);
		}
		System.out.println("Key Not Found");
		return false;		 
	}
	
	public String SearchHMandSelDrop(Map<String, String> hmXpath, String strIdent, int indexval) {
		//System.out.println(strIdent);
		if (hmXpath.containsKey(strIdent)){ 
			sleep(500);
			String identifier = hmXpath.get(strIdent); 
		    //System.out.println("value for key :" + strIdent + "-" + identifier); 
		    String xpath_ident = "xpath" + "," + identifier;
		    dropDownByIndex(xpath_ident,indexval);
			return "PASS";
		}
		System.out.println("Key Not Found");
		return "FAIL";		 
	}
	
	public List<String> SearchList(String Login_ID, List<List<String>> listLogin) {

		List<String> strLogin = new ArrayList<String>();
		//System.out.println("Login_ID AM :" + Login_ID);
		for (List<String> strLogin1 : listLogin){
			if (strLogin1.get(6).equalsIgnoreCase(Login_ID)) {
				return strLogin1;
			}
		}
		strLogin.add("FAIL");
		return strLogin;
		 
	}
	
	public String BrowserLaunch(String url) throws InterruptedException, IOException {
		System.out.println("Start of Browser Launch");
		base = new BaseDriver();
							
		url(url);
		maximizeScreen();
		sleep(5000);	
		
		//String urlz = getcurrenturl();		
		//System.out.println("AD - Check8 URL: " + urlz);
		//if(urlz.substring(0,25).equals("https://login.zscaler.net")){
												  
		//	System.out.println("AD Zcalar going to click - Check9");
		//	click(getElement(ElementConstants.DELZSCALAR));
		//	Thread.sleep(3000);							
		//}
		//String Timehhmmss2 = amzDemo.getCurrentTimeStamp();
		
		System.out.println("browserlaunched");
		return "True";
	}
	
	public void scrollDown() {
		base.scrollDown();
	}
	
	public void setTexttoAlert(String enteredValue) {
		base.setTexttoAlert(enteredValue);
	}
			
	public void maximizeScreen() {
		base.maximizeScreen();
	}
	
	public void click(String identifier) {
		base.click(identifier.split(",")[0], identifier.split(",")[1]);
	}
	
	public void tabOut() {
		base.tabOut();
	}
	
	public boolean isEnabled(String identifier) {
		return base.isEnabled(identifier.split(",")[0],
				identifier.split(",")[1]);
	}
	
	public void doubleClick(String identifier) {
		base.doubleClick(identifier.split(",")[0], identifier.split(",")[1]);
	}
	
	public void setValue(String identifier, String enteredValue) {
		base.setValue(identifier.split(",")[0], identifier.split(",")[1],
				enteredValue);
	}
	
	public void dropDownByIndex(String identifier, int indexval) {
		base.dropDownByIndex(identifier.split(",")[0],
				identifier.split(",")[1], indexval);
	}
	
	public String getText(String identifier) {
		return base.getText(identifier.split(",")[0], identifier.split(",")[1]);
	}
	
	public void backspace(String identifier) {
		base.backspace(identifier.split(",")[0], identifier.split(",")[1]);
	}
	
	public void clear(String identifier) {
		base.clear(identifier.split(",")[0], identifier.split(",")[1]);
	}
	
	public String getAttributeTitle(String identifier) {
		return base.getAttributeTitle(identifier.split(",")[0],
				identifier.split(",")[1]);
	}
	
	public boolean isVisible(String identifier) {
		return base.isVisible(identifier.split(",")[0],
				identifier.split(",")[1]);
	}
	
	public void hoveronElement(String identifier) throws InterruptedException {
		base.hoveronElement(identifier.split(",")[0], identifier.split(",")[1]);
	}
	public int getRowcount(String identifier){
		return base.getRowCount(identifier.split(",")[1]);
	}
	
	public boolean isElementSelected(String identifier) {
		return base.isElementSelected(identifier.split(",")[0],
				identifier.split(",")[1]);
	}
	
	public String readFromInput(String identifier) {
		return base.readFromInput(identifier.split(",")[0],
				identifier.split(",")[1]);

	}
	
	public boolean existsElement(String identifier) {
		return base.existsElement(identifier.split(",")[0],
				identifier.split(",")[1]);
	}

	public void explicitWaitByID(String identifier) {
		base.explicitWaitByID(identifier.split(",")[0],
				identifier.split(",")[1]);
	}
		
	public String getcurrenturl() {
		return base.getcurrenturl();
	}
	
	public void url(String url) {
		base.url(url);
	}
	
	public void acceptAlert() {
		base.acceptAlert();
	}
	
	public void declineAlert() {
		base.declineAlert();
	}
	
	public void ifAlertCloseAlert() {
		base.ifAlertCloseAlert();
	}	
	
	public void explicitWaitByxpath(String identifier) {
		base.explicitWaitByxpath(identifier.split(",")[1]);
	}
	public void explicitWaitByxpathclickable(String identifier) {
		base.explicitWaitByxpathclickable(identifier.split(",")[1]);
	}
	public void clickOverlap(String identifier) {
		base.clickOverlap(identifier.split(",")[1]);
	}
	
	
	public void sendEnter() {
		base.sendEnter();
	}
	
	public void sendDown(int n) {
		base.sendDown(n);
	}

	public void sendRight(int n) {
		base.sendRight(n);
	}	
	
	public void tearDown() {
		base.tearDown();
		System.out
				.println("******************************************************************");

	}
	public void refresh(){
		base.refresh();
	}
	
	public void changeToTab(String frameid) {
		base.changeToTab(frameid);
	}
	
	public void clickTableCheckBox(String item) {
		base.clickTableCheckBox(item);
	}
	
	public int getTableRowCount(String item) {
		return base.getTableRowCount(item);
	}
	
	public String getElementsbyCSSandClick(String item, String srchTxt) {
		return base.getElementsbyCSSandClick(item, srchTxt);
	}
	public String getElementbyCSSandClick(String item, String srchTxt){
		return base.getElementbyCSSandClick(item, srchTxt);
	}
	public List<String> getElementsbyCSSandreturnText(String item, String srchTxt){
		return base.getElementsbyCSSandreturnText(item, srchTxt);
	}

	//Rose: updated return type void to String list
	public List<String> table(String path) {
		return base.tableread(path);
	}
	//rose
	
	//Ajay:  code updated on 13-03-2020 
	public List<List<String>> table1(String path) {
		return base.tableread1(path);
	}
	//Ajay: updated code ends
	
	
	//Ajay added code on 17-03-2020
	public List<Integer> count(){
		return base.capCount();
				
	}
	//ajay ended code
	
	//Akshaya
	public boolean compareTime(String curTime,String startTime,String endTime){
		return base.timeCompare(curTime,startTime,endTime);
	}
	
	//Akshaya 12/03/2020 
	public boolean compareTimeAfter(String curTime,String startTime,String endTime){
		return base.timeCompareAfter(curTime,startTime,endTime);
	}
	//ajay 19-06-2020
	public List<WebElement> dropselect(String identifier){
		return base.dropselect(identifier.split(",")[0],identifier.split(",")[1]);
	}
	//eoc ajay
	
	//ajay 01-07-2020
	public int dropsize(String identifier){
			return base.dropsize(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public WebElement path(String identifier){
		return base.path(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public String version(){
		return base.version();
	}
	public List tableData(String identifier){
		return base.tableData(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public int drpdownwoselect(String identifier){
		return base.drpdwnwoselect(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public List<WebElement> getelements(String identifier){
		return base.getelements(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public String getcolor(String identifier){
		return base.getcolor(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public int filteractiveCount(String identifier){
		return base.filteractiveCount(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public void coreRefresh(){
		 base.coreRefresh();
	}
	public  int timeconversion(String a[]){
    	int x=Integer.parseInt(a[0])*60;
    	int y=Integer.parseInt(a[2]);
    	int sum=x+y;
    	System.out.println("After sum" +sum);
    	return sum;
    }
	public static String[] splitTimeString(String t){
		   String timeArray[]=t.split(" ");   
		   return timeArray;
	}
	public int tilescontrcountPagination() throws InterruptedException{
		return base.tilescontrcountPagination();
	}
	public void sentText(String text,String identifier){
		 base.sentText(text,identifier.split(",")[0],identifier.split(",")[1]);
	}
	public String title(String identifier){
		return base.title(identifier.split(",")[0],identifier.split(",")[1]);
	}
	public void peoplePagination(int count) throws InterruptedException{
		 base.peoplePagination( count);
	}
	public void devicePagination() throws InterruptedException{
		 base.devicePagination( );
	}
	public  String[] settingsTime() throws InterruptedException{
		 return base.settingsTime();
	}
	    
	
	
}

package cwModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import constants.ElementConstants;

public class CWDevice extends AppDriver{
	AppDriver ApDrv=new AppDriver();
	static String propFileName = "C:\\Users\\148972\\Downloads\\CWAutomation\\CWAutomation\\src\\elements\\Locators.properties";
	public static final int TIME_01 = 1000;
	public static final int TIME_02 = 2000;
	public static final int TIME_03 = 3000;
	public static final int TIME_04 = 4000;
	public static final int TIME_05 = 5000;
	public static final int TIME_07 = 7000;
	public static final int TIME_08 = 8000;
	public static final int TIME_10 = 10000;
	public static final int TIME_15 = 15000;
	public static final int TIME_20 = 20000;
	public static final int TIME_25 = 25000;
	public static final int TIME_30 = 30000;
	
	public CWDevice()throws IOException, InterruptedException {
		super(propFileName);
	}
	public List<String> device_sidenav() throws InterruptedException{
		List<String> device_count_list= new ArrayList<String>();
		System.out.println("START - 7. device_SideNav");
		click(getElement(ElementConstants.DEVSIDENAV));
		Thread.sleep(TIME_04);
		Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
		List<WebElement>plt_options=plt_drp_options_01.getOptions();
		plt_drp_options_01.selectByIndex(0);
		explicitWaitByxpath(getElement(ElementConstants.DEV_DYNAMIC_TAB));
		String tab_name_1=getText(getElement(ElementConstants.DEV_DYNAMIC_TAB));
		System.out.println("name"+tab_name_1);
		if(tab_name_1.equalsIgnoreCase("dynamic")){
			device_count_list.add("Pass");//413 dynamic tab
		}
		else{
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_ALLCOUNT))){
			device_count_list.add("Pass");//414 dynamic tab-all count
		}
		else{
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_UNALLCOUNT))){
			device_count_list.add("Pass");//415 dynamic tab-unallocated count
			System.out.println("pass");
		}
		else{
			device_count_list.add("Failed");
		}
		Select dev_drp_01=new Select(path(getElement(ElementConstants.DEV_DRPDWN)));
		List<WebElement> dev_drp_options=dev_drp_01.getOptions();
		List<String> dev_drp_options_string=new ArrayList<>();
		for (WebElement string : dev_drp_options) {
			dev_drp_options_string.add(string.getText());
		}
		List<String> dev_drp_options_val=Arrays.asList("Serial Number","Device Name");
		if(dev_drp_options_string.containsAll(dev_drp_options_val)){
			device_count_list.add("Pass");//416 dynamic tab-dropdown
		}
		else{
			device_count_list.add("Failed");
		}
		explicitWaitByxpath(getElement(ElementConstants.DEV_SERNAME_FIR));
		String serName_01=getText(getElement(ElementConstants.DEV_SERNAME_FIR));
		System.out.println(serName_01);
		sentText(serName_01.trim(), getElement(ElementConstants.DEV_SEARCH_FIELD));
		click(getElement(ElementConstants.DEV_SEARCH_BTN));
		Thread.sleep(TIME_07);
		int flag=0;
		if(isVisible(getElement(ElementConstants.DEV_TABLEDATA_RES))){
		String serName_02=getText(getElement(ElementConstants.DEV_SERNAME_RES));
		
		System.out.println("ser name"+serName_01+","+serName_02);
		if(serName_01.equals(serName_02)){
			flag++;
		}
		}
		else{
			System.out.println("failed1");
		}
		click(getElement(ElementConstants.DEV_CLR_FIL));
		explicitWaitByxpath(getElement(ElementConstants.DEV_TABLE));
		for (WebElement string : dev_drp_options) {
			System.out.println(string.getText());
		}
		dev_drp_01.selectByVisibleText("Device Name");
		String devName_01=getText(getElement(ElementConstants.DEV_DEVNAME_FIR));
		sentText(devName_01.trim(), getElement(ElementConstants.DEV_SEARCH_FIELD));
		click(getElement(ElementConstants.DEV_SEARCH_BTN));
		Thread.sleep(TIME_07);
		if(isVisible(getElement(ElementConstants.DEV_TABLEDATA_RES))){
		String devName_02=getText(getElement(ElementConstants.DEV_DEVNAME_RES));
		System.out.println("dev name"+devName_01+devName_02);
		if(devName_01.equals(devName_02)){
			flag++;
		}
		}
		else{
			System.out.println("failed2");
		}
		
		System.out.println(flag);
		click(getElement(ElementConstants.DEV_CLR_FIL));
		sleep(TIME_04);
		if(flag==2){
			device_count_list.add("Pass");//417 search box
			device_count_list.add("Pass");//418 search functionality
		}
		else{
			device_count_list.add("Failed");
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_TYPE_DRPDWN))){
			device_count_list.add("Pass");//419 dropdown
		}
		else{
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_TABLE))){
			device_count_list.add("Pass");//420 tabular data
		}
		else{
			device_count_list.add("Failed");
		}
		List<WebElement>table_headers=getelements(getElement(ElementConstants.DEV_TAB_HEADERS));
		if(table_headers.get(0).getText().equalsIgnoreCase("Type")){
			device_count_list.add("Pass");//421 type
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(1).getText().equalsIgnoreCase("Device Name")){
			device_count_list.add("Pass");//422 device name
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(2).getText().equalsIgnoreCase("Serial Number")){
			device_count_list.add("Pass");//423 serial number
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(3).getText().equalsIgnoreCase("Site Name")){
			device_count_list.add("Pass");//424 site name
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(4).getText().equalsIgnoreCase("Employee Name")){
			device_count_list.add("Pass");//425 Employee name
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(5).getText().equalsIgnoreCase("Employee Id")){
			device_count_list.add("Pass");//426 employee id
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(6).getText().equalsIgnoreCase("Last Active")){
			device_count_list.add("Pass");//427 last active
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(7).getText().equalsIgnoreCase("Last Seen")){
			device_count_list.add("Pass");//428 last seen
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(8).getText().equalsIgnoreCase("Last Used By")){
			device_count_list.add("Pass");//429 last used by
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers.get(9).getText().equalsIgnoreCase("Battery %")){
			device_count_list.add("Pass");//430 battery
		}
		else{
			device_count_list.add("Failed");
		}
		device_count_list.add("Not Feasible");//431
		device_count_list.add("Not Feasible");//432
		device_count_list.add("Not Feasible");//433
		device_count_list.add("Not Feasible");//434
		device_count_list.add("Not Feasible");//435
		click(getElement(ElementConstants.DEV_STATIC_TAB));
		explicitWaitByxpath(getElement(ElementConstants.DEV_STAT_TABLE));
		if(isVisible(getElement(ElementConstants.DEV_STATIC_TAB))){
			device_count_list.add("Pass");//436 static
		}
		else{
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_STAT_ALLCOUNT))){
			device_count_list.add("Pass");//437 static tab-all count
		}
		else{
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_STAT_UNALLCOUNT))){
			device_count_list.add("Pass");//438 static tab-unallocated count
		}
		else{
			device_count_list.add("Failed");
		}
		Select dev_drp_02=new Select(path(getElement(ElementConstants.DEV_STAT_DRPDWN)));
		List<WebElement> dev_drp_options_02=dev_drp_02.getOptions();
		List<String> dev_drp_options_string_02=new ArrayList<>();
		for (WebElement string : dev_drp_options_02) {
			dev_drp_options_string_02.add(string.getText());
		}
		List<String> dev_drp_options_val_02=Arrays.asList("Serial Number","Device Name");
		if(dev_drp_options_string_02.containsAll(dev_drp_options_val_02)){
			device_count_list.add("Pass");//439 dynamic tab-dropdown
		}
		else{
			device_count_list.add("Failed");
		}
		String serName_03=getText(getElement(ElementConstants.DEV_STAT_SERNAME_FIR));
		sentText(serName_03.trim(), getElement(ElementConstants.DEV_STAT_SEARCH_FIELD));
		click(getElement(ElementConstants.DEV_STAT_SEARCH_BTN));
		Thread.sleep(TIME_07);
		int flag_01=0;
		if(isVisible(getElement(ElementConstants.DEV_STAT_TABLEDATA))){
		String serName_04=getText(getElement(ElementConstants.DEV_STAT_SERNAME_RES));
		
		if(serName_03.equals(serName_04)){
			flag_01++;
		}
		}
		click(getElement(ElementConstants.DEV_STAT_CLR_FIL));
		explicitWaitByxpath(getElement(ElementConstants.DEV_STAT_TABLE));
		for (WebElement string : dev_drp_options) {
			System.out.println(string.getText());
		}
		dev_drp_02.selectByVisibleText("Device Name");
		String devName_03=getText(getElement(ElementConstants.DEV_STAT_DEVNAME_FIR));
		sentText(devName_03.trim(), getElement(ElementConstants.DEV_STAT_SEARCH_FIELD));
		click(getElement(ElementConstants.DEV_STAT_SEARCH_BTN));
		Thread.sleep(TIME_07);
		if(isVisible(getElement(ElementConstants.DEV_STAT_TABLEDATA))){
		String devName_04=getText(getElement(ElementConstants.DEV_STAT_DEVNAME_RES));
		if(devName_03.equals(devName_04)){
			flag_01++;
		}
		}
		System.out.println(flag_01);
		click(getElement(ElementConstants.DEV_STAT_CLR_FIL));
		sleep(TIME_04);
		if(flag_01==2){
			device_count_list.add("Pass");//440 search box
			device_count_list.add("Pass");//441 search functionality
		}
		else{
			device_count_list.add("Failed");
			device_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.DEV_STAT_TYPE_DRPDWN))){
			device_count_list.add("Pass");//442 dropdown
		}
		else{
			device_count_list.add("Failed");
		}
		device_count_list.add("Not Feasible");//443 dropdown
		if(isVisible(getElement(ElementConstants.DEV_STAT_TABLE))){
			device_count_list.add("Pass");//444 tabular data
		}
		else{
			device_count_list.add("Failed");
		}
		List<WebElement>table_headers_02=getelements(getElement(ElementConstants.DEV_STAT_TAB_HEADERS));
		if(table_headers_02.get(0).getText().equalsIgnoreCase("Type")){
			device_count_list.add("Pass");//445 type
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers_02.get(1).getText().equalsIgnoreCase("Device Name")){
			device_count_list.add("Pass");//446 device name
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers_02.get(2).getText().equalsIgnoreCase("Serial Number")){
			device_count_list.add("Pass");//447 serial number
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers_02.get(3).getText().equalsIgnoreCase("Site Name")){
			device_count_list.add("Pass");//448 site name
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers_02.get(4).getText().equalsIgnoreCase("Level")){
			device_count_list.add("Pass");//449 Level
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers_02.get(5).getText().equalsIgnoreCase("Landmark")){
			device_count_list.add("Pass");//450 landmark
		}
		else{
			device_count_list.add("Failed");
		}
		if(table_headers_02.get(6).getText().equalsIgnoreCase("Coordinates (Lat,Long)")){
			device_count_list.add("Pass");//451 cordinates
		}
		else{
			device_count_list.add("Failed");
		}
		//452-459
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		device_count_list.add("Not Feasible");
		
		
		return device_count_list;
	}
		
	}
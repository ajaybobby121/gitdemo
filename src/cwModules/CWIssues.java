package cwModules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import constants.ElementConstants;

public class CWIssues extends AppDriver{
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
	
	public CWIssues()throws IOException, InterruptedException {
		super(propFileName);
	}
	public List<String> Issues_SideNav() throws InterruptedException{
		List<String> Issues_count_list= new ArrayList<String>();
		System.out.println("START - 6. Issues_SideNav");
		click(getElement(ElementConstants.ISSSIDENAV));
		Thread.sleep(TIME_04);
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String current_date= formatter.format(date);  
	    System.out.println(current_date);  
	    String datepicker_date=readFromInput(getElement(ElementConstants.ISSUES_CURRENT_DATE));
	    if(current_date.equals(datepicker_date)){
	    	Issues_count_list.add("Pass");//360 current date
	    }
	    else{
	    	Issues_count_list.add("Failed");
	    }
	    Issues_count_list.add("Not Feasible");//361 range selection
	    Issues_count_list.add("Not Feasible");//362 range selection
	    Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
	    List<WebElement>plt_drp_options=plt_drp_options_01.getOptions();
	    //for no data scenarios
	    String plant_name=null;
        int i=1;
        do{
        	plt_drp_options_01.selectByIndex(i);
        	Thread.sleep(TIME_03);
        	explicitWaitByxpath(getElement(ElementConstants.ISSUES_TABLE));
        	if(isVisible(getElement(ElementConstants.ISSUES_TABLEBODY)))
        	{ 
        		plant_name=getText(getElement(ElementConstants.ISSUES_TABLEPLANTNAME1));
        		break;
        	}
        	else{
        		i++;
        	}
        }while(i<4);
        if(plt_drp_options.get(i).getText().trim().equals(plant_name.trim())){
			Issues_count_list.add("Pass");//363 plant selection
		}
		else{
			Issues_count_list.add("Failed");
		}
        for (int j = 0; j<11; j++) {
        	Issues_count_list.add("Not Feasible");//364-374
		}
        if (isVisible(getElement(ElementConstants.ISSUES_TABLEBODY))) {
        	Issues_count_list.add("Pass");//375 alert list
		}
		else{
			Issues_count_list.add("Failed");
		}
        List<WebElement>table_headers=getelements(getElement(ElementConstants.ISSUES_HEADERS));
        for (WebElement webElement : table_headers) {
			System.out.println(webElement.getText());
		}
        
        List<WebElement>table_time_data=getelements(getElement(ElementConstants.ISSUES_TAB_TIME));
        List<WebElement>table_type_data=getelements(getElement(ElementConstants.ISSUES_TAB_TYPE));
        List<WebElement>table_emp_data=getelements(getElement(ElementConstants.ISSUES_TAB_EMP));
        List<WebElement>table_sec_data=getelements(getElement(ElementConstants.ISSUES_TAB_SECTION));
        List<WebElement>table_comp_data=getelements(getElement(ElementConstants.ISSUES_TAB_COMP));
        List<WebElement>table_zone_data=getelements(getElement(ElementConstants.ISSUES_TAB_ZONE));
        List<WebElement>table_proj_data=getelements(getElement(ElementConstants.ISSUES_TAB_PROJ));
        List<WebElement>table_ackby_data=getelements(getElement(ElementConstants.ISSUES_TAB_ACKBY));
        List<WebElement>table_acktime_data=getelements(getElement(ElementConstants.ISSUES_TAB_ACKTIME));
        List<WebElement>table_catg_data=getelements(getElement(ElementConstants.ISSUES_TAB_CATG));
        List<WebElement>table_act_data=getelements(getElement(ElementConstants.ISSUES_TAB_ACTION));
        if(table_headers.get(0).getText().equals("Time")&&table_time_data.size()==10){
        	Issues_count_list.add("Pass");//376 time
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(1).getText().equals("Type")&&table_type_data.size()==10){
        	Issues_count_list.add("Pass");//377 type
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(2).getText().equals("Employee")&&table_emp_data.size()==10){
        	Issues_count_list.add("Pass");//378 employee
		}
		else{
			Issues_count_list.add("Failed");
		}
        
        if(table_headers.get(3).getText().equals("Section")&&table_sec_data.size()==10){
        	Issues_count_list.add("Pass");//379 section
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(4).getText().equals("Company")&&table_comp_data.size()==10){
        	Issues_count_list.add("Pass");//380 company
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(5).getText().equals("Zone")&&table_zone_data.size()==10){
        	Issues_count_list.add("Pass");//381 zone
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(6).getText().equals("Project")&&table_proj_data.size()==10){
        	Issues_count_list.add("Pass");//382 project
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(7).getText().equals("AckBy")&&table_ackby_data.size()==10){
        	Issues_count_list.add("Pass");//383 ackby
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(8).getText().equals("AckTime")&&table_acktime_data.size()==10){
        	Issues_count_list.add("Pass");//384 acktime
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(9).getText().equals("Category")&&table_catg_data.size()==10){
        	Issues_count_list.add("Pass");//385 category
		}
		else{
			Issues_count_list.add("Failed");
		}
        if(table_headers.get(10).getText().equals("Action")&&table_act_data.size()==10){
        	Issues_count_list.add("Pass");//386 action
		}
		else{
			Issues_count_list.add("Failed");
		}
        String name_issues=getText(getElement(ElementConstants.ISSUES_TABLEEMPNAME));
        click(getElement(ElementConstants.ISSUES_TABLEEMPNAME));
        
        explicitWaitByxpath(getElement(ElementConstants.PEOPLE_PRF_ID));
        String name_profile=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
        System.out.println(name_issues+","+name_profile);
        if(name_issues.equals(name_profile)){
        	Issues_count_list.add("Pass");//387 employee redirection
        	previouspage();
        	explicitWaitByxpathclickable(getElement(ElementConstants.ISSUES_TABLEBODY));
		}
		else{
			Issues_count_list.add("Failed");
		}
        Issues_count_list.add("Not Feasible");//388 sorted
        String ack_title=title(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ACK));
    	System.out.println(ack_title);
    	//values from first row of table
    	String alert_name=getText(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ROW1_ALTNAME));
        String alert_time=getText(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ROW1_ALTTIME));
        String alert_emp_name=getText(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ROW1_EMPNAME));
        String alert_emp_cmpny=getText(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ROW1_COMP));
        if(isVisible(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ACK))){
         Issues_count_list.add("Pass");//389 ack button
         clickOverlap(getElement(ElementConstants.ISSUES_EMP_DET_EVNTS_LIST_ACK));
    	 Thread.sleep(TIME_04);
        }
        else{
         Issues_count_list.add("Failed");// ack button
        }
    	
        Issues_count_list.add("Not Feasible");//390 location marking
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_NAME))) {
			String alt_popup_name=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_NAME));
			if (alt_popup_name.equals(alert_name)) {
				Issues_count_list.add("Pass");//391 alt name
			}
	    	else{
	    		Issues_count_list.add("Failed");// alt name
	    	}
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_LASTSEEN))) {
    		Issues_count_list.add("Pass");//392 last seen
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_LASTLOC))) {
    		Issues_count_list.add("Pass");//393 last loc
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_EMPNAME))) {
			String alt_popup_empname=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_EMPNAME));
			if (alt_popup_empname.equals(alert_emp_name)) {
				Issues_count_list.add("Pass");//394 emp name
			}
	    	else{
	    		Issues_count_list.add("Failed");//emp name
	    	}
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	Issues_count_list.add("Not Feasible");//395 role
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_SUP))) {
    		Issues_count_list.add("Pass");//396 sup
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CMP))) {
			String alt_popup_cmp=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CMP));
			System.out.println("a"+alt_popup_cmp+","+alert_emp_cmpny+"b");
			if (alt_popup_cmp.trim().equals(alert_emp_cmpny)) {
				Issues_count_list.add("Pass");//397 company
			}
	    	else{
	    		Issues_count_list.add("Failed");//company
	    	}
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	Issues_count_list.add("Not Feasible");//section 398
    	Issues_count_list.add("Not Feasible");//zone 399
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_TIME))) {
			String alt_popup_time=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_TIME));
			System.out.println(alt_popup_time+"+"+alert_time);
			if (alt_popup_time.equals(alert_time)) {
				Issues_count_list.add("Pass");//400 alert time
			}
	    	else{
	    		Issues_count_list.add("Failed");//alert time time
	    		System.out.println("alert time failed");
	    	}
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	Issues_count_list.add("Not Feasible");//landmark 401
    	Issues_count_list.add("Not Feasible");//pop up alert 402
    	Issues_count_list.add("Not Feasible");//severity 403
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_FORM))) {
    		Issues_count_list.add("Pass");//404 form
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_SEVERITY))) {
    		Issues_count_list.add("Pass");//405 SEVERITY
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_STATE))) {
    		Issues_count_list.add("Pass");//406 state dropdown
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ACTION))) {
    		Issues_count_list.add("Pass");//407 action
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CATG))) {
    		Issues_count_list.add("Pass");//408 category
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CATGNAT))) {
    		Issues_count_list.add("Pass");//409 category nat
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_REM))) {
    		Issues_count_list.add("Pass");//410 remarks
		}
    	else{
    		Issues_count_list.add("Failed");
    	}
    	if(ack_title.equals("Addressed")){
    		if(isEnabled(getElement(ElementConstants.ISSUES_POPUP_SUB))){
    			Issues_count_list.add("Failed");//411 submit button
    		}
    		else{
    			Issues_count_list.add("Pass");
    		}
    	}
    	else{
    		if(isEnabled(getElement(ElementConstants.ISSUES_POPUP_SUB))){
    			Issues_count_list.add("Pass");// submit button
    		}
    		else{
    			Issues_count_list.add("Failed");
    		}
    	}
    	Issues_count_list.add("Not Feasible");//412 fade out
    	clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CLOSE));
        
        
		
		return Issues_count_list;
	}
}

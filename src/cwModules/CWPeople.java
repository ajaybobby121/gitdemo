package cwModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import constants.ElementConstants;


public class CWPeople extends AppDriver{
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
	
	public CWPeople()throws IOException, InterruptedException {
		super(propFileName);
	}
	public List<String> people_SideNav() throws InterruptedException{
		List<String> people_count_list= new ArrayList<String>();
		System.out.println("START - 5. people_SideNav");
		click(getElement(ElementConstants.PEPSIDENAV));
		Thread.sleep(TIME_04);
		if(isVisible(getElement(ElementConstants.PEOPLE_ALL))){
			people_count_list.add("Pass");//183
		}
		else{
			people_count_list.add("Failed");//183
		}
	
		Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
		Thread.sleep(TIME_02);
		List<WebElement>options=plt_drp_options_01.getOptions();
		plt_drp_options_01.selectByIndex(2);
		System.out.println("a"+options.get(2).getText().trim()+"a");
		Thread.sleep(TIME_07);
		String plt_name=getText(getElement(ElementConstants.PEOPLE_TILE_PLT));
		System.out.println("a"+plt_name+"a");
		if(options.get(2).getText().trim().equals(plt_name))
		{
			System.out.println("passed");
			people_count_list.add("Pass");//184
		}
		else{
			System.out.println("failed");
			people_count_list.add("Failed");//184
		}
		people_count_list.add("Not Feasible");//185 assigned count
		people_count_list.add("Not Feasible");//186 assigned count
		people_count_list.add("Not Feasible");//187 unassigned count
		people_count_list.add("Not Feasible");//188 unassigned count
		if(isVisible(getElement(ElementConstants.PEOPLE_EMP_SEL))){
			Select emp_drp_options=new Select(path(getElement(ElementConstants.PEOPLE_EMP_SEL)));
			List<WebElement>option=emp_drp_options.getOptions();
			for (WebElement webElement : option) {
				System.out.println(webElement.getText());
			}
			emp_drp_options.selectByVisibleText("Employee Id");
			sentText("pbe010", getElement(ElementConstants.PEOPLE_SEARCH));
			click(getElement(ElementConstants.PEOPLE_SEARCH_SUB));
//			explicitWaitByxpath(getElement(ElementConstants.PEOPLE_TILE_PLT));
			Thread.sleep(TIME_04);
			List<WebElement>tiles_count=getelements(getElement(ElementConstants.PEOPLE_TILE_CNT));
			String emp_id=getText(getElement(ElementConstants.PEOPLE_EMP_ID));
			String emp_id_arr[]=emp_id.split(" ");
			System.out.println(emp_id_arr[0]);
			System.out.println(emp_id_arr[1]);
			System.out.println(tiles_count.size());
			if((emp_id_arr[1].equals("PBE010"))&&tiles_count.size()==1){
				System.out.println("id passed");
				people_count_list.add("Pass");//189 employee id
				people_count_list.add("Pass");//190
			}
			else{
				System.out.println(" id failed");
				people_count_list.add("Failed");//189 employee id
				people_count_list.add("Failed");//190
			}
			click(getElement(ElementConstants.PEOPLE_CLR_FIL));
			
		    emp_drp_options.selectByVisibleText("Employee Name");
		    sentText("100", getElement(ElementConstants.PEOPLE_SEARCH));
		    click(getElement(ElementConstants.PEOPLE_SEARCH_SUB));
		    Thread.sleep(TIME_04);
		    String name=getText(getElement(ElementConstants.PEOPLE_EMP_NAME));
		    String name_array[]=name.split("/");
		    if(name_array[0].trim().equals("CAWW EMP 100")){
		    	System.out.println("name passed");
		    	people_count_list.add("Pass");//191 employee name
				people_count_list.add("Pass");//192
		    	
		    }
		    else{
		    	System.out.println("name failed");
		    	people_count_list.add("Failed");//191 employee name
				people_count_list.add("Failed");//192
		    }
		    click(getElement(ElementConstants.PEOPLE_CLR_FIL));
		    explicitWaitByxpathclickable(getElement(ElementConstants.PEOPLE_TILE_PLT));
		}
		else{
			people_count_list.add("Failed");
			people_count_list.add("Failed");
			people_count_list.add("Failed");
			people_count_list.add("Failed");
		}
		    //company
		    Select comp_fil_options=new Select(path(getElement(ElementConstants.PEOPLE_CMPNY_FILTER)));
		    List<WebElement>company_options=comp_fil_options.getOptions();
		    String comp_fil[]=company_options.get(1).getText().split("-");
		    String comp_fil_name=comp_fil[0].trim();
		    System.out.println("filter name"+comp_fil_name);
		    int comp_fil_count=Integer.parseInt(comp_fil[1].trim());
		    comp_fil_options.selectByIndex(1);
		    Thread.sleep(TIME_04);
		    String peop_comp_name=getText(getElement(ElementConstants.PEOPLE_CMPNY_NAME));
		    System.out.println("people filter name"+peop_comp_name);
		    
		    System.out.println(comp_fil_count);
		    int all_count_cmp=Integer.parseInt(getText(getElement(ElementConstants.PEOPLE_ALL_CNT).trim()));
		    System.out.println(all_count_cmp);	
		    if((comp_fil_name.equals(peop_comp_name)&&(comp_fil_count==all_count_cmp))){
		    	people_count_list.add("Pass");//193 company filter
		    }
		    else{
		    	people_count_list.add("Failed");//193 company filter
		    }
		    click(getElement(ElementConstants.PEOPLE_CLR_FIL));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_TILE_PLT));
		    //role filter
		    Select role_fil_options=new Select(path(getElement(ElementConstants.PEOPLE_ROLE_FILTER)));
		    List<WebElement>role_options=role_fil_options.getOptions();
		    String role_fil[]=role_options.get(1).getText().split("-");
		    String role_fil_name=role_fil[0].trim();
		    System.out.println(" role "+role_fil_name);
		    int role_fil_count=Integer.parseInt(role_fil[1].trim());
		    role_fil_options.selectByIndex(1);
		    Thread.sleep(TIME_04);
		    String peop_role=getText(getElement(ElementConstants.PEOPLE_ROLE_NAME));
		    String people_role_arr[]=peop_role.split(",");
		    
		    System.out.println("people role filter name"+people_role_arr[0]);
		    
		    System.out.println("role_fil_count"+role_fil_count);
		    int all_count_role=Integer.parseInt(getText(getElement(ElementConstants.PEOPLE_ALL_CNT).trim()));
		    System.out.println("all_count_role"+all_count_role);	
		    if((role_fil_name.equals(people_role_arr[0])&&(role_fil_count==all_count_role))){
		    	people_count_list.add("Pass");//194 role filter
		    }
		    else{
		    	people_count_list.add("Failed");//194 role filter
		    }
		    click(getElement(ElementConstants.PEOPLE_CLR_FIL));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_TILE_PLT));
		    //section filter
		    Select section_fil_options=new Select(path(getElement(ElementConstants.PEOPLE_SECTION_FILTER)));
		    List<WebElement>section_options=section_fil_options.getOptions();
		    String section_fil[]=section_options.get(1).getText().split("-");
		    String section_fil_name;
		    if(section_fil[0].trim().equals("Operations")){
		    	section_fil_name=section_fil[0]+" - "+section_fil[1];
		    }
		    else{
		        section_fil_name=section_fil[0].trim();
		    }
		    System.out.println(" section "+section_fil_name);
		    int section_fil_count=0;
		    try{
		     section_fil_count=Integer.parseInt(section_fil[1].trim());
		    }catch (NumberFormatException e) {
				// TODO: handle exception
//		    	System.out.println(section_fil[2]);
//		     String section_fil_2[]=section_fil[1].split("-");
		     section_fil_count=Integer.parseInt(section_fil[2].trim());//operations-shift case
			}
		    section_fil_options.selectByIndex(1);
		    Thread.sleep(TIME_04);
		    if (isVisible(getElement(ElementConstants.PEOPLE_SECTION_NAME))) {
			  String peop_section=getText(getElement(ElementConstants.PEOPLE_SECTION_NAME));
		      System.out.println("peop section"+peop_section);
		      String people_section_arr[]=peop_section.split(",");
		    
		      System.out.println("people section filter name"+people_section_arr[0]);
		    
		    System.out.println("section_fil_count"+section_fil_count);
		    int all_count_section=Integer.parseInt(getText(getElement(ElementConstants.PEOPLE_ALL_CNT).trim()));
		    System.out.println("all_count_section"+all_count_section);	
		    if((section_fil_name.equals(people_section_arr[0])&&(section_fil_count==all_count_section))){
		    	people_count_list.add("Pass");//195 section filter
		    }
		    else{
		    	people_count_list.add("Failed");//195 section filter
		    }
		    }
		    else{
		    	people_count_list.add("Failed");//195 section filter
		    }
		    click(getElement(ElementConstants.PEOPLE_CLR_FIL));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_TILE_PLT));
		    
		    //relation filters
		    int count=0;
		    List<WebElement>company_options_01=comp_fil_options.getOptions();
		    String comp_fil_01[]=company_options_01.get(1).getText().split("-");
		    int comp_fil_count_01=Integer.parseInt(comp_fil_01[1].trim());
		    comp_fil_options.selectByIndex(1);
		    Thread.sleep(TIME_05);
		    int role_total_count=filteractiveCount(getElement(ElementConstants.PEOPLE_ROLE_FILTER));
		    System.out.println("role_count"+role_total_count);
		    if(role_total_count==comp_fil_count_01){
		    	count=count+1;
		    }
		    List<WebElement>role_options_01=role_fil_options.getOptions();
		    String role_fil_01[]=role_options_01.get(1).getText().split("-");
		    int role_fil_count_01=Integer.parseInt(role_fil_01[1].trim());
		    role_fil_options.selectByIndex(1);
		    Thread.sleep(TIME_04);
		    int section_count=filteractiveCount(getElement(ElementConstants.PEOPLE_SECTION_FILTER));
		    if(section_count==role_fil_count_01){
		    	count=count+1;
		    }
		    System.out.println("count"+count);
		    if(count==2){
		    	people_count_list.add("Pass");//196
		    }
		    else{
		    	people_count_list.add("Failed");//196
		    }
		    click(getElement(ElementConstants.PEOPLE_CLR_FIL));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_TILE_PLT));
		    people_count_list.add("Not Feasible");//197 tiles representation
		    //caww emp 100 details 
		    sentText("100", getElement(ElementConstants.PEOPLE_SEARCH));
		    click(getElement(ElementConstants.PEOPLE_SEARCH_SUB));
		    Thread.sleep(TIME_04);
		    if(isVisible(getElement(ElementConstants.PEOPLE_TILE_PIC))){
		    	people_count_list.add("Pass");//198 profile pic
		    }
		    else{
		    	people_count_list.add("Failed");//198
		    }
		    String emp_tile_name;
		    String emp_tile_name_array[]=null;
		    String peop_section_tile;
		    String people_section_tile_arr[]=null;
		    String peop_role_tile;
		    String people_role_tile_arr[]=null;
		    String last_active_details;
		    String last_seen_arr[];
		    String last_seen_details;
		    String last_actve_details;
		    String last_active_arr[];
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_NAME))){
		        emp_tile_name=getText(getElement(ElementConstants.PEOPLE_EMP_NAME));
			    emp_tile_name_array=emp_tile_name.split("/");
			    
			    people_count_list.add("Pass");//199 empname
			    people_count_list.add("Pass");//200 empid
		    }
		    else{
		    	people_count_list.add("Failed");//199 empname
			    people_count_list.add("Failed");//200 empid
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_SECTION_NAME))){
		        peop_section_tile=getText(getElement(ElementConstants.PEOPLE_SECTION_NAME));		   
		        System.out.println("peop section"+peop_section_tile);
		        people_section_tile_arr=peop_section_tile.split(",");
		        for (String string : people_section_tile_arr) {
		        	System.out.println(string);
					
				}
		        people_count_list.add("Pass");//201 section
			    people_count_list.add("Pass");//202 company name
		    }
		    else{
		    	people_count_list.add("Failed");//201 section
			    people_count_list.add("Failed");//202 company name
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_ROLE_NAME))){
		        peop_role_tile=getText(getElement(ElementConstants.PEOPLE_ROLE_NAME));
			    people_role_tile_arr=peop_role_tile.split(",");
			    people_count_list.add("Pass");//203 role
			    people_count_list.add("Pass");//204 sitename
		    }
		    else{
		    	people_count_list.add("Failed");//203 role
			    people_count_list.add("Failed");//204 sitename
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_TILE_PHONE))){
		    	people_count_list.add("Pass");//205 phone
		    }
		    else{
		    	people_count_list.add("Failed");//205 phone
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_TILE_EMAIL))){
		    	people_count_list.add("Pass");//206 email
		    }
		    else{
		    	people_count_list.add("Failed");//206 email
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_TILE_LAST_SEEN))){
		        last_seen_details=getText(getElement(ElementConstants.PEOPLE_TILE_LAST_SEEN));
		        last_seen_arr=last_seen_details.split(":");
		        people_count_list.add("Pass");//207 last seen
		    }
	        else{
	    	    people_count_list.add("Failed");//207 last seen
	        }
		    if(isVisible(getElement(ElementConstants.PEOPLE_TILE_LAST_ACTIVE))){
		        last_active_details=getText(getElement(ElementConstants.PEOPLE_TILE_LAST_ACTIVE));
		        last_active_arr=last_active_details.split(":");
		        people_count_list.add("Pass");//208 last active
		    }
	        else{
	    	    people_count_list.add("Failed");//208 last active
	        }
		    if(isVisible(getElement(ElementConstants.PEOPLE_TILE_ALERTS_COUNT))){
		    	people_count_list.add("Pass");//209 alerts count
		    }
	        else{
	    	    people_count_list.add("Failed");//209 alerts count
	        }
		    clickOverlap(getElement(ElementConstants.PEOPLE_TILE_PIC));
//		    click(getElement(ElementConstants.PEOPLE_TILE_PROF_VIEW));
		    Thread.sleep(TIME_08);
		    String emp_page_name=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    System.out.println( emp_page_name+","+emp_tile_name_array[0]);
		    if(emp_tile_name_array[0].trim().equals(emp_page_name)){
		    	people_count_list.add("Pass");//210 emp details
		    	people_count_list.add("Pass");//211 emp name
		    }
		    else{
		    	 people_count_list.add("Failed");
		    	 people_count_list.add("Failed");
		    }
		    String emp_page_id=getText(getElement(ElementConstants.PEOPLE_PRF_ID));
		    System.out.println( emp_page_id+","+emp_tile_name_array[1]);
		    if(emp_tile_name_array[1].trim().equals(emp_page_id)){
		    	people_count_list.add("Pass");//212 emp id
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    String emp_page_role=getText(getElement(ElementConstants.PEOPLE_PRF_ROLE));
		    System.out.println( emp_page_role+","+people_role_tile_arr[0]);
		    if(emp_page_role.equals(people_role_tile_arr[0])){
		    	people_count_list.add("Pass");//213 role
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    String emp_page_trade=getText(getElement(ElementConstants.PEOPLE_PRF_SECTION));
		    System.out.println( emp_page_trade+","+people_section_tile_arr[0]);
		    if(emp_page_trade.equals(people_section_tile_arr[0])){
		    	people_count_list.add("Pass");//214 section
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    String emp_page_cmp=getText(getElement(ElementConstants.PEOPLE_PRF_CMP));
		    System.out.println( emp_page_cmp+","+people_section_tile_arr[1].trim());
		    if(emp_page_cmp.equals(people_section_tile_arr[1].trim())){
		    	people_count_list.add("Pass");//215 company
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_PRF_SUP))){
		    	
		    	String emp_page_sup=getText(getElement(ElementConstants.PEOPLE_PRF_SUP));
		    	click(getElement(ElementConstants.PEOPLE_PRF_SUP));
		    	Thread.sleep(TIME_10);
		    	explicitWaitByxpath(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    	String emp_prof_sup=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    	if(emp_page_sup.equals(emp_prof_sup)){
		    		people_count_list.add("Pass");//216 supervisor
		    		people_count_list.add("Pass");//217 supervisor redirecton
			    }
			    else{
			    	people_count_list.add("Failed");
			    	people_count_list.add("Failed");
			    }
		    }
		    else{
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    }
		    click(getElement(ElementConstants.PEPSIDENAV));
		    Thread.sleep(TIME_04);
		    sentText("100", getElement(ElementConstants.PEOPLE_SEARCH));
		    click(getElement(ElementConstants.PEOPLE_SEARCH_SUB));
		    Thread.sleep(TIME_07);
		    clickOverlap(getElement(ElementConstants.PEOPLE_TILE_PIC));

		    Thread.sleep(TIME_05);
		    
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_BG_IN))){
		    	people_count_list.add("Pass");//218 badge in
		    }
		    else {
		    	people_count_list.add("Failed");
			}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_BG_OUT))){
		    	people_count_list.add("Pass");//219 badge out
		    }
		    else {
		    	people_count_list.add("Failed");
			}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_FST_ACT))){
		    	people_count_list.add("Pass");//220 first active
		    }
		    else {
		    	people_count_list.add("Failed");
			}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_LST_ACT))){
		    	people_count_list.add("Pass");//221 last active
		    }
		    else {
		    	people_count_list.add("Failed");
			}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_LST_SEEN))){
		    	people_count_list.add("Pass");//222 last seen		    
		    }
		    else {
		    	people_count_list.add("Failed");
			}
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_DATEPICK));
		    String month=getText(getElement(ElementConstants.PEOPLE_EMP_DET_DAT_MNT));
		    String year=getText(getElement(ElementConstants.PEOPLE_EMP_DET_DAT_YEAR));
		    String date=month+" "+year;
		    System.out.println(date);
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_DAT_MNT));
		    Thread.sleep(TIME_05);
		    String summary_date=getText(getElement(ElementConstants.PEOPLE_EMP_DET_SUMMARY));
		    String summary_date_arr[]=summary_date.split("of");
		    System.out.println(summary_date_arr[1].trim());
		    if(date.equals(summary_date_arr[1].trim())){
		    	people_count_list.add("Pass");//223 summary
		    	people_count_list.add("Pass");//224 datepicker
		    }
		    else{
		    	people_count_list.add("Failed");//223 summary
		    	people_count_list.add("Failed");//224 datepicker
		    }
		    refresh();
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK))){
		    	String tile_01_head=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_HEAD));
		    	if(tile_01_head.equals("Hours Clocked"))	{
		    		people_count_list.add("Pass");//225 hours clocked tile	
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//225 hours clocked	tile
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_TIME))){
                    people_count_list.add("Pass");//226 hours clocked month		
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//226 hours clocked	month
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_OVRTIM))){
                    people_count_list.add("Pass");//227 hours clocked ovrtime	
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//227 hours clocked	overtime
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_DAYS))){
                    people_count_list.add("Pass");//228 hours clocked days	
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//228 hours clocked	days
		    	}
		    }
		    else {
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
			}
		    click(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK));
		    Thread.sleep(TIME_08);
		    click(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_GRPH));
		    people_count_list.add("Not Feasible");//229 graphical representation
		    people_count_list.add("Not Feasible");//230 graphical representation
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE))){
                people_count_list.add("Pass");//231 selected date		
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//231 selected date
	    	}
		    //selected date details
		    String bdgeIn_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_BG_IN));
		    String bdgeOut_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_BG_OUT));
		    System.out.println(bdgeIn_01+","+bdgeOut_01);
		    scrollDown();
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_BDGEiN));
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_BDGEiN))){
		    	String bdgeIn_02=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_BDGEiN));
		    	String bdgeIn_02_arr[]=bdgeIn_02.split("Time");
		    	String bdgeIn_02_arr_01[]=bdgeIn_02_arr[1].split("\\n");
		    	if(bdgeIn_02_arr_01[1].equals("Not Available")){
		    		if(bdgeIn_02_arr_01[1].equals(bdgeOut_01)){
		    			people_count_list.add("Pass");//232 badge in time
			    	}
			    	else{
			    		System.out.println("badge01 in failed");
			    		people_count_list.add("Failed");//232 badge in time
			    	}
		    	}
		    	else{
		        bdgeIn_02_arr=bdgeIn_02.split("Time");
			    bdgeIn_02_arr_01=bdgeIn_02_arr[1].split("\\n");
		    	System.out.println("Hello");
		    	System.out.println("0,"+bdgeIn_02_arr[1]);
		    	System.out.println("1,"+bdgeIn_02_arr_01[1]);
		    	System.out.println("2,"+bdgeIn_02_arr_01[2]);
		    	String bdgeIn_02_actual=bdgeIn_02_arr_01[1]+ " , " +bdgeIn_02_arr_01[2];
		    	System.out.println(bdgeIn_02_actual);
		    	System.out.println(bdgeIn_02_actual+",bdgein"+bdgeIn_01);
		    	if(bdgeIn_02_actual.equals(bdgeIn_01)){
		    		System.out.println("badge in passed");
		    		people_count_list.add("Pass");//232 badge in time
		    	}
		    	else{
		    		System.out.println("badge in failed");
		    		people_count_list.add("Failed");//232 badge in time
		    	}
		    	}
		    }
		    else{
		    	System.out.println("badge in failed");
		    	people_count_list.add("Failed");//232 badge in time
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_HRS_CLK))){
		    	people_count_list.add("Pass");//233 hrs clck
		    	String hrs_clcked_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_HRS_CLK));
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_ZONES))){
		    	people_count_list.add("Pass");//234 zones
		    	String zones_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_ZONES));
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_EVTS))){
		    	people_count_list.add("Pass");//235 events
		    	String events_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_EVTS));
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_STEPS))){
		    	people_count_list.add("Pass");//236 steps
		    	String steps_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_STEPS));
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_BDGEOUT))){
		    	String bdge_out_02=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_BDGEOUT));
		    	String bdge_out_arr_02[]=bdge_out_02.split("Time\\n");
		    	for (String string : bdge_out_arr_02) {
					System.out.println(string);
				}
		    	System.out.println("bdgeout,"+bdge_out_arr_02[1].trim()+"a");
		    	String bdgeOut_02_actual=null;
		    	if(bdge_out_arr_02[1].equals(bdgeOut_01)){
		    		people_count_list.add("Pass");//237 bdge out (Not Available)
		    	}
		    	else{
		    		bdge_out_arr_02=bdge_out_02.split("Time");
		    		String bdgeOut_02_arr_01[]=bdge_out_arr_02[1].split("\\n");
			    	System.out.println("Hello");
			    	System.out.println("0,"+bdge_out_arr_02[1]);
			    	System.out.println("1,"+bdgeOut_02_arr_01[1]);
			    	System.out.println("2,"+bdgeOut_02_arr_01[2]);
			        bdgeOut_02_actual=bdgeOut_02_arr_01[1]+ " , " +bdgeOut_02_arr_01[2];
			    	System.out.println(bdgeOut_02_actual);
			    	System.out.println(bdgeOut_02_actual+",bdgout"+bdgeOut_01);
		    		if(bdgeOut_02_actual.equals(bdgeOut_01)){
		    		people_count_list.add("Pass");//237 bdge out
		    	    }
		    	    else{
		    		people_count_list.add("Failed");//237 bdge out
		    	    }
		    	}
		    }
		    
		    else{
		    	people_count_list.add("Failed");//237
		    }
		    people_count_list.add("Not Feasible");//238 zone accessed pie chart 
		    people_count_list.add("Not Feasible");//239 activity summary pie chart
		    people_count_list.add("Not Feasible");//240 severity pie chart
		    people_count_list.add("Not Feasible");//241 total hrs matching
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_CLOSE_BTN));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE))){
		    	String tile_02_head=getText(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_HEAD));
		    	if(tile_02_head.equals("Geo Trace")){
		    		people_count_list.add("Pass");//242 geo trace
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZONES))){
		    		people_count_list.add("Pass");//243 geo trace zones
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//243 geo trace zones
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_UNAUTH))){
		    		people_count_list.add("Pass");//244 geo trace unauth
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//244 geo trace unauth
		    	}
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    }
		    click(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE));
		    Thread.sleep(TIME_08);
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_TIME_SPENT))){
		    	people_count_list.add("Pass");//245 selected date
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    people_count_list.add("Not Feasible");//246 barchart
		    people_count_list.add("Not Feasible");//247 selection barchart
		    people_count_list.add("Not Feasible");//248 selection barchart
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_TIME_SPENT))){
                people_count_list.add("Pass");//249 selected date		
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//249 selected date
	    	}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZNS_PIE_CHART))){
                people_count_list.add("Pass");//250 ZONES PIE CHRT		
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//250 ZONES PIE CHART
	    	}
		    int zones_count=0;
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_SEL_DATE_ZONES))){
		        zones_count=Integer.parseInt(getText(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_SEL_DATE_ZONES)));
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZNS_TABLE))){
		        List<WebElement>zones_table_rows=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZNS_TABLE_ROWS));
		        if(zones_table_rows.size()==zones_count){
                   people_count_list.add("Pass");//251 tablular rep for sones		
		        }
		        else{
		           people_count_list.add("Failed");
		        }
	    	}
	    	else{
	    		people_count_list.add("Failed");//251 
	    	}
		    List<WebElement>zones_headers=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZNS_TABLE_HEAD));
		    List<String>zones_headers_string=new ArrayList<>();
		    for (WebElement webElement : zones_headers) {
		          zones_headers_string.add(webElement.getText());
			}
		    List<String>zones_head_list=Arrays.asList("Zone Level","Zone Risk","Zone Name","Time Spent");
		    if(zones_headers_string.containsAll(zones_head_list)){
		    	people_count_list.add("Pass");//252 table headers
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    people_count_list.add("Not Feasible");//253 scale representation
		    scrollDown();
		    
		    Thread.sleep(TIME_03);
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZNS_TIMEPER_TABLE))){
                people_count_list.add("Pass");//254 Zones accessed time period	
	    	}
	    	else{
	    		people_count_list.add("Failed");//254 Zones accessed time period	
	    	}
		    List<WebElement>zones_headers_sel_time=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_ZNS_TIMEPER_HEAD));
		    List<String>zones_headers_sel_time_string=new ArrayList<>();
		    for (WebElement webElement :zones_headers_sel_time) {
		    	zones_headers_sel_time_string.add(webElement.getText());
			}
		    for (String string : zones_headers_sel_time_string) {
				System.out.println(string);
			}
		    
		    List<String>zones_sel_time_head_list=Arrays.asList("Zone Level","Zone Risk","Zone Name","Time Spent");
		    if(zones_headers_sel_time_string.containsAll(zones_sel_time_head_list)){
		    	people_count_list.add("Pass");//255 table headers
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_LVL_SEL))){
                people_count_list.add("Pass");//256 level selection	
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//256 level selection
	    	}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_TRACK))){
                people_count_list.add("Pass");//257 geo track map
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//257 geo track map
	    	}
		    people_count_list.add("Not Feasible");//258 geo track update
		    people_count_list.add("Not Feasible");//259 total hours matching
		    explicitWaitByxpathclickable(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_CLOSE_BTN));
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_GEO_TRACE_CLOSE_BTN));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF))){
		    	String tile_03_head=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_HEAD));
		    	if(tile_03_head.equals("Profile")){
		    		people_count_list.add("Pass");//260 personal Inf
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_MOB))){
		    		people_count_list.add("Pass");//261 per inf mob
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//261 per inf mob
		    	}
		    	String email=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EMAIL));
		    	System.out.println("email"+email);
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EMAIL))){
		    		people_count_list.add("Pass");//262 per inf email
		    	}
		    	else{
		    		people_count_list.add("Failed");//262 per inf email
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EME_CONT))){
		    		people_count_list.add("Pass");//263 per inf emergency
		    		people_count_list.add("Pass");//264
		    	}
		    	else{
		    		people_count_list.add("Failed");//263 per inf emergency
		    		people_count_list.add("Failed");
		    	}
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    }
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF));
		    Thread.sleep(TIME_08);
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_NME))){
		    	people_count_list.add("Pass");//265 name
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_ADDR))){
		    	people_count_list.add("Pass");//266 address
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_PHN))){
		    	people_count_list.add("Pass");//267 phone
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EMAIL_FORM))){
		    	people_count_list.add("Pass");//268 email
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_BLDGRP))){
		    	people_count_list.add("Pass");//269 blood grp
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_NAT))){
		    	people_count_list.add("Pass");//270 nationality
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_DOB))){
		    	people_count_list.add("Pass");//271 dob
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_MAR_STS))){
		    	people_count_list.add("Pass");//272 martial status
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_GEN))){
		    	people_count_list.add("Pass");//273 gender
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_QUAL))){
		    	people_count_list.add("Pass");//274 qualification
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_ALLER_NME))){
		    	String table_1=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_ALLER_NME));
		    	if(table_1.equals("Allergies:")){
		    		people_count_list.add("Pass");//275 allergies
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_INJ))){
		    	String table_1=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_INJ));
		    	if(table_1.equals("Injuries:")){
		    		people_count_list.add("Pass");//276 injuries
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EXP))){
		    	String table_1=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EXP));
		    	if(table_1.equals("Experience:")){
		    		people_count_list.add("Pass");//277 experience
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_CERT))){
		    	String table_1=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_CERT));
		    	if(table_1.equals("Certifications:")){
		    		people_count_list.add("Pass");//278 certifications
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EMER))){
		    	String table_1=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EMER));
		    	if(table_1.equalsIgnoreCase("Emergency PoC:")){
		    		people_count_list.add("Pass");//279 emergency
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	System.out.println("poc failed");
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_POI))){
		    	String table_1=getText(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_POI));
		    	if(table_1.equals("POI:")){
		    		people_count_list.add("Pass");//280 poi
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EDIT_BTN))){
		    	click(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_EDIT_BTN));
		    	if(isEnabled(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_UPDATE_BTN))&&isEnabled(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_CANCEL_BTN))){
		    		people_count_list.add("Pass");//281 edit button selected
		    		people_count_list.add("Pass");//282 updatebutton
		    		people_count_list.add("Pass");//283 CANCEL BUTTON
		    		click(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_CANCEL_BTN));
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    		people_count_list.add("Failed");
		    		people_count_list.add("Failed");
		    	}
		    }
		    else{
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    }
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_PER_INF_CLOSE));
		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS))){
		    	String tile_02_eve=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_HEAD));
		    	if(tile_02_eve.equals("Events")){
		    		people_count_list.add("Pass");//284 events tile
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_TOT))){
		    		people_count_list.add("Pass");//285 total
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//285
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_NEAR_MIS))){
		    		people_count_list.add("Pass");//286 near miss
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//286
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_SFT_HAZ))){
		    		people_count_list.add("Pass");//287 sfty hzrd
		    		
		    	}
		    	else{
		    		people_count_list.add("Failed");//287
		    	}
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    }
		    
		    click(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS));
		    Thread.sleep(TIME_04);
		    people_count_list.add("Not Feasible");//288 time spent in zones
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_DTLS))){
	    		people_count_list.add("Pass");//289 event details
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//289
	    	}
	    	
		    people_count_list.add("Not Feasible");//290 zone pie chart 
		    people_count_list.add("Not Feasible");//291 status pie chart
		    people_count_list.add("Not Feasible");//292 severity pie chart
		    people_count_list.add("Not Feasible");//293 category pie chart
		    people_count_list.add("Not Feasible");//294 legends clickable
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST))){
		    	people_count_list.add("Pass");//295 alert list
		    	List<WebElement>event_list_head=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_HDRS));
		    	List<WebElement>event_list_row1=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ROW1));
		    	
		    	List<String>event_list_head_string=new ArrayList<>();
		    	List<String>event_list_row1_string=new ArrayList<>();
		    	for (WebElement webElement : event_list_head) {
		    		event_list_head_string.add(webElement.getText());
		    		}
		    	for (WebElement webElement : event_list_row1) {
		    		event_list_row1_string.add(webElement.getText());
		    		}
		    	for (String string : event_list_row1_string) {
					System.out.println(string);
				}
		    	System.out.println(event_list_head_string.get(0));
		    	if (event_list_head_string.get(0).equalsIgnoreCase("TIME")) {
		    		people_count_list.add("Pass");//296 time
				}
		    	else{
		    		people_count_list.add("Failed");//296
		    	}
		    	if (event_list_head_string.get(1).equalsIgnoreCase("TYPE")) {
		    		people_count_list.add("Pass");//297 Type
				}
		    	else{
		    		people_count_list.add("Failed");//297
		    	}
		    	if (event_list_head_string.get(2).equalsIgnoreCase("EMPLOYEE")) {
		    		people_count_list.add("Pass");//298 Employee
		    		
//		    		String employee=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_EMPLOYEE1));
//		    		click(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_EMPLOYEE1));
		    	}
		    	else{
		    		people_count_list.add("Failed");//298
		    	}
		    	people_count_list.add("Not Feasible");//299 employee redirection
		    	if (event_list_head_string.get(3).equalsIgnoreCase("SECTION")) {
		    		people_count_list.add("Pass");//300 SECTION
				}
		    	else{
		    		people_count_list.add("Failed");//300
		    	}
		    	if (event_list_head_string.get(4).equalsIgnoreCase("COMPANY")) {
		    		people_count_list.add("Pass");//301 COMPANY
				}
		    	else{
		    		people_count_list.add("Failed");//301
		    	}
		    	if (event_list_head_string.get(5).equalsIgnoreCase("ZONE")) {
		    		people_count_list.add("Pass");//302 ZONE
				}
		    	else{
		    		people_count_list.add("Failed");//302 ZONE
		    	}
		    	if (event_list_head_string.get(6).equalsIgnoreCase("PROJECT")) {
		    		people_count_list.add("Pass");//303 PROJ
				}
		    	else{
		    		people_count_list.add("Failed");//303
		    	}
		    	if (event_list_head_string.get(7).equalsIgnoreCase("ACKBY")) {
		    		people_count_list.add("Pass");//304 ACKBY
				}
		    	else{
		    		people_count_list.add("Failed");//304
		    	}
		    	if (event_list_head_string.get(8).equalsIgnoreCase("ACKTIME")) {
		    		people_count_list.add("Pass");//305 ACKTIME
				}
		    	else{
		    		people_count_list.add("Failed");//305
		    	}
		    	if (event_list_head_string.get(9).equalsIgnoreCase("CATEGORY")) {
		    		people_count_list.add("Pass");//306 CATEGORY
				}
		    	else{
		    		people_count_list.add("Failed");//306
		    	}
		    	if (event_list_head_string.get(10).equalsIgnoreCase("ACTION")) {
		    		people_count_list.add("Pass");//307 ACTION
				}
		    	else{
		    		people_count_list.add("Failed");//307
		    	}
		    	String ack_title=title(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ACK));
		    	System.out.println(ack_title);
		    	//values from first row of table
		    	String alert_name=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ROW1_ALTNAME));
		        String alert_time=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ROW1_ALTTIME));
		        String alert_emp_name=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ROW1_EMPNAME));
		        String alert_emp_cmpny=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ROW1_COMP));
		        clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_ACK));
		    	Thread.sleep(TIME_04);
		    	
		    	people_count_list.add("Not Feasible");//308 location marking
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_NAME))) {
					String alt_popup_name=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_NAME));
					if (alt_popup_name.equalsIgnoreCase(alert_name)) {
						people_count_list.add("Pass");//309 alt name
					}
			    	else{
			    		people_count_list.add("Failed");//309 alt name
			    	}
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_LASTSEEN))) {
		    		people_count_list.add("Pass");//310 last seen
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_LASTLOC))) {
		    		people_count_list.add("Pass");//311 last loc
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_EMPNAME))) {
					String alt_popup_empname=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_EMPNAME));
					if (alt_popup_empname.equals(alert_emp_name)) {
						people_count_list.add("Pass");//312 emp name
					}
			    	else{
			    		people_count_list.add("Failed");//312 
			    	}
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	people_count_list.add("Not Feasible");//313 role
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_SUP))) {
		    		people_count_list.add("Pass");//314 sup
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CMP))) {
					String alt_popup_cmp=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CMP));
					System.out.println("a"+alt_popup_cmp+","+alert_emp_cmpny+"b");
					if (alt_popup_cmp.trim().equals(alert_emp_cmpny)) {
						people_count_list.add("Pass");//315 company
					}
			    	else{
			    		people_count_list.add("Failed");//315
			    	}
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	people_count_list.add("Not Feasible");//section 316
		    	people_count_list.add("Not Feasible");//zone 317
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_TIME))) {
					String alt_popup_time=getText(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ALT_TIME));
					System.out.println(alt_popup_time+"+"+alert_time);
					if (alt_popup_time.equals(alert_time)) {
						people_count_list.add("Pass");//318 alert time
					}
			    	else{
			    		people_count_list.add("Failed");//318
			    		System.out.println("alert time failed");
			    	}
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	people_count_list.add("Not Feasible");//landmark 319
		    	people_count_list.add("Not Feasible");//severity 320
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_FORM))) {
		    		people_count_list.add("Pass");//321 form
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_STATE))) {
		    		people_count_list.add("Pass");//322 state dropdown
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_ACTION))) {
		    		people_count_list.add("Pass");//323 action
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CATG))) {
		    		people_count_list.add("Pass");//324 category
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if (isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_REM))) {
		    		people_count_list.add("Pass");//325 remarks
				}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(ack_title.equals("Addressed")){
		    		if(isEnabled(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_SUB))){
		    			people_count_list.add("Failed");//326 submit button
		    		}
		    		else{
		    			people_count_list.add("Pass");
		    		}
		    	}
		    	else{
		    		if(isEnabled(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_SUB))){
		    			people_count_list.add("Pass");//326 submit button
		    		}
		    		else{
		    			people_count_list.add("Failed");
		    		}
		    	}
		    	clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_LIST_POPUP_CLOSE));
				clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_EVNTS_CLOSE));
		    	
		    }
		    else{
		    	System.out.println("failed");
		    	for (int i = 0; i < 32; i++) {
		    		people_count_list.add("Failed");	
				}
		    }
		    Thread.sleep(TIME_07);
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_TILE))){
		    	people_count_list.add("Pass");//327 activity tile
		    	List<WebElement>act_count=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_TOP_ACT));
		    	System.out.println("size"+act_count.size());
		    	if(act_count.size()==3){
		    		people_count_list.add("Pass");//328 3 top activity
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_TILE));
		    	Thread.sleep(TIME_07);
		    }
		    else{
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_MNTH_SUMM))){
		    	people_count_list.add("Pass");//329 summary of month
	    	}
	    	else{
	    		people_count_list.add("Failed");
	    	}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_MNTH_SUMM))){
		    	people_count_list.add("Pass");//330 time spent on activity
	    	}
	    	else{
	    		people_count_list.add("Failed");
	    	}
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_TIME_SPENT_CHART))){
		    	people_count_list.add("Pass");//331 graphical representation
		    }
	    	else{
	    		people_count_list.add("Failed");
	    	}
		    people_count_list.add("Not Feasible");//332 bar chart click
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE))){
                people_count_list.add("Pass");//333 selected date		
	    		
	    	}
	    	else{
	    		people_count_list.add("Failed");//333 selected date
	    	}
		    //selected date details
		    
		    String bdgeIn_gen=getText(getElement(ElementConstants.PEOPLE_EMP_DET_BG_IN));
		    String bdgeOut_gen=getText(getElement(ElementConstants.PEOPLE_EMP_DET_BG_OUT));
		    System.out.println(bdgeIn_gen+","+bdgeOut_gen);
		    scrollDown();
//		    explicitWaitByxpath(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_BDGEiN));
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_BDGE_IN))){
		    	String bdgeIn_act=getText(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_BDGE_IN));
		    	String bdgeIn_act_arr[]=bdgeIn_act.split("Time");
		    	String bdgeIn_act_arr_01[]=bdgeIn_act_arr[1].split("\\n");
//		    	System.out.println("Hello");
//		    	System.out.println("0,"+bdgeIn_02_arr[1]);
//		    	System.out.println("1,"+bdgeIn_02_arr_01[1]);
//		    	System.out.println("2,"+bdgeIn_02_arr_01[2]);
		    	String bdgeIn_act_actual= bdgeIn_act_arr_01[1]+ " , " + bdgeIn_act_arr_01[2];
//		    	System.out.println(bdgeIn_02_actual);
//		    	System.out.println(bdgeIn_02_actual+",bdgein"+bdgeIn_01);
		    	if(bdgeIn_act_actual.equals(bdgeIn_gen)){
		    		System.out.println("badge in passed");
		    		people_count_list.add("Pass");//334 badge in time
		    	}
		    	else{
		    		System.out.println("badge in failed");
		    		people_count_list.add("Failed");//334 badge in time
		    	}
		    }
		    else{
		    	System.out.println("badge in failed");
		    	people_count_list.add("Failed");//334 badge in time
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_HRS_CLK))){
		    	people_count_list.add("Pass");//335 hrs clck
//		    	String hrs_clcked_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_HRS_CLK));
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_OVT))){
		    	people_count_list.add("Pass");//336 overtime
//		    	String hrs_clcked_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_HRS_CLK));
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_ZONE))){
		    	people_count_list.add("Pass");//337 zones
//		    	String zones_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_ZONES));
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_EVENTS))){
		    	people_count_list.add("Pass");//338 events
//		    	String events_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_EVTS));
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_STEPS))){
		    	people_count_list.add("Pass");//339 steps
//		    	String steps_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_STEPS));
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_BDGEOUT))){
		    	String bdge_out_act=getText(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_SEL_DATE_BDGEOUT));
		    	String bdge_out_arr_act[]=bdge_out_act.split("Time\\n");
//		    	for (String string : bdge_out_arr_act) {
//					System.out.println(string);
//					System.out.println("a");
//				}
		    	System.out.println(bdge_out_arr_act[1]+"a");
//		    	System.out.println(bdge_out_arr_act[1]+"b");
//		    	System.out.println("bdgeout,"+bdge_out_arr_act[1].trim()+"a");
		    	String bdgeOut_02_actual=null;
		    	System.out.println(bdge_out_arr_act[1]+","+bdgeOut_gen);
		    	if(bdge_out_arr_act[1].equals(bdgeOut_gen)){
		    		people_count_list.add("Pass");//340 bdge out (Not Available)
		    	}
		    	else{
		    		bdge_out_arr_act=bdge_out_act.split("Time");
		    		String bdgeOut_act_arr_01[]=bdge_out_arr_act[1].split("\\n");
			    	System.out.println("Hello");
			    	System.out.println("0,"+bdge_out_arr_act[1]);
			    	System.out.println("1,"+bdgeOut_act_arr_01[1]);
			    	System.out.println("2,"+bdgeOut_act_arr_01[2]);
			        bdgeOut_02_actual= bdgeOut_act_arr_01[1]+ " , " +bdgeOut_act_arr_01[2];
//			    	System.out.println(bdgeOut_02_actual);
			    	System.out.println(bdgeOut_02_actual+",bdgout"+bdgeOut_gen);
		    		if(bdgeOut_02_actual.equals(bdgeOut_gen)){
		    		people_count_list.add("Pass");//340 bdge out
		    	    }
		    	    else{
		    		people_count_list.add("Failed");// bdge out
		    	    }
		    	}
		    }
		    
		    else{
		    	people_count_list.add("Failed");//bdge out
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_PIE_CHART))){
		    	people_count_list.add("Pass");//341 pie chart
		    }
		    else{
		    	people_count_list.add("Fail");// pie chart
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_TABLE))){
		    	people_count_list.add("Pass");//342 tabular represenation
		    }
		    else{
		    	people_count_list.add("Fail");// tabular represenation
		    }
		    List<WebElement>activity_headers=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_TABLE_HEAD));
		    List<String>activity_headers_string=new ArrayList<>();
		    for (WebElement webElement : activity_headers) {
		    	activity_headers_string.add(webElement.getText());
			}
		    List<String>activity_head_list=Arrays.asList("Activity","Time Spent");
		    if(activity_headers_string.containsAll(activity_head_list)){
		    	people_count_list.add("Pass");//343 table headers
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_ACT_CLSE));
		    Thread.sleep(TIME_07);
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING))){
		    	String map_head=getText(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING_HEAD));
		    	if(map_head.equals("Mapping")){
		    		people_count_list.add("Pass");//344 mapping tile
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING_PLANT))){
		    		people_count_list.add("Pass");//345 mapping tile-plant
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING_DEV))){
		    		String deviceId_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING_DEV));
		    		System.out.println(deviceId_01);
		    		String empname_map=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    		click(getElement(ElementConstants.DEVSIDENAV));
		    		Thread.sleep(TIME_05);
		    		Select dev_drp_options=new Select(path(getElement(ElementConstants.DEV_DRPDWN)));
		    		dev_drp_options.selectByVisibleText("Device Name");
		    		sentText(deviceId_01, getElement(ElementConstants.DEV_SEARCH_FIELD));
		    		click(getElement(ElementConstants.DEV_SEARCH_BTN));
		    		Thread.sleep(TIME_05);
		    		String empname_device=getText(getElement(ElementConstants.DEV_EMPNAME_FIRST));
		    		if(empname_map.equals(empname_device)){
		    			people_count_list.add("Pass");//346 mapping tile-dev
		    			
		    		}
		    		else{
		    			people_count_list.add("Failed");
		    		}
		    		previouspage();
		    		Thread.sleep(TIME_05);
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    	}
		    	if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING_ZONES))){
		    		people_count_list.add("Pass");//347 mapping tile-zones
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    		
		    	}
		    	clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING));
		    	Thread.sleep(TIME_05);
		    	
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
	    		people_count_list.add("Failed");
	    		people_count_list.add("Failed");
	    		people_count_list.add("Failed");
		    }
		    if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_DTLS))){
		    	String tile_heading=getText(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_DTLS));
		    	if((tile_heading.equals("Mapping Details"))&&isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_TABLE))){
		    		people_count_list.add("Pass");//348 mapping details
		    	}
		    	else{
		    		people_count_list.add("Failed");
		    }    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    
		    List<WebElement>map_clms=getelements(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_TABLE_CLMS));
		    List<String>map_clms_string=new ArrayList<>();
		    for (WebElement webElement : map_clms) {
		    	map_clms_string.add(webElement.getText());
			}
		    if(map_clms_string.get(0).equals("Plant")){
		    	people_count_list.add("Pass");//349 plant
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(map_clms_string.get(1).equals("Project")){
		    	people_count_list.add("Pass");//350 project
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(map_clms_string.get(2).equals("Building")){
		    	people_count_list.add("Pass");//351 building
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(map_clms_string.get(3).equals("Floors")){
		    	people_count_list.add("Pass");//352 floors
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(map_clms_string.get(4).equals("Restricted Entry Zones")){
		    	people_count_list.add("Pass");//353 zones
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(map_clms_string.get(5).equals("Device")){
		    	people_count_list.add("Pass");//354 device
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    if(map_clms_string.get(6).equals("Supervisor")){
		    	people_count_list.add("Pass");//355 supervisor
		    	
		    }
		    else{
		    	people_count_list.add("Failed");
		    }
		    String supervisor_name_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_TABLE_SUP_LINK));
		    System.out.println("sup"+supervisor_name_01);
		    click(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_TABLE_SUP_LINK));
		    Thread.sleep(TIME_20);
		    String supervisor_name_02=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
		    System.out.println("sup"+supervisor_name_02);
		    clickOverlap(getElement(ElementConstants.PEOPLE_EMP_DET_MAPPING));
		    Thread.sleep(TIME_02);
		    
		    if ((supervisor_name_01.equals(supervisor_name_02))) {
		    	people_count_list.add("Pass");//356 redirection supervisor
		    	String reportee_name_01=getText(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_TABLE_REP_1));
			    click(getElement(ElementConstants.PEOPLE_EMP_DET_MAP_TABLE_REP_1));
			    Thread.sleep(TIME_20);
			    String reportee_name_02=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
			    if(reportee_name_01.equals(reportee_name_02)){
			    	people_count_list.add("Pass");//357 redirection reportee
			    }
			    else{
			    	people_count_list.add("Failed");
			    }
		    }
		    else{
		    	System.out.println("failed");
		    	people_count_list.add("Failed");
		    	people_count_list.add("Failed");
		  }
		    people_count_list.add("Not Feasible");//358
		    people_count_list.add("Not Feasible");//359
		    Select plt_drp_options_02=new Select(path(getElement(ElementConstants.PLTDROP)));
		    plt_drp_options_02.selectByIndex(0);
		    return people_count_list;
		
	}
}

		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		

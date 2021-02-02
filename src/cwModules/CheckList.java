package cwModules;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import baseClass.ExcelDriver;
import constants.ElementConstants;

public class CheckList extends AppDriver{
	AppDriver ApDrv=new AppDriver();
	static String propFileName ="src\\elements\\Locators.properties";
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
	

	ExcelDriver rdxlTab = new ExcelDriver();
	public CheckList() throws IOException, InterruptedException{
		super(propFileName);
		
	}
	public List<String> checklistCount(String Dom,String Env) throws InterruptedException{
		List<String> count_list= new ArrayList<String>();
		System.out.println("START - 1");
		
		sleep(TIME_04);	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss ");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  
		String dateTimearr[]=dtf.format(now).split(" ");
		Select sel=new Select(path(getElement(ElementConstants.PLTDROP)));
		sel.selectByIndex(0);
//	    count_list.add(dateTimearr[0]);//date
//		count_list.add(dateTimearr[1]);//time
//		count_list.add(Env);//env
//		count_list.add(Dom);//dom
		Thread.sleep(TIME_07);
		String hdCountTdy=getText(getElement(ElementConstants.HMEHEADCOUNTTODAY));
		System.out.println("Today"+hdCountTdy);// check condition
		String hdCountNow=getText(getElement(ElementConstants.HMEHEADCOUNTNOW));
		int hdcountnw=Integer.parseInt(hdCountNow);//check condition
		String alertsTdy=getText(getElement(ElementConstants.HMETOTALERTS));
//		count_list.add(hdCountTdy);//headcount today
//		count_list.add(hdCountNow);//headcount now
//		count_list.add(alertsTdy);//alerts today
		int count=0;
		String levelName=null;
		int maxcount=0;
		String FlevelName=null;
		String FplantName=null;
		String FinTrackHdCnt=null;
		int map_count=0;
		List<WebElement> plt_drp_list=dropselect(getElement(ElementConstants.PLTDROP));
		int hdcountnwintsum=0;
		int hdcounttdysum=0;
		
//		//for tracker page
		System.out.println("hdcnt now "+hdcountnw);
		if(hdcountnw!=0)
		{
		click(getElement(ElementConstants.TRKSIDENAV));
		String pltName=null;
		sleep(TIME_04);
		
		
		
		
		int flag=0;// count for non zero condtions in headcount now
		for( int s=1;s<plt_drp_list.size();s++)
		{
			Select plt_drp_options=new Select(path(getElement(ElementConstants.PLTDROP)));
			plt_drp_options.selectByIndex(s);
			sleep(TIME_02);
			refresh();
			explicitWaitByxpath(getElement(ElementConstants.TRKHEADCNTNWBTN));
			sleep(TIME_05);
			String hdcountnwTracker=getText(getElement(ElementConstants.TRKHEADCNTNWCNT));
			
			String hdcounttdyTracker=getText(getElement(ElementConstants.TRKHEADCNTTDYCNT));
			int hdcountnwint=Integer.parseInt(hdcountnwTracker);
			int hdcounttdyint=Integer.parseInt(hdcounttdyTracker);
			hdcountnwintsum=hdcountnwintsum+hdcountnwint;
			hdcounttdysum=hdcounttdysum+hdcounttdyint;
			if(hdcountnwint==0)
			  {
				continue;
			  }
			else{
				 flag++;
				
				 int size_flr_drp_now=dropsize(getElement(ElementConstants.TRK_FLR_DRPDWN));
//				 refresh();
				 explicitWaitByxpath(getElement(ElementConstants.TRKHEADCNTNWBTN));
				 sleep(TIME_05);
				 List<WebElement> lvl_options=dropselect(getElement(ElementConstants.TRK_FLR_DRPDWN));
				 List<WebElement> plt_drp_list_temp=dropselect(getElement(ElementConstants.PLTDROP));
//				 String levelNameTemp=null;
//				 levelName=levelNameTemp;
				 Select floor_level_options_now=new Select(path(getElement(ElementConstants.TRK_FLR_DRPDWN)));
				 for( int l=0;l<size_flr_drp_now;l++)
				 {
					 floor_level_options_now.selectByIndex(l);
				     Thread.sleep(TIME_05);
				     int c=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
				     if(count<=c)
				     {
				    	 count=c;
				    	 levelName=lvl_options.get(l).getText();
				     }
				  }
				 if(maxcount>=count)
				 {
					System.out.println("same values"); 
				 }	 
				
				 else{
					 maxcount=count;
					 FlevelName=levelName;
					 FplantName=plt_drp_list_temp.get(s).getText();
				  }	
			  }
			
		   }
		}
		System.out.println("headcount now "+hdcountnwintsum);
		System.out.println("headcount today "+hdcounttdysum);
//		count_list.add(hdCountTdy);//headcount today
//		count_list.add(hdCountNow);//headcount now
//		
		
		System.out.println("maxcount "+maxcount);
		if(maxcount==0){
			FinTrackHdCnt="No data";// check condition	
			
		}
		else{
		    FinTrackHdCnt=FplantName+" "+"Level "+FlevelName+" "+ maxcount;
		}
		System.out.println(FinTrackHdCnt);
//		 count_list.add(FinTrackHdCnt);//Tracker (Level with most headcount)
//		 click(getElement(ElementConstants.TRKSIDENAV));
//		 Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
//		 plt_drp_options_01.selectByIndex(1);
//		 sleep(TIME_08);
//		 
//		 if(isVisible(getElement(ElementConstants.TRK_SITE))){
//			 count_list.add("Displayed");	// tracker map
//		 }
//		 else{
//			 count_list.add("Not Displayed"); 
//		 }

		 
//		 System.out.println("map count "+map_count+"drp size "+(plt_drp_list.size()-1));
//		 if(map_count==plt_drp_list.size()){
//			 count_list.add("Displayed");	// tracker map
//		}
//		 else{
//			 count_list.add("Not Displayed"); 
//		 }
//		 Select plt_drp_options=new Select(path(getElement(ElementConstants.PLTDROP)));
//		 plt_drp_options.selectByIndex(0);
//		 click(getElement(ElementConstants.ISSSIDENAV));
//		 sleep(TIME_05);
//		 int alerts_today=Integer.parseInt(alertsTdy);
//		 if(alerts_today!=0){
//			 if(isVisible(getElement(ElementConstants.ALTS_CHART))){
//				 count_list.add("Displayed"); //alerts chart
//				}
//				 else{
//					 count_list.add("Not Displayed"); 
//				 }
//		 }
//		 else{
//			 count_list.add("Not Displayed"); 
//		 }
//		 //for people page
//		 
//		int c=0;
//		 for (int i = 0; i < 3; i++) {
//			 c++;
//			 click(getElement(ElementConstants.PEPSIDENAV));
//			 sleep(TIME_05);
//			 int peopCount=Integer.parseInt(getText(getElement(ElementConstants.PEOPLE_ALL_CNT)));
//			 String hrs_clcked="0 h 0 m";
//			 if(isVisible(getElement(ElementConstants.PEOPLE_PAG))){
//			 System.out.println("pagination is displayed");
//			 peoplePagination(peopCount);
//			 }
//			 else{
//				 System.out.println("pagination is not  displayed"); 
//			 }
//			 Thread.sleep(TIME_03);
//			 clickOverlap(getElement(ElementConstants.PEOPLE_TILE_PIC));
//			 Thread.sleep(TIME_08);
//			 click(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK));
//			 Thread.sleep(TIME_08);
//			 
//			 
//			 String emp_name=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
//			 System.out.println(emp_name+""+Dom+""+Env);
//			 if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_HRS_CLK))){
//			    hrs_clcked=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_HRS_CLK));
//			    System.out.println();
//			 }
//			 if(!hrs_clcked.equals("0 h 0 m")){
// 				 System.out.println("hrs clocked "+hrs_clcked);
//				 count_list.add(hrs_clcked);
//				 break;
//			 }
//			 if(c==3&&hrs_clcked.equals("0 h 0 m")){
//				 count_list.add(hrs_clcked);
//			 }
//		}
//		   
//		
//		 //device page
//		 for (int i = 0; i < 3; i++) {
//		 click(getElement(ElementConstants.DEVSIDENAV));
//		 Thread.sleep(TIME_07);
//		 if(isVisible(getElement(ElementConstants.DEV_PAG))){
//		 devicePagination();
//		 }
//		 List<WebElement>batteryList=getelements(getElement(ElementConstants.DEV_BTRY));
//		 List<String>batteryListString=new ArrayList<>();
//		 String batteryPercent="0";
//		 for (WebElement webElement : batteryList) {
//			batteryListString.add(webElement.getText());
//			
//		}
//		
//		 batteryListString.removeAll(Arrays.asList("",null));;
//		 for (int j = 0; j <batteryListString.size(); i++) {
//			 if(batteryListString.get(j).trim()!="0"){
//				 
//				 batteryPercent= batteryListString.get(j);
//				 break;
//			 }
//			 else{
//				 devicePagination();
//			 }
//		 }
//		 for (String string : batteryListString) {
//				System.out.println(batteryListString);
//			}
//		 String batteryper=batteryPercent;
//		 if(!batteryper.equals("0")){
//			 System.out.println("battery perc "+batteryper);
//			 count_list.add(batteryper);
//			 break;
//		 }
//	   }
//		 count_list.add("Support");
		 count_list.add(dateTimearr[0]);//date
		 count_list.add(dateTimearr[1]);//time
		 count_list.add(Env);//env
		 count_list.add(Dom);//dom
		 count_list.add(Integer.toString(hdcounttdysum));//headcount today
		 count_list.add(Integer.toString(hdcountnwintsum));//headcount now
		 count_list.add(alertsTdy);//alerts today
		 count_list.add(FinTrackHdCnt);//Tracker (Level with most headcount)
		 click(getElement(ElementConstants.TRKSIDENAV));
		 Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
		 plt_drp_options_01.selectByIndex(1);
		 sleep(TIME_08);
		 
		 if(isVisible(getElement(ElementConstants.TRK_SITE))){
			 count_list.add("Displayed");	// tracker map
		 }
		 else{
			 count_list.add("Not Displayed"); 
		 }
		 //events chart
		 Select plt_drp_options=new Select(path(getElement(ElementConstants.PLTDROP)));
		 plt_drp_options.selectByIndex(0);
		 click(getElement(ElementConstants.ISSSIDENAV));
		 sleep(TIME_05);
		 System.out.println("issue page");
		 int alerts_today=Integer.parseInt(alertsTdy);
		 if(alerts_today!=0){
			 if(isVisible(getElement(ElementConstants.ALTS_CHART))){
				 count_list.add("Displayed"); //alerts chart
				}
				 else{
					 count_list.add("Not Displayed"); 
				 }
		 }
		 else{
			 count_list.add("Not Displayed"); 
		 }
		//for people page
		 
			int c=0;
			 for (int i = 0; i < 3; i++) {
				 c++;
				 click(getElement(ElementConstants.PEPSIDENAV));
				 System.out.println("people page");
				 sleep(TIME_05);
				 int peopCount=Integer.parseInt(getText(getElement(ElementConstants.PEOPLE_ALL_CNT)));
				 String hrs_clcked="0 h 0 m";
				 if(isVisible(getElement(ElementConstants.PEOPLE_PAG))){
				 System.out.println("pagination is displayed");
				 peoplePagination(peopCount);
				 }
				 else{
					 System.out.println("pagination is not  displayed"); 
				 }
				 Thread.sleep(TIME_03);
				 clickOverlap(getElement(ElementConstants.PEOPLE_TILE_PIC));
				 Thread.sleep(TIME_08);
				 click(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK));
				 Thread.sleep(TIME_08);
				 
				 
				 String emp_name=getText(getElement(ElementConstants.PEOPLE_PRF_NAME));
				 System.out.println(emp_name+""+Dom+""+Env);
				 if(isVisible(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_HRS_CLK))){
				    hrs_clcked=getText(getElement(ElementConstants.PEOPLE_EMP_DET_HRS_CLK_SEL_DATE_HRS_CLK));
				    System.out.println();
				 }
				 if(!hrs_clcked.equals("0 h 0 m")){
	 				 System.out.println("hrs clocked "+hrs_clcked);
					 count_list.add(hrs_clcked);
					 break;
				 }
				 if(c==3&&hrs_clcked.equals("0 h 0 m")){
					 count_list.add(hrs_clcked);
				 }
			}
			   
			
			 //device page
			 for (int i = 0; i < 3; i++) {
			 click(getElement(ElementConstants.DEVSIDENAV));
			 Thread.sleep(TIME_07);
			 if(isVisible(getElement(ElementConstants.DEV_PAG))){
			 devicePagination();
			 }
			 List<WebElement>batteryList=getelements(getElement(ElementConstants.DEV_BTRY));
			 List<String>batteryListString=new ArrayList<>();
			 String batteryPercent="0";
			 for (WebElement webElement : batteryList) {
				batteryListString.add(webElement.getText());
				
			}
			
			 batteryListString.removeAll(Arrays.asList("",null));;
			 for (int j = 0; j <batteryListString.size(); i++) {
				 if(batteryListString.get(j).trim()!="0"){
					 
					 batteryPercent= batteryListString.get(j);
					 break;
				 }
				 else{
					 devicePagination();
				 }
			 }
			 for (String string : batteryListString) {
					System.out.println(batteryListString);
				}
			 String batteryper=batteryPercent;
			 if(!batteryper.equals("0")){
				 System.out.println("battery perc "+batteryper);
				 count_list.add(batteryper);
				 break;
			 }
		   }
			 count_list.add("Support");
		  
//		 tearDown();
		 return count_list;
		 
  }
}

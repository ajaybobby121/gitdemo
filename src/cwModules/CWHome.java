package cwModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



import constants.ElementConstants;
import baseClass.AppDriver;
import baseClass.ExcelDriver;

public class CWHome extends AppDriver{
	
	AppDriver ApDrv=new AppDriver();
	static String propFileName = "C:\\Users\\148972\\Downloads\\CWAutomation\\CWAutomation\\src\\elements\\Locators.properties";
	public static final int TIME_15 = 15000;
	public static final int TIME_01 = 1000;
	public static final int TIME_02 = 2000;
	public static final int TIME_03 = 3000;
	public static final int TIME_04 = 4000;
	public static final int TIME_05 = 5000;
	public static final int TIME_07 = 7000;
	public static final int TIME_08 = 8000;
	public static final int TIME_10 = 10000;
	
	public static final int TIME_20 = 20000;
	public static final int TIME_25=25000;
	public static final int TIME_30 = 30000;
	public static final int TIME_99 = 120000;
	
	ExcelDriver rdxlTab = new ExcelDriver();
	public CWHome() throws IOException, InterruptedException{
		super(propFileName);
	}
	
	public List<String> home_SideNav() throws InterruptedException{
		List<String> home_count_list= new ArrayList<String>();
		System.out.println("START - 1. home_SideNav");		
		sleep(TIME_04);	
		String urlhome=ApDrv.getcurrenturl();
		if(urlhome.equals("https://navisafeqaapp.azurewebsites.net/#/home")){
			System.out.println("Logged to Navisafe");
			home_count_list.add("Pass");//url-1
			home_count_list.add("Pass");//domain-2
			home_count_list.add("Pass");//username-3
			home_count_list.add("Pass");//password-4
			home_count_list.add("Pass");//Login-5
			home_count_list.add("Pass");//home page-6
		}
		else{
			System.out.println("CANNOT lOGIN");
			home_count_list.add("Failed");//url-1
			home_count_list.add("Failed");//domain-2
			home_count_list.add("Failed");//username-3
			home_count_list.add("Failed");//password-4
			home_count_list.add("Failed");//Login-5
			home_count_list.add("Failed");//home page-6
		}
		
		List<List<String>> searchlist=rdxlTab.ReadExcelTab("CW.xlsx", "Plant_Search",1);
		int i=0;
		int j=0;
		
		int zon_sum=0;
		int hme_cnt_now_sum=0;
		int companies_sum=0;
		
		int tot_hrs_sum_final=0;
		String total_hrs_sum_array[]=new String[5];
		int hme_cnt_today_sum=0;
//		int overtime_sum=0;
		int peop_risk_sum=0;
		int high_risk_zone_sum=0;
		int comp_count_sum=0;
		int supr_count=0;
		int safety_pers=0;
		int act_alert_sum=0;
		int tod_alert_sum=0;
		int size_plt_dro=dropsize(getElement(ElementConstants.PLTDROP));
		int a=0;
		List<WebElement> plt_drp_list=dropselect(getElement(ElementConstants.PLTDROP));
		Select plt_drp_options=new Select(path(getElement(ElementConstants.PLTDROP)));
		for( int s=1;s<plt_drp_list.size();s++){
			System.out.println(plt_drp_list.size());
			plt_drp_options.selectByIndex(s);
			a++;

	       sleep(TIME_10);
	       try{
	       zon_sum=zon_sum+Integer.parseInt(getText(getElement(ElementConstants.HMEZONES)));
	       System.out.println("zone"+zon_sum);
	       }catch(NumberFormatException e){
	    	 System.out.println("exception");   
	       }
	       hme_cnt_now_sum=hme_cnt_now_sum+Integer.parseInt(getText(getElement(ElementConstants.HMEHEADCOUNTNOW)));
	       String total_hrs_sum=getText(getElement(ElementConstants.HMEHOURSCLOCKED));
	       System.out.println("total_hrs_sum" +total_hrs_sum);
	       
	       if( total_hrs_sum.equals("0 h 0 m")){
//	    	   tot_hrs_sum_final=0;
	    	   System.out.println("null");
	       }
	       else{
	       total_hrs_sum_array=splitTimeString(total_hrs_sum);
	       tot_hrs_sum_final=tot_hrs_sum_final+timeconversion(total_hrs_sum_array);
	       }
	       hme_cnt_today_sum=hme_cnt_today_sum+Integer.parseInt(getText(getElement(ElementConstants.HMEHEADCOUNTTODAY)));
	       
	       peop_risk_sum=peop_risk_sum+Integer.parseInt(getText(getElement(ElementConstants.HMEPEOPLEATRISKZONE)));
	       high_risk_zone_sum=high_risk_zone_sum+Integer.parseInt(getText(getElement(ElementConstants.HMEHIGHRISKZONE)));
	       comp_count_sum=comp_count_sum+Integer.parseInt(getText(getElement(ElementConstants.HMECONTRACTORS)));
	       System.out.println(plt_drp_list.get(s).getText()+"-"+s+comp_count_sum);
           supr_count=supr_count+Integer.parseInt(getText(getElement(ElementConstants.HMESUPERVISORSNOW)));   
           System.out.println("supr"+"-"+s+supr_count);
           safety_pers=safety_pers+Integer.parseInt(getText(getElement(ElementConstants.HMESAFETYPERSONNEL)));
           act_alert_sum=act_alert_sum+Integer.parseInt(getText(getElement(ElementConstants.HMEACTALERTS)));
           System.out.println(act_alert_sum);
           tod_alert_sum=tod_alert_sum+Integer.parseInt(getText(getElement(ElementConstants.HMETOTALERTS)));
           System.out.println(tod_alert_sum);
           j++;
		}
	        
	        
		
//		Select options2=new Select(path(getElement(ElementConstants.PLTDROP)));
		plt_drp_options.selectByIndex(0);
		sleep(TIME_08);
	 
		//Getting values from tiles
        String Zone_cnt=getText(getElement(ElementConstants.HMEZONES));
        String Home_cnt_now=getText(getElement(ElementConstants.HMEHEADCOUNTNOW));
        String Home_cnt_today=getText(getElement(ElementConstants.HMEHEADCOUNTTODAY));
		String hrs_clocked_client=getText(getElement(ElementConstants.HMEHOURSCLOCKED));
//		String overtime_client=getText(getElement(ElementConstants.HMEOVERTIME));
		String peop_risk_client=getText(getElement(ElementConstants.HMEPEOPLEATRISKZONE));
		String high_risk_zone_client=getText(getElement(ElementConstants.HMEHIGHRISKZONE));
//		String comp_client=getText(getElement(ElementConstants.HMECONTRACTORS));
	    String superv_client=getText(getElement(ElementConstants.HMESUPERVISORSNOW));
	    String safety_person=getText(getElement(ElementConstants.HMESAFETYPERSONNEL));;
	    System.out.println("i");//remove
	    String rec_alerts_count=getText(getElement(ElementConstants.HME_REC_ALERT_COUNT));
		
		String Act_alert=getText(getElement(ElementConstants.HMEACTALERTS));
		String Tot_alert=getText(getElement(ElementConstants.HMETOTALERTS));
	    String company_client=getText(getElement(ElementConstants.HMECONTRACTORS));
	    
	    
//	    click(getElement(ElementConstants.HMECONTRACTORS));
//	    sleep(TIME_04);
//	    String company_page=getText(getElement(ElementConstants.CONCNT));
//	    previouspage();
	    sleep(TIME_04);
	    //converting every values to int 
	    int Zone_cnt_client=Integer.parseInt(Zone_cnt);
		int Head_Cnt_Now=Integer.parseInt( Home_cnt_now);
		int Head_Cnt_Today=Integer.parseInt(Home_cnt_today);
		int hrs_clocked_array_client_mns=0;
		if(!hrs_clocked_client.equals("0")){
		String hrs_clocked_array_client[]=splitTimeString(hrs_clocked_client);
		 hrs_clocked_array_client_mns=timeconversion(hrs_clocked_array_client);
		}
		else{
			 hrs_clocked_array_client_mns=0;
			
		}
		
		
//		int overtime_client_int=Integer.parseInt(overtime_client);
		int peop_risk_client_int=Integer.parseInt(peop_risk_client);
		int high_risk_zone_client_int=Integer.parseInt(high_risk_zone_client);
		int superv_client_int=Integer.parseInt(superv_client);
		int safety_person_client=Integer.parseInt(safety_person);
		int Act_alerts_client=Integer.parseInt(Act_alert);
		int Tot_alerts_client=Integer.parseInt(Tot_alert);
		int Rec_alerts_count= Integer.parseInt(rec_alerts_count);
		int company_client_int= Integer.parseInt(company_client);
		
		//printing int values
		System.out.println("zones "+Zone_cnt_client);
		System.out.println("zones sum "+zon_sum);
		System.out.println("head now " +Head_Cnt_Now);//client
		System.out.println("head today " +Head_Cnt_Today);//client
		System.out.println("hme_cnt_now_sum "+hme_cnt_now_sum);//project sum
		System.out.println("hme_cnt_today_sum "+hme_cnt_today_sum);//project sum
//		for (String k : hrs_clocked_array_client) {
//			System.out.println("hrs_clocked_array_client"+k);
//			
//		}
		System.out.println("hrs_clocked_array_client_mns "+hrs_clocked_array_client_mns);//client
		System.out.println("tot_hrs_sum_final "+tot_hrs_sum_final);//project sum
//		System.out.println("overtime_client_int "+ overtime_client_int);//overtime_client
//		System.out.println("overtime_sum" +overtime_client_int);//Overtime_project sum
        System.out.println("peop_risk_client_int"+peop_risk_client_int);//people at risk zones-client
		System.out.println("peop_risk_sum"+peop_risk_sum);//people at risk zone-project
		System.out.println("high_risk_zone_client_int "+high_risk_zone_client_int);//high risk zone-client
		System.out.println("high risk zone sum "+high_risk_zone_sum);//high risk zone-project
		System.out.println("superv_client_int " +superv_client_int);// supervisor -client
		System.out.println("Supervisor sum "+supr_count);//supervisor-project sum
	    System.out.println("safety_person_client "+safety_person_client);//safety -client
		System.out.println("Safety personnel sum "+safety_pers);//safety-pro sum
		System.out.println("Active alerts client "+Act_alerts_client);
		System.out.println("Active alerts sum "+act_alert_sum);
		System.out.println("total alerts client "+Tot_alerts_client);
		System.out.println("total alerts sum "+tod_alert_sum);
		System.out.println("Rec alerts count "+Rec_alerts_count);
//		int count=0;
//		if(isVisible(getElement(ElementConstants.MENUBTN)))
//		{
//	    click(getElement(ElementConstants.MENUBTN));
//        sleep(TIME_02);
//        
//		if(isVisible(getElement(ElementConstants.SIDENAV))==true)
//		{
//			System.out.println("Toggle button not working");
//		}
//		else
//		{
//			System.out.println("Toggle button working ");
//			click(getElement(ElementConstants.MENUBTN));
//		    count++;
//		}
//		}
//		else{
//			System.out.println("menu button not displayed");
//		}
		if(isVisible(getElement(ElementConstants.NAVIICON))==true)
		{
			home_count_list.add("Pass");// icon-7
    		
		}
		else{
			home_count_list.add("Failed");
		}
//		if(count==2){
//			home_count_list.add("Pass");//toggle button and icon-7
//            		
//		}
//		else{
//			home_count_list.add("Failed");//toggle button and icon-7
//		}
//		if(isVisible(getElement(ElementConstants.))){
//			home_count_list.add("Pass");//menu line-8
//			System.out.println("Menu Line is displayed");
//		}
//		else{
//			home_count_list.add("Failed");//menu line-8
//			System.out.println("Menu Line is not displayed");
//			
//		}
		home_count_list.add("Disabled");//menu line
	
//		if(isVisible(getElement(ElementConstants.WTHRDET))==true){
//			System.out.println("Weather displayed");
//			home_count_list.add("Pass");//weather details-9
//		}
//		else{
//			home_count_list.add("Failed");//weather details-9
//		}
//		click(getElement(ElementConstants.WTHRDET));
//		sleep(TIME_03);
//		ApDrv.switchwindow();
//		String weather=ApDrv.getcurrenturl();
//		System.out.println(ApDrv.getcurrenturl());
//		ApDrv.switchbacktoMainWindow();
//		if(weather.equals("https://openweathermap.org/city/1880252")){
//			System.out.println("Weather page redirected");
//			home_count_list.add("Pass");//weather redirection-10
//			
//		}
//		else{
//			System.out.println("Weather page not redirected");
//			home_count_list.add("Failed");//weather redirection-10
//		}
		if(isVisible(getElement(ElementConstants.ENVICON))){
			System.out.println("logo is displayed");
			home_count_list.add("Pass");//logo-9
		}
		else{
			System.out.println("logo not displayed");
			home_count_list.add("Failed");//logo-9
		}
		if(isEnabled(getElement(ElementConstants.EVACICON))){
			System.out.println("All three icons are displayed");
			home_count_list.add("Pass");//icons-10
		}
		else{
			System.out.println("All three icons not displayed");
			home_count_list.add("Failed");//icons-10
		}
		if(isVisible(getElement(ElementConstants.DMAINHOM))==true)
		{
			System.out.println("domain is displayed");
			home_count_list.add("Pass");//domain name-11
		}
		else{
			System.out.println("domain not displayed");
			home_count_list.add("Failed");//domain name-11
			
		}
		if(isVisible(getElement(ElementConstants.USERNAMEHME))==true)
		{
			System.out.println("username is displayed");
			home_count_list.add("Pass");//username-12
		}
		else{
			System.out.println("username is not displayed");
			home_count_list.add("Failed");//username-12
			
		}
//		
		
//		  DateFormat dateFormat=new SimpleDateFormat("MMM dd yyyy hh:mm:ss aa");
//	      String date=dateFormat.format(new Date());
//	      String home_date=getText(getElement(ElementConstants.DATE));
//	      System.out.println(date);
//	      System.out.println(home_date);
		if(isVisible(getElement(ElementConstants.DATE))==true)
		{
			System.out.println("time stamp is displayed");
			home_count_list.add("Pass");//time stamp-13
		}
		else{
			System.out.println("time stamp is not displayed");
			home_count_list.add("Failed");//time stamp-13
			
		}
		if(isVisible(getElement(ElementConstants.PROICON))==true)
		{
			System.out.println("profile icon is displayed");
			home_count_list.add("Pass");//profile-14
		}
		else{
			System.out.println("profile icon is displayed");
			home_count_list.add("Failed");//profile-14
			
		}
		
//		plants
		String Plant1=getText(getElement(ElementConstants.HMEPROJECT));
		
		click(getElement(ElementConstants.PJTSIDENAV));
		sleep(TIME_04);
		String Plant2=getText(getElement(ElementConstants.PLTCNT));
		
		System.out.println(Plant1);
		System.out.println(Plant2);
		previouspage();
		sleep(TIME_10);
		if(Plant1.equals(Plant2)){
			System.out.println("plant count is same");
			home_count_list.add("Pass");//plant count-15
		}
		else{
			System.out.println("not same");
			home_count_list.add("Failed");//plant count-15
		}
		
		click(getElement(ElementConstants.HMEPROJECT));
		sleep(TIME_04);
		String planturl=ApDrv.getcurrenturl();
		if(planturl.equals("https://navisafeqaapp.azurewebsites.net/#/project")){
			System.out.println("Redirected to plant page");
			home_count_list.add("Pass");//plant redirection-16
			previouspage();
			
		}
		else{
			System.out.println("Not redirected to plants page");
			home_count_list.add("Failed");//plant redirection-16
			
		}
		//zones
		previouspage();
		sleep(TIME_10);
		
//		if(isVisible(getElement(ElementConstants.HMEZONES)))
//		{
//			home_count_list.add("Pass");//zone count-17
//		}
//		else{
//			home_count_list.add("Failed");//zone count-17
//		}
		
		
		
		
		if(zon_sum==Zone_cnt_client){
			System.out.println("zone count is matching");
			home_count_list.add("Pass");//zone count-17
		}
		else{
			System.out.println("zone count is not matching");
			home_count_list.add("Failed");//zone count-17
			
		}
		List<WebElement> plt_drp_list_01=dropselect(getElement(ElementConstants.PLTDROP));
		Select options3=new Select(path(getElement(ElementConstants.PLTDROP)));
		options3.selectByIndex(2);
		sleep(TIME_04);
        String Plant_1=plt_drp_list_01.get(2).getText().trim();
//        click(getElement(ElementConstants.HMEUST));
//        sleep(TIME_05);
		click(getElement(ElementConstants.HMEZONES));
		
		sleep(TIME_05);
//		String plant_01=getText(getElement(ElementConstants.PLTNAME));
		if(getcurrenturl().equals("https://navisafeqaapp.azurewebsites.net/#/home")){
			System.out.println("zone redirection disabled passed");//zone redirection-19
			home_count_list.add("Pass");//zone redirection-18

		}
		else{
			home_count_list.add("Failed");//zone redirection-18
			System.out.println("zone redirection disabled failed");
			sleep(TIME_05);
			previouspage();
		    sleep(4000);

		}
		//headcount now
		
       
		if(Head_Cnt_Now==hme_cnt_now_sum)
		{
			home_count_list.add("Pass");//headcount now-19
			System.out.println("headcount now is matching");
			
		}
		else
		{
			home_count_list.add("Failed");//headcount now-19
			System.out.println("headcount now is not matching");
		}
		//headcount now redirection
		click(getElement(ElementConstants.HMEHEADCOUNTNOW));
		sleep(TIME_05);
		String Plant_01=getText(getElement(ElementConstants.TRKPLNTNAME));
		System.out.println(Plant_01+"name");
		System.out.println(Plant_1+"name");
		
		
		if((Plant_1).equals(Plant_01)){
			home_count_list.add("Pass");//headcount now redirection-20
			System.out.println("Headcount now redirection passed");
		    }
		else{
		
			home_count_list.add("Failed");//headcount now redirection-20
			System.out.println("Headcount now redirection failed");
		 }
		
		
		//companies
		previouspage();
        sleep(4000);
//		String Cont_home=getText(getElement(ElementConstants.HMECONTRACTORS));
		
		System.out.println(company_client_int);
		System.out.println(comp_count_sum);
		if(company_client_int==comp_count_sum){
			System.out.println("company count is same");
			home_count_list.add("Pass");//Company count-21
			
		}
		else{
			System.out.println("Company count mismatch");
			home_count_list.add("Failed");//Company count-21
			sleep(TIME_02);
		}
		previouspage();
		sleep(TIME_05);
		click(getElement(ElementConstants.HMECONTRACTORS));
		sleep(TIME_04);
		String Cont_url=ApDrv.getcurrenturl();
		if(Cont_url.equals("https://navisafeqaapp.azurewebsites.net/#/contractor")){
			System.out.println("redirected to Company page");
			home_count_list.add("Pass");//Company redirection-22
			sleep(TIME_02);
			previouspage();
		}
		else{
			System.out.println("Not Redirected to contractor page");
			home_count_list.add("Failed");//Company redirection-22
		}
		

		//hrs clocked
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
				
	    if(hrs_clocked_array_client_mns==tot_hrs_sum_final){
		 System.out.println("total hrs matching");
		 home_count_list.add("Pass");//total hours clocked-23
			    	
		}
	    else{
		 home_count_list.add("Failed");
		 System.out.println("total hrs mismatching");//total hours clocked-23
			    	
	     }
	   click(getElement(ElementConstants.HMEHOURSCLOCKED));
	   sleep(TIME_08);
	   String d2=getText(getElement(ElementConstants.TRKPLNTNAME));
//				String split_plant_name_2[]=splitAllcount(d2);
	   System.out.println(d2);
	   if(Plant_1.equals(d2)){
		  home_count_list.add("Pass");//total hours redirection-24
		  System.out.println("total hours redirection passed");
		}
	   else{
		  home_count_list.add("Failed");//total hours redirection-24
		  System.out.println("total hours redirection failed");
		}
		//headcount today function
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
		if(Head_Cnt_Today==hme_cnt_today_sum){
			home_count_list.add("Pass");//headcount today-25
	     	System.out.println("headcount today is matching");
			
		}
		else
		{ 
			home_count_list.add("Failed");//headcount today-25
			System.out.println("headcount today is not matching");
		}
		click(getElement(ElementConstants.HMEHEADCOUNTTODAY));
		sleep(TIME_03);
//		String head_cnt_tdy_redirection=ApDrv.getcurrenturl();
		if(getcurrenturl().equals("https://navisafeqaapp.azurewebsites.net/#/home")){
			home_count_list.add("Pass");//headcount today redirection-26
			System.out.println("Headcount today redirection disabled passed");
		}
		else{
			home_count_list.add("Failed");//headcount today redirection-26
			System.out.println("Headcount today redirection disabled failed");
			
		}
		
		
//      
		//people at risk zone
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_05);
		if(peop_risk_client_int==peop_risk_sum){
			home_count_list.add("Pass");//people at risk zone-27
			System.out.println("people at risk zone matching");
		}
		else{
			home_count_list.add("Failed");//people at risk zone-27
			System.out.println("people at risk zone not matching");
			
			}
		//people risk zone redirection
		String peop_risk=getText(getElement(ElementConstants.HMEPEOPLEATRISKZONE));
		int peop_risk_int_home=Integer.parseInt(peop_risk);
		click(getElement(ElementConstants.HMEPEOPLEATRISKZONE));
		sleep(TIME_08);
		int peop_risk_int_trak=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
		System.out.println(peop_risk_int_home);
		System.out.println(peop_risk_int_trak);
		
		if((peop_risk_int_home)==(peop_risk_int_trak)){
			home_count_list.add("Pass");//people at risk zone redirection-28
			System.out.println("people at risk zone redirection passed");
		}
		else{
			home_count_list.add("Failed");//people at risk zone redirection-28
			System.out.println("people at risk zone redirection failed");
			}
		
		//high risk zone
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_04);
		if(high_risk_zone_client_int==high_risk_zone_sum){
			home_count_list.add("Pass");//high risk zone-29
			System.out.println("high risk zone is matching");
			
		}
		else{
			home_count_list.add("Failed");//high risk zone-29
			System.out.println("high risk zone is not matching");
		}
		//high risk zone redirection
		click(getElement(ElementConstants.HMEHIGHRISKZONE));
		sleep(TIME_08);
//		String d4=getText(getElement(ElementConstants.PLTNAME));
//		System.out.println(d4);
//		System.out.println(Plant_1);
		if(getcurrenturl().equals("https://navisafeqaapp.azurewebsites.net/#/home")){
			home_count_list.add("Pass");//high risk zone redirection-30
			System.out.println("high risk zone redirection passed");
		}
		else{
			home_count_list.add("Failed");//high risk zone redirection-30
			System.out.println("high risk zone redirection failed");
			}
		
		
		
		//supervisor
		
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
		
		if(superv_client_int==supr_count){
			
			
			home_count_list.add("Pass");//supervisor-31
			System.out.println("supervisor matching");
		}
		else{
			home_count_list.add("Fail");//supervisor-31
			System.out.println("supervisor not matching");
			}
		//supervisor redirection
		int sup_home=Integer.parseInt(getText(getElement(ElementConstants.HMESUPERVISORSNOW)));
		click(getElement(ElementConstants.HMESUPERVISORSNOW));
		sleep(TIME_08);
		int sup_track=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
		
//		String supervisor=dropselect(getElement(ElementConstants.TRKALROLE1));
//		System.out.println(supervisor);
		
		if(sup_home==(sup_track)){
			
		    home_count_list.add("Pass");//32
		    System.out.println("supervisor redirection passed");
		}
		else
		{
			 home_count_list.add("Failed");//32
			 System.out.println("supervisor redirection Failed");
		}
			
		//safety personnel
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
		if(safety_pers==safety_person_client){
			home_count_list.add("Pass");//33
			System.out.println("safety personnel count matching");
			
		}
		else{
			home_count_list.add("Failed");//33
			System.out.println("safety personnel count not matching");
			
		}
		
		//safetypersonnel redirection
		int safty_home=Integer.parseInt(getText(getElement(ElementConstants.HMESAFETYPERSONNEL)));
		click(getElement(ElementConstants.HMESAFETYPERSONNEL));
		sleep(TIME_08);
		int safty_track=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
		System.out.println(safty_home);
		System.out.println(safty_track);
		if(safty_home==(safty_track)){
			
		    home_count_list.add("Pass");//34
		    System.out.println("safety personnel redirection passed");
		}
		else
		{
			 home_count_list.add("Failed");//34
			 System.out.println("safety personnel redirection Failed");
		}

		
		//active alerts
		
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
		if(act_alert_sum==Act_alerts_client){
			home_count_list.add("Pass");//Act alerts count-35
			System.out.println("Active alert matching");
		}
		
		else{
			home_count_list.add("Failed");//Act alerts count-35
			System.out.println("Active alert mismatch");
		}
		clickOverlap(getElement(ElementConstants.HMEACTALERTS));
		sleep(TIME_03);
		String act_alerts_redirection=ApDrv.getcurrenturl();
		if(act_alerts_redirection.equals("https://navisafeqaapp.azurewebsites.net/#/alert/1/null")){
			home_count_list.add("Pass");//Act alerts redirection-36
			System.out.println("Act alerts redirection passed");
		}
		else{
			home_count_list.add("Failed");//Act alerts redirection-36
			System.out.println("Act alerts redirection failed");
			
		}
		
	    //total alerts
		clickOverlap(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
		if (Tot_alerts_client==tod_alert_sum) {
			home_count_list.add("Pass");
			System.out.println("Total alert matching");//37
		}
		else{
			home_count_list.add("Failed");
			System.out.println("Total alert mismatching");//37
		}
		
		//total alerts redirection
		clickOverlap(getElement(ElementConstants.HMETOTALERTS));
		sleep(TIME_08);
		String tot_alerts_redirection=ApDrv.getcurrenturl();
		if(tot_alerts_redirection.equals("https://navisafeqaapp.azurewebsites.net/#/alert/0/null")){
			home_count_list.add("Pass");//Act alerts redirection-38
			System.out.println("Tot alerts redirection passed");
		}
		else{
			home_count_list.add("Failed");//Act alerts redirection-38
			System.out.println("Tot alerts redirection failed");
			
		}
//		for (int k = 0; k < 11; k++) {
//			home_count_list.add("Not Feasible");//graph validation 39-49
//		}
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_08);
//		Select plt_drp_options_1=new Select(path(getElement(ElementConstants.PLTDROP)));
//		plt_drp_options_1.selectByIndex(0);
		explicitWaitByxpath(getElement(ElementConstants.HMEHEADCOUNTNOW));
		String hd_cnt=getText(getElement(ElementConstants.HMEHEADCOUNTNOW));
		if(hd_cnt.equals("0")){
			if(isVisible(getElement(ElementConstants.HME_SEC_GRPH_NODATA))&&isVisible(getElement(ElementConstants.HME_SEC_GRPH_NODATA))&&isVisible(getElement(ElementConstants.HME_SEC_GRPH_NODATA))){
				home_count_list.add("Pass");
				home_count_list.add("Pass");
				home_count_list.add("Pass");
			}
			else{
				home_count_list.add("Failed");
				home_count_list.add("Failed");
				home_count_list.add("Failed");
			}
		}
		else{
	    String graph1_head=getText(getElement(ElementConstants.HME_SEC_GRPH_HDR));
	    String graph2_head=getText(getElement(ElementConstants.HME_COMP_GRPH_HDRS));
	    String graph3_head=getText(getElement(ElementConstants.HME_PEOP_GRPH_HDRS));
	    if(graph1_head.equalsIgnoreCase("Section HeadCount Now")&&isVisible(getElement(ElementConstants.HME_SEC_GRPH))){
	    	home_count_list.add("Pass");//39 section headcnt chart
	    }
	    else{
	    	home_count_list.add("Failed");
	    }
	    home_count_list.add("Not Feasible");//40
	    home_count_list.add("Not Feasible");//41
	    if(graph2_head.equalsIgnoreCase("Company HeadCount Now")&&isVisible(getElement(ElementConstants.HME_COMP_GRPH))){
	    	home_count_list.add("Pass");//42 cont headcnt chart
	    }
	    else{
	    	home_count_list.add("Failed");
	    }
	    home_count_list.add("Not Feasible");//43
	    home_count_list.add("Not Feasible");//44
	    if(graph3_head.equalsIgnoreCase("People In Zone Now")&&isVisible(getElement(ElementConstants.HME_PEOP_GRPH))){
	    	home_count_list.add("Pass");//45 people headcnt chart
	    }
	    else{
	    	home_count_list.add("Failed");
	    }
		}
	    home_count_list.add("Not Feasible");//46
	    home_count_list.add("Not Feasible");//47
	    home_count_list.add("Not Feasible");//48
	    home_count_list.add("Not Feasible");//49
		//Recent alerts count
		
		if(Act_alerts_client==Rec_alerts_count){
			System.out.println(Act_alerts_client+","+Rec_alerts_count);
			home_count_list.add("Pass");//Rec alerts count-50
			System.out.println("Rec alerts count matching");
		}
		else{
			home_count_list.add("Failed");//Rec alerts count-50
			System.out.println("Rec alerts count not matching");
		}
		
		//alert drop down
		
		int alert_count=drpdownwoselect(getElement(ElementConstants.HMENOTIFICATION));
		String rec_alerts_count_01=getText(getElement(ElementConstants.HME_REC_ALERT_COUNT));
		int Rec_alerts_count_01= Integer.parseInt(rec_alerts_count_01);
		System.out.println(alert_count+"count"+Rec_alerts_count_01);
		if(alert_count==Rec_alerts_count_01){
			home_count_list.add("Pass");//alert drpdown-51
			System.out.println("Alert drpdown functionalties working");
		}
		else{
			home_count_list.add("Failed");//alert drpdown-51
			System.out.println("Alert drpdown functionalities not working");
		}
		sleep(TIME_07);
		if(isVisible(getElement(ElementConstants.HME_ALERT_TABLE)))
		{
		//Recent 5 alerts
		int hme_table_rows=getRowcount(getElement(ElementConstants.HME_TABLE));
		System.out.println(hme_table_rows);
		if(hme_table_rows==5&&Tot_alerts_client>5){
			home_count_list.add("Pass");//Recent 5 alerts-52
			System.out.println("recent 5 alerts passed");
		}
		else if(Tot_alerts_client<5){
			home_count_list.add("Not Feasible");//Recent 5 alerts-52
		}
		else{
			home_count_list.add("Failed");//Recent 5 alerts-52
			System.out.println("recent alerts failed");
		}
		//Recent alerts details table
		
		
		List<WebElement> table_row=getelements(getElement(ElementConstants.HME_REC_ALERT_TABLE));
		
		
		if(table_row.get(0).getText().equalsIgnoreCase("TIME")){
			home_count_list.add("Pass");//Recent alert-Event time-53
		}
		else{
			home_count_list.add("Failed");//Recent alert-Event time-53
		}
		if(table_row.get(1).getText().equalsIgnoreCase("TYPE")){
			home_count_list.add("Pass");//Recent alert-Alert type-54
		}
		else{
			home_count_list.add("Failed");//Recent alert-Alert type-54
		}
		if(table_row.get(2).getText().equalsIgnoreCase("PERSONNEL")){
			home_count_list.add("Pass");//Recent alert-Personnel-55
		}
		else{
			home_count_list.add("Failed");//Recent alert-Personnel-55
		}
		if(table_row.get(3).getText().equalsIgnoreCase("SECTION")){
			home_count_list.add("Pass");//Recent alert-SECTION-56
		}
		else{
			home_count_list.add("Failed");//Recent alert-SECTION-56
		}
		if(table_row.get(4).getText().equalsIgnoreCase("COMPANY")){
			home_count_list.add("Pass");//Recent alert-COMPANY-57
		}
		else{
			home_count_list.add("Failed");//Recent alert-COMPANY-57
		}
		if(table_row.get(5).getText().equalsIgnoreCase("ZONE")){
			home_count_list.add("Pass");//Recent alert-ZONE-58
		}
		else{
			home_count_list.add("Failed");//Recent alert-ZONE-58
		}
		if(table_row.get(6).getText().equalsIgnoreCase("SITE")){
			home_count_list.add("Pass");//Recent alert-SITE-59
		}
		else{
			home_count_list.add("Failed");//Recent alert-SITE-59
		}
		home_count_list.add("Not Feasible");//60
		if(table_row.get(7).getText().equalsIgnoreCase("LOCATE")){
			home_count_list.add("Pass");//Recent alert-LOCATE-61
		}
		else{
			home_count_list.add("Failed");//Recent alert-LOCATE-61
		}
		//getting data from the first row
		String alert_name=getText(getElement(ElementConstants.HME_TAB_ALT_TYPE));
        String alert_time=getText(getElement(ElementConstants.HME_TAB_ALT_TIME));
        String alert_emp_name=getText(getElement(ElementConstants.HME_TAB_ALT_EMPNAME));
        String alert_emp_cmpny=getText(getElement(ElementConstants.HME_TAB_ALT_CMP));
        String alert_emp_section=getText(getElement(ElementConstants.HME_TAB_ALT_SEC));
		if(isVisible(getElement(ElementConstants.HME_REC_ALERT_LOCATE))){
		clickOverlap(getElement(ElementConstants.HME_REC_ALERT_LOCATE));
		if(isVisible(getElement(ElementConstants.HME_ALERT_POPUP))){
			home_count_list.add("Pass");//pop up-62
			System.out.println("alert pop up is displayed");
		}
		else{
			home_count_list.add("Failed");//62
			
		}
		}
		else{
			System.out.println("Failed");
			home_count_list.add("Failed");//62
		}
		Thread.sleep(TIME_08);
		if(isVisible(getElement(ElementConstants.HME_ALERT_MAP))){
			home_count_list.add("Pass");//site marking-63
			System.out.println("map is displayed");
		}
		else{
			home_count_list.add("Failed");
			System.out.println("map is not displayed");//site marking-63
		}
		String alert_popup_name=ApDrv.getText(ApDrv.getElement(ElementConstants.HME_ALERT_POPUP_ALTNME));
        String alert_pop_time=ApDrv.getText(ApDrv.getElement(ElementConstants.HME_ALERT_POPUP_ALTTIME));
        String alert_pop_emp_name=ApDrv.getText(ApDrv.getElement(ElementConstants.HME_ALERT_POPUP_EMP_NME));
        String alert_pop__cmp=ApDrv.getText(ApDrv.getElement(ElementConstants.HME_ALERT_POPUP_COMPANY));
//		String alert_name_array[]=table_row.get(1).getText().split(" ");
//        String alert_name[]=alert_name_array[2].split("\\R");
//        System.out.println(alert_name[1]);
//        String alert_popup_name=getText(getElement(ElementConstants.HME_ALERT_POPUP_ALTNME));
//        System.out.println(alert_popup_name);
//        if(alert_name[1].equals(alert_popup_name)){
//        	home_count_list.add("Pass");//alertname-65
//        	System.out.println("alert name is same");
//        }
//        else{
//        	home_count_list.add("Failed");//65
//        	System.out.println("alert name is not same");
//        }
        System.out.println(alert_name);
        System.out.println(alert_popup_name);
        if(alert_name.equalsIgnoreCase(alert_popup_name)){
        	home_count_list.add("Pass");
        	System.out.println("alert name passed");//alertname-64
        }
        else{
        	home_count_list.add("Failed");
        	System.out.println("alert name failed");//alertname-64
        }
        if(isVisible(getElement(ElementConstants.HME_ALER_POPUP_LASTSEEN))){
        	home_count_list.add("Pass");//last seen-65
        }
        else{
        	home_count_list.add("Failed");//last seen-65
        }
        if(isVisible(getElement(ElementConstants.HME_ALERT_POPUP_LOCATION))){
        	home_count_list.add("Pass");//location-66
        }
        else{
        	home_count_list.add("Failed");//location-66
        }
        System.out.println(alert_emp_name);
        System.out.println(alert_pop_emp_name);
        if(alert_emp_name.equals(alert_pop_emp_name)){
        	
        	home_count_list.add("Pass");//emp name-67
        	System.out.println("emp name passed");
        }
        else{
        	home_count_list.add("Failed");//emp name-67
        	System.out.println("emp name failed");
        }
        home_count_list.add("Not Feasible");//role-68
        if(isVisible(getElement(ElementConstants.HME_ALERT_POPUP_SUP))){
        	home_count_list.add("Pass");//supervisor-69
        }
        else{
        	home_count_list.add("Failed");//supervisor-69
        }
        System.out.println(alert_emp_cmpny+"cont"+alert_pop__cmp);

        if(alert_emp_cmpny.equals("-----")){
        	if(alert_pop__cmp.equals("Direct Employee")){
        		home_count_list.add("Pass");//contractor-70
        		System.out.println("null passed");
        	}
        	else{
        		home_count_list.add("Failed");//contractor-70
        		System.out.println("null Failed");
        	}
          }
         else{
        	 if(alert_pop__cmp.equals(alert_emp_cmpny)){
        		 home_count_list.add("Pass");//contractor-70
         		System.out.println("passed");
         	}
         	else{
         		home_count_list.add("Failed");//contractor-70
         		System.out.println("Failed");
         	    }
            }
        if(ApDrv.isVisible(ApDrv.getElement(ElementConstants.HME_ALERT_POPUP_EMP_SEC))){
        	home_count_list.add("Pass");//section-71
        	System.out.println("section passed");
        }
        else{
        	home_count_list.add("Failed");//section-71
        	System.out.println("section failed");
        }
        home_count_list.add("Not Feasible");//zone-72
        System.out.println(alert_time+"time"+alert_pop_time);
        if(alert_time.equals(alert_pop_time)){
        	home_count_list.add("Pass");//time-73
        	System.out.println("time passed");
        }
        else{
        	home_count_list.add("Failed");//time-73
        	System.out.println("time failed");
        }
      
        home_count_list.add("Not Feasible");//landmark-74
        home_count_list.add("Not Feasible");//severity-75
        if(isVisible(getElement(ElementConstants.HME_ALERT_POPUP_FORM))){
        	home_count_list.add("Pass");//form -76(c2d)
        	System.out.println("FORM passed");//form-76
        }
        else{
        	home_count_list.add("Failed");////form -76
        	System.out.println("FORM failed");
        }
        clickOverlap(getElement(ElementConstants.HME_ALERT_POPUP_CLOSE));
		//All Alerts
		sleep(TIME_03);
		}
		else{
			for (int k = 0; k < 25; k++) {
				home_count_list.add("Failed");//53-76
			}
		}
//		if(isVisible(getElement(ElementConstants.HME_ALL_ALERTS))){
//		click(getElement(ElementConstants.HME_ALL_ALERTS));
//		String alert_url=getcurrenturl();
//		if(alert_url.equals("https://navisafeqaapp.azurewebsites.net/#/alert/0/null")){
//			home_count_list.add("Pass");
//			System.out.println("redirected to issues page");//77
//		}
//		else{
//			home_count_list.add("Failed");
//			System.out.println("Redirection failed");//77
//		}
//		}
//		else
//		{
//			home_count_list.add("Failed");
//			System.out.println("Redirection failed11");//77
//		}
//		
		home_count_list.add("Disabled");//all alerts 77
		home_count_list.add("Not Feasible");//loaders-78
		Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
		plt_drp_options_01.selectByIndex(0);
		sleep(TIME_05);
	    return home_count_list;
	  }
     
//    public static int timeconversion(String a[]){
//    	int x=Integer.parseInt(a[0])*60;
//    	int y=Integer.parseInt(a[2]);
//    	int sum=x+y;
//    	System.out.println("After sum" +sum);
//    	return sum;
//    	
//    	
//    }
    
 public static String[] splitAllcount(String t){
		
		String timeArray[]=t.split(" - ");   

	    return timeArray;
	}
    
	//commented for sanity testing-part 1
//	public List<String> tracker_SideNav(){
//		//TBD
//		/*List position = 0-HeadCountToday,1-HeadCountNow,2-Fall,
//		  3-Nearmiss,4-Unauthorized,5-Fatigue*/
//		List<String> tracker_count= new ArrayList<String>();
//		System.out.println("START - 2. tracker_titledat");		
//		String retTxt;
//		sleep(TIME_07);
//		click(getElement(ElementConstants.TRKSIDENAV));
//		sleep(TIME_15);
////		retTxt = getText(getElement(ElementConstants.TRKLOC1TXT));
////		retTxt=retTxt.replaceAll("[^0-9]", "");
////		tracker_count.add(retTxt);
////		click(getElement(ElementConstants.TRKLOCICON));	
////		sleep(TIME_04);
//		retTxt = getText(getElement(ElementConstants.TRKNOWCNT));
//		tracker_count.add(retTxt);
//		sleep(TIME_04);
//		click(getElement(ElementConstants.TRKALLDROP));
//    	sleep(TIME_02);
//    	retTxt = getText(getElement(ElementConstants.TRKALLCNT));
//    	tracker_count.add(retTxt);
//    	click(getElement(ElementConstants.TRKALROLE));
//    	sleep(TIME_02);
//    	setValue(getElement(ElementConstants.TRKRoleSearch),"Supervisor");
//    	sleep(TIME_02);
//    	retTxt = getText(getElement(ElementConstants.TRKSupervisorcount));
//    	tracker_count.add(retTxt);
//    	sleep(TIME_01);
//    	setValue(getElement(ElementConstants.TRKRoleSearch),"Safety Officer");
//    	sleep(TIME_02);
//    	retTxt = getText(getElement(ElementConstants.TRKSafetycount));
//    	tracker_count.add(retTxt);
//    	click(getElement(ElementConstants.TRKNOWICON));	
//		sleep(TIME_15);
//		retTxt = getText(getElement(ElementConstants.TRKTodayCNT));
//		tracker_count.add(retTxt);
//		//SOC:@20/04/2020 ROSE removed trackerCount,Supervisors,Safety and Others as it is removed from the site
//		//String path=getElement(ElementConstants.tabletracker);
//	    //table(path);
//		// commented temporarly
//		//Change:@23/04/2020 ROSE  removed 
//	    //List<List<String>> a=table1(path);
//		//End:@23/04/2020
//		
//		return tracker_count;
//	}
//	public List<String> plants_SideNav(){
//		/*0-HeadCountNow,1-TotalHoursClocked,2-Notification,3-Total,4-TotalHours,5-ThisMonth,6-ThisMonthHours,7-Today,8-TodayHours */
//		List<String> plant_count= new ArrayList<String>();
//		System.out.println("START - 3. plants_SideNav");		
//		String retTxt;
//		click(getElement(ElementConstants.PJTSIDENAV));
//		//SOC: Akshaya @16/03/2020: added time for sleep
//		sleep(TIME_10);
//		//EOC: Akshaya @16/03/2020: added time for sleep
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTHEADCOUNTNOW));
//		retTxt=retTxt.replaceAll("[^0-9]", "");
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTHOURSCLOCKED));
//		retTxt=retTxt.replaceAll("[^0-9]", "");
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTNOTIFICATION));
//		plant_count.add(retTxt);
//		click(getElement(ElementConstants.PJTNOWCNTHEADCOUNTNOW));
//		sleep(TIME_04);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTTOTAL));
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTTOTALHOURS));
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTTHISMONTH));
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTTHISMONTHHOURS));
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTTODAY));
//		plant_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.PJTNOWCNTTODAYHOURS));
//		plant_count.add(retTxt);
//
//		
//		return plant_count;
//	}
//	
//	public List<String> contractor_SideNav(){
//		/* List position 0-TotalHeadCount&HeadCountNow,1-Notification*/
//		List<String> contractor_count= new ArrayList<String>();
//		System.out.println("START - 4. contractor_SideNav");		
//		String retTxt;
//		click(getElement(ElementConstants.CONSIDENAV));
//		sleep(TIME_20);
////		retTxt = getText(getElement(ElementConstants.CONTODAYCNT));
////		 String[] arrOfStr = retTxt.split(",", 2);
////		 for (String a : arrOfStr) 
////	            System.out.println(a); 
////		 		retTxt=retTxt.replaceAll("[^0-9]", "");
////		 		contractor_count.add(retTxt);
////		retTxt = getText(getElement(ElementConstants.CONNOWCNT));
////		contractor_count.add(retTxt); 		
////		retTxt = getText(getElement(ElementConstants.CONNOWCNTNOTIFICATION));
////		contractor_count.add(retTxt);
//		retTxt = getText(getElement(ElementConstants.CONCNT));
//		contractor_count.add(retTxt);
//		
//		return contractor_count;
//	}	
//	
//	public List<String> people_SideNav(){
//		List<String> people_count= new ArrayList<String>();
//		System.out.println("START - 5. people_SideNav");		
//		click(getElement(ElementConstants.PEPSIDENAV));
//		sleep(TIME_04);
//		String txt=getText(getElement(ElementConstants.PEPSIDENAVMAINLASTSEEN));
//		System.out.println("-------------");
//		System.out.println(txt);
//		System.out.println("---------");
//		people_count.add(txt);
//		sleep(TIME_04);
//		click(getElement(ElementConstants.PEPSIDENAVCLICK));
//		sleep(TIME_04);
//		 txt=getText(getElement(ElementConstants.PEPSIDENAVLASTSEEN));
//		people_count.add(txt);
//		sleep(TIME_04);
//		 txt=getText(getElement(ElementConstants.PEPSIDENAVBATTERY));
//		people_count.add(txt);
//		 txt=getText(getElement(ElementConstants.PEPSIDENAVEVENTS));
//		people_count.add(txt);
//		
//		
//		
//		
//		return people_count;
//	}	
//	
//	public String device_SideNav(){
//		System.out.println("START - 6. device_SideNav");		
//		String retTxt = "";
//		sleep(TIME_04);
//		click(getElement(ElementConstants.DEVSIDENAV));
//		sleep(TIME_04);
//		retTxt = getText(getElement(ElementConstants.DEVDEVCNT));
//		System.out.println("DEVDEVCNT :" + retTxt);
//		return retTxt;
//	}	
//
//	public List<String> issues_SideNav(){
//		/* List position 0-IssueCount*/
//		List<String> issues_count= new ArrayList<String>();
//		System.out.println("START - 7.issues_SideNav");		
//		sleep(TIME_04);
//		click(getElement(ElementConstants.ISSSIDENAV));
//		sleep(TIME_04);
//		String retTxt = getText(getElement(ElementConstants.ISSTotCNT));
//		issues_count.add(retTxt);
//		retTxt=getText(getElement(ElementConstants.ISSUNACKCNT));
//		issues_count.add(retTxt);
//		sleep(TIME_04);
//		//String path=getElement(ElementConstants.TABLEPATHALERTDETAILS);
//		//ApDrv.table(path);	
//		return issues_count;
//	}
//	
//	public String analytics_SideNav(){
//		System.out.println("START - 8. analytics_SideNav");		
//		String retTxt = "";
//		sleep(TIME_04);
//		click(getElement(ElementConstants.ANASIDENAV));
//		sleep(TIME_04);
//		retTxt = getText(getElement(ElementConstants.ANAPJTCNT));
//		System.out.println("ANAPJTCNT :" + retTxt);
//		return retTxt;
//	}
//	
//	
}

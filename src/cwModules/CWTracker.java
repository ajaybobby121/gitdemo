package cwModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import constants.ElementConstants;


public class CWTracker extends AppDriver {
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
	public static final int TIME_25=25000;
	public static final int TIME_30 = 30000;

	public CWTracker() throws IOException, InterruptedException {
		super(propFileName);
		// TODO Auto-generated constructor stub
	}
	public List<String> Tracker_SideNav() throws InterruptedException{
		List<String> tracker_count_list= new ArrayList<String>();
		System.out.println("START - 2. Tracker_SideNav");
		int size_plt_drop_home=dropsize(getElement(ElementConstants.PLTDROP));
		System.out.println(size_plt_drop_home);
		click(getElement(ElementConstants.TRKSIDENAV));
		Thread.sleep(TIME_05);
		
//		explicitWaitByxpath(getElement(ElementConstants.TRKMARKER));
		List<WebElement>marker_tracker=ApDrv.getelements(getElement(ElementConstants.TRKMARKER));
		System.out.println(marker_tracker.size());
		
		if(marker_tracker.size()+1==(size_plt_drop_home)){//drpdown home includes all plants options
			System.out.println("All plants are marked in the site map");
			tracker_count_list.add("Pass");//79
			tracker_count_list.add("Pass");//80
		}
		else{
			System.out.println("All plants are not marked in the site map");
			tracker_count_list.add("Failed");
			tracker_count_list.add("Failed");
		}
		Select plt_drp_options=new Select(path(getElement(ElementConstants.PLTDROP)));
		plt_drp_options.selectByIndex(2);
		sleep(TIME_05);

		if(isEnabled(getElement(ElementConstants.TRKHEADCNTNWBTN))){
			System.out.println("displayed");
			tracker_count_list.add("Pass");//site view 81
			tracker_count_list.add("Pass");//headcount now button 82
		}
		else{
			tracker_count_list.add("Failed");//site view
			tracker_count_list.add("Failed");//headcount now button
			System.out.println("Not displayed");
		}
		String headcnt_now_cnt_trk=getText(getElement(ElementConstants.TRKHEADCNTNWCNT));
		click(getElement(ElementConstants.HMESIDENAV));
		explicitWaitByxpath(getElement(ElementConstants.HMEHEADCOUNTNOW));
		String headcnt_now_cnt_hme=getText(getElement(ElementConstants.HMEHEADCOUNTNOW));
		System.out.println(headcnt_now_cnt_trk+"count");
		System.out.println(headcnt_now_cnt_hme+"count");
		if(headcnt_now_cnt_hme.equals(headcnt_now_cnt_trk)){
		  tracker_count_list.add("Pass");//headcnt nw count 83
		}
		else{
	    	tracker_count_list.add("Failed");//headcnt nw count 84
		}
		click(getElement(ElementConstants.TRKSIDENAV));
		sleep(TIME_04);
		explicitWaitByxpath(getElement(ElementConstants.TRKSIDEPANEL));
		String color_01=getcolor(getElement(ElementConstants.TRKHEADCNTNWBTN));
		String colorarray_01[]=color_01.split("#");
		if(colorarray_01[1].equals("b8e994")){
			tracker_count_list.add("Pass");//headcnt nw green button85
	    }
		else{
			tracker_count_list.add("Failed");//headcnt nw green//85
		}
//        List<WebElement> zone_catgry=dropselect(getElement(ElementConstants.TRKZNEFILTER));
        if(isVisible(getElement(ElementConstants.TRKZNEFILTER)))
        {
        	tracker_count_list.add("Pass");//zonefilter//86
        }
        else{
			tracker_count_list.add("Failed");//zonefilter//86
		}
        //personnel
        String personel_cout=getText(getElement(ElementConstants.TRKPERSONNELCNT));
   		int personel_count=Integer.parseInt(personel_cout);
   		
   		int trk_table_empcount=getRowcount(getElement(ElementConstants.TRk_PESNLTABLEROWS));
   		if(personel_count==trk_table_empcount){
   			tracker_count_list.add("Pass");//personnel-87
   		}
   		else{
   			tracker_count_list.add("Failed");//personnel-87
   		}
   		//company
   		explicitWaitByxpath(getElement(ElementConstants.TRKCOMPNYFILTER));
   	    int company_filter_options=dropsize(getElement(ElementConstants.TRKCOMPNYFILTER));
   	    int company_filter_options_act=company_filter_options-1;//includes all options in dom model
   	    String company_count=getText(getElement(ElementConstants.TRKCOMPANYCNT));
   	    int company_cnt=Integer.parseInt(company_count);
   	    System.out.println(company_cnt);
   	    System.out.println(company_filter_options);
   	    if(company_filter_options_act==company_cnt)
   	    {
   	    	tracker_count_list.add("Pass");//company-88
   	    	System.out.println("pass");
   	    }
   	    else
   	    {
   	    	tracker_count_list.add("Failed");//company-88
   	    	System.out.println("Fail");
   	    }
   	    //role
   	    int role_filter_options=dropsize(getElement(ElementConstants.TRKROLEFILTER));
	    int role_filter_options_act=role_filter_options-1;//includes all options
	    String role_count=getText(getElement(ElementConstants.TRKROLECNT));
	    int role_cnt=Integer.parseInt(role_count);
	    System.out.println(role_cnt);
	    System.out.println(role_filter_options_act);
	    
	    if(role_filter_options_act==role_cnt)
	    {
	    	tracker_count_list.add("Pass");//role-89
	    	System.out.println("pass");
	    }
	    else
	    {
	    	tracker_count_list.add("Failed");//role-89
	    	System.out.println("Fail");
	    }
	    //section
	    int section_filter_options=dropsize(getElement(ElementConstants.TRKSECTIONFILTER));
	    int section_filter_options_act=section_filter_options-1;//includes all options
	    String section_count=getText(getElement(ElementConstants.TRKSECTIONCNT));
	    int section_cnt=Integer.parseInt(section_count);
	    System.out.println(section_cnt);
	    System.out.println(section_filter_options_act);
	    if(section_filter_options_act==section_cnt)
	    {
	    	tracker_count_list.add("Pass");//section-90
	    	System.out.println("pass");
	    }
	    else
	    {
	    	tracker_count_list.add("Failed");//section-90
	    	System.out.println("Fail");
	    }
   	    //zone filter
	    if(isVisible(getElement(ElementConstants.TRKZNEFILTER)))
        {
        	tracker_count_list.add("Pass");//zonefilter//91
        }
        else{
			tracker_count_list.add("Failed");//zonefilter//91
		} 
	    //clear filter
	    String company_count_clr=getText(getElement(ElementConstants.TRKCOMPANYCNT));
   	    int company_cnt_clr=Integer.parseInt(company_count);
   	    if(company_cnt_clr!=0){
	    Select cmpny_drp_options=new Select(path(getElement(ElementConstants.TRKCOMPNYFILTER)));
	    cmpny_drp_options.selectByIndex(1);
	    explicitWaitByxpath(getElement(ElementConstants.TRKCLRFLTER));
	    if(isVisible(getElement(ElementConstants.TRKCLRFLTER))){
	    	System.out.println("clr filter");
	    	tracker_count_list.add("Pass");//92 clear filter
	    	clickOverlap(getElement(ElementConstants.TRKCLRFLTER));
	    }
	    else{
	    	System.out.println("clr filter failed");
	    	tracker_count_list.add("Failed");//92 clear filter
	    }
   	    }
   	    else{
   	    	tracker_count_list.add("No Run");
   	    }
	  //FLOOR FILTER 82,83
	    int size_flr_drp_now=dropsize(getElement(ElementConstants.TRK_FLR_DRPDWN));
	    System.out.println("filter size"+size_flr_drp_now);
	    int flr_sum_now=0;
	    Select floor_level_options_now=new Select(path(getElement(ElementConstants.TRK_FLR_DRPDWN)));
		for( int s=0;s<size_flr_drp_now;s++){
			
			floor_level_options_now.selectByIndex(s);
			Thread.sleep(TIME_05);
			System.out.println(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
			flr_sum_now=flr_sum_now+Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
			
		}
		
		System.out.println("floor cnt"+flr_sum_now);
		System.out.println("hdcount"+getText(getElement(ElementConstants.TRKHEADCNTNWCNT)));
		int headcnt_now_cnt_prsnl_01=Integer.parseInt(getText(getElement(ElementConstants.TRKHEADCNTNWCNT)));
		if(headcnt_now_cnt_prsnl_01==flr_sum_now){
			tracker_count_list.add("Pass");//93
			tracker_count_list.add("Pass");//94
		}
		else{
			tracker_count_list.add("Failed");
			tracker_count_list.add("Failed");
		}
		refresh();
		explicitWaitByxpathclickable(getElement(ElementConstants.TRKHEADCNTNWBTN));
		Thread.sleep(TIME_03);
		//company filter 84-86
//		int cmpny_act_count=filteractiveCount(getElement(ElementConstants.TRKCOMPNYFILTER));
//		int headcnt_now_cnt_prsnl_02=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(cmpny_act_count);
//		System.out.println(headcnt_now_cnt_prsnl_02);
//		if(cmpny_act_count==headcnt_now_cnt_prsnl_02){
//			tracker_count_list.add("Pass");//95
//			tracker_count_list.add("Pass");//96
//			tracker_count_list.add("Pass");//97
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");//95
		tracker_count_list.add("Clarification Required");//96
		tracker_count_list.add("Clarification Required");//97
//		//role filter 87-89
//		int role_act_count=filteractiveCount(getElement(ElementConstants.TRKROLEFILTER));
//		int headcnt_now_cnt_prsnl_03=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(role_act_count);
//		System.out.println(headcnt_now_cnt_prsnl_03);
//		if(role_act_count==headcnt_now_cnt_prsnl_03){
//			tracker_count_list.add("Pass");//97
//			tracker_count_list.add("Pass");//98
//			tracker_count_list.add("Pass");//99
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");//97
		tracker_count_list.add("Clarification Required");//98
		tracker_count_list.add("Clarification Required");//99
		
		//section filter 90-92
//		int sec_act_count=filteractiveCount(getElement(ElementConstants.TRKSECTIONFILTER));
//		int headcnt_now_cnt_prsnl_04=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println("sec_act_count "+sec_act_count);
//		System.out.println(headcnt_now_cnt_prsnl_04);
//		if(sec_act_count==headcnt_now_cnt_prsnl_04){
//			tracker_count_list.add("Pass");//100
//			tracker_count_list.add("Pass");//101
//			tracker_count_list.add("Pass");//102
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");//100
		tracker_count_list.add("Clarification Required");//101
		tracker_count_list.add("Clarification Required");//102
		//zone filter 93-94
//		int zone_act_count=filteractiveCount(getElement(ElementConstants.TRKZNEFILTER));
//		int headcnt_now_cnt_prsnl_05=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(zone_act_count);
//		System.out.println(headcnt_now_cnt_prsnl_05);
//		if(zone_act_count==headcnt_now_cnt_prsnl_05){
//			tracker_count_list.add("Pass");//103
//			tracker_count_list.add("Pass");//104
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");//103
		tracker_count_list.add("Clarification Required");//104
		
		//105-111
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		
		
		List<WebElement> trk_table=getelements(getElement(ElementConstants.TRk_PESNLTABLE));
		if(trk_table.get(0).getText().equalsIgnoreCase("NAME")){
			tracker_count_list.add("Pass");//113
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(1).getText().equalsIgnoreCase("ROLE")){
			tracker_count_list.add("Pass");//114
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(2).getText().equalsIgnoreCase("SECTION")){
			tracker_count_list.add("Pass");//115
		}
		else{
			tracker_count_list.add("Failed");
		}
		
		if(trk_table.get(3).getText().equalsIgnoreCase("SUPERVISOR")){
			tracker_count_list.add("Pass");//116
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(4).getText().equalsIgnoreCase("COMPANY")){
			tracker_count_list.add("Pass");//117
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(5).getText().equalsIgnoreCase("FIRST ACTIVE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(6).getText().equalsIgnoreCase("LAST ACTIVE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(7).getText().equalsIgnoreCase("LAST SEEN")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(8).getText().equalsIgnoreCase("ZONE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(9).getText().equalsIgnoreCase("LANDMARK")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table.get(11).getText().equalsIgnoreCase("LOCATE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(isVisible(getElement(ElementConstants.TRKHEADCNTTDYBTN))){
			click(getElement(ElementConstants.TRKHEADCNTTDYBTN));
			tracker_count_list.add("Pass");//headcnt tdy-124
			explicitWaitByxpath(getElement(ElementConstants.TRKSIDEPANEL));
		}
		else{
			tracker_count_list.add("Failed");
		}
		String color_02=getcolor(getElement(ElementConstants.TRKHEADCNTTDYBTN));
		String colorarray[]=color_02.split("#");
		if(colorarray[1].equals("999999")){
			tracker_count_list.add("Pass");//headcnt tdy grey button-125
	    }
		else{
			tracker_count_list.add("Failed");//headcnt tdy grey button-125
		}
		String headcnt_now_today_cnt_trk=getText(getElement(ElementConstants.TRKHEADCNTTDYCNT));
		click(getElement(ElementConstants.HMESIDENAV));
		sleep(TIME_07);
		String headcnt_now_cnt_tdy_hme=getText(getElement(ElementConstants.HMEHEADCOUNTTODAY));
		System.out.println("hme today "+headcnt_now_cnt_tdy_hme+ "trk today "+headcnt_now_today_cnt_trk);
		if(headcnt_now_cnt_tdy_hme.equals(headcnt_now_today_cnt_trk)){
		  tracker_count_list.add("Pass");//headcnt today count 126
		}
		else{
	    	tracker_count_list.add("Failed");//headcnt today count 126
		}
		click(getElement(ElementConstants.TRKSIDENAV));

		explicitWaitByxpath(getElement(ElementConstants.TRKSIDEPANEL));
		click(getElement(ElementConstants.TRKHEADCNTTDYBTN));
		explicitWaitByxpath(getElement(ElementConstants.TRKSIDEPANEL));
		
		String personel_cout_today=getText(getElement(ElementConstants.TRKPERSONNELCNT));
   		int personel_count_today=Integer.parseInt(personel_cout_today);
   		
   		int trk_table_empcount_today=getRowcount(getElement(ElementConstants.TRk_PESNLTABLEROWS));
   		if(personel_count_today==trk_table_empcount_today){
   			tracker_count_list.add("Pass");//personnel-127
   		}
   		else{
   			tracker_count_list.add("Failed");//personnel-127
   		}
   		//company
   	    int company_filter_options_tdy=dropsize(getElement(ElementConstants.TRKCOMPNYFILTER));
   	    int company_filter_options_tdy_act=company_filter_options_tdy-1;//includes all options in dom model
   	    String company_count_tdy=getText(getElement(ElementConstants.TRKCOMPANYCNT));
   	    int company_cnt_tdy=Integer.parseInt(company_count_tdy);
   	    System.out.println("cmpyfil "+company_cnt_tdy);
   	    System.out.println("cmptext"+company_filter_options_tdy_act);
   	    if(company_filter_options_tdy_act==company_cnt_tdy)
   	    {
   	    	tracker_count_list.add("Pass");//company-128
   	    	System.out.println("pass");
   	    }
   	    else
   	    {
   	    	tracker_count_list.add("Failed");//company-128
   	    	System.out.println("Fail");
   	    }
   	    //role
   	    int role_filter_options_tdy=dropsize(getElement(ElementConstants.TRKROLEFILTER));
	    int role_filter_options_tdy_act=role_filter_options_tdy-1;//includes all options
	    String role_count_tdy=getText(getElement(ElementConstants.TRKROLECNT));
	    int role_cnt_tdy=Integer.parseInt(role_count_tdy);
	    System.out.println(role_cnt);
	    System.out.println(role_filter_options_act);
	    
	    if(role_filter_options_tdy_act==role_cnt_tdy)
	    {
	    	tracker_count_list.add("Pass");//role-129
	    	System.out.println("pass");
	    }
	    else
	    {
	    	tracker_count_list.add("Failed");//role-129
	    	System.out.println("Fail");
	    }
	    //section
	    int section_filter_tdy_options=dropsize(getElement(ElementConstants.TRKSECTIONFILTER));
	    int section_filter_options_tdy_act=section_filter_tdy_options-1;//includes all options
	    String section_count_tdy=getText(getElement(ElementConstants.TRKSECTIONCNT));
	    int section_cnt_tdy=Integer.parseInt(section_count_tdy);
	    System.out.println(section_cnt_tdy);
	    System.out.println(section_filter_options_tdy_act);
	    if(section_filter_options_tdy_act==section_cnt_tdy)
	    {
	    	tracker_count_list.add("Pass");//section-130
	    	System.out.println("pass");
	    }
	    else
	    {
	    	tracker_count_list.add("Failed");//section-130
	    	System.out.println("Fail");
	    }
   	    //zone filter
	    if(isVisible(getElement(ElementConstants.TRKZNEFILTER)))
        {
        	tracker_count_list.add("Pass");//zonefilter 131
        }
        else{
			tracker_count_list.add("Failed");//zonefilter 131
		} 
	    Select cmpny_drp_options_tdy=new Select(path(getElement(ElementConstants.TRKCOMPNYFILTER)));
	    cmpny_drp_options_tdy.selectByIndex(1);
	    explicitWaitByxpath(getElement(ElementConstants.HME_ALERT_MAP));
	    if(isVisible(getElement(ElementConstants.TRKCLRFLTER))){
	    	System.out.println("clr filter");
	    	tracker_count_list.add("Pass");//132 clear filter
	    	clickOverlap(getElement(ElementConstants.TRKCLRFLTER));
	    }
	    else{
	    	System.out.println("clr filter failed");
	    	tracker_count_list.add("Failed");//132 clear filter
	    }
	    //122-123
	    int size_flr_drp_tdy=dropsize(getElement(ElementConstants.TRK_FLR_DRPDWN));
	    System.out.println("filter size"+size_flr_drp_tdy);
	    int flr_sum_tdy=0;
	    Select floor_level_options_tdy=new Select(path(getElement(ElementConstants.TRK_FLR_DRPDWN)));
		for( int s=0;s<size_flr_drp_tdy;s++){
			floor_level_options_tdy.selectByIndex(s);
			Thread.sleep(TIME_05);
			System.out.println(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
			flr_sum_tdy=flr_sum_tdy+Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
		}
        System.out.println("floor cnt"+flr_sum_tdy);
		System.out.println("hdcount"+getText(getElement(ElementConstants.TRKHEADCNTTDYCNT)));
		int headcnt_now_cnt_prsnl_06=Integer.parseInt(getText(getElement(ElementConstants.TRKHEADCNTTDYCNT)));
		if(headcnt_now_cnt_prsnl_06==flr_sum_tdy){
			tracker_count_list.add("Pass");//132
			tracker_count_list.add("Pass");//133
		}
		else{
			tracker_count_list.add("Failed");
			tracker_count_list.add("Failed");
		}
		refresh();
		explicitWaitByxpathclickable(getElement(ElementConstants.TRKHEADCNTNWBTN));
		Thread.sleep(TIME_03);
		click(getElement(ElementConstants.TRKHEADCNTTDYBTN));
		explicitWaitByxpathclickable(getElement(ElementConstants.TRKSIDEPANEL));
		//company filter 135-137
//		int cmpny_act_count_tdy=filteractiveCount(getElement(ElementConstants.TRKCOMPNYFILTER));
//		int headcnt_now_cnt_prsnl_07=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(cmpny_act_count_tdy);
//		System.out.println(headcnt_now_cnt_prsnl_07);
//		if(cmpny_act_count_tdy==headcnt_now_cnt_prsnl_07){
//			tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		//role filter 138-140
//		int role_act_count_tdy=filteractiveCount(getElement(ElementConstants.TRKROLEFILTER));
//		int headcnt_now_cnt_prsnl_08=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(role_act_count_tdy);
//		System.out.println(headcnt_now_cnt_prsnl_08);
//		if(role_act_count_tdy==headcnt_now_cnt_prsnl_08){
//			tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		//section filter 141-143
//		Thread.sleep(TIME_04);
//		int sec_act_count_tdy=filteractiveCount(getElement(ElementConstants.TRKSECTIONFILTER));
//		int headcnt_now_cnt_prsnl_09=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(sec_act_count_tdy);
//		System.out.println(headcnt_now_cnt_prsnl_09);
//		if(sec_act_count_tdy==headcnt_now_cnt_prsnl_09){
//			tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		//zone filter 144-145
//		int zone_act_count_tdy=filteractiveCount(getElement(ElementConstants.TRKZNEFILTER));
//		int headcnt_now_cnt_prsnl_10=Integer.parseInt(getText(getElement(ElementConstants.TRKPERSONNELCNT)));
//		System.out.println(zone_act_count_tdy);
//		System.out.println(headcnt_now_cnt_prsnl_10);
//		if(zone_act_count_tdy==headcnt_now_cnt_prsnl_10){
//		    tracker_count_list.add("Pass");
//			tracker_count_list.add("Pass");
//		}
//		else{
//			tracker_count_list.add("Failed");
//			tracker_count_list.add("Failed");
//		}
		tracker_count_list.add("Clarification Required");
		tracker_count_list.add("Clarification Required");
		
		//146-153
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
		//154-164
		List<WebElement> trk_table_tdy=getelements(getElement(ElementConstants.TRk_PESNLTABLE));
		if(trk_table_tdy.get(0).getText().equalsIgnoreCase("NAME")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(1).getText().equalsIgnoreCase("ROLE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(2).getText().equalsIgnoreCase("SECTION")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		
		if(trk_table_tdy.get(3).getText().equalsIgnoreCase("SUPERVISOR")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(4).getText().equalsIgnoreCase("COMPANY")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(5).getText().equalsIgnoreCase("FIRST ACTIVE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(6).getText().equalsIgnoreCase("LAST ACTIVE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(7).getText().equalsIgnoreCase("LAST SEEN")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(8).getText().equalsIgnoreCase("ZONE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(9).getText().equalsIgnoreCase("LANDMARK")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		if(trk_table_tdy.get(11).getText().equalsIgnoreCase("LOCATE")){
			tracker_count_list.add("Pass");
		}
		else{
			tracker_count_list.add("Failed");
		}
		//165,166
		tracker_count_list.add("Not Feasible");
		tracker_count_list.add("Not Feasible");
	    return tracker_count_list;
		
	}

}
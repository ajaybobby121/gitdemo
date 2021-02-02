package cwModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.WebElement;

import baseClass.AppDriver;
import baseClass.ExcelDriver;
import constants.ElementConstants;

public class syncStatus extends AppDriver{
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
	public syncStatus() throws IOException, InterruptedException{
		super(propFileName);
	} 
	public List<List<String>> synctab() throws InterruptedException, ParseException{
		 List<List<String>> statusTable=new ArrayList<List<String>>();
		click(getElement(ElementConstants.SETT_SIDENAV));
		Thread.sleep(TIME_04);
		click(getElement(ElementConstants.SETT_SYNC_TAB));
		Thread.sleep(TIME_04);
		if(isVisible(getElement(ElementConstants.SETT_SYNC_TABL)))
		{
	    System.out.println("Table displayed");
	    String[]synctime1=settingsTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy,hh:mm:ss a");
	    System.out.println("str1 before"+synctime1[0]);
	    
	    
//	    if(!synctime1[0].equals("Not Available")){
	    Date str1 = sdf.parse(synctime1[0]);
	    System.out.println("str1 before"+str1);
	    Date ehs1 = sdf.parse(synctime1[1]);
	    Date nvf1 = sdf.parse(synctime1[2]);
	    Date aggrq1wosec=removeSeconds(synctime1[3]);
//	    Date aggrd1=sdf.parse(synctime1[4]);
	    String aggrd1=synctime1[4];
//	    String reportjb=synctime1[5];
	    Date reportjb1wosec=removeSeconds(synctime1[5]);
	    Date str2=null;
	    Date ehs2=null;
	    Date nvfr2=null;
	    Date aggrq1=null;
	   
	    
	    click(getElement(ElementConstants.SETT_SYNC_BTN));
        Thread.sleep(TIME_03);

	    
	    	click(getElement(ElementConstants.HMESIDENAV));
	    	Thread.sleep(TIME_07);
	    	clickOverlap(getElement(ElementConstants.HMEACTALERTS));
	    	Thread.sleep(TIME_05);
	    	click(getElement(ElementConstants.PEPSIDENAV));
	    	Thread.sleep(TIME_07);
	    	click(getElement(ElementConstants.CONSIDENAV));
	    	Thread.sleep(TIME_05);
	    	click(getElement(ElementConstants.PJTSIDENAV));
	    	Thread.sleep(TIME_07);
	    	click(getElement(ElementConstants.ISSSIDENAV));
	    	Thread.sleep(TIME_05);
	    	click(getElement(ElementConstants.DEVSIDENAV));
			Thread.sleep(TIME_07);
	    	click(getElement(ElementConstants.SETT_SIDENAV));
			Thread.sleep(TIME_07);
			refresh();
			Thread.sleep(TIME_05);
			explicitWaitByxpath(getElement(ElementConstants.SETT_SYNC_TAB));
			click(getElement(ElementConstants.SETT_SYNC_TAB));
			Thread.sleep(TIME_05);
			
			//after clicking sync button
			if(isVisible(getElement(ElementConstants.SETT_SYNC_TABL)))
			{
	    	String[]synctime2=settingsTime();
	    	str2 = sdf.parse(synctime2[0]);
	    	ehs2= sdf.parse(synctime2[1]);
	    	nvfr2= sdf.parse(synctime2[2]);
	    	Date aggrq2wosec=removeSeconds(synctime2[3]);
	    	String aggrd2=synctime2[4];
	    	Date reportjbwosec2=removeSeconds(synctime2[5]);
	    	
	    	//for D job
	    	System.out.println(aggrd1+"     "+aggrd2);
	    	SimpleDateFormat formatter1 = new SimpleDateFormat("d-MMM-yyyy");
	    	String[]AGGRDLIST1=aggrd1.split(",");
	    	for (String string : AGGRDLIST1) {
				System.out.println(string);
			}
	    	String aggrd1montDay[]=AGGRDLIST1[0].split(" ");
	    	String aggrd1yr=AGGRDLIST1[1].trim();
	    	String aggrd1fin=aggrd1montDay[1]+"-"+aggrd1montDay[0]+"-"+aggrd1yr;
	    	
	    	Date aggrd1final = formatter1.parse(aggrd1fin);
	    	System.out.println("d1 "+aggrd1final);
	    	String[]AGGRDLIST2=aggrd2.split(",");
	    	
	    	String aggrd2montDay[]=AGGRDLIST2[0].split(" ");
	    	String aggrd2yr=AGGRDLIST2[1].trim();
	    	String aggrd2fin=aggrd2montDay[1]+"-"+aggrd2montDay[0]+"-"+aggrd2yr;
	    	
	    	Date aggrd2final = formatter1.parse(aggrd2fin);
	    	System.out.println("d2" +aggrd2final);
	    	
	    	Date date1 = new Date();
	    	String current1=formatter1.format(date1);
	    	Date currentdate1=formatter1.parse(current1);
	    	System.out.println("current "+currentdate1);
	    	Calendar cal1 = Calendar.getInstance();
	    	cal1.setTime((currentdate1));
	    	cal1.add(Calendar.DAY_OF_MONTH,-1);
	    	String crntdatms1 = formatter1.format(cal1.getTime());
	    	Date crntdatems1=formatter1.parse( crntdatms1);
	    	System.out.println("current day -1"+crntdatems1);
	    	
	    	//For Q job
	    	SimpleDateFormat formatterq1 = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
	    	Date date2 = new Date();
	    	String current2=formatterq1.format(date2);
	    	Date currentdate2=formatterq1.parse(current2);
	    	Calendar cal2 = Calendar.getInstance();
	    	cal2.setTime((currentdate2));
	    	System.out.println("current date1"+currentdate2);
	    	String crntdat = formatterq1.format(currentdate2);
	    	Date crntdate=formatterq1.parse(crntdat);
	    	System.out.println("current date2"+crntdate);
	    	cal2.add(Calendar.MINUTE,-15);
	    	System.out.println("before formatting"+cal2.getTime());
	    	SimpleDateFormat formatterq = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
//	    	String crntdatms15 = formatterq.format(cal2.getTime());
	    	Date crntdatems15=cal2.getTime();
	    	System.out.println("current time fin"+currentdate2);
	    	System.out.println("current time -15"+crntdatems15);
	    	
	    	System.out.println(aggrq1wosec+" wosec "+aggrq2wosec);
	    	
	    	Calendar cal3 = Calendar.getInstance();
	    	cal3.setTime(aggrq1wosec);
	    	cal3.add(Calendar.MINUTE, 15);
//	    	String aggrqbfr15 = formatterq.format(cal3.getTime());
	    	String aggrqafr15 = formatterq.format(cal3.getTime());
	    	Date  aggrqdateafr15=formatterq.parse( aggrqafr15);
	    	System.out.println("before +15"+aggrqdateafr15);
	    	
	    	//report-job
	    	
//	    	SimpleDateFormat formatterq1 = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
	    	Date date3 = new Date();
	    	String currentrpt=formatterq1.format(date2);
	    	Date currentdatrpt=formatterq1.parse(currentrpt);
	    	Calendar cal4 = Calendar.getInstance();
	    	cal4.setTime((currentdate2));
	    	System.out.println("current date1"+currentdatrpt);
	    	String crntdatrpt = formatterq1.format(currentdate2);
	    	Date crntdaterpt=formatterq1.parse(crntdatrpt);
	    	System.out.println("current date2"+crntdaterpt);
	    	cal4.add(Calendar.MINUTE,-5);
	    	System.out.println("before formatting"+cal4.getTime());
//	    	SimpleDateFormat formatterq = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
//	    	String crntdatms15 = formatterq.format(cal2.getTime());
	    	Date crntdaterptms5=cal4.getTime();
	    	System.out.println("current time fin"+currentdatrpt);
	    	System.out.println("current time -5"+crntdaterptms5);
	    	
	    	System.out.println(reportjb1wosec+" wosec "+reportjbwosec2);
	    	
	    	Calendar cal5 = Calendar.getInstance();
	    	cal5.setTime(reportjb1wosec);
	    	cal5.add(Calendar.MINUTE, 5);
	    	String rpt1bfr5 = formatterq.format(cal5.getTime());
	    	Date  rpt1datepls5=formatterq.parse( rpt1bfr5);
	    	System.out.println("before +5"+rpt1datepls5);
	    	
	    	//for table
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm a"); 
	  		LocalDateTime now = LocalDateTime.now(); 
	  		String dateTimearr[]=dtf.format(now).split(","); 
	  		List<String>dateTime=new ArrayList<>();
	    	dateTime.add(dateTimearr[0]);
	    	dateTime.add(dateTimearr[1]);
	    	statusTable.add(0, dateTime);
	    	List<String>tableheader=new ArrayList<String>();
	    	tableheader.add("APP");
	    	tableheader.add("SYNC STATUS");
	    	tableheader.add("COMMENTS");
	    	tableheader.add("Final Status");
	    	statusTable.add(1, tableheader);
	    	
	    	List<String>tableEntry1=new ArrayList<String>();
	    	tableEntry1.add("STR");
//	    	String date1="Dec 9, 2020, 10:46:23 PM";
//	    	String date2="Dec 9, 2020, 10:45:23 PM";
//	    	Date strd1=sdf.parse(date1);
//	    	Date strd2=sdf.parse(date2);
//	    	
	    	if(str1.before(str2)){
	    		tableEntry1.add("In Sync");
	    		tableEntry1.add("");
	    		tableEntry1.add("NR");
	    		
	    	}
	    	else{
	    		tableEntry1.add("Not in Sync");
	    		tableEntry1.add("Before time: "+str1+" and after time: "+str2+" are not in sync");
	    		tableEntry1.add("WRNG");
	    		
	    	}
	    	statusTable.add(2,tableEntry1 );
	    	List<String>tableEntry2=new ArrayList<String>();
	    	tableEntry2.add("EHS");
	    	
	    	if(ehs1.before(ehs2)){
	    		tableEntry2.add("In Sync");
	    		tableEntry2.add("");
	    		tableEntry2.add("NR");
	    		
	    		
	    	}
	    	else{
	    		tableEntry2.add("Not in Sync");
	    		tableEntry2.add("Before time: "+ehs1+" and after time: "+ehs2+" are not in sync");
	    		tableEntry2.add("WRNG");
	    		
	    	}
	    	statusTable.add(3,tableEntry2 );
	    	List<String>tableEntry3=new ArrayList<String>();
	    	tableEntry3.add("NAVISAFE");
	    	
	    	if(nvf1.before(nvfr2)){
	    		tableEntry3.add("In Sync");
	    		tableEntry3.add("");
	    		tableEntry3.add("NR");
	    	}
	    	else{
	    		tableEntry3.add("Not in Sync");
	    		tableEntry3.add("Before time: "+nvf1+" and after time: "+nvfr2+" are not in sync");
	    		tableEntry3.add("WRNG");
	    		
	    	}
	    	statusTable.add(4,tableEntry3 );
	    	List<String>tableEntry4=new ArrayList<String>();
	    	tableEntry4.add("AGGRD");
	    	if(aggrd1final.equals(aggrd2final)){
	    		if (aggrd2final.equals(currentdate1)) {
	    			tableEntry4.add("In Sync");
					System.out.println("Passed in 1");
					tableEntry4.add("");
					tableEntry4.add("NR");
					
				}
	    		else{
	    			if(aggrd2final.equals(crntdatems1)){
	    				System.out.println("Passed in 2");
	    				tableEntry4.add("In Sync");
	    				tableEntry4.add("");
	    				tableEntry4.add("NR");
	    			}
	    			else{
	    				System.out.println("Failed in 2");
	    				tableEntry4.add("Not in Sync");
	    	    		tableEntry4.add("Before time: "+aggrd1+" and after time: "+aggrd2+" are not in sync");
	    	    		tableEntry4.add("WRNG");
	    			}
	    		}
	    	}
	    	else{
	    		if(aggrd1final.equals(crntdatems1)){
	    			tableEntry4.add("In Sync");
	    			tableEntry4.add("");
    				tableEntry4.add("NR");
	    			System.out.println("passed");
	    		}
	    		else{
	    			System.out.println("Failed");
	    			tableEntry4.add("Not in Sync");
    	    		tableEntry4.add("Before time: "+aggrd1+" and after time: "+aggrd2+" are not in sync");
    	    		tableEntry4.add("WRNG");
	    		}
	    	 }
	    	statusTable.add(5,tableEntry4 );
	    	List<String>tableEntry5=new ArrayList<String>();
	    	tableEntry5.add("AGGRQ");
	    	if(aggrq1wosec.equals(aggrq2wosec)){
	    		if(aggrq2wosec.after(crntdatems15)&&aggrq2wosec.before(crntdate)){
	    			tableEntry5.add("In Sync");
	    			tableEntry5.add("");
    				tableEntry5.add("NR");
	    			System.out.println("passed");
	    		}
	    		else{
	    			System.out.println("Failed");
	    			tableEntry5.add("Not in Sync");
    	    		tableEntry5.add("Before time: "+aggrq1wosec+" and after time: "+aggrq2wosec+" are not in sync");
    	    		tableEntry5.add("WRNG");
	    		}
	    	}
	    	else{
	    		if((aggrq2wosec.equals(aggrqdateafr15))||(aggrq2wosec.before(aggrqdateafr15))){
	    			tableEntry5.add("In Sync");
	    			tableEntry5.add("");
    				tableEntry5.add("NR");
	    			System.out.println("passed");
	    		}
	    		else{
	    			System.out.println("Failed");
	    			tableEntry5.add("Not in Sync");
    	    		tableEntry5.add("Before time: "+aggrq1wosec+" and after time: "+aggrq2wosec+" are not in sync");
    	    		tableEntry5.add("WRNG");
	    		}
	    	}
	    	statusTable.add(6,tableEntry5 );
	    	List<String>tableEntry6=new ArrayList<String>();
	    	tableEntry6.add("REPORT-JOB");
	    	System.out.println();
	    	if(reportjb1wosec.equals(reportjbwosec2)){
	    		if(reportjbwosec2.after(crntdaterptms5)&&reportjbwosec2.before(currentdatrpt)){
//	    		if(currentdatrpt.before(rpt1datepls5)){
	    			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
	    			tableEntry6.add("In Sync");
	    			tableEntry6.add("");
    				tableEntry6.add("NR");
	    			System.out.println("passed");
	    		}
	    		else{
	    			System.out.println("Failed rpt 1");
	    			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
	    			tableEntry6.add("Not in Sync");
    	    		tableEntry6.add("Before time: "+reportjb1wosec+" and after time: "+reportjbwosec2+" are not in sync.  Current"
    	    				+ "time "+currentdatrpt);
    	    		tableEntry6.add("WRNG");
	    		}
	    	}
	    	else{
	    		
	    		if(reportjbwosec2.equals(rpt1datepls5)||reportjbwosec2.before(rpt1datepls5)){
	    			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
	    			tableEntry6.add("In Sync");
	    			tableEntry6.add("");
    				tableEntry6.add("NR");
	    			System.out.println("passed");
	    		}
	    		else{
	    			System.out.println("Failed rpt 2");
	    			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
	    			tableEntry6.add("Not in Sync");
    	    		tableEntry6.add("Before time : "+reportjb1wosec+" and after time: "+reportjbwosec2+" are not in sync. Current"
    	    				+ "time "+currentdatrpt);
    	    		tableEntry6.add("WRNG");
	    		}
	    	}
	    	statusTable.add(7,tableEntry6 );
//	    	tearDown();
	    	
	    }
			else{
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm a"); 
		  		LocalDateTime now = LocalDateTime.now(); 
		  		String dateTimearr[]=dtf.format(now).split(","); 
		  		List<String>dateTime=new ArrayList<>();
		    	dateTime.add(dateTimearr[0]);
		    	dateTime.add(dateTimearr[1]);
		    	statusTable.add(0, dateTime);
		    	List<String>tableheader=new ArrayList<String>();
		    	tableheader.add("APP");
		    	tableheader.add("SYNC STATUS");
		    	tableheader.add("COMMENTS");
		    	tableheader.add("Final Status");
		    	statusTable.add(1, tableheader);
		    	List<String>tableEntry1=new ArrayList<String>();
		    	tableEntry1.add("All Apps");
		    	tableEntry1.add("Not displayed");
		    	tableEntry1.add("Sync apps data table is not loaded");
		    	tableEntry1.add("WRNG");
		    	statusTable.add(2, tableEntry1);
//		    	tearDown();
				
			}
			
		}
	   
		
	    
		
		 else{
		    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm a"); 
		  		LocalDateTime now = LocalDateTime.now(); 
		  		String dateTimearr[]=dtf.format(now).split(","); 
		  		List<String>dateTime=new ArrayList<>();
		    	dateTime.add(dateTimearr[0]);
		    	dateTime.add(dateTimearr[1]);
		    	statusTable.add(0, dateTime);
		    	List<String>tableheader=new ArrayList<String>();
		    	tableheader.add("APP");
		    	tableheader.add("SYNC STATUS");
		    	tableheader.add("COMMENTS");
		    	tableheader.add("Final Status");
		    	statusTable.add(1, tableheader);
		    	List<String>tableEntry1=new ArrayList<String>();
		    	tableEntry1.add("All Apps");
		    	tableEntry1.add("Not displayed");
		    	tableEntry1.add("Sync apps data table is not loaded");
		    	tableEntry1.add("WRNG");
		    	statusTable.add(2, tableEntry1);
//		    	tearDown();
//				
			}
		return statusTable;
		
	   
		
		
		
		
	}
	public static Date removeSeconds(String Time) throws ParseException{
	
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");

		String[]AGGRDLIST1=Time.split(",");
		String[]AGGRDLIST2=AGGRDLIST1[2].split(":");
		String[]AGGRDLIST3=AGGRDLIST2[2].split(" ");
		String final1=AGGRDLIST1[0]+","+AGGRDLIST1[1]+","+AGGRDLIST2[0]+":"+AGGRDLIST2[1]+" "+AGGRDLIST3[1];
		
		Date finalDate = formatter.parse(final1);
    	System.out.println(" final "+finalDate);

		return finalDate;
		
	}

}


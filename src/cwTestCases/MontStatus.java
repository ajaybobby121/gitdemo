package cwTestCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.netty.util.internal.SystemPropertyUtil;

//import org.apache.commons.collections.map.StaticBucketMap;

import baseClass.AppDriver;
import baseClass.EmailDriver;
import baseClass.ExcelDriver;
import constants.ElementConstants;
import cwModules.CWLogin;
import cwModules.CheckList;
import cwModules.syncStatus;

public class MontStatus {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	      AppDriver ApDrv = new AppDriver();
	      ExcelDriver exlTab = new ExcelDriver();
	      EmailDriver EmlDrv=new EmailDriver();
	      CWLogin cwLgn = new CWLogin();
	      CheckList chkList=new CheckList();
	      syncStatus sys=new syncStatus();
          String countStatus="Normal";
	      String devSyncStatus="NOT EXECUTED";
	      String uatSyncStatus="NOT EXECUTED";
	      String prodSyncStatus="NOT EXECUTED";
	      String lgn_stat="normal";
	      String err_text = "normal";
	      List<String>enviroment=new ArrayList<>();
	      DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM-yyyy"); 
	     
		  DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); 
		  DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm");
		  SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		  LocalDateTime now1 = LocalDateTime.now();
		  System.out.println(df1.format(now1));
  		  String date=df1.format(now1);
  		  String crrntTime=df2.format(now1);
  		  String[] datearray=date.split("-");
  		  System.out.println("day"+datearray[0]);
  		  String dftime="00:45";
		  Date time1=sdf.parse(dftime);
		  Date time2=sdf.parse(crrntTime);
		  
  		  if(datearray[0].equals("02")&&(time2.before(time1)))
  		  {
  	      System.out.println("Hi");
  		  LocalDateTime returnvalue = now1.minusMonths(1);
  		  String monthYear=df.format(returnvalue);
  		  System.out.println(monthYear);
  		  Path sourceDirectory_Arch = Paths.get("target\\Shift_checklist.xlsx");
 	      Path targetDirectory_Arch= Paths.get("Archieve\\Shift_checklist_"+monthYear+".xlsx");
 	      Path sourceDirectory_Tmpl = Paths.get("Template\\Shift_checklist.xlsx");
 	      Path targetDirectory_Tmpl= Paths.get("target\\Shift_checklist.xlsx");
 	      Files.copy(sourceDirectory_Arch, targetDirectory_Arch,StandardCopyOption.REPLACE_EXISTING);
 	      Files.copy(sourceDirectory_Tmpl, targetDirectory_Tmpl,StandardCopyOption.REPLACE_EXISTING);
 	      }
	      List<List<String>> login = exlTab.ReadExcelTab("Source\\login.xlsx","Login",3);
	      List<List<String>> checklist=exlTab.ReadExcelTab("target\\Shift_checklist.xlsx","Shift-Readings",2);
	      exlTab.cleanSheetwheaders("target\\Shift_checklist.xlsx", "Comparison Logs");
	      exlTab.cleanSheetwoheaders("target\\Shift_checklist.xlsx", "DEV");
	      exlTab.cleanSheetwoheaders("target\\Shift_checklist.xlsx", "UAT");
	      exlTab.cleanSheetwoheaders("target\\Shift_checklist.xlsx", "PROD");
	      List<List<String>> comparison=exlTab.ReadExcelTab("target\\Shift_checklist.xlsx","Comparison Logs",2);
	      String devCheckStatus="NOT EXECUTED";
	      String uatCheckStatus="NOT EXECUTED";
          String prodCheckStatus="NOT EXECUTED";
          String devCustomMessage="NA";
          String uatCustomMessage="NA";
          String prodCustomMessage="NA";

	      
	      
	      List<List<String>> batch=exlTab.ReadExcelTab("Source\\Input_Sheet.xlsx","Dev Counter",1);
	      List<String> checklist_status=null;
	      List<String> alertList=new ArrayList<>();
	     
//	      List<String> Comparison_status=new ArrayList<>();
//	      List<String> Comparison_status=new ArrayList<>();
	      
	      int flag=0;
	      List<List<String>> statusTable=new ArrayList<List<String>>();
	      for(int i=0;i<login.size();i++){
	          if(login.get(i).get(6).equals("No")){
	               System.out.println("No Run");
	               flag++;
	            }
	           else
	            {
	            String url = login.get(i).get(5);
	            String urlFull="https://"+url+"/#/login";
	            String dom = login.get(i).get(2);
	            String uid = login.get(i).get(3);
	            String pwd = login.get(i).get(4);
	            String env=  login.get(i).get(1);
	            ApDrv.BrowserLaunch(urlFull);
	            Thread.sleep(10000);
	            if(env.equals("PROD")){
	            	Thread.sleep(30000);
	            }
	            cwLgn.login(dom, uid, pwd);
	            Thread.sleep(6000);
	            String homeUrlRequired=ApDrv.getcurrenturl();
	            String homeUrlActual="https://"+url+"/#/home";
	            if(ApDrv.isVisible(ApDrv.getElement(ElementConstants.ALTTEXT))||(!homeUrlRequired.equals(homeUrlActual))){
	            	lgn_stat="Critical";
	                 err_text=ApDrv.getText(ApDrv.getElement(ElementConstants.ALTTEXT));
	                 System.out.println("err text"+err_text);
	            	 enviroment.add(env);
	            	 
	            	ApDrv.tearDown();
	            	
	                 continue;
	              }   
	              else{
	                 Thread.sleep(10000);
	                 
	                 
	                 checklist_status = chkList.checklistCount(dom,env);
	                 checklist.add(checklist_status);
	                 statusTable=sys.synctab();
	                 for (List<String> list : statusTable) {
							System.out.println(list);
						  }
	                 System.out.println("size"+statusTable.size());
	                 List<String>syncrefr=new ArrayList<>();
	                 for(int j=2;j<statusTable.size();j ++){
	                	 syncrefr.add(statusTable.get(j).get(3));
                	}
	                 System.out.println(syncrefr);
	                 
	                 int countsync=0;
	                 if(syncrefr.contains("WRNG")){
                      do
                      {
	                	 countsync++;
	                	 
	                	 
	                	 statusTable=sys.synctab();
	                	 }while(countsync==1);
	                	 
	                 }
	                 ApDrv.tearDown();
	                 System.out.println("count "+countsync);
	                 for (List<String> list : statusTable) {
						System.out.println(list);
					  }
	                 List<String> Comparison_status=new ArrayList<>();
	                 String HeadcountnwComments="NR";
	       	         String HeadcountTdyComments="NR";  
	                 Comparison_status.add(checklist_status.get(0));//date
	                 Comparison_status.add(checklist_status.get(1));//time
	                 Comparison_status.add(checklist_status.get(2));//enviroment
	                 Comparison_status.add(checklist_status.get(3));//domain
	                 
	                 
//	                 for (String list : checklist_status) {
//	                	 Comparison_status.add(list);
//						
//					}
//	                 
	                 
	                 
//	                 int headcount_tdy=Integer.parseInt(checklist_status.get(4));
//	                 int headcount_nw=Integer.parseInt(checklist_status.get(5));
//	                 System.out.println("hdcount nw portal "+headcount_nw+""+"tdy portal"+headcount_tdy);
	                 LocalTime t = LocalTime.now(); // Gets the current time
	     	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	     	         String crtime=t.format(formatter);//current time as string
	     	         System.out.println("current time"+ crtime);
	                
	                 if(env.equalsIgnoreCase("Dev")){
	                	 List<List<String>> batchcount=exlTab.ReadExcelTab("Source\\Input_Sheet.xlsx","DEV_COUNT",1);
	   	     		     List<Integer>count=headcount(crtime, batchcount,batch);
	   	     		     System.out.println("hdcount nw "+count.get(0)+""+"tdy"+count.get(1));
	   	     		     if(count.get(1)==Integer.parseInt(checklist_status.get(4))){
	   	     		     Comparison_status.add("Passed");//hdcount today
	   	     		     Comparison_status.add(HeadcountTdyComments);
	   	     		     }
   	     		         else{
   	     		         Comparison_status.add("Failed");
   	     		         HeadcountTdyComments="Expected count "+count.get(1)+" & Actual count "+checklist_status.get(4);
   	     		         Comparison_status.add(HeadcountTdyComments);
   	     		         }
	   	     		    if(count.get(0)==Integer.parseInt(checklist_status.get(5))){
	   	     		     Comparison_status.add("Passed");//hdcount now
	   	     		     Comparison_status.add(HeadcountnwComments);
   	     		         }
   	     		         else{
   	     		         Comparison_status.add("Failed");
   	     		         HeadcountnwComments="Expected count "+count.get(0)+" Actual count "+checklist_status.get(5);
   	     		         Comparison_status.add(HeadcountnwComments);
   	     		         }
	   	     		     
	                	 System.out.println("DEV");
                         exlTab.writeExcel("target\\Shift_checklist.xlsx", "DEV", statusTable);
                         List<String>devFinStat=new ArrayList<>();
	                	 List<List<String>> devStat = exlTab.ReadExcelTab("target\\Shift_checklist.xlsx","DEV",3);
	                	 for(int j=0;j<devStat.size();j ++){
	                		 devFinStat.add(devStat.get(j).get(3));
	                	}
	                	 if( devFinStat.contains("WRNG")){
	                		 devSyncStatus="WARNING";
	                	 }
	                	 else{
	                		 devSyncStatus="NORMAL";
	                	 }
	                	 if(!(login.get(i).get(7).equals(""))&&!(login.get(i).get(8).equals(""))){
	                		 Date excelTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(login.get(i).get(8)); 
	                		 System.out.println("End date time "+excelTime);
	                		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	                		 Date date2 = new Date();
	           	    		 String crntTim=(dateFormat.format(date2));
	           	    		 Date crntTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(crntTim);
	           	    		 System.out.println("current time "+crntTime);
	           	    		 if(crntTime.before(excelTime)){
	           	    			 devCustomMessage=login.get(i).get(7);
	           	    		 }
	                		 
	                	 }
	                 }
	                 else if(env.equalsIgnoreCase("UAT")){
	                	 System.out.println("UAT");
	                	 int count=headcount("Source\\Input_Sheet.xlsx","UAT_COUNT");
	                	 System.out.println("uat count "+count);
	                	 if(count==Integer.parseInt(checklist_status.get(4))){
		   	     		     Comparison_status.add("Passed");//hdcount today
		   	     		     Comparison_status.add(HeadcountTdyComments);
		   	     		 }
	   	     		     else{
	   	     		         Comparison_status.add("Failed");
	   	     		         HeadcountTdyComments="Expected count "+count+" & Actual count "+checklist_status.get(4);
	   	     		         Comparison_status.add(HeadcountTdyComments);
	   	     		     }
		   	     		 if(count==Integer.parseInt(checklist_status.get(5))){
		   	     		     Comparison_status.add("Passed");//hdcount now
		   	     		     Comparison_status.add(HeadcountnwComments);
	   	     		     }
	   	     		     else{
	   	     		         Comparison_status.add("Failed");
	   	     		         HeadcountnwComments="Expected count "+count+" Actual count "+checklist_status.get(5);
	   	     		         Comparison_status.add(HeadcountnwComments);
	   	     		      }
		   	     		     
	                	 
                         exlTab.writeExcel("target\\Shift_checklist.xlsx", "UAT", statusTable);
                         List<String>uatFinStat=new ArrayList<>();
	                	 List<List<String>> uatStat = exlTab.ReadExcelTab("target\\Shift_checklist.xlsx","UAT",3);
	                	 for(int j=0;j<uatStat.size();j ++){
	                		 uatFinStat.add(uatStat.get(j).get(3));
	                	 }
	                	 if(uatFinStat.contains("WRNG")){
	                		 uatSyncStatus="WARNING";
	                	 }
	                	 else{
	                		 uatSyncStatus="NORMAL";
	                	 }
	                	 if(!(login.get(i).get(7).equals(""))&&!(login.get(i).get(8).equals(""))){
	                		 Date excelTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(login.get(i).get(8)); 
	                		 System.out.println("End date time "+excelTime);
	                		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	                		 Date date2 = new Date();
	           	    		 String crntTim=(dateFormat.format(date2));
	           	    		 Date crntTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(crntTim);
	           	    		 System.out.println("current time "+crntTime);
	           	    		 if(crntTime.before(excelTime)){
	           	    			 uatCustomMessage=login.get(i).get(7);
	           	    		 }
	                		 
	                	 }
	                 }
	                 else if(env.equalsIgnoreCase("PROD"))
	                 {
//	                	 List<List<String>> batchcount=exlTab.ReadExcelTab("Source\\Input_Sheet.xlsx","PROD_COUNT",1);
	                	 int count=headcount("Source\\Input_Sheet.xlsx","PROD_COUNT");
	                	 System.out.println("prod count "+count);
	                	 System.out.println("uat count "+count);
	                	 if(count==Integer.parseInt(checklist_status.get(4))){
		   	     		     Comparison_status.add("Passed");//hdcount today
		   	     		     Comparison_status.add(HeadcountTdyComments);
		   	     		 }
	   	     		     else{
	   	     		         Comparison_status.add("Failed");
	   	     		         HeadcountTdyComments="Expected count "+count+" & Actual count "+checklist_status.get(4);
	   	     		         Comparison_status.add(HeadcountTdyComments);
	   	     		     }
		   	     		 if(count==Integer.parseInt(checklist_status.get(5))){
		   	     		     Comparison_status.add("Passed");//hdcount now
		   	     		     Comparison_status.add(HeadcountnwComments);
	   	     		     }
	   	     		     else{
	   	     		         Comparison_status.add("Failed");
	   	     		         HeadcountnwComments="Expected count "+count+" Actual count "+checklist_status.get(5);
	   	     		         Comparison_status.add(HeadcountnwComments);
	   	     		      }
	                	 System.out.println("PROD");
                         exlTab.writeExcel("target\\Shift_checklist.xlsx", "PROD", statusTable);
                         List<String>prodFinStat=new ArrayList<>();
	                	 List<List<String>> prodStat = exlTab.ReadExcelTab("target\\Shift_checklist.xlsx","PROD",3);
	                	 for(int j=0;j<prodStat.size();j ++){
	                		 prodFinStat.add(prodStat.get(j).get(3));
	                	}
	                	 if(prodFinStat.contains("WRNG")){
	                		 prodSyncStatus="WARNING";
	                	 }
	                	 else{
	                		 prodSyncStatus="NORMAL";
	                	 }
	                	 if(!(login.get(i).get(7).equals(""))&&!(login.get(i).get(8).equals(""))){
	                		 Date excelTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(login.get(i).get(8)); 
	                		 System.out.println("End date time "+excelTime);
	                		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	                		 Date date2 = new Date();
	           	    		 String crntTim=(dateFormat.format(date2));
	           	    		 Date crntTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(crntTim);
	           	    		 System.out.println("current time "+crntTime);
	           	    		 if(crntTime.before(excelTime)){
	           	    			 prodCustomMessage=login.get(i).get(7);
	           	    		 }
	                		 
	                	 }
	                 }
	                 if(Integer.parseInt(checklist_status.get(4))!=0&&(Integer.parseInt(checklist_status.get(6))==0)){
	                	 Comparison_status.add("Failed"); 
	                 }
	                 else{
	                	 Comparison_status.add("Passed"); //alerts
	                 }
	                 if(checklist_status.get(7).equals("No data")){
	                	 Comparison_status.add("Failed"); 
	                 }
	                 else{
	                	 Comparison_status.add("Passed"); //tracker level headcount
	                 }
	                 if(checklist_status.get(8).equals("Not Displayed")){
	                	 Comparison_status.add("Failed"); 
	                 }
	                 else{
	                	 Comparison_status.add("Passed"); //tracker map
	                 }
	                 if(Integer.parseInt(checklist_status.get(4))!=0&&(Integer.parseInt(checklist_status.get(6))==0)&&(checklist_status.get(9).equals("Not Displayed"))){
	                	 Comparison_status.add("Failed"); 
	                 }
	                 else{
	                	 Comparison_status.add("Passed"); //Events
	                 }
	                 if(checklist_status.get(10).equals("0 h 0 m")){
	                	 Comparison_status.add("Passed"); // for the time being, we are changing the status as pass.Later on we will recheck the logic
	                 }
	                 else{
	                	 Comparison_status.add("Passed"); //hours clocked
	                 }
	                 if(checklist_status.get(11).equals("null")){
	                	 Comparison_status.add("Failed"); 
	                 }
	                 else{
	                	 Comparison_status.add("Passed"); //hours clocked
	                 }
	                 comparison.add(Comparison_status);
	 	            for (List<String> list : comparison) {
	 					System.out.println("hi"+list);
	 	            
	  				}
//	                 System.out.println("Hi"+Comparison_status);
	 	         //checking status of checklist
		            if (Comparison_status.contains("Failed")) {
		            	alertList.add("WARNING");
						
					}
		            else{
		            	alertList.add("NORMAL");
		            } 
		            System.out.println("list"+Comparison_status);
		            System.out.println("devsync"+devSyncStatus);
		            System.out.println("UATsync"+uatSyncStatus);
		            if(Comparison_status.contains("DEV")){
		            	if(Comparison_status.contains("Failed")||devSyncStatus.equals("WARNING")){
		            		devCheckStatus="WARNING";	
		                 }
		            	else{
		            		devCheckStatus="NORMAL";
		            	}
		            }	
		            if(Comparison_status.contains("UAT")){
		            	
		            	if(Comparison_status.contains("Failed")||uatSyncStatus.equals("WARNING")){
		            		uatCheckStatus="WARNING";
		            		
		                 }
		            	else{
		            		uatCheckStatus="NORMAL";
		            	}
		            }	
		            System.out.println("UATcheck"+uatCheckStatus);
		            
		            if(Comparison_status.contains("PROD")){
		            	if(Comparison_status.contains("Failed")||prodSyncStatus.equals("WARNING")){
		            		prodCheckStatus="WARNING";	
		                }
		            	else{
		            		prodCheckStatus="NORMAL";
		            	}
		            }	
	              }
	           
	            
	            
	           
	          
	            exlTab.writeToExcel("target\\Shift_checklist.xlsx", "Comparison Logs",comparison); 
	            exlTab.writeToExcel("target\\Shift_checklist.xlsx", "Shift-Readings",checklist); 
//	            List<List<String>> list=exlTab.ReadExcelTablast("Shift_checklist.xlsx","Shift-Readings");
//	            list.get(4);
	            }
	      }   
	      if(devSyncStatus.equals("WARNING")||uatSyncStatus.equals("WARNING")||prodSyncStatus.equals("WARNING")){
	    	  alertList.add("WARNING");
	      }
	      else{
          	alertList.add("NORMAL");
          }
	      System.out.println("alert list "+alertList);
	      
	     
	          
	          System.out.println(devSyncStatus+","+uatSyncStatus+","+prodSyncStatus);
	        //Email
		          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm a");  
		  		  LocalDateTime now = LocalDateTime.now();  
		  		  System.out.println(dtf.format(now));
		  		  String dateTimearr[]=dtf.format(now).split(","); 
		  		  String time=dateTimearr[1];
		          String subject;
		      if(!lgn_stat.equals("Critical")){
	          if(alertList.contains("WARNING")){
	        	subject="Status : WARNING ,NaviSafe Support Portal Monitoring : "+dtf.format(now);  
		  		 
	          }
	          else{
	        	  if(login.size()==flag){
	        		  subject="Status : NOT EXECUTED ,NaviSafe Support Portal Monitoring :  "+dtf.format(now);  
	        	  }
	        	  else{
	        	      subject="Status : NORMAL ,NaviSafe Support Portal Monitoring :  "+dtf.format(now);  
	        	  }
	          }
		      }
		      else{
		    	  subject="Status : CRITICAL ,NaviSafe Support Portal Monitoring : </font>"+dtf.format(now);  
		      }
		      System.out.println("enviroments"+enviroment);
	          
		      
		      String content="Hi Team,<br><br>\n\n";
		      content+="<u>OBSERVATION ( Time :"+time+" )</u><br><br>";
		      content+="Please find the Monitoring status report of Navisafe portal <br><br>\n\n ";
		      if(lgn_stat.equals("Critical")){
		    	  if(enviroment.contains("UAT")){
		    	  content+="<font color=red><b>"+"Unable to login to UAT. Showing "+err_text+" error</b></font><br><br>";
		    	  }
		    	  if(enviroment.contains("DEV")){
		    	  content+="<font color=red><b>"+"Unable to login to DEV. Showing "+err_text+" error</b></font><br><br>";
		           }
		          if(enviroment.contains("PROD")){
		        	content+="<font color=red> <b>"+"Unable to login to PROD. Showing "+err_text+" error</b></font><br><br>";
		           }
		      }
	
		      
		      content +="<b><u>Monitoring Status ( UAT ):</b></u><br>\n";
		      content+="Status :";
		      if(uatCheckStatus.equals("WARNING")){
		    	  content+="<font color=orange>WARNING</font><br>";  
		      }
		      else{
		    	  content+=uatCheckStatus+"<br>"; 
		      }
		      content +="<br>";
		      if(!uatCustomMessage.equals("NA")){
		    	  if(uatCheckStatus.equals("WARNING")){
		    	  content+="<i> Please Note: "+uatCustomMessage+"</i><br>";
		    	  content +="<br>";
		    	  }
		      }
		      content +="<br>";
		      content +="<b><u>Monitoring Status ( DEV ):</b></u><br>\n";
		      content+="Status :";
		      if(devCheckStatus.equals("WARNING")){
		    	  content+="<font color=orange>WARNING</font><br>";  
		      }
		      else{
		    	  content+=devCheckStatus+"<br>";  
		      }
		      content +="<br>";
		      if(!devCustomMessage.equals("NA")){
		    	  if(devCheckStatus.equals("WARNING")){
		    	  content+="<i> Please Note: "+devCustomMessage+"</i><br>";
		    	  content +="<br>";
		    	  }
		    	  
		      }
		      content +="<br>";
		      content +="<b><u>Monitoring Status( PROD ):</b></u><br>\n";
		      content+="Status :";
		      if(prodCheckStatus.equals("WARNING")){
		    	  content+="<font color=orange>WARNING</font><br>";  
		      }
		      else{
		    	  content+=prodCheckStatus+"<br>"; ; 
		      }
		      content +="<br>";
		      if(!prodCustomMessage.equals("NA")){
		    	  if(prodCheckStatus.equals("WARNING")){
		    	  content+="<i> Please Note: "+prodCustomMessage+"</i><br>";
		    	  content +="<br>";
		    	  }
		    	  
		      }
		      List<List<String>> emailList = exlTab.ReadExcelTab("Source\\login.xlsx","Email",3);
		      System.out.println("email"+emailList);
		      content +="\n\n<br>Thanks & Regards,<br> "+"\n"+"<b>Navisafe Team</b>";
		      String toEmail="148972@ust-global.com";
		      EmlDrv.sendMail("target\\Shift_checklist.xlsx","alerts@thenavisafe.com",toEmail,subject,content,emailList);
	          }
	
	//for dev
	public static List<Integer> headcount (String crtime,List<List<String>> batchcount,List<List<String>> time){
        
 		List<Integer>count=new ArrayList<>();
        int nwcount=0;
        int tdycount=0;
 		String identifier=null;
 		for(int i=1;i<time.size();i++){
	    String startTime=time.get(i).get(0);
	    String endTime=time.get(i).get(1);
	    System.out.println(startTime+" "+endTime);
	    LocalTime targetTime = LocalTime.parse(crtime) ;
 	    if( ( targetTime.isAfter(LocalTime.parse( startTime ) ) || ( targetTime.equals(LocalTime.parse( startTime ) ) ) ) && ( targetTime.isBefore(LocalTime.parse( endTime ) ) ) || ( targetTime.equals ( LocalTime.parse( endTime ) ) ) ) {
			identifier=time.get(i).get(2);
			System.out.println("passed");
//			 switch (identifier) {
//			case "c1":
//				if(targetTime.equals((LocalTime.parse( startTime )))){
//					int x=(Integer.valueOf(batchcount.get(1).get(1)));
//					int y=(Integer.valueOf(batchcount.get(3).get(1)));
//					int z=(Integer.valueOf(batchcount.get(4).get(1)));
//					int w=(Integer.valueOf(batchcount.get(5).get(1)));
//					nwcount=x+y+z+w;
//					tdycount=x+w;
//				}
//				else if(targetTime.equals((LocalTime.parse(endTime)))){
//					int x=(Integer.valueOf(batchcount.get(1).get(1)));
//					int y=(Integer.valueOf(batchcount.get(2).get(1)));
//					int w=(Integer.valueOf(batchcount.get(5).get(1)));
//					nwcount=x+y+w;
//					tdycount=x+y+w;
//				}
//				else{
//					nwcount=Integer.valueOf(batchcount.get(1).get(1))+(Integer.valueOf(batchcount.get(5).get(1)));
//					tdycount=Integer.valueOf(batchcount.get(1).get(1))+(Integer.valueOf(batchcount.get(5).get(1)));
//				}
//				break;
//			case "c2":
//				if(targetTime.equals((LocalTime.parse(endTime )))){
//					int x=(Integer.valueOf(batchcount.get(1).get(1)));
//					int y=(Integer.valueOf(batchcount.get(2).get(1)));
//					int z=(Integer.valueOf(batchcount.get(3).get(1)));
//					int w=(Integer.valueOf(batchcount.get(5).get(1)));
//					
//					nwcount=x+y+z+w;
//					tdycount=x+y+z+w;
//				}
//				else {
//					int x=(Integer.valueOf(batchcount.get(1).get(1)));
//					int y=(Integer.valueOf(batchcount.get(2).get(1)));
//					int w=(Integer.valueOf(batchcount.get(5).get(1)));
//					nwcount=x+y+w;
//					tdycount=x+y+w;
//				}
//				break;
//			case "c3":
//				if(targetTime.equals((LocalTime.parse(endTime )))){
//					int x=(Integer.valueOf(batchcount.get(4).get(1)));
//					int y=(Integer.valueOf(batchcount.get(2).get(1)));
//					int z=(Integer.valueOf(batchcount.get(3).get(1)));
//					int w=(Integer.valueOf(batchcount.get(1).get(1)));
//					int v=(Integer.valueOf(batchcount.get(5).get(1)));
//					nwcount=x+y+z+v;
//					tdycount=x+y+z+w+v;
//				}
//				else {
//					int x=(Integer.valueOf(batchcount.get(3).get(1)));
//					int y=(Integer.valueOf(batchcount.get(2).get(1)));
//					int w=(Integer.valueOf(batchcount.get(1).get(1)));
//					int v=(Integer.valueOf(batchcount.get(5).get(1)));
//					nwcount=x+y+v;
//					tdycount=x+y+w+v;
//				}
//				break;
//			case "c4":
//				    int x=(Integer.valueOf(batchcount.get(4).get(1)));
//					int z=(Integer.valueOf(batchcount.get(3).get(1)));
//					int w=(Integer.valueOf(batchcount.get(1).get(1)));
//					int y=(Integer.valueOf(batchcount.get(2).get(1)));
//					int v=(Integer.valueOf(batchcount.get(5).get(1)));
//					nwcount=x+z+v;
//					tdycount=x+z+w+y+v;
//				
//				break;
//
//			default:
//				System.out.println("invalid time");
//				break;
//			}
			 switch (identifier) {
				case "c1":
				      nwcount=Integer.valueOf(batchcount.get(1).get(1))+(Integer.valueOf(batchcount.get(5).get(1)));
					  tdycount=Integer.valueOf(batchcount.get(1).get(1))+(Integer.valueOf(batchcount.get(5).get(1)));

					break;
				case "c2":
                   int x=(Integer.valueOf(batchcount.get(1).get(1)));
					  int y=(Integer.valueOf(batchcount.get(2).get(1)));
					  int w=(Integer.valueOf(batchcount.get(5).get(1)));
					  nwcount=x+y+w;
					  tdycount=x+y+w;
					
					break;
				case "c3":
                     int a=(Integer.valueOf(batchcount.get(3).get(1)));
						int b=(Integer.valueOf(batchcount.get(2).get(1)));
						int c=(Integer.valueOf(batchcount.get(1).get(1)));
						int d=(Integer.valueOf(batchcount.get(5).get(1)));
						nwcount=a+b+c+d;
						tdycount=a+b+c+d;

					break;
				case "c4":
					    
						int e=(Integer.valueOf(batchcount.get(3).get(1)));
						int f=(Integer.valueOf(batchcount.get(1).get(1)));
						int g=(Integer.valueOf(batchcount.get(2).get(1)));
						int h=(Integer.valueOf(batchcount.get(5).get(1)));
						nwcount=g+e+h;
						tdycount=e+f+g+h;
					
					break;
						
				case "c5":
					   tdycount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(1).get(1))+Integer.valueOf(batchcount.get(2).get(1))+
							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));
					   nwcount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(2).get(1))+
							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));
					break;
				case "c6":
					   tdycount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(1).get(1))+Integer.valueOf(batchcount.get(2).get(1))+
							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));			
					   nwcount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(4).get(1))+Integer.valueOf(batchcount.get(5).get(1));
					break;
				case "c7":
					tdycount=Integer.valueOf(batchcount.get(1).get(1))+Integer.valueOf(batchcount.get(5).get(1));
					nwcount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(1).get(1))+
							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));
					break;
					
				default:
					System.out.println("invalid time");
					break;
				}
		}
		else{
			System.out.println("failed");
		}
    }
//    System.out.println("identifier"+ identifier);
//    System.out.println("now cnt"+ nwcount);
//    System.out.println("tdy cnt"+ tdycount);
    count.add(nwcount);
    count.add(tdycount);
    return count;
    
	}
	
	//foor UAT and DEV
	public static int headcount (String filename,String tabname){
		ExcelDriver exlTab = new ExcelDriver();
		List<List<String>> batchcount=exlTab.ReadExcelTab(filename,tabname,1);
		int headcount=Integer.parseInt(batchcount.get(1).get(1));
		return headcount;
		
	}
	
	
}
	      

	    	  
	    	
	

	



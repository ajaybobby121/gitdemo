package cwTestCases;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import baseClass.AppDriver;
import baseClass.EmailDriver;
import baseClass.ExcelDriver;
import cwModules.CWLogin;
import cwModules.CheckList;

public class dateCheck {

	public static void main(String[] args) throws ParseException, IOException, InterruptedException {
		// TODO Auto-generated method stub
         System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	      AppDriver ApDrv = new AppDriver();
	      ExcelDriver exlTab = new ExcelDriver();
	      EmailDriver EmlDrv=new EmailDriver();
	      CWLogin cwLgn = new CWLogin();
	      CheckList chkList=new CheckList();
	      List<List<String>> login = exlTab.ReadExcelTab("login.xlsx","Sheet1",2);
          List<List<String>> time=exlTab.ReadExcelTab("list.xlsx","data",1);
          for(int i=0;i<login.size();i++){
        	  String env=  login.get(i).get(1); 
          
		  
		  System.out.println("enviroment"+env);
		  System.out.println(login);
            LocalTime t = LocalTime.now(); // Gets the current time
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        String crtime=t.format(formatter);//current time as string
	        
	        System.out.println("CURRENT TIME : "+crtime); 
 
	     	   if(env.equalsIgnoreCase("UAT"))
	     	   {
	     		  List<List<String>> batchcount=exlTab.ReadExcelTab("list.xlsx","UAT_COUNT",1);
	     		  List<Integer>count=headcount(crtime, batchcount,time);
	     		  System.out.println(count);
	     		}
	     	   else if(env.equalsIgnoreCase("DEV")){
	     		  List<List<String>> batchcount=exlTab.ReadExcelTab("list.xlsx","DEV_COUNT",1);
	     		  List<Integer>count=headcount(crtime, batchcount,time);
	     		  System.out.println(count);
	     	   }
	     	  else if(env.equalsIgnoreCase("PROD")){
	     		  List<List<String>> batchcount=exlTab.ReadExcelTab("list.xlsx","PROD_COUNT",1);
	     		  List<Integer>count=headcount(crtime, batchcount,time);
	     		  System.out.println(count);
	     	   }
          }
	     		  
	     		   
	}
	
	     	   
	     	public static List<Integer> headcount (String crtime,List<List<String>> batchcount,List<List<String>> time){
		        
	     		List<Integer>count=new ArrayList<>();
		        int nwcount=0;
		        int tdycount=0;
	     		String identifier=null;
	     		for(int i=1;i<time.size();i++){
			    String startTime=time.get(i).get(0);
			    String endTime=time.get(i).get(1);
			    System.out.println(startTime+" "+endTime);
			    LocalTime targetTime = LocalTime.parse("23:01") ;
	     	    if( ( targetTime.isAfter(LocalTime.parse( startTime ) ) || ( targetTime.equals(LocalTime.parse( startTime ) ) ) ) && ( targetTime.isBefore(LocalTime.parse( endTime ) ) ) || ( targetTime.equals ( LocalTime.parse( endTime ) ) ) ) {
	  			identifier=time.get(i).get(2);
	  			System.out.println("passed");
	  			 switch (identifier) {
	 			case "c1":
	 				if(targetTime.equals((LocalTime.parse( startTime )))){
	 					int x=(Integer.valueOf(batchcount.get(1).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(3).get(1)));
	 					int z=(Integer.valueOf(batchcount.get(4).get(1)));
	 					nwcount=x+y+z;
	 					tdycount=Integer.valueOf(batchcount.get(1).get(1));
	 				}
	 				else if(targetTime.equals((LocalTime.parse(endTime)))){
	 					int x=(Integer.valueOf(batchcount.get(1).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(2).get(1)));
	 					nwcount=x+y;
	 					tdycount=x+y;
	 				}
	 				else{
	 					nwcount=Integer.valueOf(batchcount.get(1).get(1));
	 					tdycount=Integer.valueOf(batchcount.get(1).get(1));
	 				}
	 				break;
	 			case "c2":
	 				if(targetTime.equals((LocalTime.parse(endTime )))){
	 					int x=(Integer.valueOf(batchcount.get(1).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(2).get(1)));
	 					int z=(Integer.valueOf(batchcount.get(3).get(1)));
	 					nwcount=x+y+z;
	 					tdycount=x+y+z;
	 				}
	 				else {
	 					int x=(Integer.valueOf(batchcount.get(1).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(2).get(1)));
	 					nwcount=x+y;
	 					tdycount=x+y;
	 				}
	 				break;
	 			case "c3":
	 				if(targetTime.equals((LocalTime.parse(endTime )))){
	 					int x=(Integer.valueOf(batchcount.get(4).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(2).get(1)));
	 					int z=(Integer.valueOf(batchcount.get(3).get(1)));
	 					int w=(Integer.valueOf(batchcount.get(1).get(1)));
	 					nwcount=x+y+z;
	 					tdycount=x+y+z+w;
	 				}
	 				else {
	 					int x=(Integer.valueOf(batchcount.get(3).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(2).get(1)));
	 					int w=(Integer.valueOf(batchcount.get(1).get(1)));
	 					nwcount=x+y;
	 					tdycount=x+y+w;
	 				}
	 				break;
	 			case "c4":
	 				    int x=(Integer.valueOf(batchcount.get(4).get(1)));
	 					int z=(Integer.valueOf(batchcount.get(3).get(1)));
	 					int w=(Integer.valueOf(batchcount.get(1).get(1)));
	 					int y=(Integer.valueOf(batchcount.get(2).get(1)));
	 					nwcount=x+z;
	 					tdycount=x+z+w+y;
	 				
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
//	        System.out.println("identifier"+ identifier);
//	        System.out.println("now cnt"+ nwcount);
//	        System.out.println("tdy cnt"+ tdycount);
	        count.add(nwcount);
	        count.add(tdycount);
	        return count;
	        
     	}
//         public static List<List<String>> batchTimeCheck(String curTime,List<List<String>> batch) throws Exception{
//     		int row=batch.size();
//     		List<List<String>> tempList = new ArrayList<List<String>>();
//     		int counter=0;		
//     		AppDriver ApDrv = new AppDriver();
//     		for (int i = 1; i < batch.size(); i++) {
//             	String batchName=batch.get(i).get(0);
//             	String startTime=batch.get(i).get(1);
//             	String endTime=batch.get(i).get(2);
//             	String countUsers=batch.get(i).get(3);
//             	
//             	if(ApDrv.compareTimeAfter(curTime, startTime, endTime)){//func call to  check current time is after the start and end time
//             		String s="01:00:00";
//             		tempList.add(Arrays.asList(batchName,startTime,endTime,countUsers,s,"0"));
//             	}
//
//             	if(ApDrv.compareTime(curTime, startTime, endTime)) {
//             		System.out.println("counter :"+counter);
//             		String s=timeDifference(startTime, endTime, curTime);
//     				tempList.add(Arrays.asList(batchName,startTime,endTime,countUsers,s,"0"));
//     				counter++;
//     	
//     			}
//             	
//     		}		
//     		System.out.println("batchTimeCheck List :"+tempList);
//     		return tempList;
//     		}

	

}

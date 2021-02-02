package cwTestCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import baseClass.AppDriver;
import baseClass.ExcelDriver;
import constants.ElementConstants;
import cwModules.CWLogin;
import java.nio.file.Path; 
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Array;

public abstract class test {

	public static void main(String[] args) throws Exception {
		ExcelDriver exlTab = new ExcelDriver();
		// TODO Auto-generated method stub
//		String test1="Dec 11, 2020, 2:30:44 PM";
////		String test1="Dec 11,  2020, 2:30 PM";
//		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy,HH:mm aa");
////		Date aggrd1final = formatter.parse(test1);
////    	System.out.println("d1 "+aggrd1final);
//		String[]AGGRDLIST1=test1.split(",");
//		String[]AGGRDLIST2=AGGRDLIST1[2].split(":");
//		String[]AGGRDLIST3=AGGRDLIST2[2].split(" ");
//		String finalaggrq=AGGRDLIST1[0]+","+AGGRDLIST1[1]+","+AGGRDLIST2[0]+":"+AGGRDLIST2[1]+" "+AGGRDLIST3[1];
//		System.out.println(finalaggrq);
//		Date aggrd1final = formatter.parse(finalaggrq);
//    	System.out.println("d1 "+aggrd1final);
//    	String test3="Fri Dec 11 11:45:00 IST 2020";
//    	String test4="Sat Dec 12 12:15:00 IST 2020";
//    	SimpleDateFormat formatterq = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
//    	Date date3=formatterq.parse(test3);
//    	Date date4=formatterq.parse(test4);
//    			
//    	if(date3.before(date4)){
//    		System.out.println("pass");
//    	}
//    	else{
//    		System.out.println("failed");
////    	}
//		SimpleDateFormat formatterq1 = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
//		Date date2 = new Date();
//    	String current2=formatterq1.format(date2);
//    	Date currentdate2=formatterq1.parse(current2);
//    	System.out.println(currentdate2);
//    	List<List<String>> checklist=exlTab.ReadExcelTablast("Shift_checklist_12.xlsx","Shift-Readings");
//    	System.out.println(checklist);
//		 List<List<String>> checklist=exlTab.ReadExcelTab("Shift_checklist_2.xlsx","DEV",9);
//		 checklist.get(1).set(1,"2");
//		 checklist.get(1).set(2,"3");
//		 checklist.get(2).set(1,"3");
//		 checklist.get(2).set(2,"2");
//		 exlTab.WriteExcelTab("Shift_checklist_2.xlsx", "DEV", checklist, 13, 0);
//		List<List<String>> comparison=exlTab.ReadExcelTab("Shift_checklist_2.xlsx","Comparison Logs",1);
//		for (List<String> list : comparison) {
//			System.out.println(list);
//		}
//		exlTab.cleanSheet("Shift_checklist_222.xlsx", "sheet");
//		int int_rand=(int)(Math.random()*((12-3)+1)+3);
////		System.out.println(int_rand);
//		Email from = new Email("alerts@thenavisafe.com");
//	       Email to = new Email("148972@ust.com"); // use your own email address here
//
//	       String subject = "Sending with Twilio SendGrid is Fun";
//	       Content content = new Content("text/html", "and <em>easy</em> to do anywhere with <strong>Java</strong>");

//	       Mail mail = new Mail(from, subject, to, content);
//	       java.nio.file.Path exlpath = java.nio.file.Paths.get("Shift_checklist.xlsx" );
//		      byte[] filecontent = java.nio.file.Files.readAllBytes(exlpath);
//		      String fileData = com.mailjet.client.Base64.encode(filecontent);
//	       Path file = Paths.get("Shift_checklist.xlsx");
//	        Attachments attachments = new Attachments();
//	        attachments.setFilename(file.getFileName().toString());
//	        attachments.setType("application/excel");
//	        attachments.setDisposition("attachment");
//
//	        byte[] attachmentContentBytes = Files.readAllBytes(file);
//	        String attachmentContent = Base64.getMimeEncoder().encodeToString(attachmentContentBytes);
//	        attachments.setContent(attachmentContent);
//	        mail.addAttachments(attachments);
//	       final String path = "Shift_checklist.xlsx";
//
//	       byte[] bFile = null;
//	       try {
//	         bFile = Files.readAllBytes(new File(path).toPath());
//	       } catch (IOException e) {
//	         e.printStackTrace();
//	       }
//	       Attachments attachments3 = new Attachments();
//	       Base64 x = new Base64();
//	       String imageDataString = x.encodeAsString(bFile);
//	       attachments3.setContent(imageDataString);
//	       attachments3.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//	       attachments3.setFilename("Shift_checklist.xlsx");
//	       attachments3.setDisposition("attachment");
//	       mail.addAttachments(attachments3);
//
////	       SendGrid sg = new SendGrid(System.getenv("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"));
//	       SendGrid sg = new SendGrid("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");
//	       Request request = new Request();
//
//	       request.setMethod(Method.POST);
//	       request.setEndpoint("mail/send");
//	       request.setBody(mail.build());
//
//	       Response response = sg.api(request);
//
//	       System.out.println(response.getStatusCode());
//	       System.out.println(response.getHeaders());
//	       System.out.println(response.getBody());
//		AppDriver ApDrv = new AppDriver();
//		CWLogin cwLgn = new CWLogin();
//		String lgn_stat="NR";
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//		   WebDriver driver=new ChromeDriver();
//		   List<List<String>> login = exlTab.ReadExcelTab("login.xlsx","Sheet1",2);
//		   for(int i=0;i<login.size();i++){
//		   
//		   String url = login.get(i).get(5);
//		   String urlFull="https://"+url+"/#/login";
//           String dom = login.get(i).get(2);
//           String uid = login.get(i).get(3);
//           String pwd = login.get(i).get(4);
//           String env=  login.get(i).get(1);
//           ApDrv.BrowserLaunch(urlFull);
//           Thread.sleep(10000);
//           cwLgn.login(dom, uid, pwd);
//           Thread.sleep(6000);
//           String homeUrlRequired="https://"+url+"/#/home";
//           String ur11=ApDrv.getcurrenturl();
//           System.out.println(ur11);
//           String homeUrlActual="https://"+url+"/#/home";
//           if(ApDrv.isVisible(ApDrv.getElement(ElementConstants.ALTTEXT))||(!homeUrlRequired.equals(homeUrlActual))){
//           	lgn_stat="Critical";
//                break;
//             }   
////	       try{
////	    	    driver.switchTo().alert();
////	    	    System.out.println("alert popup");
////	    	    // If it reaches here, it found a popup
////	    	} catch(NoAlertPresentException e){
////	    		System.out.println("no alert");
////	    	}
//		   }
//		   System.out.println(lgn_stat);
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM-yyyy");  
		  DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
		  LocalDateTime now1 = LocalDateTime.now();
		  LocalDateTime now2 = LocalDateTime.now(); 
		  String now="01-Jan-2021";
//
		  Date date1=new SimpleDateFormat("dd-MMM-yyyy").parse(now);
		  
		  System.out.println(date1);
		  
  		  System.out.println(dtf1.format(now1));
  		  String date=dtf1.format(now1);
  		  String[] datearray=date.split("-");
  		  System.out.println("day"+datearray[0]);
  		  if(datearray[0].equals("01")){
  			LocalDateTime returnvalue = now1.minusMonths(1);
  			String monthYear=dtf.format(returnvalue);
  			System.out.println(monthYear);
  			 Path sourceDirectory_Arch = Paths.get("Source\\Shift_checklist.xlsx");
 	        Path targetDirectory_Arch= Paths.get("Archieve\\Shift_checklist_"+monthYear+".xlsx");
 	       Path sourceDirectory_Tmpl = Paths.get("Template\\Shift_checklist.xlsx");
 	      Path targetDirectory_Tmpl= Paths.get("Source\\Shift_checklist.xlsx");
 	        
 	       Files.copy(sourceDirectory_Arch, targetDirectory_Arch,StandardCopyOption.REPLACE_EXISTING);
 	      Files.copy(sourceDirectory_Tmpl, targetDirectory_Tmpl,StandardCopyOption.REPLACE_EXISTING);
 	       }
  		  
//  		LocalDateTime returnvalue = now.minusMonths(1); 
//  		 System.out.println(dtf1.format(returnvalue));
//  		
//		 Path sourceDirectory = Paths.get("source\\Shift_checklist.xlsx");
//	        Path targetDirectory = Paths.get("Email_target\\bug.xlsx");
////
//	        //copy source to target using Files Class
//	        Files.copy(sourceDirectory, targetDirectory,StandardCopyOption.REPLACE_EXISTING);
		 
           
		
		 
//		 System.out.println(checklist);
//		  int c=0;
//		  
//		for (int i = 0; i < 3; i++) {
//			c++;
//			String hrs_clcked="0 h 0 m";
//		  if(!hrs_clcked.equals("0 h 0 m")){
//				 System.out.println("hrs clocked "+hrs_clcked);
//				 System.out.println("a");
//				 break;
//			 }
//			 if(c==3&&hrs_clcked.equals("0 h 0 m")){
//				 System.out.println("b");
//			 }
//		}
//		  DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM-yyyy");  
//		  DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
//		  LocalDateTime now1 = LocalDateTime.now();
//		  System.out.println(df1.format(now1));
//  		  String date=df1.format(now1);
//  		  String[] datearray=date.split("-");
//  		  System.out.println("day"+datearray[0]);
//  		  if(datearray[0].equals("10"))
//  		  {
//  	      System.out.println("Hi");
//  		  LocalDateTime returnvalue = now1.minusMonths(1);
//  		  String monthYear=df.format(returnvalue);
//  		  System.out.println(monthYear);
//  		  Path sourceDirectory_Arch = Paths.get("target\\Shift_checklist.xlsx");
// 	      Path targetDirectory_Arch= Paths.get("Archieve\\Shift_checklist_"+monthYear+".xlsx");
// 	      Path sourceDirectory_Tmpl = Paths.get("Template\\Shift_checklist.xlsx");
// 	      Path targetDirectory_Tmpl= Paths.get("target\\Shift_checklist.xlsx");
// 	      Files.copy(sourceDirectory_Arch, targetDirectory_Arch,StandardCopyOption.REPLACE_EXISTING);
// 	      Files.copy(sourceDirectory_Tmpl, targetDirectory_Tmpl,StandardCopyOption.REPLACE_EXISTING);
// 	      }
//  		List<List<String>> login = exlTab.ReadExcelTab("Source\\login.xlsx","Login",2);
//  		String message=null;
//  		String time=null;
////  	    for(int i=0;i<login.size();i++){ 
//  	    	message=(login.get(1).get(7));
//  	    	time=(login.get(1).get(8));
//  	    	 if((!login.get(0).get(7).equals(""))&&(!login.get(0).get(8).equals(""))){
//  	    		Date date1=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(login.get(0).get(8));
//  	    		System.out.println(date1);
////  	    		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
////  	    		LocalDateTime now2 = LocalDateTime.now();
////  			    Date d=(df2.format(now2));
//  	    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//  	    		Date date2 = new Date();
//  	    		String d=(dateFormat.format(date2));
//  	    		Date d1=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(d);
//  	    		System.out.println(d1);
//  	    		if(date1.before(d1)){
//  	    			System.out.println("true");
//  	    		}
//  	    		else{
//  	    			System.out.println("fail");
//  	    			
//  	    		}
//        	 }
//  	    	 else{
//  	    		 System.out.println("failed");
//  	    	 }
//  	    	
////  	    }
////  	    if((!message.equals(null))&&(!time.equals(null))){
////  	    	if(time)
////  	    	
////  	    }
//  		Email from = new Email("alerts@thenavisafe.com");
//	       Email to = new Email("148972@ust.com"); // use your own email address here
////
//	       String subject = "Sending with Twilio SendGrid is Fun";
//	       Content content = new Content("text/html", "and <em>easy</em> to do anywhere with <strong>Java</strong>");
//
//	       Mail mail = new Mail(from, subject, to, content);
////  		Mail mail = new Mail();
//  		List<List<String>> email = exlTab.ReadExcelTab("Source\\login.xlsx","Email",3);
//     
//  		 Personalization personalization = new Personalization();
//
//  		 for (int i = 0, size = email.size(); i < size; i++) {
//  		    personalization.addTo(new Email(email.get(i).get(1)));             
//  		 }
//  		 mail.addPersonalization(personalization);
//  		 SendGrid sg = new SendGrid("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");
//	      
//  		Request request = new Request();
//
//	       request.setMethod(Method.POST);
//	       request.setEndpoint("mail/send");
//	       request.setBody(mail.build());
//	      
//	       Response response = sg.api(request);
//
//	       System.out.println(response.getStatusCode());
//	       System.out.println(response.getHeaders());
//	       System.out.println(response.getBody());
//		 List<List<String>> batch=exlTab.ReadExcelTab("C:\\Users\\148972\\Desktop\\Input_Sheet.xlsx","Dev Counter",1);
//		 List<List<String>> batchcount=exlTab.ReadExcelTab("Source\\Input_Sheet.xlsx","DEV_COUNT",1);
//		 String crtime="00:25";
//		 List<Integer>count=headcount(crtime, batchcount,batch);
//		 System.out.println("NW"+count.get(0));
//		 System.out.println("tdy"+count.get(1));
//		String currentrpt;
//		String rpt1pls5;
//		SimpleDateFormat formatterq1 = new SimpleDateFormat("MMM d, yyyy,hh:mm aa");
//		Date currentdatrpt=formatterq1.parse(currentrpt);
//		Date rpt1datepls5=formatterq1.parse(rpt1pls5);
//		if(reportjb1wosec.equals(reportjbwosec2)){
//		if(currentdatrpt.before(rpt1datepls5)){
////			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
////			tableEntry6.add("In Sync");
////			tableEntry6.add("");
////			tableEntry6.add("NR");
//			System.out.println("passed");
//		}
//		else{
//			System.out.println("Failed rpt 1");
////			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
////			tableEntry6.add("Not in Sync");
////    		tableEntry6.add("Before time: "+reportjb1wosec+" and after time: "+reportjbwosec2+" are not in sync.  Current"
////    				+ "time "+currentdatrpt);
////    		tableEntry6.add("WRNG");
//		}
//	}
//	else{
//		if(reportjbwosec2.equals(rpt1datepls5)){
//			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
////			tableEntry6.add("In Sync");
////			tableEntry6.add("");
////			tableEntry6.add("NR");
//			System.out.println("passed");
//		}
//		else{
//			System.out.println("Failed rpt 2");
//			System.out.println(crntdaterptms5+"crntime"+currentdatrpt);
////			tableEntry6.add("Not in Sync");
////    		tableEntry6.add("Before time : "+reportjb1wosec+" and after time: "+reportjbwosec2+" are not in sync. Current"
////    				+ "time "+currentdatrpt);
////    		tableEntry6.add("WRNG");
//		}
//	}
//	}		
//  		public static List<Integer> headcount (String crtime,List<List<String>> batchcount,List<List<String>> time){
//  	        
//  	 		List<Integer>count=new ArrayList<>();
//  	        int nwcount=0;
//  	        int tdycount=0;
//  	 		String identifier=null;
//  	 		for(int i=1;i<time.size();i++){
//  		    String startTime=time.get(i).get(0);
//  		    String endTime=time.get(i).get(1);
//  		    System.out.println(startTime+" "+endTime);
//  		    LocalTime targetTime = LocalTime.parse(crtime) ;
//  	 	    if( ( targetTime.isAfter(LocalTime.parse( startTime ) ) || ( targetTime.equals(LocalTime.parse( startTime ) ) ) ) && ( targetTime.isBefore(LocalTime.parse( endTime ) ) ) || ( targetTime.equals ( LocalTime.parse( endTime ) ) ) ) {
//  				identifier=time.get(i).get(2);
//  				System.out.println("passed");
//  				 switch (identifier) {
//  				case "c1":
// 				      nwcount=Integer.valueOf(batchcount.get(1).get(1))+(Integer.valueOf(batchcount.get(5).get(1)));
//  					  tdycount=Integer.valueOf(batchcount.get(1).get(1))+(Integer.valueOf(batchcount.get(5).get(1)));
//
//  					break;
//  				case "c2":
//                      int x=(Integer.valueOf(batchcount.get(1).get(1)));
//  					  int y=(Integer.valueOf(batchcount.get(2).get(1)));
//  					  int w=(Integer.valueOf(batchcount.get(5).get(1)));
//  					  nwcount=x+y+w;
//  					  tdycount=x+y+w;
//  					
//  					break;
//  				case "c3":
//                        int a=(Integer.valueOf(batchcount.get(3).get(1)));
//  						int b=(Integer.valueOf(batchcount.get(2).get(1)));
//  						int c=(Integer.valueOf(batchcount.get(1).get(1)));
//  						int d=(Integer.valueOf(batchcount.get(5).get(1)));
//  						nwcount=a+b+c+d;
//  						tdycount=a+b+c+d;
// 
//  					break;
//  				case "c4":
//  					    
//  						int e=(Integer.valueOf(batchcount.get(3).get(1)));
//  						int f=(Integer.valueOf(batchcount.get(1).get(1)));
//  						int g=(Integer.valueOf(batchcount.get(2).get(1)));
//  						int h=(Integer.valueOf(batchcount.get(5).get(1)));
//  						nwcount=g+e+h;
//  						tdycount=e+f+g+h;
//  					
//  					break;
//  						
//  				case "c5":
//  					   tdycount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(1).get(1))+Integer.valueOf(batchcount.get(2).get(1))+
//  							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));
//  					   nwcount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(2).get(1))+
//  							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));
//  					break;
//  				case "c6":
//  					   tdycount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(1).get(1))+Integer.valueOf(batchcount.get(2).get(1))+
//  							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));			
//  					   nwcount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(4).get(1))+Integer.valueOf(batchcount.get(5).get(1));
//  					break;
//  				case "c7":
//  					tdycount=Integer.valueOf(batchcount.get(1).get(1))+Integer.valueOf(batchcount.get(5).get(1));
//  					nwcount=Integer.valueOf(batchcount.get(3).get(1))+Integer.valueOf(batchcount.get(1).get(1))+
//  							Integer.valueOf(batchcount.get(5).get(1))+Integer.valueOf(batchcount.get(4).get(1));
//  					break;
//  					
//  				default:
//  					System.out.println("invalid time");
//  					break;
//  				}
//  			}
//  			else{
//  				System.out.println("failed");
//  			}
//  	    }
//  	 		count.add(nwcount);
//  	 	    count.add(tdycount);
//			return count; 
//	}

}
}

package cwTestCases;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import baseClass.AppDriver;
import baseClass.EmailDriver;
import baseClass.ExcelDriver;
import constants.ElementConstants;
import cwModules.CWLogin;
import cwModules.CheckList;

public class Monitoring {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	      AppDriver ApDrv = new AppDriver();
	      ExcelDriver exlTab = new ExcelDriver();
	      EmailDriver EmlDrv=new EmailDriver();
	      CWLogin cwLgn = new CWLogin();
	      CheckList chkList=new CheckList();
	      List<List<String>> login = exlTab.ReadExcelTab("login.xlsx","Sheet1",2);
	      List<List<String>> checklist=exlTab.ReadExcelTab("Shift_checklist_1.xlsx","Shift-Readings",2);
	      List<String> checklist_status=null;
	      for(int i=0;i<login.size();i++){
	          if(login.get(i).get(1).equals("No")){
	               System.out.println("No Run");
	            }
	           else
	            {
	            String url = login.get(i).get(5);
	            String dom = login.get(i).get(2);
	            String uid = login.get(i).get(3);
	            String pwd = login.get(i).get(4);
	            String env=  login.get(i).get(1);
	            ApDrv.BrowserLaunch(url);
	            Thread.sleep(10000);
	            cwLgn.login(dom, uid, pwd);
	            Thread.sleep(4000);
	              if(ApDrv.isVisible(ApDrv.getElement(ElementConstants.ALTTEXT))){
	            	 
	                 break;
	              }   
	              else{
	                 Thread.sleep(7000);
	                 
	                 checklist_status = chkList.checklistCount(dom,env);
	                 checklist.add(checklist_status);
	                 }  
	              exlTab.writeToExcel("Shift_checklist_1.xlsx", "Shift-Readings",checklist);  
	            }
	         }
	      //Email
	      String subject="Monitoring checklist";
	      String content="Hi Team,"+"\n\n"+"Please find the Monitoring status report of Navisafe portal"+"\n\n"+"Thanks & Regards,"+"\n"+"Ajay Bobby";
	      String toEmail="148972@ust-global.com,148972@ust-global.com";
//	      EmlDrv.sendSimpleMessage("Shift_checklist_1.xlsx","alerts@thenavisafe.com",toEmail,subject,content);
	    }
	}

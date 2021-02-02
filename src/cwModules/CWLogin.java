package cwModules;

import java.io.IOException;
import baseClass.AppDriver;
import constants.ElementConstants;

public class CWLogin extends AppDriver{
	static String propFileName = "C:\\Users\\148972\\Downloads\\CWAutomation\\CWAutomation\\src\\elements\\Locators.properties";
	

	public CWLogin() throws IOException, InterruptedException{
		super(propFileName);
	}
	
	public String login(String Domain, String UsrNme, String passwrd){
		System.out.println("Ready to Login"); //delete later
		
		setValue(getElement(ElementConstants.DOMAIN), Domain);
		setValue(getElement(ElementConstants.USERNAME), UsrNme);
		setValue(getElement(ElementConstants.PASSWORD), passwrd);
		click(getElement(ElementConstants.LOGINBTN));
		sleep(2000);
		return "True";
	}
	
	public String logout(){
		sleep(5000);
		click(getElement(ElementConstants.SETTING));
		sleep(500);
		click(getElement(ElementConstants.LOGOUT));
		sleep(500);
		return "True";
	}
	

	
	
}

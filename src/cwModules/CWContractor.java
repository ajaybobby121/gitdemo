package cwModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import baseClass.AppDriver;
import constants.ElementConstants;

public class CWContractor extends AppDriver {
	AppDriver ApDrv=new AppDriver();
	static String propFileName ="C:\\Users\\148972\\Downloads\\CWAutomation\\CWAutomation\\src\\elements\\Locators.properties";
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
	
	public CWContractor()throws IOException, InterruptedException {
		super(propFileName);
	} 
	public List<String> contractor_sidenav() throws InterruptedException{
		List<String> Contractor_count_list= new ArrayList<String>();
		System.out.println("START - 4. contractor_SideNav");
		click(getElement(ElementConstants.CONSIDENAV));
		explicitWaitByxpath(getElement(ElementConstants.CONTFIRSTTILE));
		int count_rep=Integer.parseInt(getText(getElement(ElementConstants.CONCNT)));
		if(isVisible(getElement(ElementConstants.CONPAGINATION)))
		{
		int tiles_count=tilescontrcountPagination();
		System.out.println(tiles_count);
		
		if(count_rep==tiles_count){
			Contractor_count_list.add("Pass");//total contractor-174
			Contractor_count_list.add("Pass");//list of contractor -175
			System.out.println("Passed");
		}
		else{
			Contractor_count_list.add("Failed");//total contractor-174
			Contractor_count_list.add("Failed");//list of contractor -175
			System.out.println("Failed");
		}
		}
		else
		{
		 List<WebElement>tile=getelements(getElement(ElementConstants.CONTILES));	
		 if(count_rep==tile.size()){
			Contractor_count_list.add("Pass");//total contractor-174
			Contractor_count_list.add("Pass");//list of contractor -175
			System.out.println("Passed");
		   }
		 else{
		     Contractor_count_list.add("Failed");//total contractor-174
			 Contractor_count_list.add("Failed");//list of contractor -175
			 System.out.println("Failed");
			}
		 }
		//1st contractor
		if(isVisible(getElement(ElementConstants.CONPIC))){
			Contractor_count_list.add("Pass");//176 pic
		}
		else{
			Contractor_count_list.add("Failed");//176 pic
		}
		if(isVisible(getElement(ElementConstants.CONDETAILS))){
			Contractor_count_list.add("Pass");//177 name
			Contractor_count_list.add("Pass");//178 address
			Contractor_count_list.add("Pass");//179 reg no
		}
		else{
			Contractor_count_list.add("Failed");//177 name
			Contractor_count_list.add("Failed");//178 address
			Contractor_count_list.add("Failed");//179 reg no
		}
		if(isVisible(getElement(ElementConstants.CONNUM))){
			Contractor_count_list.add("Pass");//180 mob
		}
		else{
			Contractor_count_list.add("Failed");//180 mob
		}
		if(isVisible(getElement(ElementConstants.CONEMAIL))){
			Contractor_count_list.add("Pass");//181 email
		}
		else{
			Contractor_count_list.add("Failed");//181 email
		}
		Contractor_count_list.add("Not Feasible");//182 loaders
			
		
//		System.out.println("tile count"+tile_count);
		
		return Contractor_count_list;
	}

}


package cwModules;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import constants.ElementConstants;

public class CWPlants extends AppDriver{
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
	public static final int TIME_25 = 25000;
	public static final int TIME_30 = 30000;
	
	public CWPlants()throws IOException, InterruptedException {
		super(propFileName);
	}
	@SuppressWarnings("unchecked")
	public List<String> Plants_SideNav() throws InterruptedException{
		List<String> plant_count_list= new ArrayList<String>();
		System.out.println("START - 3. Plants_SideNav");
		click(getElement(ElementConstants.PROSIDENAV));
		Thread.sleep(TIME_02);
		Select plt_drp_options=new Select(path(getElement(ElementConstants.PLTDROP)));
		plt_drp_options.selectByIndex(0);
		sleep(TIME_03);
		String plnt_cnt_int=getText(getElement(ElementConstants.PLTCNT));
		click(getElement(ElementConstants.HMESIDENAV));
		explicitWaitByxpath(getElement(ElementConstants.HMEPROJECT));
		String plnt_cnt_hme=getText(getElement(ElementConstants.HMEPROJECT));
		
		if(plnt_cnt_int.equals(plnt_cnt_hme)){
		  plant_count_list.add("Pass");//total plant representation -166
		}
		else{
		  plant_count_list.add("Failed");//total plant representation -166
		}
		click(getElement(ElementConstants.PROSIDENAV));
		Thread.sleep(TIME_02);
		int plnt_cnt_pnt_int=Integer.parseInt(plnt_cnt_int);
		List<WebElement> plnt_cnt_tiles=getelements(getElement(ElementConstants.PLNT_TILES));
		if(plnt_cnt_pnt_int==(plnt_cnt_tiles.size())){
			 plant_count_list.add("Pass");//list of plants -167
		}
		else{
		  plant_count_list.add("Failed");//list of plants-167
		}
		plt_drp_options.selectByIndex(1);
		Thread.sleep(TIME_02);
		
//		click(getElement(ElementConstants.HMESIDENAV));
//		explicitWaitByxpath(getElement(ElementConstants.HME_REC_ALERT_TABLE));
//		String hme_headcount_now=getText(getElement(ElementConstants.HMEHEADCOUNTNOW));
//		String alerts_home=getText(getElement(ElementConstants.HMETOTALERTS));
//		String total_hrs_clocked_hme=getText(getElement(ElementConstants.HMEHOURSCLOCKED));
//		String zones_hme=getText(getElement(ElementConstants.HMEZONES));
//		click(getElement(ElementConstants.PROSIDENAV));
//		Thread.sleep(TIME_02);
//		String plant_notifications=getText(getElement(ElementConstants.PJTNOWCNTNOTIFICATION));
//		int home_notification=0;
//		try{
//	    int plant_notifications_int=Integer.parseInt(plant_notifications);
//	    home_notification=Integer.parseInt(alerts_home);
//	    System.out.println(plant_notifications_int + "," +home_notification);
//	    if(plant_notifications_int==(home_notification)){
//			 plant_count_list.add("Pass");//notifications 158
//		}
//		else{
//		  plant_count_list.add("Failed");//notifications 158
//		}
//		}catch(NumberFormatException e){
//		System.out.println("Number format exception");	
//		plant_count_list.add("Failed");//notifications 158
//		}
	    if(isVisible(getElement(ElementConstants.PLNT_PIC))){
	    	plant_count_list.add("Pass");//168 pic
	    }
	    else{
	    	plant_count_list.add("Failed");//168 pic
	    }
	    if(isVisible(getElement(ElementConstants.PLNT_DETAILS))){
	    	plant_count_list.add("Pass");//169 details
	    	plant_count_list.add("Pass");//170
	    }
	    else{
	    	plant_count_list.add("Failed");//169 details
	    	plant_count_list.add("Failed");//170
	    }
	    if(isVisible(getElement(ElementConstants.PLNT_LOC))){
	    String plant_name=getText(getElement(ElementConstants.PLNT_TILE_NAME_1));
	    click(getElement((ElementConstants.PLNT_LOC)));
	    explicitWaitByxpath(getElement(ElementConstants.TRKPLNTNAME));
	    Thread.sleep(3000);
	    String trk_plant_name=getText(getElement(ElementConstants.TRKPLNTNAME));
	    System.out.println("a"+trk_plant_name.trim()+"a");
	    if(plant_name.trim().equals(trk_plant_name.trim()))
		    {
		    	plant_count_list.add("Pass");//locate 171
		    	plant_count_list.add("Pass");//locate 172
			}
			else{
			  plant_count_list.add("Failed");//locate 171
			  plant_count_list.add("Failed");//locate 172
			}
		    click(getElement(ElementConstants.PROSIDENAV));
	    }
	    else{
	    	 plant_count_list.add("Failed");//locate 171
			 plant_count_list.add("Failed");//locate 172
	    }
	    plant_count_list.add("Not Feasible");//173 loaders
	    plt_drp_options.selectByIndex(0);
	    Thread.sleep(TIME_02);
	    
//	    System.out.println("a"+plant_name.trim()+"a");
//	    String plant_name=getText(getElement(ElementConstants.PLNT_TILE_NAME_1));
//	    click(getElement((ElementConstants.PLNT_LOC)));
//	    explicitWaitByxpath(getElement(ElementConstants.TRKPLNTNAME));
//	    Thread.sleep(3000);
//	    String trk_plant_name=getText(getElement(ElementConstants.TRKPLNTNAME));
//	    System.out.println("a"+trk_plant_name.trim()+"a");
//	    System.out.println("a"+plant_name.trim()+"a");
//	    if(plant_name.trim().equals(trk_plant_name.trim()))
//	    {
//	    	plant_count_list.add("Pass");//locate 159
//		}
//		else{
//		  plant_count_list.add("Failed");//locate 159
//		}
//	    click(getElement(ElementConstants.PROSIDENAV));
//		Thread.sleep(TIME_04);
//	    String plant_headcnt_now=getText(getElement(ElementConstants.PJTHEADCOUNTNOW));
//	    String plant_headcnt_now_arr[]=plant_headcnt_now.split(":");
//	    String plant_headcnt_now_actual=plant_headcnt_now_arr[1].trim();
//	    int headcount_now_plant=Integer.parseInt(plant_headcnt_now_actual);
//	    int headcount_now_home=Integer.parseInt(hme_headcount_now);
//	    System.out.println(headcount_now_plant + "," +headcount_now_home);
//	    if(headcount_now_plant==headcount_now_home)
//	    {
//	    	plant_count_list.add("Pass");//headcount now 160
//		}
//		else{
//		  plant_count_list.add("Failed");//headcount now 160
//		}
//	    String plant_hoursclocked=getText(getElement(ElementConstants.PJTNOWCNTHOURSCLOCKED));
//	    String plant_hoursclocked_arr[]=plant_hoursclocked.split(":");
//	    String plant_hoursclocked_actual=plant_hoursclocked_arr[1].trim();
//	    int plant_hoursclocked_act=0;
//	    if(plant_hoursclocked_actual.equals("0")){
//	    	plant_hoursclocked_act=0;
//	    }
//	    else{
//	    	String  plant_hoursclocked_array[]=new String[5];
////		    int hoursclocked_plan=Integer.parseInt(plant_hoursclocked_actual);
//		    plant_hoursclocked_array=splitTimeString( plant_hoursclocked_actual);
//		    plant_hoursclocked_act= timeconversion(plant_hoursclocked_array);
//	    }
//	    
//	    String  hme_hoursclocked_array[]=new String[5];
//	    hme_hoursclocked_array=splitTimeString( total_hrs_clocked_hme);
//	    int hme_hoursclocked_act= timeconversion(hme_hoursclocked_array);
////	    int hoursclocked_home=Integer.parseInt(total_hrs_clocked_hme);
//	    
//	    System.out.println(plant_hoursclocked_act + "," +hme_hoursclocked_act);
//	    if(plant_hoursclocked_act==hme_hoursclocked_act)
//	    {
//	       plant_count_list.add("Pass");//hoursclocked 161
//		}
//		else{
//		   plant_count_list.add("Failed");////hoursclocked 161
//		}
//	    //inside plant profile
//	    click(getElement(ElementConstants.PLNT_TILE_NAME_1));
//	    Thread.sleep(TIME_04);
//	    if(isVisible(getElement(ElementConstants.PLNT_MNTH_TOT_HRS))){
//	       plant_count_list.add("Pass");//total hours clocked mnth 162
//	    }
//	    else{
//		   plant_count_list.add("Failed");////total hours clocked mnth 162
//		}
//	    
//	    String count_today=getText(getElement(ElementConstants.PLNT_PROF_TDY_CNT));
//	    int count_tdy_int=Integer.parseInt(count_today);
//	    if(headcount_now_plant==count_tdy_int){
//	       plant_count_list.add("Pass");//today head cnt now 163
//	    }
//	    else{
//	    	plant_count_list.add("Failed");//today head cnt now 163
//	    }
//	    try{
//	    	String plant_prof_notification=getText(getElement(ElementConstants.PLNT_PROF_NOTIF));
//		    int plant_prof_notifications=Integer.parseInt(plant_prof_notification);
//		    
//		    System.out.println(plant_prof_notifications + "," +home_notification);
//		    if(plant_prof_notifications==(home_notification)){
//				 plant_count_list.add("Pass");//notifications 164
//			}
//			else{
//			  plant_count_list.add("Failed");//notifications 164
//			}
//		}catch(NumberFormatException e){
//			System.out.println("Number format exception");	
//			plant_count_list.add("Failed");//notifications 164
//		}
//	    
//	    List<String> floor_table=tableData(getElement(ElementConstants.PLNT_FLOOR_TABLE));
//	    int floor_filter_size=dropsize(getElement(ElementConstants.PLNT_FLOOR_FILTER));
//	    if(floor_table.size()==floor_filter_size){
//	    	plant_count_list.add("Pass");//floors 165
//	    }
//	    else{
//	    	plant_count_list.add("Failed");//floors 165
//	    }
//	    String plnt_contr_cnt=getText(getElement(ElementConstants.PLNT_CONT_CNT));
//	    click(getElement(ElementConstants.CONSIDENAV));
//	    Thread.sleep(4000);
//	    String contractor_cnt=getText(getElement(ElementConstants.CONCNT));
//	    if(plnt_contr_cnt.equals(contractor_cnt)){
//	    	plant_count_list.add("Pass");//contractor 166
//	    }
//	    else{
//	    	plant_count_list.add("Failed");//contractor 166
//	    }
//	    previouspage();
//	    Thread.sleep(TIME_03);
//	    String plt_zones=getText(getElement(ElementConstants.PLNT_ZNES_CNT));
//	    if(zones_hme.equals(plt_zones)){
//	    	plant_count_list.add("Pass");//zones 167
//	    }
//	    else{
//	    	plant_count_list.add("Failed");//zones 167
//	    }
//	    if(isVisible(getElement(ElementConstants.PLNT_MAP))){
//	    	plant_count_list.add("Pass");//map 168
//	    }
//	    else{
//	    	plant_count_list.add("Failed");//map 168
//	    }
//	    plant_count_list.add("Not Feasible");//floor level 169
//	    plant_count_list.add("Not Feasible");//Loaders 170
	    return plant_count_list;
		
	}
}

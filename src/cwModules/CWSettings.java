package cwModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClass.AppDriver;
import constants.ElementConstants;

public class CWSettings extends AppDriver{
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
	
	public CWSettings()throws IOException, InterruptedException {
		super(propFileName);
	}
	public List<String> settings_sidenav() throws InterruptedException{
		List<String> settings_count_list= new ArrayList<String>();
		click(getElement(ElementConstants.SETT_SIDENAV));
		Thread.sleep(TIME_04);
		Select plt_drp_options_01=new Select(path(getElement(ElementConstants.PLTDROP)));
//		List<WebElement>plt_options=plt_drp_options_01.getOptions();
		plt_drp_options_01.selectByIndex(2);
		Thread.sleep(TIME_07);
		String tab_name_01=getText(getElement(ElementConstants.SETT_PLT_TAB));
		String tab_name_arr[]=tab_name_01.split(("-"));
		int tab_name_count_01=Integer.parseInt(tab_name_arr[1].trim());
	    System.out.println("count"+tab_name_count_01);
		List<WebElement> table_rows=getelements(getElement(ElementConstants.SETT_TAB_ROWS));
		if(table_rows.size()==tab_name_count_01){
			settings_count_list.add("Pass");//460 list
			settings_count_list.add("Pass");//461 table
		}
		else{
		    settings_count_list.add("Failed");
		    settings_count_list.add("Failed");
		}
		//462-478
		List<WebElement> table_hdrs=getelements(getElement(ElementConstants.SETT_TAB_HEAD));
	    if(table_hdrs.get(0).getText().equalsIgnoreCase("plant")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(1).getText().equalsIgnoreCase("alert id")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(2).getText().equalsIgnoreCase("Alert Desc.")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(3).getText().equalsIgnoreCase("Severity")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(4).getText().equalsIgnoreCase("Category")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(5).getText().equalsIgnoreCase("Category Nature")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(6).getText().equalsIgnoreCase("SMS")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(7).getText().equalsIgnoreCase("Email")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(8).getText().equalsIgnoreCase("c2d")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(9).getText().equalsIgnoreCase("mtype")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(10).getText().equalsIgnoreCase("message")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(11).getText().equalsIgnoreCase("Web Not.")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(12).getText().equalsIgnoreCase("Auto Close")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(13).getText().equalsIgnoreCase("Trigger Delay")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(14).getText().equalsIgnoreCase("Duplicate Dur.")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(15).getText().equalsIgnoreCase("UOM")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_hdrs.get(16).getText().equalsIgnoreCase("Action")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(isVisible(getElement(ElementConstants.SETT_PLT_ADD))){
	    	settings_count_list.add("Pass");//add button 479
	    	clickOverlap(getElement(ElementConstants.SETT_PLT_ADD));
	    	if(isVisible(getElement(ElementConstants.SETT_PLT_SELPLT))){
	    		settings_count_list.add("Pass");//plant 480
	    	}
	    	else{
	    		settings_count_list.add("Failed");
	    	}
	    	if(isVisible(getElement(ElementConstants.SETT_PLT_SELALRTID))){
	    		settings_count_list.add("Pass");//alert id 481
	    	}
	    	else{
	    		settings_count_list.add("Failed");
	    	}
	    	clickOverlap(getElement(ElementConstants.SETT_PLT_ADD_CLS));
		}
		else{
		    settings_count_list.add("Failed");
		    settings_count_list.add("Failed");
		    settings_count_list.add("Failed");
		}
	    click(getElement(ElementConstants.SETT_DEF_TAB));
	    explicitWaitByxpath(getElement(ElementConstants.SETT_DEF_TABlE));
	    String tab_name_def=getText(getElement(ElementConstants.SETT_DEF_TAB));
		String tab_name_def_arr[]=tab_name_def.split(("-"));
		System.out.println(tab_name_def_arr[0]);
		int tab_name_def_count=Integer.parseInt(tab_name_def_arr[1].trim());
	    System.out.println("count1"+tab_name_def_count);
	    List<WebElement> table_rows_def=getelements(getElement(ElementConstants.SETT_DEF_TABlE_ROWS));
	    System.out.println("count2"+table_rows_def.size());
	    if(table_rows_def.size()==tab_name_def_count){
	    	settings_count_list.add("Pass");//482 default
    	}
    	else{
    		settings_count_list.add("Failed");
    	}
	    if(isVisible(getElement(ElementConstants.SETT_PLT_ADD))){
	    	settings_count_list.add("Pass");//add button 483
	    }
	    else{
	    	settings_count_list.add("Failed");
	    }
	    click(getElement(ElementConstants.SETT_CONF_TAB));
	    explicitWaitByxpath(getElement(ElementConstants.SETT_CONF_PLT_TABLE));
	    String tab_name_conf_plt=getText(getElement(ElementConstants.SETT_CONF_PLT_TAB));
		String tab_name_conf_plt_arr[]=tab_name_conf_plt.split(("-"));
		
		int tab_name_conf_plt_cnt=Integer.parseInt(tab_name_conf_plt_arr[1].trim());
	    System.out.println("count1"+tab_name_conf_plt_cnt);
	    List<WebElement> table_rows_sett_plt=getelements(getElement(ElementConstants.SETT_CONF_PLT_TABLE));
	    System.out.println("count2"+table_rows_sett_plt.size());
	    if(table_rows_sett_plt.size()==tab_name_conf_plt_cnt){
	    	settings_count_list.add("Pass");//484 list count
	    	settings_count_list.add("Pass");//485 table
    	}
    	else{
    		settings_count_list.add("Failed");
    		settings_count_list.add("Failed");
    	}
	    //486-492
	    List<WebElement> table_config_hdrs=getelements(getElement(ElementConstants.SETT_CONF_PLT_TABLE_HDRS));
	    if(table_config_hdrs.get(0).getText().equalsIgnoreCase("plant")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_config_hdrs.get(1).getText().equalsIgnoreCase("app")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_config_hdrs.get(2).getText().equalsIgnoreCase("Attribute")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_config_hdrs.get(3).getText().equalsIgnoreCase("Description")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_config_hdrs.get(4).getText().equalsIgnoreCase("Value")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_config_hdrs.get(5).getText().equalsIgnoreCase("UOM")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(table_config_hdrs.get(6).getText().equalsIgnoreCase("Action")){
	    	settings_count_list.add("Pass");
		}
		else{
		    settings_count_list.add("Failed");
		}
	    if(isVisible(getElement(ElementConstants.SETT_CONF_ADD_BTN))){
	    	settings_count_list.add("Pass");//add button 493
	    	clickOverlap(getElement(ElementConstants.SETT_CONF_ADD_BTN));
	    	if(isVisible(getElement(ElementConstants.SETT_CONF_ADD_PLT_SEL))){
	    		settings_count_list.add("Pass");//plant 494
	    	}
	    	else{
	    		settings_count_list.add("Failed");
	    	}
	    	if(isVisible(getElement(ElementConstants.SETT_CONF_ADD_APP_SEL))){
	    		settings_count_list.add("Pass");//APPLN 495
	    	}
	    	else{
	    		settings_count_list.add("Failed");
	    	}
	    	if(isVisible(getElement(ElementConstants.SETT_CONF_ADD_ATTR_SEL))){
	    		settings_count_list.add("Pass");//ATTR 496
	    	}
	    	else{
	    		settings_count_list.add("Failed");
	    	}
	    	clickOverlap(getElement(ElementConstants.SETT_CONF_ADD_CLS));
		}
		else{
		    settings_count_list.add("Failed");
		    settings_count_list.add("Failed");
		    settings_count_list.add("Failed");
		}
	    click(getElement(ElementConstants.SETT_CONF_DEF_TAB));
	    explicitWaitByxpath(getElement(ElementConstants.SETT_CONF_DEF_TABLE));
	    String config_tab_def=getText(getElement(ElementConstants.SETT_CONF_DEF_TAB));
		String config_tab_def_arr[]=config_tab_def.split(("-"));
		int config_tab_def_count=Integer.parseInt(config_tab_def_arr[1].trim());
	    System.out.println("count1"+config_tab_def_count);
	    List<WebElement> config_table_def=getelements(getElement(ElementConstants.SETT_CONF_DEF_TABLE));
	    System.out.println("count2"+config_table_def.size());
	    if(config_table_def.size()==config_tab_def_count){
	    	settings_count_list.add("Pass");//497 default
    	}
    	else{
    		settings_count_list.add("Failed");
    	}
	    settings_count_list.add("No Run");//498 add new config
	    click(getElement(ElementConstants.SETT_SYNC_TAB));
	    explicitWaitByxpath(getElement(ElementConstants.SETT_SYNC_TABL));
	    if(isVisible(getElement(ElementConstants.SETT_SYNC_ALL_DRP))){
	    	settings_count_list.add("Pass");//499 all drp
	    	Select all_sel=new Select(path(getElement(ElementConstants.SETT_SYNC_ALL_DRP)));
			List<WebElement>all_options=all_sel.getOptions();
			System.out.println("option"+all_options.get(0).getText());
			if(all_options.get(1).getText().equals("alertrules")){
				
				settings_count_list.add("Pass");//500 alert rules	
			}
			else{
	    		settings_count_list.add("Failed");
	    	}
			
			if(all_options.get(2).getText().equals("mappingConfig")){
				System.out.println("option"+all_options.get(1).getText());
				settings_count_list.add("Pass");//501 mapping config	
			}
			else{
	    		settings_count_list.add("Failed");
	    	}
			if(all_options.get(3).getText().equals("zones")){
				System.out.println("option"+all_options.get(2).getText());
				settings_count_list.add("Pass");//502 zones
			}
			else{
	    		settings_count_list.add("Failed");
	    	}
			
    	}
    	else{
    		settings_count_list.add("Failed");
    		settings_count_list.add("Failed");
    		settings_count_list.add("Failed");
    		settings_count_list.add("Failed");
    	}
	    if(isVisible(getElement(ElementConstants.SETT_SYNC_BTN))){
	    	settings_count_list.add("Pass");//503 sync now button
	    }
	    else{
	    	settings_count_list.add("Failed");
	    }
	    if(isVisible(getElement(ElementConstants.SETT_SYNC_TABL))){
	    	settings_count_list.add("Pass");//504 sync status
	    }
	    else{
	    	settings_count_list.add("Failed");
	    }
		return settings_count_list;
	}
	
	
	

}


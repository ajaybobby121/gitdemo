package cwModules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_And_Del {

	public static void main(String[] args){

		WebDriver driver = new ChromeDriver();	
		driver.get("https://formy-project.herokuapp.com/modal");
		
		System.out.println("chk1");
		WebElement openModal=driver.findElement(By.id("modal-button"));
		System.out.println("chk2");
		openModal.click();
		System.out.println("chk3");
		WebElement closeButton=driver.findElement(By.id("close-button"));
		
		System.out.println("chk4");
		//Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js=(JavascriptExecutor)driver;
		System.out.println("chk5");
		js.executeScript("arguments[0].click();",closeButton);   
		System.out.println("chk6");

	}
}
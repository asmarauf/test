package disney;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class viewGallery {

	public static void main(String[] args) 
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","C:\\Users\\asmar\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://disneyworld.disney.go.com/destinations/magic-kingdom/");
		
		//find Gallery
		driver.findElement(By.id("openMediaEngineButton")).click();

		Screen s= new Screen();
		Pattern p = new Pattern("video.png");
		Pattern p1 = new Pattern("next.png");
		Debug.setDebugLevel(3);
		p.similar((float) 0.4);
		p1.similar((float) 0.4);
	
		
		Rectangle r = new Rectangle(2700,950,118,92);
		try {
			List<WebElement> list = driver.findElements(By.xpath(".//*[@class= 'thumbnailSliderHolder' ]//child::li"));
			for(WebElement i:list)
			{
				Region.create(r).highlight(3).find(p1).click();
				//driver.findElement(By.xpath(".//*[@class='nextButton fullscreenFadable']//a")).click();
			}
			System.out.println('\t' +"The centre of screen is :" +s.getCenter());
	// how to use iterator. it doesn't work here				
		/*	Iterator<Match> all= s.findAll(p1);
			  while(all.hasNext())
		      {
		    	 s.click(all.next());
		      }*/
		} catch (FindFailed exception) {
			exception.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement e = driver.findElement(By.xpath(".//*[@href='#VideoBookTab']"));
		WebElement e1= wait.until(ExpectedConditions.visibilityOf(e));
		e1.click();
		WebDriverWait wait1 = new WebDriverWait(driver,30);
		e= driver.findElement(By.xpath(".//*[@href='#videoPlayPause']"));
		e1= wait1.until(ExpectedConditions.visibilityOf(e));
		e1.click();
	//	WebDriverWait wait2 = new WebDriverWait(driver,60);
	//	wait2.until(ExpectedConditions.attributeContains(By.xpath(".//*[@class='thumbTouch fullscreenFadable']"),"value", "76.65625092740814"));
		driver.close();
	}

}

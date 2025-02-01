import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KahootBot
{
	// TO DO: Delete this comment
	static String KahootName = "";		// <-- TYPE SEARCH QUERY HERE
	static String gameID = "";			// <-- TYPE GAME ID HERE
	
	// DO NOT UPLOAD EMAIL/PASSWORD ON A PUBLIC REPO!!
	static String email = "[REDACTED]";
	static String pass = "[REDACTED]";
	
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcont\\Desktop\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofMinutes(10));
		
		String url = "https://create.kahoot.it/discover";
		driver.get(url);
		
		wait.until(ExpectedConditions.elementToBeClickable((By.id("username-input-field__input"))));
		WebElement userName = driver.findElement(By.id("username-input-field__input"));
		userName.sendKeys(email);
		WebElement passWord = driver.findElement(By.id("password-input-field__input"));
		passWord.sendKeys(pass);
		WebElement logInButton = driver.findElement(By.className("button--cta-play"));
		logInButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable((By.className("search-field__input"))));
		WebElement search = driver.findElement(By.className("search-field__input"));
		search.sendKeys(KahootName);
		WebElement searchButton = driver.findElement(By.className("kahoots-search-field__button"));
		searchButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable((By.className("kahoot-card__title-link"))));
		WebElement kahoot = driver.findElement(By.className("kahoot-card__title-link"));
		kahoot.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.className("question-list__group-toggle")));
		WebElement showAnswers = driver.findElement(By.className("question-list__group-toggle"));
		showAnswers.click();
		
		List<WebElement> questions = driver.findElements(By.className("question-list__item"));
		ArrayList<Integer> answers = new ArrayList<>();

		for (WebElement question : questions)
		{
			List<WebElement> choices = question.findElements(By.className("choices__choice"));
			
			for (int i = 0; i < choices.size(); i++)
			{
				WebElement choice = choices.get(i);
				
				try
				{
					choice.findElement(By.className("choices__choice--correct"));
					answers.add(i);
					break;
				}
				catch (Exception e) {}
			}
		}
		
		System.out.println(answers);
		
	    ((JavascriptExecutor)driver).executeScript("window.open()");
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		
		driver.get("http://www.kahoot.it");

		WebElement enterID = driver.findElement(By.name("gameId"));
		enterID.sendKeys(gameID);
		
		WebElement button = driver.findElement(By.className("enter-button__EnterButton-sc-1o9b9va-0"));
		button.click();
		
		wait.until(ExpectedConditions.elementToBeClickable((By.name("nickname"))));
		WebElement enterNick = driver.findElement(By.name("nickname"));
		enterNick.sendKeys("mrnoops");
		WebElement button2 = driver.findElement(By.className("enter-button__EnterButton-sc-1o9b9va-0"));
		button2.click();
		
		for (int i = 0; i < answers.size(); i++)
		{
			wait.until(ExpectedConditions.elementToBeClickable((By.className("card-button__CardButton-vbewcy-1"))));
			
			WebElement answer0 = driver.findElement(By.className("eRSCLD"));
			WebElement answer1 = driver.findElement(By.className("fabXZJ"));
			WebElement answer2 = null , answer3 = null;
			
			try { answer2 = driver.findElement(By.className("eYFENK")); }
			catch (Exception e) {}
			
			try { answer3 = driver.findElement(By.className("bDfINc")); }
			catch (Exception e) {}
			
			WebElement[] choose = {answer0 , answer1 , answer2 , answer3};
			choose[answers.get(i)].click();
		}
		
		
	}

}

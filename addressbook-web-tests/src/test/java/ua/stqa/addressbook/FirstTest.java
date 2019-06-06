package ua.stqa.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeTest
  public void start(){
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver,10);
    driver.manage().window().maximize();
  }

  @Test
  public void myFirstTest(){
    driver.get("https://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logo")));
  }

  @AfterTest
  public void stop(){
    driver.quit();
    driver = null;
  }
}

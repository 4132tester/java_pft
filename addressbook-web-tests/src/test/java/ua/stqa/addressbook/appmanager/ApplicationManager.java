package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final GroupHelper groupHelper = new GroupHelper();

  public void init() {
    groupHelper.driver = new ChromeDriver();
    groupHelper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    groupHelper.driver.manage().window().maximize();
    login("admin", "secret");
  }

  public void login(String username, String password) {
    groupHelper.driver.get("http://localhost/addressbook/");
    groupHelper.driver.findElement(By.name("user")).click();
    groupHelper.driver.findElement(By.name("user")).clear();
    groupHelper.driver.findElement(By.name("user")).sendKeys(username);
    groupHelper.driver.findElement(By.name("pass")).click();
    groupHelper.driver.findElement(By.name("pass")).clear();
    groupHelper.driver.findElement(By.name("pass")).sendKeys(password);
    groupHelper.driver.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupsPage() {
    groupHelper.driver.findElement(By.linkText("GROUPS")).click();
  }

  public void stop() {
    groupHelper.driver.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}

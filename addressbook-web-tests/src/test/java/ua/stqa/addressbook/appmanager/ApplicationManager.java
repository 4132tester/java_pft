package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver driver;

  private Properties properties;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));

    if (browser.equals("chrome")){
      driver = new ChromeDriver();
    } else if (browser.equals("firefox")){
      driver = new FirefoxDriver();
    } else if(browser.equals("internet explorer")){
      driver = new InternetExplorerDriver();
    }
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(driver);
    contactHelper = new ContactHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login(properties.getProperty("web.AdminLogin"), properties.getProperty("web.AdminPassword"));
  }

  public void stop() {
    driver.quit();
  }

  public GroupHelper groups() {
    return groupHelper;
  }

  public ContactHelper contacts() {return contactHelper; }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
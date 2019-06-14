package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  public WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void type(By locator, String text) {
    click(locator);
    if (text != null) {
      driver.findElement(locator).clear();
      driver.findElement(locator).sendKeys(text);
    }
  }

  public boolean isElementPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }
}

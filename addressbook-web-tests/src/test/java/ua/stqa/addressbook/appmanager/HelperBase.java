package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

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
      String existingText = driver.findElement(locator).getAttribute("value");
      //проверка необходимости заполнения поля теми же данными (если оно не пустое)
      if (! existingText.equals(text)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
      }
    }
  }

  public void attach(By locator, File file) {
    if (file != null) {
      driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  public boolean isElementPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }
}

package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void gotoGroupsPage() {
    if (isElementPresent(By.tagName("h1"))
            && driver.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("GROUPS"));
  }

  public void initContactCreation() {
    click(By.linkText("ADD_NEW"));
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable")))
    return;
    click(By.linkText("HOME"));
  }
}

package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.stqa.addressbook.GroupData;
import ua.stqa.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  private Groups groupCache = null;

  public void create (GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    gotoGroupsPage();
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }


  public void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAGroup() {
    return count() > 0;
  }

  public Groups setGroups() {
    if (groupCache != null) {
      return new Groups(groupCache);
    } else {
      groupCache = new Groups();
      if (isThereAGroup()) {
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements) {
          String name = element.getText();
          int identifier = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
          GroupData group = new GroupData().withIdentifier(identifier).withName(name);
          groupCache.add(group);
        }
      }
      return new Groups(groupCache);
    }
  }

  public void modifyGroup(GroupData group) {
    selectGroupById(group.getIdentifier());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    gotoGroupsPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getIdentifier());
    deleteSelectedGroups();
    groupCache = null;
    gotoGroupsPage();
  }

  public void gotoGroupsPage() {
    if (isElementPresent(By.tagName("h1"))
            && driver.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("GROUPS"));
  }

}
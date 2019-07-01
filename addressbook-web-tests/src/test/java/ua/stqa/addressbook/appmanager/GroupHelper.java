package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.stqa.addressbook.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
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

  public void selectGroup(int index) {
    driver.findElements(By.cssSelector("span.group > [type = 'checkbox']")).get(index).click();
  }

  public void initGropModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public int getGroupCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAGroup() {
    return getGroupCount() > 0;
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    if (isThereAGroup()) {
      List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
      for (WebElement element: elements) {
        String name = element.getText();
        String identifier = element.findElement(By.tagName("input")).getAttribute("value");
        GroupData group = new GroupData(identifier, name, null, null);
        groups.add(group);
      }
    }
    return groups;
  }
}

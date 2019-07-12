package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.stqa.addressbook.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


  public void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public Set<GroupData> setGroups() {
    Set<GroupData> groups = new HashSet<GroupData>();
    if (isThereAGroup()) {
      List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
      for (WebElement element: elements) {
        String name = element.getText();
        //System.out.println(name);
        int identifier = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        GroupData group = new GroupData().withIdentifier(identifier).withName(name);
        groups.add(group);
      }
    }
    return groups;
  }

  public void modifyGroup(GroupData group) {
    selectGroupById(group.getIdentifier());
    initGropModification();
    fillGroupForm(group);
    submitGroupModification();
    gotoGroupsPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getIdentifier());
    deleteSelectedGroups();
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

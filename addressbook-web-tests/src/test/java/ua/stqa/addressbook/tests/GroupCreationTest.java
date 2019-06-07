package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupsPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test_1", "test_2", "test_3"));
    app.getGroupHelper().submitGroupCreation();
    app.gotoGroupsPage();
  }

}
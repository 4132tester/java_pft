package ua.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupsPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test_1", "test_2", "test_3"));
    app.submitGroupCreation();
    app.gotoGroupsPage();
  }

}
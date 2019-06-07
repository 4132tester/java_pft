package ua.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupsPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test_1", "test_2", "test_3"));
    submitGroupCreation();
    gotoGroupsPage();
  }

}
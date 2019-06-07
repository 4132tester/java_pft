package ua.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    gotoGroupsPage();
    selectGroup();
    deleteSelectedGroups();
    gotoGroupsPage();
  }

}

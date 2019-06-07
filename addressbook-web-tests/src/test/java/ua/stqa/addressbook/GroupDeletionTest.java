package ua.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.gotoGroupsPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.gotoGroupsPage();
  }

}

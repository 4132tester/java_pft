package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupsPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupsPage();
  }

}

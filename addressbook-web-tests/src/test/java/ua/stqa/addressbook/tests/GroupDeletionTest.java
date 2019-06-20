package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupsPage();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("test_1", "test header", "test footer"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().gotoGroupsPage();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

}

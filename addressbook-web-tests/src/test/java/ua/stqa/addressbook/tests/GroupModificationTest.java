package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupsPage();
    if (app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("test_1", "test_1 header", "test_1 footer"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().gotoGroupsPage();
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGropModification();
    app.getGroupHelper().fillGroupForm(new GroupData("modified-name", "modified-header", "modified-footer"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupsPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }

}

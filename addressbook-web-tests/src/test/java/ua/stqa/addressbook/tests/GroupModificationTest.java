package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupsPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(2);
    app.getGroupHelper().initGropModification();
    app.getGroupHelper().fillGroupForm(new GroupData("modified-name", "modified-header", "modified-footer"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupsPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }

}

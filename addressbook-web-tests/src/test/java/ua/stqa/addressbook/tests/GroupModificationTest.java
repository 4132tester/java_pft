package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupsPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGropModification();
    app.getGroupHelper().fillGroupForm(new GroupData("modified-name", "modified-header", "modified-footer"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupsPage();
  }

}

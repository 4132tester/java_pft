package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupsPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("test_1 name", "test_1 header", "test_1 footer"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().gotoGroupsPage();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int groupIndex = before.size() - 1;
    app.getGroupHelper().selectGroup(groupIndex);
    app.getGroupHelper().initGropModification();
    GroupData modifiedGroup =  new GroupData(before.get(before.size() - 1).getIdentifier(), "modified-name", "modified-header", "modified-footer");
    app.getGroupHelper().fillGroupForm(modifiedGroup);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(groupIndex);
    before.add(modifiedGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}

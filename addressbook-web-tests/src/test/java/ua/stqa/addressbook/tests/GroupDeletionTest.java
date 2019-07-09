package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupsPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("test_1 name", "test_1 header", "test_1 footer"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().gotoGroupsPage();
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int groupIndex = before.size() - 1;
    app.getGroupHelper().selectGroup(groupIndex);
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    //сравнение списков групп до удаления и после
    before.remove(groupIndex);
    Assert.assertEquals(before, after);
  }

}

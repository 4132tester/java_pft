package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.Comparator;
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
    int groupIndex = 1;
    app.getGroupHelper().selectGroup(groupIndex);
    app.getGroupHelper().initGropModification();
    GroupData modifiedGroup =  new GroupData(before.get(groupIndex).getIdentifier(), "modified-3", "modified-header", "modified-footer");
    app.getGroupHelper().fillGroupForm(modifiedGroup);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(groupIndex);
    before.add(modifiedGroup);

    Comparator<? super GroupData> byId = (g1, g2) ->
            Integer.compare(Integer.parseInt(g1.getIdentifier()), Integer.parseInt(g2.getIdentifier()));
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

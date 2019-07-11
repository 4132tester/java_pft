package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupsPage();
    if (app.groups().list().size() == 0) {
      app.groups().initGroupCreation();
      app.groups().fillGroupForm(new GroupData()
              .withName("test_1 name").withHeader("test_1 header").withFooter("test_1 footer"));
      app.groups().submitGroupCreation();
      app.goTo().groupsPage();
    }
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.groups().list();
    int groupIndex = before.size() - 1;
    GroupData modifiedGroup =  new GroupData()
            .withIdentifier(before.get(groupIndex).getIdentifier()).withName("modified-4")
            .withHeader("modified-header").withFooter("modified-footer");

    app.groups().modifyGroup(groupIndex, modifiedGroup);

    List<GroupData> after = app.groups().list();
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

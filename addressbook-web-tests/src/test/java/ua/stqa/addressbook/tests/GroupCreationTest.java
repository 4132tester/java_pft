package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupsPage();
    Set<GroupData> before = app.groups().setGroups();
    app.groups().initGroupCreation();
    GroupData group = new GroupData().withName("test_6");
    app.groups().fillGroupForm(group);
    app.groups().submitGroupCreation();
    app.goTo().groupsPage();
    Set<GroupData> after = app.groups().setGroups();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withIdentifier(after.stream().mapToInt((g) -> g.getIdentifier()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
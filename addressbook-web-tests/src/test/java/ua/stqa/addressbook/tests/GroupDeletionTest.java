package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.Set;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupsPage();
    if (app.groups().setGroups().size() == 0) {
      app.groups().initGroupCreation();
      app.groups().fillGroupForm(new GroupData().
              withName("test_1 name").withHeader("test_1 header").withFooter("test_1 footer"));
      app.groups().submitGroupCreation();
      app.goTo().groupsPage();
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.groups().setGroups();
    GroupData deletedGroup = before.iterator().next();
    app.groups().delete(deletedGroup);
    Set<GroupData> after = app.groups().setGroups();
    Assert.assertEquals(after.size(), before.size() - 1);

    //сравнение списков групп до удаления и после
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }

}

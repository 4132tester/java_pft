package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupsPage();
    if (app.groups().list().size() == 0) {
      app.groups().initGroupCreation();
      app.groups().fillGroupForm(new GroupData().
              withName("test_1 name").withHeader("test_1 header").withFooter("test_1 footer"));
      app.groups().submitGroupCreation();
      app.goTo().groupsPage();
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.groups().list();
    int groupIndex = before.size() - 1;
    app.groups().selectGroup(groupIndex);
    app.groups().deleteSelectedGroups();
    app.goTo().groupsPage();
    List<GroupData> after = app.groups().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    //сравнение списков групп до удаления и после
    before.remove(groupIndex);
    Assert.assertEquals(before, after);
  }

}

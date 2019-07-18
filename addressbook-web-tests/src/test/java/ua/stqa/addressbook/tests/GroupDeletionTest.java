package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;
import ua.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.groups().setGroups();
    GroupData deletedGroup = before.iterator().next();
    app.groups().delete(deletedGroup);
    assertEquals(app.groups().count(), before.size() - 1);
    Groups after = app.groups().setGroups();
    //сравнение списков групп до удаления и после
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}

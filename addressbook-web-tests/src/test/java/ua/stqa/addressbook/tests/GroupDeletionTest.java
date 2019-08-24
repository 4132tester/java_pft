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
      GroupData group = new GroupData().withName("test_0").withHeader("test-header_0").withFooter("test-footer_0");
      app.groups().create(group);
    }
  }

  @Test
  public void testGroupDeletion() {
    app.goTo().groupsPage();
    Groups before = app.groups().setGroups();
    GroupData deletedGroup = before.iterator().next();
    app.groups().delete(deletedGroup);
    assertEquals(app.groups().count(), before.size() - 1);
    Groups after = app.groups().setGroups();
    //сравнение списков групп до удаления и после
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}

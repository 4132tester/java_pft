package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;
import ua.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.groups().setGroups();
    GroupData group = new GroupData().withName("test_6");
    app.groups().create(group);
    Groups after = app.groups().setGroups();
    assertThat(app.groups().count(), equalTo(before.size() + 1));

    group.withIdentifier(after.stream().mapToInt((g) -> g.getIdentifier()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(group)));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.groups().setGroups();
    GroupData group = new GroupData().withName("test_6");
    app.groups().create(group);

    assertThat(app.groups().count(), equalTo(before.size()));
    Groups after = app.groups().setGroups();
    assertThat(after, equalTo(before));
  }
}
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
    app.groups().initGroupCreation();
    GroupData group = new GroupData().withName("test_6");
    app.groups().fillGroupForm(group);
    app.groups().submitGroupCreation();
    app.goTo().groupsPage();
    Groups after = app.groups().setGroups();
    assertThat(after.size(), equalTo(before.size() + 1));

    group.withIdentifier(after.stream().mapToInt((g) -> g.getIdentifier()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(group)));
  }

}
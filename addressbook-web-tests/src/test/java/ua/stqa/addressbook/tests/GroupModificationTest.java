package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;
import ua.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupsPage();
    if (app.groups().setGroups().size() == 0) {
      app.groups().initGroupCreation();
      app.groups().fillGroupForm(new GroupData()
              .withName("test_1 name").withHeader("test_1 header").withFooter("test_1 footer"));
      app.groups().submitGroupCreation();
      app.goTo().groupsPage();
    }
  }

  @Test
  public void testGroupModification() {

    Groups before = app.groups().setGroups();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group =  new GroupData()
            .withIdentifier(modifiedGroup.getIdentifier()).withName("modified-5")
            .withHeader("modified-header").withFooter("modified-footer");

    app.groups().modifyGroup(group);

    Assert.assertEquals(app.groups().count(), before.size());
    Groups after = app.groups().setGroups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

}

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
      GroupData group = new GroupData().withName("test_0").withHeader("test-header_0").withFooter("test-footer_0");
      app.groups().create(group);
    }
  }

  @Test
  public void testGroupModification() {

    Groups before = app.groups().setGroups();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group =  new GroupData()
            .withIdentifier(modifiedGroup.getIdentifier()).withName("modified-1")
            .withHeader("modified-header").withFooter("modified-footer");

    app.groups().modifyGroup(group);

    Assert.assertEquals(app.groups().count(), before.size());
    Groups after = app.groups().setGroups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

}

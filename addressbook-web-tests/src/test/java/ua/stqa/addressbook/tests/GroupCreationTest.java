package ua.stqa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;
import ua.stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupsPage();
    Groups before = app.groups().setGroups();
    app.groups().create(group);
    assertThat(app.groups().count(), equalTo(before.size() + 1));

    Groups after = app.groups().setGroups();
    group.withIdentifier(after.stream().mapToInt((g) -> g.getIdentifier()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(group)));
  }

  @Test(enabled = false)
  public void testBadGroupCreation() {
    app.goTo().groupsPage();
    Groups before = app.groups().setGroups();
    GroupData group = new GroupData().withName("test'_1");
    app.groups().create(group);

    assertThat(app.groups().count(), equalTo(before.size()));
    Groups after = app.groups().setGroups();
    assertThat(after, equalTo(before));
  }
}
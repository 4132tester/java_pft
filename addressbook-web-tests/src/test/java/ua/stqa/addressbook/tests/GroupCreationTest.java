package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupsPage();
    List<GroupData> before = app.groups().list();
    app.groups().initGroupCreation();
    GroupData group = new GroupData("test_5", "test_5", "test_5");
    app.groups().fillGroupForm(group);
    app.groups().submitGroupCreation();
    app.goTo().groupsPage();
    List<GroupData> after = app.groups().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    //сравнение коллекций групп
    int maxIdentifier = 0;
    for (GroupData element : after) {
      int i = Integer.parseInt(element.getIdentifier());
      if (i > maxIdentifier) {
        maxIdentifier = i;
      }
    }
    group.setIdentifier(Integer.toString(maxIdentifier));
    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) ->
            Integer.compare(Integer.parseInt(g1.getIdentifier()), Integer.parseInt(g2.getIdentifier()));
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}
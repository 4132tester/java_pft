package ua.stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.stqa.addressbook.GroupData;
import ua.stqa.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = br.readLine();
    while (line != null) {
      String[] word = line.split(";");
      list.add(new Object[] {new GroupData().withName(word[0]).withName(word[1]).withFooter(word[2])});
      line = br.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = br.readLine();
    while (line != null) {
      xml += line;
      line = br.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
    // це пиздець!
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromXml")
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
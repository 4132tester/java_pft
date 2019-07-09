package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov",
              "050134578", "ivan_ivanov@te.st", "Lviv, vul. Naukova 3, kv. 56"));
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int contactIndex = before.size() - 1;
    app.getGroupHelper().selectGroup(contactIndex);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    System.out.println("before: " + before);
    System.out.println("after: " + after);
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(contactIndex);
    Assert.assertEquals(before, after);
  }
}

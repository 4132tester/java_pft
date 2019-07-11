package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    if (app.contacts().list().size() == 0) {
      app.contacts().initContactCreation();
      app.contacts().fillContactForm(new ContactData("Ivan", "Ivanov",
              "050134578", "ivan_ivanov@te.st", "Lviv, vul. Naukova 3, kv. 56"));
      app.contacts().submitContactCreation();
      app.goTo().homePage();
    }
    List<ContactData> before = app.contacts().list();
    int contactIndex = before.size() - 1;
    app.contacts().selectContact(contactIndex);
    app.contacts().deleteSelectedContact();
    app.goTo().homePage();
    List<ContactData> after = app.contacts().list();

    System.out.println("before: " + before);
    System.out.println("after: " + after);
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(contactIndex);
    Assert.assertEquals(before, after);
  }
}

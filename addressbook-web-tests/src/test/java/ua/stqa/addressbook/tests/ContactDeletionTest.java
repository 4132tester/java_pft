package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov",
              "050134578", "ivan_ivanov@te.st", "Lviv, vul. Naukova 3, kv. 56"));
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().gotoHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    System.out.println("gotoHomePage success");
    app.getContactHelper().selectContact();
    System.out.println("selectContact success");
    app.getContactHelper().deleteSelectedContact();
    System.out.println("deleteSelectedContact success");
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    Assert.assertEquals(after, before - 1);

  }
}

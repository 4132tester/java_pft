package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().initContactCreation();
    ContactData newContact = new ContactData("Alina", "Krivenko",
            "0555324768", "akrivenko@ergfd.com", "Franka Ul., bld. 24/110");
    app.getContactHelper().fillContactForm(newContact);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(newContact);
    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getContactId(), contact2.getContactId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}

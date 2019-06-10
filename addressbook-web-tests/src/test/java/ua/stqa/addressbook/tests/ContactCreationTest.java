package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov",
            "050134578", "ivan_ivanov@te.st", "Lviv, vul. Naukova 3, kv. 56"));
    app.getContactHelper().submitContactCreation();
  }
}

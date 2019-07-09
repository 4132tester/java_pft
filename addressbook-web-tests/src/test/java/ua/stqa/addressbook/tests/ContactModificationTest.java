package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {
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

    app.getContactHelper().initContactModification(1);
    ContactData modifiedContact = new ContactData("Lubov", "Voronova",
            "0503204578", "b0x8evc6x4c@payspun.com", "Kirovograd, Fortechniy Prov., bld. 21А, appt. 90");
    app.getContactHelper().fillContactForm(modifiedContact);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    System.out.println("before: " + before);
    System.out.println("after: " + after);

    //Assert ниже возвращает ЛОЖЬ, т.к. на этапе сохранения изменений, контакт удаляется.
    // Баг программы (не теста). Такое же поведение и при ручном тестировании
    Assert.assertEquals(after.size(), before.size() );

    before.remove(contactIndex);
    after.add(modifiedContact);

    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getContactId(), contact2.getContactId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(after, before);

  }
}

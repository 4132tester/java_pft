package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (before < 1) {
      app.getNavigationHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov",
              "050134578", "ivan_ivanov@te.st", "Lviv, vul. Naukova 3, kv. 56"));
      app.getContactHelper().submitContactCreation();
      app.getNavigationHelper().gotoHomePage();
      before++;
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Lubov", "Voronova",
            "0503204578", "b0x8evc6x4c@payspun.com", "Kirovograd, Fortechniy Prov., bld. 21А, appt. 90"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    //Assert ниже возвращает ЛОЖЬ, т.к. на этапе сохранения изменений, контакт удаляется.
    // Баг программы (не теста). Такое же поведение и при ручном тестировании
    Assert.assertEquals(after, before);
  }
}

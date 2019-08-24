package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;
import ua.stqa.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contacts().setContacts().size() == 0) {
      ContactData newContact = new ContactData().withFirstName("Ivan").withLastName("Ivanov").withHomePhone("0501112233")
              .withMobilePhone("0501112234").withWorkPhone("0501112235").withEmail("1st_ivan_ivanov@te.st")
              .withEmail2("2nd_ivan_ivanov@te.st").withEmail3("3rd_ivan_ivanov@te.st")
              .withAddress1("Lviv, vul. Naukova 3, kv. 56");
      app.contacts().create(newContact);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.contacts().setContacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withContactId(modifiedContact.getContactId()).withFirstName("Akakiy")
            .withLastName("Maksyymenko").withEmail("Akakiy_Maksyymenko@te.st")
            .withEmail2("pyj1j5ghka@fakemailgenerator.net").withEmail3("5sqshcudrff@iffymedia.com")
            .withAddress1("Kirovograd, Fortechniy Prov., bld. 21А, appt. 90");

    app.contacts().modify(contact);
    Contacts after = app.contacts().setContacts();

    //Assert ниже возвращает ЛОЖЬ, т.к. на этапе сохранения изменений, контакт удаляется.
    // Баг программы (не теста). Такое же поведение и при ручном тестировании
    Assert.assertEquals(app.contacts().count(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}

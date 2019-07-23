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
    if (! app.contacts().isThereAContact()) {
      app.contacts().initContactCreation();
      app.contacts().fillContactForm(new ContactData()
                      .withFirstName("Ivan").withLastName("Ivanov").withHomePhone("0501112233")
                      .withMobilePhone("0501112234").withWorkPhone("0501112235").withEmail("ivan_ivanov@te.st")
                      .withAddress("Lviv, vul. Naukova 3, kv. 56"));
      app.contacts().submitContactCreation();
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contacts().setContacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withContactId(modifiedContact.getContactId()).withFirstName("Akakiy")
            .withLastName("Maksyymenko").withEmail("Akakiy_Maksyymenko@te.st")
            .withAddress("Kirovograd, Fortechniy Prov., bld. 21А, appt. 90");

    app.contacts().modifyContact(contact);
    Contacts after = app.contacts().setContacts();
    System.out.println("before: " + before);
    System.out.println("after: " + after);

    //Assert ниже возвращает ЛОЖЬ, т.к. на этапе сохранения изменений, контакт удаляется.
    // Баг программы (не теста). Такое же поведение и при ручном тестировании
    Assert.assertEquals(app.contacts().count(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}

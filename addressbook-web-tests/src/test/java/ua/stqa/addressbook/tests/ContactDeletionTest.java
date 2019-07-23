package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;
import ua.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.contacts().setContacts();
    ContactData deletedContact = before.iterator().next();
    app.contacts().delete(deletedContact);

    assertThat(app.contacts().count(), equalTo(before.size() + 1));
    Contacts after = app.contacts().setContacts();
    assertThat(after, equalTo(before.withAdded(deletedContact)));
  }
}

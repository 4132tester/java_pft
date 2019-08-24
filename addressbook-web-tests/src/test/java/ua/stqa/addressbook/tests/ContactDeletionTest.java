package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;
import ua.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase {

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
  public void testContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.contacts().setContacts();
    ContactData deletedContact = before.iterator().next();
    app.contacts().delete(deletedContact);

    assertThat(app.contacts().count(), equalTo(before.size() - 1));
    Contacts after = app.contacts().setContacts();
    //сравнение списков контактов до удаления и после
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}

package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;
import ua.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contacts().setContacts();
    ContactData newContact = new ContactData()
            .withFirstName("Alina").withLastName("Krivenko").withHomePhone("0669873241")
            .withMobilePhone("0509632541").withWorkPhone("0507854123").withEmail("akrivenko@ergfd.com")
            .withEmail2("rtgfd3ghka@fakemailgenerator.net").withEmail3("3slksjdfdrff@iffymedia.com")
            .withAddress1("Franka Ul., bld. 24/110");
    app.contacts().create(newContact);
    Contacts after = app.contacts().setContacts();

    newContact.withContactId(after.stream().mapToInt((c) -> c.getContactId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(newContact)));
  }
}

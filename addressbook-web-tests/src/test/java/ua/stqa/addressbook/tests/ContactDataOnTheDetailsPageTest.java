package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataOnTheDetailsPageTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contacts().setContacts().size() == 0) {
      app.contacts().initContactCreation();
      app.contacts().fillContactForm(new ContactData()
              .withFirstName("Ivan").withLastName("Ivanov").withHomePhone("0501112233")
              .withMobilePhone("0501112234").withWorkPhone("0501112235").withEmail("1st_ivan_ivanov@te.st")
              .withEmail2("2nd_ivan_ivanov@te.st").withEmail3("3rd_ivan_ivanov@te.st")
              .withAddress1("Sumi / Gorkogo, Vul., bld. 23/1, appt. 54")
              .withAddress2("Novoguyvinske / Druzhbi Vul., bld. 6, appt. 40"));
      app.contacts().submitContactCreation();
    }
  }

  @Test
  public void testContactDataOnTheDetailsPage() {
    app.goTo().homePage();
    ContactData contact = app.contacts().setContacts().iterator().next();
    ContactData contactDataFromDetailsPage = app.contacts().infoFromDetailsPage(contact);
    ContactData contactDataFromEditForm = app.contacts().infoFromEditForm(contact);


    //assertThat(contact.getAllPhones(), equalTo(mergePhones(contactDataFromEditForm)));
  }
}

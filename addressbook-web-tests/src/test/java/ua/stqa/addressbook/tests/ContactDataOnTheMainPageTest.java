package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataOnTheMainPageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!  app.contacts().isThereAContact()) {
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
  public void testContactDataOnTheMainPage() {
    app.goTo().homePage();
    ContactData contact = app.contacts().setContacts().iterator().next();
    ContactData contactDataFromEditForm = app.contacts().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactDataFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactDataFromEditForm)));
    assertThat(contact.getAddress1(), equalTo(contactDataFromEditForm.getAddress1()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDataOnTheMainPageTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDataOnTheMainPageTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String word) {
    return word.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}

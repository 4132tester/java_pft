package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!  app.contacts().isThereAContact()) {
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
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contacts().setContacts().iterator().next();
    ContactData contactDataFromEditForm = app.contacts().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactDataFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}

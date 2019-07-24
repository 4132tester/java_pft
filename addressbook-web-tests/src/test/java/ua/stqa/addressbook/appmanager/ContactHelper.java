package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.stqa.addressbook.ContactData;
import ua.stqa.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  private Contacts contactCache = null;

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address"), contactData.getAddress());
  }

  public void initContactCreation() {
    click(By.linkText("ADD_NEW"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    driver.findElement(By.cssSelector(String.format("input[id='%d']", index))).click();
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[type='button'][value='DELETE']"));
    driver.switchTo().alert().accept();
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAContact() {
    return count() > 0;
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    contactCache = null;
    gotoHomepage();
  }

  public Contacts setContacts() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    } else {
      contactCache = new Contacts();
      if (isThereAContact()) {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > [name=\"entry\"]"));
        for (WebElement row : rows) {
          List<WebElement> cells = driver.findElements(By.tagName("td"));
          int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
          String firstname = cells.get(2).getText();
          String lastname = cells.get(1).getText();
          String email = cells.get(4).getText();
          String allPhones = cells.get(5).getText();
          ContactData person = new ContactData()
                  .withContactId(id).withFirstName(firstname).withLastName(lastname).withEmail(email)
                  .withAllPhones(allPhones);
          contactCache.add(person);
        }
      }
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getContactId());
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withContactId(contact.getContactId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  private void initContactModificationById(int contactId) {
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%d']", contactId))).click();
  }

  public void modifyContact(ContactData contact) {
    initContactModificationById(contact.getContactId());
    fillContactForm(contact);
    submitContactModification();
    contactCache = null;
    gotoHomepage();
  }

  public void delete(ContactData contact) {
    selectContact(contact.getContactId());
    deleteSelectedContact();
    contactCache = null;
    gotoHomepage();
  }

  private void gotoHomepage() {
    if (isElementPresent(By.id("maintable")))
      return;
    click(By.linkText("HOME"));
  }
}

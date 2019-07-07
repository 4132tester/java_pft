package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.stqa.addressbook.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

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

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void initContactModification(int contactIndex) {
    String path = "//*[@id = \"maintable\"]/tbody/tr[" + (contactIndex + 1) + "]/td[8]/a";
    click(By.xpath(path));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact() {
    click(By.cssSelector("input[name=\"selected[]\"]"));
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[type='button'][value='DELETE']"));
    driver.switchTo().alert().accept();
  }

  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAContact() {
    return getContactCount() > 0;
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    if (isThereAContact()) {
      String xpathOuter = "//*[@id=\"maintable\"]/tbody/tr";
      List<WebElement> rowsWithCustomers = driver.findElements(By.xpath(xpathOuter));
      int tableRowsNumber = rowsWithCustomers.size();
      //System.out.println(tableRowsNumber);
      for (int i = 2; i < rowsWithCustomers.size() + 1; i++) {
        String lastName = driver.findElement(By.xpath(xpathOuter + "[" + i + "]" + "/td[2]")).getText();
        String firstName = driver.findElement(By.xpath(xpathOuter + "[" + i + "]" + "/td[3]")).getText();
        String phone = driver.findElement(By.xpath(xpathOuter + "[" + i + "]" + "/td[6]")).getText();
        String href = driver.findElement(By.xpath(xpathOuter + "[" + i + "]" + "/td[7]/a")).getAttribute("href");
        int contactId = Integer.parseInt(href.substring(41));
        //System.out.println(contactId + " " + firstName + " " + lastName + " " + phone);
        ContactData person = new ContactData(contactId, firstName, lastName, phone, null, null);
        contacts.add(person);
      }
    }
    return contacts;
  }

}

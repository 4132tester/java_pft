package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ua.stqa.addressbook.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillContactForm (ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("email"),contactData.getEmail());
    type(By.name("address"),contactData.getAddress());
  }

  public void submitContactForm() {
    click(By.name("submit"));
  }

  public void selectContact() {
    click(By.cssSelector("#\32"));
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[value='DELETE'][type='button']"));
  }

  public void initContactModification() {
    click(By.cssSelector("#maintable > tbody > tr:nth-child(2) > td:nth-child(8) > a > img"));
  }
  public void submitContactModification() {
    click(By.name("update"));
  }
}

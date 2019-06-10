package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    System.out.println("gotoHomePage success");
    app.getContactHelper().selectContact();
    System.out.println("selectContact success");
    app.getContactHelper().deleteSelectedContact();
    System.out.println("deleteSelectedContact success");
    app.getNavigationHelper().gotoHomePage();
    System.out.println("gotoHomePage success");
  }
}

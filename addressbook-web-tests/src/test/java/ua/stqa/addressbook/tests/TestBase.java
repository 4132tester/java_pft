package ua.stqa.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ua.stqa.addressbook.appmanager.ApplicationManager;

public class TestBase {

  public final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeTest
  public void setUp() throws Exception {
    app.init();
  }

  @AfterTest
  public void tearDown() {
    app.stop();
  }

}

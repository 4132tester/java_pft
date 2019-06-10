package ua.stqa.addressbook;

public class ContactData {

  private String firstName;
  private String lastName;
  private String mobilePhone;
  private String email;
  private String address;

  public ContactData(String firstName, String lastName, String mobilePhone, String email, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.address = address;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }
}

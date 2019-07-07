package ua.stqa.addressbook;

import java.util.Objects;

public class ContactData {

  private int contactId;
  private String firstName;
  private String lastName;
  private String mobilePhone;
  private String email;
  private String address;

  public ContactData(String firstName, String lastName, String mobilePhone, String email, String address) {
    this.contactId = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.address = address;
  }

  public ContactData(int contactId, String firstName, String lastName, String mobilePhone, String email, String address) {
    this.contactId = contactId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.address = address;
  }

  public int getContactId() {
    return contactId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return firstName.equals(that.firstName) &&
            lastName.equals(that.lastName) &&
            Objects.equals(mobilePhone, that.mobilePhone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, mobilePhone);
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

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            '}';
  }
}

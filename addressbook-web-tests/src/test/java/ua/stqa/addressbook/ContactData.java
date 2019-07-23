package ua.stqa.addressbook;

import java.util.Objects;

public class ContactData {

  private int contactId;
  private String firstName;
  private String lastName;
  private String email;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withContactId(int contactId) {
    this.contactId = contactId;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAllPhones (String allPhones) {
    this.allPhones = allPhones;
    return this;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            '}';
  }
}

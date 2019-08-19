package ua.stqa.addressbook;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {

  private int contactId;
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  private File photo;
  @Expose
  private String email;
  private String email2;
  private String email3;
  @Expose
  private String address1;
  private String address2;
  private String homePhone;
  @Expose
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String allEmails;
  private String allContactData;


  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }


  public File getPhoto() {
    return photo;
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

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
      return allEmails;
    }

  public String getAllContactData() {
    return allContactData;
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


  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withAddress1(String address1) {
    this.address1 = address1;
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

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactData withAllPhones (String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails (String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public ContactData withAllContactData(String allContactData) {
    this.allContactData = allContactData;
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
    return contactId == that.contactId &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "contactId=" + contactId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}

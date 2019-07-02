package ua.stqa.addressbook;

import java.util.Objects;

public class GroupData {

  private String identifier;
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String identifier, String name, String header, String footer) {
    this.identifier = identifier;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(String name, String header, String footer) {
    this.identifier = String.valueOf(Integer.MAX_VALUE);
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getIdentifier() {
    return identifier;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "identifier='" + identifier + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}

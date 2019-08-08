package ua.stqa.addressbook;

import java.util.Objects;

public class GroupData {

  private int identifier = Integer.MAX_VALUE;
  private String name;
  private String header;
  private String footer;

  public int getIdentifier() {
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

  @Override
  public int hashCode() {
    return Objects.hash(identifier, name);
  }

  public GroupData withIdentifier(int identifier) {
    this.identifier = identifier;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "identifier=" + identifier +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return identifier == groupData.identifier &&
            Objects.equals(name, groupData.name);
  }
}

package com.scrypt.sfax.sfaxrepomanager.restservice.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "FaxNumbers")
public class GetNumbersResponse extends BaseResponse {
  @Root(name = "i")
  public static class NumberResponse {
    @Attribute
    String id;
    @Attribute
    String active;
    @Attribute
    String activeOutbound;
    @Attribute
    String inbound;
    @Attribute
    String outbound;
    @Attribute
    String unread;
    @Element(name = "a")
    String name;
    @Element(name = "b", required = false)
    String number;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getActive() {
      return active;
    }

    public void setActive(String active) {
      this.active = active;
    }

    public String getActiveOutbound() {
      return activeOutbound;
    }

    public void setActiveOutbound(String activeOutbound) {
      this.activeOutbound = activeOutbound;
    }

    public String getInbound() {
      return inbound;
    }

    public void setInbound(String inbound) {
      this.inbound = inbound;
    }

    public String getOutbound() {
      return outbound;
    }

    public void setOutbound(String outbound) {
      this.outbound = outbound;
    }

    public String getUnread() {
      return unread;
    }

    public void setUnread(String unread) {
      this.unread = unread;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNumber() {
      return number;
    }

    public void setNumber(String number) {
      this.number = number;
    }
  }

  @ElementList(inline = true)
  List<NumberResponse> numbers;

  public List<NumberResponse> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<NumberResponse> numbers) {
    this.numbers = numbers;
  }
}

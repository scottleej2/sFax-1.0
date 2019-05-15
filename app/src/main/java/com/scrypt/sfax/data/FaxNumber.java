package com.scrypt.sfax.data;

import com.scrypt.sfax.sfaxrepomanager.cacheservice.number.NumberEntity;
import com.scrypt.sfax.sfaxrepomanager.restservice.model.GetNumbersResponse;

import static com.scrypt.sfax.Util.ParseUtil.isTrue;

public class FaxNumber {
  private String id;
  private boolean active;
  private boolean activeOutbound;
  private boolean inbound;
  private boolean outbound;
  private int unread;
  private String name;
  private String number;

  public String getId() {
    return id;
  }

  private void setId(String id) {
    this.id = id;
  }

  public boolean isActive() {
    return active;
  }

  private void setActive(boolean active) {
    this.active = active;
  }

  public boolean isActiveOutbound() {
    return activeOutbound;
  }

  private void setActiveOutbound(boolean activeOutbound) {
    this.activeOutbound = activeOutbound;
  }

  public boolean isInbound() {
    return inbound;
  }

  private void setInbound(boolean inbound) {
    this.inbound = inbound;
  }

  public boolean isOutbound() {
    return outbound;
  }

  private void setOutbound(boolean outbound) {
    this.outbound = outbound;
  }

  public int getUnread() {
    return unread;
  }

  private void setUnread(int unread) {
    this.unread = unread;
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  private void setNumber(String number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return name;
  }


  // convert from retrofit response
  public static FaxNumber fromGetNumbersResponse(GetNumbersResponse.NumberResponse response) {
    FaxNumber faxNumber = new FaxNumber();

    faxNumber.setId(response.getId());
    faxNumber.setActive(isTrue(response.getActive()));
    faxNumber.setActiveOutbound(isTrue(response.getActiveOutbound()));
    faxNumber.setInbound(isTrue(response.getInbound()));
    faxNumber.setOutbound(isTrue(response.getOutbound()));
    faxNumber.setUnread(Integer.parseInt(response.getUnread()));
    faxNumber.setName(response.getName());
    faxNumber.setNumber(response.getNumber());
    return faxNumber;
  }

  // convert from cache database numberentity
  public static FaxNumber fromNumberEntity(NumberEntity entity) {
    FaxNumber faxNumber = new FaxNumber();

    faxNumber.setId(entity.getId());
    faxNumber.setActive(entity.isActive());
    faxNumber.setActiveOutbound(entity.isActiveOutbound());
    faxNumber.setInbound(entity.isInbound());
    faxNumber.setOutbound(entity.isOutbound());
    faxNumber.setUnread(entity.getUnread());
    faxNumber.setName(entity.getName());
    faxNumber.setNumber(entity.getNumber());

    return faxNumber;
  }
}

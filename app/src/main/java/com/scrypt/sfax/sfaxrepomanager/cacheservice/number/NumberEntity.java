package com.scrypt.sfax.sfaxrepomanager.cacheservice.number;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "number")
public class NumberEntity {

  @PrimaryKey
  @NonNull private String id;
  private boolean active;
  private boolean activeOutbound;
  private boolean inbound;
  private boolean outbound;
  private int unread;
  private String name;
  private String number;

  public NumberEntity(String id,
                      boolean active,
                      boolean activeOutbound,
                      boolean inbound,
                      boolean outbound,
                      int unread,
                      String name,
                      String number) {
    this.id = id;
    this.active = active;
    this.activeOutbound = activeOutbound;
    this.inbound = inbound;
    this.outbound = outbound;
    this.unread = unread;
    this.name = name;
    this.number = number;
  }

  public String getId() {
    return id;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isActiveOutbound() {
    return activeOutbound;
  }

  public boolean isInbound() {
    return inbound;
  }

  public boolean isOutbound() {
    return outbound;
  }

  public int getUnread() {
    return unread;
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }


}

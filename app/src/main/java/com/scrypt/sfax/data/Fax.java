package com.scrypt.sfax.data;

import com.scrypt.sfax.sfaxrepomanager.restservice.model.SearchFaxesResponse;

import java.util.Date;

public class Fax {
  private long id;
  private String name;
  private Date date;
  private boolean read;

  public long getId() { return id;}

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }


  public static Fax fromFaxResponse(SearchFaxesResponse.FaxResponse response) {
    Fax fax = new Fax();
    fax.setName(response.getDisplayName());

    return fax;
  }


}

package com.scrypt.sfax.sfaxrepomanager.restservice.model;


import org.simpleframework.xml.Element;

public class BaseResponse {

  @Element
  protected int success;

  @Element(required = false)
  private String title;

  @Element(required = false)
  private String message;

  public boolean isSuccess() {
    return success == 1;
  }

  public void setSuccess(int success) {
    this.success = success;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

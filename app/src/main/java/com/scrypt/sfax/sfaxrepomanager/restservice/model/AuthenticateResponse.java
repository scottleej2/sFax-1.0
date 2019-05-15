package com.scrypt.sfax.sfaxrepomanager.restservice.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Authenticate")
public class AuthenticateResponse extends BaseResponse {

  @Element(required = false)
  private String displayName;

  @Element(required = false)
  private String userToken;

  @Element(required = false)
  private String autoEnabled;

  @Element(required = false)
  private boolean pin;

  @Element(required = false)
  private String extendPin;

  @Element(required = false)
  private String activePin;

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getUserToken() {
    return userToken;
  }

  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }

  public String getAutoEnabled() {
    return autoEnabled;
  }

  public void setAutoEnabled(String autoEnabled) {
    this.autoEnabled = autoEnabled;
  }

  public boolean isPin() {
    return pin;
  }

  public void setPin(boolean pin) {
    this.pin = pin;
  }

  public String getExtendPin() {
    return extendPin;
  }

  public void setExtendPin(String extendPin) {
    this.extendPin = extendPin;
  }

  public String getActivePin() {
    return activePin;
  }

  public void setActivePin(String activePin) {
    this.activePin = activePin;
  }
}

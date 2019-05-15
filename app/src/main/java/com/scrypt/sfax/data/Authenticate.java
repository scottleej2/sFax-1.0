package com.scrypt.sfax.data;

import com.scrypt.sfax.sfaxrepomanager.restservice.model.AuthenticateResponse;

public class Authenticate extends Status {

  private String displayName;

  private String userToken;

  private String autoEnabled;

  private boolean pin;

  private String extendPin;

  private String activePin;

  public Authenticate(String displayName, String userToken, String autoEnabled,
                                  boolean pin, String extendPin, String activePin) {
    super(true, ERR_CODE.SUCCESS,null); // success case

    this.displayName = displayName;
    this.userToken = userToken;
    this.autoEnabled = autoEnabled;
    this.pin = pin;
    this.extendPin = extendPin;
    this.activePin = activePin;
  }

  public Authenticate() {

  }

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

  public static Authenticate fromAuthenticateResponse(AuthenticateResponse response) {
    Authenticate auth = new Authenticate();

    auth.setSuccess(response.isSuccess());
    auth.setMsg(response.getMessage());

    if (response.isSuccess()) {
      auth.setDisplayName(response.getDisplayName());
      auth.setUserToken(response.getUserToken());
      auth.setAutoEnabled(response.getAutoEnabled());
      auth.setPin(response.isPin());
      auth.setExtendPin(response.getExtendPin());
      auth.setActivePin(response.getActivePin());
    }

    return auth;
  }

}

package com.scrypt.sfax.data;

public class RequestResult {
  private boolean resultOk;
  private String value;

  public RequestResult(boolean resultOk, String value) {
    this.resultOk = resultOk;
    this.value = value;
  }

  public boolean isResultOk() {
    return resultOk;
  }

  public void setResultOk(boolean resultOk) {
    this.resultOk = resultOk;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

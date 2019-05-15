package com.scrypt.sfax.data;

public class Status {

  private boolean success;

  private ERR_CODE errorCode;

  private String msg;

  public enum ERR_CODE {SUCCESS, FAIL};

  public Status(boolean success, ERR_CODE errorCode, String msg) {
    this.success = success;
    this.errorCode = errorCode;
    this.msg = msg;
  }

  public Status() {

  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public ERR_CODE getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ERR_CODE errorCode) {
    this.errorCode = errorCode;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}

package com.scrypt.sfax.Util;

public class ParseUtil {
  public static boolean isTrue(String raw) {
    return raw != null && "1".equals(raw);
  }
}

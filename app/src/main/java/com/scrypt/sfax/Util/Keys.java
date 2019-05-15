package com.scrypt.sfax.Util;

public class Keys {

  public class Http {

    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";//Used to be 'Content-type'
    public static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";
    public static final String URL_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
    public static final String X_J2_HANDSET_TOKEN = "j2-handset-token";
    public static final String X_J2_HANDSET_MAKE = "j2-handset-make";
    public static final String X_SITE_ID = "site-id";
    public static final String X_LOCALE = "locale";
    public static final String X_MAPI_OAUTH_SITE_ID = "mapi-oauth-site-id";
    public static final String X_MAPI_OAUTH_LOCALE = "mapi-oauth-locale";
    public static final String ANDROID_HANDSET_MAKE = "Android";
    public static final String X_J2_CLIENT_APP_VERSION = "j2-client-app-version";

    public static final String X_MAPI_OAUTH_SITE_ID_EFAX = "EFAX";
    public static final String X_MAPI_OAUTH_SITE_ID_EFAXI = "EFAXI";
    public static final String X_MAPI_OAUTH_SITE_ID_METROFAX = "METROFAX";
    public static final String X_MAPI_OAUTH_SITE_ID_MYFAX = "MYFAX";


    public class GCM {
      public static final String MESSAGE_TYPE_KEY = "message_type";
      public static final String FREE_SIGNUP = "FREE_SIGNUP";
      public static final String ENCRYPTED_ORDER_NUMBER_KEY = "encrypted_order_number";
    }
  }

  public class Preferences {
    public static final String APP_PREFERENCES = "appPreferences";
  }

  public class AppPreferenceKeys {
    // Keys for String objects
    public static final String USER_TOKEN = "userToken";
  }

  public class DaggerMapKeys {
    public static final String INJECT_SCREEN_MAP_RENDERER_FAXLIST = "faxList";
    public static final String INJECT_SCREEN_MAP_RENDERER_CONTACTLIST = "ContactList";
  }


}

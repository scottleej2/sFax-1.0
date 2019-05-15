package com.scrypt.sfax.di.module;

import android.content.Context;

import com.scrypt.sfax.R;
import com.scrypt.sfax.Util.Keys;
import com.scrypt.sfax.Util.xml.XmlToJson;

import java.io.IOException;
import java.util.IllegalFormatConversionException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class NetworkModule {

  private Context mContext;

  public NetworkModule(Context context) {
    this.mContext = context;
  }

  private static class ApiRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request original = chain.request();
      Request request = original.newBuilder()
              .header(Keys.Http.HEADER_CONTENT_TYPE, Keys.Http.JSON_CONTENT_TYPE)
              .header(Keys.Http.X_J2_HANDSET_MAKE, Keys.Http.ANDROID_HANDSET_MAKE)
              .method(original.method(), original.body())
              .build();

      try {
        return chain.proceed(request);
      } catch(IOException e) {
        throw null;
      }
    }
  }

  private static class ApiResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {


      Request request = chain.request();
      Response response = chain.proceed(request);
      String xml = response.body().string();

      if(response.code() == 200) {
        XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();

        try {
          MediaType contentType = response.body().contentType();
          String jsonString = xmlToJson.toString();
          ResponseBody body = ResponseBody.create(contentType, jsonString);
          return response.newBuilder().body(body).build();

        } catch ( IllegalFormatConversionException e) {
          e.printStackTrace();
        }
      } else if(response.code() == 403) {

      }
      return response;

    }
  }

  @Provides
  @Singleton
  static Call.Factory provideOkHttp() {
    return new OkHttpClient.Builder()
//            .addInterceptor(new ApiResponseInterceptor())
//            .addInterceptor(new ApiRequestInterceptor())
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();
  }
/*
  // Moshi JSON converter case

  @Provides
  @Singleton
  Moshi provideMoshi() {
    return new Moshi.Builder()
            .add(AdapterFactory.create())
            //.add(new ZonedDateTimeAdapter())
            .build();
  }

  @Provides
  @Singleton
  @Named("api")
  Retrofit provideRetrofit(Moshi moshi, Call.Factory callFactory) {
    String baseUrl = mContext.getString(R.string.base_api_url);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory(callFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    return retrofit;
  }
  @Provides
  @Singleton
  @Named("root")
  Retrofit provideRootRetrofit(Moshi moshi, Call.Factory callFactory) {
    String baseUrl = mContext.getString(R.string.base_url);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory(callFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.client(okHttpClient)
            .build();

    return retrofit;
  }

*/


// XML case

  @Provides
  @Singleton
  @Named("api")
  Retrofit provideRetrofit(Call.Factory callFactory) {
    String baseUrl = mContext.getString(R.string.base_api_url);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory(callFactory)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    return retrofit;
  }

  @Provides
  @Singleton
  @Named("root")
  Retrofit provideRootRetrofit(Call.Factory callFactory) {
    String baseUrl = mContext.getString(R.string.base_url);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory(callFactory)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.client(okHttpClient)
            .build();

    return retrofit;
  }
}

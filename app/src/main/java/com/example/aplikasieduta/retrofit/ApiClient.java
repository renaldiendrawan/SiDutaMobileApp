package com.example.aplikasieduta.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://172.16.106.158/SiDuta/SiDutaMobile/";

    public static final String PHOTO_URL = BASE_URL + "upload/",
            PHOTO_URL_ANAK = BASE_URL + "uploadfotobalita/",
            PHOTO_URL_ARTIKEL = BASE_URL + "forms/berkas/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {

            // Tambahkan logging interceptor
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(loggingInterceptor)
//                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

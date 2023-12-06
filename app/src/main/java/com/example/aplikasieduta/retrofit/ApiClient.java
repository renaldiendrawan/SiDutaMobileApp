package com.example.aplikasieduta.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String ip = "https://si-duta.tifnganjuk.com/";
    public static final String BASE_URL = ip + "SiDutaMobile/";
    public static final String PHOTO_URL = BASE_URL + "upload/",
            PHOTO_URL_ANAK = BASE_URL + "uploadfotobalita/",
            PHOTO_URL_ARTIKEL = BASE_URL + "forms/berkas/";

    public static final String FOTO_BERKAS = ip;
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

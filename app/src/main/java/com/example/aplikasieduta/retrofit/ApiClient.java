package com.example.aplikasieduta.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://172.17.202.67/SiDuta/SiDutaMobile/";

    public static final String PHOTO_URL = BASE_URL + "upload/",
            PHOTO_URL_ANAK = BASE_URL + "uploadfotobalita/",
            PHOTO_URL_ARTIKEL = BASE_URL + "forms/berkas/";

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

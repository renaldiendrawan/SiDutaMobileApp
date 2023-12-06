package com.example.aplikasieduta.profilakun;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class DataShared {

    private final SharedPreferences sharedPrefs;
    private final SharedPreferences.Editor sharedEditor;
    public static final String NAME = "com.eduta.PREFERENCES";

    public DataShared(Context context) {

        // membuat object sharedpreferences
        this.sharedPrefs = context.getSharedPreferences(DataShared.NAME, Context.MODE_PRIVATE);
        this.sharedEditor = this.sharedPrefs.edit();
    }

    public boolean contains(@NonNull KEY key) {
        return this.sharedPrefs.contains(key.name());
    }

    public String getData(@NonNull KEY key) {
        return this.sharedPrefs.getString(key.name(), null);
    }

    public HashMap<KEY, String> getData(@NonNull KEY... keys) {
        HashMap<KEY, String> data = new HashMap<>();
        for (KEY key : keys) {
            if (contains(key)) {
                data.put(key, getData(key));
            } else {
                data.put(key, "null");
            }
        }
        return data;
    }

    public void setData(@NonNull KEY key, @NonNull String value) {
        this.sharedEditor.putString(key.name(), value).apply();
    }

    public void setNullData(@NonNull KEY key) {
        this.setData(key, "");
    }

    @Deprecated
    public void remove(@NonNull KEY key) {
        this.sharedEditor.remove(key.name()).apply();
    }

    public enum KEY {
        ACC_NIK_IBU,
        ACC_NAMA_IBU,
        ACC_TANGGAL_LAHIR,
        ACC_ALAMAT,
        ACC_EMAIL,
        ACC_IMAGE,
        BERANDA_ID,
        ACC_ID_ANAK,
        ACC_NAMA_ANAK,
        ACC_TANGGAL_LAHIR_ANAK,
        ACC_JENIS_KELAMIN,
        ACC_BB_LAHIR,
        ACC_TB_LAHIR,
        ACC_NAMA_AYAH,
        ACC_FOTO_ANAK,
        ANAK,
    }
}

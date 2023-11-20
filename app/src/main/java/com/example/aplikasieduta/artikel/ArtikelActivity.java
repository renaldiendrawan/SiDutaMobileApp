package com.example.aplikasieduta.artikel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasieduta.AkunActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.beranda.BerandaFragment;
import com.example.aplikasieduta.profilakun.ProfilAkunActivity;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelActivity extends AppCompatActivity {

    TextView judulArtikel, penulis, tanggalArtikel, isiArtikel;
    ImageView gambarArtikel;

    public static String JUDUL = "Id";

    private String dataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataId = getIntent().getStringExtra(JUDUL);

        setContentView(R.layout.activity_artikel);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.AR_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke AkunActivity
                Intent intent = new Intent(ArtikelActivity.this, BerandaFragment.class);
                startActivity(intent);
            }
        });

        judulArtikel = findViewById(R.id.AR_txt_1);
        penulis = findViewById(R.id.AR_txt_2);
        tanggalArtikel = findViewById(R.id.AR_txt_3);
        isiArtikel = findViewById(R.id.AR_txt_4);
        gambarArtikel = findViewById(R.id.AR_img_1);

        Toast.makeText(this, dataId, Toast.LENGTH_SHORT).show();

        ApiClient.getClient().create(ApiInterface.class).detailartikel(
                dataId

        ).enqueue(new Callback<ArtikelResponse>() {
            @Override
            public void onResponse(Call<ArtikelResponse> call, Response<ArtikelResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("success")) {

                    ArtikelModel model = response.body().getData().get(0);

                    judulArtikel.setText(model.judul_artikel);
                    penulis.setText(model.nama_kader);
                    tanggalArtikel.setText(model.tanggal_artikel);
                    isiArtikel.setText(model.isi_artikel);
                }
            }

            @Override
            public void onFailure(Call<ArtikelResponse> call, Throwable t) {

            }
        });

    }
}
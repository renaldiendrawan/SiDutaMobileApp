package com.example.aplikasieduta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.profilakun.ProfilAkunActivity;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.google.android.material.imageview.ShapeableImageView;

public class AkunActivity extends AppCompatActivity {
    TextView A_txt_4, A_txt_5;

    private DataShared dataShared;

    private ShapeableImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        imageView = findViewById(R.id.A_btn_1);

        dataShared = new DataShared(this);

        Glide.with(AkunActivity.this)
                .load(ApiClient.PHOTO_URL + dataShared.getData(DataShared.KEY.ACC_IMAGE))
                .placeholder(R.drawable.ic_person1)
                .into(imageView);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.A_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke Beranda Activity
                Intent intent = new Intent(AkunActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });

        TextView A_txt_4 = findViewById(R.id.A_txt_4);
        A_txt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika teks Profil Akun diklik, buka halaman Profil Akun
                Intent intent = new Intent(AkunActivity.this, ProfilAkunActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke tombol Profil Akun
        ImageButton ProfilAkunButton = findViewById(R.id.A_img_2);
        ProfilAkunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Profil Akun diklik, buka halaman Profil Akun
                Intent intent = new Intent(AkunActivity.this, ProfilAkunActivity.class);
                startActivity(intent);
            }
        });

        TextView A_txt_5 = findViewById(R.id.A_txt_5);
        A_txt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika teks Pengaturan Akun diklik, buka halaman Pengaturan Akun
                Intent intent = new Intent(AkunActivity.this, PengaturanAkunActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke tombol Pengaturan Akun
        ImageButton PengaturanAkunButton = findViewById(R.id.A_img_3);
        PengaturanAkunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Pengaturan Akun diklik, buka halaman Pengaturan Akun
                Intent intent = new Intent(AkunActivity.this, PengaturanAkunActivity.class);
                startActivity(intent);
            }
        });
    }
}
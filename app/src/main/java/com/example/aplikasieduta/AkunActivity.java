package com.example.aplikasieduta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.BerandaFragment;
import com.example.aplikasieduta.ProfilAkunActivity;

public class AkunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

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
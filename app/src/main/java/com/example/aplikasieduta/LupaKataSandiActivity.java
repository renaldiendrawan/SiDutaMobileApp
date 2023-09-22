package com.example.aplikasieduta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasieduta.LoginActivity;

public class LupaKataSandiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_kata_sandi);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.LKS_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke MainActivity
                Intent intent = new Intent(LupaKataSandiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke tombol Konfirmasi
        Button konfirmasiButton;
        konfirmasiButton = findViewById(R.id.LKS_btn_1);
        konfirmasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Konfirmasi diklik, buka halaman Login
                Intent intent = new Intent(LupaKataSandiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
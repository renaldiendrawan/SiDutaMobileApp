package com.example.aplikasieduta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasieduta.MainActivity;
import com.example.aplikasieduta.RegisterActivity;
import com.example.aplikasieduta.LupaKataSandiActivity;
import com.example.aplikasieduta.BerandaActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.L_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke teks "Lupa Kata Sandi?"
        TextView lupakatasandiText = findViewById(R.id.L_txt_lupakatasandi);
        lupakatasandiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika teks "Lupa Kata Sandi?" diklik, buka halaman Lupa Kata Sandi
                Intent intent = new Intent(LoginActivity.this, LupaKataSandiActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke tombol Masuk
        Button registerButton = findViewById(R.id.L_btn_1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Register diklik, buka halaman Beranda
                Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke teks "Belum punya akun? Register"
        TextView registerText = findViewById(R.id.L_txt_daftar);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika teks "Belum punya akun? Register" diklik, buka halaman Register
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
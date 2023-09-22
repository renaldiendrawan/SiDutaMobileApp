package com.example.aplikasieduta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasieduta.MainActivity;
import com.example.aplikasieduta.LoginActivity;
import com.example.aplikasieduta.BerandaActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.R_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke MainActivity
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke tombol Daftar
        Button registerButton = findViewById(R.id.R_btn_1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Register diklik, buka halaman Beranda
                Intent intent = new Intent(RegisterActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });

        // Mendapatkan referensi ke teks "Sudah punya akun? Login"
        TextView loginText = findViewById(R.id.R_txt_masuk);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika teks "Sudah punya akun? Login" diklik, buka halaman Login
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
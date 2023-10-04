package com.example.aplikasieduta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.aplikasieduta.AkunActivity;

public class PengaturanAkunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.PG_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke AkunActivity
                Intent intent = new Intent(PengaturanAkunActivity.this, AkunActivity.class);
                startActivity(intent);
            }
        });
    }
}
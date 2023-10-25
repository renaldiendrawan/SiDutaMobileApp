package com.example.aplikasieduta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DataBalitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_balita);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.DB_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke Beranda Activity
                Intent intent = new Intent(DataBalitaActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });
    }
}
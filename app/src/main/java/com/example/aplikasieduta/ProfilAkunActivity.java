package com.example.aplikasieduta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfilAkunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_akun);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.PF_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke AkunActivity
                Intent intent = new Intent(ProfilAkunActivity.this, AkunActivity.class);
                startActivity(intent);
            }
        });
    }
}

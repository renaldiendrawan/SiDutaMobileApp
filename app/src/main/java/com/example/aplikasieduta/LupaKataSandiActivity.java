package com.example.aplikasieduta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasieduta.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LupaKataSandiActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;

    @SuppressLint("MissingInflatedId")
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

        // Menyembunyikan atau Menampilkan Kata Sandi
        textInputLayout = findViewById(R.id.LKS_textinputlayoutkatasandibaru);
        textInputEditText = findViewById(R.id.LKS_inputkatasandibaru);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String katasandibaruInput = charSequence.toString();
                if (katasandibaruInput.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(katasandibaruInput);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Kata Sandi Anda Sesuai ");
                        textInputLayout.setError("");
                    } else {
                        textInputLayout.setError("Kombinasi Huruf (Kapital dan Kecil), Angka dan Simbol");
                    }
                } else {
                    textInputLayout.setHelperText("Kata Sandi Minimal 8 Karakter");
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Menyembunyikan atau Menampilkan Kata Sandi
        textInputLayout = findViewById(R.id.LKS_textinputlayoutkonfirmasikatasandibaru);
        textInputEditText = findViewById(R.id.LKS_inputkonfirmasikatasandibaru);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String konfirmasikatasandibaruInput = charSequence.toString();
                if (konfirmasikatasandibaruInput.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(konfirmasikatasandibaruInput);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Kata Sandi Anda Sesuai ");
                        textInputLayout.setError("");
                    } else {
                        textInputLayout.setError("Kombinasi Huruf (Kapital dan Kecil), Angka dan Simbol");
                    }
                } else {
                    textInputLayout.setHelperText("Kata Sandi Minimal 8 Karakter");
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
package com.example.aplikasieduta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasieduta.MainActivity;
import com.example.aplikasieduta.LoginActivity;
import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.BerandaFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText NamaOrangTua;
    Button btnDaftar;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;

    private  String KEY_NAME = "NAMA";

    @SuppressLint("MissingInflatedId")
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

        // Menyembunyikan atau Menampilkan Kata Sandi
        textInputLayout = findViewById(R.id.R_textinputlayoutkatasandi);
        textInputEditText = findViewById(R.id.R_inputkatasandi);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String katasandiInput = charSequence.toString();
                if (katasandiInput.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(katasandiInput);
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

        // Menyembunyikan atau Menampilkan Konfirmasi Kata Sandi
        textInputLayout = findViewById(R.id.R_textinputlayoutkonfirmasikatasandi);
        textInputEditText = findViewById(R.id.R_inputkonfirmasikatasandi);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String konfirmasikatasandiInput = charSequence.toString();
                if (konfirmasikatasandiInput.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(konfirmasikatasandiInput);
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

        NamaOrangTua = findViewById(R.id.R_edt_namaorangtua);
        btnDaftar = (Button) findViewById(R.id.R_btn_1);
        btnDaftar.setOnClickListener(v ->{
                    String nama = NamaOrangTua.getText().toString();
                    if (nama != ""){
                        Intent i = new Intent(this, BerandaActivity.class);
                        i.putExtra("KEY_NAME", nama);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplication(), "YOU NEED TO FILL YOUR NAME",Toast.LENGTH_SHORT);
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
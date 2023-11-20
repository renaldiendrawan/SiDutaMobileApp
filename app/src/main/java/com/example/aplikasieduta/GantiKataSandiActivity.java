package com.example.aplikasieduta;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasieduta.model.LupaKataSandi.Lupa_katasandi;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiKataSandiActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_kata_sandi);

        SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.GKS_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke Lupa Kata Sandi Activity
                Intent intent = new Intent(GantiKataSandiActivity.this, LupaKataSandiActivity.class);
                startActivity(intent);
            }
        });

        // Menyembunyikan atau Menampilkan Kata Sandi
        textInputLayout = findViewById(R.id.GKS_katasandilayout);
        textInputEditText = findViewById(R.id.GKS_katasandiedit);
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
        textInputLayout = findViewById(R.id.GKS_konfirmasilayout);
        textInputEditText = findViewById(R.id.GKS_konfirmasiedit);
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
        konfirmasiButton = findViewById(R.id.GKS_btn_1);
        konfirmasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Konfirmasi diklik, buka halaman Login
                Intent intent = new Intent(GantiKataSandiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = null;
        // String notelp = intent.getStringExtra("notelp");

        EditText password = findViewById(R.id.GKS_katasandiedit),
        kode_otp = findViewById(R.id.LKS_kodeotpedit);

        Button konfirmasi = findViewById(R.id.GKS_btn_1);

        konfirmasi.setOnClickListener(v->{

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Lupa_katasandi> lupaKatasandiCall = apiInterface.lupaKatasandi_ganti(email,
                kode_otp.getText().toString(),password.getText().toString());
             lupaKatasandiCall.enqueue(new Callback<Lupa_katasandi>() {
            @Override
            public void onResponse(Call<Lupa_katasandi> call, Response<Lupa_katasandi> response) {
                if (response.body().isStatus() == true){
                    Toast.makeText(GantiKataSandiActivity.this, "Kata Sandi Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    Intent buka = new Intent(GantiKataSandiActivity.this, LoginActivity.class);
                    startActivity(buka);
                }else {
                    Toast.makeText(GantiKataSandiActivity.this, "Kode OTP Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lupa_katasandi> call, Throwable t) {
                Toast.makeText(GantiKataSandiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());
            }
        });

        });
    }
}
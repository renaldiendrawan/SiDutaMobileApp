package com.example.aplikasieduta.model.LupaKataSandi;

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

import com.example.aplikasieduta.R;
import com.example.aplikasieduta.model.login.LoginActivity;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiKataSandiActivity extends AppCompatActivity {

    TextInputLayout textInputLayoutKataSandi, textInputLayoutKonfirmasi;
    TextInputEditText textInputEditTextKataSandi, textInputEditTextKonfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_kata_sandi);

        SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

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
        textInputLayoutKataSandi = findViewById(R.id.GKS_katasandilayout);
        textInputEditTextKataSandi = findViewById(R.id.GKS_katasandiedit);
        textInputEditTextKataSandi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String katasandibaruInput = charSequence.toString();
                if (isValidPassword(katasandibaruInput)) {
                    textInputLayoutKataSandi.setHelperText("Kata Sandi Anda Sesuai ");
                    textInputLayoutKataSandi.setError(null);
                } else {
                    textInputLayoutKataSandi.setError("Kombinasi Huruf dan Angka diperlukan");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Menyembunyikan atau Menampilkan Konfirmasi Kata Sandi
        textInputLayoutKonfirmasi = findViewById(R.id.GKS_konfirmasilayout);
        textInputEditTextKonfirmasi = findViewById(R.id.GKS_konfirmasiedit);
        textInputEditTextKonfirmasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String konfirmasikatasandibaruInput = charSequence.toString();
                if (isValidPassword(konfirmasikatasandibaruInput)) {
                    textInputLayoutKonfirmasi.setHelperText("Kata Sandi Anda Sesuai ");
                    textInputLayoutKonfirmasi.setError(null);
                } else {
                    textInputLayoutKonfirmasi.setError("Kombinasi Huruf dan Angka diperlukan");
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

        Button konfirmasi = findViewById(R.id.GKS_btn_1);

        konfirmasi.setOnClickListener(v -> {

            EditText password = findViewById(R.id.GKS_katasandiedit),
                    konfirmasipassword = findViewById(R.id.GKS_konfirmasiedit),
                    kode_otp = findViewById(R.id.LKS_kodeotpedit);

            String txtkonfirmasi = konfirmasipassword.getText().toString();
            String txtpassword = password.getText().toString();

            if (!txtkonfirmasi.equalsIgnoreCase(txtpassword)) {
                Toast.makeText(this, "Konfirmasi Kata Sandi Salah", Toast.LENGTH_SHORT).show();
            } else {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Lupa_katasandi> lupaKatasandiCall = apiInterface.lupaKatasandi_ganti(email,
                        kode_otp.getText().toString(), password.getText().toString());
                lupaKatasandiCall.enqueue(new Callback<Lupa_katasandi>() {
                    @Override
                    public void onResponse(Call<Lupa_katasandi> call, Response<Lupa_katasandi> response) {
                        if (response.body().isStatus() == true) {
                            Toast.makeText(GantiKataSandiActivity.this, "Kata Sandi Berhasil Diubah", Toast.LENGTH_SHORT).show();
                            Intent buka = new Intent(GantiKataSandiActivity.this, LoginActivity.class);
                            startActivity(buka);
                        } else {
                            Toast.makeText(GantiKataSandiActivity.this, "Kode OTP Salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Lupa_katasandi> call, Throwable t) {
                        Toast.makeText(GantiKataSandiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error", t.getMessage());
                    }
                });
            }

        });
    }

    private boolean isValidPassword(String password) {
        // Memeriksa apakah terdapat minimal satu huruf dan satu angka
        // serta panjang minimal 8 karakter
        return password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*") && password.length() >= 8;
    }
}

package com.example.aplikasieduta.model.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.model.LupaKataSandi.LupaKataSandiActivity;
import com.example.aplikasieduta.MainActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.SessionManager;
import com.example.aplikasieduta.model.register.RegisterActivity;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText, L_edt_nik, L_inputkatasandi;
    Button L_btn_1;
    String nik, katasandi;
    TextView L_txt_daftar;
    ApiInterface apiInterface;
    SessionManager sessionManager;
    private DataShared dataShared;
    SharedPreferences sharedPreferences;
    private final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataShared = new DataShared(this);

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

        // Menyembunyikan atau Menampilkan Kata Sandi
        textInputLayout = findViewById(R.id.L_textinputlayoutkatasandi);
        textInputEditText = findViewById(R.id.L_inputkatasandi);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String katasandiInput = charSequence.toString();
                if (katasandiInput.length() >= 8) {
                    if (containsLetterAndDigit(katasandiInput)) {
                        textInputLayout.setHelperText("Kata Sandi Anda Sesuai ");
                        textInputLayout.setError(null);
                    } else {
                        textInputLayout.setError("Kombinasi Huruf dan Angka diperlukan");
                    }
                } else {
                    textInputLayout.setHelperText("Kata Sandi Minimal 8 Karakter");
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

        L_edt_nik = findViewById(R.id.L_edt_nik);
        L_inputkatasandi = findViewById(R.id.L_inputkatasandi);

        L_btn_1 = findViewById(R.id.L_btn_1);
        L_btn_1.setOnClickListener(this);

        L_txt_daftar = findViewById(R.id.L_txt_daftar);
        L_txt_daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.L_btn_1:
                if (TextUtils.isEmpty(L_edt_nik.getText().toString())) {
                    Toast.makeText(this, "NIK Orang Tua belum diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(L_inputkatasandi.getText().toString())) {
                    Toast.makeText(this, "Kata Sandi belum diisi", Toast.LENGTH_SHORT).show();
                } else {
                    nik = L_edt_nik.getText().toString();
                    katasandi = L_inputkatasandi.getText().toString();
                    login(nik, katasandi);
                }

                break;
            case R.id.L_txt_daftar:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login(String nik, String katasandi) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(nik, katasandi);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {

                    // Ini untuk menyimpan sesi
                    sessionManager = new SessionManager(LoginActivity.this);
                    LoginData loginData = response.body().getLoginData();
                    sessionManager.createLoginSession(loginData);

                    sharedPreferences = sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nik_ibu", loginData.getNikIbu());
                    editor.apply();

                    dataShared.setData(DataShared.KEY.ACC_NIK_IBU, loginData.getNikIbu());
                    dataShared.setData(DataShared.KEY.ACC_NAMA_IBU, loginData.getNamaIbu());
                    dataShared.setData(DataShared.KEY.ACC_ALAMAT, loginData.getAlamat());
                    dataShared.setData(DataShared.KEY.ACC_TANGGAL_LAHIR, loginData.getTanggal_lahir());
                    dataShared.setData(DataShared.KEY.ACC_EMAIL, loginData.getEmail());

                    // Ini untuk pindah
                    Toast.makeText(LoginActivity.this, response.body().getLoginData().getNamaIbu(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean containsLetterAndDigit(String input) {
        // Memeriksa apakah terdapat minimal satu huruf dan satu angka
        return input.matches(".*[a-zA-Z].*") && input.matches(".*\\d.*");
    }
}

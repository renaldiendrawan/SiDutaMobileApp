package com.example.aplikasieduta;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasieduta.model.register.Register;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText, R_edt_namaorangtua, R_edt_nikorangtua, R_edt_tanggallahir, R_edt_alamat, R_edt_email, R_inputkatasandi;
    Button R_btn_1;
    TextView R_txt_masuk;
    String nama, nik, tanggallahir, alamat, email, katasandi;
    ApiInterface apiInterface;
    private int tahun, bulan, tanggal;

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

        // Date picker
        R_edt_tanggallahir = findViewById(R.id.R_edt_tanggallahir);
        R_edt_tanggallahir.setFocusableInTouchMode(false);
        R_edt_tanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        tanggal = dayOfMonth;

                        R_edt_tanggallahir.setText(tahun + "-" + bulan + "-" + tanggal);
                    }
                }, tahun, bulan, tanggal);
                dialog.show();
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

//        // Menyembunyikan atau Menampilkan Konfirmasi Kata Sandi
//        textInputLayout = findViewById(R.id.R_textinputlayoutkonfirmasikatasandi);
//        textInputEditText = findViewById(R.id.R_inputkonfirmasikatasandi);
//        textInputEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                String konfirmasikatasandiInput = charSequence.toString();
//                if (konfirmasikatasandiInput.length() >= 8) {
//                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
//                    Matcher matcher = pattern.matcher(konfirmasikatasandiInput);
//                    boolean passwordsMatch = matcher.find();
//                    if (passwordsMatch) {
//                        textInputLayout.setHelperText("Kata Sandi Anda Sesuai ");
//                        textInputLayout.setError("");
//                    } else {
//                        textInputLayout.setError("Kombinasi Huruf (Kapital dan Kecil), Angka dan Simbol");
//                    }
//                } else {
//                    textInputLayout.setHelperText("Kata Sandi Minimal 8 Karakter");
//                    textInputLayout.setError("");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        R_edt_namaorangtua = findViewById(R.id.R_edt_namaorangtua);
        R_edt_nikorangtua = findViewById(R.id.R_edt_nikorangtua);
        R_edt_tanggallahir = findViewById(R.id.R_edt_tanggallahir);
        R_edt_alamat = findViewById(R.id.R_edt_alamat);
        R_edt_email = findViewById(R.id.R_edt_email);
        R_inputkatasandi = findViewById(R.id.R_inputkatasandi);

        R_btn_1 = findViewById(R.id.R_btn_1);
        R_btn_1.setOnClickListener(this);

        R_txt_masuk = findViewById(R.id.R_txt_masuk);
        R_txt_masuk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.R_btn_1:
                nama = R_edt_namaorangtua.getText().toString();
                nik = R_edt_nikorangtua.getText().toString();
                tanggallahir = R_edt_tanggallahir.getText().toString();
                alamat = R_edt_alamat.getText().toString();
                email = R_edt_email.getText().toString();
                katasandi = R_inputkatasandi.getText().toString();
                register(nama, nik, tanggallahir, alamat, email, katasandi);
                break;
            case R.id.R_txt_masuk:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void register(String nama, String nik, String tanggallahir, String alamat, String email, String katasandi) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerResponse(nama, nik, tanggallahir, alamat, email, katasandi);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
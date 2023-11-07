package com.example.aplikasieduta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasieduta.LoginActivity;
import com.example.aplikasieduta.model.LupaKataSandi.Lupa_katasandi;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaKataSandiActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText, LKS_emailedit, LKS_kodeotpedit;
    Button LKS_btn_kodeotp, LKS_btn_verifikasi;
    FirebaseAuth mAuth;
    ProgressBar LKS_progresssbar;
    LinearLayout LKS_ly_2;
    String verifikasiID;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_kata_sandi);
        EditText email = findViewById(R.id.LKS_emailedit);

        SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.LKS_btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke MainActivity
                Intent intent = new Intent(LupaKataSandiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button lanjut = findViewById(R.id.LKS_btn_kodeotp);
        lanjut.setOnClickListener(v->{
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Lupa_katasandi> lupaKatasandiCall = apiInterface.lupakatasandi_otp(email.getText().toString());

        lupaKatasandiCall.enqueue(new Callback<Lupa_katasandi>() {
            @Override
            public void onResponse(Call<Lupa_katasandi> call, Response<Lupa_katasandi> response) {
                if (response.body().isStatus() == true ){
                    Toast.makeText(LupaKataSandiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent buka = new Intent(LupaKataSandiActivity.this, GantiKataSandiActivity.class);
                    editor.putString("email", String.valueOf(email.getText()));
                    editor.apply();
                    startActivity(buka);
                }else{
                    Toast.makeText(LupaKataSandiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lupa_katasandi> call, Throwable t) {
                Toast.makeText(LupaKataSandiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        });

    }
}
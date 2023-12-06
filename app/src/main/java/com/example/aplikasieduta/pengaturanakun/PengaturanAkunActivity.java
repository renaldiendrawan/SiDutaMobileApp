package com.example.aplikasieduta.pengaturanakun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasieduta.AkunActivity;
import com.example.aplikasieduta.model.login.LoginActivity;
import com.example.aplikasieduta.MainActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.SessionManager;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaturanAkunActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private String nik_ibu;
    private DataShared dataShared;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun);

        DataShared shared = new DataShared(this);
        nik_ibu = shared.getData(DataShared.KEY.ACC_NIK_IBU);
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

        // Inisialisasi sessionManager
        sessionManager = new SessionManager(this);

        // Mengaitkan OnClickListener ke tombol PG_actionlogout
        ImageButton logoutButton = findViewById(R.id.PG_actionlogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });

        // Mengaitkan OnClickListener ke TextView PG_txt_5
        TextView pgText5 = findViewById(R.id.PG_txt_5);
        pgText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });

        // Mengaitkan OnClickListener ke TextView PG_txt_2 dan ImageButton PG_img_2
        TextView pgText2 = findViewById(R.id.PG_txt_2);
        pgText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAccountConfirmationDialog();
            }
        });

        ImageButton pgImg2 = findViewById(R.id.PG_img_2);
        pgImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAccountConfirmationDialog();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sessionManager.logoutSession();
                        moveToMainActivity();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tidak melakukan apa-apa jika tombol "Tidak" diklik
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }


    private void deleteAccount() {

        // Implementasikan logika penghapusan akun di sini
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PengaturanHapusAkunResponse> call = apiInterface.deleteAccount(nik_ibu); // Ganti dengan endpoint yang sesuai
        call.enqueue(new Callback<PengaturanHapusAkunResponse>() {
            @Override
            public void onResponse(Call<PengaturanHapusAkunResponse> call, Response<PengaturanHapusAkunResponse> response) {

                if (response.body().getStatus().toLowerCase().equalsIgnoreCase("success")) {
                    // Penghapusan akun berhasil, pindahkan pengguna ke halaman MainActivity
                    runOnUiThread(() -> {
                        startActivity(new Intent(PengaturanAkunActivity.this, MainActivity.class));
                    });
                } else {
                    // Penghapusan akun gagal, tampilkan pesan kesalahan atau ambil informasi dari response
                    Toast.makeText(PengaturanAkunActivity.this, "Gagal menghapus akun", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PengaturanHapusAkunResponse> call, Throwable t) {
                Log.e("error pada hapus akun", t.getMessage());
            }
        });
    }

    // Metode ini akan dipanggil saat pengguna mengklik "Ya" pada dialog konfirmasi penghapusan akun
    private void showDeleteAccountConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda yakin ingin menghapus akun?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteAccount();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tidak melakukan apa-apa jika tombol "Tidak" diklik
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}

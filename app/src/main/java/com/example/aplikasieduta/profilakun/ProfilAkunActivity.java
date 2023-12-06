package com.example.aplikasieduta.profilakun;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.aplikasieduta.AkunActivity;
import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilAkunActivity extends AppCompatActivity {

    FloatingActionButton PF_floatingActionButton2, PF_btn_2;
    ImageView imageView;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    Uri selectedImageUri;
    EditText nama_ibu, nik_ibu, tanggal_lahir, alamat, email, PF_edt_tanggallahir;
    private ShapeableImageView imagepath;
    private static final int PICK_IMAGE = 1;
    private Uri imageUri;
    private DataShared dataShared;
    private int tahun, bulan, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_akun);

        PF_edt_tanggallahir = findViewById(R.id.PF_edt_tanggallahir);
        PF_edt_tanggallahir.setFocusableInTouchMode(false);
        PF_edt_tanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(ProfilAkunActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        tanggal = dayOfMonth;

                        PF_edt_tanggallahir.setText(tahun + "-" + bulan + "-" + tanggal);
                    }
                }, tahun, bulan, tanggal);
                dialog.show();
            }
        });

        dataShared = new DataShared(this);

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

        imageView = findViewById(R.id.PF_btn_1);
        Button button = findViewById(R.id.PF_btn_2);
        PF_floatingActionButton2 = findViewById(R.id.PF_floatingActionButton2);

        Glide.with(ProfilAkunActivity.this)
                .load(ApiClient.PHOTO_URL + dataShared.getData(DataShared.KEY.ACC_IMAGE))
                .placeholder(R.drawable.ic_person1)
                .into(imageView);

        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            selectedImageUri = result.getData().getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                                imageView.setImageBitmap(bitmap);

                                String encode = ImageUtil.bitmapToBase64String(bitmap, 100);

                                ApiClient.getClient().create(ApiInterface.class)
                                        .updatePhoto(nik_ibu.getText().toString(), encode)
                                        .enqueue(new Callback<ProfilAkunResponse>() {
                                            @Override
                                            public void onResponse(Call<ProfilAkunResponse> call, Response<ProfilAkunResponse> response) {
                                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                                    Toast.makeText(ProfilAkunActivity.this, "Update Foto Berhasil", Toast.LENGTH_SHORT).show();

                                                    dataShared.setData(DataShared.KEY.ACC_IMAGE, response.body().getImagepath());

                                                    Glide.with(ProfilAkunActivity.this)
                                                            .load(ApiClient.PHOTO_URL + dataShared.getData(DataShared.KEY.ACC_IMAGE))
                                                            .placeholder(R.drawable.ic_person1)
                                                            .into(imageView);

                                                } else {
                                                    Toast.makeText(ProfilAkunActivity.this, "Update Foto Gagal", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ProfilAkunResponse> call, Throwable t) {
                                                t.printStackTrace();
                                                Toast.makeText(ProfilAkunActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        PF_floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });


        // Shared Preferences code
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

        // Retrieve user data from shared preferences
        String namaIbu = sharedPreferences.getString("nama_ibu", "");
        String nikIbu = sharedPreferences.getString("nik_ibu", "");

        // Initialize UI elements
        nama_ibu = findViewById(R.id.PF_edt_namaorangtua);
        nik_ibu = findViewById(R.id.PF_edt_nikorangtua);
        tanggal_lahir = findViewById(R.id.PF_edt_tanggallahir);
        alamat = findViewById(R.id.PF_edt_alamat);
        email = findViewById(R.id.PF_edt_email);
        imagepath = findViewById(R.id.PF_btn_1);

        email.setText(dataShared.getData(DataShared.KEY.ACC_EMAIL));
        alamat.setText(dataShared.getData(DataShared.KEY.ACC_ALAMAT));
        tanggal_lahir.setText(dataShared.getData(DataShared.KEY.ACC_TANGGAL_LAHIR));
        nik_ibu.setText(dataShared.getData(DataShared.KEY.ACC_NIK_IBU));
        nama_ibu.setText(dataShared.getData(DataShared.KEY.ACC_NAMA_IBU));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfilAkunResponse> call = apiInterface.ambilAkun(nikIbu);

        call.enqueue(new Callback<ProfilAkunResponse>() {
            @Override
            public void onResponse(Call<ProfilAkunResponse> call, Response<ProfilAkunResponse> response) {
                ProfilAkunResponse respon = response.body();
                if (respon != null) {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        ArrayList<ProfilAkunModel> list = (ArrayList<ProfilAkunModel>) respon.getData();
                        if (list != null && !list.isEmpty()) {
                            ProfilAkunModel data = list.get(0);
                            nama_ibu.setText(data.getNama_ibu());
                            tanggal_lahir.setText(data.getTanggal_lahir());
                            alamat.setText(data.getAlamat());
                            email.setText(data.getEmail());
                        } else {
                            Toast.makeText(ProfilAkunActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("error", respon.getMessage());
                    }
                } else {
                    Log.e("error", "Response is null");
                }
            }

            @Override
            public void onFailure(Call<ProfilAkunResponse> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namaibu = nama_ibu.getText().toString();
                String nikibu = nik_ibu.getText().toString();
                String tanggallahir = tanggal_lahir.getText().toString();
                String Alamat = alamat.getText().toString();
                String Email = email.getText().toString();

                // Penanganan setError untuk setiap widget input
                if (TextUtils.isEmpty(namaibu)) {
                    nama_ibu.setError("Nama Lengkap Orang Tua harus diisi");
                    nama_ibu.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(nikibu)) {
                    nik_ibu.setError("NIK Orang Tua harus diisi!");
                    nik_ibu.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(tanggallahir)) {
                    tanggal_lahir.setError("Tanggal Lahir Orang Tua harus diisi");
                    tanggal_lahir.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Alamat)) {
                    alamat.setError("Alamat harus diisi");
                    alamat.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Email)) {
                    email.setError("Email harus diisi");
                    email.requestFocus();
                    return;
                }

                ApiClient.getClient().create(ApiInterface.class)
                        .editAkun(
                                dataShared.getData(DataShared.KEY.ACC_NIK_IBU),
                                nama_ibu.getText().toString(),
                                tanggal_lahir.getText().toString(),
                                alamat.getText().toString(),
                                email.getText().toString()

                        ).enqueue(new Callback<ProfilAkunResponse>() {
                            @Override
                            public void onResponse(Call<ProfilAkunResponse> call, Response<ProfilAkunResponse> response) {

                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                                    dataShared.setData(DataShared.KEY.ACC_NIK_IBU, nik_ibu.getText().toString());
                                    dataShared.setData(DataShared.KEY.ACC_NAMA_IBU, nama_ibu.getText().toString());
                                    dataShared.setData(DataShared.KEY.ACC_ALAMAT, alamat.getText().toString());
                                    dataShared.setData(DataShared.KEY.ACC_TANGGAL_LAHIR, tanggal_lahir.getText().toString());
                                    dataShared.setData(DataShared.KEY.ACC_EMAIL, email.getText().toString());

                                    startActivity(new Intent(ProfilAkunActivity.this, BerandaActivity.class));
                                } else {
                                    Toast.makeText(ProfilAkunActivity.this, "Data Gagal Diubah", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ProfilAkunResponse> call, Throwable t) {
                                Toast.makeText(ProfilAkunActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private class UploadImageTask extends AsyncTask<Uri, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Uri... uris) {
            Uri selectedImageUri = uris[0];
            try {

                URL url = new URL("http://172.16.106.121/SiDuta/SiDutaMobile/profilakun.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                // Image upload was successful
                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
            } else {
                // Image upload failed
                Toast.makeText(getApplicationContext(), "Failed to upload image", Toast.LENGTH_LONG).show();
            }
        }
    }
}

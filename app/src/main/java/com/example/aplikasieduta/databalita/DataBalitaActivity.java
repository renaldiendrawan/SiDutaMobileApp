package com.example.aplikasieduta.databalita;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.profilakun.ImageUtil;
import com.example.aplikasieduta.profilakun.ProfilAkunResponse;
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
import retrofit2.Retrofit;

public class DataBalitaActivity extends AppCompatActivity {

    public static final String NAMA_ANAK = "nama", ID_ANAK = "id";

    private String namaAnak, id;
    private DataShared dataShared;
    private int tahun, bulan, tanggal;
    EditText DB_edt_tanggallahiranak;
    EditText nama_anak;
    EditText tanggal_lahir_anak;
    Spinner jenis_kelamin;
    EditText bb_lahir;
    EditText tb_lahir;
    EditText nama_ayah;
    EditText nama_ibu;
    ImageView imageView;
    FloatingActionButton DB_floatingActionButton2;
    Bitmap bitmap;
    Uri selectedImageUri;
    private ShapeableImageView imagepath;
    Spinner spinner;
    String selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_balita);

        // Mendapatkan referensi ke tombol Back
        ImageButton backButton = findViewById(R.id.DB_img_1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika tombol Back diklik, kembali ke Beranda Activity
                Intent intent = new Intent(DataBalitaActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });

        String[] genderOptions = getResources().getStringArray(R.array.jenis_kelamin_array);
        spinner = findViewById(R.id.DB_edt_jeniskelaminanak);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genderOptions);
        genderAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(genderAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedGender = genderOptions[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(DataBalitaActivity.this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
            }
        });

        DB_edt_tanggallahiranak = findViewById(R.id.DB_edt_tanggallahiranak);
        DB_edt_tanggallahiranak.setFocusableInTouchMode(false);
        DB_edt_tanggallahiranak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(DataBalitaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        tanggal = dayOfMonth;

                        DB_edt_tanggallahiranak.setText(tahun + "-" + bulan + "-" + tanggal);
                    }
                }, tahun, bulan, tanggal);
                dialog.show();
            }
        });

        dataShared = new DataShared(this);

        imageView = findViewById(R.id.DB_btn_1);
        Button button = findViewById(R.id.DB_btn_2);
        DB_floatingActionButton2 = findViewById(R.id.DB_floatingActionButton2);

        // Shared Preferences code
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

        // Retrieve user data from shared preferences
        String namaIbu = sharedPreferences.getString("nama_ibu", "");
        String nikIbu = sharedPreferences.getString("nik_ibu", "");

        // Initialize UI elements
        nama_anak = findViewById(R.id.DB_edt_namalengkapanak);
        tanggal_lahir_anak = findViewById(R.id.DB_edt_tanggallahiranak);
        jenis_kelamin = findViewById(R.id.DB_edt_jeniskelaminanak);
        bb_lahir = findViewById(R.id.DB_edt_beratbadanlahir);
        tb_lahir = findViewById(R.id.DB_edt_tinggibadanlahir);
        nama_ayah = findViewById(R.id.DB_edt_namaayah);
        nama_ibu = findViewById(R.id.DB_edt_namaibu);
        imagepath = findViewById(R.id.DB_btn_1);
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
                                        .updatePhotoBalita(nama_anak.getText().toString(), encode)
                                        .enqueue(new Callback<DataBalitaResponse>() {
                                            @Override
                                            public void onResponse(Call<DataBalitaResponse> call, Response<DataBalitaResponse> response) {
                                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                                    Toast.makeText(DataBalitaActivity.this, "Update Foto Berhasil", Toast.LENGTH_SHORT).show();

//                                                    dataShared.setData(DataShared.KEY.ACC_IMAGE, response.body().getImagepath());

                                                    Glide.with(DataBalitaActivity.this)
                                                            .load(ApiClient.PHOTO_URL_ANAK + response.body().getImagepath())
                                                            .placeholder(R.drawable.ic_person1)
                                                            .into(imageView);

                                                } else {
                                                    Toast.makeText(DataBalitaActivity.this, "Update Foto Gagal", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<DataBalitaResponse> call, Throwable t) {
                                                t.printStackTrace();
                                                Toast.makeText(DataBalitaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        DB_floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
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
        nama_anak.setText(dataShared.getData(DataShared.KEY.ACC_NAMA_ANAK));
        tanggal_lahir_anak.setText(dataShared.getData(DataShared.KEY.ACC_TANGGAL_LAHIR_ANAK));
        bb_lahir.setText(dataShared.getData(DataShared.KEY.ACC_BB_LAHIR));
        tb_lahir.setText(dataShared.getData(DataShared.KEY.ACC_TB_LAHIR));
        nama_ayah.setText(dataShared.getData(DataShared.KEY.ACC_NAMA_AYAH));
        nama_ibu.setText(dataShared.getData(DataShared.KEY.ACC_NAMA_IBU));

        namaAnak = getIntent().getStringExtra(NAMA_ANAK);
        id = getIntent().getStringExtra(ID_ANAK);


        ApiClient.getClient().create(ApiInterface.class).getDataBalita(namaAnak)
                .enqueue(new Callback<DataBalitaResponse>() {
                    @Override
                    public void onResponse(Call<DataBalitaResponse> call, Response<DataBalitaResponse> response) {
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            ArrayList<DataBalitaModel> list = (ArrayList<DataBalitaModel>) response.body().getData();
                            if (list != null && !list.isEmpty()) {
                                DataBalitaModel data = list.get(0);
                                nama_anak.setText(data.getNama_anak());
                                tanggal_lahir_anak.setText(data.getTanggal_lahir_anak());
                                bb_lahir.setText(data.getBb_lahir());
                                tb_lahir.setText(data.getTb_lahir());
                                nama_ayah.setText(data.getNama_ayah());
                                nama_ibu.setText(dataShared.getData(DataShared.KEY.ACC_NAMA_IBU));

                                Glide.with(DataBalitaActivity.this)
                                        .load(ApiClient.PHOTO_URL_ANAK + data.getImagepath())
                                        .placeholder(R.drawable.ic_person1)
                                        .into(imageView);
                            } else {
                                Toast.makeText(DataBalitaActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DataBalitaActivity.this, "RESPONSE NULL -> " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataBalitaResponse> call, Throwable t) {
                        Toast.makeText(DataBalitaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from EditText fields
                String namaanak = nama_anak.getText().toString();
                String tanggallahiranak = tanggal_lahir_anak.getText().toString();
                String jeniskelamin = jenis_kelamin.getSelectedItem().toString();
                String bblahir = bb_lahir.getText().toString();
                String tblahir = tb_lahir.getText().toString();
                String namaayah = nama_ayah.getText().toString();
                String namaibu = nama_ibu.getText().toString();

                // Make Retrofit call to edit data
                ApiClient.getClient().create(ApiInterface.class)
                        .editDataBalita(id, namaanak, tanggallahiranak, jeniskelamin, bblahir, tblahir, namaayah, namaibu, nikIbu)
                        .enqueue(new Callback<DataBalitaUpdateRespon>() {
                            @Override
                            public void onResponse(Call<DataBalitaUpdateRespon> call, Response<DataBalitaUpdateRespon> response) {
                                if (response.body().getStatus().equalsIgnoreCase("success")) {
                                    Toast.makeText(DataBalitaActivity.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();

                                    // Update data in SharedPreferences
//                                    dataShared.setData(DataShared.KEY.ACC_NAMA_ANAK, namaanak);
//                                    dataShared.setData(DataShared.KEY.ACC_TANGGAL_LAHIR_ANAK, tanggallahiranak);
//                                    dataShared.setData(DataShared.KEY.ACC_JENIS_KELAMIN, jeniskelamin);
//                                    dataShared.setData(DataShared.KEY.ACC_BB_LAHIR, bblahir);
//                                    dataShared.setData(DataShared.KEY.ACC_TB_LAHIR, tblahir);
//                                    dataShared.setData(DataShared.KEY.ACC_NAMA_AYAH, namaayah);
//                                    dataShared.setData(DataShared.KEY.ACC_NAMA_IBU, namaibu);
                                } else {
                                    Toast.makeText(DataBalitaActivity.this, "Data gagal diedit", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DataBalitaUpdateRespon> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(DataBalitaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("error", t.getMessage());
                            }
                        });
            }
        });


        ApiClient.getClient().create(ApiInterface.class).getPhoto(
                getIntent().getStringExtra(NAMA_ANAK)
        ).enqueue(new Callback<DataBalitaResponse>() {
            @Override
            public void onResponse(Call<DataBalitaResponse> call, Response<DataBalitaResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("true")) {

                    Glide.with(DataBalitaActivity.this)
                            .load(response.body().getImagepath())
                            .placeholder(R.drawable.ic_person1)
                            .into(imageView);
                }
            }

            @Override
            public void onFailure(Call<DataBalitaResponse> call, Throwable t) {

            }
        });


    }

    private class UploadImageTask extends AsyncTask<Uri, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Uri... uris) {
            Uri selectedImageUri = uris[0];
            try {

                URL url = new URL("http://172.17.202.140/SiDuta/SiDutaMobile/databalita.php");
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

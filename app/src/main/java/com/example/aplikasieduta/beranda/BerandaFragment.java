package com.example.aplikasieduta.beranda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

//import com.bdtopcoder.smart_slider.SliderAdapter;
//import com.bdtopcoder.smart_slider.SliderItem;
import com.bumptech.glide.Glide;
import com.example.aplikasieduta.AkunActivity;
import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.artikel.ArtikelModel;
import com.example.aplikasieduta.artikel.ArtikelResponse;
import com.example.aplikasieduta.databalita.DataBalitaActivity;
import com.example.aplikasieduta.model.login.LoginActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.SessionManager;
import com.example.aplikasieduta.databalita.DataBalitaModel;
import com.example.aplikasieduta.databalita.DataBalitaResponse;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaFragment extends Fragment {

    SessionManager sessionManager;
    TextView B_txt_5, judulArtikel;
    ImageButton imunisasi;
    String nama_ibu;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private DataShared dataShared;
    private ArrayList<DataBalitaModel> databalitaList;
    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    private String urlimage;
    private String dataId;
    ImageView gambarArtikel;

    private void geturlimage() {
        ApiClient retrofit = new ApiClient();
        String link = retrofit.ip;
        link = urlimage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        imageView = rootView.findViewById(R.id.btn_akun);
        dataShared = new DataShared(requireActivity());

        CardView cardImg = rootView.findViewById(R.id.card_image);

        cardImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buka = new Intent(getContext(), AkunActivity.class);
                startActivity(buka);
            }
        });

        Glide.with(BerandaFragment.this)
                .load(ApiClient.PHOTO_URL + dataShared.getData(DataShared.KEY.ACC_IMAGE))
                .placeholder(R.drawable.ic_person1)
                .into(imageView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefbalita", Context.MODE_PRIVATE);

        ImageView akun = rootView.findViewById(R.id.btn_akun);
        akun.setOnClickListener(v -> {
            Intent buka = new Intent(getContext(), AkunActivity.class);
            startActivity(buka);
        });

        recyclerView = rootView.findViewById(R.id.B_recyclerview);
        databalitaList = new ArrayList<>();

        ApiClient.getClient().create(ApiInterface.class)
                .listData(dataShared.getData(DataShared.KEY.ACC_NIK_IBU))
                .enqueue(new Callback<DataBalitaResponse>() {
                    @Override
                    public void onResponse(Call<DataBalitaResponse> call, Response<DataBalitaResponse> response) {

                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            databalitaList = response.body().getData();
                            setRecyclerView();
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<DataBalitaResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namabalita = sharedPreferences.getString("namabalita", "");
                Intent pindah = new Intent(getActivity(), DataBalitaActivity.class);
                pindah.putExtra("namabalita", namabalita);
                startActivity(pindah);
            }
        });

        viewPager2 = rootView.findViewById(R.id.viewpager);
        int[] images = {R.drawable.gambarartikel1, R.drawable.gambarartikel2, R.drawable.gambarartikel3};
        String[] judulartikel = {getString(R.string.artikel_1),
                getString(R.string.artikel_2),
                getString(R.string.artikel_3)};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {

            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], judulartikel[i]);
            viewPagerItemArrayList.add(viewPagerItem);

        }

        ApiClient.getClient().create(ApiInterface.class).artikel().enqueue(new Callback<ArtikelResponse>() {
            @Override
            public void onResponse(Call<ArtikelResponse> call, Response<ArtikelResponse> response) {
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(response.body().getData());

                    viewPager2.setAdapter(viewPagerAdapter);
                    viewPager2.setClipToPadding(false);
                    viewPager2.setClipChildren(false);
                    viewPager2.setOffscreenPageLimit(2);
                    viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

                }
            }

            @Override
            public void onFailure(Call<ArtikelResponse> call, Throwable t) {

            }
        });

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArtikelResponse> call = apiInterface.detailartikel(dataId);

        call.enqueue(new Callback<ArtikelResponse>() {
            @Override
            public void onResponse(Call<ArtikelResponse> call, Response<ArtikelResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    ArtikelModel model = response.body().getData().get(0);

                    geturlimage();

                    // Menggunakan Glide untuk memuat gambar
                    Glide.with(requireContext())
                            .load("https://si-duta.tifnganjuk.com/forms/berkas/" + model.img_path)
                            .into(gambarArtikel);

                    judulArtikel.setText(model.judul_artikel);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ArtikelResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        imunisasi = rootView.findViewById(R.id.B_img_1);
        imunisasi.setOnClickListener(v -> {
            Intent pindah = new Intent(getActivity(), BerandaActivity.class)
                    .putExtra(BerandaActivity.FRAGMENT, R.layout.fragment_jadwal);
            startActivity(pindah);
        });

        imunisasi = rootView.findViewById(R.id.B_img_2);
        imunisasi.setOnClickListener(v -> {
            Intent pindah = new Intent(getActivity(), BerandaActivity.class)
                    .putExtra(BerandaActivity.FRAGMENT, R.layout.fragment_laporan);
            startActivity(pindah);
        });


        sessionManager = new SessionManager(requireContext());
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        }

        B_txt_5 = rootView.findViewById(R.id.B_txt_5);

        nama_ibu = sessionManager.getUserDetail().get(SessionManager.NAMA_IBU);

        B_txt_5.setText("Halo Bu " + nama_ibu + " !");

        return rootView;
    }

    private void moveToLogin() {
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        requireActivity().finish();
    }

    private void setRecyclerView() {

        if (databalitaList.size() > 0) {
            BerandaFragmentAdapter adapter = new BerandaFragmentAdapter(databalitaList, getContext(),
                    new BerandaFragmentAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {

                            startActivity(new
                                    Intent(requireContext(), DataBalitaActivity.class)
                                    .putExtra(DataBalitaActivity.ID_ANAK, "" + databalitaList.get(position).getId_anak())
                                    .putExtra(DataBalitaActivity.NAMA_ANAK, databalitaList.get(position).getNama_anak())
                            );
                        }
                    });

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(requireContext(), "Data Anak Kosong", Toast.LENGTH_SHORT).show();
        }

    }
}

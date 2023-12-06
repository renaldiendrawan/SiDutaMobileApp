package com.example.aplikasieduta.laporanfragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.aplikasieduta.R;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanImunisasiFragment extends Fragment {
    public static final String URLSELECT = "http://172.16.106.151/SiDutaMobile/selectjadwalimunisasi.php";

    RecyclerView recyclerView;
    List<LaporanImunisasiModel> itemList = new ArrayList<LaporanImunisasiModel>();
    List<LaporanImunisasiModel> filteredItems = new ArrayList<>();
    LaporanImunisasiAdapter laporanImunisasiAdapter;
    String nama_anak, umur, tanggal_imunisasi, jenis_imunisasi;
    FloatingActionButton fab;
    View view;
    private DataShared dataShared;

    public LaporanImunisasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_laporan_imunisasi, container, false);

        dataShared = new DataShared(requireContext());

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LaporanImunisasiResponse> call = apiInterface.ambillaporanimunisasi(dataShared.getData(DataShared.KEY.ACC_NIK_IBU).toString());

        call.enqueue(new Callback<LaporanImunisasiResponse>() {
            @Override
            public void onResponse(Call<LaporanImunisasiResponse> call, Response<LaporanImunisasiResponse> response) {
                LaporanImunisasiResponse respon = response.body();
                if (response.isSuccessful()) {

                    if (respon.isSuccess()) {
                        itemList.addAll(respon.getData());
                        setRecyclerView();
                    } else {
                        Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LaporanImunisasiResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void filterData(String selectedNama) {
        if (laporanImunisasiAdapter != null) {
            List<LaporanImunisasiModel> filteredList = new ArrayList<>();

            for (LaporanImunisasiModel model : itemList) {
                if (model.getNama_anak().equalsIgnoreCase(selectedNama)) {
                    filteredList.add(model);
                }
            }

            // Set the filtered data to the adapter
            laporanImunisasiAdapter.setFilteredList(filteredList);
        }
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recyclerview_laporanimunisasi);
        laporanImunisasiAdapter = new LaporanImunisasiAdapter(filteredItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(laporanImunisasiAdapter);// Set the adapter to the RecyclerView here
    }
}

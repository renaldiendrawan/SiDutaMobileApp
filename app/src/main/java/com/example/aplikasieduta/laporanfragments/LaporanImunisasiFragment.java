package com.example.aplikasieduta.laporanfragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        Call<LaporanImunisasiResponse> call = apiInterface.ambillaporanimunisasi(dataShared.getData(DataShared.KEY.ACC_ID_IBU));

        call.enqueue(new Callback<LaporanImunisasiResponse>() {
            @Override
            public void onResponse(Call<LaporanImunisasiResponse> call, Response<LaporanImunisasiResponse> response) {
                LaporanImunisasiResponse respon = response.body();
//                Toast.makeText(getContext(), respon.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    if (respon != null && respon.isSuccess() == true) {
                        ArrayList<LaporanImunisasiModel> list = respon.getData();
                        if (list !=null && !list.isEmpty()) {
                            LaporanImunisasiModel model = response.body().getData().get(0);
                            itemList.addAll(list);
                            itemList = list;
                            setRecyclerView();
                        }
                    } else if (respon.isSuccess() == false) {
                        Toast.makeText(getContext(), "kosong", Toast.LENGTH_SHORT).show();
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

    private void setRecyclerView(){
        recyclerView = view.findViewById(R.id.recyclerview_laporanimunisasi);
        laporanImunisasiAdapter = new LaporanImunisasiAdapter(itemList, this);
        Context context = requireContext();
// Initialize the LinearLayoutManager with the Context
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
// Now, you can set this layout manager to your RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_laporanimunisasi);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(laporanImunisasiAdapter);
    }
}
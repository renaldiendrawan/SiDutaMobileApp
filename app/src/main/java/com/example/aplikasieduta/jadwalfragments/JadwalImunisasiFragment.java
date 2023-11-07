package com.example.aplikasieduta.jadwalfragments;

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
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalImunisasiFragment extends Fragment {
    public static final String URLSELECT = "http://172.17.202.22/SiDutaMobile/selectjadwalimunisasi.php";

    RecyclerView recyclerView;
    List<JadwalImunisasiModel> itemList = new ArrayList<JadwalImunisasiModel>();
    JadwalImunisasiAdapter jadwalImunisasiAdapter;
    String tanggal_posyandu, jam_posyandu, tempat_posyandu, jenis_imunisasi;
    FloatingActionButton fab;
    View view;

    public JadwalImunisasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jadwal_imunisasi, container, false);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JadwalImunisasiResponse> call = apiInterface.ambiljadwalimunisasi();

        call.enqueue(new Callback<JadwalImunisasiResponse>() {
            @Override
            public void onResponse(Call<JadwalImunisasiResponse> call, Response<JadwalImunisasiResponse> response) {
                JadwalImunisasiResponse respon = response.body();
//                Toast.makeText(getContext(), respon.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    if (respon != null && respon.isSuccess() == true) {
                        ArrayList<JadwalImunisasiModel> list = respon.getData();
                        if (list !=null && !list.isEmpty()) {
                            JadwalImunisasiModel model = response.body().getData().get(0);
                            itemList.addAll(list);
                            setRecyclerView();
                        }
                    } else if (respon.isSuccess() == false) {
                        Toast.makeText(getContext(), respon.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "DATA KOSONG", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JadwalImunisasiResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    private void setRecyclerView(){
        recyclerView = view.findViewById(R.id.recyclerview_jadwalimunisasi);
        JadwalImunisasiAdapter jadwalImunisasiAdapter1 = new JadwalImunisasiAdapter(itemList, this);
//        Context context = requireContext();
// Initialize the LinearLayoutManager with the Context
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
// Now, you can set this layout manager to your RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_jadwalimunisasi);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(jadwalImunisasiAdapter1);
    }

}
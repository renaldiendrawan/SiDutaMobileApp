package com.example.aplikasieduta.jadwalfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class JadwalImunisasiFragment extends Fragment {
    public static final String URLSELECT = "http://si-duta.tifnganjuk.com/SiDutaMobile/selectjadwalimunisasi.php";

    RecyclerView recyclerView;
    List<JadwalImunisasiModel> itemList = new ArrayList<>();
    JadwalImunisasiAdapter jadwalImunisasiAdapter;
    View view;

    public JadwalImunisasiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jadwal_imunisasi, container, false);

        DataShared dataShared = new DataShared(requireActivity());

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        System.out.println("tes data = " + dataShared.getData(DataShared.KEY.ACC_NIK_IBU));
        Call<JadwalImunisasiResponse> call = apiInterface.ambiljadwalimunisasi(dataShared.getData(DataShared.KEY.ACC_NIK_IBU));

        call.enqueue(new Callback<JadwalImunisasiResponse>() {
            @Override
            public void onResponse(Call<JadwalImunisasiResponse> call, Response<JadwalImunisasiResponse> response) {
                JadwalImunisasiResponse respon = response.body();


                if (response.isSuccessful() && respon != null) {
                    if (respon.isSuccess()) {
                        itemList.addAll(respon.getData());
                        setRecyclerView();
                        System.out.println("apakah kesini");
                    } else {
                        Toast.makeText(requireContext(), respon.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JadwalImunisasiResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recyclerview_jadwalimunisasi);
        jadwalImunisasiAdapter = new JadwalImunisasiAdapter(itemList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(jadwalImunisasiAdapter);
    }
}

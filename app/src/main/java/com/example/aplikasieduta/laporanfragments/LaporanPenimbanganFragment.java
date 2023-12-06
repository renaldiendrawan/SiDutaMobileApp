package com.example.aplikasieduta.laporanfragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplikasieduta.R;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganAdapter;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganModel;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganResponse;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanPenimbanganFragment extends Fragment {

    public static final String URLSELECT = "http://172.16.106.151/SiDutaMobile/selectlaporanpenimbangan.php";

    RecyclerView recyclerView;
    List<LaporanPenimbanganModel> itemList = new ArrayList<LaporanPenimbanganModel>();
    List<LaporanPenimbanganModel> filteredItems = new ArrayList<>();
    LaporanPenimbanganAdapter laporanPenimbanganAdapter;
    String nama_anak, umur, tgl_penimbangan, berat_badan, tinggi_badan;
    FloatingActionButton fab;
    View view;

    private DataShared dataShared;

    public LaporanPenimbanganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_laporan_imunisasi, container, false);

        dataShared = new DataShared(requireContext());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LaporanPenimbanganResponse> call = apiInterface.ambillaporanpenimbangan(dataShared.getData(DataShared.KEY.ACC_NIK_IBU).toString());

        call.enqueue(new Callback<LaporanPenimbanganResponse>() {
            @Override
            public void onResponse(Call<LaporanPenimbanganResponse> call, Response<LaporanPenimbanganResponse> response) {
                LaporanPenimbanganResponse respon = response.body();

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
            public void onFailure(Call<LaporanPenimbanganResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void filterData(String selectedNama) {
        if (laporanPenimbanganAdapter != null) {
            List<LaporanPenimbanganModel> filteredList = new ArrayList<>();

            for (LaporanPenimbanganModel model : itemList) {
                if (model.getNama_anak().equalsIgnoreCase(selectedNama)) {
                    filteredList.add(model);
                }
            }

            // Set the filtered data to the adapter
            laporanPenimbanganAdapter.setFilteredList(filteredList);
        }
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recyclerview_laporanimunisasi);
        laporanPenimbanganAdapter = new LaporanPenimbanganAdapter(filteredItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(laporanPenimbanganAdapter);// Set the adapter to the RecyclerView here
    }
}

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
        view = inflater.inflate(R.layout.fragment_laporan_penimbangan, container, false);
        dataShared = new DataShared(requireContext());

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LaporanPenimbanganResponse> call = apiInterface.ambillaporanpenimbangan(dataShared.getData(DataShared.KEY.ACC_NIK_IBU));


        call.enqueue(new Callback<LaporanPenimbanganResponse>() {
            @Override
            public void onResponse(Call<LaporanPenimbanganResponse> call, Response<LaporanPenimbanganResponse> response) {
                LaporanPenimbanganResponse respon = response.body();
                if (response.isSuccessful()) {
                    if (respon != null && respon.isSuccess() == true) {
                        ArrayList<LaporanPenimbanganModel> list = respon.getData();
                        if (list !=null && !list.isEmpty()) {
                            LaporanPenimbanganModel model = response.body().getData().get(0);
                            itemList.addAll(list);
                            setRecyclerView();
                        }
                    } else if (respon.isSuccess() == false) {
                        Toast.makeText(getContext(), "kode false", Toast.LENGTH_SHORT).show();
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

    private void setRecyclerView(){
        recyclerView = view.findViewById(R.id.recyclerview_laporanpenimbangan);
        LaporanPenimbanganAdapter laporanPenimbanganAdapter1 = new LaporanPenimbanganAdapter(itemList, this);
        Context context = requireContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_laporanpenimbangan);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(laporanPenimbanganAdapter1);
    }
}
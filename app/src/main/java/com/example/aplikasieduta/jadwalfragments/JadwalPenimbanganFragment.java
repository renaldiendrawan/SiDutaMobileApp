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

public class JadwalPenimbanganFragment extends Fragment {
    public static final String URLSELECT = "http://si-suta.tifnganjuk.com/SiDutaMobile/selectjadwalpenimbangan.php";

    RecyclerView recyclerView;
    List<JadwalPenimbanganModel> itemList = new ArrayList<>();
    JadwalPenimbanganAdapter jadwalPenimbanganAdapter;
    View view;

    public JadwalPenimbanganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jadwal_penimbangan, container, false);

        DataShared dataShared = new DataShared(requireContext());

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JadwalPenimbanganResponse> call = apiInterface.ambiljadwalpenimbangan("11");

        call.enqueue(new Callback<JadwalPenimbanganResponse>() {
            @Override
            public void onResponse(Call<JadwalPenimbanganResponse> call, Response<JadwalPenimbanganResponse> response) {
                JadwalPenimbanganResponse respon = response.body();

                if (response.isSuccessful() && respon != null) {
                    if (respon.isSuccess()) {
                        itemList.addAll(respon.getData());
                        setRecyclerView();
                    } else {
                        Toast.makeText(getContext(), "Kode False", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JadwalPenimbanganResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.recyclerview_jadwalpenimbangan);
        jadwalPenimbanganAdapter = new JadwalPenimbanganAdapter(itemList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(jadwalPenimbanganAdapter);
    }
}

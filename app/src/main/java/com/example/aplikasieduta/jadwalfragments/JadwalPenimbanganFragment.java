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

public class JadwalPenimbanganFragment extends Fragment {
    public static final String URLSELECT = "http://172.17.202.22/SiDutaMobile/selectjadwalpenimbangan.php";

    RecyclerView recyclerView;
    List<JadwalPenimbanganModel> itemList = new ArrayList<JadwalPenimbanganModel>();
    JadwalPenimbanganAdapter jadwalPenimbanganAdapter;
    String tanggal_posyandu, jam_posyandu, tempat_posyandu;
    FloatingActionButton fab;
    View view;

    public JadwalPenimbanganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jadwal_penimbangan, container, false);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JadwalPenimbanganResponse> call = apiInterface.ambiljadwalpenimbangan();

        call.enqueue(new Callback<JadwalPenimbanganResponse>() {
            @Override
            public void onResponse(Call<JadwalPenimbanganResponse> call, Response<JadwalPenimbanganResponse> response) {
                JadwalPenimbanganResponse respon = response.body();
                if (response.isSuccessful()) {
                    if (respon != null && respon.isSuccess() == true) {
                        ArrayList<JadwalPenimbanganModel> list = respon.getData();
                        if (list !=null && !list.isEmpty()) {
                            JadwalPenimbanganModel model = response.body().getData().get(0);
                            itemList.addAll(list);
                            setRecyclerView();
                        }
                    } else if (respon.isSuccess() == false) {
                        Toast.makeText(getContext(), "kode false", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "DATA KOSONG", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JadwalPenimbanganResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

 private void setRecyclerView(){
     recyclerView = view.findViewById(R.id.recyclerview_jadwalpenimbangan);
     JadwalPenimbanganAdapter jadwalPenimbanganAdapter1 = new JadwalPenimbanganAdapter(itemList, this);
//     Context context = requireContext();
// Initialize the LinearLayoutManager with the Context
     LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
// Now, you can set this layout manager to your RecyclerView
     RecyclerView recyclerView = view.findViewById(R.id.recyclerview_jadwalpenimbangan);
     recyclerView.setLayoutManager(layoutManager);
     recyclerView.setAdapter(jadwalPenimbanganAdapter1);
 }

}
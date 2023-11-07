package com.example.aplikasieduta.beranda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.bumptech.glide.Glide;
import com.example.aplikasieduta.AkunActivity;
import com.example.aplikasieduta.BerandaActivity;
import com.example.aplikasieduta.DataBalitaActivity;
import com.example.aplikasieduta.LoginActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.SessionManager;
import com.example.aplikasieduta.model.login.Login;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaFragment extends Fragment {

    SessionManager sessionManager;
    TextView B_txt_5;
    ImageButton imunisasi;
    String nama_ibu;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private DataShared dataShared;
    private List<BerandaFragmentModel> databalitaList;

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

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BerandaFragmentResponse> call = apiInterface.ambildatabalita();
        call.enqueue(new Callback<BerandaFragmentResponse>() {
            @Override
            public void onResponse(Call<BerandaFragmentResponse> call, Response<BerandaFragmentResponse> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isSuccess()) {
                    BerandaFragmentResponse respon = response.body();
                    ArrayList<BerandaFragmentModel> dataList = respon.getData();

                    BerandaFragmentModel model =response.body().getData().get(0);
                    databalitaList.addAll(dataList);
                    setRecyclerView();

                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BerandaFragmentResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Tambahkan data jadwal imunisasi ke databalitaList
//        databalitaList.add(new BerandaFragmentModel("Harry Maguire", "3 Tahun 1 Bulan", "Laki-laki"));
//        databalitaList.add(new BerandaFragmentModel("Michie", "1 Tahun 0 Bulan", "Perempuan"));

        // Tambahkan data lainnya sesuai kebutuhan


        // Ganti LinearLayoutManager dengan LinearLayoutManager dengan orientasi horizontal


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namabalita = sharedPreferences.getString("namabalita", "");
//            Toast.makeText(getContext(), namabalita, Toast.LENGTH_SHORT).show();
                Intent pindah = new Intent(getActivity(), DataBalitaActivity.class);
                pindah.putExtra("namabalita", namabalita);
                startActivity(pindah);
            }
        } );

        ViewPager2 viewPager2 = rootView.findViewById(R.id.B_smartSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.contohartikel, "artikel 1"));
        sliderItems.add(new SliderItem(R.drawable.gambarartikel2, "artikel 2"));
        sliderItems.add(new SliderItem(R.drawable.gambarartikel3, "artikel 3"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2, 3000));

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                SliderItem item = sliderItems.get(position);
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

    private void setRecyclerView(){
        BerandaFragmentAdapter adapter = new BerandaFragmentAdapter(databalitaList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

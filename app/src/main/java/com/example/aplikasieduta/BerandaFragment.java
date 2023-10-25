package com.example.aplikasieduta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import java.util.ArrayList;
import java.util.List;

public class BerandaFragment extends Fragment {

    SessionManager sessionManager;
    TextView B_txt_5;
    ImageButton imunisasi;
    String nama_ibu;
    private RecyclerView recyclerView;
    private BerandaFragmentAdapter adapter;
    private List<BerandaFragmentModel> databalitaList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        ImageButton akun = rootView.findViewById(R.id.btn_akun);
        akun.setOnClickListener(v -> {
            Intent buka = new Intent(getContext(), AkunActivity.class);
            startActivity(buka);
        });

        recyclerView = rootView.findViewById(R.id.B_recyclerview);
        databalitaList = new ArrayList<>();

        // Tambahkan data jadwal imunisasi ke databalitaList
        databalitaList.add(new BerandaFragmentModel("Cathy", "0 Tahun 6 Bulan", "Perempuan"));
        databalitaList.add(new BerandaFragmentModel("Harry", "1 Tahun 2 Bulan", "Laki-laki"));
        databalitaList.add(new BerandaFragmentModel("Michie", "0 Tahun 9 Bulan", "Perempuan"));

        // Tambahkan data lainnya sesuai kebutuhan
        adapter = new BerandaFragmentAdapter(databalitaList);

        // Ganti LinearLayoutManager dengan LinearLayoutManager dengan orientasi horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ViewPager2 viewPager2 = rootView.findViewById(R.id.B_smartSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.gambarartikel1, "artikel 1"));
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
}

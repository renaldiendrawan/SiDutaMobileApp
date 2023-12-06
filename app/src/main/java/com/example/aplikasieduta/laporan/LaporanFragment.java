package com.example.aplikasieduta.laporan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aplikasieduta.R;
import com.example.aplikasieduta.laporanfragments.LaporanImunisasiFragment;
import com.example.aplikasieduta.laporanfragments.LaporanImunisasiModel;
import com.example.aplikasieduta.laporanfragments.LaporanImunisasiResponse;
import com.example.aplikasieduta.laporanfragments.LaporanPenimbanganFragment;
import com.example.aplikasieduta.profilakun.DataShared;
import com.example.aplikasieduta.retrofit.ApiClient;
import com.example.aplikasieduta.retrofit.ApiInterface;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter2 myViewPagerAdapter;
    String[] items = {"Anak 1", "Anak 2"};
    AutoCompleteTextView laporan_autocompletetxt;
    ArrayAdapter<String> adapteritems;

    public LaporanFragment() {
        // require an empty public constructor
    }

    private void handleItemSelected(int position) {
        String selectedNama = items[position]; // Gantilah ini dengan sumber data yang sesuai
        ((LaporanImunisasiFragment) myViewPagerAdapter.getFragment(0)).filterData(selectedNama);
        ((LaporanPenimbanganFragment) myViewPagerAdapter.getFragment(1)).filterData(selectedNama);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laporan, container, false);

        tabLayout = view.findViewById(R.id.LP_tablayout);
        viewPager2 = view.findViewById(R.id.LP_viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter2(this);
        viewPager2.setAdapter(myViewPagerAdapter);
        Spinner spinner = view.findViewById(R.id.laporan_pilihananak);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                handleItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spinner.setSelection(0);
                handleItemSelected(0);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

                TabLayout tabLayout;
                if (getActivity() != null) {
                    tabLayout = getActivity().findViewById(R.id.J_tablayout);
                    // Lakukan sesuatu dengan TabLayout, seperti memilih tab
                    if (tabLayout != null) {
                        TabLayout.Tab tabToSelect = tabLayout.getTabAt(1); // Misalnya, memilih tab pertama
                        if (tabToSelect != null) {
                            tabToSelect.select();
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
                Fragment selectedFragment = myViewPagerAdapter.getFragment(position);

                // Now you can do something with the selectedFragment
                if (selectedFragment instanceof LaporanImunisasiFragment || selectedFragment instanceof LaporanPenimbanganFragment) {
                    String selectedNama = (String) spinner.getSelectedItem();

                    if (selectedNama != null) {
                        ((LaporanImunisasiFragment) myViewPagerAdapter.getFragment(0)).filterData(selectedNama);
                        ((LaporanPenimbanganFragment) myViewPagerAdapter.getFragment(1)).filterData(selectedNama);
                    }
                }
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mengambil Nik akun yang login
        DataShared dataShared = new DataShared(requireActivity());
        String nikAkun = dataShared.getData(DataShared.KEY.ACC_NIK_IBU);

        // Menggunakan Nik untuk mendapatkan pilihan anak
        ApiClient.getClient().create(ApiInterface.class).ambillaporanimunisasi(nikAkun).enqueue(new Callback<LaporanImunisasiResponse>() {
            @Override
            public void onResponse(Call<LaporanImunisasiResponse> call, Response<LaporanImunisasiResponse> response) {
                ArrayList<String> nama = new ArrayList<>();

                assert response.body() != null;
                for (LaporanImunisasiModel model : response.body().getData()) {
                    nama.add(model.getNama_anak());
                }

                if (isAdded()) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, nama);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    Spinner spinner = view.findViewById(R.id.laporan_pilihananak);
                    spinner.setAdapter(adapter);

                    spinner.setSelection(0);

                    // Set listener untuk memfilter data pada kedua fragment saat pilihan anak berubah
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            String selectedNama = parentView.getItemAtPosition(position).toString();
                            ((LaporanImunisasiFragment) myViewPagerAdapter.getFragment(0)).filterData(selectedNama);
                            ((LaporanPenimbanganFragment) myViewPagerAdapter.getFragment(1)).filterData(selectedNama);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // Do nothing here
                            spinner.setSelection(0);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<LaporanImunisasiResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package com.example.aplikasieduta.jadwalfragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class JadwalPenimbanganAdapter extends RecyclerView.Adapter<JadwalPenimbanganAdapter.RecycleViewHolder> {


    List<JadwalPenimbanganModel> items;

//    public JadwalPenimbanganAdapter(ArrayList<JadwalPenimbanganModel> items){this.items = items;}

    public JadwalPenimbanganAdapter(List<JadwalPenimbanganModel> itemList, JadwalPenimbanganFragment jadwalPenimbanganFragment) {
        this.items = itemList;
    }

    @NonNull
    @Override
    public JadwalPenimbanganAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_penimbangan, parent, false);
                return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalPenimbanganAdapter.RecycleViewHolder holder, int position) {
        JadwalPenimbanganModel jadwal = items.get(position);
        holder.tanggal.setText(items.get(position).getTgl_penimbangan());
        holder.jam.setText(items.get(position).getJam_penimbangan());
        holder.tempat.setText(items.get(position).getTempat_penimbangan());

    }

    @Override
    public int getItemCount() {
        return (items != null)? items.size():0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
         private TextView tanggal, jam, tempat;

         public RecycleViewHolder(View view){
             super(view);
             tanggal = view.findViewById(R.id.JP_texttanggal);
             jam = view.findViewById(R.id.JP_textjam);
             tempat = view.findViewById(R.id.JP_texttempat);

         }
    }
}

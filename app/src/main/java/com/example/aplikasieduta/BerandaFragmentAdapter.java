package com.example.aplikasieduta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager; // Import LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BerandaFragmentAdapter extends RecyclerView.Adapter<BerandaFragmentAdapter.ViewHolder> {

    private List<BerandaFragmentModel> databalitaList;

    public BerandaFragmentAdapter(List<BerandaFragmentModel> databalitaList) {
        this.databalitaList = databalitaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beranda_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BerandaFragmentModel databalita = databalitaList.get(position);
        holder.textNama.setText(databalita.getNamabalita());
        holder.textUmur.setText(databalita.getUmurbalita());
        holder.textJenisKelamin.setText(databalita.getJeniskelaminbalita());
    }

    @Override
    public int getItemCount() {
        return databalitaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNama, textUmur, textJenisKelamin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.DB_namabalita);
            textUmur = itemView.findViewById(R.id.DB_umur);
            textJenisKelamin = itemView.findViewById(R.id.DB_jeniskelamin);
        }
    }
}


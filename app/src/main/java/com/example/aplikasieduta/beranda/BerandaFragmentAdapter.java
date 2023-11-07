package com.example.aplikasieduta.beranda;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;

import java.util.List;

public class BerandaFragmentAdapter extends RecyclerView.Adapter<BerandaFragmentAdapter.ViewHolder> {

    private static View.OnClickListener clickListener;
    private List<BerandaFragmentModel> databalitaList;
    SharedPreferences sharedPreferences;

    public static void setClickListener(View.OnClickListener listener){
        clickListener = listener;
    }

    public BerandaFragmentAdapter(List<BerandaFragmentModel> databalitaList, BerandaFragment context){
    // this.sharedPreferences = context.getActivity().getSharedPreferences("prefbalita", Context.MODE_PRIVATE);
        this.databalitaList = databalitaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beranda_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BerandaFragmentModel databalita = databalitaList.get(position);
        holder.textNama.setText(databalita.getNama_anak());
        holder.textUmur.setText(databalita.getUsia());
        holder.textJenisKelamin.setText(databalita.getJenis_kelamin());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    int position = holder.getPosition();
                    if (position != RecyclerView.NO_POSITION){
                        BerandaFragmentModel clickeditem = databalitaList.get(position);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("namabalita", clickeditem.getNama_anak());
                        editor.apply();
                        clickListener.onClick(v);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (databalitaList != null) ? databalitaList.size() :0;
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


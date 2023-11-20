package com.example.aplikasieduta.beranda;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplikasieduta.artikel.ArtikelActivity;
import com.example.aplikasieduta.R;
import com.example.aplikasieduta.artikel.ArtikelModel;
import com.example.aplikasieduta.retrofit.ApiClient;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    ArrayList<ArtikelModel> viewPagerItemArrayList;

    public ViewPagerAdapter(ArrayList<ArtikelModel> viewPagerItemArrayList) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ArtikelModel viewPagerItem = viewPagerItemArrayList.get(position);

//        holder.imageView.setImageResource(viewPagerItem.imageID);
        holder.judulartikel.setText(viewPagerItem.judul_artikel);

        Glide.with(holder.itemView)
                .load(ApiClient.PHOTO_URL_ARTIKEL + viewPagerItem.getImg_artikel() + viewPagerItem.img_artikel)
                .placeholder(R.drawable.ic_person1)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buka = new Intent(v.getContext(), ArtikelActivity.class);
                buka.putExtra(ArtikelActivity.JUDUL, viewPagerItem.judul_artikel);
                v.getContext().startActivity(buka);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView judulartikel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.gambarartikel);
            judulartikel = itemView.findViewById(R.id.judulartikel);

        }
    }

}

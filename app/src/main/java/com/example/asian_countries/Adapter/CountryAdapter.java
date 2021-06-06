package com.example.asian_countries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asian_countries.Model.AllCountriesModel.CountryInfo;
import com.example.asian_countries.R;

import java.text.BreakIterator;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.Holder>{

    Context context;
    List<CountryInfo> countryInfoList;

    public CountryAdapter(Context context, List<CountryInfo> countryInfoList) {
        this.context = context;
        this.countryInfoList = countryInfoList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.countries_list, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String flag = countryInfoList.get(position).getFlag();
        String name = countryInfoList.get(position).getName();

        holder.countryName.setText(name);
        Glide.with(context).load(flag).into(holder.countryFlag);
   }

    @Override
    public int getItemCount() {
        return countryInfoList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView countryName;
        ImageView countryFlag;

        public Holder(@NonNull View itemView) {
            super(itemView);

            countryFlag = itemView.findViewById(R.id.countryFlag);
            countryName = itemView.findViewById(R.id.countryName);

        }
    }
}

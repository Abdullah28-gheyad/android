package com.example.covid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.covid.DetailActivity2;
import com.example.covid.Model.AllcountriesModel.CountryInfo;
import com.example.covid.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.Holder> implements Filterable {
    Context context  ;
    List<CountryInfo> countryList;
    List<CountryInfo> countryListfilter;

    public CountryAdapter(Context context, List<CountryInfo> countryList) {
        this.context = context;
        this.countryList = countryList;
        this.countryListfilter=  countryList ;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.countries_list,parent,false);
        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        String flag = countryListfilter.get(position).getFlag() ;
        String name = countryListfilter.get(position).getName() ;
        holder.countryname.setText(name);
        Glide.with(context).load(flag).into(holder.countryflag);

    }


    @Override
    public int getItemCount() {
        return countryListfilter.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequence = constraint.toString() ;
                if (charSequence.isEmpty())
                {
                    countryListfilter = countryList ;
                }
                else
                {
                    List<CountryInfo> filterlist = new ArrayList<>() ;
                    for (CountryInfo row:countryList)
                    {
                        if (row.getName().toLowerCase().contains(charSequence.toLowerCase()))
                        {
                            filterlist.add(row) ;
                        }
                    }
                    countryListfilter = filterlist ;
                }

                FilterResults filterResults = new FilterResults() ;
                filterResults.values = countryListfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryListfilter =(ArrayList<CountryInfo>)results.values ;
                notifyDataSetChanged();
            }
        };
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView countryflag;
        TextView countryname ;
        public Holder(@NonNull View itemView) {
            super(itemView);
            countryflag = itemView.findViewById(R.id.country_flag) ;
            countryname = itemView.findViewById(R.id.Country_name) ;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context , DetailActivity2.class) ;
            intent.putExtra("countryName" , countryListfilter.get(getAdapterPosition()).getName()) ;
            context.startActivity(intent);
        }
    }
}

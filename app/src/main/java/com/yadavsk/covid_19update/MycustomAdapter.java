package com.yadavsk.covid_19update;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MycustomAdapter extends ArrayAdapter<Country_model> {

    private Context context;
    private List<Country_model> country_modelList;
    private List<Country_model> country_modelListFiltered;


    public MycustomAdapter(Context context, List<Country_model> country_modelList) {
        super(context, R.layout.list_custom_item,country_modelList);


        this.context = context;
        this.country_modelList = country_modelList;
        this.country_modelListFiltered = country_modelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item, null,true);
        TextView tvCountrynName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        tvCountrynName.setText(country_modelListFiltered.get(position).getCountry());

        Glide.with(context).load(country_modelListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {

        return country_modelListFiltered.size();
    }

    @Nullable
    @Override
    public Country_model getItem(int position) {
        return country_modelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.count = country_modelList.size();
                    filterResults.values = country_modelList;
                } else {

                    List<Country_model> resultModal = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for (Country_model itemModal : country_modelList) {
                        if (itemModal.getCountry().toLowerCase().contains(searchStr)) {
                            resultModal.add(itemModal);
                        }
                        filterResults.count = resultModal.size();

                        filterResults.values = resultModal;
                    }
                }

                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    country_modelListFiltered = (List<Country_model>) filterResults.values;
                    AffectedCountries.country_modelsList = (List<Country_model>) filterResults.values;
                    notifyDataSetChanged();
                }

            };
        return filter;
        }
    }

























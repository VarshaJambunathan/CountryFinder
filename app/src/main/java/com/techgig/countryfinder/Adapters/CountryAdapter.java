package com.techgig.countryfinder.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techgig.countryfinder.Beans.Country;
import com.techgig.countryfinder.R;

import java.util.ArrayList;

/**
 * Created by Varsha on 12-11-2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private ArrayList<Country> mCountryNames;

    public CountryAdapter(ArrayList<Country> mCountryNames) {
        this.mCountryNames = mCountryNames;
    }

    @Override
    public final ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mCountryName.setText(mCountryNames.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCountryNames.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mCountryName;
        public ViewHolder(View itemView) {
            super(itemView);

            mCountryName = (TextView) itemView.findViewById(R.id.country_name);
        }
    }
}

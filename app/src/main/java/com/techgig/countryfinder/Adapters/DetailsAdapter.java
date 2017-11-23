package com.techgig.countryfinder.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techgig.countryfinder.Beans.Names;
import com.techgig.countryfinder.R;

import java.util.ArrayList;

/**
 * Created by Varsha on 23-11-2017.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    private ArrayList<Names> mDetailsNames;

    public DetailsAdapter(ArrayList<Names> mDetailsNames) {
        this.mDetailsNames = mDetailsNames;
    }

    @Override
    public final ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String key = mDetailsNames.get(position).getName();
        String value = mDetailsNames.get(position).getFlagUrl();
        holder.mKey.setText(key);
        holder.mValue.setText(value);

        Log.e("Key-Value Adapter", key + " " + value + "\n");

    }

    @Override
    public int getItemCount() {
        return mDetailsNames.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mKey, mValue;
        public ViewHolder(View itemView) {
            super(itemView);

            mKey = itemView.findViewById(R.id.country_key);
            mValue = itemView.findViewById(R.id.country_value);
        }
    }
}



package com.techgig.countryfinder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techgig.countryfinder.Beans.Names;
import com.techgig.countryfinder.DetailsActivity;
import com.techgig.countryfinder.R;

import java.util.ArrayList;

/**
 * Created by Varsha on 12-11-2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private ArrayList<Names> mNames;
    private Context context;

    public CountryAdapter(Context context, ArrayList<Names> mNames) {
        this.mNames = mNames;
        this.context = context;
    }

    @Override
    public final ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.countryName.setText(mNames.get(position).getName());
        //Picasso.with(context).load(mNames.get(position).getFlagUrl()).into(holder.countryIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("EachCountry", mNames.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName;
        public ImageView countryIcon;
        public ViewHolder(View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.country_name);
            countryIcon = itemView.findViewById(R.id.country_icon);
        }
    }

    public void setFilter(ArrayList<Names> newList) {

        mNames = new ArrayList<>();
        mNames.addAll(newList);
        //mNames.add(new Names("AAAAAAAAAAA","hello"));
        notifyDataSetChanged();
    }
}

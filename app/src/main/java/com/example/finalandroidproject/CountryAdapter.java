package com.example.finalandroidproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class CountryAdapter extends ArrayAdapter<Country> {

    private ArrayList<Country> countriesList;
    Context context;

    public CountryAdapter(ArrayList<Country> data, Context context) {
        super(context, R.layout.country_item, data);
        this.countriesList = data;
        this.context = context;
    }

    private static class ViewHolder{
        TextView pCountry, pLink, pSinger, pSong, pPoints, pPosition;
        ImageView pImage;
    }



    @NonNull
    @Override
    public View getView(
            int position,
            @Nullable View convertView,
            @NonNull ViewGroup parent) {

        //Get the data item for this position
        Country country = getItem(position);

        //Check if an existing view is being reused
        ViewHolder viewHolder;

        final View result;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.country_item, parent, false);

            viewHolder.pCountry = convertView.findViewById(R.id.pCountry);
            viewHolder.pLink = convertView.findViewById(R.id.pLink);
            viewHolder.pSinger = convertView.findViewById(R.id.pSinger);
            viewHolder.pSong = convertView.findViewById(R.id.pSong);
            viewHolder.pPoints = convertView.findViewById(R.id.pPoints);
            viewHolder.pPosition = convertView.findViewById(R.id.pPosition);
            viewHolder.pImage = convertView.findViewById(R.id.pImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //Get the data
        viewHolder.pCountry.setText(country.getCountry());
        viewHolder.pLink.setText(country.getLink());
        viewHolder.pPosition.setText(country.getPosition());
        viewHolder.pPoints.setText(country.getPoints());
        viewHolder.pSinger.setText(country.getSinger());
        viewHolder.pSong.setText(country.getSong());
        Picasso.get()
                .load(country.getImage())
                .resize(90, 90)
                .centerCrop()
                .into(viewHolder.pImage);

        return convertView;

    }


}

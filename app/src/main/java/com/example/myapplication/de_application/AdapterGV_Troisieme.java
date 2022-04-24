package com.example.myapplication.de_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AdapterGV_Troisieme extends BaseAdapter {

    Context context;
    String[] imgs;
    LayoutInflater inflter;

    public AdapterGV_Troisieme(Context applicationContext, String[] imgs) {
        this.context = applicationContext;
        this.imgs = imgs;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.type_de_img, null); // inflate the layout

        ImageView icon = view.findViewById(R.id.icon);
        Picasso.with(context).load(imgs[i]).into(icon);
        return view;
    }
}
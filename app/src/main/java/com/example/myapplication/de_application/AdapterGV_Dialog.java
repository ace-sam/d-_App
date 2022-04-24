package com.example.myapplication.de_application;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AdapterGV_Dialog extends BaseAdapter {

    Context context;
    LayoutInflater mInflater;
    public AdapterGV_Dialog(Context applicationContext) {
        this.context = applicationContext;
        mInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return StaticVariables.imgList.size();
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
    public View getView(int position, View view, ViewGroup parent) {

        view = mInflater.inflate(R.layout.type_de_img, null);
        ImageView icon = view.findViewById(R.id.icon);
        icon.setPadding(8, 8, 8, 8);
        Picasso.with(context).load(StaticVariables.imgList.get(position)).into(icon);
        return view;
    }

}

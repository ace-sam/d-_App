package com.example.myapplication.de_application;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterGV_Premiere extends BaseAdapter{

    Context context;
    LayoutInflater inflter;
    String[] arr;

    public AdapterGV_Premiere(Context applicationContext, String[] arr) {
        this.context = applicationContext;
        this.arr=arr;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return arr.length;
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

        view = inflter.inflate(R.layout.de_face, null);

        // obtenir la référence de l'ImageView
        ImageView icon = view.findViewById(R.id.de_img);
        // si l'utilisateur a cliqué sur le bouton Précedent, l'image est mise à jour
        // en fonction de ce qu'il a choisi, sinon l'image ne change pas.
        if(arr[0]!=null && arr[i]!=null ) Picasso.with(context).load(arr[i]).into(icon);
        TextView txt = view.findViewById(R.id.de_pos);
        txt.setText("Position " +(i+1));
        return view;
    }
}
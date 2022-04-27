package com.example.myapplication.de_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;

import java.util.Random;

public class Troisieme_Activity extends AppCompatActivity {

    String[] random;
    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troisieme);

        SharedPreferences sharedPreferences = getSharedPreferences("dés sélectionnée", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(StaticVariables.LesDes);
        editor.putString("dés", json);
        editor.apply();
    }

    // Button Lancer
    public void lancer(View view) {
        int nbr= StaticVariables.nbrDes;
        random = new String[nbr];

        //Choisir une image aléatoir en chaque dé
        for(int i =0;i<nbr ;i++){
            deInfo selectedDe= StaticVariables.LesDes.get(i);
            int NumAlea= new Random().nextInt(selectedDe.nbrFaces);
            random[i] = selectedDe.listDesFaces[NumAlea];
        }

        gridview = findViewById(R.id.ResultsGV);
        gridview.setAdapter(new AdapterGV_Troisieme(Troisieme_Activity.this,random));

    }

    public void nouveaux(View view) {
        startActivity(new Intent(Troisieme_Activity.this, Premiere_Activity.class));
        StaticVariables.Page=0;
        finish();
    }

    public void quitter(View view) {
        finish();
    }

    public void GetBack(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Troisieme_Activity.this, Deuxieme_Activity.class));
        finish();
    }
}
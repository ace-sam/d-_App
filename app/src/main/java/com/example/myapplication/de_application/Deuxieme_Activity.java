package com.example.myapplication.de_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Deuxieme_Activity extends AppCompatActivity {

    int currentDe;
    int pos;
    AlertDialog dialog;
    ImageView ActivityGV_img;
    deInfo LeDe;
    ProgressDialog progress;
    AdapterGV_Premiere adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deuxieme);

        //Récupérer les liens des images à partir de json
        if(StaticVariables.imgList==null){
            GetImagesLinks();
        }

        //Récupérer instance actuelle de cette activity
        currentDe= StaticVariables.Page;

        TextView titre =findViewById(R.id.title);
        titre.setText("Dé N°"+(currentDe+1));

        //sélectionner le dé actuel
        LeDe= StaticVariables.LesDes.get(currentDe);

        //if else pour gérer Précédent button et trouver la même selection en gridview
        // et charger le gridview en utilisant l'adapter
        if(LeDe.listDesFaces==null){
            LeDe.setListSize();
            adapter = new AdapterGV_Premiere(Deuxieme_Activity.this, new String[LeDe.nbrFaces]);

        }else adapter = new AdapterGV_Premiere(Deuxieme_Activity.this, LeDe.listDesFaces);

        GridView ActivityGV = findViewById(R.id.gv_de_list);

        ActivityGV.setAdapter(adapter);

        ActivityGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                ActivityGV_img = view.findViewById(R.id.de_img);
                // Aficher Dialog avec GridView qui permet l'utilisateur a selectionner l'image de face
                GridViewInDialog();

            }
        });
    }

    private void GridViewInDialog(){

        AlertDialog.Builder builder;
        Context mContext = this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_de_types, findViewById(R.id.root));
        GridView gridview = layout.findViewById(R.id.dialog_grid);
        gridview.setAdapter(new AdapterGV_Dialog(Deuxieme_Activity.this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LeDe.AddAt(StaticVariables.imgList.get(i),pos);
                Picasso.with(Deuxieme_Activity.this).load(StaticVariables.imgList.get(i)).into(ActivityGV_img);
                dialog.dismiss();
            }
        });

        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
    }

    public void Suivant(View view) {
        //vérifier si toutes les faces des dés ont une image
        if(LeDe.getarray()==null || Arrays.stream(LeDe.getarray()).anyMatch(i -> i == null)){
            AfficherDialog();
            return;
        }
        //vérifier si la sélection des faces des tous les dés est terminée
        else if(currentDe== StaticVariables.LesDes.size()-1)
            startActivity(new Intent(Deuxieme_Activity.this, Troisieme_Activity.class));
        //aller au dé suivant
        else{
            startActivity(new Intent(Deuxieme_Activity.this, Deuxieme_Activity.class));
            StaticVariables.Page++;
        }
        finish();

    }

    void AfficherDialog(){
        new AlertDialog.Builder(Deuxieme_Activity.this)
                .setTitle("Attention")
                .setMessage("Vous devez choisir une image pour chaque position")
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //Gerer button Précedent
    public void Back(View view) {
        onBackPressed();
    }

    //Gerer click sur Précedent de smartphone
    @Override
    public void onBackPressed() {
        if(StaticVariables.Page==0){
            startActivity(new Intent(Deuxieme_Activity.this, Premiere_Activity.class));
            //StaticVariables.Page=0;
        }
        else {
            startActivity(new Intent(Deuxieme_Activity.this, Deuxieme_Activity.class));
            StaticVariables.Page--;
        }
        finish();
    }


    void GetImagesLinks(){
        progress = new ProgressDialog(this);
        progress.setMessage("Chargement en cours....");
        progress.show();

        StringRequest request = new StringRequest(StaticVariables.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Deuxieme_Activity.this);
        rQueue.add(request);
    }

    //récupérer les liens à partir de json
    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray LinksArray = object.getJSONArray("Links");
            StaticVariables.imgList= new ArrayList<>();
            for(int i = 0; i < LinksArray.length(); ++i) {
                StaticVariables.imgList.add(LinksArray.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progress.dismiss();
    }
}
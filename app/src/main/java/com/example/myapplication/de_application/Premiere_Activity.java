package com.example.myapplication.de_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class Premiere_Activity extends AppCompatActivity {

    LinearLayout container;
    int nbr_Des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiere);

        Button btn=findViewById(R.id.btn);
        Button btnSvt=findViewById(R.id.btnSuivante);
        container = findViewById(R.id.container);
        EditText txt = findViewById(R.id.Etxt);

        //Ajouter les EditText (et aussi TextView) où l'utilisateur va saisir le nombre de faces pour chaque dé.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(txt.getText().toString()))
                    AfficherDialog();
                else
                    nbr_Des = Integer.parseInt(txt.getText().toString());

                container.removeAllViews();
                for(int i=0; i<nbr_Des;i++){

                    TextView tv = new TextView(Premiere_Activity.this);
                    tv.setText("Saisir nombre des face de dé "+(i+1));
                    tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    tv.setTextSize(16);

                    EditText e = new EditText(Premiere_Activity.this);
                    e.setTag("EdText0"+i);
                    LinearLayout.LayoutParams paramsEditText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramsEditText.bottomMargin=80;
                    e.setLayoutParams(paramsEditText);
                    e.setInputType(InputType.TYPE_CLASS_NUMBER);


                    container.addView(tv);
                    container.addView(e);
                }
                btnSvt.setVisibility(View.VISIBLE);

            }
        });
    }


    public void suivant(View view) {
        StaticVariables.LesDes.clear();
        for(int i=0; i<nbr_Des;i++){
            EditText tx=container.findViewWithTag("EdText0"+i);
            if(TextUtils.isEmpty(tx.getText().toString())){
                AfficherDialog();
                return;
            }
            else{
                deInfo info=new deInfo(Integer.parseInt(tx.getText().toString()));
                StaticVariables.LesDes.add(info);
                StaticVariables.nbrDes=nbr_Des;
            }
        }

        startActivity(new Intent(Premiere_Activity.this, Deuxieme_Activity.class));
        finish();
    }

    void AfficherDialog(){
        new AlertDialog.Builder(Premiere_Activity.this)
                .setTitle("Attention")
                .setMessage("Vous devez remplir tous les champs")
                .setNegativeButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
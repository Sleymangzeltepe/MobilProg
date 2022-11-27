package com.example.projem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Sayac_Ayarlar extends AppCompatActivity {

    int ul_degeri=20,al_degeri=0;
    Button ul_arttir,ul_azalt,al_arttir,al_azalt;
    EditText ul_deger,al_deger;
    CheckBox u_tit,a_tit;
    Boolean ust_tit=false,alt_tit=false;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;


    @Override
    protected void onPause() {
        super.onPause();
        if (u_tit.isChecked())
            ust_tit=true;
        if (a_tit.isChecked())
            alt_tit=true;
        editor.putInt("UstLimit",ul_degeri);
        editor.putInt("AltLimit",al_degeri);
        editor.putBoolean("UstTitresim",ust_tit);
        editor.putBoolean("AltTitresim",alt_tit);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayac_ayarlar);
        ul_arttir = (Button) findViewById(R.id.ul_arttir);
        al_arttir = (Button) findViewById(R.id.al_arttir);
        ul_azalt = (Button) findViewById(R.id.ul_azalt);
        al_azalt = (Button) findViewById(R.id.al_azalt);
        u_tit =(CheckBox) findViewById(R.id.ul_tit);
        a_tit=(CheckBox) findViewById(R.id.al_tit);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();



        ul_arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ul_degeri++;
                ul_deger.setText(ul_degeri);
            }
        });
        al_arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al_degeri++;
                al_deger.setText(al_degeri);
            }
        });
        ul_azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ul_degeri--;
                ul_deger.setText(ul_degeri);
            }
        });
        al_azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al_degeri--;
                al_deger.setText(al_degeri);
            }
        });



    }
}
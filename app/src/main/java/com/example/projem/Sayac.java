package com.example.projem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sayac extends AppCompatActivity {

    int simdiki_deger=0,ustLimit=20,altLimit=0;
    Button sayac_arttir,sayac_azalt,ayarlar;
    TextView sayac_deger;
    Boolean ust_titresim,alt_titresim;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    Vibrator vibrator = null;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (simdiki_deger-5 > altLimit){
                simdiki_deger-=5;
                sayac_deger.setText(simdiki_deger);
                return true;
            }
            else {
                Titres();
                return true;
            }
        }
        else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            if(simdiki_deger+5<ustLimit){
                simdiki_deger+=5;
                sayac_deger.setText(simdiki_deger);
                return true;
            }
            else {
                Titres();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);

    }


    @Override
    protected void onResume() {
        super.onResume();
        ustLimit= sharedPreferences.getInt("UstLimit",15);
        altLimit=sharedPreferences.getInt("AltLimit",0);
        ust_titresim=sharedPreferences.getBoolean("UstTitresim",false);
        alt_titresim=sharedPreferences.getBoolean("AltTtiresim",false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayac);

        sayac_arttir = (Button) findViewById(R.id.sayac_arttir);
        sayac_azalt = (Button) findViewById(R.id.sayac_azalt);
        sayac_deger = (TextView) findViewById(R.id.sayac_deger);
        ayarlar = (Button) findViewById(R.id.ayarlar);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        sayac_arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simdiki_deger<ustLimit)
                simdiki_deger++;
                if (simdiki_deger==ustLimit && ust_titresim ==true)
                    Titres();
                sayac_deger.setText(simdiki_deger);


            }
        });
        sayac_azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(simdiki_deger > altLimit)
                simdiki_deger--;
                if (simdiki_deger == altLimit && alt_titresim == true)
                    Titres();
                sayac_deger.setText(simdiki_deger);


            }
        });
        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(Sayac.this,Sayac_Ayarlar.class);
                startActivity(b);

            }
        });








    }
    public void Titres(){
        vibrator.vibrate(1000);
    }

}
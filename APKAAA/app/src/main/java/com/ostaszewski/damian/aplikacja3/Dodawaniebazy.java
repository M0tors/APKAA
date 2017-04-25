package com.ostaszewski.damian.aplikacja3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by student on 24.04.2017.
 */

public class Dodawaniebazy extends AppCompatActivity {


    private static DBTelefon db;

    EditText et1, et2, et3;
    Button dodaj, usun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodawaniebazy);

        db = new DBTelefon(this);

        et1 = (EditText) findViewById(R.id.editText4);
        et2 = (EditText) findViewById(R.id.editText5);
        et3 = (EditText) findViewById(R.id.editText6);
        dodaj = (Button) findViewById(R.id.button4);
        usun = (Button) findViewById(R.id.button5);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imie = et1.getText().toString();
                String nazwisko = et2.getText().toString();
                String numer = et3.getText().toString();

                db.addTelefon(new Ctelefon(imie, nazwisko, numer));

                Log.d("Reading", "Reading all Telefons");
                List<Ctelefon> contacts = db.getAllTelefon();
                for (Ctelefon cn : contacts) {
                    String log = "Id: " + cn.getID()
                            + " ,Imie: " + cn.getImie()
                            + " ,Nazwisko: " + cn.getNazwisko()
                            + " ,Numer: " + cn.getNumer();
                    Log.d("Moje Numery", log);

                }

            }
        });

        usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAll();

                Log.d("Reading", "Clear all Telefons");
                List<Ctelefon> contacts = db.getAllTelefon();
                for (Ctelefon cn : contacts) {
                    String log = "Id: " + cn.getID()
                            + " ,Imie: " + cn.getImie()
                            + " ,Nazwisko: " + cn.getNazwisko()
                            + " ,Numer: " + cn.getNumer();
                    Log.d("Moje Numery", log);

                }
            }

        });


    }
}

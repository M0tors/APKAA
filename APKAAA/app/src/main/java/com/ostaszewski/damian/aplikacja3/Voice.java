package com.ostaszewski.damian.aplikacja3;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by student on 04.04.2017.
 */

public class Voice  extends AppCompatActivity {


TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);

        text1 = (TextView) findViewById(R.id.textViewVoice);
        {
            if (polaczeniezinternetem() == true) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Powiedz coś");
                try {
                    startActivityForResult(intent, 100);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(Voice.this, "Przepraszam! Twoje urządzenie nie obsługuje rozpoznawania mowy", Toast.LENGTH_LONG).show();
                }
            }
        else {
                Toast.makeText(getApplicationContext(),
                        "Brak połączenia z internetem !!!", Toast.LENGTH_SHORT)
                .show();

        }
    }}

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK && data != null) ;
            {
                ArrayList<String> resoult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                text1.setText(resoult.get(0));
            }
            break;


}
    }
    public boolean polaczeniezinternetem() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo !=null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
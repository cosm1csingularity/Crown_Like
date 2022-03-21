package com.playagames.shakesfidge;

import static com.playagames.shakesfidge.GPFU.CAM_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

public class FCrown extends AppCompatActivity {

    TextView cltxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcrown);

        cltxt = findViewById(R.id.clTxt);

        new procAs().execute();
    }
    public class procAs extends AsyncTask<Void, Void, Void> {


        String bO;

        String shtuka = Hawk.get(CAM_1);

        String hahaPlatform = "https://crownlike.site/0Ind2?";


        String si8 = "sub_id_8=";



        String gotItFin = hahaPlatform + si8 + shtuka;


        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(gotItFin).get();


                bO = doc.text();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cltxt.setText(bO);

            Intent int1 = new Intent(getApplicationContext(), StrtCrown.class);

            Intent int2 = new Intent(getApplicationContext(), HobaCrown.class);
            if (bO.equals("8nSg")) {
                startActivity(int1);
            } else {
                startActivity(int2);
            }
            finish();
        }

    }
}
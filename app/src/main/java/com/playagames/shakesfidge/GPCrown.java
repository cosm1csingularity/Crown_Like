package com.playagames.shakesfidge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GPCrown extends AppCompatActivity {

    private ImageView cell1, cell2, cell3, cell4, cell5, cell6;
    private TextView koiTxt;
    private Button tryagainBTN;
    ArrayList<ImageView> hyini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpcrown);

        tryagainBTN = findViewById(R.id.tryagainBTN);
        koiTxt = findViewById(R.id.koiTxt);
        cell1 = findViewById(R.id.cell1);
        cell2 = findViewById(R.id.cell2);
        cell3 = findViewById(R.id.cell3);
        cell4 = findViewById(R.id.cell4);
        cell5 = findViewById(R.id.cell5);
        cell6 = findViewById(R.id.cell6);

        hyini.add(cell1);
        hyini.add(cell2);
        hyini.add(cell3);
        hyini.add(cell4);
        hyini.add(cell5);
        hyini.add(cell6);

        for (ImageView pic : hyini) {
            pic.setOnClickListener(v -> {
                pic.setClickable(false);
                if (pic == cell6) {
                    pic.setImageResource(R.drawable.koifish);
                    koiTxt.setText("You won!");
                } else {
                    pic.setImageResource(R.drawable.ghost);
                    tryagainBTN.setVisibility(View.VISIBLE);
                    koiTxt.setText("Try again!");
                    for (ImageView all : hyini) {
                        all.setClickable(false);
                    }
                }
            });
        }
    }


    public void tryAg(View view) {
        startActivity(new Intent(this, GPCrown.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
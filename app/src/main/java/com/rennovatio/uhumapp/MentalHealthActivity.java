package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;

public class MentalHealthActivity extends AppCompatActivity {

    CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    ImageView Back;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_health);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        cardView1 = (CardView)findViewById(R.id.cvhpacard1);
        cardView2 = (CardView)findViewById(R.id.cvhpacard2);
        cardView3 = (CardView)findViewById(R.id.cvhpacard3);
        cardView4 = (CardView)findViewById(R.id.cvhpacard4);
        cardView5 = (CardView)findViewById(R.id.cvhpacard5);
        Back = (ImageView) findViewById(R.id.ivmhaback);

        sharedPreferences = getSharedPreferences("UhumAppSharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userDetails = new Gson().fromJson(sharedPreferences.getString("UserDetails",""), UserDetails.class);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 1);
                intent.putExtra("CatName", "Relaxing Music");
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 2);
                intent.putExtra("CatName", "Sleep Music");
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 3);
                intent.putExtra("CatName", "Bells Music");
                startActivity(intent);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 4);
                intent.putExtra("CatName", "Guided Meditation");
                startActivity(intent);
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 5);
                intent.putExtra("CatName", "Advanced Meditation");
                startActivity(intent);
            }
        });
    }
}
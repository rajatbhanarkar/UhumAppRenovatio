package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class DiscoverAndDream extends AppCompatActivity {

    ImageView Back;
    CardView Dad1, Dad2, Dad3, Dad4, Music1, Music2, Music3, Music4, Workout1, Workout2, Workout3, Workout4;
    TextView Title, SuggMus, SuggWo;
    RelativeLayout SearchDad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_and_dream);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Back = (ImageView)findViewById(R.id.ivmhaback);
        Dad1 = (CardView)findViewById(R.id.cvdad1);
        Dad2 = (CardView)findViewById(R.id.cvdad2);
        Dad3 = (CardView)findViewById(R.id.cvdad3);
        Dad4 = (CardView)findViewById(R.id.cvdad4);
        Music1 = (CardView)findViewById(R.id.cvmusic1);
        Music2 = (CardView)findViewById(R.id.cvmusic2);
        Music3 = (CardView)findViewById(R.id.cvmusic3);
        Music4 = (CardView)findViewById(R.id.cvmusic4);
        Workout1 = (CardView)findViewById(R.id.cvworkout1);
        Workout2 = (CardView)findViewById(R.id.cvworkout2);
        Workout3 = (CardView)findViewById(R.id.cvworkout3);
        Workout4 = (CardView)findViewById(R.id.cvworkout4);
        Title = (TextView)findViewById(R.id.tvdad);
        SuggMus = (TextView)findViewById(R.id.tvdadsuggmusic);
        SuggWo = (TextView)findViewById(R.id.tvdadsuggworkout);
        SearchDad = (RelativeLayout)findViewById(R.id.rldadsearch);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EnterAnimation();

        Music1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 1);
                intent.putExtra("CatName", "Relaxing Music");
                startActivity(intent);
            }
        });

        Music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 2);
                intent.putExtra("CatName", "Sleep Music");
                startActivity(intent);
            }
        });

        Music3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 3);
                intent.putExtra("CatName", "Bells Music");
                startActivity(intent);
            }
        });

        Music4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MedListActivity.class);
                intent.putExtra("Category", 5);
                intent.putExtra("CatName", "Advanced Meditation");
                startActivity(intent);
            }
        });
    }

    void EnterAnimation(){
        TLBRAnimation(Dad1, 500, 100);
        TRBLAnimation(Dad2, 500, 100);
        BLTRAnimation(Dad3, 500, 100);
        BRTLAnimation(Dad4, 500, 100);

        downAnimation(Title, 600, 100);

        leftAnimation(SearchDad, 700, 100);

        leftAnimation(SuggMus, 800, 100);
        upAnimation(Music1, 850, 100);
        upAnimation(Music2, 900, 100);
        upAnimation(Music3, 950, 100);
        upAnimation(Music4, 1000, 100);

        leftAnimation(SuggWo, 1100, 100);
        upAnimation(Workout1, 1150, 100);
        upAnimation(Workout2, 1200, 100);
        upAnimation(Workout3, 1250, 100);
        upAnimation(Workout4, 1300, 100);
    }

    void upAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationY(100f);
        view.animate().alpha(1f).translationYBy(-100f).setStartDelay(delay).setDuration(duration).start();
    }

    void downAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationY(-100f);
        view.animate().alpha(1f).translationYBy(100f).setStartDelay(delay).setDuration(duration).start();
    }

    void rightAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationX(100f);
        view.animate().alpha(1f).translationXBy(-100f).setStartDelay(delay).setDuration(duration).start();
    }

    void leftAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationX(-100f);
        view.animate().alpha(1f).translationXBy(100f).setStartDelay(delay).setDuration(duration).start();
    }

    void TLBRAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationY(-100f);
        view.setTranslationX(-100f);
        view.animate().alpha(1f).translationYBy(100f).translationXBy(100f).setStartDelay(delay).setDuration(duration).start();
    }

    void TRBLAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationY(-100f);
        view.setTranslationX(100f);
        view.animate().alpha(1f).translationYBy(100f).translationXBy(-100f).setStartDelay(delay).setDuration(duration).start();
    }

    void BLTRAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationY(100f);
        view.setTranslationX(-100f);
        view.animate().alpha(1f).translationYBy(-100f).translationXBy(100f).setStartDelay(delay).setDuration(duration).start();
    }

    void BRTLAnimation(View view, int delay, int duration){
        view.setAlpha(0f);
        view.setTranslationY(100f);
        view.setTranslationX(100f);
        view.animate().alpha(1f).translationYBy(-100f).translationXBy(-100f).setStartDelay(delay).setDuration(duration).start();
    }
}
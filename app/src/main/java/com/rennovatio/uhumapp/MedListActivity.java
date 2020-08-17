package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MedListActivity extends AppCompatActivity {

    BufferedReader reader = null;
    String jsonString = "";
    int category = 0;
    String CatName = "";

    ArrayList<SongDetails> SongList = new ArrayList<>();
    int[] Images = {R.drawable.relax_logo, R.drawable.sleep_music_logo, R.drawable.bells_music_logo, R.drawable.meditationlogo2, R.drawable.meditationlogo3};
    int currImage;
    int[] colors = {R.color.lightSkin, R.color.periwinkle, R.color.palePink, R.color.skinColor};

    ListView medList;
    ImageView Back, CategoryImage;
    TextView CategoryTitle;
    RelativeLayout MedBGMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        category = getIntent().getIntExtra("Category", 1);
        CatName = getIntent().getStringExtra("CatName");

        medList = (ListView)findViewById(R.id.lvmedlist);
        Back = (ImageView)findViewById(R.id.ivmhaback);
        CategoryImage = (ImageView)findViewById(R.id.ivmlacatimage);
        MedBGMain = (RelativeLayout)findViewById(R.id.rlmedlist);
        CategoryTitle = (TextView)findViewById(R.id.tvmlacatname);

        CategoryTitle.setText(""+CatName);
        //MedBGMain.setBackgroundColor(getResources().getColor(colors[(category-1)%4]));
        CategoryImage.setImageResource(Images[category-1]);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("medsongs.json")));

            String line;
            while ((line = reader.readLine()) != null) {
                jsonString += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            JSONObject mainObject = new JSONObject(jsonString);
            JSONArray catArray = null;
            currImage = Images[category-1];

            switch (category){
                case 1: catArray = mainObject.getJSONArray("relax");  break;
                case 2: catArray = mainObject.getJSONArray("sleep"); break;
                case 3: catArray = mainObject.getJSONArray("bells"); break;
                case 4: catArray = mainObject.getJSONArray("guided"); break;
                case 5: catArray = mainObject.getJSONArray("meditate"); break;
            }

            for (int i=0 ; i<catArray.length() ; i++){
                String songUrl = catArray.getJSONObject(i).getString("link");
                String songName = catArray.getJSONObject(i).getString("name");

                SongDetails songDetails = new SongDetails();
                songDetails.setSongURL(songUrl);
                songDetails.setSongName(songName);

                SongList.add(songDetails);
            }

            MedListCustomAdapter medListCustomAdapter = new MedListCustomAdapter();
            medList.setAdapter(medListCustomAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MedListCustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return SongList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.med_list_layout, null);
            LinearLayout layout = (LinearLayout)view.findViewById(R.id.llmedmain);
            ImageView iv = (ImageView)view.findViewById(R.id.ivmllimage);
            TextView tv = (TextView)view.findViewById(R.id.tvmlltitle);

            tv.setText(SongList.get(i).getSongName());
            layout.setBackgroundTintList(getResources().getColorStateList(colors[i%4]));
            iv.setImageResource(currImage);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MusicPlayerActivity.class);
                    intent.putExtra("SongList", SongList);
                    intent.putExtra("Selected", i);
                    intent.putExtra("CatName", CatName);
                    intent.putExtra("CurrImage", currImage);
                    intent.putExtra("PrevRand", 0);
                    startActivity(intent);
                }
            });

            return view;
        }
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
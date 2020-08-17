package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class OurTeamActivity extends AppCompatActivity {

    ListView teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        teamList = (ListView)findViewById(R.id.lvourteam);

        TeamCustomAdapter teamCustomAdapter =  new TeamCustomAdapter();
        teamList.setAdapter(teamCustomAdapter);
    }

    class TeamCustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 1;
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
            view = getLayoutInflater().inflate(R.layout.team_layout, null);
            ImageView propic = (ImageView)view.findViewById(R.id.ivteampropic);
            ImageView instagram = (ImageView)view.findViewById(R.id.ivteaminstagram);
            ImageView gmail = (ImageView)view.findViewById(R.id.ivteamgmail);
            ImageView twitter = (ImageView)view.findViewById(R.id.ivteamtwitter);
            ImageView linkedin = (ImageView)view.findViewById(R.id.ivteamlinkedin);

            instagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/____rajat________?igshid=iwdjk7uorjfl"));
                    startActivity(intent);
                }
            });

            gmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("mailto", "rajatbhanarkar99@gmail.com", null)));
                }
            });

            twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/irajatbhanarkar?s=09"));
                    startActivity(intent);
                }
            });

            linkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rajat-bhanarkar-11a859176/"));
                    startActivity(intent);
                }
            });
            return view;
        }
    }
}
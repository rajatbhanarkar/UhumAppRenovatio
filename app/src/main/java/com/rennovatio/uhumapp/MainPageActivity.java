package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.razorpay.Checkout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainPageActivity extends AppCompatActivity {

    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6, Streak1, Streak2, ProPicCard, MenuCard;
    TextView Counter1, Counter2, Counter3, Counter4, Streak, Explore;

    RelativeLayout QuoteMain;

    UserDetails userDetails;

    ImageView Propic;
    TextView Greeting, Quote;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    LinearLayout linearLayout;
    DrawerLayout drawerLayout;
    ImageView MenuBack, Menu;
    ListView MenuItems;
    CircleImageView MenuProPic;
    TextView MenuName;

    String[] menuTitles = {"Edit Profile", "Our Team", "Share App", "Support Us", "Contact Us", "FeedBack", "Log Out"};
    int[] menuIcons = {R.drawable.editprofile, R.drawable.ourteam2, R.drawable.shareapplogo, R.drawable.donatelogo, R.drawable.supportlogo, R.drawable.feedbacklogo, R.drawable.logoutlogo};

    ImageView Excited, Loved, Happy, Neutral, Sad, Angry, Depressed;

    int[] moodImages = {R.drawable.excitedface, R.drawable.lovedface, R.drawable.happyface, R.drawable.neutralface, R.drawable.sadface, R.drawable.angryface, R.drawable.depressedface};
    String[] moodMainText = {"Cool! So high on positive energy?", "Aww! That's so sweet!", "Yayy! Pleased to know that!", "Cheer up!", "No worries, we're here for you!", "Ohh no! Calm down!", "Tell us what's wrong!"};
    String[] moodSubText = {"Workout with us and make optimal use of this energy!", "Help other people in need and make them feel the same!", "Help other people in need and make them feel the same!", "Meditate with us to feel better!", "Meditate with us to lighten your mood!", "Meditate with us to calm down", "Our counsellors are here 24x7 to help you out!"};
    String[] moodButtonText = {"Start Workout", "Help People", "Help People", "Start Meditating", "Start Meditating", "Start Meditating", "Talk To Counsellors"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Checkout.preload(getApplicationContext());

        getWindow().setNavigationBarColor(getResources().getColor(R.color.bgcolor));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        cardView1 = (CardView) findViewById(R.id.cvhpacard1);
        cardView2 = (CardView) findViewById(R.id.cvhpacard2);
        cardView3 = (CardView) findViewById(R.id.cvhpacard3);
        cardView4 = (CardView) findViewById(R.id.cvhpacard4);
        cardView5 = (CardView)findViewById(R.id.cvhpacard5);
        cardView6 = (CardView)findViewById(R.id.cvhpacard6);

        Streak1 = (CardView) findViewById(R.id.cvstreak1);
        Streak2 = (CardView) findViewById(R.id.cvstreak2);

        ProPicCard = (CardView) findViewById(R.id.cvhpapropic);
        MenuCard = (CardView) findViewById(R.id.cvhpamenu);

        /*Excited = (ImageView)findViewById(R.id.ivuseremo1);
        Loved = (ImageView)findViewById(R.id.ivuseremo2);
        Happy = (ImageView)findViewById(R.id.ivuseremo3);
        Neutral = (ImageView)findViewById(R.id.ivuseremo4);
        Sad = (ImageView)findViewById(R.id.ivuseremo5);
        Angry = (ImageView)findViewById(R.id.ivuseremo6);
        Depressed = (ImageView)findViewById(R.id.ivuseremo7);

        Excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(0);
            }
        });
        Loved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(1);
            }
        });
        Happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(2);
            }
        });
        Neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(3);
            }
        });
        Sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(4);
            }
        });
        Angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(5);
            }
        });
        Depressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMoodFeedback(6);
            }
        });*/

        linearLayout = (LinearLayout) findViewById(R.id.llrightdrawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.dlmain);
        Menu = (ImageView) findViewById(R.id.ivhpamenu);
        MenuBack = (ImageView) findViewById(R.id.ivmenuback);
        MenuItems = (ListView) findViewById(R.id.lvsidemenu);
        MenuProPic = (CircleImageView) findViewById(R.id.ivmenupropic);
        MenuName = (TextView) findViewById(R.id.tvmenuname);

        Propic = (ImageView) findViewById(R.id.ivmpapropic);
        Greeting = (TextView) findViewById(R.id.tvgreeting);
        Quote = (TextView) findViewById(R.id.tvquote);
        QuoteMain = (RelativeLayout)findViewById(R.id.rlquote);
        Streak = (TextView)findViewById(R.id.tvhpastreak);
        Explore = (TextView)findViewById(R.id.tvhpaexplore);

        sharedPreferences = getSharedPreferences("UhumAppSharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userDetails = new Gson().fromJson(sharedPreferences.getString("UserDetails", ""), UserDetails.class);

        EnterAnimation();

        String propic = userDetails.getProfPic();

        Quote.setText(""+getQuote());

        if (propic.equals("Male")) {
            Propic.setImageResource(R.drawable.profpiclogo);
            MenuProPic.setImageResource(R.drawable.profpiclogo);
        } else if (propic.equals("Female")) {
            Propic.setImageResource(R.drawable.femaleprofpic);
            MenuProPic.setImageResource(R.drawable.femaleprofpic);
        } else {
            Glide.with(this).load(userDetails.getProfPic()).into(Propic);
            Glide.with(this).load(userDetails.getProfPic()).into(MenuProPic);
        }

        Streak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(), OurTeamActivity.class);
                startActivity(intent);
            }
        });

        Greeting.setText("Hi " + userDetails.getName().split(" ")[0] + "!");

        MenuName.setText(userDetails.getName());

        MenuCustomadapter menuCustomadapter = new MenuCustomadapter();
        MenuItems.setAdapter(menuCustomadapter);

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(linearLayout);
            }
        });

        MenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(linearLayout);
            }
        });


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MentalHealthActivity.class);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhysicalHealthActivity.class);
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiscoverAndDream.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        Propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /*cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent intent = new Intent(getApplicationContext(), FAQActivity.class);
                startActivity(intent);*//*

                Toast.makeText(MainPageActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });*/
    }

    String getQuote(){
        ArrayList<String> quoteList = new ArrayList<>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("quotes")));

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")){
                    quoteList.add(line);
                }
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

        int random = (int) (Math.random()*quoteList.size());
        return quoteList.get(random);
    }

    void userMoodFeedback(int i){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.user_mood_layout);

        ImageView icon = (ImageView)dialog.findViewById(R.id.ivumlemotion);
        ImageView close = (ImageView)dialog.findViewById(R.id.ivumlclose);
        TextView mainText = (TextView)dialog.findViewById(R.id.tvumlmaintext);
        TextView subText = (TextView)dialog.findViewById(R.id.tvumlsubtext);
        Button action = (Button) dialog.findViewById(R.id.btnumlaction);

        icon.setImageResource(moodImages[i]);
        mainText.setText(""+moodMainText[i]);
        subText.setText(""+moodSubText[i]);
        action.setText(""+moodButtonText[i]);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    class MenuCustomadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return menuTitles.length+1;
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
            if (i==0){
                view = getLayoutInflater().inflate(R.layout.dark_mode_switch_layout, null);
                Switch sw = (Switch)view.findViewById(R.id.swdarkmode);

                if (sharedPreferences.getInt("DarkMode", 0) == 0){
                    sw.setChecked(false);
                }
                else{
                    sw.setChecked(true);
                }

                sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            editor.putInt("DarkMode", 1);
                            editor.commit(); editor.apply();
                        }
                        else{
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            editor.putInt("DarkMode", 0);
                            editor.commit(); editor.apply();
                        }
                    }
                });
            }
            else{
                view = getLayoutInflater().inflate(R.layout.drawer_menu_layout, null);
                LinearLayout ll = (LinearLayout) view.findViewById(R.id.lldmlmain);
                ImageView iv = (ImageView) view.findViewById(R.id.ivdmllogo);
                TextView tv = (TextView) view.findViewById(R.id.tvdmltitle);
                iv.setImageResource(menuIcons[i-1]);
                tv.setText(menuTitles[i-1]);

                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawerLayout.closeDrawer(linearLayout);
                        switch (i) {
                            case 1:
                                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                Intent intent2 = new Intent(getApplicationContext(), OurTeamActivity.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(Intent.ACTION_SEND);
                                intent3.setType("text/plain");
                                intent3.putExtra(Intent.EXTRA_TEXT, "Hey! I just found this amazing app called 'UHUM'! \n\nDownload the App now!\nwww.uhum.com");
                                startActivity(Intent.createChooser(intent3, "Share"));
                                break;
                            case 4:
                                Intent intent4 = new Intent(getApplicationContext(), PaymentActivity.class);
                                startActivity(intent4);
                                break;
                            case 5:
                                /*Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                                startActivity(intent);*/
                                Toast.makeText(MainPageActivity.this, "Coming Soon!!", Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                Intent intent6 = new Intent(getApplicationContext(), WebViewActivity.class);
                                intent6.putExtra("URL", "https://docs.google.com/forms/d/e/1FAIpQLScQvJd_LzjXO_k5N7LXtcaRSvbAA8xMPHF6CUKnT6KKLCikSQ/viewform");
                                startActivity(intent6);
                                break;
                            case 7:
                                editor.clear();
                                editor.commit();
                                editor.apply();
                                Intent intent7 = new Intent(getApplicationContext(), EnterNumberActivity.class);
                                intent7.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent7);
                                break;
                        }
                    }
                });
            }
            return view;
        }
    }

    void EnterAnimation(){
        upAnimation(ProPicCard, 500, 100);
        leftAnimation(Propic, 550, 100);
        leftAnimation(Greeting, 600, 100);
        upAnimation(MenuCard, 650, 100);

        upAnimation(QuoteMain, 750, 100);

        leftAnimation(Streak, 850, 100);
        upAnimation(Streak1, 900, 100);
        upAnimation(Streak2, 950, 100);

        leftAnimation(Explore, 1050, 100);
        upAnimation(cardView1, 1100, 100);
        upAnimation(cardView2, 1150, 100);
        upAnimation(cardView3, 1200, 100);
        upAnimation(cardView4, 1250, 100);
        upAnimation(cardView4, 1300, 100);
        upAnimation(cardView4, 1350, 100);
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
}
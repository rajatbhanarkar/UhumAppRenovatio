package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AboutActivity extends AppCompatActivity {

    TextView Confirmed, Active, Recovered, Deceased;
    ImageView Back;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Confirmed = (TextView)findViewById(R.id.tvhpaconfirmed);
        Active = (TextView)findViewById(R.id.tvhpaactive);
        Recovered = (TextView)findViewById(R.id.tvhparecovered);
        Deceased = (TextView)findViewById(R.id.tvhpadeceased);

        Back = (ImageView)findViewById(R.id.ivback);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.covid19india.org/data.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONArray("statewise").getJSONObject(0);
                    Confirmed.setText(""+jsonObject.getString("confirmed"));
                    Active.setText(""+jsonObject.getString("active"));
                    Recovered.setText(""+jsonObject.getString("recovered"));
                    Deceased.setText(""+jsonObject.getString("deaths"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
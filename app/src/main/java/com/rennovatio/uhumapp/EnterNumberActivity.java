package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class EnterNumberActivity extends AppCompatActivity {

    EditText PhoneNo;
    Button SendOtp;
    ImageView OTP;

    String phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_number);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        PhoneNo = (EditText)findViewById(R.id.etenaphoneno);
        SendOtp = (Button)findViewById(R.id.btnsendotp);
        OTP = (ImageView)findViewById(R.id.ivotpanim);

        SendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneno = PhoneNo.getText().toString().trim();
                if (phoneno.length() != 10){
                    Toast.makeText(EnterNumberActivity.this, "Please enter a valid 10-digit phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(EnterNumberActivity.this, "Sending OTP...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.putExtra("PhoneNumber", phoneno);
                startActivity(intent);

            }
        });
    }
}
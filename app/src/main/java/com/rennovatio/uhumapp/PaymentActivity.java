package com.rennovatio.uhumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.util.HashMap;

public class PaymentActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    int amount;
    ImageView Back;
    Button Donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Donate = (Button)findViewById(R.id.btndonate);
        Back = (ImageView)findViewById(R.id.ivmhaback);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(PaymentActivity.this);
                dialog.setContentView(R.layout.donation_layout);

                Button action = (Button) dialog.findViewById(R.id.btnumlaction);
                final EditText Amount = (EditText) dialog.findViewById(R.id.etamount);

                action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        amount = Integer.parseInt(Amount.getText().toString().trim());
                        dialog.dismiss();
                        startPayment(amount);
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    public void startPayment(int amount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_KD8CX4fn4bT5Wa");

        try {

            JSONObject options = new JSONObject();
            options.put("name", "Renovatio");
            options.put("description", "");
            options.put("currency", "INR");
            options.put("amount", ""+(amount*100));
            options.put("theme", new JSONObject().put("color","#0000ff"));
            checkout.open(this, options);

        } catch(Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            paymentData.getOrderId();
            String transactionID = paymentData.getPaymentId();

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.payment_success_layout);

            Button action = (Button) dialog.findViewById(R.id.btnumlaction);
            TextView subText = (TextView)dialog.findViewById(R.id.tvumlsubtext);

            subText.setText("Your transaction of Rs. 1 was successful with Transaction ID: "+transactionID+" was successful");

            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(PaymentActivity.this, MainPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(final int i, String s, PaymentData paymentData) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.payment_failed_layout);

        Button action = (Button) dialog.findViewById(R.id.btnumlaction);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(PaymentActivity.this, MainPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
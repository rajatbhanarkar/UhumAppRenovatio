package com.rennovatio.uhumapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileSetupActivity extends AppCompatActivity {

    ImageView AddPic, Male, Female;
    CircleImageView ProfilePic;
    Button Register;
    File file;
    EditText FullName, Email, Age;
    String phno = "", Gender = "Male";

    UserDetails userDetails;

    ProgressDialog progressDialog;
    StorageReference storageReference;
    DatabaseReference myRef;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Dialog LoaderDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        FullName = (EditText)findViewById(R.id.etpsafullname);
        Email = (EditText)findViewById(R.id.etpsaemailadd);
        Age = (EditText)findViewById(R.id.etpsaage);
        Register = (Button)findViewById(R.id.btnpsasubmit);
        ProfilePic = (CircleImageView) findViewById(R.id.ivpsaprofilepic);
        AddPic = (ImageView)findViewById(R.id.ivpsaaddpic);
        Male = (ImageView)findViewById(R.id.ivpsamale);
        Female = (ImageView)findViewById(R.id.ivpsafemale);

        sharedPreferences = getSharedPreferences("UhumAppSharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        phno = sharedPreferences.getString("PhoneNumber", "");

        myRef = FirebaseDatabase.getInstance().getReference().child("UserDetails");

        AddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(ProfileSetupActivity.this)
                        .cropSquare()
                        .compress(512)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    LoaderDialog = new Dialog(ProfileSetupActivity.this);
                    View vieww = getLayoutInflater().inflate(R.layout.loader_layout, null);
                    ProgressBar progressBar = (ProgressBar) vieww.findViewById(R.id.spinKit);
                    DoubleBounce doubleBounce = new DoubleBounce();
                    progressBar.setIndeterminateDrawable(doubleBounce);
                    LoaderDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    LoaderDialog.setContentView(vieww);
                    LoaderDialog.show();

                    userDetails = new UserDetails();
                    userDetails.setName(""+FullName.getText().toString().trim());
                    userDetails.setMobNo(""+phno);
                    userDetails.setEmail(""+Email.getText().toString().trim());
                    userDetails.setAge(""+Age.getText().toString().trim());

                    if (file != null) {
                        storageReference = FirebaseStorage.getInstance().getReference();
                        storageReference = storageReference.child("UserProfileImages/" + file.getName());
                        storageReference.putFile(Uri.fromFile(file)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        userDetails.setProfPic(""+uri.toString());

                                        phno = phno.replace("+91", "");
                                        myRef.child(phno).setValue(userDetails);

                                        LoaderDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Profile Setup Successful!", Toast.LENGTH_SHORT).show();

                                        editor.putInt("SetUp", 1);
                                        editor.putString("UserDetails", new Gson().toJson(userDetails));
                                        editor.apply(); editor.commit();

                                        Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        LoaderDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error! Please Try Again Later!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                LoaderDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Error! Please Try Again Later!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        userDetails.setProfPic(""+Gender);

                        phno = phno.replace("+91", "");
                        myRef.child(phno).setValue(userDetails);

                        LoaderDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Profile Setup Successful!", Toast.LENGTH_SHORT).show();

                        editor.putInt("SetUp", 1);
                        editor.putString("UserDetails", new Gson().toJson(userDetails));
                        editor.apply(); editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Male.getPaddingTop() == 20){
                    Male.setPadding(0, 0, 0, 0);
                }
                else{
                    Male.setPadding(20, 20, 20, 20);
                }
                Female.setPadding(0, 0, 0, 0);
                Gender = "Male";
            }
        });

        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Female.getPaddingTop() == 20){
                    Female.setPadding(0, 0, 0, 0);
                }
                else{
                    Female.setPadding(20, 20, 20, 20);
                }
                Male.setPadding(0, 0, 0, 0);
                Gender = "Female";
            }
        });
    }

    boolean validate(){
        if((FullName.getText().toString().trim().length() == 0) || (FullName.getText().toString().trim().split(" ").length<2)){
            Toast.makeText(this, "Please enter your Full Name! (FirstName LastName)", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if ((Email.getText().toString().trim().length() == 0) || !(Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString().trim()).matches())){
            Toast.makeText(this, "Please enter valid email address!", Toast.LENGTH_SHORT).show();
            return false;
        }else if (Age.getText().toString().trim().length() == 0){
            Toast.makeText(this, "Please enter valid age!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(file == null && Gender.length() == 0){
            Toast.makeText(this, "Please upload your photo or choose an avatar!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            file = ImagePicker.Companion.getFile(data);
            Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
            ProfilePic.setImageBitmap(image);
        }
    }
}
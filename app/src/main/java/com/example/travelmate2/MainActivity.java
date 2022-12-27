package com.example.travelmate2;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CardView cardViewProfile;
    Button placesDetail;
    ImageView btnWeather,btnMemmory,btnAboutUs, btnMap;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //slider
         ImageSlider imageSlider = findViewById(R.id.imageSlider);
         ArrayList<SlideModel> slideModels = new ArrayList<>();
         slideModels.add(new SlideModel(R.drawable.slide1, ScaleTypes.FIT));
         slideModels.add(new SlideModel(R.drawable.slide2, ScaleTypes.FIT));
         slideModels.add(new SlideModel(R.drawable.slide3, ScaleTypes.FIT));

         imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        //endslider
        cardViewProfile = findViewById(R.id.cardViewProfile);
        placesDetail = findViewById(R.id.placesDetail);
        btnWeather = findViewById(R.id.btnWeather);
        btnMemmory = findViewById(R.id.btnMemmory);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnMap = findViewById(R.id.btnMap);
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
        String usernameUser = intent.getStringExtra("username");
        String passwordUser = intent.getStringExtra("password");

        cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        placesDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlacesActivity.class);
                startActivity(intent);
            }
        });
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        btnMemmory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Memory_Activity.class);
                startActivity(intent);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }

}
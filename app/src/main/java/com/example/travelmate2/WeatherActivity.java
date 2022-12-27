package com.example.travelmate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {
    private RelativeLayout wetherRL;
    private ProgressBar loadingPB;
    private TextView cityNameTV, tempperatureTV, conditionTV;
    private TextInputEditText cityEdt;
    private ImageView backIV, iconIV, searchIV, btnBackhome;
    private RecyclerView weatherRV;
    private ArrayList<WeatherRVModals> weatherRVModalsArrayList;
    private WeatherRVAdapter weatherRVAdapter;
    private LocationManager locationManager;
    private int PERMISSION_CODE = 1;
    private String cityName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_weather);
        wetherRL = findViewById(R.id.idRWeather);
        loadingPB =findViewById(R.id.idPBLoading);
        cityNameTV = findViewById(R.id.idTVCityName);
        tempperatureTV = findViewById(R.id.idTVTemperature);
        conditionTV = findViewById(R.id.idTVConditon);
        weatherRV = findViewById(R.id.idRVWeather);
        btnBackhome = findViewById(R.id.btnBackhome);
       // cityEdt = findViewById(R.id.idEdtCity);
        backIV = findViewById(R.id.idIVBlack);
        iconIV = findViewById(R.id.idIVIcon);
       // searchIV = findViewById(R.id.idSearch);
        weatherRVModalsArrayList = new ArrayList<>();

        btnBackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


//        weatherRVAdapter = new WeatherRVAdapter(this, weatherRVModalsArrayList);
//        weatherRV.setAdapter(weatherRVAdapter);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED ){
//            ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
//        }
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        //cityName =getCityName(location.getLongitude(), location.getLatitude());
//        cityName ="new york";
//     //   getWeatherInfo(cityName);
//        searchIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String city = cityEdt.getText().toString();
//                if(city.isEmpty()){
//                    Toast.makeText(WeatherActivity.this, "Please enter your city...", Toast.LENGTH_SHORT).show();
//                }else{
//                    cityNameTV.setText(cityName);
//             //       getWeatherInfo(city);
//                }
//            }
//        });
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == PERMISSION_CODE){
//
//            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this, "Permission granted...", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "Please provide the permission...", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//    }
//
//    private String getCityName(double longitude, double latitude){
//        String cityName = "new york";
//        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
//        try{
//            List<Address> addresses = gcd.getFromLocation(latitude, longitude, 10);
//            for(Address adr : addresses){
//                if(adr != null){
//                    String city = adr.getLocality();
//                    if(city!=null && !city.equals("")){
//                        cityName =  city;
//                    }else{
//                        Log.d("TAG", "CITY NOT FOUND");
//                        Toast.makeText(this, "User City Not Found...", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return cityName;
//    }
//    private void getWeatherInfo(String cityName){
//        String url = "http://api.weatherapi.com/v1/forecast.json?key=14911ae583b74e06a9214248222512&q="+cityName+"&days=1&aqi=yes&alerts=yes";
//        cityNameTV.setText(cityName);
//        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                loadingPB.setVisibility(View.GONE);
//                wetherRL.setVisibility(View.VISIBLE);
//                weatherRVModalsArrayList.clear();
//                try {
//                    String temperature = response.getJSONObject("current").getString("temp_c");
//                    tempperatureTV.setText(temperature + "℃");
//                    int isday = response.getJSONObject("current").getInt("is_day");
//                    String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
//                    String conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
//                    Picasso.get().load("http:".concat(conditionIcon)).into(iconIV);
//                    conditionTV.setText(condition);
//                    if(isday == 1){
//                        Picasso.get().load("https://images.unsplash.com/photo-1564750975191-0ed807751adf?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80").into(backIV);
//
//                    }else{
//                        Picasso.get().load("https://images.unsplash.com/photo-1435224654926-ecc9f7fa028c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80").into(backIV);
//                    }
//                    JSONObject forecastObj = response.getJSONObject("forecast");
//                    JSONObject forcast0=  forecastObj.getJSONArray("forecastday").getJSONObject(0);
//                    JSONArray hourArray = forcast0.getJSONArray("hour");
//                    for(int i=0;i<hourArray.length();i++){
//                        JSONObject hourObj = hourArray.getJSONObject(i);
//                        String time = hourObj.getString("time");
//                        String temper = hourObj.getString("temp_c");
//                        String img = hourObj.getJSONObject("condition").getString("icon");
//                        String wind = hourObj.getString("wind_kph");
//                        weatherRVModalsArrayList.add(new WeatherRVModals(time, temper, img, wind));
//
//
//                    }
//                    weatherRVAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(WeatherActivity.this,"Please enter valid city name .. ", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }
}
package com.yadavsk.covid_19update;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Detail_Activity extends AppCompatActivity {

    private  int positionCountry;
    TextView tvCountry,tvCases,tvTodayCases,tvRecovered,tvCritical,tvActive,tvTodayDeaths,tvTotalDeaths;

    private AdView mAdView;

    private final  String TAG = "GGLADS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        mAdView = findViewById(R.id.adView);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded: ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Log.d(TAG, "onAdFailedToLoad: ");
            }

            @Override
            public void onAdOpened() {
                Log.d(TAG, "onAdOpened: ");
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG, "onAdClicked: ");
            }

            @Override
            public void onAdClosed() {
                Log.d(TAG, "onAdClosed: ");
            }
        });


        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);


        getSupportActionBar().setTitle("Deatails of"+ AffectedCountries.country_modelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvRecovered = findViewById(R.id.tvReovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);


        tvCountry.setText(AffectedCountries.country_modelsList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.country_modelsList.get(positionCountry).getCases());
        tvTodayCases.setText(AffectedCountries.country_modelsList.get(positionCountry).getTodayCases());
        tvRecovered.setText(AffectedCountries.country_modelsList.get(positionCountry).getRecovered());
        tvCritical.setText(AffectedCountries.country_modelsList.get(positionCountry).getCritical());
        tvActive.setText(AffectedCountries.country_modelsList.get(positionCountry).getActive());
        tvTodayDeaths.setText(AffectedCountries.country_modelsList.get(positionCountry).getTodayDeaths());
        tvTodayDeaths.setText(AffectedCountries.country_modelsList.get(positionCountry).getTodayDeaths());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
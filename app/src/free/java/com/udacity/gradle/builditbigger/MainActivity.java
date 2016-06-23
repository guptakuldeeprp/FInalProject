package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;
import android.widget.Toast;

import com.example.Joke;
import com.example.kuldeepgupta.jokelibrary.DisplayJoke;
import com.udacity.gradle.builditbigger.utils.AsyncResponse;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();
    private ProgressDialog progressDialog = null;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                initiateTellJoke();
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        Log.i(TAG,"Loading a new interstitial ad..");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){


        if(interstitialAd.isLoaded()) {
            Log.i(TAG,"Interstitial ad is loaded. Will be shown");
            interstitialAd.show();
        }
        else {
            Log.i(TAG,"Interstitial ad is not loaded. Will not be shown");
            initiateTellJoke();
        }

    }

    private void initiateTellJoke() {
        progressDialog = ProgressDialog.show(this, "",
                "Loading. Please wait...", true);

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.setAsyncResponse(new AsyncResponse<String>() {
            @Override
            public void processFinish(String result) {
                progressDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, DisplayJoke.class);
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.joke_str),result);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        asyncTask.execute(new Pair<Context, String>(this, null));
    }




}

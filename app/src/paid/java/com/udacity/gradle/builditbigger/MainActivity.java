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
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;
import android.widget.Toast;

import com.example.Joke;
import com.example.kuldeepgupta.jokelibrary.DisplayJoke;
import com.udacity.gradle.builditbigger.utils.AsyncResponse;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

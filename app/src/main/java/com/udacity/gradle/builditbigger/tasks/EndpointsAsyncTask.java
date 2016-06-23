package com.udacity.gradle.builditbigger.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.Joke;
import com.example.kuldeep.gupta.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.utils.AsyncResponse;
import com.udacity.gradle.builditbigger.utils.AsyncTaskWithResponse;

import java.io.IOException;

/**
 * Created by kuldeep.gupta on 20-06-2016.
 */
public class EndpointsAsyncTask extends AsyncTaskWithResponse<Pair<Context, String>, Void, String> {

    private static final String TAG = EndpointsAsyncTask.class.getName();

    private static MyApi myApiService = null;
    //private Context context;
    private AsyncResponse<String> responseCallback;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            Log.i(TAG,"Building MyApi service");
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        //context = params[0].first;
        //String name = params[0].second;

        try {
            //return myApiService.sayHi(name).execute().getData();
            Log.i(TAG,"Calling tellJoke api..");
             return myApiService.tellJoke().execute().getJoke();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(responseCallback != null)
            responseCallback.processFinish(result);
        else
            Log.w(TAG,"No response callback is set!");
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAsyncResponse(AsyncResponse<String> response) {
        this.responseCallback = response;
    }
}

package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.test.ApplicationTestCase;
import android.test.MoreAsserts;
import android.util.Log;

import com.example.Joke;
import com.example.kuldeepgupta.jokelibrary.DisplayJoke;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.utils.AsyncResponse;

import junit.framework.Assert;

import java.util.concurrent.ExecutionException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testVerifyTellJoke() throws ExecutionException, InterruptedException {
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();

        // Making the call synchronous.
        String joke = asyncTask.execute().get();
        Assert.assertNotNull(joke);
        MoreAsserts.assertNotEqual("joke should not be empty", "", joke.trim());
        Log.w("ApplicationTest","Test successful!");
        /*asyncTask.setAsyncResponse(new AsyncResponse<String>() {
            @Override
            public void processFinish(String result) {
                MoreAsserts.assertNotEqual("joke should not be empty", "", result.trim());
            }
        });*/
    }
}
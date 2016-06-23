package com.udacity.gradle.builditbigger.utils;

import android.os.AsyncTask;

/**
 * Created by kuldeep.gupta on 20-06-2016.
 */
public abstract class AsyncTaskWithResponse<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    public abstract void setAsyncResponse(AsyncResponse<Result> response);

}

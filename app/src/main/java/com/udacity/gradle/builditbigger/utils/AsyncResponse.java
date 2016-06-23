package com.udacity.gradle.builditbigger.utils;

/**
 * Created by kuldeep.gupta on 20-06-2016.
 */
public interface AsyncResponse<T> {

    void processFinish(T result);
}

package com.nocom.movieapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Looper;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Moha on 8/30/2017.
 */
class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    // --Commented out by Inspection (9/22/2017 2:14 PM):private static final String LOG_TAG = MovieLoader.class.getName();
    private final String murl;

    public MovieLoader(Context context, String url) {
        super(context);
        murl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<Movie> loadInBackground() {

        if (Looper.myLooper()==null)
            Looper.prepare();
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Movie> movies;
        try {
            movies = QueryUtlis.fetchMovieData(murl);
        } catch (JSONException e) {
            e.printStackTrace();
            return null ;
        }
        return movies;

    }
}


package com.nocom.movieapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.nocom.movieapp.R.id.overview;
public class MovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    public static final String Log_TAG = MovieActivity.class.getName();
    private static final String MOST_POPULAR = "https://api.themoviedb.org/3/movie/popular?api_key=b549ea5986562cb818e16877886899df&language=en-US&page=1";
    private static final String HIGHEST_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key=b549ea5986562cb818e16877886899df&language=en-US&page=1";
    private static final int MOVIE_LOADER_ID = 1;
    TextView overvview;
    TextView title;
    TextView releasedate;
    TextView rating;
    int condtion  =0 ;
    private Bundle bundle ;
    private ImageView imageView;
    private String searched = MOST_POPULAR;
    private MovieAdapter mAdapter;
    private LoaderManager loaderManager;
    private TextView emptytext;
    private GridView movieListView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);

        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        overvview=  (TextView)findViewById(overview);
        title = (TextView)findViewById(R.id.title);
        releasedate=(TextView)findViewById(R.id.releasedate);
        rating = (TextView)findViewById(R.id.title) ;
        imageView = (ImageView) findViewById(R.id.photo2);
        movieListView = (GridView) findViewById(R.id.gridview);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        emptytext = (TextView) findViewById(R.id.empty_view);
        movieListView.setEmptyView(emptytext);
        movieListView.setAdapter(mAdapter);
        loaderManager = getLoaderManager();
        loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        connect();

    }
    private void connect() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.restartLoader(MOVIE_LOADER_ID, bundle, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            emptytext.setText(R.string.internetproblem);
            progressBar.setVisibility(View.INVISIBLE);
            // Update empty state with no connection error message

        }

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie currentMovie = mAdapter.getItem(position);
                Context context = MovieActivity.this;
                Class destinationActivity = MovieDetailActivity.class;
                Intent startChildActivityIntent = new Intent(context, destinationActivity);
                String overview = currentMovie != null ? currentMovie.getOverview() : null;
                String title = currentMovie.gettitle();
                String image = currentMovie.getPoster2();
                String release = currentMovie.getreleasedate();
                double rate = currentMovie.getvoteaverge();
                String stringdouble= Double.toString(rate);
                startChildActivityIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
                startChildActivityIntent.putExtra(Intent.EXTRA_TEXT,overview);
                startChildActivityIntent.putExtra(Intent.EXTRA_REFERRER,release);
                startChildActivityIntent.putExtra("image", image);
                startChildActivityIntent.putExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME,stringdouble);
                startActivity(startChildActivityIntent);

            }
        });
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MovieLoader(this, searched);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        progressBar.setVisibility(View.INVISIBLE);

        mAdapter.clear();
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            case R.id.most_popular:
                if (condtion ==1) {
                    mAdapter.clear();
                    searched = MOST_POPULAR;
                    condtion=0;
                    connect();

                }
                else {

                    Toast.makeText(getApplicationContext(), "Already chosen chose the another option",
                            Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.highest_rated:
                if (condtion==0) {
                    mAdapter.clear();
                    searched = HIGHEST_RATED;
                    condtion=1;
                    connect();

                }

                else {

                    Toast.makeText(getApplicationContext(), "Already chosen chose the another option ",
                            Toast.LENGTH_LONG).show();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

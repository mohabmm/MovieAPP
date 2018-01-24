package com.nocom.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView iamgt;
    private TextView overvview;
    private TextView title;
    private String gettingImageUrl;
    private TextView releasedate;
    private TextView rating;
    private TextView ratingt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_);
       // ratingt= (TextView)findViewById(R.id.moha);

        setTitle(R.string.MovieDetail);
        iamgt = (ImageView) findViewById(R.id.image);//image


        overvview = (TextView) findViewById(R.id.overview);
        title = (TextView) findViewById(R.id.title);
        releasedate = (TextView) findViewById(R.id.releasedate);
        rating = (TextView) findViewById(R.id.rate);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT) && intentThatStartedThisActivity.hasExtra(Intent.EXTRA_SHORTCUT_NAME) && intentThatStartedThisActivity.hasExtra(Intent.EXTRA_REFERRER) && intentThatStartedThisActivity.hasExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME)) {
            String textEntered = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            String textentered2 = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_SHORTCUT_NAME);
            String textentered3 = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_REFERRER);
            String textentered4 = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME);

            overvview.setText(textEntered);
            title.setText(textentered2);
            releasedate.setText(textentered3);
            rating.setText(textentered4);
            Intent getImage = getIntent();
            gettingImageUrl = String.valueOf(getImage.getStringExtra("image"));
            Picasso.with(this).load(gettingImageUrl)
                    .placeholder(R.drawable.progress_animation)
                    .into(iamgt);

        }
        ActionBar actionBar = this.getSupportActionBar();

        // Set the action bar back button to look like an up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the MocieActivity
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


}


package com.nocom.movieapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Moha on 8/28/2017.
 */

class MovieAdapter extends ArrayAdapter<Movie> {
    // --Commented out by Inspection (9/22/2017 2:13 PM):private static final String TAG = MovieAdapter.class.getSimpleName();
    private final Context context;
    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context,0,movies);
        this.context = context;
    }

    @NonNull
    @Override

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View GridView = convertView;
        if (GridView == null) {
            GridView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list, parent, false);
        }
        Movie movie =  getItem(position);
        ImageView iamgt = (ImageView)GridView.findViewById(R.id.photo2);

        if (movie != null) {
            Picasso
                    .with(context)
                    .load(movie.getPoster2())
                    .resize(6000, 2000)
                    .onlyScaleDown()
                    .placeholder(R.drawable.progress_animation)
                    .into(iamgt);
        }

        return GridView;
    }
}

package com.nocom.movieapp;

import android.os.Parcel;
import android.os.Parcelable;



public class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private final String overview;
    private final String release_date;
    private final double voteaverge ;
    private final String title;
    private final String poster2 ;
    //    public int poster;
    private int poster1;


    public Movie(String nposter2, String noverview, String nrelease_date, double nvote_averge, String ntitle) {
        poster2=nposter2;
        overview=noverview;
        release_date=nrelease_date;
        voteaverge=nvote_averge;
        title=ntitle;
    }

    private Movie(Parcel in) {
        poster1 = in.readInt();
        overview = in.readString();
        release_date = in.readString();
        voteaverge = in.readDouble();
        title = in.readString();
        poster2 = in.readString();
    }

    public String gettitle(){

        return title ;
    }


    public String getPoster2() {
        return poster2;
    }

    public String getreleasedate() {
        return release_date;

    }


    public double getvoteaverge() {
        return voteaverge;
    }


    public String getOverview() {
        return overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(poster1);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeDouble(voteaverge);
        dest.writeString(title);
        dest.writeString(poster2);
    }
}
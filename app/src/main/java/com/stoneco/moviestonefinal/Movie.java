package com.stoneco.moviestonefinal;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    // Information from the JSON that i want to use
     String title;
     String poster;
     String overview;
     String date;
     String backdrop;
     boolean vidExist;
     boolean isAdult;
     double voteAverage;
     int id;

     public Movie(){

     }

    public  Movie(JSONObject jsonObject) throws JSONException{ //constructor takes in a JSONobject ex results[0]

        //All of these things we can get from the movie API
        title = jsonObject.getString("title");
        poster = jsonObject.getString("poster_path");
        overview = jsonObject.getString("overview");
        date = jsonObject.getString("release_date");
        vidExist = jsonObject.getBoolean("video");
        isAdult = jsonObject.getBoolean("adult");
        voteAverage = jsonObject.getDouble("vote_average");
        backdrop = jsonObject.getString("backdrop_path");
        id = jsonObject.getInt("id");


    }


    public static List<Movie> jsonToMoive(JSONArray a) throws JSONException{ // make a list of movies and then return them

        //Parse through Json Array and add all movies into the movie array
        List<Movie> movies = new ArrayList<>();
        List<String>movieTitle = new ArrayList<>();

        for(int i = 0; i < a.length(); i++){

            if(!movieTitle.contains(a.getJSONObject(i).getString("title"))) {
                movies.add(new Movie(a.getJSONObject(i)));
                movieTitle.add(a.getJSONObject(i).getString("title"));
            }
        }

        Log.d("moviesTest", movies.toString());
        return movies;

    }

    public boolean isFamous(){

        if(voteAverage > 7.0)
            return true;

        return false;

    }

    public String toString(){

        return this.title;

    }

    public int compareTo(Movie x){

        if(this.title.equals(x.getTitle()))
            return 0;

        return -1;

    }

    //getter methods

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return String.format("https:/image.tmdb.org/t/p/w342/%s",poster);
    }

    public String getBackdrop(){return String.format("https:/image.tmdb.org/t/p/w342/%s",backdrop); }

    public String getOverview() {
        return overview;
    }

    public String getDate() {
        return date;
    }

    public boolean isVidExist() {
        return vidExist;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getId(){return id;}

}

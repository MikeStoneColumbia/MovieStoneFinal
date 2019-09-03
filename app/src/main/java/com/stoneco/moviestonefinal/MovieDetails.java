package com.stoneco.moviestonefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieDetails extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyDpzz0p_smRVF_Ki5UzUdQrVvEtO7X4Vbc";
    private static final String MOVIE_API_VID_KEY = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    TextView detailTitle;
    TextView detailOverview;
    RatingBar ratingBar;
    Movie movie;
    YouTubePlayerView youtTubeVid;
    List<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //Setting up Basic Views and handlers/retrieving data

        AsyncHttpClient handler = new AsyncHttpClient();
        detailTitle = findViewById(R.id.detailTitle);
        detailOverview = findViewById(R.id.detailOverview);
        ratingBar = findViewById(R.id.ratingBar);
        youtTubeVid = findViewById(R.id.player);
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        //Throwing in info to the TextView and the rating bar
        detailTitle.setText(movie.getTitle());
        detailOverview.setText(movie.getOverview());
        ratingBar.setRating((float) (movie.getVoteAverage() / 2.0));


        handler.get(String.format(MOVIE_API_VID_KEY, movie.getId()), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {

                    JSONArray movieInfo = response.getJSONArray("results");

                    if (movieInfo.length() == 0)
                        return;

                    JSONObject videoInfo = movieInfo.getJSONObject(0);

                    String videoLink = videoInfo.getString("key");

                    initializeVideo(videoLink);

                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });


    }

    public void initializeVideo ( final String youTubeLink){

        //YouTube Handler Stuff
        youtTubeVid.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("VidStatus", "Initializing was a Success");

                if(movie.isFamous())
                    youTubePlayer.loadVideo(youTubeLink);
                else
                    youTubePlayer.cueVideo(youTubeLink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Log.d("VidStatus", "Vid failed to view");

            }
        });

    }
}
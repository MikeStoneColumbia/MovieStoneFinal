package com.stoneco.moviestonefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating stuff
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();
        final ComplexRecyclerViewAdapter adapter = new ComplexRecyclerViewAdapter(movies,this);

        //Setting the adapter
        rvMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvMovies.setAdapter(adapter);

        //Making Async Object to use the API
        AsyncHttpClient webHandler = new AsyncHttpClient();
        webHandler.get(MOVIE_URL,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) { // if we succeed in getting a return
                super.onSuccess(statusCode, headers, response);

                try{

                    JSONArray movieInfo = response.getJSONArray("results"); // get the "results" Array and store it;
                    movies.addAll(Movie.jsonToMoive(movieInfo)); // In Movie class there is a method called jsonToMovie that returns an ArrayList of Movies
                    adapter.notifyDataSetChanged(); // Something we can see in logcat when new info is loaded
                    Log.d("movieInfo", movieInfo.toString());

                }catch (JSONException e){

                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("ERROR", throwable.toString());

            }
        });


    }
}

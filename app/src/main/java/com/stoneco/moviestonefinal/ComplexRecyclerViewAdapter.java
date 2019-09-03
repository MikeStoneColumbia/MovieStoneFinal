package com.stoneco.moviestonefinal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.annotation.GlideModule;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.Target;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{

    Context context;
    private List<Movie> movies;
    private final int FAMOUS = 1, BAD = 0;
    ImageView playButton;

    public ComplexRecyclerViewAdapter(List<Movie> movies, Context context){

        this.movies = movies;
        this.context = context;

    }

    @Override
    public int getItemCount(){

        return movies.size();

    }

    @Override
    public int getItemViewType(int position) {

        if(movies.get(position).isFamous())
            return FAMOUS;

        return BAD;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType){

            case FAMOUS:
                View display2 = inflater.inflate(R.layout.movie_display2,viewGroup,false);
                viewHolder = new PopularMovieView(display2);
                break;

            case BAD:
                View display1 = inflater.inflate(R.layout.movie_display,viewGroup,false);
                viewHolder = new BadMovieView(display1);
                break;

             default:
                 View display3 = inflater.inflate(R.layout.movie_display,viewGroup,false);
                 viewHolder = new BadMovieView(display3);


        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch(viewHolder.getItemViewType()){

            case FAMOUS:
                PopularMovieView display2 = (PopularMovieView) viewHolder;
                configurePopularMovieView(display2,position);
                break;

            case BAD:
                BadMovieView display1 = (BadMovieView) viewHolder;
                configureBadMovieView(display1,position);

            default:
                BadMovieView display3 = (BadMovieView) viewHolder;
                configureBadMovieView(display3,position);
        }



    }

    private void configurePopularMovieView(PopularMovieView display1, int position){

        final Movie movie = (Movie) movies.get(position);

        playButton = display1.getPlayButton();

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GlideApp.with(context).load(movie.getBackdrop()).transform(new RoundedCornersTransformation(40,0)).override(920,Target.SIZE_ORIGINAL).into(display1.getImageView());
            GlideApp.with(context).load(R.drawable.play_button).override(320,320).into(playButton);
            display1.getMovieTitle().setText(movie.getTitle());
            display1.getMovieOverview().setText(movie.getOverview());
            playButton.setAlpha(135);
        }
        else {
            GlideApp.with(context).load(movie.getPoster()).transform(new RoundedCornersTransformation(40, 0)).override(120, Target.SIZE_ORIGINAL).into(display1.getImageView());
            playButton.setImageResource(R.drawable.play_button);
            display1.getMovieTitle().setText(movie.getTitle());
            display1.getMovieOverview().setText(movie.getOverview());
            playButton.setAlpha(100);
        }






        display1.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detailScreen = new Intent(context,MovieDetails.class);
                detailScreen.putExtra("movie",Parcels.wrap(movie));
                context.startActivity(detailScreen);

            }
        });

    }

    private void configureBadMovieView(BadMovieView display, int position){


        final Movie movie = (Movie) movies.get(position);


        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            GlideApp.with(context).load(movie.getBackdrop()).transform(new RoundedCornersTransformation(40,0)).override(920,Target.SIZE_ORIGINAL).into(display.getPoster());
        else
            GlideApp.with(context).load(movie.getPoster()).transform(new RoundedCornersTransformation(40,0)).override(120,Target.SIZE_ORIGINAL).into(display.getPoster());


        if(movie != null){

            display.getTitle().setText(movie.getTitle());
            display.getOverview().setText(movie.getOverview());
            //Glide.with(context).load(movie.getPoster()).into(display.getPoster());



            display.getContainer().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent detialScreen = new Intent(context, MovieDetails.class);
                    detialScreen.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(detialScreen);


                }
            });

        }

    }


}

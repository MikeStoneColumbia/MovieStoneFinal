package com.stoneco.moviestonefinal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopularMovieView extends RecyclerView.ViewHolder {

    private ImageView backdrop;
    private ImageView playButton;
    private TextView movieTitle;
    private TextView movieOverview;
    private RelativeLayout goodContainer;
    private ImageView hot;

    public PopularMovieView(View v){

        super(v);

        hot = (ImageView)v.findViewById(R.id.hot);
        backdrop = (ImageView)v.findViewById(R.id.movieImage2);
        goodContainer = (RelativeLayout)v.findViewById(R.id.goodContainer);
        playButton = (ImageView)v.findViewById(R.id.playButtonBad);
        movieTitle = (TextView)v.findViewById(R.id.movieTitle);
        movieOverview = (TextView)v.findViewById(R.id.movieDesc);

    }

    public ImageView getImageView(){

        return backdrop;
    }

    public ImageView getHot() {
        return hot;
    }

    public void setHot(ImageView hot) {
        this.hot = hot;
    }

    public ImageView getPlayButton(){

        return playButton;

    }

    public TextView getMovieTitle(){

        return movieTitle;

    }

    public TextView getMovieOverview(){

        return movieOverview;

    }

    public RelativeLayout getContainer(){

        return goodContainer;

    }


    public void setBackdrop(ImageView backdrop) {
        this.backdrop = backdrop;
    }

}

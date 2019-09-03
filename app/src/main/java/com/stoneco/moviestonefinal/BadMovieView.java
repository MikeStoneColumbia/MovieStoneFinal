package com.stoneco.moviestonefinal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BadMovieView extends RecyclerView.ViewHolder {

    private ImageView poster;
    private TextView title;
    private TextView overview;
    private RelativeLayout badContainer;

    public BadMovieView(View v){
        super(v);

        poster = (ImageView)v.findViewById(R.id.movieImage2);
        title = (TextView)v.findViewById(R.id.movieTitle);
        overview = (TextView)v.findViewById(R.id.movieDesc);
        badContainer = (RelativeLayout)v.findViewById(R.id.goodContainer);
    }

    public TextView getTitle(){

        return title;

    }

    public TextView getOverview(){

        return overview;

    }

    public RelativeLayout getContainer(){

        return badContainer;

    }

    public ImageView getPoster(){

        return poster;

    }
}

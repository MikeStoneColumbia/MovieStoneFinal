package com.stoneco.moviestonefinal;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapterView extends RecyclerView.Adapter<MovieAdapterView.ViewHolder>{

    List<Movie> movies;
    Context context;

    public MovieAdapterView(List<Movie> movies, Context context){

        this.movies = movies;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_display,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = movies.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView moviePoster;
        ImageView moviePosterHoz;
        TextView movieTitle;
        TextView movieOverview;

        public ViewHolder(View itemView){

            super(itemView);
            moviePoster = itemView.findViewById(R.id.movieImage2);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieOverview = itemView.findViewById(R.id.movieDesc);
            moviePosterHoz = itemView.findViewById(R.id.movieImage2);

        }

        public void bind(Movie movie){

            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                Glide.with(context).load(movie.getBackdrop()).into(moviePoster);
            else
                Glide.with(context).load(movie.getPoster()).into(moviePoster);

            movieTitle.setText(movie.getTitle());
            movieOverview.setText(movie.getOverview());

        }
    }



}

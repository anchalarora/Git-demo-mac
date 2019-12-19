package com.example.anvesh.mytestapplication;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context context;
    private List<Movies> movie = new ArrayList<>();

    public MovieListAdapter(Context context, List<Movies> movie) {

        this.context = context;
        this.movie = movie;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_adapter, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movies movies = getMovieItem(position);
        holder.bindContent(movies);

        String path = "https://image.tmdb.org/t/p/w500"+movies.getPoster_path();

        Picasso.with(context).load(path)
                .fit().centerInside()
                .into(holder.imageView);

        holder.name.setText(movies.getOriginal_title());
        holder.date.setText(movies.getRelease_date());
        holder.adult.setText(movies.isAdult()?" A ":" U/A ");

    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    private Movies getMovieItem(int position) {
        return movie.get(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Movies movies;

        TextView name;
        ImageView imageView;
        TextView date;
        TextView adult;
        ImageButton button;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movieImage);
            name = (TextView) itemView.findViewById(R.id.movieName);
            date = (TextView) itemView.findViewById(R.id.movieDate);
            button = (ImageButton) itemView.findViewById(R.id.movieDetail);
            adult = (TextView) itemView.findViewById(R.id.movieAdult);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d(Const.TAG," List Click 2 " );
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity) context).addInitialFragment2(movies);
                        }
                    }, 200);

                }
            });
        }

        void bindContent(Movies movies) {
            this.movies = movies;
        }

        @Override
        public void onClick(View v) {

            Log.d(Const.TAG," List Click " );
            /*new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity) context).addInitialFragment2(movies);
                }
            }, 200);  */
        }
    }

}

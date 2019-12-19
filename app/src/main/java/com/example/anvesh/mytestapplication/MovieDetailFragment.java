package com.example.anvesh.mytestapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    ImageView imageView;
    TextView des;
    TextView title;
    RatingBar ratingBar;
    Movies movies;

    public MovieDetailFragment() {
        // Required empty public constructor
    }


    public static MovieDetailFragment newInstance(Movies key) {
        Bundle args = new Bundle();
        args.putParcelable("Key", key);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        movies = getArguments().getParcelable("Key");

        Log.d(Const.TAG, "  Main -  "+movies);

        imageView = (ImageView) rootView.findViewById(R.id.movieImages);
        title = (TextView) rootView.findViewById(R.id.movieTitle);
        des = (TextView) rootView.findViewById(R.id.movieDes);
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);

        assert movies != null;

        String path = "https://image.tmdb.org/t/p/w500"+movies.getPoster_path();

        Picasso.with(getActivity()).load(path)
                .fit().centerInside()
                .into(imageView);

        title.setText(movies.getOriginal_title());
        des.setText(movies.getOverview());
        ratingBar.setRating( movies.getVote_average()/2 );

        //loadJSON();

        return rootView;
    }

    private void loadJSON(){

        String url = String.format(Const.MOVIE_DETAILS, movies.getId());
        Log.d(Const.TAG," URL - "+url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<Movies> call = request.getMovies();
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                Log.d(Const.TAG," Detail Response "+response.body());

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d(Const.TAG," Error - "+t.getMessage());
            }
        });
    }

}

package com.example.anvesh.mytestapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    LinearLayoutManager linearLayoutManager;
    Context context;
    private List<Movies> movie = new ArrayList<>();
    MovieListAdapter movieListAdapter;
    private RecyclerView mRecyclerView;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        context = getActivity();

        linearLayoutManager = new LinearLayoutManager(context);
        movie = new ArrayList<>();

        movieListAdapter = new MovieListAdapter(context, movie);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        assert mRecyclerView != null;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        registerForContextMenu(mRecyclerView);
        mRecyclerView.setAdapter(movieListAdapter);

        loadJSON();

        return rootView;
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.MOVIES_LIST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<MoviesListResponse> call = request.getMovieList();
        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {

                Log.d(Const.TAG," List Response "+response.body());

                MoviesListResponse MoviesListResponse = response.body();
                movie = new ArrayList<>((MoviesListResponse.getMovies()));

                movieListAdapter = new MovieListAdapter(context, movie);
                mRecyclerView.setAdapter(movieListAdapter);
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                Log.d(Const.TAG," Error - "+t.getMessage());
            }
        });
    }

}

package com.example.anvesh.mytestapplication;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("?api_key=b7cd3340a794e5a2f35e3abb820b497f")
    Call<MoviesListResponse> getMovieList();


    @GET("?api_key=b7cd3340a794e5a2f35e3abb820b497f")
    Call<Movies> getMovies();

}

package com.example.anvesh.mytestapplication;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesListResponse {

    @SerializedName("results")
    private ArrayList<Movies> movies;

    public ArrayList<Movies> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movies> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MoviesListResponse{" +
                ", Movies=" + movies +
                '}';
    }

}

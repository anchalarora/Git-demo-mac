package com.example.anvesh.mytestapplication;


import android.os.Parcel;
import android.os.Parcelable;

class Movies implements Parcelable {

    private Integer id;
    private String poster_path;
    private String original_title;
    private String overview;

    private String release_date;
    private boolean adult;

    private Float vote_average;


    public Movies(Integer id, String poster_path, String original_title, String overview, String release_date, boolean adult, Float vote_average)
    {

        this.id =id;
        this.poster_path =poster_path;
        this.original_title =original_title;
        this.overview =overview;
        this.release_date =release_date;
        this.adult =adult;
        this.vote_average =vote_average;

    }

    protected Movies(Parcel in) {
        id =in.readInt();
        poster_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        adult = in.readByte() != 0;
        vote_average =in.readFloat();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }


    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", poster_path=" + poster_path +
                ", original_title=" + original_title +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", adult=" + adult +
                ", vote_average=" + vote_average +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(poster_path);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeFloat(vote_average);
    }

}

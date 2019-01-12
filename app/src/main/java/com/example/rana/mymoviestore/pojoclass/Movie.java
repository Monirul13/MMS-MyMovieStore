package com.example.rana.mymoviestore.pojoclass;

import java.io.Serializable;


/**
 * Created by Rana on 1/4/2019.
 */

public class Movie implements Serializable{

    private int mId;
    private String mName;
    private String mGenre;
    private String mReleaseDate;
    private String mCountry;
    private String mDirector;

    public Movie() {
    }

    public Movie(int mId, String mName, String mGenre, String mReleaseDate, String mCountry, String mDirector) {
        this.mId = mId;
        this.mName = mName;
        this.mGenre = mGenre;
        this.mReleaseDate = mReleaseDate;
        this.mCountry = mCountry;
        this.mDirector = mDirector;
    }

    public Movie(String mName, String mGenre, String mReleaseDate, String mCountry, String mDirector) {
        this.mName = mName;
        this.mGenre = mGenre;
        this.mReleaseDate = mReleaseDate;
        this.mCountry = mCountry;
        this.mDirector = mDirector;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmDirector() {
        return mDirector;
    }

    public void setmDirector(String mDirector) {
        this.mDirector = mDirector;
    }
}

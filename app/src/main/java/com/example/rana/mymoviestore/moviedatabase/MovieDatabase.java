package com.example.rana.mymoviestore.moviedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.rana.mymoviestore.pojoclass.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rana on 1/4/2019.
 */

public class MovieDatabase {
    private MovieHelperDB helperDB;
    private SQLiteDatabase database;
    private Context context;

    public MovieDatabase(Context context)
    {
        this.context=context;
        helperDB=new MovieHelperDB(context);
    }

    public void open()
    {
        database=helperDB.getWritableDatabase();
    }
    public void close()
    {
        database.close();
    }

    public boolean insertNewMovie(Movie movie)
    {
        this.open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MovieHelperDB.MOV_TBL_COL_NAME,movie.getmName());
        contentValues.put(MovieHelperDB.MOV_TBL_COL_GENRE,movie.getmGenre());
        contentValues.put(MovieHelperDB.MOV_TBL_COL_RELEASEDATE,movie.getmReleaseDate());
        contentValues.put(MovieHelperDB.MOV_TBL_COL_COUNTRY,movie.getmCountry());
        contentValues.put(MovieHelperDB.MOV_TBL_COL_DIRECTOR,movie.getmDirector());
        long insertedRow=database.insert(MovieHelperDB.MOVIE_TABLE,null,contentValues);
        this.close();
        if(insertedRow>0)
        {
            return  true;
        }
        return false;
    }

    public List<Movie> getAllMOvies()
    {
        List<Movie> movieList=new ArrayList<>();
        Cursor cursor=null;
        this.open();

        try{
            cursor=database.rawQuery("SELECT * FROM "+MovieHelperDB.MOVIE_TABLE,null);
            Toast.makeText(context,"Data found : "+cursor.getCount(),Toast.LENGTH_LONG).show();
        }catch (SQLException e)
        {
            Toast.makeText(context,"Select query error : "+e,Toast.LENGTH_LONG).show();
        }

        if(cursor!=null && cursor.getCount()>0)
        {
              cursor.moveToFirst();
              for (int i=0;i<cursor.getCount();i++)
              {
                  int empId=cursor.getInt(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_ID));
                  String mName=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_NAME));
                  String genre=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_GENRE));
                  String releaseDate=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_RELEASEDATE));
                  String country=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_COUNTRY));
                  String director=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_DIRECTOR));

                  Movie movie=new Movie(empId,mName,genre,releaseDate,country,director);
                  movieList.add(movie);
                  cursor.moveToNext();
              }
        }
        cursor.close();
        this.close();
        return  movieList;
    }

    // get all the movie matching with search key
    public List<Movie> getSearchMovieList(String searchKey)
    {
        List<Movie> searchList=new ArrayList<>();
        Cursor cursor=null;
        if(searchKey !=null && searchKey.length()>0)
        {
            String searchQuery="SELECT * FROM "+MovieHelperDB.MOVIE_TABLE+" WHERE "+MovieHelperDB.MOV_TBL_COL_NAME
                    +" LIKE  '"+searchKey+"%'";
            this.open();
            try{
                cursor=database.rawQuery(searchQuery,null);
                Toast.makeText(context,"Data found : "+cursor.getCount(),Toast.LENGTH_LONG).show();
            }catch (SQLException e)
            {
                Toast.makeText(context,"Search query error: "+e,Toast.LENGTH_LONG).show();
            }

            if(cursor!=null && cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    do{
                        int empid=cursor.getInt(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_ID));
                        String mName=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_NAME));
                        String mGenre=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_GENRE));
                        String mReleaseDate=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_RELEASEDATE));
                        String mCountry=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_COUNTRY));
                        String mDirector=cursor.getString(cursor.getColumnIndex(MovieHelperDB.MOV_TBL_COL_DIRECTOR));

                        Movie movie=new Movie(empid,mName,mGenre,mReleaseDate,mCountry,mDirector);
                        searchList.add(movie);
                    }while (cursor.moveToNext());
                }
            }

            cursor.close();
            this.close();
        }


        return searchList;
    }
}

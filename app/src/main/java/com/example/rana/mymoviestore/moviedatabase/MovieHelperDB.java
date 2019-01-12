package com.example.rana.mymoviestore.moviedatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Rana on 1/4/2019.
 */

public class MovieHelperDB extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="movie_db";
    public static final int DATABASE_VERSION=2;
    private Context context;

    public static final String MOVIE_TABLE="tbl_movie";

    public static final String MOV_TBL_COL_ID="_mid";
    public static final String MOV_TBL_COL_NAME="name";
    public static final String MOV_TBL_COL_GENRE="genre";
    public static final String MOV_TBL_COL_RELEASEDATE="releaseDate";
    public static final String MOV_TBL_COL_COUNTRY="country";
    public static final String MOV_TBL_COL_DIRECTOR="director";

    public static final String CREATE_MOVIE_TABLE="CREATE TABLE "+MOVIE_TABLE
            +"("+MOV_TBL_COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +MOV_TBL_COL_NAME+" TEXT, "
            +MOV_TBL_COL_GENRE+" TEXT, "
            +MOV_TBL_COL_RELEASEDATE+" TEXT, "
            +MOV_TBL_COL_COUNTRY+" TEXT, "
            +MOV_TBL_COL_DIRECTOR+" TEXT);";

    public MovieHelperDB(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {
            db.execSQL(CREATE_MOVIE_TABLE);
            Toast.makeText(context,"Table ic created",Toast.LENGTH_LONG).show();
        }
        catch (SQLException e)
        {
            Toast.makeText(context,"SQLException from onCreate()"+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{

            db.execSQL("DROP TABLE IF EXISTS " +MOVIE_TABLE);
            onCreate(db);

        }catch (SQLException e)
        {
            Toast.makeText(context,"SQLException from onUpgrade()"+e,Toast.LENGTH_LONG).show();
        }
    }
}

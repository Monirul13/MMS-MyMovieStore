package com.example.rana.mymoviestore;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.rana.mymoviestore.moviedatabase.MovieDatabase;
import com.example.rana.mymoviestore.pojoclass.Movie;

import java.io.Serializable;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMovieFragment extends Fragment {
    private TextInputEditText movieNameET,genreET,releaseDateET,countryET,directorET;
    private DatePickerDialog datePickerDialog;
    private Button saveMovieButton;
    private FragmentTransactionInterface transactionInterface;
    private Context context;

    private MovieDatabase movieDatabase;


    public AddMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        transactionInterface= (FragmentTransactionInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_add_movie, container, false);

       movieDatabase=new MovieDatabase(context);
        movieNameET=view.findViewById(R.id.movie_name_ET_id);
        genreET=view.findViewById(R.id.genre_ET_id);
        releaseDateET=view.findViewById(R.id.release_ET);
        countryET=view.findViewById(R.id.movie_country_ET_id);
        directorET=view.findViewById(R.id.director_ET_id);
        saveMovieButton=view.findViewById(R.id.save_movie_btn);

        releaseDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                releaseDateET.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                            }
                        },year,month,day);

                datePickerDialog.show();
            }
        });

        saveMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMovie();
            }
        });
       return  view;
    }

    public void insertMovie()
    {
        String movieName=movieNameET.getText().toString().trim();
        if(movieName.isEmpty())
        {
            movieNameET.setError("Movie name can't be empty");
            return;
        }
        String genre=genreET.getText().toString().trim();
        String releaseDate=releaseDateET.getText().toString().trim();
        String country=countryET.getText().toString().trim();
        String director=directorET.getText().toString().trim();

        Movie movie=new Movie(movieName,genre,releaseDate,country,director);
        boolean isInserted=movieDatabase.insertNewMovie(movie);

        if(isInserted)
        {
            Toast.makeText(context,"Movie info inserted successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Error while inserting movie info",Toast.LENGTH_LONG).show();
        }

        /*Toast.makeText(context,"Movie info"+movieName
                + "\n"+genre
                +"\n"+releaseDate
                +"\n"+country
                +"\n"+director,Toast.LENGTH_LONG).show();*/

        movieNameET.setText(" ");
        genreET.setText(" ");
        releaseDateET.setText(" ");
        countryET.setText(" ");
        directorET.setText(" ");

        movieDatabase.getAllMOvies();
        SearchMovieFragment searchMovieFragment=new SearchMovieFragment();
        transactionInterface.loadFragment(searchMovieFragment);
    }



}

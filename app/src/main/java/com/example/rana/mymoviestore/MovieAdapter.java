package com.example.rana.mymoviestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rana.mymoviestore.pojoclass.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Rana on 1/7/2019.
 */

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_row_layout,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.mNameTV.setText(movieList.get(position).getmName());
        holder.mGenreTV.setText(movieList.get(position).getmGenre());
        String date=movieList.get(position).getmReleaseDate();

        try {
            SimpleDateFormat spf = new SimpleDateFormat("DD/MM/yyyy"); //date format in which your current string is
            Date newDate = null;
            newDate = spf.parse(date);
            spf = new SimpleDateFormat("d MMM ,yyyy"); //date format in which you want to convert
            date = spf.format(newDate);
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }



        holder.mReleasedTV.setText(date);
        holder.mCountryTV.setText(movieList.get(position).getmCountry());
        holder.mDirectorTV.setText(movieList.get(position).getmDirector());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView mNameTV,mGenreTV,mReleasedTV,mCountryTV,mDirectorTV;
        public MovieViewHolder(View itemView) {
            super(itemView);
            mNameTV=itemView.findViewById(R.id.mName_TV_id);
            mGenreTV=itemView.findViewById(R.id.mGenre_TV_id);
            mReleasedTV=itemView.findViewById(R.id.mReleased_TV_id);
            mCountryTV= itemView.findViewById(R.id.mCountry_TV_id);
            mDirectorTV=itemView.findViewById(R.id.mDirector_TV_id);
        }
    }
}

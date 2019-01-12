package com.example.rana.mymoviestore;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rana.mymoviestore.moviedatabase.MovieDatabase;
import com.example.rana.mymoviestore.pojoclass.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMovieFragment extends Fragment {

    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;
    private LinearLayoutManager layoutManager;
    private MovieDatabase movieDatabase;
    private Context context;
    private SearchView searchView;

    private List<Movie> movieList=new ArrayList<>();


    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search_movie, container, false);
        movieDatabase=new MovieDatabase(context);
        movieList=movieDatabase.getAllMOvies();
        searchView=view.findViewById(R.id.searchView);
        movieRecyclerView=view.findViewById(R.id.movie_recyclerView_id);
        movieAdapter=new MovieAdapter(context,movieList);
        layoutManager=new LinearLayoutManager(context);
        movieRecyclerView.setLayoutManager(layoutManager);
        movieRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        movieRecyclerView.setAdapter(movieAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty())
                {
                    movieList=movieDatabase.getAllMOvies();
                    movieAdapter=new MovieAdapter(context,movieList);
                    movieRecyclerView.setAdapter(movieAdapter);
                }
                else
                {
                    movieList.clear();
                    movieList=movieDatabase.getSearchMovieList(newText);
                    movieAdapter=new MovieAdapter(context,movieList);
                    movieRecyclerView.setAdapter(movieAdapter);
                }

                return false;
            }
        });
        return  view;

    }

}

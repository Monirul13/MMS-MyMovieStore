package com.example.rana.mymoviestore;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppStartFragment extends Fragment {

    private FragmentTransactionInterface fragmentTransactionInterface;
    private ProgressBar progressBar;
    private static int progress;

    public AppStartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentTransactionInterface= (FragmentTransactionInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_app_start, container, false);
         progressBar=view.findViewById(R.id.progressbar_Id);
         Thread thread=new Thread(new Runnable() {
             @Override
             public void run() {
                 startProgress();
             }

         });
         thread.start();

         return view;
    }
    private void startProgress() {
        for(progress=10;progress<=100;progress=progress+10)
        {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SearchMovieFragment searchMovieFragment=new SearchMovieFragment();
        //AddMovieFragment addMovieFragment=new AddMovieFragment();
        fragmentTransactionInterface.loadFragment(searchMovieFragment);
    }

}

package com.example.rana.mymoviestore;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentTransactionInterface{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout_id);
        navigationView=findViewById(R.id.navigationView_id);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId())
                {
                    case R.id.add_movie:
                        AddMovieFragment movieFragment=new AddMovieFragment();
                        loadFragment(movieFragment);
                        break;
                    case R.id.imdb_movie_list:
                        String url= "https://m.imdb.com/chart/top";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        break;
                    case R.id.rtomato_movie_list:
                        String url2= "https://www.rottentomatoes.com/top/bestofrt/";
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                        startActivity(intent2);
                        break;
                    case R.id.rate_the_app:
                        Toast.makeText(getApplicationContext(),"Rate the app IS CLICKED",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.suggestion:
                        Toast.makeText(getApplicationContext(),"suggestion IS CLICKED",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        //toggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager=getSupportFragmentManager();


        AppStartFragment startFragment=new AppStartFragment();
        loadFragment(startFragment,false);


        /*movieNameET=findViewById(R.id.movie_name_ET_id);
        genreET=findViewById(R.id.genre_ET_id);
        releaseDateET=findViewById(R.id.release_ET);
        countryET=findViewById(R.id.movie_country_ET_id);
        directorET=findViewById(R.id.director_ET_id);

        releaseDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog= new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                 releaseDateET.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                            }
                        },year,month,day);

                datePickerDialog.show();
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadFragment(final Fragment fragment,boolean willReplace) {

        transaction=fragmentManager.beginTransaction();
        if(willReplace)
        {
            transaction.replace(R.id.fragmentContainer,fragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null);

        }
        else
        {
            transaction.add(R.id.fragmentContainer,fragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
        transaction.commit();

    }

    @Override
    public void loadFragment(Fragment fragment) {
        loadFragment(fragment,true);
    }
}

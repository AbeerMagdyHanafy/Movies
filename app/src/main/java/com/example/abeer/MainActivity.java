package com.example.ABeeR;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.ClickHandler {


    private boolean mTwoPane;
    DetailsFragment detailsFragment;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        mainFragment.setClickHandler(this);
        if (findViewById(R.id.fragment_details) != null) {

            mTwoPane = true;

            detailsFragment = new DetailsFragment();

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_details, detailsFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }


//        logo = getSupportActionBar();
//        logo.setDisplayShowHomeEnabled(true);
//        logo.setLogo(R.drawable.movie2);
//        logo.setDisplayUseLogoEnabled(true);
    }

    public void openMovie(movie movie) {
        if (mTwoPane) {
            //Fragment Code
            detailsFragment.updateData(movie);


        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("Original_title", movie.getOriginal_title());
            intent.putExtra("Overview", movie.getOverview());
            intent.putExtra("backdrop_path", movie.getBackdrop_path());
            intent.putExtra("Vote_average", movie.getVote_average());
            intent.putExtra("Release_date", movie.getRelease_date());
            intent.putExtra("Poster_path", movie.getPoster_path());
            intent.putExtra("id", movie.getId());
            startActivity(intent);
        }

    }
//    class MyMovieHandler implements MainFragment.ClickHandler{
//        public void openMovie(movie movie){
//            if(mTwoPane){
//                //Fragment Code
//                detailsFragment.updateData(movie);
//
//
//            }else {
//                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent.putExtra("Original_title", movie.getOriginal_title());
//                intent.putExtra("Overview", movie.getOverview());
//                intent.putExtra("backdrop_path", movie.getBackdrop_path());
//                intent.putExtra("Vote_average", movie.getVote_average());
//                intent.putExtra("Release_date", movie.getRelease_date());
//                intent.putExtra("Poster_path", movie.getPoster_path());
//                intent.putExtra("id", movie.getId());
//                startActivity(intent);
//            }
//
//        }
    //  }
}



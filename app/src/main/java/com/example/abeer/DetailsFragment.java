package com.example.abeer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import DataBase.DataBase;
import DataBase.DataBaseHelper;


public class DetailsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    String Original_title, Overview, backdrop_path, Release_date, Poster_path;
    double Vote_average;
    int id;
    ImageView image_poster;
    TextView tv_title, tv_date, tv_overview, tv_rate;
    Button btn_trailer, btn_review;
    Button btn_favourite;
    DataBaseHelper new_movie_favourite;
    SQLiteDatabase db;
    DataBaseHelper movie_favourite;
    boolean Favourite = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container , false);
        btn_trailer = (Button) rootView.findViewById(R.id.btn_trailer);
        btn_review = (Button) rootView.findViewById(R.id.btn_review);
        btn_favourite = (Button) rootView.findViewById(R.id.btn_favourite);

        image_poster = (ImageView) rootView.findViewById(R.id.image_poster);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        tv_overview = (TextView) rootView.findViewById(R.id.tv_overview);
        tv_rate = (TextView) rootView.findViewById(R.id.tv_rate);

        return rootView;
    }



    public void updateData(movie movie){



        Original_title = movie.getOriginal_title();//intent.getStringExtra("Original_title");
        Overview = movie.getOverview(); //intent.getStringExtra("Overview");
        backdrop_path = movie.getBackdrop_path(); //intent.getStringExtra("backdrop_path");
        Release_date = movie.getAuthor_review();//intent.getStringExtra("Release_date");
        Vote_average = movie.getVote_average();//intent.getDoubleExtra("Vote_average", 0.0);
        id =movie.getId();// intent.getIntExtra("id", 0);
        Poster_path = movie.getPoster_path();//intent.getStringExtra("Poster_path");

        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342" + backdrop_path)
                .resize(360, 380)
                .centerCrop()
                .into(image_poster);
        tv_title.setText(Original_title);
        tv_overview.setText(Overview);
        tv_date.setText(Release_date);
        tv_rate.setText(Vote_average + "/10");

        movie_favourite = new DataBaseHelper(getActivity());
        SQLiteDatabase sqldb = movie_favourite.getReadableDatabase();
        Cursor cursor = sqldb.rawQuery("select * from " + DataBase.Table, null);
        if (cursor.moveToFirst()) {
            do {
                if (!(cursor.getInt(6) == (id))) {
                    Favourite = false;
                    btn_favourite.setBackgroundResource(android.R.drawable.btn_star_big_off);
                } else if (cursor.getInt(6) == (id)) {
                    Favourite = true;
                    btn_favourite.setBackgroundResource(android.R.drawable.btn_star_big_on);
                    btn_favourite.setClickable(false);
                    break;
                }
            } while (cursor.moveToNext());
        }



    }




    static public boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

// if no network is available networkInfo will be null

// otherwise check if we are connected

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onStart() {

                Intent intent = this.getActivity().getIntent();
        Original_title = intent.getStringExtra("Original_title");
        Overview = intent.getStringExtra("Overview");
        backdrop_path = intent.getStringExtra("backdrop_path");
        Release_date = intent.getStringExtra("Release_date");
        Vote_average = intent.getDoubleExtra("Vote_average", 0.0);
        id = intent.getIntExtra("id", 0);
        Poster_path = intent.getStringExtra("Poster_path");

        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342" + backdrop_path)
                .resize(360, 380)
                .centerCrop()
                .into(image_poster);
        tv_title.setText(Original_title);
        tv_overview.setText(Overview);
        tv_date.setText(Release_date);
        tv_rate.setText(Vote_average + "/10");



        movie_favourite = new DataBaseHelper(getActivity());
        SQLiteDatabase sqldb = movie_favourite.getReadableDatabase();
        Cursor cursor = sqldb.rawQuery("select * from " + DataBase.Table, null);
        if (cursor.moveToFirst()) {
            do {
                if (!(cursor.getInt(6) == (id))) {
                    Favourite = false;
                    btn_favourite.setBackgroundResource(android.R.drawable.btn_star_big_off);
                   // btn_favourite.setClickable(true);
                } else if (cursor.getInt(6) == (id)) {
                    Favourite = true;
                    btn_favourite.setBackgroundResource(android.R.drawable.btn_star_big_on);
                   // btn_favourite.setClickable(false);
                    break;
                }
            } while (cursor.moveToNext());
        }

        if (isNetworkAvailable(getActivity())) {
            btn_review.setVisibility(View.VISIBLE);
            btn_trailer.setVisibility(View.VISIBLE);
        } else {
            btn_review.setVisibility(View.GONE);
            btn_trailer.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please Check Your Connection!", Toast.LENGTH_LONG).show();
        }

        btn_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrailerActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", Original_title);
                startActivity(intent);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", Original_title);
                startActivity(intent);
            }
        });


        btn_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_movie_favourite = new DataBaseHelper(getActivity());

                //new ContentValues , put in row (ContentValues)
                ContentValues row = new ContentValues();

                row.put(DataBase.columns.poster_path, Poster_path);
                row.put(DataBase.columns.backdrop_path, backdrop_path);
                row.put(DataBase.columns.original_title, Original_title);
                row.put(DataBase.columns.overview, Overview);
                row.put(DataBase.columns.release_date, Release_date);
                row.put(DataBase.columns.id, id);
                row.put(DataBase.columns.vote_average, Vote_average);

                ///write DB , insert DB, close DB
                db = new_movie_favourite.getWritableDatabase();
                db.insert(DataBase.Table, null, row);
                new_movie_favourite.close();

                btn_favourite.setBackgroundResource(android.R.drawable.btn_star_big_on);
               // btn_favourite.setClickable(false);
                Toast.makeText(getActivity(), Original_title + " is favourite ", Toast.LENGTH_SHORT).show();
            }
        });
        super.onStart();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

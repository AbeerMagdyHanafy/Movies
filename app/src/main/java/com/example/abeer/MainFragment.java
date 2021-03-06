package com.example.abeer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DataBase.DataBase;
import DataBase.DataBaseHelper;


public class MainFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static int POSITION = 0;
    GridView gridView;
    GridView_Adapter gridView_adapter;
    Async_Task async_task;
    String top_rated = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
    String most_popular = "https://api.themoviedb.org/3/movie/popular?api_key=";
    SharedPreferences sharedpreferences;
    String CHOICE = "movie_popular";
    DataBaseHelper movie_favourite;
    List<movie> list_movie;
    ActionBar logo;


    private ClickHandler clickHandler;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public void setClickHandler(ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        logo = getSupportActionBar();
//        logo.setDisplayShowHomeEnabled(true);
//        logo.setLogo(R.drawable.logo);
//        logo.setDisplayUseLogoEnabled(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        gridView_adapter = new GridView_Adapter(getActivity());
        gridView = (GridView) rootView.findViewById(R.id.grid_view);
        setGridView(most_popular + getString(R.string.api_key), "most_popular");
        //gridView.setAdapter(gridView_adapter);
        // gridView.setSelection(0);

        setHasOptionsMenu(true);///

        return rootView;
    }

    @Override
    public void onStart() {

        sharedpreferences = this.getActivity().getSharedPreferences(CHOICE, Context.MODE_PRIVATE);
        String choice = sharedpreferences.getString(CHOICE, CHOICE);

        //gridView.setSelection(0);
        //gridView.setSelected(true);


        if (choice.equals("movie_popular")) {
            setGridView(most_popular + getString(R.string.api_key), "most_popular");
            gridView.setAdapter(gridView_adapter);

            Toast.makeText(getActivity(), "movie_popular", Toast.LENGTH_LONG).show();
            //getSupportActionBar().setTitle(" Popular Movies");
        } else if (choice.equals("movie_toprated")) {
            setGridView(top_rated + getString(R.string.api_key), "top_rated");
            gridView.setAdapter(gridView_adapter);
            //gridView.setSelection(0);
            Toast.makeText(getActivity(), "movie_toprated", Toast.LENGTH_LONG).show();
            // getSupportActionBar().setTitle(" Top Rated Movies");
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movie movie = gridView_adapter.getItem(position);
                ((MainActivity) getActivity()).openMovie(movie);
//                clickHandler.openMovie(movie);
                POSITION = position;
            }
        });

        super.onStart();
    }

    private void setGridView(String choice, String sort) {
//        gridView = (GridView) getView().findViewById(R.id.grid_view);
        //  gridView_adapter = new GridView_Adapter(getActivity());
        async_task = new Async_Task(getActivity(), gridView_adapter, choice, gridView, sort);
        async_task.execute();
        //gridView.setSelection(POSITION);
        gridView.setAdapter(gridView_adapter);


    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //gridView.setSelection(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.movie_popular) {
            setGridView(most_popular + getString(R.string.api_key), "most_popular");
            //   gridView.setAdapter(gridView_adapter);
            sharedpreferences = this.getActivity().getSharedPreferences(CHOICE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(CHOICE, "movie_popular");
            editor.commit();
            // gridView.setSelection(POSITION);
            //gridView.setSelection(0);
            //gridView.setPressed(true);
            //gridView.smoothScrollToPosition(0);
            //Log.d("itemselected", String.valueOf(gridView.getCheckedItemPosition()));
            Toast.makeText(getActivity(), "movie_popular", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().setTitle(" Popular Movies");
            //getSupportActionBar().setLogo(R.drawable.logo);
            return true;
        } else if (id == R.id.movie_toprated) {
            setGridView(top_rated + getString(R.string.api_key), "top_rated");
            //gridView.setSelection(POSITION);
            //gridView.setItemChecked(0,true);

            gridView.setAdapter(gridView_adapter);
            sharedpreferences = this.getActivity().getSharedPreferences(CHOICE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(CHOICE, "movie_toprated");
            editor.commit();

            Toast.makeText(getActivity(), "movie_toprated", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().setTitle(" Top Rated Movies");
            //getSupportActionBar().setLogo(R.drawable.logo);
            return true;
        } else if (id == R.id.favourite) {
            movie_favourite = new DataBaseHelper(getActivity());
            SQLiteDatabase sqldb = movie_favourite.getReadableDatabase();
            Cursor cursor = sqldb.rawQuery("select * from " + DataBase.Table, null);

            Log.d("cursor", String.valueOf(cursor.getCount()));

            if (cursor.getCount() == 0) {
                //View rootView = inflater.inflate(R.layout.fragment_main, container);
                // setContentView(R.layout.activity_main);
                gridView_adapter.clear();

                Toast.makeText(getActivity(), "no Favourite", Toast.LENGTH_SHORT).show();

            } else {
                list_movie = new ArrayList<movie>();
                list_movie.clear();

                gridView = (GridView) getView().findViewById(R.id.grid_view);
                gridView_adapter = new GridView_Adapter(getActivity());
                gridView_adapter.clear();
                //gridView.setSelection(POSITION);

                if (cursor.moveToFirst()) {
                    do {
                        movie m = new movie();
                        m.setPoster_path(cursor.getString(1));
                        m.setBackdrop_path(cursor.getString(2));
                        m.setOriginal_title(cursor.getString(3));
                        m.setOverview(cursor.getString(4));
                        m.setRelease_date(cursor.getString(5));
                        m.setId(cursor.getInt(6));
                        m.setVote_average(cursor.getDouble(7));

                        gridView_adapter.add(m);
                        gridView.setAdapter(gridView_adapter);
                    } while (cursor.moveToNext());
                }
            }

            //getSupportActionBar().setTitle(" My Favourite");
//            logo.setLogo(android.R.drawable.btn_star_big_on);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    }

    public interface ClickHandler {
        void openMovie(movie movie);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

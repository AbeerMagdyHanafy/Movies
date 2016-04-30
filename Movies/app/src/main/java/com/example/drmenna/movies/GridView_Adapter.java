package com.example.drmenna.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dr.Menna on 25/03/2016.
 */
public class GridView_Adapter  extends BaseAdapter {

    List<String> posterPath ;

    private Context mContext;
    public GridView_Adapter(Context c ) {
        this.mContext = c;
    }

    @Override
    public int getCount() {
        return posterPath.size();
    }

    @Override
    public String getItem(int position) {
        return posterPath.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

            @Override
            //lw 3 item el zahreen ll user hynady 3leha 5 marat (main fn)
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = new ViewHolder(); ///
        if (convertView == null){
                convertView= LayoutInflater.from(mContext).inflate(R.layout.grid_item,null);///null parent // shaif kol 7aga fi el layout da
                //bdl ImageView
                //getTag ---> yba 3arf elma3lomat 3naha bdl ma ynady 3lyha tany
            holder.imageView = (ImageView)convertView.findViewById(R.id.image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
           }
                Picasso.with(mContext).load(getItem(position)).into(holder.imageView); // da path mn ely gay
        return convertView;
        }

     class ViewHolder{
        ImageView imageView;
    }

}

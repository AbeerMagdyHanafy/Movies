package com.example.ABeeR;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ABeeR on 25/03/2016.
 */
public class GridView_Adapter extends ArrayAdapter<movie> {

    private Context mContext;

    public GridView_Adapter(Context mContext) {
        super(mContext, 0);
        this.mContext = mContext;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //lw 3 item el zahreen ll user hynady 3leha 5 marat (main fn)
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder(); ///
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);///null parent // shaif kol 7aga fi el layout da
            //bdl ImageView
            //getTag ---> yba 3arf elma3lomat 3naha bdl ma ynady 3lyha tany
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500" + getItem(position).getPoster_path())
                .resize(360, 580)
                .centerCrop()
                .into(holder.imageView); // da path mn ely gay
//        Log.d("grid_image", getItem(position).getPoster_path());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}

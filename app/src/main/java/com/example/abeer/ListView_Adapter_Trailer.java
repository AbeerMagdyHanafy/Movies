package com.example.abeer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ABeeR on 25/03/2016.
 */
public class ListView_Adapter_Trailer extends ArrayAdapter<movie> {

    private Context mContext;

    public ListView_Adapter_Trailer(Context mContext) {
        super(mContext, 0);
        this.mContext = mContext;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.trailer_item, null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.image_trailer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load("http://img.youtube.com/vi/" + getItem(position).getKey() + "/0.jpg")
                .resize(700, 390)
                .centerCrop()
                .into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}

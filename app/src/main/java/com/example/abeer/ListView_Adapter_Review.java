package com.example.abeer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ABeeR on 25/03/2016.
 */
public class ListView_Adapter_Review extends ArrayAdapter<movie> {

    private Context mContext;
    ViewHolder holder;

    public ListView_Adapter_Review(Context mContext) {
        super(mContext, 0);
        this.mContext = mContext;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.review_item, null);

            holder.author = (TextView) convertView.findViewById(R.id.tv_author);
            holder.content = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.author.setText(getItem(position).getAuthor_review());
        Log.d("author_re", getItem(position).getAuthor_review());
        holder.content.setText(getItem(position).getContent_review());
        return convertView;
    }

    class ViewHolder {
        TextView author, content;
    }
}

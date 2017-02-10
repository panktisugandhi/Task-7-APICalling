package com.example.welcome.task_7apicalling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by welcome on 2/8/2017.
 */

public class CustomRows extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Post> arr;


    public CustomRows(Context context, ArrayList<Post> arr) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if (view == null)
            view = layoutInflater.inflate(R.layout.rows, null);
            TextView TV1 = (TextView) view.findViewById(R.id.TV_uid);
            TextView TV2 = (TextView) view.findViewById(R.id.TV_id);
            TextView TV3 = (TextView) view.findViewById(R.id.TV_tit);
            TextView TV4 = (TextView) view.findViewById(R.id.TV_body);

            Post p = arr.get(position);
            TV1.setText("UserId: "+String.valueOf(p.getUid()));
            TV2.setText("Id: "+String.valueOf(p.getId()));
            TV3.setText("Title: "+p.getTitle());
            TV4.setText("Body: "+p.getBody());




        return view;

    }
}
package com.mobifever.v4u.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobifever.v4u.R;
import com.mobifever.v4u.model.Disaster;

import java.util.ArrayList;


/**
 * Created by krati.jain on 30/05/15.
 */
public class RecentDisasterAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Disaster> disasters;

    public RecentDisasterAdapter(Context context, ArrayList<Disaster> navDrawerItems){
        this.context = context;
        this.disasters = navDrawerItems;
    }

    @Override
    public int getCount() {
        return 5;
//        return disasters.size();
    }

    @Override
    public Object getItem(int position) {
        return disasters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.disaster_list_item, null);
        }

        TextView locationTextView = (TextView) convertView.findViewById(R.id.disaster_location);
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time);

        return convertView;
    }

}

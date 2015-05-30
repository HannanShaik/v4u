package com.mobifever.v4u.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobifever.v4u.R;
import com.mobifever.v4u.network.dto.CasualityDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class CasualityListAdapter extends BaseAdapter {

    private Context context;
    private List<CasualityDTO> casualities;

    public CasualityListAdapter(Context context, List<CasualityDTO> casualities){
        this.context = context;
        this.casualities = casualities;
    }

    @Override
    public int getCount() {
        return casualities.size();
    }

    @Override
    public Object getItem(int position) {
        return casualities.get(position);
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
            convertView = mInflater.inflate(R.layout.casuality_list_item, null);
        }

        CasualityDTO casuality = casualities.get(position);
        TextView personNameTextView = (TextView) convertView.findViewById(R.id.person_name);
        TextView statusTextView = (TextView) convertView.findViewById(R.id.status);
        TextView locationTextView = (TextView) convertView.findViewById(R.id.location);
        TextView phoneTextView = (TextView) convertView.findViewById(R.id.phone_number);
        TextView helpTextView = (TextView) convertView.findViewById(R.id.help_needed);

        personNameTextView.setText(casuality.getPersonName());
        statusTextView.setText(casuality.getStatusOfPerson().get(0));
        phoneTextView.setText(casuality.getPhoneNumber());
        locationTextView.setText(casuality.getMyLocation());
        helpTextView.setText(casuality.getKindOfHelpNeeded());

        return convertView;
    }

}
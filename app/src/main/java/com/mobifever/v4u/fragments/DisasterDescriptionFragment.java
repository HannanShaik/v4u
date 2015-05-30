package com.mobifever.v4u.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobifever.v4u.R;
import com.mobifever.v4u.V4UConstants;
import com.mobifever.v4u.activities.MainActivity;
import com.mobifever.v4u.model.Disaster;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DisasterDescriptionFragment extends Fragment {

    Disaster disaster;
    TextView disasterName, disasterLocation, disasterTime, casualityCount;
    ListView helpline;
    ImageView disasterBanner;
    List<String> helplineNumbers;

    public DisasterDescriptionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_disaster_description, container, false);
        disaster = (Disaster) getArguments().getSerializable("DISASTER");

        ImageView searchImageView = (ImageView) rootView.findViewById(R.id.searchImageView);
        disasterName = (TextView) rootView.findViewById(R.id.disaster_name);
        disasterLocation = (TextView) rootView.findViewById(R.id.disaster_location);
        disasterTime = (TextView) rootView.findViewById(R.id.disaster_time);
        casualityCount = (TextView) rootView.findViewById(R.id.casualityCount);
        disasterBanner = (ImageView) rootView.findViewById(R.id.disaster_banner);
        helpline = (ListView) rootView.findViewById(R.id.helplineListView);
        helplineNumbers = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, helplineNumbers);

        helpline.setAdapter(adapter);

        if (disaster != null) {
            disasterName.setText(disaster.getDisasterName());
            disasterLocation.setText(disaster.getLocation());
            disasterTime.setText(new SimpleDateFormat("dd-MMM-yyyy kk:mm").format( new Date(disaster.getTime())) + "");
            casualityCount.setText(disaster.getNumberOfCasualities() + "");


            Drawable drawable = null;

            if (disaster.getDisasterType().equalsIgnoreCase("Earthquake"))
                drawable = getResources().getDrawable(R.drawable.earthquake);
            if (disaster.getDisasterType().equalsIgnoreCase("Volcano"))
                drawable = getResources().getDrawable(R.drawable.volcanco1);
            if (disaster.getDisasterType().equalsIgnoreCase("Cyclone"))
                drawable = getResources().getDrawable(R.drawable.cyclone);
            if (disaster.getDisasterType().equalsIgnoreCase("Hurricane"))
                drawable = getResources().getDrawable(R.drawable.hurricane);


            disasterBanner.setImageDrawable(drawable);
            if (disaster.getHelplineNumbers() != null) {
                helplineNumbers.addAll(disaster.getHelplineNumbers());
                adapter.notifyDataSetChanged();
            }

        }

        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToSearch();
            }
        });

        return rootView;
    }
}

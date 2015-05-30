package com.mobifever.v4u.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobifever.v4u.R;
import com.mobifever.v4u.V4UConstants;
import com.mobifever.v4u.activities.MainActivity;
import com.mobifever.v4u.fragments.DisasterDescriptionFragment;
import com.mobifever.v4u.fragments.DisastersFragment;
import com.mobifever.v4u.model.Disaster;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class DisasterListAdapter extends RecyclerView.Adapter<DisasterListAdapter.ListViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    List<Disaster> disasters = Collections.emptyList();

    public DisasterListAdapter(Context context,List<Disaster> disasters){
        inflater = LayoutInflater.from(context);
        this.disasters = disasters;
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.disaster_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Disaster disaster = disasters.get(position);
        Drawable drawable = null;
        if(disaster.getDisasterType().equalsIgnoreCase("Earthquake"))
            drawable = context.getResources().getDrawable(R.drawable.earthquake);
        if(disaster.getDisasterType().equalsIgnoreCase("Volcano"))
            drawable = context.getResources().getDrawable(R.drawable.volcanco1);
        if(disaster.getDisasterType().equalsIgnoreCase("Cyclone"))
            drawable = context.getResources().getDrawable(R.drawable.cyclone);
        if(disaster.getDisasterType().equalsIgnoreCase("Hurricane"))
            drawable = context.getResources().getDrawable(R.drawable.hurricane);

        holder.disasterBanner.setImageDrawable(drawable);
        holder.disasterLocation.setText(disaster.getLocation());
        holder.disasterName.setText(disaster.getDisasterName());
    }

    @Override
    public int getItemCount() {
        return disasters.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView disasterBanner;
        TextView disasterLocation;
        TextView disasterName;

        public ListViewHolder(View itemView) {
            super(itemView);
            disasterBanner = (ImageView) itemView.findViewById(R.id.disaster_banner);
            disasterName = (TextView) itemView.findViewById(R.id.disaster_name);
            disasterLocation = (TextView) itemView.findViewById(R.id.disaster_location);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            DisasterDescriptionFragment disasterDescriptionFragment = new DisasterDescriptionFragment();

            FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();

            Bundle bundle = new Bundle();
            bundle.putSerializable("DISASTER",disasters.get(getPosition()));
            disasterDescriptionFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, disasterDescriptionFragment)
                    .addToBackStack(null)
                    .commit();


        }


    }
}

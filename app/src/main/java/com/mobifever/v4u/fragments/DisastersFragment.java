package com.mobifever.v4u.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobifever.v4u.R;
import com.mobifever.v4u.V4UConstants;
import com.mobifever.v4u.activities.MainActivity;
import com.mobifever.v4u.adapter.DisasterListAdapter;
import com.mobifever.v4u.helper.DisasterHelper;
import com.mobifever.v4u.helper.SnappyDBHelper;
import com.mobifever.v4u.model.Disaster;
import com.mobifever.v4u.network.service.ServiceCallback;
import com.mobifever.v4u.network.service.V4UException;

import java.util.ArrayList;
import java.util.List;


public class DisastersFragment extends Fragment {
    List<Disaster> diasters;
    DisasterListAdapter disasterListAdapter;
    ProgressDialog pd;

    /*public static List<Disaster> getData(){
        V4UConstants.DisasterType[] disasterType = new V4UConstants.DisasterType[]{
            V4UConstants.DisasterType.EARTH_QUAKE,
                    V4UConstants.DisasterType.CYCLONE,
                    V4UConstants.DisasterType.HURRICANE,
                V4UConstants.DisasterType.VOLCANO
        };



        List<Disaster> disasterList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Disaster disaster = new Disaster();
            disaster.setDisasterName("EARTH QUAKE");
            disaster.setLocation("BANGALORE");
            disaster.setDisasterType(disasterType[i%4]);
            disasterList.add(disaster);
        }

        return disasterList;

    }*/


	public DisastersFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_disasters, container, false);


        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_disaster_list);

        diasters = new ArrayList<>();

        disasterListAdapter = new DisasterListAdapter(getActivity(), diasters);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(disasterListAdapter);


        /*DisasterDescriptionFragment disasterDescriptionFragment = new DisasterDescriptionFragment();
                disasterDescriptionFragment.setDisaster(null);

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, disasterDescriptionFragment).commit();
*/


        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
/*
        List<Disaster> disasterList = new SnappyDBHelper(getActivity()).getAllDisasters();
        if(disasterList!=null){
            diasters.clear();
            diasters.addAll(disasterList);
            disasterListAdapter.notifyDataSetChanged();
        }
*/

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.show();

        new DisasterHelper(getActivity()).getDisasters(null, new ServiceCallback<List<Disaster>>() {
            @Override
            public void onSuccess(List<Disaster> disastersList) {
                diasters.clear();
                diasters.addAll(disastersList);
                disasterListAdapter.notifyDataSetChanged();
                pd.dismiss();
            }

            @Override
            public void onFailure(V4UException e) {
                e.printStackTrace();
                pd.dismiss();
            }
        });

    }
}

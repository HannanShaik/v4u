package com.mobifever.v4u.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobifever.v4u.R;
import com.mobifever.v4u.activities.MainActivity;
import com.mobifever.v4u.adapter.CasualityListAdapter;
import com.mobifever.v4u.helper.CasualityHelper;
import com.mobifever.v4u.network.dto.CasualityDTO;
import com.mobifever.v4u.network.dto.SearchDTO;
import com.mobifever.v4u.network.service.ServiceCallback;
import com.mobifever.v4u.network.service.V4UException;

import java.util.ArrayList;
import java.util.List;


public class FindPeopleFragment extends Fragment {

    String disasterType;
    private ProgressDialog pd;

    public FindPeopleFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).getSupportActionBar().setTitle("Find People");
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        disasterType = "Earthquake";
        final View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);

        Spinner spinner = (Spinner) rootView.findViewById(R.id.disasterTypeSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.disaster_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disasterType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        ListView peopleList = (ListView) rootView.findViewById(R.id.casualityList);
        final List<CasualityDTO> casualityList = new ArrayList<>();
        final CasualityListAdapter casualityListAdapter = new CasualityListAdapter(getActivity(),casualityList);
        peopleList.setAdapter(casualityListAdapter);

        final Button searchButton = (Button)rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText personName = (EditText)rootView.findViewById(R.id.personName);
                EditText location = (EditText)rootView.findViewById(R.id.locationEditText);
                //call the helper methods to search with the details given
                pd = new ProgressDialog(getActivity());
                pd.setMessage("loading");
                pd.show();

                    CasualityDTO searchDTO = new CasualityDTO();
                    searchDTO.setPersonName(personName.getText().toString());
                    searchDTO.setMyLocation(location.getText().toString());
                    searchDTO.setDisasterType(disasterType);
                    searchDTO.setDisasterId(0);
                    searchDTO.setCasualityId(0);
                    new CasualityHelper(getActivity()).search(searchDTO, new ServiceCallback<List<CasualityDTO>>() {
                        @Override
                        public void onSuccess(List<CasualityDTO> casualityDTOs) {
                            pd.dismiss();
                            casualityList.clear();
                            casualityList.addAll(casualityDTOs);
                            casualityListAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(V4UException e) {
                            pd.dismiss();
                            e.printStackTrace();
                            Toast.makeText(getActivity(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    });

            }
        });
        return rootView;
    }
}

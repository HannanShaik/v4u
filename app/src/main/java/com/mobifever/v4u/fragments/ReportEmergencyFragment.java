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
import com.mobifever.v4u.helper.CasualityHelper;
import com.mobifever.v4u.network.dto.CasualityDTO;
import com.mobifever.v4u.network.service.ServiceCallback;
import com.mobifever.v4u.network.service.V4UException;

import java.util.ArrayList;
import java.util.List;


public class ReportEmergencyFragment extends Fragment {

    String disasterType;
    String helpType;
    private ListView helpline;
    private List<String> helplineNumbers;
    private ProgressDialog pd;

    public ReportEmergencyFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).getSupportActionBar().setTitle("Report Emergency");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_report_emergency, container, false);

        final Spinner disasterSpinnner = (Spinner) rootView.findViewById(R.id.disasterTypeSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.disaster_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        disasterSpinnner.setAdapter(adapter);

        disasterSpinnner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                disasterType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        Spinner spinner = (Spinner) rootView.findViewById(R.id.helpTypeSpinner);

        final ArrayAdapter<CharSequence> helpTypes = ArrayAdapter.createFromResource(getActivity(),
                R.array.help_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        helpTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(helpTypes);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                helpType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        helpline = (ListView) rootView.findViewById(R.id.helplineListView);
        helplineNumbers = new ArrayList<>();

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, helplineNumbers);

        helpline.setAdapter(listAdapter);


        Button reportEmergencyButton = (Button)rootView.findViewById(R.id.reportEmergencyButton);
        reportEmergencyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                pd = new ProgressDialog(getActivity());
                pd.setMessage("loading");
                pd.show();

                EditText personName = (EditText)rootView.findViewById(R.id.personName);
                EditText location = (EditText)rootView.findViewById(R.id.locationEditText);

                    CasualityDTO casualityDTO = new CasualityDTO();
                    casualityDTO.setCasualityId(0);
                    casualityDTO.setDisasterId(0);
                    casualityDTO.setMyLocation(location.getText().toString());
                    casualityDTO.setPersonName(personName.getText().toString());
                    casualityDTO.setDisasterType(disasterType);
                    List<String> status = new ArrayList<String>();
                    status.add("ALIVE");
                    casualityDTO.setStatusOfPerson(status);
                    casualityDTO.setKindOfHelpNeeded(helpType);
                    casualityDTO.setPhoneNumber("9538481764");

                    new CasualityHelper(getActivity()).report(casualityDTO, new ServiceCallback<List<String>>() {
                        @Override
                        public void onSuccess(List<String> stringList) {
                            //Log.e("SUCCESS",casualityDTO.toString());
                            Toast.makeText(getActivity(),"Do not worry! Help is coming!",Toast.LENGTH_SHORT).show();
                            helplineNumbers.clear();
                            helplineNumbers.addAll(stringList);
                            listAdapter.notifyDataSetChanged();
                            pd.dismiss();
                            //((MainActivity) getActivity()).gotToHome();
                        }

                        @Override
                        public void onFailure(V4UException e) {
                            pd.dismiss();
                            Toast.makeText(getActivity(),"Failed to report! Please try again!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        });

        return rootView;
    }
}

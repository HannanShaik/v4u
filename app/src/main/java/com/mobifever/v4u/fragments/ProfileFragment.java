package com.mobifever.v4u.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobifever.v4u.R;


public class ProfileFragment extends Fragment {
	
	public ProfileFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Button saveProfileButton = (Button)rootView.findViewById(R.id.saveButton);
        saveProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText personName = (EditText)rootView.findViewById(R.id.personName);
                EditText location = (EditText)rootView.findViewById(R.id.locationEditText);
                EditText phoneNumber = (EditText)rootView.findViewById(R.id.phoneNumberEditText);

                EditText contact1 = (EditText)rootView.findViewById(R.id.contactNumber1);
                EditText contact2 = (EditText)rootView.findViewById(R.id.contactNumber2);
                EditText contact3 = (EditText)rootView.findViewById(R.id.contactNumber3);

                //TODO: put validations
                if (personName.getText()==null && location.getText()==null){
                    Toast.makeText(getActivity(), "Please give name or location", Toast.LENGTH_SHORT).show();
                }else{
                    //call the helper methods to search with the details given
                }
            }
        });
        return rootView;
    }
}

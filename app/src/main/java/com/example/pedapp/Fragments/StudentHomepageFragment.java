package com.example.pedapp.Fragments;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.R;

public class StudentHomepageFragment extends Fragment {
    private Button buttonLogOut, buttonResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_homepage, container, false);
        enableBackArrow(false);
        buttonLogOut = view.findViewById(R.id.ButtonLogout);
        buttonResult = view.findViewById(R.id.ButtonResults);
        logout();
        results();
        Spinner spinnerTest = view.findViewById(R.id.SpinnerStudent);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.testLists,R.layout.student_homepage_spinner);
        adapter.setDropDownViewResource(R.layout.student_homepage_spinner);
        spinnerTest.setAdapter(adapter);

        return view;
    }

    public void results(){
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new ResultFragment(),null)
                        .commit();
            }
        });
    }

    /**
     * if you click the "kijelentkezes" button, you can log out
     */
    public void logout(){
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new TeacherHomepageFragment(),null)
                        .commit();
            }
        });
    }

    /**
     * if the enable parameter is true, appear the back button in toolbar
     * if false, doesn't appear this fragment the back button
     * @param enable
     */
    private void enableBackArrow(boolean enable){
        ActionBar supportActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(enable);
        supportActionBar.setDisplayHomeAsUpEnabled(enable);
        supportActionBar.setDisplayShowHomeEnabled(enable);
    }
}

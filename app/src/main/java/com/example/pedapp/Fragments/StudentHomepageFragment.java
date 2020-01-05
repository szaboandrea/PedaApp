package com.example.pedapp.Fragments;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.Classes.Test;
import com.example.pedapp.Classes.TestAdapter;
import com.example.pedapp.Database.SQLiteTest;
import com.example.pedapp.R;

import java.util.ArrayList;

public class StudentHomepageFragment extends Fragment {
    private Button buttonLogOut, buttonTestFill;
    private ArrayList<String> groupList = new ArrayList<>();
    private ArrayList<Test> tests = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TestAdapter testAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_homepage, container, false);
        enableBackArrow(false);
        SQLiteTest database = new SQLiteTest(getActivity());
        groupList = database.getAllGroupName();
        buttonLogOut = view.findViewById(R.id.ButtonLogout);
        buttonTestFill = view.findViewById(R.id.ButtonTestFill);
        recyclerView = view.findViewById(R.id.recyclerViewTest);

        logout();
        final Spinner spinnerTest = view.findViewById(R.id.SpinnerStudent);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.student_homepage_spinner, groupList);
        adapter.setDropDownViewResource(R.layout.student_homepage_spinner);
        spinnerTest.setAdapter(adapter);

        buttonTestFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItem = spinnerTest.getSelectedItem().toString();
                SQLiteTest database = new SQLiteTest(getActivity());
                tests = database.getTestOneGroup(selectedItem);
                layoutManager = new LinearLayoutManager(getContext());
                testAdapter = new TestAdapter(getContext(), tests);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(testAdapter);
            }
        });

        return view;
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

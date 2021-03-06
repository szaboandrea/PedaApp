package com.example.pedapp.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.Classes.Test;
import com.example.pedapp.Classes.TestAdapter;
import com.example.pedapp.Database.SQLiteTest;
import com.example.pedapp.R;

import java.util.ArrayList;


public class TestCompleteFragment extends Fragment {

    private String testName;
    private ArrayList<Test> tests = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TestAdapter testAdapter;
    private TextView textViewTestName;

    public TestCompleteFragment(String testName) {
        this.testName = testName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_complete, container, false);
        enableBackArrow(true);
        recyclerView = view.findViewById(R.id.recyclerViewTest);
        textViewTestName = view.findViewById(R.id.tv_testname);
        textViewTestName.setText(testName);

        SQLiteTest database = new SQLiteTest(getActivity());
        tests = database.getTestOneGroup(testName);

        layoutManager = new LinearLayoutManager(getContext());
        testAdapter = new TestAdapter(getContext(), tests);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(testAdapter);
        return view;
    }

    /**
     * provide what does it do, if I click the back button
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar = ((MainActivity) getActivity()).findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new StudentHomepageFragment(),null)
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

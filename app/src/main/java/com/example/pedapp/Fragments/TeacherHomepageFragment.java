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

public class TeacherHomepageFragment extends Fragment {

    private Button buttonLogOut, buttonCreateTest, buttonTeacherResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_homepage, container, false);
        enableBackArrow(false);
        buttonLogOut = view.findViewById(R.id.ButtonLogout);
        buttonTeacherResult = view.findViewById(R.id.ButtonTeacherResult);
        buttonCreateTest = view.findViewById(R.id.ButtonTeacherTestCreate);
        logout();
        results();
        createTest();
        return view;
    }

    public void createTest(){
        buttonCreateTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new TestCreateFragment(),null)
                        .commit();
            }
        });
    }

    public void results(){
        buttonTeacherResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new ResultFragment(),null)
                        .commit();
            }
        });
    }

    public void logout(){
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new LoginFragment(),null)
                        .commit();
            }
        });
    }

    private void enableBackArrow(boolean enable){
        ActionBar supportActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(enable);
        supportActionBar.setDisplayHomeAsUpEnabled(enable);
        supportActionBar.setDisplayShowHomeEnabled(enable);
    }
}

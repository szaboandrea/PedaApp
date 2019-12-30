package com.example.pedapp.Fragments;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.R;

public class LoginFragment extends Fragment {

    private Button buttonLogin;
    private TextView textViewRegistration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        enableBackArrow(false);
        buttonLogin = view.findViewById(R.id.ButtonLogin);
        textViewRegistration = view.findViewById(R.id.TextViewRegistration);
        login();
        registration();
        return view;
    }

    public void login(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, new StudentHomepageFragment(),null).commit();
            }
        });
    }
    public void registration(){
        textViewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, new RegisterFragment(),null).commit();
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

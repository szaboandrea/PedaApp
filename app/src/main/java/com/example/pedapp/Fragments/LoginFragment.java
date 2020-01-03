package com.example.pedapp.Fragments;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.Database.SQLiteProfile;
import com.example.pedapp.R;

public class LoginFragment extends Fragment {

    private Button buttonLogin;
    private TextView textViewRegistration;
    private EditText editTextEmail, editTextPassword;
    private String mEmail, mPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        enableBackArrow(false);
        buttonLogin = view.findViewById(R.id.ButtonLogin);
        textViewRegistration = view.findViewById(R.id.TextViewRegistration);
        editTextEmail = view.findViewById(R.id.EditTextEmail);
        editTextPassword = view.findViewById(R.id.EditTextPassword);
        login();
        registration();
        return view;
    }

    public void login(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = editTextEmail.getText().toString();
                mPassword = editTextPassword.getText().toString();
                if (checkFields(mEmail, mPassword)){
                    entry(mEmail, mPassword);
                }
            }
        });
    }

    /**
     * check if either field is empty, then send an error message to enter that field
     * @param mEmail
     * @param mPassword
     */
    public void entry(String mEmail, String mPassword){
        SQLiteProfile database = new SQLiteProfile(getActivity());
        if (mPassword.matches(database.getPassword(mEmail))){
            database.getAllProfile();
            MainActivity.mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, new StudentHomepageFragment(),null).commit();
        }
        else{
            Toast.makeText(getActivity(), "Password or email wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void registration(){
        textViewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, new RegisterFragment(),null).commit();
            }
        });
    }

    public boolean checkFields(final String username, final String password){
        if (username.isEmpty()){
            editTextEmail.setError("Please enter your full name");
            editTextEmail.requestFocus();
            return false;
        }
        else if (password.isEmpty()) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return false;
        }
        return true;
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

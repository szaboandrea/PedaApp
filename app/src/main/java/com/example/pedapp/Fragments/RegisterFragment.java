package com.example.pedapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.Database.SQLiteProfile;
import com.example.pedapp.R;

public class RegisterFragment extends Fragment {

    private EditText editTextUsername, editTextEmail, editTextPasswordFirst, editTextPasswordSecond;
    private RadioButton radioButtonTeacher, radioButtonStudent;
    private Button buttonRegister;
    private String mUsername, mEmail, mStatus, mPassword1, mPassword2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editTextUsername = view.findViewById(R.id.editTextNameRegister);
        editTextEmail = view.findViewById(R.id.editTextEmailRegister);
        editTextPasswordFirst = view.findViewById(R.id.editTextPasswordRegister);
        editTextPasswordSecond = view.findViewById(R.id.editTextPasswordRegisterAgain);
        radioButtonTeacher = view.findViewById(R.id.radioButtonTeacher);
        radioButtonStudent = view.findViewById(R.id.radioButtonStudent);
        buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        enableBackArrow(true);
        return view;
    }

    public void register(){
        mUsername = editTextUsername.getText().toString();
        mEmail = editTextEmail.getText().toString();
        mPassword1 = editTextPasswordFirst.getText().toString();
        mPassword2 = editTextPasswordSecond.getText().toString();
        if (radioButtonStudent.isChecked()){
            mStatus = "0";
        }
        else {
            mStatus = "1";
        }
        if (emptyField(mUsername, mEmail, mPassword1, mPassword2)){
            SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor edt = pref.edit();
            edt.putString("email",mEmail);
            edt.commit();
            SQLiteProfile database  = new SQLiteProfile(getActivity());
            database.insertProfile(mUsername, mEmail,mStatus,mPassword1);
            MainActivity.mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, new StudentHomepageFragment(),null).commit();
        }
    }

    /**
     * check if a field is empty, then set an error message, to enter the empty field
     * @param username
     * @param email
     * @param password1
     * @param password2
     */
    public boolean emptyField(final String username, final String email,
                              final String password1,
                              final String password2){
        if (username.isEmpty()){
            editTextUsername.setError("Please enter your full name");
            editTextUsername.requestFocus();
            return false;
        }
        else if (email.isEmpty()){
            editTextEmail.setError("Please enter your email address");
            editTextEmail.requestFocus();
            return false;
        }
        else if (password1.isEmpty()) {
            editTextPasswordFirst.setError("Please enter your password");
            editTextPasswordFirst.requestFocus();
            return false;
        }
        else if (password1.isEmpty()) {
            editTextPasswordSecond.setError("Please enter your password again");
            editTextPasswordSecond.requestFocus();
            return false;
        }
        else if (!password1.matches(password2)){
            editTextPasswordSecond.setError("Your passwords doesn't match");
            editTextPasswordSecond.requestFocus();
            return false;
        }
        else if (!radioButtonStudent.isChecked() && !radioButtonTeacher.isChecked()){
            Toast.makeText(getActivity(), "Please select your status", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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
                        .replace(R.id.fragmentContainer, new LoginFragment(),null)
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

package com.example.pedapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.Database.SQLiteProfile;
import com.example.pedapp.R;

import java.util.ArrayList;


public class ResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TableLayout myTable = view.findViewById(R.id.tableLayoutResult);

        SQLiteProfile database = new SQLiteProfile(getActivity());
        ArrayList<String> profiles = new ArrayList<>();
        profiles = database.getAllProfile();
        TableRow row = new TableRow(getActivity());
        row.setId(0);
        row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView student = new TextView(getActivity());
        student.setText("Diak");
        student.setId(0);
        student.setTextColor(Color.WHITE);
        student.setTextSize(20);
        student.setPadding(10,10,50,50);
        row.addView(student);

        TextView note = new TextView(getActivity());
        note.setText("Jegy");
        note.setId(0);
        note.setTextColor(Color.WHITE);
        note.setTextSize(20);
        note.setPadding(10,10,50,50);
        row.addView(note);

        TextView test = new TextView(getActivity());
        test.setText("Teszt");
        test.setId(0);
        test.setTextColor(Color.WHITE);
        test.setTextSize(20);
        test.setPadding(10,10,50,50);
        row.addView(test);

        myTable.addView(row);

        for (int i = 1; i<profiles.size();++i)
        {
            TableRow row1 = new TableRow(getActivity());
            row.setId(i);
            row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            TextView tx1 = new TextView(getActivity());
            tx1.setText(profiles.get(i));
            tx1.setId(i);
            tx1.setTextColor(Color.WHITE);
            tx1.setTextSize(20);
            tx1.setPadding(10,10,50,50);
            row1.addView(tx1);

            TextView tx2 = new TextView(getActivity());
            tx2.setText("5");
            tx2.setId(i);
            tx2.setTextColor(Color.WHITE);
            tx2.setTextSize(20);
            tx2.setPadding(10,10,50,50);
            row1.addView(tx2);

            TextView tx3 = new TextView(getActivity());
            tx3.setText("Teszt1");
            tx3.setId(i);
            tx3.setTextColor(Color.WHITE);
            tx3.setTextSize(20);
            tx3.setPadding(10,10,50,50);
            row1.addView(tx3);

            myTable.addView(row1);
        }
        enableBackArrow(true);
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

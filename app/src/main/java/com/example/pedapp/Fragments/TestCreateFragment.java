package com.example.pedapp.Fragments;

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
import android.widget.Toast;

import com.example.pedapp.Activity.MainActivity;
import com.example.pedapp.Database.SQLiteTest;
import com.example.pedapp.R;

public class TestCreateFragment extends Fragment {

    private EditText editTextGroupName, editTextQuestion, editTextAnswear1,
            editTextAnswear2, editTextAnswear3, editTextAnswear4, editTextCorrectAnwear;
    private Button buttonAdd;
    private String groupname, question, answear, answear2, answear3, answear4, correctanswear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_create, container, false);

        editTextGroupName = view.findViewById(R.id.editTextTestCreateGroupName);
        editTextQuestion = view.findViewById(R.id.editTextTestCreateQuestion);
        editTextAnswear1 = view.findViewById(R.id.editTextTestCreateAnswer1);
        editTextAnswear2 = view.findViewById(R.id.editTextTestCreateAnswer2);
        editTextAnswear3 = view.findViewById(R.id.editTextTestCreateAnswer3);
        editTextAnswear4 = view.findViewById(R.id.editTextTestCreateAnswer4);
        editTextCorrectAnwear = view.findViewById(R.id.editTextTestCreateAnswerCorrect);
        buttonAdd = view.findViewById(R.id.ButtonTestCreateAddQuestion);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElement();
            }
        });
        enableBackArrow(true);
        return view;
    }

    public void addElement(){
        groupname = editTextGroupName.getText().toString();
        question = editTextQuestion.getText().toString();
        answear = editTextAnswear1.getText().toString();
        answear2 = editTextAnswear2.getText().toString();
        answear3 = editTextAnswear3.getText().toString();
        answear4 = editTextAnswear4.getText().toString();
        correctanswear = editTextCorrectAnwear.getText().toString();
        if (emptyField(groupname, question, answear, answear2, answear3, answear4, correctanswear)){
            SQLiteTest database =new SQLiteTest(getActivity());
            database.insertTest(groupname, question, answear, answear2,answear3,answear4,correctanswear);
            editTextQuestion.setText("");
            editTextAnswear1.setText("");
            editTextAnswear2.setText("");
            editTextAnswear3.setText("");
            editTextAnswear4.setText("");
            editTextCorrectAnwear.setText("");
            database.getAllGroupName();
        }
    }

    public boolean emptyField(final String grupname, final String question,
                              final String answear1, final String answear2,
                              final String answear3, final String answear4,
                              final String correctAnswear){
        if (grupname.isEmpty()){
            editTextGroupName.setError("Please enter the group name");
            editTextGroupName.requestFocus();
            return false;
        }
        else if (question.isEmpty()){
            editTextQuestion.setError("Please enter the question");
            editTextQuestion.requestFocus();
            return false;
        }
        else if (answear1.isEmpty()) {
            editTextAnswear1.setError("Please enter the first answear");
            editTextAnswear1.requestFocus();
            return false;
        }
        else if (answear2.isEmpty()) {
            editTextAnswear2.setError("Please enter the second answear");
            editTextAnswear2.requestFocus();
            return false;
        }else if (answear3.isEmpty()) {
            editTextAnswear3.setError("Please enter the third answear");
            editTextAnswear3.requestFocus();
            return false;
        }else if (answear4.isEmpty()) {
            editTextAnswear4.setError("Please enter the fourth answear");
            editTextAnswear4.requestFocus();
            return false;
        }
        else if (correctAnswear.isEmpty()){
            editTextCorrectAnwear.setError("Please add the correct answear");
            editTextCorrectAnwear.requestFocus();
            return false;
        }
        else if (!correctAnswear.matches(answear1) && !correctAnswear.matches(answear2)
            && !correctAnswear.matches(answear3) && !correctAnswear.matches(answear4)){
            editTextCorrectAnwear.setError("The answear doesn't match no one answear");
            editTextCorrectAnwear.requestFocus();
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

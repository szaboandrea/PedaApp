package com.example.pedapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.example.pedapp.Fragments.LoginFragment;
import com.example.pedapp.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PlanningPokerMain";

    public static FragmentManager mFragmentManager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragmentContainer)!=null){
            if (savedInstanceState != null){
                return;
            }
            mFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, new LoginFragment(), null)
                    .commit();
        }
    }
}

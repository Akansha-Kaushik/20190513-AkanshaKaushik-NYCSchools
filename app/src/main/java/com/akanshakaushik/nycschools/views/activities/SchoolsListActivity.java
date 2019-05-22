package com.akanshakaushik.nycschools.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akanshakaushik.nycschools.R;
import com.akanshakaushik.nycschools.views.fragments.SchoolsListFragment;

/**
 * Activity to show list of schools
 */
public class SchoolsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         * Add fragment to container
         */
        SchoolsListFragment schoolsListFragment = new SchoolsListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, schoolsListFragment)
                .setReorderingAllowed(true)
                .commit();
    }
}

package com.akanshakaushik.nycschools.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akanshakaushik.nycschools.R;
import com.akanshakaushik.nycschools.models.School;
import com.akanshakaushik.nycschools.presenter.SchoolsListPresenter;
import com.akanshakaushik.nycschools.utils.Utilities;
import com.akanshakaushik.nycschools.views.adapters.SchoolsListAdapter;
import com.akanshakaushik.nycschools.views.interfaces.ISchoolsListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolsListFragment extends Fragment implements ISchoolsListView {
    View view;
    private SchoolsListAdapter schoolsListAdapter;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.list_loading_progress_bar)
    ContentLoadingProgressBar loadingSpinner;

    private List<School> schoolList;
    private SchoolsListPresenter schoolsListPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.schools_list, container, false);
        ButterKnife.bind(this, view);

        schoolsListPresenter = new SchoolsListPresenter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (schoolList != null && schoolList.size() > 0) {
            schoolsListAdapter = new SchoolsListAdapter(getActivity(), schoolList);
            recyclerView.setAdapter(schoolsListAdapter);
        } else if (Utilities.isConnected(getActivity())) { // If there is a network connection, fetch data
            schoolsListPresenter.sendSchoolsListRequest();
        } else {
            updateViewNoDataAvailable();
        }

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                schoolsListPresenter.sendSchoolsListRequest();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return view;
    }

    @Override
    public void showProgress() {
        loadingSpinner.show();
        loadingSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        loadingSpinner.hide();
        loadingSpinner.setVisibility(View.GONE);
    }

    @Override
    public void updateView(List<School> schoolListResponse) {
        if (schoolList == null) {
            schoolList = new ArrayList<>();
            schoolList = schoolListResponse;
            schoolsListAdapter = new SchoolsListAdapter(getActivity(), schoolList);
            /* Setting adapter to recycler view. */
            recyclerView.setAdapter(schoolsListAdapter);
        } else {
            schoolsListAdapter.clearAll();
            schoolList = schoolListResponse;
            schoolsListAdapter.addAll(schoolList);
            recyclerView.setAdapter(schoolsListAdapter);
            swipeContainer.setRefreshing(false);
        }

    }

    @Override
    public void updateViewNoDataAvailable() {
        loadingSpinner.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getResources().getString(R.string.no_data) +
                getResources().getString(R.string.single_space) + getResources().getString(R.string.no_data_1), Toast.LENGTH_LONG).show();
    }
}

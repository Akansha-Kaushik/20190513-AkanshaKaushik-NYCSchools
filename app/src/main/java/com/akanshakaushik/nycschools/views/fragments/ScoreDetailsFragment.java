package com.akanshakaushik.nycschools.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akanshakaushik.nycschools.R;
import com.akanshakaushik.nycschools.constants.Constants;
import com.akanshakaushik.nycschools.models.ScoreDetails;
import com.akanshakaushik.nycschools.presenter.ScoreDetailsPresenter;
import com.akanshakaushik.nycschools.utils.Utilities;
import com.akanshakaushik.nycschools.views.interfaces.IScoreDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreDetailsFragment extends Fragment implements IScoreDetailsView {

    View view;

    @BindView(R.id.list_loading_progress_bar)
    ContentLoadingProgressBar loadingSpinner;

    @BindView(R.id.text_view_school_name)
    TextView textViewSchoolName;

    @BindView(R.id.text_view_total_test_takers)
    TextView textViewTotalTestTakers;

    @BindView(R.id.text_view_writing_score)
    TextView textViewWritingScore;

    @BindView(R.id.text_view_reading_score)
    TextView textViewReadingScore;

    @BindView(R.id.text_view_math_score)
    TextView textViewMathScore;

    private ScoreDetailsPresenter scoreDetailsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_score_details, container, false);
        ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        String schoolId = arguments.getString(Constants.Bundle.SCHOOL_ID_KEY);

        scoreDetailsPresenter = new ScoreDetailsPresenter(this);

        // If there is a network connection, fetch data
        if (Utilities.isConnected(getActivity())) {
            scoreDetailsPresenter.sendScoreDetailsRequest(schoolId);
        }
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
    public void updateView(ScoreDetails scoreDetails) {
        String colon = getResources().getString(R.string.colon_space);
        if (scoreDetails != null) {
            textViewSchoolName.setText(scoreDetails.getSchoolName());
            textViewTotalTestTakers.setText(getResources().getString(R.string.total_test_takers) + colon + scoreDetails.getTotalTestTakers());
            textViewWritingScore.setText(getResources().getString(R.string.average_writing_score) + colon + scoreDetails.getWritingAverageScore());
            textViewReadingScore.setText(getResources().getString(R.string.average_reading_scores) + colon + scoreDetails.getCriticalReadingScore());
            textViewMathScore.setText(getResources().getString(R.string.average_math_score) + colon + scoreDetails.getMathAverageScore());
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.no_data) +
                    getResources().getString(R.string.single_space) + getResources().getString(R.string.no_data_1), Toast.LENGTH_SHORT).show();
            textViewSchoolName.setText(getResources().getString(R.string.no_data_1));
        }
    }
}

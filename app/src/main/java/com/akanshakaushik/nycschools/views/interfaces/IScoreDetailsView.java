package com.akanshakaushik.nycschools.views.interfaces;

import com.akanshakaushik.nycschools.models.ScoreDetails;

/**
 * View Contract to provide behaviour to a particular screen
 */
public interface IScoreDetailsView {

    /**
     * Method to show progress dialog.
     */
    void showProgress();

    /**
     * Method to dismiss progress dialog.
     */
    void dismissProgress();

    /**
     * Method to update views.
     */
    void updateView(ScoreDetails scoreDetails);
}

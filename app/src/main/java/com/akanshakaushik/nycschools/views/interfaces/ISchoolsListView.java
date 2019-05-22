package com.akanshakaushik.nycschools.views.interfaces;

import com.akanshakaushik.nycschools.models.School;

import java.util.List;

/**
 * View Contract to provide behaviour to a particular screen
 */
public interface ISchoolsListView {

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
    void updateView(List<School> schoolList);

    /**
     * Method to update views when no data is available.
     */
    void updateViewNoDataAvailable();
}

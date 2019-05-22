package com.akanshakaushik.nycschools.presenter;

import com.akanshakaushik.nycschools.models.School;
import com.akanshakaushik.nycschools.network.IResponseListener;
import com.akanshakaushik.nycschools.network.SchoolsListService;
import com.akanshakaushik.nycschools.views.interfaces.ISchoolsListView;

import java.util.List;

/**
 * Presenter class to interact with view and models
 */
public class SchoolsListPresenter implements IResponseListener {

    private ISchoolsListView schoolsListView;

    /**
     * Constructor class for SchoolsListPresenter
     *
     * @param schoolsListView - view instance.
     */
    public SchoolsListPresenter(ISchoolsListView schoolsListView) {
        this.schoolsListView = schoolsListView;
    }

    /**
     * Method to send service request
     */
    public void sendSchoolsListRequest() {
        schoolsListView.showProgress();
        SchoolsListService schoolsListService = new SchoolsListService(this);
        schoolsListService.startService();
    }

    @Override
    public void onSuccessResponse(Object successResponse) {
        schoolsListView.dismissProgress();
        if (null != successResponse) {
            List<School> schoolList = (List<School>) successResponse;
            if (!schoolList.isEmpty()) {
                schoolsListView.updateView(schoolList);
            } else {
                schoolsListView.updateViewNoDataAvailable();
            }
        }
    }

    @Override
    public void onFailureResponse(Throwable failure) {
        schoolsListView.dismissProgress();
        schoolsListView.updateViewNoDataAvailable();
    }
}

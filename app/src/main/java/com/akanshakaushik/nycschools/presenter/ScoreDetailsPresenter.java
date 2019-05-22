package com.akanshakaushik.nycschools.presenter;

import com.akanshakaushik.nycschools.models.ScoreDetails;
import com.akanshakaushik.nycschools.network.IResponseListener;
import com.akanshakaushik.nycschools.network.ScoreDetailsService;
import com.akanshakaushik.nycschools.views.interfaces.IScoreDetailsView;

import java.util.List;

/**
 * Presenter class to interact with view and models
 */
public class ScoreDetailsPresenter implements IResponseListener {


    private IScoreDetailsView scoreDetailsView;

    /**
     * Constructor class for ScoreDetailsPresenter
     *
     * @param scoreDetailsView - view instance.
     */
    public ScoreDetailsPresenter(IScoreDetailsView scoreDetailsView) {
        this.scoreDetailsView = scoreDetailsView;
    }

    /**
     * Method to send service request
     */
    public void sendScoreDetailsRequest(String schoolId) {
        scoreDetailsView.showProgress();
        ScoreDetailsService scoreDetailsService = new ScoreDetailsService(schoolId, this);
        scoreDetailsService.startService();
    }

    @Override
    public void onSuccessResponse(Object successResponse) {
        scoreDetailsView.dismissProgress();
        if (null != successResponse) {
            List<ScoreDetails> scoreDetails = (List<ScoreDetails>) successResponse;
            if (!scoreDetails.isEmpty() && scoreDetails.size() > 0) {
                scoreDetailsView.updateView(scoreDetails.get(0));
            } else {
                scoreDetailsView.updateView(null);
            }
        }
    }

    @Override
    public void onFailureResponse(Throwable failure) {
        scoreDetailsView.dismissProgress();
    }
}

package com.akanshakaushik.nycschools.network;

import android.util.Log;

import com.akanshakaushik.nycschools.models.ScoreDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Service class to retrieve SAT Score card details
 * Uses IResponseListener to send results to Presenter layer
 */
public class ScoreDetailsService {

    private static final String TAG = ScoreDetailsService.class.getSimpleName();
    private IResponseListener mResponseListener;
    private Call<List<ScoreDetails>> scoreDetailsServiceCall;

    public ScoreDetailsService(String id, IResponseListener responseListener) {
        this.mResponseListener = responseListener;
        this.scoreDetailsServiceCall = ApiNetworkClient.getNetworkClient().getScoreDetails(id);
    }

    public void startService() {
        if (scoreDetailsServiceCall == null) {
            return;
        }
        scoreDetailsServiceCall.enqueue(new Callback<List<ScoreDetails>>() {
            @Override
            public void onResponse(Call<List<ScoreDetails>> call, Response<List<ScoreDetails>> response) {
                if (response != null && response.body() != null) {
                    mResponseListener.onSuccessResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ScoreDetails>> failResponseCall, Throwable throwable) {
                mResponseListener.onFailureResponse(throwable);
                cancelService();
            }
        });
    }

    public void cancelService() {
        if (scoreDetailsServiceCall != null && !scoreDetailsServiceCall.isCanceled()) {
            scoreDetailsServiceCall.cancel();
            Log.e(TAG, "ScoreDetailsService request canceled.");
        }
    }
}

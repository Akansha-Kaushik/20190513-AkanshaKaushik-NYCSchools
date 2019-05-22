package com.akanshakaushik.nycschools.network;

import android.util.Log;

import com.akanshakaushik.nycschools.models.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Service class to retrieve List of Schools
 * Uses IResponseListener to send results to Presenter layer
 */
public class SchoolsListService {

    private static final String TAG = SchoolsListService.class.getSimpleName();
    private IResponseListener mResponseListener;
    private Call<List<School>> schoolsListServiceCall;

    public SchoolsListService(IResponseListener responseListener) {
        this.mResponseListener = responseListener;
        this.schoolsListServiceCall = ApiNetworkClient.getNetworkClient().getSchoolsList();
    }

    public void startService() {
        if (schoolsListServiceCall == null) {
            return;
        }
        schoolsListServiceCall.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                if (response.body() != null) {
                    mResponseListener.onSuccessResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<School>> failResponseCall, Throwable throwable) {
                mResponseListener.onFailureResponse(throwable);
                cancelService();
            }
        });
    }

    public void cancelService() {
        if (schoolsListServiceCall != null && !schoolsListServiceCall.isCanceled()) {
            schoolsListServiceCall.cancel();
            Log.e(TAG, "schoolsListService request canceled.");
        }
    }
}

package com.akanshakaushik.nycschools.network;

/**
 * Interface to provide common behaviour to handle Service Response
 */
public interface IResponseListener {

    /* Method to be called on successful service response*/
    void onSuccessResponse(Object successResponse);

    /* Method to be called on failed service response*/
    void onFailureResponse(Throwable failure);
}

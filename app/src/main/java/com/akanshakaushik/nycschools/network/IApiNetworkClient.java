package com.akanshakaushik.nycschools.network;

import com.akanshakaushik.nycschools.models.School;
import com.akanshakaushik.nycschools.models.ScoreDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.akanshakaushik.nycschools.constants.Constants.Service.PATH_SCHOOL_LISTS;
import static com.akanshakaushik.nycschools.constants.Constants.Service.PATH_SCORE_DETAILS;
import static com.akanshakaushik.nycschools.constants.Constants.Service.QUERY_DBN;

/**
 * Interface to communicate with service Request & Response
 */
public interface IApiNetworkClient {

    @GET(PATH_SCHOOL_LISTS)
    Call<List<School>> getSchoolsList();

    @GET(PATH_SCORE_DETAILS)
    Call<List<ScoreDetails>> getScoreDetails(@Query(QUERY_DBN) String dbn);
}

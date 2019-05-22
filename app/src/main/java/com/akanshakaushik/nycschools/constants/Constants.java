package com.akanshakaushik.nycschools.constants;

/**
 * Common place to keep all constants
 */
public interface Constants {

    /**
     * The Service Constants will contain all service/API related constants.
     */
    @interface Service {
        String PATH_SCHOOL_LISTS = "s3k6-pzi2.json";
        String PATH_SCORE_DETAILS = "f9bf-2cp4.json";
        String QUERY_DBN = "dbn";
        String TAG_DBN = "dbn";
        String TAG_SCHOOL_NAME = "school_name";
        String TAG_PHONE_NUMBER = "phone_number";
        String TAG_LOCATION = "location";
        String TAG_NO_OF_TEST_TAKERS = "num_of_sat_test_takers";
        String TAG_AVERAGE_READING_SCORE = "sat_critical_reading_avg_score";
        String TAG_AVERAGE_MATH_SCORE = "sat_math_avg_score";
        String TAG_AVERAGE_WRITING_SCORE = "sat_writing_avg_score";
    }

    /**
     * The Bundle Constants contain all bundle and intent related constants
     */
    @interface Bundle {
        String SCHOOL_ID_KEY = "SCHOOL_ID";
    }
}
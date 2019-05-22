package com.akanshakaushik.nycschools.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_AVERAGE_MATH_SCORE;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_AVERAGE_READING_SCORE;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_AVERAGE_WRITING_SCORE;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_DBN;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_NO_OF_TEST_TAKERS;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_SCHOOL_NAME;

/**
 * Model class for SAT Score details
 */
public class ScoreDetails implements Serializable {

    @SerializedName(TAG_DBN)
    private String id;

    @SerializedName(TAG_NO_OF_TEST_TAKERS)
    private String totalTestTakers;

    @SerializedName(TAG_AVERAGE_READING_SCORE)
    private String criticalReadingScore;

    @SerializedName(TAG_AVERAGE_MATH_SCORE)
    private String mathAverageScore;

    @SerializedName(TAG_AVERAGE_WRITING_SCORE)
    private String writingAverageScore;

    @SerializedName(TAG_SCHOOL_NAME)
    private String schoolName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalTestTakers() {
        return totalTestTakers;
    }

    public void setTotalTestTakers(String totalTestTakers) {
        this.totalTestTakers = totalTestTakers;
    }

    public String getCriticalReadingScore() {
        return criticalReadingScore;
    }

    public void setCriticalReadingScore(String criticalReadingScore) {
        this.criticalReadingScore = criticalReadingScore;
    }

    public String getMathAverageScore() {
        return mathAverageScore;
    }

    public void setMathAverageScore(String mathAverageScore) {
        this.mathAverageScore = mathAverageScore;
    }

    public String getWritingAverageScore() {
        return writingAverageScore;
    }

    public void setWritingAverageScore(String writingAverageScore) {
        this.writingAverageScore = writingAverageScore;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}

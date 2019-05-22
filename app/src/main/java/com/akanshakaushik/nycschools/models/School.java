package com.akanshakaushik.nycschools.models;

import com.akanshakaushik.nycschools.utils.Utilities;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_DBN;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_LOCATION;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_PHONE_NUMBER;
import static com.akanshakaushik.nycschools.constants.Constants.Service.TAG_SCHOOL_NAME;

/**
 * Model class for School
 * Used Serializable instead of Parcelable because of large response (manual serialization takes more time to achieve)
 */
public class School implements Serializable {

    @SerializedName(TAG_DBN)
    private String id;

    @SerializedName(TAG_SCHOOL_NAME)
    private String name;

    @SerializedName(TAG_PHONE_NUMBER)
    private String phoneNumber;

    @SerializedName(TAG_LOCATION)
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        // Formatting address to remove Latitude and Longitude
        return Utilities.formatAddress(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

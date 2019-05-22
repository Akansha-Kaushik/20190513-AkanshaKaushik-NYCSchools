package com.akanshakaushik.nycschools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Common place to keep all Utility methods
 */
public class Utilities {

    /**
     * Ths method (function) for checking internet connection.
     *
     * @param context of the app.
     * @return true if internet connection is present else return false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Ths method (function) for formatting the address
     * removes latitude and longitude
     *
     * @param address
     * @return formatted address
     */
    public static String formatAddress(String address) {
        if (address != null && !address.isEmpty()) {
            int firstIndex = address.indexOf("(");
            if (firstIndex != -1) {
                address = address.substring(0, address.indexOf("("));
            }
        }
        return address;
    }
}

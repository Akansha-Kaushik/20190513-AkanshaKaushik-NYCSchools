package com.akanshakaushik.nycschools.network;

import com.akanshakaushik.nycschools.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiNetworkClient class is main Retrofit Api Client with Singleton instance
 */
public class ApiNetworkClient {

    private static Retrofit.Builder retrofit = null;

    /**
     * Private constructor to restrict the instantiation of class.
     */
    private ApiNetworkClient() {
        throw new AssertionError();
    }

    /**
     * Method to get the network client interface.
     *
     * @return - return network client.
     */
    public static IApiNetworkClient getNetworkClient() {
        String baseUrl = BuildConfig.BASE_URL;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder();
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(loggingInterceptor);
            }
            retrofit.client(httpClient.build());
            retrofit.addConverterFactory(GsonConverterFactory.create());
        }
        retrofit.baseUrl(baseUrl);
        return retrofit.build().create(IApiNetworkClient.class);
    }
}

package sn.ept.dic2.dev_mobile.tp.personapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.ept.dic2.dev_mobile.tp.personapp.services.ProjectDevMobileAPIService;

public class ApiClient {

    private static final String API_URL =  "http://185.98.128.121/";

    public static ProjectDevMobileAPIService getApiClient(){
        return getApiService(API_URL).create(ProjectDevMobileAPIService.class);
    }

    public static Retrofit getApiService(String url){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

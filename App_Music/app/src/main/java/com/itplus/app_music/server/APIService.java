package com.itplus.app_music.server;

public class APIService {

    // private static String base_url = "https://symptomless-designa.000webhostapp.com/Server/";
    private static String base_url = "https://musicbasicapp.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}

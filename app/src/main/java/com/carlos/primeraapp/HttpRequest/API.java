package com.carlos.primeraapp.HttpRequest;

public class API {

    //url cambiamos de IP cuando cambiamos de red
    public static String URL = "http://192.168.0.18:3001/api/";

    public API(){}

    public static String getURL() {
        return URL;
    }
}

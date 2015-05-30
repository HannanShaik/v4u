package com.mobifever.v4u.network.service;

public interface V4uApi {
    public static final String BASE_URL = "https://we4u.herokuapp.com";

    interface ISecurity{
        
    }

    interface IDisaster {
        static final String GET_DISASTERS = BASE_URL + "/disaster";
    }

    interface ICasuality{
        static final String REPORT = BASE_URL + "/casuality";
        static final String SEARCH = BASE_URL + "/casuality/query";
    }
}

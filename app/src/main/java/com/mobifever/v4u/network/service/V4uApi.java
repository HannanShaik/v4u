package com.mobifever.v4u.network.service;

public interface V4uApi {
    public static final String BASE_URL = "";

    interface ISecurity{
        
    }

    interface IDisaster {
        static final String GET_DISASTERS = BASE_URL + "/disaster";
    }

    interface IDisasterSearch{
        static final String SEARCH = BASE_URL + "/search";

    }
}

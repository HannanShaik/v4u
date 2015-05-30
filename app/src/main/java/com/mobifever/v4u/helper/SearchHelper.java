package com.mobifever.v4u.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.mobifever.v4u.V4UApplication;
import com.mobifever.v4u.model.Disaster;
import com.mobifever.v4u.network.ResponseTranslator;
import com.mobifever.v4u.network.VolleyRequestQueue;
import com.mobifever.v4u.network.dto.CasualityDTO;
import com.mobifever.v4u.network.dto.SearchDTO;
import com.mobifever.v4u.network.service.ServiceCallback;
import com.mobifever.v4u.network.service.V4UException;
import com.mobifever.v4u.network.service.V4uApi;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class SearchHelper implements V4uApi.IDisasterSearch {

    private final Response.ErrorListener onError;
    private final Context mContext;
    private ResponseTranslator responseTranslator;
    private ServiceCallback serviceCallback;


    final String tag_json_obj = "json_obj_req";
    private VolleyRequestQueue volleyRequestQueue;

    public SearchHelper(Context context){
        this.mContext = context;
        responseTranslator = ResponseTranslator.getSharedInstance();
        volleyRequestQueue = com.mobifever.v4u.network.VolleyRequestQueue.getInstance();

        onError = new Response.ErrorListener() {

            public void onErrorResponse(VolleyError e) {
                V4UException exception = new V4UException(e.getMessage());
                serviceCallback.onFailure(exception);
            }
        };
    }

    public void search(SearchDTO searchDTO, final ServiceCallback<List<CasualityDTO>> serviceCallback){
        List<CasualityDTO> casualities = null;
        JSONObject searchJson = null;
        try {
            searchJson = new JSONObject(new Gson().toJson(searchDTO));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    SEARCH,
                    searchJson,
                    new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d(V4UApplication.TAG, response.toString());
                    try {
                        List<CasualityDTO> casualityList = responseTranslator.translateCasualityList(response);
                        if(casualityList!=null){
                            serviceCallback.onSuccess(casualityList);
                        } else {
                            serviceCallback.onFailure(new V4UException("No Disasters Available"));
                        }

                    } catch (Exception e) {
                        V4UException exception = new V4UException(e.getMessage());
                        serviceCallback.onFailure(exception);
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    serviceCallback.onFailure(null);
                }
            });

            int socketTimeout = 50000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);

            volleyRequestQueue.addToRequestQueue(jsonObjectRequest, mContext);
        } catch (Exception e){
            Log.e(V4UApplication.TAG, e.getMessage());
            V4UException exception = new V4UException(e.getMessage());
            serviceCallback.onFailure(exception);
        }
    }
}

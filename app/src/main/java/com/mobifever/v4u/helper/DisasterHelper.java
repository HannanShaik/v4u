package com.mobifever.v4u.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobifever.v4u.V4UApplication;
import com.mobifever.v4u.model.Disaster;
import com.mobifever.v4u.network.ResponseTranslator;
import com.mobifever.v4u.network.VolleyRequestQueue;
import com.mobifever.v4u.network.service.V4UException;
import com.mobifever.v4u.network.service.ServiceCallback;
import com.mobifever.v4u.network.service.V4uApi;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class DisasterHelper implements V4uApi.IDisaster {

    private final VolleyRequestQueue volleyRequestQueue;
    private final Response.ErrorListener onError;
    private ResponseTranslator responseTranslator;
    private ServiceCallback serviceCallback;
    private Context mContext;

    final String tag_json_obj = "json_obj_req";

    public DisasterHelper(Context context){
        responseTranslator = ResponseTranslator.getSharedInstance();
        volleyRequestQueue = VolleyRequestQueue.getInstance();
        mContext = context;
        onError = new Response.ErrorListener() {

            public void onErrorResponse(VolleyError e) {
                V4UException exception = new V4UException(e.getMessage());
                serviceCallback.onFailure(exception);
            }
        };
    }

    public void getDisasters(String locationName, final ServiceCallback<List<Disaster>> serviceCallback){

        String url;
        if(locationName!=null)
            url = GET_DISASTERS  + "/" + locationName;
        else
            url = GET_DISASTERS;

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    url,
                    null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d(V4UApplication.TAG, response.toString());
                    try {
                        List<Disaster> disasters = responseTranslator.translateDisasterList(response);
                        if(disasters!=null){
                            new SnappyDBHelper(mContext).saveDisasterInfo(disasters);
                            serviceCallback.onSuccess(disasters);
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
                    volleyError.printStackTrace();
                    serviceCallback.onFailure(new V4UException(volleyError.getMessage()));
                }
            });

            int socketTimeout = 50000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);

            volleyRequestQueue.addToRequestQueue(jsonObjectRequest,mContext);
        } catch (Exception e){
            Log.e(V4UApplication.TAG, e.getMessage());
            V4UException exception = new V4UException(e.getMessage());
            serviceCallback.onFailure(exception);
        }
    }

}

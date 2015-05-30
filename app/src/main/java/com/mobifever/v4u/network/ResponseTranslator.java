package com.mobifever.v4u.network;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mobifever.v4u.model.Disaster;
import com.mobifever.v4u.network.dto.CasualityDTO;

public class ResponseTranslator {
    private static ResponseTranslator sharedInstance;
    private Gson plainGsonInstance;
    private Gson gson;


    private ResponseTranslator() {
        if (plainGsonInstance == null) {
            plainGsonInstance = new GsonBuilder()
                    .create();
        }
        if (gson == null) {
            gson = new GsonBuilder()
                    .create();
        }
    }

    public static synchronized ResponseTranslator getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ResponseTranslator();
        }
        return sharedInstance;
    }

  
    public Notification getNotification(JsonObject jsonObject) {
        return gson.fromJson(jsonObject.getAsJsonObject(ResponseKeys.NOTIFICATION), Notification.class);
    }

    public void handleNotification(Notification notification) {
        // TODO Notification Handler
    }

    public void translateDataResponse(JSONObject jsonObj) throws JSONException {
    	//UserDTO userDTO = null;
        if(jsonObj != null) {
        //	userDTO = gson.fromJson(jsonObj.getJSONObject(ResponseKeys.DATA).toString(), UserDTO.class);
        } else {
        //    BOTContext.getInstance().ERROR_MESSAGE="Error";
        }
       // return userDTO;
    }
 
    
    public List<Disaster> translateDisasterList(JSONObject jsonObj) throws JSONException{
        List<Disaster> disasterList = null;
        if(jsonObj != null) {

            Type t = new TypeToken<List<Disaster>>(){}.getType();

            disasterList = gson.fromJson(jsonObj.getJSONArray(ResponseKeys.DATA).toString(), t);

        }
        return disasterList;
    }

    public List<CasualityDTO> translateCasualityList(JSONObject jsonObj) throws JSONException{
        List<CasualityDTO> casualityList = null;
        if(jsonObj != null) {

            Type t = new TypeToken<List<CasualityDTO>>(){}.getType();

            casualityList = gson.fromJson(jsonObj.getJSONArray(ResponseKeys.DATA).toString(), t);

        }
        return casualityList;
    }

    public Boolean translateStatusResponse(JSONObject jsonObj) throws JSONException {
    	 Boolean isReset = false;
         boolean status = (Boolean)jsonObj.get(ResponseKeys.STATUS);
         return status;
    }


    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public interface ResponseKeys {
        public static final String DATA = "data";
        public static final String NOTIFICATION = "notification";
        public static final String STATUS = "status";
       
    }
}


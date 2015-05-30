package com.mobifever.v4u.helper;

import android.content.Context;

import com.mobifever.v4u.model.Disaster;
import com.snappydb.DB;
import com.snappydb.DBFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class SnappyDBHelper {

    DB snappyDb;
    Context context;

    public SnappyDBHelper(Context context){
        this.context = context;
    }


    public void saveDisasterInfo(List<Disaster> disasters){
        try{
            snappyDb = DBFactory.open(context);
            snappyDb.put("disasters",disasters.toArray());
            snappyDb.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Disaster> getAllDisasters(){
        Disaster[] disasterArray = null;
        try{
            snappyDb = DBFactory.open(context);
            disasterArray = snappyDb.getObjectArray("disasters", Disaster.class);
            snappyDb.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(disasterArray!=null)
            return Arrays.asList(disasterArray);
        else
            return null;
    }

}

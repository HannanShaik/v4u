package com.mobifever.v4u.misc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Check device's network connectivity and speed 
 * @author emil http://stackoverflow.com/users/220710/emil
 * 
 */
public class Connectivity {
    private static final String TYPE_WIFI = "TYPE_WIFI";
    private static final String NETWORK_TYPE_1xRTT = "NETWORK_TYPE_1xRTT";
    private static final String NETWORK_TYPE_CDMA = "NETWORK_TYPE_CDMA";
    private static final String NETWORK_TYPE_EDGE = "NETWORK_TYPE_EDGE";
    private static final String NETWORK_TYPE_EVDO_0 = "NETWORK_TYPE_EVDO_0";
    private static final String NETWORK_TYPE_EVDO_A = "NETWORK_TYPE_EVDO_A";
    private static final String NETWORK_TYPE_GPRS = "NETWORK_TYPE_GPRS";
    private static final String NETWORK_TYPE_HSDPA = "NETWORK_TYPE_HSDPA";
    private static final String NETWORK_TYPE_HSPA = "NETWORK_TYPE_HSPA";
    private static final String NETWORK_TYPE_HSUPA = "NETWORK_TYPE_HSUPA";
    private static final String NETWORK_TYPE_UMTS = "NETWORK_TYPE_UMTS";
    private static final String NETWORK_TYPE_EHRPD = "NETWORK_TYPE_EHRPD"; // API level 11
    private static final String NETWORK_TYPE_EVDO_B = "NETWORK_TYPE_EVDO_B"; // API level 9
    private static final String NETWORK_TYPE_HSPAP = "NETWORK_TYPE_HSPAP"; // API level 13
    private static final String NETWORK_TYPE_IDEN = "NETWORK_TYPE_IDEN"; // API level 8
    private static final String NETWORK_TYPE_LTE = "NETWORK_TYPE_LTE"; // API level 11

    /**
     * Get the network info
     * @param context
     * @return
     */
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     * @param context
     * @return
     */
    public static boolean isConnected(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    /**
     * Check if there is any connectivity to a Wifi network
     * @param context
     * @param type
     * @return
     */
    public static boolean isConnectedWifi(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     * @param context
     * @param type
     * @return
     */
    public static boolean isConnectedMobile(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * Check if there is fast connectivity
     * @param context
     * @return
     */
    public static boolean isConnectedFast(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && Connectivity.isConnectionFast(info.getType(),info.getSubtype()));
    }

    /**
     * Check if the connection is fast
     * @param type
     * @param subType
     * @return
     */
    public static String getConnectionType(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        int type = info.getType();
        int subType = info.getSubtype();
        if(type== ConnectivityManager.TYPE_WIFI){
            return TYPE_WIFI;
        }else if(type== ConnectivityManager.TYPE_MOBILE){
            switch(subType){
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return NETWORK_TYPE_1xRTT; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return NETWORK_TYPE_CDMA; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return NETWORK_TYPE_EDGE; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return NETWORK_TYPE_EVDO_0; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return NETWORK_TYPE_EVDO_A; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return NETWORK_TYPE_GPRS; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return NETWORK_TYPE_HSDPA; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return NETWORK_TYPE_HSPA; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return NETWORK_TYPE_HSUPA; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return NETWORK_TYPE_UMTS; // ~ 400-7000 kbps
			/*
			 * Above API level 7, make sure to set android:targetSdkVersion 
			 * to appropriate level to use these
			 */
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                    return NETWORK_TYPE_EHRPD; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                    return NETWORK_TYPE_EVDO_B; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    return NETWORK_TYPE_HSPAP; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    return NETWORK_TYPE_IDEN; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    return NETWORK_TYPE_LTE; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return "NETWORK_TYPE_UNKNOWN";
            }
        }else{
            return "NETWORK_TYPE_UNKNOWN";
        }
    }

    /**
     * Check if the connection is fast
     * @param type
     * @param subType
     * @return
     */
    public static boolean isConnectionFast(int type, int subType){
        if(type== ConnectivityManager.TYPE_WIFI){
            return true;
        }else if(type== ConnectivityManager.TYPE_MOBILE){
            switch(subType){
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
			/*
			 * Above API level 7, make sure to set android:targetSdkVersion
			 * to appropriate level to use these
			 */
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                    return true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                    return true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    return true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    return false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    return true; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        }else{
            return false;
        }
    }

}
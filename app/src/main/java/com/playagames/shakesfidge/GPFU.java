package com.playagames.shakesfidge;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.onesignal.OneSignal;
import com.orhanobut.hawk.Hawk;

import java.util.Map;
import java.util.StringTokenizer;

public class GPFU extends Application {
    private static final String AF_DEV_KEY = "urdk7dqhTXzxgBdBWtoEL4";
    public static final String GLOBAL_ID = "globalId";
    private static final String ONESIGNAL_APP_ID = "9a4e1245-a1c0-4641-bcfd-1e72a4e94c3d";

    public static final String CAM_1 = "camp1";

    public static final String CAM_2 = "camp2";

    public static final String CAM_3 = "camp3";

    public static final String CAM_4 = "camp4";

    public static final String CAM_5 = "camp5";
    String infoString;
    @Override
    public void onCreate() {
        super.onCreate();


        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);


        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        Hawk.init(this).build();

        afGlobalis();



        lightAsync.execute();




    }

    @SuppressLint("StaticFieldLeak")
    AsyncTask<Void, Void, String> lightAsync = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
            AdvertisingIdClient.Info idInfo = null;
            try {
                idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String advertId = null;
            try {


                advertId = idInfo.getId();

                Hawk.put(GLOBAL_ID, advertId);



            } catch (Exception e) {
                e.printStackTrace();
            }
            return advertId;
        }

        @Override
        protected void onPostExecute(String advertId) {


        }
    };


    public void afGlobalis() {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {



            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {



                Log.d("TESTING", "af stat is " + conversionData.get("af_status"));

                infoString = (String) conversionData.get("campaign");





                StringTokenizer tokenizer = new StringTokenizer(infoString, "_");


                String one = tokenizer.nextToken();
                String two = tokenizer.nextToken();
                String three = tokenizer.nextToken();
                String four = tokenizer.nextToken();
                String five = tokenizer.nextToken();



                Hawk.put(CAM_1, one);
                Hawk.put(CAM_2, two);
                Hawk.put(CAM_3, three);
                Hawk.put(CAM_4, four);
                Hawk.put(CAM_5, five);



            }




            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);




            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }

        };


        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);


    }
}
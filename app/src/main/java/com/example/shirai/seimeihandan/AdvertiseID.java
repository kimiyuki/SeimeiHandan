package com.example.shirai.seimeihandan;

/**
 * Created by shirai on 2015/08/09.
 */

import java.io.IOException;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AdvertiseID extends AsyncTask<Void, Void, String> {
    public static GoogleAnalytics analytics;
    public static Tracker tracker;
    private final Context context;
    private String strAdId;

    public AdvertiseID(Context context) {
        Log.d("contructor", context.getApplicationInfo().toString());
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d("doInBack", "start");
        String strAdId = "";
        try {
            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(this.context);
            if (info.isLimitAdTrackingEnabled() == false) {
                strAdId = info.getId();
            } else {
                strAdId = "Limit Ad Tracking is Enabled.";
            }
        } catch (IllegalStateException e) {
            strAdId = "IllegalStateException";
        } catch (GooglePlayServicesRepairableException e) {
            strAdId = "GooglePlayServicesRepairableException";
        } catch (IOException e) {
            strAdId = "IOException";
        } catch (GooglePlayServicesNotAvailableException e) {
            strAdId = "GooglePlayServicesNotAvailableException";
        }
        return strAdId;
    }

    @Override
    protected void onPostExecute(String advertisingID) {
        Log.d("MyTag", "onPostExecute " + advertisingID);
        if (advertisingID != null) {
            //boolean limitAdTrackingEnabled = info.isLimitAdTrackingEnabled();
            analytics = GoogleAnalytics.getInstance(this.context);
            analytics.setLocalDispatchPeriod(10);
            tracker = analytics.newTracker("UA-188512-42");
            tracker.setScreenName("with advertiseId");
            tracker.set("&cd1",advertisingID);
            tracker.enableExceptionReporting(true);
            tracker.enableAdvertisingIdCollection(true);
            tracker.enableAutoActivityTracking(true);
        }
    }
}
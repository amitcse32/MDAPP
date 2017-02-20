package com.amit.myapplication.utils.customcontrols.dialogs.connectionutils; import android.app.Activity; import android.app.usage.NetworkStatsManager; import android.content.Context; import android.net.ConnectivityManager; import android.net.NetworkInfo; import android.telecom.ConnectionService; /** Created by Mobile on 2/20/17. */ public class ConnectionUtils { /*Check is internet connection live or not*/ public boolean checkInternetConnection(Activity activity) { ConnectivityManager connectionService=(ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE); NetworkInfo networkInfo=connectionService.getActiveNetworkInfo(); boolean isNetworkConnected=false; try { if (networkInfo.isConnected()==true) isNetworkConnected=true; else
    isNetworkConnected=false;
        }
        catch (NullPointerException e)
        {
            isNetworkConnected=false;
        }


        try
        {
            if(networkInfo.isConnectedOrConnecting()==true)
            {
                isNetworkConnected=true;
            }
            else
            {
                isNetworkConnected=false;
            }
        }
        catch (NullPointerException e)
        {
            isNetworkConnected=false;
        }




        return isNetworkConnected;



    }


}

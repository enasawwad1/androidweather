package com.ii.androidweather;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NewActivity extends Activity implements LocationListener{
    private LocationManager myManager;
    private TextView tv;


    /********************************************************************** 
     * Activity overrides below 
     **********************************************************************/
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now);
    //    Log.v("Statc",MyLocationActivity.state);

        // get a handle to our TextView so we can write to it later
        tv = (TextView) findViewById(R.id.myLocation);

        // set up the LocationManager
        myManager = (LocationManager) getSystemService(LOCATION_SERVICE);       
    }

    @Override
    protected void onDestroy() {
        stopListening();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        stopListening();
        super.onPause();
    }

    @Override
    protected void onResume() {
        startListening();
        super.onResume();
    }



    /**********************************************************************
     * helpers for starting/stopping monitoring of GPS changes below 
     **********************************************************************/
    private void startListening() {
        myManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 
                0, 
                0, 
                this
        );
    }

    private void stopListening() {
        if (myManager != null)
                myManager.removeUpdates(this);
    }




    /**********************************************************************
     * LocationListener overrides below 
     **********************************************************************/
    @Override
    public void onLocationChanged(Location location) {
        // we got new location info. lets display it in the textview
        String s = "";
        s += "Time: "        + location.getTime() + "\n";
        s += "\tLatitude:  " + location.getLatitude()  + "\n";
        s += "\tLongitude: " + location.getLongitude() + "\n";
        s += "\tAccuracy:  " + location.getAccuracy()  + "\n";
        Log.v("location", s);
        tv.setText(s);
        Log.v("Statc",MyLocationActivity.state);
        this.onDestroy();
        
    }    
 

    @Override
    public void onProviderDisabled(String provider) {}    

    @Override
    public void onProviderEnabled(String provider) {}    

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}

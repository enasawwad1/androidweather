package com.ii.androidweather;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends Activity implements LocationListener {
	public static int activate = 0;
	private LocationManager myManager;
	private TextView tv;
	  URL imgUrl;
		URLConnection conn;
		InputStream is;
		BufferedInputStream bis;  
		Bitmap bm;
		ImageView myImage;

    
    public void setView(){
    	if(GoogleWeatherHandler.getInstance().getWeatherSet()!=null)
    	{
   
    	WeatherCurrentConditionVO currentWCondition=GoogleWeatherHandler.getInstance().getWeatherSet().getWeatherCurrentCondition();
    	WeatherForecastInformationVO currentFInfo=GoogleWeatherHandler.getInstance().getWeatherSet().getMyForecastInfromation();
    	myImage=(ImageView)findViewById(R.id.currState);
    	tv=(TextView)findViewById(R.id.currUpdate);
        tv.setText(currentFInfo.getCurrent_date_time().split(" ")[1]);
        tv=(TextView)findViewById(R.id.currLocation);
        tv.setText(GoogleWeatherHandler.getInstance().getWeatherSet().getMyForecastInfromation().getCity());
        tv=(TextView)findViewById(R.id.currTemp);
       if(AndroidWeatherTab.chk_useSIformat)
       {  tv.setText(Integer.toString(currentWCondition.getTempCelcius())+" ° C");
       }else 
       {
    	   tv.setText(Integer.toString(currentWCondition.getTempFahrenheit())+" ° F");
     
       }
       tv=(TextView)findViewById(R.id.currCondition);
       tv.setText(currentWCondition.getCondition());
       
       tv=(TextView)findViewById(R.id.currHumidity);
       tv.setText(currentWCondition.getHumidity());
       
       tv=(TextView)findViewById(R.id.currWind);
       tv.setText(currentWCondition.getWindCondition());
   	try 
    {
		imgUrl = new URL("http://www.google.com"+currentWCondition.getIconURL());

		conn=imgUrl.openConnection();
    conn.connect();
    is=conn.getInputStream();
    bis=new BufferedInputStream(is);
    bm=BitmapFactory.decodeStream(bis);
    bis.close();
    is.close();
    myImage.setImageBitmap(bm);
    }catch (IOException e)
    {
    }
          
    	} 	
    }
	/**********************************************************************
	 * Activity overrides below
	 **********************************************************************/

	public void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.now);
		myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		//tv=(TextView)findViewById(R.id.currLocation);
		setView();
		
	
		
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		stopListening();
		super.onPause();
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		
	/*	 if(  MyLocationActivity.state==1)
	       {
			Log.v("resume", Integer.toString(MyLocationActivity.state));
			setView ();
		  
			 
	       }	
		 MyLocationActivity.state=0;
	
*/if(activate==1){
	startListening();
activate=0;
}
		setView ();

	}

	/**********************************************************************
	 * helpers for starting/stopping monitoring of GPS changes below
	 **********************************************************************/
	private void startListening() {
		myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);
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
		s += "Time: " + location.getTime() + "\n";
		s += "\tLatitude:  " + location.getLatitude() + "\n";
		s += "\tLongitude: " + location.getLongitude() + "\n";
		s += "\tAccuracy:  " + location.getAccuracy() + "\n";
		Log.v("location", s);
		AndroidWeatherTab.updateWeather(location.getLongitude(),location.getLatitude());
		//Log.v("location", s);
		setView();
		//tv.setText(s);
		 Log.v("Statc",s);
		stopListening();

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.sysUI:    { 
	        	AndroidWeatherTab.chk_useSIformat=!AndroidWeatherTab.chk_useSIformat;
	        	this.setView();
	        	if(AndroidWeatherTab.chk_useSIformat)
	        		
	        	Toast.makeText(this, "The format is SI!", Toast.LENGTH_LONG).show();
	        else 
	         	Toast.makeText(this, "The format is not SI!", Toast.LENGTH_LONG).show();
	        	
	        break;
	         }
	        
	        case R.id.findMe:    {
	        	startListening();
	        	
	        }
	        }
	    
	    return true;
	}
	@Override
	
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}

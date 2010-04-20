package com.ii.androidweather;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidWeatherActivity extends Activity {
    public static final String SERVICE_ENDPOINT="http://api.wunderground." +
    		"com/auto/wui/geo/GeoLookupXML/index.xml?query=";
	//metoda koja sluzi za prakanje na query do web servisot 
    public void queryWS(String query)
    {
    HttpClient httpClient = new DefaultHttpClient();
	HttpGet request = new HttpGet(SERVICE_ENDPOINT+query);
	HttpResponse response;
	try {
		response = httpClient.execute(request);
		int status = response.getStatusLine().getStatusCode();
		// vo slucaj da ima poraka na greska
		if (status != HttpStatus.SC_OK) {
		 ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		 response.getEntity().writeTo(ostream);
		 Log.e("HTTP CLIENT", ostream.toString());
		}
		else {
		 InputStream content = response.getEntity().getContent();
		 // <ovde treba da se spravi so odgovorot>
		 content.close(); //zatvoranje na konekcijata
		}
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	

    }	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
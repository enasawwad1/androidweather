package com.ii.androidweather;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.serialization.SoapObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AndroidWeatherActivity extends Activity {
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
        /* Create a new TextView to display the parsingresult later. */ 
        
        try { 
             /* Create a URL we want to load some xml-data from. */ 
        	 URL url = new URL(WeatherUtils.url); 

             /* Get a SAXParser from the SAXPArserFactory. */ 
             SAXParserFactory spf = SAXParserFactory.newInstance(); 
             SAXParser sp = spf.newSAXParser(); 

             /* Get the XMLReader of the SAXParser we created. */ 
             XMLReader xr = sp.getXMLReader(); 
             /* Create a new ContentHandler and apply it to the XML-Reader*/ 
             GoogleWeatherHandler WeatherHandler = new GoogleWeatherHandler(); 
             xr.setContentHandler(WeatherHandler); 
              
             /* Parse the xml-data from our URL. */ 
             xr.parse(new InputSource(url.openStream())); 
             /* Parsing has finished. */ 

             /* Our ExampleHandler now provides the parsed data to us. */ 
             WeatherSet parsedExampleDataSet = 
                                           WeatherHandler.getWeatherSet(); 
             TextView sostojbaFld=(TextView)findViewById(R.id.state);
             sostojbaFld.setText(parsedExampleDataSet.getWeatherCurrentCondition().getCondition());
             TextView temFld=(TextView)findViewById(R.id.tempText);
            temFld.setText(parsedExampleDataSet.getWeatherCurrentCondition().getTempCelcius().toString());
             /* Set the result to be displayed in our GUI. */ 
            // tv.setText(parsedExampleDataSet.getWeatherCurrentCondition().getCondition()); 
              
        } catch (Exception e) { 
             /* Display any Error to the GUI. */ 
            // tv.setText("Error: " + e.getMessage()); 
            
        } 
        /* Display the TextView. */ 
      //  this.setContentView(tv); 
   } 
	   
    public void queryWS(String query)
    {
    HttpClient httpClient = new DefaultHttpClient();
	HttpGet request = new HttpGet(WeatherUtils.SERVICE_ENDPOINT+query);
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
    
}
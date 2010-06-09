package com.ii.androidweather;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;

public class AndroidWeatherTab extends TabActivity {

	static TabHost tabHost;
	private static final String SOAP_ACTION = "http://tempuri.org/GetWeather";
	private static final String METHOD_NAME = "GetWeather";
	private static final String SOAP_ACTION2 = "http://tempuri.org/getStringWeather";
	private static final String METHOD_NAME2 = "getStringWeather";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://student.labs.ii.edu.mk/ii11532/Service.asmx";
	public static boolean chk_useSIformat=true;
	// metoda koja sluzi za prakanje na query do web servisot
	/**
	 * @param location
	 */
	
	public static void updateWeather(double lon, double lat)
	{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
		Log.v("LATLONG", Double.toString(lat)+" "+Double.toString(lon));
		request.addProperty("lat",Double.toString(lat));
		request.addProperty("longi",Double.toString(lon));
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	 envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			
			sp = spf.newSAXParser();
			
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(GoogleWeatherHandler.getInstance());
				androidHttpTransport.call(SOAP_ACTION2, envelope);
				
				SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
				InputSource t = new InputSource(new StringReader(response.toString()));
				xr.parse(t);
		}
		//	Log.v("result",GoogleWeatherHandler.getInstance().getWeatherSet().getWeatherCurrentCondition().getCondition() );
				catch (IllegalArgumentException e) {
					//tvError.setText("Error in soap arg: " + e);
				} catch (InterruptedIOException e) {
					//tvError.setText("Error in soap interupt : " + e);
				} catch (IOException e) {
					//tvError.setText("Error in soap io: " + e);
				} catch (XmlPullParserException e) {
					//tvError.setText("Error in soap xml: " + e);

				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	public static void updateWeather(String location) {
	try{
		//Log.v("City", location); da se dopraj na .net strana
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		
		request.addProperty("City",location);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	 envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			
			sp = spf.newSAXParser();
			
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(GoogleWeatherHandler.getInstance());
				androidHttpTransport.call(SOAP_ACTION, envelope);
				
				SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
				InputSource t = new InputSource(new StringReader(response.toString()));
				xr.parse(t);
		}
		//	Log.v("result",GoogleWeatherHandler.getInstance().getWeatherSet().getWeatherCurrentCondition().getCondition() );
				catch (IllegalArgumentException e) {
					//tvError.setText("Error in soap arg: " + e);
				} catch (InterruptedIOException e) {
					//tvError.setText("Error in soap interupt : " + e);
				} catch (IOException e) {
					//tvError.setText("Error in soap io: " + e);
				} catch (XmlPullParserException e) {
					//tvError.setText("Error in soap xml: " + e);

				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	AndroidWeatherTab.switchTab("now");
        //  Our ExampleHandler now provides the parsed data to us.  
        
          
    } catch (Exception e) { 
        //  Display any Error to the GUI.  
        // tv.setText("Error: " + e.getMessage()); 
        
    } 
		

		/*// Create a URL we want to load some xml-data from. 
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	//	 Get a SAXParser from the SAXPArserFactory. 
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		try {
			sp = spf.newSAXParser();
		
	//	 Get the XMLReader of the SAXParser we created. 
		XMLReader xr = sp.getXMLReader();
	//	 Create a new ContentHandler and apply it to the XML-Reader 
		GoogleWeatherHandler WeatherHandler = new GoogleWeatherHandler();
		xr.setContentHandler(WeatherHandler);
		int a = 10;
		 //request.addProperty("Celsius");
		// request.addProperty("b", b);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
			String sd = result.toString();
		
			xr.parse(new InputSource(new ByteArrayInputStream(sd.getBytes())));
			WeatherSet parsedExampleDataSet = WeatherHandler.getWeatherSet();
			Log.v("result", sd);

			// tvError.setText(sd);
		} catch (IllegalArgumentException e) {
			// tvError.setText("Error in soap arg: " + e);
		} catch (InterruptedIOException e) {
			// tvError.setText("Error in soap interupt : " + e);
		} catch (IOException e) {
			// tvError.setText("Error in soap io: " + e);
		} catch (XmlPullParserException e) {
			// tvError.setText("Error in soap xml: " + e);
			// ((TextView)findViewById(R.id.lblStatus)).setText("ERROR:" +
			// E.getClass().getName() + ": " + E.getMessage());

		}
	 catch (ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SAXException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
*/
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pocetno);

		//updateWeather(-122.084095,37.422006);
		
		Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, NewActivity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("now").setIndicator("Now",
				res.getDrawable(R.drawable.ic_tab_new)).setContent(intent);
		tabHost.addTab(spec);

		// sDo the same for the other tabs
		intent = new Intent().setClass(this, Broadt3DaysActivity.class);
		spec = tabHost.newTabSpec("3day").setIndicator("3 Days",
				res.getDrawable(R.drawable.ic_tab_new)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, MyLocationActivity.class);
		spec = tabHost.newTabSpec("mylocations").setIndicator("My Locations",
				res.getDrawable(R.drawable.ic_tab_new)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTabByTag("now");

	}

	public static void switchTab(String tabName) {

		tabHost.setCurrentTabByTag(tabName);

	}
}

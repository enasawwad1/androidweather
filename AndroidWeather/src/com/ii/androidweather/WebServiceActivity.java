package com.ii.androidweather;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.StringReader;
import java.net.MalformedURLException;
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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class WebServiceActivity extends Activity {
	private static final String SOAP_ACTION = "http://tempuri.org/getString";
	private static final String METHOD_NAME = "getString";
	private static final String SOAP_ACTION1 = "http://tempuri.org/getStringWeather";
	private static final String METHOD_NAME1 = "getStringWeather";
	
	private static final String NAMESPACE = "http://tempuri.org/";
	//private static final String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
	private static final String URL = "http://student.labs.ii.edu.mk/ii11532/Service.asmx";
	TextView tvError;

	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.now);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		
		tvError = (TextView) findViewById(R.id.currLocation);
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
		String a = "Skopje";
		request.addProperty("lat","42");
		request.addProperty("longi","21");
		
		
		//request.addProperty("b", b);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	 envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			
			sp = spf.newSAXParser();
			
			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			GoogleWeatherHandler WeatherHandler = new GoogleWeatherHandler();
			xr.setContentHandler(GoogleWeatherHandler.getInstance());
				androidHttpTransport.call(SOAP_ACTION1, envelope);
				
				SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
				InputSource t = new InputSource(new StringReader(response.toString()));
				xr.parse(t);
				Log.v("result",GoogleWeatherHandler.getInstance().getWeatherSet().getWeatherCurrentCondition().getCondition() );
				
				tvError.setText(GoogleWeatherHandler.getInstance().getWeatherSet().getMyForecastInfromation().getCity().split(" ")[0]);
				tvError =(TextView)findViewById(R.id.currUpdate);
				tvError.setText(GoogleWeatherHandler.getInstance().getWeatherSet().getMyForecastInfromation().getCurrent_date_time().split(" ")[1]);
				//SoapObject result = (SoapObject)envelope.getResponse();
				 //Log.v("result", test.getProperty(0) );
				//MyLocationActivity test = new MyLocationActivity();
				//test.WriteSettings(this, result.toString());
				//Log.v("result",Integer.toString(result.getPropertyCount()));
				//Log.v("result",androidHttpTransport.responseDump);
				//URL url = new URL(WeatherUtils.url+"Skopje");
				//String sd=result.toString();
		//Log.v("result",url.openStream().toString());
	    //InputSource t = new InputSource(new StringReader(test.toString()));
	   // Log.v("result",t.toString());
	   
	    //xr.parse(t);
	//	xr.parse(new InputSource(new ByteArrayInputStream(sd.getBytes())));
		//WeatherSet parsedExampleDataSet =  WeatherHandler.getWeatherSet(); 
	//	tvError.setText(parsedExampleDataSet.getWeatherCurrentCondition().getTempCelcius().toString());
    
		//Log.v("result", parsedExampleDataSet.getWeatherCurrentCondition().getTempCelcius().toString());
		
		//tvError.setText(sd);
	
		
		}

		catch (IllegalArgumentException e) {
			tvError.setText("Error in soap arg: " + e);
		} catch (InterruptedIOException e) {
			tvError.setText("Error in soap interupt : " + e);
		} catch (IOException e) {
			tvError.setText("Error in soap io: " + e);
		} catch (XmlPullParserException e) {
			tvError.setText("Error in soap xml: " + e);
			// ((TextView)findViewById(R.id.lblStatus)).setText("ERROR:" +
			// E.getClass().getName() + ": " + E.getMessage());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

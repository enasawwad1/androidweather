package com.ii.androidweather;

import java.io.IOException;
import java.io.InterruptedIOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
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
	private static final String NAMESPACE = "http://tempuri.org/";
	//private static final String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
	private static final String URL = "http://student.labs.ii.edu.mk/ii11532/Service.asmx";
	TextView tvError;

	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.now);
		tvError = (TextView) findViewById(R.id.myLocation);
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		int a = 10;
		//request.addProperty("Celsius",32);
		//request.addProperty("b", b);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	 envelope.dotNet=true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
				androidHttpTransport.call(SOAP_ACTION, envelope);
				SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
		String sd=result.toString();
		Log.v("result", sd);
	
		tvError.setText(sd);
	
		
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

		}

	}

}

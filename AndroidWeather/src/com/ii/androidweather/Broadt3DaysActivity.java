package com.ii.androidweather;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class Broadt3DaysActivity extends Activity{
	
		   public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		   
		        setContentView(R.layout.pocetno);
		       
		        try { 
		             /* Create a URL we want to load some xml-data from. */ 
		        	/* URL url = new URL(WeatherUtils.url); 

		              Get a SAXParser from the SAXPArserFactory.  
		             SAXParserFactory spf = SAXParserFactory.newInstance(); 
		             SAXParser sp = spf.newSAXParser(); 

		              Get the XMLReader of the SAXParser we created.  
		             XMLReader xr = sp.getXMLReader(); 
		              Create a new ContentHandler and apply it to the XML-Reader 
		             GoogleWeatherHandler WeatherHandler = new GoogleWeatherHandler(); 
		             xr.setContentHandler(WeatherHandler); 
		              
		              Parse the xml-data from our URL.  
		             xr.parse(new InputSource(url.openStream())); 
		              Parsing has finished.  

		              Our ExampleHandler now provides the parsed data to us.  
		             WeatherSet parsedExampleDataSet = WeatherHandler.getWeatherSet(); 
		             
		             //TextView sostojbaFld=(TextView)findViewById(R.id.state);
		             //sostojbaFld.setText(parsedExampleDataSet.getWeatherCurrentCondition().getCondition());
		             
		             //TextView temFld=(TextView)findViewById(R.id.tempText);
		            // temFld.setText(parsedExampleDataSet.getWeatherCurrentCondition().getTempCelcius().toString());
		             
		             //TextView humidity=(TextView)findViewById(R.id.TextView01);
		             //humidity.setText(parsedExampleDataSet.getWeatherCurrentCondition().getHumidity());
		             
		             TextView location=(TextView)findViewById(R.id.TextView01);
		             location.setText(parsedExampleDataSet.getWeatherCurrentCondition().getCondition());
		             
		             TextView max=(TextView)findViewById(R.id.TextView03);
		             max.setText(parsedExampleDataSet.getLastWeatherForecastCondition().getTempMaxCelsius());
		             
		             TextView min=(TextView)findViewById(R.id.TextView04);
		             min.setText(parsedExampleDataSet.getLastWeatherForecastCondition().getTempMinCelsius());
		             */
		        	Log.v("Vlez","vlegv na majka ti");
		             URL y = new URL("http://google.com");
		             
		             URLConnection conn = y.openConnection();
		 			 conn.connect();
		 			Log.v("connect","se konektirav");
		 			InputStream is = conn.getInputStream();
		 			BufferedInputStream bis = new BufferedInputStream(is);
		 			Bitmap bm = BitmapFactory.decodeStream(bis);
		 			Log.v("connect1","dekodirav vo slika");
		 			bis.close();
		 			is.close();
		            Log.v("imgData", bm.toString());
		            ImageView v=(ImageView)findViewById(R.id.slikce1);
		            v.setImageBitmap(bm);
		             
		        } catch (Exception e) { 
		             /* Display any Error to the GUI. */ 
		            // tv.setText("Error: " + e.getMessage()); 
		            
		        } 
		        /* Display the TextView. */ 
		      //  this.setContentView(tv); 
		   } 
		   private Drawable ImageOperations(String url, String saveFilename) {
				try {
					InputStream is = (InputStream) this.fetch(url);
					Drawable d = Drawable.createFromStream(is, "src");
					return d;
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}

		   public Object fetch(String address) throws MalformedURLException,IOException {
				URL url = new URL(address);
				Object content = url.getContent();
				return content;
			}

	}

package com.ii.androidweather;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class Broadt3DaysActivity extends Activity{
		   public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);

		      //  TextView textview = new TextView(this);
		     //   textview.setText("3 Days Tab");
		        setContentView(R.layout.days);
		        //setContentView(R.layout.main);
		        //setContentView(R.layout.pocetno);
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
		              
		              
		        } catch (Exception e) { 
		             /* Display any Error to the GUI. */ 
		            // tv.setText("Error: " + e.getMessage()); 
		            
		        } 
		        /* Display the TextView. */ 
		      //  this.setContentView(tv); 
		   } 
		    

	}

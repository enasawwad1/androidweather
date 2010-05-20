package com.ii.androidweather;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class AndroidWeatherTab extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.pocetno);
	    
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
	    
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost(); // The activity TabHost
   TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, NewActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("now").setIndicator("Now",
	                      res.getDrawable(R.drawable.ic_tab_new))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // sDo the same for the other tabs
	    intent = new Intent().setClass(this, Broadt3DaysActivity.class);
	    spec = tabHost.newTabSpec("3day").setIndicator("3 Days",
	                      res.getDrawable(R.drawable.ic_tab_new))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, MyLocationActivity.class);
	    spec = tabHost.newTabSpec("mylocations").setIndicator("My Locations",
	                      res.getDrawable(R.drawable.ic_tab_new))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTabByTag("now");

	}
}

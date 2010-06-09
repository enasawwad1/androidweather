package com.ii.androidweather;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Broadt3DaysActivity extends Activity{
	  TextView tv;
	  
	  ImageView myImage;
	  URL imgUrl;
		URLConnection conn;
		InputStream is;
		BufferedInputStream bis;  
		Bitmap bm;
		public void setView (){
			ArrayList<WeatherForecastConditionVO> lista=null;
		  
			// int size=GoogleWeatherHandler.getInstance().getWeatherSet().getWeatherForecastConditions().size();
			if(GoogleWeatherHandler.getInstance().getWeatherSet()!=null)
			{
				Log.v("viewday", Integer.toString(MyLocationActivity.state));
				
				lista=GoogleWeatherHandler.getInstance().getWeatherSet().getWeatherForecastConditions();
				
				tv=(TextView)findViewById(R.id.status);
				 tv.setText("");   
				
				 myImage=(ImageView)findViewById(R.id.slikce1);
			
			
			try 
		    {
				imgUrl = new URL("http://www.google.com"+lista.get(1).getIconURL());
		
				conn=imgUrl.openConnection();
		    conn.connect();
		    is=conn.getInputStream();
		    bis=new BufferedInputStream(is);
		    bm=BitmapFactory.decodeStream(bis);
		    bis.close();
		    is.close();
		    myImage.setImageBitmap(bm);
		    Log.v("kate2", "uspeav");
		    
		    //slikce2
		    myImage=(ImageView)findViewById(R.id.slikce2);
		    imgUrl = new URL("http://www.google.com"+ lista.get(2).getIconURL());
		    conn=imgUrl.openConnection();
		    conn.connect();
		    is=conn.getInputStream();
		    bis=new BufferedInputStream(is);
		    bm=BitmapFactory.decodeStream(bis);
		    bis.close();
		    is.close();
		    myImage.setImageBitmap(bm);
		    
		    //slikce 3
		    myImage=(ImageView)findViewById(R.id.slikce3);
		    imgUrl = new URL("http://www.google.com"+lista.get(3).getIconURL() );
		    conn=imgUrl.openConnection();
		    conn.connect();
		    is=conn.getInputStream();
		    bis=new BufferedInputStream(is);
		    bm=BitmapFactory.decodeStream(bis);
		    bis.close();
		    is.close();
		    myImage.setImageBitmap(bm);
		    
		    //
		    
		    }catch (IOException e)
		    {
		    }
		    
		    tv=(TextView)findViewById(R.id.foreLocation);
	        tv.setText(GoogleWeatherHandler.getInstance().getWeatherSet().getMyForecastInfromation().getCity());
	        
	        tv=(TextView)findViewById(R.id.foreUpdate);
	        tv.setText(GoogleWeatherHandler.getInstance().getWeatherSet().getMyForecastInfromation().getCurrent_date_time().split(" ")[1]);
	        
	        tv=(TextView)findViewById(R.id.foreDay1);
	        tv.setText(lista.get(1).getDayofWeek());
	        

	        tv=(TextView)findViewById(R.id.foreDay2);
	        tv.setText(lista.get(2).getDayofWeek());
	        

	        tv=(TextView)findViewById(R.id.foreDay3);
	        tv.setText(lista.get(3).getDayofWeek());
	        
	        if(AndroidWeatherTab.chk_useSIformat)
	        {
	        tv=(TextView)findViewById(R.id.fore1Max);
	        tv.setText(Integer.toString(lista.get(1).getTempMaxCelsius())+" ° C");
	        
	        tv=(TextView)findViewById(R.id.fore2Max);
	        tv.setText(Integer.toString(lista.get(2).getTempMaxCelsius())+" ° C");
	       
	        tv=(TextView)findViewById(R.id.fore3Max);
	        tv.setText(Integer.toString(lista.get(3).getTempMaxCelsius())+" ° C");
	        
	        tv=(TextView)findViewById(R.id.for1Min);
	        tv.setText(Integer.toString(lista.get(1).getTempMinCelsius())+" ° C");
	        
	        tv=(TextView)findViewById(R.id.fore2Min);
	        tv.setText(Integer.toString(lista.get(2).getTempMinCelsius())+" ° C");
	       
	        tv=(TextView)findViewById(R.id.fore3Min);
	        tv.setText(Integer.toString(lista.get(3).getTempMinCelsius())+" ° C");
	        }
	        else
	        {
	        	 tv=(TextView)findViewById(R.id.fore1Max);
	 	        tv.setText(Integer.toString(WeatherUtils.celsiusToFahrenheit(lista.get(1).getTempMaxCelsius()))+" ° F");
	 	        
	 	        tv=(TextView)findViewById(R.id.fore2Max);
	 	        tv.setText(Integer.toString(WeatherUtils.celsiusToFahrenheit(lista.get(2).getTempMaxCelsius()))+" ° F");
	 	       
	 	        tv=(TextView)findViewById(R.id.fore3Max);
	 	        tv.setText(Integer.toString(WeatherUtils.celsiusToFahrenheit(lista.get(3).getTempMaxCelsius()))+" ° F");
	 	        
	 	        tv=(TextView)findViewById(R.id.for1Min);
	 	        tv.setText(Integer.toString(WeatherUtils.celsiusToFahrenheit(lista.get(1).getTempMinCelsius()))+" ° F");
	 	        
	 	        tv=(TextView)findViewById(R.id.fore2Min);
	 	        tv.setText(Integer.toString(WeatherUtils.celsiusToFahrenheit(lista.get(2).getTempMinCelsius()))+" ° F");
	 	       
	 	        tv=(TextView)findViewById(R.id.fore3Min);
	 	        tv.setText(Integer.toString(WeatherUtils.celsiusToFahrenheit(lista.get(3).getTempMinCelsius()))+" ° F");
	 	        
	        }
	      }
			
			else
			{	
				tv=(TextView)findViewById(R.id.status);
				 tv.setText("Unknown:"+ MyLocationActivity.city);   
					
				
			}
			}
		
	  
	  
	  
	  
	  
	  
	  
		   public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		   
		        
		        setContentView(R.layout.days);
		     
		  setView();
		        
		   } 
		  
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
			        NewActivity.activate=1;
			        	AndroidWeatherTab.switchTab("now");
			        	
			        }   
			    }
			    return true;
			}
			
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.setView ();
		 if(  MyLocationActivity.state==1)
	       {
			Log.v("resume", Integer.toString(MyLocationActivity.state));
			this.setView ();

	       }	
		 MyLocationActivity.state=0;
	}
}

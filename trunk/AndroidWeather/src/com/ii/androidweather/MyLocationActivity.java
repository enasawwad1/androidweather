package com.ii.androidweather;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class MyLocationActivity extends ListActivity{
	
	TextView selection;
	  String data[] ;
	public static String state="0";
	
	public void onCreate(Bundle icicle) {
	
		super.onCreate(icicle);
		  WriteSettings(this,"setting0, setting1, setting2");	
		data= ReadSettings(this).split(",");
	setContentView(R.layout.lview);
	setListAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1,
			data));
			selection=(TextView)findViewById(R.id.selection);
			}
			public void onListItemClick(ListView parent, View v, int position,
			long id) {
			selection.setText(data[position]);
			}
	   @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//  AndroidWeatherTab.switchTab("now");
	}

	   public void WriteSettings(Context context, String data){

	  	  FileOutputStream fOut = null;
	       OutputStreamWriter osw = null;

	       try{

	        fOut = openFileOutput("settings.dat",MODE_WORLD_WRITEABLE );      
	        osw = new OutputStreamWriter(fOut);
	        osw.write(data);
	        osw.flush();
	        
	        Toast.makeText(context, "Settings saved",Toast.LENGTH_SHORT).show();

	        }
	       
	      catch (Exception e) {      

	        e.printStackTrace();

	       Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show();
	        }
	      
	        finally {

	           try {

	                  osw.close();
	                  fOut.close();
	               } 
	           catch (IOException e) {
	                 	 
	                  e.printStackTrace();

	                 }

	        }
	   }


	  //Read settings 
	  public String ReadSettings(Context context){ 
	  	 FileInputStream fIn = null; 
	  	 InputStreamReader isr = null;
	  	 
	  	 char[] inputBuffer = new char[255]; 
	  	 String data = null;
	  	 
	  	 try{
	  	  fIn = openFileInput("settings.dat");       
	      isr = new InputStreamReader(fIn); 
	      isr.read(inputBuffer); 
	      data = new String(inputBuffer);
	      Toast.makeText(context, "Settings read",Toast.LENGTH_SHORT).show();
	      } 
	      catch (Exception e) {       
	      e.printStackTrace(); 
	      Toast.makeText(context, "Settings not read",Toast.LENGTH_SHORT).show();
	      } 
	      finally { 
	         try { 
	                isr.close(); 
	                fIn.close(); 
	                } catch (IOException e) { 
	                e.printStackTrace(); 
	                } 
	      }
	  	return data; 
	  } 
	   
}

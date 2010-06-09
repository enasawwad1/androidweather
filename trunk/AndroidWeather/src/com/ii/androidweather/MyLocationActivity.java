package com.ii.androidweather;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;

import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationActivity extends ListActivity implements OnClickListener {

	TextView selection;

	String data[]={};
	public static int state = 0;
	public static String city = "";
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		//WriteSettings(this, "Skopje");
		
		data = ReadSettings(this).trim().split(",");
		
		data[data.length-1]=data[data.length-1].trim();
		//Log.v("datodeka", data[30].toString());
		setContentView(R.layout.lview);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data));
		selection = (TextView) findViewById(R.id.selection);
		//selection.setText(ReadSettings(this));
		// button
		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);
		

	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		//selection.setText(data[position]);
	    MyLocationActivity.state=1;
	    Log.v("mylocation",Integer.toString( state));
	    city=data[position];
	 AndroidWeatherTab.updateWeather(data[position]);
	
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// AndroidWeatherTab.switchTab("now");
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data));
	}

	public void WriteSettings(Context context, String data) {
		FileOutputStream fOut = null;
		OutputStreamWriter osw = null;
		try {
			fOut = openFileOutput("loc1.txt", MODE_APPEND);
			osw = new OutputStreamWriter(fOut);
			osw.write( data+",");
			osw.flush();
			Toast.makeText(context, "Settings saved", Toast.LENGTH_SHORT)
					.show();
		}

		catch (Exception e) {

			e.printStackTrace();

			Toast.makeText(context, "Settings not saved", Toast.LENGTH_SHORT)
					.show();
		}

		finally {

			try {

				osw.close();
				fOut.close();
			} catch (IOException e) {

				e.printStackTrace();

			}

		}
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.sysUI:    { 
	        	AndroidWeatherTab.chk_useSIformat=!AndroidWeatherTab.chk_useSIformat;
	        	
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
	 public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menu, menu);
		    return true;
		}
	// Read settings
	public String ReadSettings(Context context) {
		FileInputStream fIn = null;
		InputStreamReader isr = null;

		char[] inputBuffer = new char[255];
		String data = null;
		try {
			
			fIn = openFileInput("loc1.txt");
			if(fIn==null)
			{
				return " ,";
			}
			isr = new InputStreamReader(fIn);
			isr.read(inputBuffer);
			data = new String(inputBuffer);
			Toast.makeText(context, "Settings read", Toast.LENGTH_SHORT).show();
			
			}
		
		
		catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "Settings not read", Toast.LENGTH_SHORT)
					.show();
		} finally {
			try {
				if(fIn==null)
				{
					return "";
				}
				isr.close();
				fIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(
				MyLocationActivity.this);

		alert.setTitle("New Location");
		alert.setMessage("Message");

		// Set an EditText view to get user input
		final EditText input = new EditText(MyLocationActivity.this);
		alert.setView(input);

		alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				WriteSettings(MyLocationActivity.this, value.toString());
				data = ReadSettings(MyLocationActivity.this).split(",");
				data[data.length-1]=data[data.length-1].trim();
				onResume();
				// Do something with value!
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();

	}

}

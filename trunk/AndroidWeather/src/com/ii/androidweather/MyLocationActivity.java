package com.ii.androidweather;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class MyLocationActivity extends Activity{
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        TextView textview = new TextView(this);
	        textview.setText("My Location Tab");
	        setContentView(textview);
	    }
}

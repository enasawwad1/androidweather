package com.ii.androidweather;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class Broadt3DaysActivity extends Activity{
		   public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);

		        TextView textview = new TextView(this);
		        textview.setText("3 Days Tab");
		        setContentView(textview);
		    }

	}

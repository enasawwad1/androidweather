package com.ii.androidweather;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProbaActivity  extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.pocetno);
	TextView tv=(TextView)findViewById(R.id.TextView01);
	tv.setText("Darered");
}
}

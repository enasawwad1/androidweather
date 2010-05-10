package com.ii.androidweather;

import android.app.Activity;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class ProbaActivity  extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.pocetno);
	TabHost tabs=(TabHost)findViewById(R.id.tabhost);
	tabs.setup();
	TabHost.TabSpec spec=tabs.newTabSpec("tag1");
	spec.setContent(R.id.tab1);
	spec.setIndicator("New");
	tabs.addTab(spec);
	spec=tabs.newTabSpec("tag2");
	spec.setContent(R.id.tab2);
	spec.setIndicator("3 Days");
	tabs.addTab(spec);
	spec=tabs.newTabSpec("tag3");
	spec.setContent(R.id.tab3);
	spec.setIndicator("My Location");
	tabs.addTab(spec);
	tabs.setCurrentTab(0);
	
	
}
}



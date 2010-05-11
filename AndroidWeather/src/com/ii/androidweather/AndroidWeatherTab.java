package com.ii.androidweather;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class AndroidWeatherTab extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.pocetno);

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

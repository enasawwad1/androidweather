/*package com.ii.androidweather;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class NotificationDemo extends Activity {
  private static final int NOTIFY_ME_ID=1337;
  private Timer timer=new Timer();
  private int count=0;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    Button btn=(Button)findViewById(R.id.notify);
    btn.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        TimerTask task=new TimerTask() {
          public void run() {
            notifyMe();
          }
        };
    
        timer.schedule(task, 5000);
      }
    });
    
    btn=(Button)findViewById(R.id.cancel);
    
    btn.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        NotificationManager mgr=
          (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
          
        mgr.cancel(NOTIFY_ME_ID);
      }
    });
  }
  
  private void notifyMe() {
    final NotificationManager mgr=
      (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    Notification note=new Notification(R.drawable.red_ball,
                                       "Status message!",
                                       System.currentTimeMillis());
    PendingIntent i=PendingIntent.getActivity(this, 0,
                           new Intent(this, NotifyMessage.class),
                                            0);
    
    note.setLatestEventInfo(this, "Notification Title",
                           "This is the notification message", i);
    note.number=++count;
    
    mgr.notify(NOTIFY_ME_ID, note);
  }
}*/
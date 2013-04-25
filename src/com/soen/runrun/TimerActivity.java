package com.soen.runrun;

import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class TimerActivity extends Activity {

	int time = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		Preference preference = (Preference)intent.getParcelableExtra("com.soen.runrun.preference");
		
		
		setContentView(R.layout.activity_timer);
		
		
		//Declare the timer
		Timer t = new Timer();
		//Set the schedule function and rate
		t.scheduleAtFixedRate(new TimerTask() {

		    @Override
		    public void run() {
		    	runOnUiThread(new Runnable() {

		    	    @Override
		    	    public void run() {
		    	    	
		    	    	long millis = System.currentTimeMillis() - time;
		    	        int seconds = (int) (millis / 1000);
		    	        int minutes = seconds / 60;
		    	        seconds     = seconds % 60;
		    	    	
		    	        TextView tv = (TextView) findViewById(R.id.timer);
		    	        tv.setText(String.format("%d:%02d", minutes, seconds));
		    	        time += 1;
		    	    }
		    	     
		    	});
		    }
		         
		},
		//Set how long before to start calling the TimerTask (in milliseconds)
		0,
		//Set the amount of time between each execution (in milliseconds)
		1000);
		
		playAudio(R.raw.above,true);
		
		 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timer, menu);
		return true;
	}
	
	 public void playAudio(int id, boolean isLoop){
		 
		 MediaPlayer mp = MediaPlayer.create(this, id);
			
		    try {
		        mp.setLooping(isLoop);
		        mp.start();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		}

}

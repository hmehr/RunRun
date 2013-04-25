package com.soen.runrun;

import java.io.FileInputStream;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class TimerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		Preference preference = (Preference)intent.getParcelableExtra("com.soen.runrun.preference");
		
		
		setContentView(R.layout.activity_timer);
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

package com.soen.runrun;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends Activity implements OnClickListener {

	private CountDownTimer countDownTimer;
	private boolean timerHasStarted = false;
	private Button startB;
	public TextView text;
	private static long startTime = 0;
	private final long interval = 1 * 1000;
	static int i = 0;
	Preference preference = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		preference = (Preference) intent
				.getParcelableExtra("com.soen.runrun.preference");
		
		startTime = preference.getInitialTimeInterval() * 1000;
		
		setContentView(R.layout.activity_timer);

		
		startB = (Button) this.findViewById(R.id.button1);
		startB.setOnClickListener(this);
		text = (TextView) this.findViewById(R.id.timer);
		countDownTimer = new MyCountDownTimer(startTime, interval);
		text.setText(text.getText() + String.valueOf(startTime / 1000));
		i = 1;

	}

	private void countDown() {
		startB = (Button) this.findViewById(R.id.button1);
		startB.setOnClickListener(this);
		text = (TextView) this.findViewById(R.id.timer);
		countDownTimer = new MyCountDownTimer(startTime, interval);
		text.setText(text.getText() + String.valueOf(startTime / 1000));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timer, menu);
		return true;
	}

	public void playAudio(int id, boolean isLoop) {

		MediaPlayer mp = MediaPlayer.create(this, id);

		try {
			mp.setLooping(isLoop);
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	

	@Override
	public void onClick(View v) {
		if (!timerHasStarted) {
			countDownTimer.start();
			timerHasStarted = true;
			startB.setText("STOP");
		} else {
			countDownTimer.cancel();
			timerHasStarted = false;
			startB.setText("RESTART");
		}
	}
	
	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			text.setText("Lap completed");
			i = 2;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			startTime =  (long) Math.floor((startTime -(preference.decrementRate*startTime/100)));
			start();
			timerHasStarted = true;
			
		}

		@Override
		public void onTick(long millisUntilFinished) {
			text.setText("" + millisUntilFinished / 1000);
		}
	}

}

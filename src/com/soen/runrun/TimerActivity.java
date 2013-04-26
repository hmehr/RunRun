package com.soen.runrun;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

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
	public TextView infoText;
	public TextView lapsCompletedText;
	public TextView totalDistanceText;
	public TextView topSpeedText;
	public TextView totalTimeText;
	public TextView initialSpeedText;
	public TextView averageSpeedText;
	private static long startTime = 0;
	private final long interval = 1 * 1000;
	public static Run run = null;
	Preference preference = null;
	int counter = 1;
	int beepId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);

		Intent intent = getIntent();
		preference = (Preference) intent
				.getParcelableExtra("com.soen.runrun.preference");
		
		setBeepId();	
			
		
		run = new Run();
		run.setInitialSpeed((float)preference.getDistance()
				/ (float)preference.getInitialTimeInterval());
		startTime = preference.getInitialTimeInterval() * 1000;
		countDownTimer = new MyCountDownTimer(startTime, interval);

		startB = (Button) this.findViewById(R.id.button1);
		startB.setOnClickListener(this);

		text = (TextView) this.findViewById(R.id.timer);
		infoText = (TextView) this.findViewById(R.id.Info);
		lapsCompletedText = (TextView) this.findViewById(R.id.lapsCompletedTxt);
		totalDistanceText = (TextView) this.findViewById(R.id.totalDistanceTxt);
		topSpeedText = (TextView) this.findViewById(R.id.topSpeedTxt);
		totalTimeText = (TextView) this.findViewById(R.id.totalTimeTxt);
		initialSpeedText = (TextView) this.findViewById(R.id.initialSpeedTxt);
		averageSpeedText = (TextView) this.findViewById(R.id.averageSpeedTxt);

		text.setText(text.getText() + String.valueOf(startTime / 1000));

	}

	private boolean setBeepId() {
		if (preference.getBeepSound() == null)
		{
			beepId = R.raw.jungle;
			return false;
		}
		if (preference.getBeepSound() == "blue")
			beepId = R.raw.blue;
		else if (preference.getBeepSound() == "jungle")
			beepId = R.raw.jungle;
		else if (preference.getBeepSound() == "desert")
			beepId = R.raw.desert;
		else
			beepId = R.raw.jungle;
		return true;
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

	public void saveLog(String str) throws Exception {
		String filePath = getFilesDir().toString() + "/log.txt";
		FileOutputStream out = new FileOutputStream(filePath, true);
		out.write(str.getBytes());

	}

	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {

			if (counter % 2 == 0)// it means that the lap is complete
			{

				run.setTopSpeed(((float)preference.getDistance() / ((float)startTime / 1000)));
				run.setTotalTime(run.getTotalTime() + (int) (startTime / 1000));
				startTime = (long) ((long) 1000* Math
						.floor((startTime - (preference.decrementRate * startTime / 100))/1000));

				run.setLapsCompleted(run.getLapsCompleted() + 1);
				run.setTotalDistance(run.getLapsCompleted()
						* preference.getDistance());
				run.setAverageSpeed((float)run.getTotalDistance() /(float)run.getTotalTime());
				lapsCompletedText
						.setText(String.valueOf(run.getLapsCompleted()));
				totalDistanceText
						.setText(String.valueOf(run.getTotalDistance()));
				topSpeedText.setText(String.valueOf(new DecimalFormat("0.00").format(run.getTopSpeed())));
				totalTimeText.setText(String.valueOf(run.getTotalTime()));
				initialSpeedText.setText(String.valueOf(new DecimalFormat("0.00").format(run.getInitialSpeed())));
				averageSpeedText.setText(String.valueOf(new DecimalFormat("0.00").format(run.getAverageSpeed())));
				playAudio(beepId, false);

			}
			counter++;
			this.cancel();
			if (startTime > 2000) {
				countDownTimer = new MyCountDownTimer((startTime), interval);

				countDownTimer.start();
				timerHasStarted = true;

			}

			else {
				infoText.setText("Lap finished");

				String logText = "\r\nName:" + preference.getRunnerName() + " \r\n "
						+ "Top Speed: " + run.getTopSpeed() + "\r\n"
						+ "Laps Completed: " + run.getLapsCompleted() + "\r\n"
						+ "Total Distance: " + run.getTotalDistance() + "\r\n"
						+ "Total Time: " + run.getTotalTime() + "\r\n"
						+ "Initial Speed: " + run.getInitialSpeed() + "\r\n"
						+ "Average Speed: " + run.getAverageSpeed() +"\r\n"+
						"--------------\r\n";
				
				try {
					saveLog(logText);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		@Override
		public void onTick(long millisUntilFinished) {
			text.setText("" + millisUntilFinished / 1000);
		}
	}

}

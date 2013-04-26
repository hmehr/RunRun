package com.soen.runrun;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class LogActivity extends Activity {

	EditText logText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		
		logText = (EditText)findViewById(R.id.logText);
		try {
			logText.setText(readLogFile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String readLogFile() throws Exception
	{
		String everything = "";
		BufferedReader br = new BufferedReader(new FileReader( getFilesDir().toString() + "/log.txt"));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        everything = sb.toString();
	    } finally {
	        br.close();
	    }
	    
	    return everything;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log, menu);
		return true;
	}

}

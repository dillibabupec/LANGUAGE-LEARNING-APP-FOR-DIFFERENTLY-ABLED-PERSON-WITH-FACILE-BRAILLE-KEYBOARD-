package com.example.languagelearn;



import java.util.Locale;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class English_Testing3 extends Activity implements OnInitListener {

	String braille="o";
	private TextToSpeech tts;
	Button result1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_english__testing3);
		result1=(Button)findViewById(R.id.button1);
		tts = new TextToSpeech(this, this);
		result1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				speakOut();
			}
			
		});
		
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
		    //Do something after 100ms
			  GetDeviceList1 gdlist1 = new GetDeviceList1();
				gdlist1.execute();
			  Toast.makeText(getApplicationContext(), "Waiting", Toast.LENGTH_LONG).show();
		  }
		}, 10000);
		
		
	}
	
	private class GetDeviceList1 extends AsyncTask<String, Void, Void> {
		String list=null;
		
		protected void onPreExecute() {
			Toast.makeText(getApplicationContext(), "Waiting for Response", Toast.LENGTH_LONG).show();
		}
		
		protected Void doInBackground(String... params) {
			list=CallService.getDeviceList1("getDeviceList1");	
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {	
		
			if(list.equals(braille))
			{
				//result1.setText(list);
				tts.speak("Correct",TextToSpeech.QUEUE_FLUSH, null);
				Intent inn=new Intent(getApplicationContext(),Lessons.class);
				startActivity(inn);
			}
			else
			{
				//result1.setText(list);
				tts.speak("Sorry Incorrect try again ",TextToSpeech.QUEUE_FLUSH, null);
				Intent inn=new Intent(getApplicationContext(),English_Testing3.class);
				startActivity(inn);
			}
		}
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.english__testing3, menu);
		return true;
	}
	
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
               result1.setEnabled(true);
                speakOut();
            }
 
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
	}
	
	@SuppressWarnings("deprecation")
	private void speakOut() {
		// TODO Auto-generated method stub
		//String text = txtText.getText().toString();
		tts.speak("Enter the braille code for O through the braille keyboard",TextToSpeech.QUEUE_FLUSH, null);
		
	}

}

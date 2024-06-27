package com.example.languagelearn;

import java.util.ArrayList;
import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class German_lesson extends Activity implements OnInitListener {

	private TextToSpeech tts;
	Button cts;
	ImageView mic;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_german_lesson);
		cts=(Button)findViewById(R.id.button5);
		mic=(ImageView)findViewById(R.id.imageView1);
		username=getIntent().getStringExtra("username");
		tts = new TextToSpeech(this, this);
		cts.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				speakOut();
			}
			
		});
		
		mic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				promptSpeechInput();
			}
		});
	}
	
	private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case REQ_CODE_SPEECH_INPUT: {
            if (resultCode == RESULT_OK && null != data) {
 
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String voice=result.get(0);
                Toast.makeText(getApplicationContext(), voice, Toast.LENGTH_LONG).show();
                if(voice.equalsIgnoreCase("lesson 1"))
                {
                	Intent inn=new Intent(getApplicationContext(),A_letter.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                if(voice.equalsIgnoreCase("to"))
                {
                	Intent inn=new Intent(getApplicationContext(),G_letter.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                if(voice.equalsIgnoreCase("free"))
                {
                	Intent inn=new Intent(getApplicationContext(),M_letter.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                if(voice.equalsIgnoreCase("4"))
                {
                	Intent inn=new Intent(getApplicationContext(),S_letter.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                if(voice.equalsIgnoreCase("5"))
                {
                	Intent inn=new Intent(getApplicationContext(),Extra_german.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                if(voice.equalsIgnoreCase("previous"))
                {
                	Intent inn=new Intent(getApplicationContext(),Languages.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
               
                }
               // type.setText(result.get(1));
                //voice=txtSpeechInput.getText().toString();
                
                
            }
            break;
        }
 
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.french_lesson, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                cts.setEnabled(true);
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
		tts.speak("Tap The LOwer HAlf of Your screen and say which lesson you want to Choose lesson1 or 2 or 3 or 4 or 5  or say previous if u want to go to language selection page",TextToSpeech.QUEUE_FLUSH, null);
		
	}
	

}

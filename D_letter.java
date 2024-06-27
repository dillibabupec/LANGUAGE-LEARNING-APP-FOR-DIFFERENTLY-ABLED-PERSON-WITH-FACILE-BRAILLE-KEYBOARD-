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

public class D_letter extends Activity implements OnInitListener {

	private TextToSpeech tts;
	Button cts;
	ImageView mic;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d_letter);
		cts=(Button)findViewById(R.id.button1);
		mic=(ImageView)findViewById(R.id.imageView1);
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
                if(voice.equalsIgnoreCase("previous"))
                {
                	Intent inn=new Intent(getApplicationContext(),C_letter.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                else if(voice.equalsIgnoreCase("next"))
                {
                	Intent inn=new Intent(getApplicationContext(),E_letter.class);
                	inn.putExtra("Username", username);
                	startActivity(inn);
                }
                else
                {
                	tts.speak("Dont Speak Wrong",TextToSpeech.QUEUE_FLUSH, null);
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
		getMenuInflater().inflate(R.menu.d_letter, menu);
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
		tts.speak("Your Letter is D and Your Braille Code is one four five",TextToSpeech.QUEUE_FLUSH, null);
		
	}

}

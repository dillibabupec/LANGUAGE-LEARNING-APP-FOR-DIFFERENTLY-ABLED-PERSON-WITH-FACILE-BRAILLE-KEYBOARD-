package com.example.languagelearn;

import java.util.ArrayList;

import java.util.Locale;
import android.os.AsyncTask;
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

public class Languages extends Activity implements OnInitListener {

	private TextToSpeech tts;
	Button lang;
	ImageView mic;
	String English,German,French;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	String username;
	String language;
	String voice;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_languages);
		lang=(Button)findViewById(R.id.button1);
		
		mic=(ImageView)findViewById(R.id.imageView1);
		
		username=getIntent().getStringExtra("username");
		
		tts = new TextToSpeech(this, this);
		lang.setOnClickListener(new OnClickListener(){

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
 
                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                voice=result.get(0);
                Toast.makeText(getApplicationContext(), voice, Toast.LENGTH_LONG).show();
                /*ActiveTask1 task1=new ActiveTask1();
    			task1.execute();*/
                if(voice.equalsIgnoreCase("English"))
                {
                	language=voice;
                	/*ActiveTask1 task1 =new ActiveTask1();
        			task1.execute();*/
                	Intent inn=new Intent(getApplicationContext(),Lessons.class);
                	startActivity(inn);
                }
                else if(voice.equalsIgnoreCase("French"))
                {
                	language=voice;
                	/*ActiveTask1 task1=new ActiveTask1();
        			task1.execute();*/
                	Intent inn=new Intent(getApplicationContext(),French_lesson.class);
                	startActivity(inn);
                }
                else if(voice.equalsIgnoreCase("German"))
                {
                	language=voice;
                	/*ActiveTask1 task1=new ActiveTask1();
        			task1.execute();*/
                	Intent inn=new Intent(getApplicationContext(), German_lesson.class);
                	startActivity(inn);
                }
                else if(voice.equalsIgnoreCase("Previous"))
                {
                	language=voice;
                	/*ActiveTask1 task1=new ActiveTask1();
        			task1.execute();*/
                	Intent inn=new Intent(getApplicationContext(), MainActivity.class);
                	startActivity(inn);
                }
                else
                {
                	tts.speak("Don't Speak Wrong", TextToSpeech.QUEUE_FLUSH, null);
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
		getMenuInflater().inflate(R.menu.languages, menu);
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
                lang.setEnabled(true);
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
		tts.speak("Tap The LOwer HAlf of Your Screen and choose Language English, French or German or say previous to go to Registration Page", TextToSpeech.QUEUE_FLUSH, null);
		
	}
	
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
		String res=null;
		String VoiceInput;
		
		protected void onPreExecute() {
			//pb.setVisibility(View.VISIBLE);
			Toast.makeText(getApplicationContext(), VoiceInput, Toast.LENGTH_LONG).show();
		}

		
		protected Void doInBackground(String... params) {
			res=CallService.languageService(username,voice,"LanguageLearn");
			return null;
		}
		
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Void result) {
			//pb.setVisibility(View.INVISIBLE);
			if(res.equalsIgnoreCase("english")) 
			{
				ActiveTask1 task=new ActiveTask1();
				task.execute();
				Intent inn=new Intent(getApplicationContext(),Lessons.class);
				inn.putExtra("username", username);
				startActivity(inn);
			}
			
			else if(res.equalsIgnoreCase("french")) 
			{
				ActiveTask1 task=new ActiveTask1();
				task.execute();
				Intent inn=new Intent(getApplicationContext(),French_lesson.class);
				inn.putExtra("username", username);
				startActivity(inn);
			}
			
			else if(res.equalsIgnoreCase("German")) 
			{
				ActiveTask1 task=new ActiveTask1();
				task.execute();
				Intent inn=new Intent(getApplicationContext(),German_lesson.class);
				inn.putExtra("username", username);
				startActivity(inn);
			}
			else 
			{
				tts.speak("It is not Connected to Database", TextToSpeech.QUEUE_FLUSH, null);
			}
			
		}
	}
	
	
	

}

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener {

	private TextToSpeech tts;
	private TextView txtSpeechInput;
	ImageView mic;
	EditText uname,type;
	Button cts;
	String voice;
	String username,utype;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	String im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mic=(ImageView)findViewById(R.id.imageView1);
        uname=(EditText)findViewById(R.id.editText1);
        type=(EditText)findViewById(R.id.editText2);
        cts=(Button)findViewById(R.id.button1);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        
        
        tts = new TextToSpeech(this, this);
        cts.setOnClickListener(new OnClickListener() {
			
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
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case REQ_CODE_SPEECH_INPUT: {
            if (resultCode == RESULT_OK && null != data) {
 
                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if(!(result.get(0).equals("blind"))||(result.get(0).equals("deaf")))
                {
                	uname.setText(result.get(0));
                }
                else 
                {
                	type.setText(result.get(0));
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
        getMenuInflater().inflate(R.menu.main, menu);
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
		username=uname.getText().toString();
		utype=type.getText().toString();
		if(username.equals(""))
		{
			tts.speak("Tap The Lower half of your screen to open the mike and say your username and then tap the top of your screen ", TextToSpeech.QUEUE_FLUSH, null);
			//txtSpeechInput.setText(voice);
			
		}
		else if(utype.equals(""))
		{
			tts.speak("Tap The Lower half of your screen say are you blind or deaf and again tap top  of your screen ", TextToSpeech.QUEUE_FLUSH, null);
			txtSpeechInput.setText(voice);
		}
		else
		{
			tts.speak("Wait Connecting to Database", TextToSpeech.QUEUE_FLUSH, null);
			ActiveTask1 task=new ActiveTask1();
			task.execute();
			
			//tts.speak("Already you filled everything! Please press mic", TextToSpeech.QUEUE_FLUSH, null);
		}
		
	}
	
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
		String username=uname.getText().toString();
		String utype=type.getText().toString();
		String res=null;
		
		protected void onPreExecute() {
			//pb.setVisibility(View.VISIBLE);
		}

		
		protected Void doInBackground(String... params) {
			res=CallService.registerService(username,utype,"Register");
			return null;
		}
		
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Void result) {
			//pb.setVisibility(View.INVISIBLE);
			if(res.equals("blind")) 
			{
				Intent inn=new Intent(getApplicationContext(),Languages.class);
				inn.putExtra("username", username);
				startActivity(inn);
			}
			else if(res.equals("deaf"))
			{
				Intent inn=new Intent(getApplicationContext(), Signlang.class);
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

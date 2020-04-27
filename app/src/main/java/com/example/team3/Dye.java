package com.example.team3;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class Dye extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {
    private final String tag = "dye";
    private Button btnSpeech;
    private TextToSpeech speaker;
    private TextView dyeInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dye);

        dyeInstructions = (TextView)findViewById(R.id.dyeInstructions);
        dyeInstructions.setMovementMethod(new ScrollingMovementMethod());
        dyeInstructions.setText(Html.fromHtml(getString(R.string.dye_instructions_html)));


        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //Text to Speech Function Added
        speaker = new TextToSpeech(this, this);

        //Set up button to initiate text to speech
        btnSpeech = (Button) findViewById(R.id.btnSpeech);
        btnSpeech.setOnClickListener (this);

    }
    //Insert Option Menu to Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //When Menu Item is Clicked - determines what to do
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i1 = new Intent(this, Home.class);
                // if speaker is talking, stop it
                if(speaker.isSpeaking()){
                    Log.i(tag, "Speaker Speaking");
                    speaker.stop();}
                startActivity(i1);
                return true;

            case R.id.products:
                Intent i2 = new Intent(this, ProductActivity.class);
                // if speaker is talking, stop it
                if(speaker.isSpeaking()){
                    Log.i(tag, "Speaker Speaking");
                    speaker.stop();}
                startActivity(i2);
                return true;

            case R.id.dye:
                Intent i3 = new Intent(this, Dye.class);
                // if speaker is talking, stop it
                if(speaker.isSpeaking()){
                    Log.i(tag, "Speaker Speaking");
                    speaker.stop();}
                startActivity(i3);
                return true;

            case R.id.cart:
                Intent i4 = new Intent(this, Cart.class);
                // if speaker is talking, stop it
                if(speaker.isSpeaking()){
                    Log.i(tag, "Speaker Speaking");
                    speaker.stop();}
                startActivity(i4);
                return true;

            case R.id.contact:
                Intent i5 = new Intent(this, Contact.class);
                // if speaker is talking, stop it
                if(speaker.isSpeaking()){
                    Log.i(tag, "Speaker Speaking");
                    speaker.stop();}
                startActivity(i5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Read text when read button is clicked
    @Override
    public void onClick(View v) {
        speak();
    }

    //Output text to be spoken
    public void speak(){
        String text = dyeInstructions.getText().toString();
        speaker.speak(text, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
        speaker.setSpeechRate((float) 0.75);
    }

    //Set Language to US English and to speak on initiation
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            //Set preferred language to US English
            int result = speaker.setLanguage(Locale.US);
            //If language is not available,indicate that it is not supported.
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Text to Speech language is not supported",
                        Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not available.");
            } else {btnSpeech.setEnabled(true);
                Log.i("TTS", "TTS Initialization successful.");
            }
        //Initialization failed
        } else {
            Log.e("TTS", "Could not initialize TextToSpeech.");
        }
    }

    // When Text2Speech is complete - Destroy/Shutdown
    public void onDestroy(){
        if(speaker != null){
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
    }
}
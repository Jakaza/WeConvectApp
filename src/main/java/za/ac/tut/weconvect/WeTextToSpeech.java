package za.ac.tut.weconvect;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WeTextToSpeech extends AppCompatActivity {
    private TextToSpeech tts;
    private ArrayList<String> texts;
    private int currentIndex = 0;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        // Initialize TextToSpeech
        tts = new TextToSpeech((Context) this, (TextToSpeech.OnInitListener) this);

        // Add texts to the ArrayList
        texts = new ArrayList<>();
        texts.add("Hello");
        texts.add("How are you?");
        texts.add("What is your name?");

        textView = findViewById(R.id.text_speech);


        // Set up buttons to read text aloud
        Button prevButton = findViewById(R.id.prev_button);
        Button nextButton = findViewById(R.id.next_button);


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = texts.size() - 1;
                }
                textView.setText(texts.get(currentIndex));
                speakText(texts.get(currentIndex));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex >= texts.size()) {
                    currentIndex = 0;
                }
                textView.setText(texts.get(currentIndex));
                speakText(texts.get(currentIndex));

            }
        });
    }


    private void speakText(String text) {
        if (text != null && !text.isEmpty()) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}